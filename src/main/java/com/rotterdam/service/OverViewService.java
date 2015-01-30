package com.rotterdam.service;

import com.rotterdam.dto.OverViewDto;
import com.rotterdam.dto.WeekDto;
import com.rotterdam.dto.WeekOverViewDto;
import com.rotterdam.model.entity.PeriodType;
import com.rotterdam.tools.PeriodDefiner;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 30.01.15.
 */
@Named
public class OverViewService {

    @Inject
    private PeriodDefiner periodDefiner;

    @Inject
    private WeekService weekService;

    public OverViewDto getOverView(OverViewDto overViewDto, long userId) {

        List<Date> startingDaysOfWeeksOfCurrentPeriod =
                periodDefiner.getStartingDaysOfWeeksOfCurrentPeriod(overViewDto.date, PeriodType.FOURWEEK);

        for(int i = 0; i < startingDaysOfWeeksOfCurrentPeriod.size(); i++){
            if(overViewDto.usedWeeks.get(i)){
                //if week needle
                WeekDto weekDto = weekService.getWeekByStartDateAndUserId(startingDaysOfWeeksOfCurrentPeriod.get(i), userId);

                //just add it
                overViewDto.weekList.add(new WeekOverViewDto(weekDto));
            }
        }

        return overViewDto;
    }
}
