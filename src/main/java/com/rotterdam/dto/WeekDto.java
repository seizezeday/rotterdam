package com.rotterdam.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 19.01.15.
 */
public class WeekDto {
    public Map<String, DayDto> days = new HashMap<>();

    @Override
    public String toString() {
        return "WeekDto{" +
                "days=" + days +
                '}';
    }
}

