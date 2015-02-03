package com.rotterdam.service;

import com.rotterdam.dto.*;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.*;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.PeriodDefiner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 30.01.15.
 */
@Named
public class OverViewService {

    @Inject
    private PeriodDefiner periodDefiner;

    @Inject
    private WeekService weekService;

    @Inject
    private TimeForService timeForService;

    @Inject
    private PeriodDao periodDao;

    @Inject
    private UserDao userDao;

    @Transactional
    public OverViewDto getOverView(OverViewDto overViewDto, long userId) {

        User user = userDao.selectById(userId);
        overViewDto.user = new UserDto(user.getFirstname(), user.getSurname());

        if(overViewDto.date == null)
            return overViewDto;
        List<Date> startingDaysOfWeeksOfCurrentPeriod =
                periodDefiner.getStartingDaysOfWeeksOfCurrentPeriod(overViewDto.date, PeriodType.FOURWEEK);

        Date start = startingDaysOfWeeksOfCurrentPeriod.get(0);
        Date end = DateTools.getDatePlusDays(startingDaysOfWeeksOfCurrentPeriod.get(3), 6);
        overViewDto.startEnd = new StartEndDto(start, end);

        if(overViewDto.usedWeeks == null)
            return overViewDto;
        for (int i = 0; i < overViewDto.usedWeeks.size(); i++){
            Date startDate = startingDaysOfWeeksOfCurrentPeriod.get(overViewDto.usedWeeks.get(i) - 1);
            WeekDto weekDto = weekService.getWeekByStartDateAndUserId(startDate, userId);

            //just add it
            WeekOverViewDto weekOverViewDto = new WeekOverViewDto(weekDto);
            weekOverViewDto.calculateTotal();
            overViewDto.weekList.add(weekOverViewDto);
        }


        return overViewDto;
    }

    @Transactional
    public OverViewDetailDto getOverViewDetail(Date date, long userId){
        OverViewDetailDto overViewDetailDto = new OverViewDetailDto();

        Period period = periodDao.selectByDateBetweenAndUser(date, userId);
        calculateTotal(overViewDetailDto, period);
        calculate130(overViewDetailDto, period);
        calculate150(overViewDetailDto, period);
        calculate200(overViewDetailDto, period);
        calculateWeekDates(overViewDetailDto, period);
        TimeForDto timeForOfPrevPeriod = timeForService.getTimeForOfPrevPeriod(date, userId);
        overViewDetailDto.overTime = timeForOfPrevPeriod.overTime;

        return overViewDetailDto;
    }

    private void calculateWeekDates(OverViewDetailDto overViewDetailDto, Period period) {
        List<Date> startingDays = periodDefiner.getStartingDaysOfWeeksOfCurrentPeriod(period.getStartDate(), PeriodType.FOURWEEK);
        for (Date date : startingDays){
            overViewDetailDto.weekDates.add(
                    new StartEndDto(date, DateTools.getDatePlusDays(date, 6))
            );
        }
    }

    private void calculate130(OverViewDetailDto overViewDetailDto, Period period){
        //calculate mon-fri

        double promisedPeriodTime = 0;
        double periodTime = 0;
        for (Week week : period.getWeeks()){
            double timeDays = 0;
            for (Day day : week.getDays()){
                String weekDayTitle = DateTools.getWeekDayTitle(day.getDate());
                if(weekDayTitle.equals("Saturday") || weekDayTitle.equals("Sunday"))
                    continue;
                if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                    RideType rideType = day.getWorkHours().get(0).getRideType();
                    if(rideType.equals(RideType.Werkdag)) {
                        for (WorkHour workHour : day.getWorkHours()) {
                            double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                            double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                            int restTime = workHour.getRestTime() / 60;
                            timeDays += endTime - startTime - restTime;
                        }
                    } else {
                        if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                            Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                            timeDays += DateTools.getDoubleFormatHours(promisedTimeByDate);
                        }
                    }
                }
            }
            promisedPeriodTime += timeForService.getPromisedWeekTime(week);
            periodTime += timeDays;
        }



        if(periodTime > promisedPeriodTime)
            overViewDetailDto.total130 = periodTime - promisedPeriodTime;
        else overViewDetailDto.total130 = (double) 0;
    }

    private void calculate150(OverViewDetailDto overViewDetailDto, Period period){
        double worked = 0;
        for (Week week : period.getWeeks()){
            for(Day day : week.getDays()){
                if(DateTools.getWeekDayTitle(day.getDate()).equals("Saturday")){
                    if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                        RideType rideType = day.getWorkHours().get(0).getRideType();
                        if(rideType.equals(RideType.Werkdag)) {
                            for (WorkHour workHour : day.getWorkHours()) {
                                double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                                double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                                int restTime = workHour.getRestTime() / 60;
                                worked += endTime - startTime - restTime;
                            }
                        } else {
                            if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                                Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                                worked += DateTools.getDoubleFormatHours(promisedTimeByDate);
                            }
                        }
                    }
                }
            }
        }
            overViewDetailDto.total150 = worked;
    }

    private void calculate200(OverViewDetailDto overViewDetailDto, Period period){
        double worked = 0;
        for (Week week : period.getWeeks()){
            for(Day day : week.getDays()){
                if(DateTools.getWeekDayTitle(day.getDate()).equals("Sunday")){
                    if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                        RideType rideType = day.getWorkHours().get(0).getRideType();
                        if(rideType.equals(RideType.Werkdag)) {
                            for (WorkHour workHour : day.getWorkHours()) {
                                double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                                double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                                int restTime = workHour.getRestTime() / 60;
                                worked += endTime - startTime - restTime;
                            }
                        } else {
                            if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                                Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                                worked += DateTools.getDoubleFormatHours(promisedTimeByDate);
                            }
                        }
                    }
                }
            }
        }
        overViewDetailDto.total200 = worked;
    }

    private void calculateTotal(OverViewDetailDto overViewDetailDto, Period period){
        double periodTime = 0;
        for (Week week : period.getWeeks()){
            double timeDays = 0;
            for (Day day : week.getDays()){
                if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                    RideType rideType = day.getWorkHours().get(0).getRideType();
                    if(rideType.equals(RideType.Werkdag)) {
                        for (WorkHour workHour : day.getWorkHours()) {
                            double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                            double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                            int restTime = workHour.getRestTime() / 60;
                            timeDays += endTime - startTime - restTime;
                        }
                    } else {
                        if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                            Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                            timeDays += DateTools.getDoubleFormatHours(promisedTimeByDate);
                        }
                    }
                }
            }
            periodTime += timeDays;
        }

        overViewDetailDto.total = periodTime;

    }




}
