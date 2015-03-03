package com.rotterdam.service;

import com.rotterdam.dto.*;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.dao.WeekDao;
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

    @Inject
    private WeekDao weekDao;

    @Inject
    private DeclarationService declarationService;

    @Transactional
    public OverViewDto getOverView(OverViewDto overViewDto, long userId) {

        User user = userDao.selectById(userId);
        overViewDto.user = new UserDto(user.getFirstname(), user.getSurname(), user.getRegNum());
        overViewDto.timeForTime = (int)user.getTimeForTime();

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
            Week week = weekDao.selectByStartDateAndUser(startDate, userId);

            if(week != null) {
                //just add it
                WeekOverViewDto weekOverViewDto = new WeekOverViewDto();

                weekOverViewDto.weekNum = DateTools.getCurrentWeekNumber(week.getStartDate()) - 1;

                weekOverViewDto.startEnd = new StartEndDto(week.getStartDate(), week.getEndDate());

                weekOverViewDto.detail = new OverViewDetailDto(); //
                calculateTotal(weekOverViewDto.detail, week);
                calculate100(weekOverViewDto.detail, week);
                calculate130(weekOverViewDto.detail, week);
                calculate150(weekOverViewDto.detail, week);
                calculate200(weekOverViewDto.detail, week);

                overViewDto.weekOverViews.add(weekOverViewDto);

                overViewDto.scheduledHours += (int)timeForService.getPromisedWeekTime(week);

                //weeks
                WeekDto weekDto = weekService.getWeekByStartDateAndUserId(startDate, userId);
                weekDto.calculateTotal();
                overViewDto.weeks.add(weekDto);

                //declarations
                overViewDto.declarations.add(declarationService.selectByWeekIdAndUserId(startDate, userId));
            }
        }

        overViewDto.totalPeriodDetail = getOverViewDetail(start, userId);
        overViewDto.date = new Date();

        return overViewDto;
    }

    @Transactional
    public OverViewDetailDto getOverViewDetail(Date date, long userId){
        OverViewDetailDto overViewDetailDto = new OverViewDetailDto();

        Period period = periodDao.selectByDateBetweenAndUser(date, userId);
        calculateTotal(overViewDetailDto, period);
        calculate100(overViewDetailDto, period);
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

    private void calculate100(OverViewDetailDto overViewDetailDto, Period period){
        for (Week week : period.getWeeks())
            calculate100(overViewDetailDto, week);
    }

    private void calculate100(OverViewDetailDto overViewDetailDto, Week week){
        double weekTime = 0;
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
                        weekTime += endTime - startTime - restTime;
                    }
                } else {
                    if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                        Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                        weekTime += DateTools.getDoubleFormatHours(promisedTimeByDate);
                    }
                }
            }
        }

        double promisedWeekTime = timeForService.getPromisedWeekTime(week);

        if(weekTime > promisedWeekTime)
            overViewDetailDto.total100 += promisedWeekTime;
        else
            overViewDetailDto.total100 += weekTime;
    }

    private void calculate130(OverViewDetailDto overViewDetailDto, Period period){
        for (Week week : period.getWeeks())
            calculate130(overViewDetailDto, week);


    }

    private void calculate130(OverViewDetailDto overViewDetailDto, Week week){
        double weekTime = 0;
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
                        weekTime += endTime - startTime - restTime;
                    }
                } else {
                    if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                        Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                        weekTime += DateTools.getDoubleFormatHours(promisedTimeByDate);
                    }
                }
            }
        }
        double promisedWeekTime = timeForService.getPromisedWeekTime(week);

       if(weekTime > promisedWeekTime)
            overViewDetailDto.total130 += (weekTime - promisedWeekTime);
    }

    private void calculate150(OverViewDetailDto overViewDetailDto, Period period){
        for (Week week : period.getWeeks())
            calculate150(overViewDetailDto, week);

    }

    private void calculate150(OverViewDetailDto overViewDetailDto, Week week){
        double worked = 0;
        for(Day day : week.getDays()){
            if(DateTools.getWeekDayTitle(day.getDate()).equals("Saturday")){
                if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                    RideType rideType = day.getWorkHours().get(0).getRideType();
                    if(rideType.equals(RideType.Werkdag)) {
                        for (WorkHour workHour : day.getWorkHours()) {
                            double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                            double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                            int restTime = workHour.getRestTime() / 60;
                            worked = endTime - startTime - restTime;
                        }
                    } else {
                        if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                            Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                            worked = DateTools.getDoubleFormatHours(promisedTimeByDate);
                        }
                    }
                }
            }
        }

        overViewDetailDto.total150 += worked;
    }

    private void calculate200(OverViewDetailDto overViewDetailDto, Period period){
        for (Week week : period.getWeeks())
            calculate200(overViewDetailDto, week);

    }

    private void calculate200(OverViewDetailDto overViewDetailDto, Week week){
        double worked = 0;
        for(Day day : week.getDays()){
            if(DateTools.getWeekDayTitle(day.getDate()).equals("Sunday")){
                if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                    RideType rideType = day.getWorkHours().get(0).getRideType();
                    if(rideType.equals(RideType.Werkdag)) {
                        for (WorkHour workHour : day.getWorkHours()) {
                            double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                            double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                            int restTime = workHour.getRestTime() / 60;
                            worked = endTime - startTime - restTime;
                        }
                    } else {
                        if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                            Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                            worked = DateTools.getDoubleFormatHours(promisedTimeByDate);
                        }
                    }
                }
            }
        }
        overViewDetailDto.total200 += worked;
    }

    private void calculateTotal(OverViewDetailDto overViewDetailDto, Period period){
        for (Week week : period.getWeeks())
            calculateTotal(overViewDetailDto, week);
    }

    private void calculateTotal(OverViewDetailDto overViewDetailDto, Week week){
        double weekTime = 0;
        for (Day day : week.getDays()){
            if(day.getWorkHours() != null && day.getWorkHours().size() != 0) {
                RideType rideType = day.getWorkHours().get(0).getRideType();
                if(rideType.equals(RideType.Werkdag)) {
                    for (WorkHour workHour : day.getWorkHours()) {
                        double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                        double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                        int restTime = workHour.getRestTime() / 60;
                        weekTime += endTime - startTime - restTime;
                    }
                } else {
                    if(timeForService.isNormalCalculationNotForWorkDay(rideType)){
                        Date promisedTimeByDate = timeForService.getPromisedTimeByDate(day.getDate(), week);
                        weekTime += DateTools.getDoubleFormatHours(promisedTimeByDate);
                    }
                }
            }
        }
        overViewDetailDto.total += weekTime;
    }




}
