package com.rotterdam.dto;

import com.rotterdam.tools.json.serializer.JsonDoubleSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by root on 24.01.15.
 */
public class TotalTimeDto {
    @JsonSerialize(using = JsonDoubleSerializer.class)
    public double totalTime;
    public HashMap<String, String> days = new LinkedHashMap<>();

}
