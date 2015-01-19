package com.rotterdam.dto;

import com.rotterdam.tools.json.JsonCommands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 19.01.15.
 */
public class DayDto{
    public Date date;
    public List<WorkHourDto> workHours = new ArrayList<>();

    @Override
    public String toString() {
        return "DayDto{" +
                "date=" + new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN).format( date) +
                ", workHours=" + workHours +
                '}';
    }
}
