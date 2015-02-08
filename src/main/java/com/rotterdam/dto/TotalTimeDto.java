package com.rotterdam.dto;

import com.rotterdam.tools.json.serializer.JsonDoubleTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasax32 on 24.01.15.
 */
public class TotalTimeDto {
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double totalTime;
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double overTime;
    public List<TimeDto> days = new ArrayList<>();

}
