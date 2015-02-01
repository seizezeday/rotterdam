package com.rotterdam.dto;

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
}

