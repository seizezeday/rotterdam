package com.rotterdam.dto;

import com.rotterdam.tools.json.JsonCommands;
import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.deserializer.JsonTimeDeserializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 21.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingsDto {

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date currentDate;

    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date monday_hours;
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date tuesday_hours;
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date wednesday_hours;
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date thursday_hours;
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date friday_hours;
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date saturday_hours;
    @JsonDeserialize(using = JsonTimeDeserializer.class)
    public Date sunday_hours;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN);
        SimpleDateFormat timeFormat = new SimpleDateFormat(JsonCommands.PARAM_TIME_PATTERN);
        return "SettingsDto{" +
                "currentDate='" + dateFormat.format(currentDate) + '\'' +
                "monday_hours='" + timeFormat.format(monday_hours) + '\'' +
                ", tuesday_hours='" + timeFormat.format(tuesday_hours) + '\'' +
                ", wednesday_hours='" +  timeFormat.format(wednesday_hours) + '\'' +
                ", thursday_hours='" +  timeFormat.format(thursday_hours) + '\'' +
                ", friday_hours='" +  timeFormat.format(friday_hours) + '\'' +
                ", saturday_hours='" +  timeFormat.format(saturday_hours) + '\'' +
                ", sunday_hours='" +  timeFormat.format(sunday_hours) + '\'' +
                '}';
    }
}
