package com.rotterdam.dto;

import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.deserializer.JsonTimeDeserializer;
import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import com.rotterdam.tools.json.JsonCommands;
import com.rotterdam.tools.json.serializer.JsonTimeSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 19.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayDto{
    @JsonSerialize(using=JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date date;
    public List<WorkHourDto> workHours = new ArrayList<>();
    @JsonSerialize(using = JsonTimeSerializer.class)
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date total;

    @Override
    public String toString() {
        return "DayDto{" +
                "date=" + new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN).format( date) +
                ", workHours=" + workHours +
                '}';
    }
}
