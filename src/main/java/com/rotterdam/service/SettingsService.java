package com.rotterdam.service;

import com.rotterdam.dto.SettingsDto;
import com.rotterdam.model.dao.PeriodDao;
import com.rotterdam.model.dao.WeekDao;
import com.rotterdam.model.entity.Period;
import com.rotterdam.model.entity.Week;
import com.rotterdam.tools.DateTools;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 03.02.15.
 */
@Named
public class SettingsService {

    @Inject
    private WeekDao weekDao;

    @Inject
    private PeriodDao periodDao;

    @Transactional
    public void save(SettingsDto settingsDto, long userId){
        //check date
        settingsDto.currentDate = DateTools.getDateOfPrevMonday(settingsDto.currentDate);

        Week week = weekDao.selectByStartDateAndUser(settingsDto.currentDate, userId);

        if(week == null){
            //need to create new week
            week = new Week();
            week = settingsDto.copyDaysOfWeekAndCheckBoxesToWeek(week);
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
            week = settingsDto.copyDaysOfWeekAndCheckBoxesToWeek(week);
            weekDao.update(week);
        }
    }



    @Transactional
    public SettingsDto getSettings(Date date, long userId){
        date = DateTools.getDateOfPrevMonday(date);

        SettingsDto settingsDto = new SettingsDto();

        Period period = periodDao.selectByDateBetweenAndUser(date, userId);

        if(period != null) {
            settingsDto.startDate = period.getStartDate();
            settingsDto.endDate = period.getEndDate();
        }

        Week week = weekDao.selectByStartDateAndUser(date, userId);


        if(week == null)
            return settingsDto;

        settingsDto = settingsDto.copyDaysOfWeekToSettingsDto(week);

        return settingsDto;
    }

    public List<SettingsDto.DateWrapper> generateFakeHours() {
        List<SettingsDto.DateWrapper> hours = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            hours.add(new SettingsDto.DateWrapper(null));
        }
        return hours;
    }
}
