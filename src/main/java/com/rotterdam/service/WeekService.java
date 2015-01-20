package com.rotterdam.service;

import com.rotterdam.dto.DayDto;
import com.rotterdam.dto.WeekDto;
import com.rotterdam.dto.WorkHourDto;
import com.rotterdam.model.dao.DayDao;
import com.rotterdam.model.dao.WeekDao;
import com.rotterdam.model.dao.WorkHoursDao;
import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.Week;
import com.rotterdam.model.entity.WorkHour;
import com.rotterdam.tools.DateTools;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
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

    @Transactional
    public boolean save(WeekDto weekDto, long userId){
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

                //we need to sort to ensure order
                Collections.sort(dayDto.workHours, WorkHourDto.workHourDtoComparatorByStartWorkingTime);
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
        } else {
            //it's possible if user not save setting tab
            return false;
        }
        return true;
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

    @Transactional
    public WeekDto getWeekByStartDateAndUserId(Date startDate, long userId){
        Week week = weekDao.selectByStartDateAndUser(startDate, userId);
        return convertToWorkHourDto(week.getDays());
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
}
