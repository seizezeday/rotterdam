package com.rotterdam.service;

import com.rotterdam.dto.*;
import com.rotterdam.model.dao.DayDao;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.dao.WeekDao;
import com.rotterdam.model.dao.WorkHoursDao;
import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.Period;
import com.rotterdam.model.entity.Week;
import com.rotterdam.model.entity.WorkHour;
import com.rotterdam.tools.DateTools;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 20.01.15.
 */
@Named
@Scope("singleton")
public class WeekService {
    @Inject
    private WeekDao weekDao;

    @Inject
    private DayDao dayDao;

    @Inject
    private WorkHoursDao workHoursDao;

    @Inject
    private PeriodDao periodDao;

    @Transactional
    public TotalTimeDto save(WeekDto weekDto, long userId){
        TotalTimeDto totalTime = null;
        //we need to find corresponding week
        Week week = weekDao.selectByStartDateAndUser(weekDto.days.get("Monday").date, userId);
        if (week != null) {
            //we need to determine what was changed or we can override
            for(DayDto dayDto :weekDto.days.values() ){
                Day day = determineDayByDate(week.getDays(), dayDto.date);
                if(day == null){
                    //we need to create new day entry
                    day = new Day(dayDto.date, week);
                    dayDao.insert(day);

                } else{
                    //day is existing
                }

                //auto-rest enabled
                checkRestTime(weekDto);

                //we need to sort to ensure order
                if(dayDto.workHours != null)
                    Collections.sort(dayDto.workHours, WorkHourDto.workHourDtoComparatorByStartWorkingTime);
                if(day.getWorkHours() != null)
                Collections.sort(day.getWorkHours(), WorkHour.workHourComparatorByStartWorkingTime);

                if(isDayChanged(dayDto.workHours, day.getWorkHours())){
                    //day changed, we need to override all workHours
                    //first remove from db
                    if(day.getWorkHours() != null)
                        for(WorkHour workHour : day.getWorkHours())
                            workHoursDao.remove(workHour);
                    //now insert new changed workHours
                    List<WorkHour> workHours = convertToWorkHour(dayDto.workHours, day);
                    for (WorkHour workHour : workHours)
                        workHoursDao.insert(workHour);
                    //save day to db
                    day.setWorkHours(workHours);
                    dayDao.update(day);
                } else{
                    //nothing changed
                }

            }
            //we need to calculate totalTime
            totalTime = calculateTotalTime(weekDto);
        } else {
            //it's possible if user not save setting tab
            return null;
        }
        return totalTime;
    }

    private Day determineDayByDate(List<Day> days, Date date){
        for (Day day : days)
            if(day.getDate().equals(date))
                return day;
        return null;
    }

    private boolean isDayChanged(List<WorkHourDto> workHourDtos, List<WorkHour> workHours){
        if(workHours == null || workHourDtos == null)
            return true;
        if(workHourDtos.size() != workHours.size())
            return true;

        for(int i = 0; i < workHours.size(); i++){
            WorkHour workHour = workHours.get(i);
            WorkHourDto workHourDto = workHourDtos.get(i);
            if(!workHour.getStartWorkingTime().equals(workHourDto.startWorkingTime))
                return true;
            if(!workHour.getEndWorkingTime().equals(workHourDto.endWorkingTime))
                return true;
            if(!new Integer(workHour.getRestTime()).equals(workHourDto.restTime))
                return true;
            if(!workHour.getRideType().equals(workHourDto.rideType))
                return true;
        }
        return false;
    }

    private List<WorkHour> convertToWorkHour(List<WorkHourDto> workHourDtos, Day dayEntity){
        List<WorkHour> workHours = new ArrayList<>();
        for(WorkHourDto workHourDto : workHourDtos){
            WorkHour workHour = new WorkHour();
            workHour.setDay(dayEntity);
            workHour.setStartWorkingTime(workHourDto.startWorkingTime);
            workHour.setEndWorkingTime(workHourDto.endWorkingTime);
            workHour.setRestTime(workHourDto.restTime);
            workHour.setRideType(workHourDto.rideType);
            workHours.add(workHour);
        }
        return workHours;
    }

    private void checkRestTime(WeekDto weekDto){
        for (DayDto dayDto : weekDto.days.values())
            for (WorkHourDto workHourDto : dayDto.workHours){
                if(workHourDto.restTime == 0){

                    double endTime = DateTools.getDoubleFormatHours(workHourDto.endWorkingTime);
                    double startTime = DateTools.getDoubleFormatHours(workHourDto.startWorkingTime);
                    double time = endTime - startTime;

                         if (time >=  4.5 && time <   7.5) workHourDto.restTime = 30;
                    else if (time >=  7.5 && time <  10.5) workHourDto.restTime = 60;
                    else if (time >= 10.5 && time <  13.5) workHourDto.restTime = 90;
                    else if (time >= 13.5 && time <  16.5) workHourDto.restTime = 120;
                    else if (time >= 16.5 && time <= 13.5) workHourDto.restTime = 150;

                }
            }
    }

