package com.rotterdam.service;

import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.entity.Period;
import com.rotterdam.model.entity.PeriodType;
import com.rotterdam.model.entity.User;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.PeriodDefiner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 21.01.15.
 */
@Named
public class PeriodService {

    @Inject
    private PeriodDao periodDao;

    @Inject
    private PeriodDefiner periodDefiner;

    @Transactional
    public void makePeriodCheck(User user){
        //now we need to deal with period check
        Date now = new Date();
        Period period = periodDao.selectByDateBetweenAndUser(now, user.getId());
        if(period == null){
            //now we need to create new period
            period = new Period();
            period.setUser(user);
            //period.setPeriodType(PeriodType.FOURWEEK); // setting this by default, can be changed in settings

//            setPeriodDateFourWeek(period, user.getId());
            setPeriodData(period, PeriodType.FOURWEEK);

            period = periodDao.insert(period);

            //now we need calculate time-for-time for previous period

        } else {
            //every thing is ok
        }
    }

    private void setPeriodData(Period period, PeriodType periodType){
        period.setPeriodType(periodType);
        List<LocalDate> startingDays = periodDefiner.getStartingDaysOfWeeksOfCurrentPeriod(LocalDate.now(), periodType);
        period.setStartDate(
                DateTools.convertFromLocalDate(
                        startingDays.get(0)));

        int weekCount = startingDays.size();

        period.setEndDate(
                DateTools.getDateOf7DayAfter(
                        DateTools.convertFromLocalDate(
                                startingDays.get(weekCount - 1))));

    }

//    private void setPeriodDateFourWeek(Period period, Long userId){
//        //first we need to check for existing of last period
//        Period lastPeriodByUser = periodDao.selectLastPeriodByUser(userId);
//        Date startDate;
//        if(period == null){
//            //we just need to find first monday of current month
//            startDate = DateTools.getDateOfFirstMonday();
//        } else {
//            //we need to find next date after end of last period
//            startDate = DateTools.getDateNextDay(lastPeriodByUser.getEndDate());//this will be monday
//        }
//        period.setStartDate(startDate);
//        period.setEndDate(DateTools.getDateAfterFourWeeks(startDate));
//    }
//
//    private void setPeriodDateMonth(Period period){
//        Date startDate = DateTools.getDateOfFirstMonday();
//        Date endDate = DateTools.getDateOfLastDay();
//        if(!DateTools.isSunday(endDate)){
//            //we need to find end of this week
//            endDate = DateTools.getDateOfNextSunday(endDate);
//        }
//        period.setStartDate(startDate);
//        period.setEndDate(endDate);
//    }
}
