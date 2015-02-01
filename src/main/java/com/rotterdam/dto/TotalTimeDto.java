package com.rotterdam.dto;

import com.rotterdam.tools.json.serializer.JsonDoubleTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by vasax32 on 24.01.15.
 */
public class TotalTimeDto {
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double totalTime;
    @JsonSerialize(using = JsonDoubleTimeSerializer.class)
    public Double overTime;
    public HashMap<String, String> days = new LinkedHashMap<>();

}
