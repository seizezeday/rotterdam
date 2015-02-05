package com.rotterdam.dto;

import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 30.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OverViewDto {
    public List<WeekOverViewDto> weekOverViews = new ArrayList<>();
    public List<Integer> usedWeeks = new ArrayList<>();
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date date;
    public StartEndDto startEnd;
    public UserDto user;
    public OverViewDetailDto totalPeriodDetail;
    public int timeForTime;
    public int scheduledHours;
    public List<WeekDto> weeks = new ArrayList<>();
    public List<DeclarationsDto> declarations = new ArrayList<>();


    @Override
    public String toString() {
        return "OverViewDto{" +
                "weekOverViews=" + weekOverViews +
                ", usedWeeks=" + usedWeeks +
                ", date=" + date +
                '}';
    }
}
