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
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 21.01.15.
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
        List<Date> startingDays = periodDefiner.getStartingDaysOfWeeksOfCurrentPeriod(new Date(), periodType);
        period.setStartDate(startingDays.get(0));

        int weekCount = startingDays.size();

        period.setEndDate(
                DateTools.getDateOf7DayAfter(
                                startingDays.get(weekCount - 1)));

    }
}
