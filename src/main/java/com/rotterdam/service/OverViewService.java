package com.rotterdam.service;

import com.rotterdam.dto.*;
import com.rotterdam.model.dao.PeriodDao;
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

    @Transactional
    public OverViewDto getOverView(OverViewDto overViewDto, long userId) {

        List<Date> startingDaysOfWeeksOfCurrentPeriod =
                periodDefiner.getStartingDaysOfWeeksOfCurrentPeriod(overViewDto.date, PeriodType.FOURWEEK);

        for (int i = 0; i < overViewDto.usedWeeks.size(); i++){
            WeekDto weekDto = weekService.getWeekByStartDateAndUserId(startingDaysOfWeeksOfCurrentPeriod.get(overViewDto.usedWeeks.get(i)), userId);

            //just add it
            overViewDto.weekList.add(new WeekOverViewDto(weekDto));
        }

//        for(int i = 0; i < startingDaysOfWeeksOfCurrentPeriod.size(); i++){
//            if(overViewDto.usedWeeks.get(i)){
//                //if week needle
//                WeekDto weekDto = weekService.getWeekByStartDateAndUserId(startingDaysOfWeeksOfCurrentPeriod.get(i), userId);
//
//                //just add it
//                overViewDto.weekList.add(new WeekOverViewDto(weekDto));
//            }
//        }

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
        TimeForDto timeForOfPrevPeriod = timeForService.getTimeForOfPrevPeriod(date, userId);
        overViewDetailDto.overTime = timeForOfPrevPeriod.overTime;

        return overViewDetailDto;
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
                for(WorkHour workHour : day.getWorkHours()){
                    double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                    double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                    int restTime = workHour.getRestTime() / 60;
                    timeDays += endTime - startTime - restTime;
                }
            }
            promisedPeriodTime += timeForService.getPromisedWeekTime(week);
            periodTime += timeDays;
        }



        if(periodTime > promisedPeriodTime)
            overViewDetailDto.total130 = new Double(periodTime - promisedPeriodTime);
        else overViewDetailDto.total130 = new Double(0);
    }

    private void calculate150(OverViewDetailDto overViewDetailDto, Period period){
        double worked = 0;
        for (Week week : period.getWeeks()){
            for(Day day : week.getDays()){
                if(DateTools.getWeekDayTitle(day.getDate()).equals("Saturday")){
                    for (WorkHour workHour : day.getWorkHours()){
                        double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                        double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                        int restTime = workHour.getRestTime() / 60;
                        worked += endTime - startTime - restTime;
                    }
                }
            }
        }
            overViewDetailDto.total150 = new Double(worked);
    }

    private void calculate200(OverViewDetailDto overViewDetailDto, Period period){
        double worked = 0;
        for (Week week : period.getWeeks()){
            for(Day day : week.getDays()){
                if(DateTools.getWeekDayTitle(day.getDate()).equals("Sunday")){
                    for (WorkHour workHour : day.getWorkHours()){
                        double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                        double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                        int restTime = workHour.getRestTime() / 60;
                        worked += endTime - startTime - restTime;
                    }
                }
            }
        }
        overViewDetailDto.total200 = new Double(worked);
    }

    private void calculateTotal(OverViewDetailDto overViewDetailDto, Period period){
        double periodTime = 0;
        for (Week week : period.getWeeks()){
            double timeDays = 0;
            for (Day day : week.getDays()){
                for(WorkHour workHour : day.getWorkHours()){
                    double endTime = DateTools.getDoubleFormatHours(workHour.getEndWorkingTime());
                    double startTime = DateTools.getDoubleFormatHours(workHour.getStartWorkingTime());
                    int restTime = workHour.getRestTime() / 60;
                    timeDays += endTime - startTime - restTime;
                }
            }
            periodTime += timeDays;
        }

        overViewDetailDto.total = new Double(periodTime);

    }




}
