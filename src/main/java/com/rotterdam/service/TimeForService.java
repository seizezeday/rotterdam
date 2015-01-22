package com.rotterdam.service;

import com.rotterdam.dto.TimeForDto;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.*;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.PeriodDefiner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 22.01.15.
 */
@Named
public class TimeForService {

    @Inject
    private PeriodDefiner periodDefiner;

    @Inject
    private PeriodDao periodDao;

    @Inject
    private UserDao userDao;

    private final int HOURS_LIMIT = 220;

    @Transactional
    public TimeForDto getTimeForOfPrevPeriod(LocalDate now, long userId){
        //first we need to get previous period
        //Period lastPeriod = periodDao.selectPrevPeriodByUser(userId);
        Period lastPeriod = getPrevPeriod(now, userId);
        if(lastPeriod == null) return null;
        TimeForDto timeForDto;
        if(!lastPeriod.isCalculated()){
            //we need to calculate timeForTime and timeForPay
            timeForDto = calculateTimeFor(lastPeriod);
            //now we need to store timeForTime
            lastPeriod.setTimeForTime(timeForDto.timeForTime);
            lastPeriod.setCalculated(true);
            periodDao.update(lastPeriod);
            //now we need to increase timeForPay
            User user = lastPeriod.getUser();
            user.setTimeForPay(user.getTimeForPay() + timeForDto.timeForPay);
            userDao.update(user);
        } else {
            double timeForTime = lastPeriod.getTimeForTime();
            double timeForPay = lastPeriod.getUser().getTimeForPay();
            timeForDto = new TimeForDto(timeForTime, timeForPay);
        }

        return timeForDto;
    }

    private TimeForDto calculateTimeFor(Period lastPeriod){
        double promisedPeriodTime = 0;
        double periodTime = 0;
        for (Week week : lastPeriod.getWeeks()){
            double timeDays = 0;
            for (Day day : week.getDays()){
                String weekDayTitle = DateTools.getWeekDayTitle(day.getDate());
                if(weekDayTitle.equals("Saturday") || weekDayTitle.equals("Sunday"))
                    continue;
                for(WorkHour workHour : day.getWorkHours()){
                    double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                    double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                    int restTime = workHour.getRestTime() / 60;
                    timeDays += endTime - startTime - restTime;
                }
            }
            promisedPeriodTime += getPromisedWeekTime(week);
            periodTime += timeDays;
        }
        double overTime = 0;
        double timeForTime = 0;
        if(periodTime > promisedPeriodTime){
            //overwork occurred
            if(periodTime < HOURS_LIMIT){
                //timeForTime not used
                overTime = periodTime - promisedPeriodTime;
            } else {
                timeForTime = periodTime - HOURS_LIMIT;
                overTime = HOURS_LIMIT - promisedPeriodTime;
            }
        }
        return new TimeForDto(timeForTime, overTime);
    }

    private double getPromisedWeekTime(Week week){
        double time = 0;
        time += DateTools.getDoubleFormatHours(week.getPromiseMondayTime());
        time += DateTools.getDoubleFormatHours(week.getPromiseTuesdayTime());
        time += DateTools.getDoubleFormatHours(week.getPromiseWednesdayTime());
        time += DateTools.getDoubleFormatHours(week.getPromiseThursdayTime());
        time += DateTools.getDoubleFormatHours(week.getPromiseFridayTime());
        return time;
    }

    private Period getPrevPeriod(LocalDate now, long userId){
        //make propagation that previous period was four-week
        List<LocalDate> startingDays = periodDefiner.getStartingDaysOfWeeksOfPreviousPeriod(now, PeriodType.FOURWEEK);
        Date startDay = DateTools.convertFromLocalDate(startingDays.get(0));
        Date endDate = DateTools.getDateOf7DayAfter(
                DateTools.convertFromLocalDate(
                        startingDays.get(startingDays.size() - 1)));
        //search
        Period period = periodDao.selectByStartAndEndDate(startDay, endDate, userId);
        if(period == null){
            //make propagation that previous period was month
            startingDays = periodDefiner.getStartingDaysOfWeeksOfPreviousPeriod(now, PeriodType.MONTH);
            startDay = DateTools.convertFromLocalDate(startingDays.get(0));
            endDate = DateTools.getDateOf7DayAfter(
                    DateTools.convertFromLocalDate(
                            startingDays.get(startingDays.size() - 1)));
            period = periodDao.selectByStartAndEndDate(startDay, endDate, userId);
        }
        return period;
    }
}
