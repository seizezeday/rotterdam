package com.rotterdam.dto;

import com.rotterdam.tools.DateTools;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by vasax32 on 19.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeekOverViewDto {
    public Map<String, DayDto> days = new LinkedHashMap<>();

    @Override
    public String toString() {
        return "WeekDto{" +
                "days=" + days +
                '}';
    }

    public WeekOverViewDto() {
    }

    public WeekOverViewDto(WeekDto weekDto){
        this.days = weekDto.days;
    }

    public void calculateTotal() {
        for(DayDto dayDto : days.values()){
            double total = 0;
            for (WorkHourDto workHourDto : dayDto.workHours){
                double endTime = DateTools.getDoubleFormatHours(workHourDto.endWorkingTime);
                double startTime = DateTools.getDoubleFormatHours(workHourDto.startWorkingTime);
                int restTime = workHourDto.restTime / 60;
                total += endTime - startTime - restTime;
            }
            dayDto.total =  DateTools.getDateFromDouble(total);
        }
    }
}