    final DecimalFormat df = new DecimalFormat("00.00");

    private TotalTimeDto calculateTotalTime(WeekDto weekDto){
        TotalTimeDto totalTimeDto = new TotalTimeDto();
        double totalTime = 0;
        for (DayDto dayDto : weekDto.days.values())
            for (WorkHourDto workHourDto : dayDto.workHours){
                double startTime = DateTools.getDoubleFormatHours(workHourDto.startWorkingTime);
                double endTime = DateTools.getDoubleFormatHours(workHourDto.endWorkingTime);
                double rest = ((double)workHourDto.restTime) / (double)60;

                double totalTimeDay = (endTime - startTime - rest);

                totalTimeDto.days.put(DateTools.getWeekDayTitle(dayDto.date), df.format(totalTimeDay));

                totalTime += totalTimeDay;
            }
        totalTimeDto.totalTime = totalTime;
        return totalTimeDto;
    }

    @Transactional
    public WeekDto getWeekByStartDateAndUserId(Date startDate, long userId){
        Week week = weekDao.selectByStartDateAndUser(startDate, userId);
        if(week != null)
            return convertToWorkHourDto(week.getDays());
        else return new WeekDto();
    }

    private WeekDto convertToWorkHourDto(List<Day> days){
        WeekDto weekDto = new WeekDto();
        for(Day day : days){
            DayDto dayDto = new DayDto();
            dayDto.date = day.getDate();
            for (WorkHour workHour : day.getWorkHours()) {
                WorkHourDto workHourDto = new WorkHourDto();
                workHourDto.startWorkingTime = workHour.getStartWorkingTime();
                workHourDto.endWorkingTime = workHour.getEndWorkingTime();
                workHourDto.restTime = workHour.getRestTime();
                workHourDto.rideType = workHour.getRideType();
                dayDto.workHours.add(workHourDto);
            }
            weekDto.days.put(DateTools.getWeekDayTitle(dayDto.date), dayDto);
        }
        return weekDto;
    }

    @Transactional
    public void save(SettingsDto settingsDto, long userId){
        //check date
        settingsDto.currentDate = DateTools.getDateOfPrevMonday(settingsDto.currentDate);

        Week week = weekDao.selectByStartDateAndUser(settingsDto.currentDate, userId);

        if(week == null){
          //need to create new week
            week = new Week();
            week = copyDaysOfWeek(week, settingsDto);
            //we need to find corresponding period
            Period period = periodDao.selectByDateBetweenAndUser(settingsDto.currentDate, userId);
            if(period == null){
                //logic error
                return;
            }
            week.setPeriod(period);
            week.setStartDate(settingsDto.currentDate);
            week.setEndDate(DateTools.getDateOf7DayAfter(settingsDto.currentDate));

            //now save to db
            weekDao.insert(week);
        } else {
            week = copyDaysOfWeek(week, settingsDto);
            weekDao.update(week);
        }
    }

    private Week copyDaysOfWeek(Week week, SettingsDto settingsDto){
        week.setPromiseMondayTime(settingsDto.monday_hours);
        week.setPromiseTuesdayTime(settingsDto.tuesday_hours);
        week.setPromiseWednesdayTime(settingsDto.wednesday_hours);
        week.setPromiseThursdayTime(settingsDto.thursday_hours);
        week.setPromiseFridayTime(settingsDto.friday_hours);
        week.setPromiseSaturdayTime(settingsDto.saturday_hours);
        week.setPromiseSundayTime(settingsDto.sunday_hours);
        return week;
    }

    private SettingsDto copyDaysOfWeek(SettingsDto settingsDto, Week week){

        settingsDto.monday_hours = week.getPromiseMondayTime();
        settingsDto.tuesday_hours = week.getPromiseTuesdayTime();
        settingsDto.wednesday_hours = week.getPromiseWednesdayTime();
        settingsDto.thursday_hours = week.getPromiseThursdayTime();
        settingsDto.friday_hours = week.getPromiseFridayTime();
        settingsDto.saturday_hours = week.getPromiseSaturdayTime();
        settingsDto.sunday_hours = week.getPromiseSundayTime();
        return settingsDto;
    }

    @Transactional
    public SettingsDto getSettings(Date date, long userId){
        date = DateTools.getDateOfPrevMonday(date);

        Week week = weekDao.selectByStartDateAndUser(date, userId);

        SettingsDto settingsDto = new SettingsDto();

        if(week == null)
            return settingsDto;

        settingsDto = copyDaysOfWeek(settingsDto, week);

        return settingsDto;
    }
}
