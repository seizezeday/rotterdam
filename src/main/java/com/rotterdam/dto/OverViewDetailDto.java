package com.rotterdam.dto;

import com.rotterdam.tools.json.serializer.JsonDoubleTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 01.02.15.
 */
public class OverViewDetailDto {
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double total = (double) 0;
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double total100 = (double) 0;
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double total130 = (double) 0;
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double total150 = (double) 0;
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double total200 = (double) 0;
    public double overTime;

    public List<StartEndDto> weekDates = new ArrayList<>();
}
