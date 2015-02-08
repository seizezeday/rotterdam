package com.rotterdam.dto;

import com.rotterdam.model.entity.Week;
import com.rotterdam.tools.json.JsonCommands;
import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.deserializer.JsonTimeHourDeserializer;
import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import com.rotterdam.tools.json.serializer.JsonTimeHourSerializer;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vasax32 on 21.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingsDto {

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date currentDate;

    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date monday_hours;
    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date tuesday_hours;
    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date wednesday_hours;
    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date thursday_hours;
    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date friday_hours;
    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date saturday_hours;
    @JsonDeserialize(using = JsonTimeHourDeserializer.class)
    @JsonSerialize(using = JsonTimeHourSerializer.class)
    public Date sunday_hours;
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date startDate;
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date endDate;

    @JsonProperty("show_compensation")
    public boolean showCompensation;

    @JsonProperty("allow_saturday_compensation")
    public boolean saturdayCompensation;

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
                "startDate='" + dateFormat.format(startDate) + '\'' +
                "showCompensation='" + showCompensation + '\'' +
                "saturdayCompensation='" + saturdayCompensation + '\'' +
                '}';
    }

    public Week copyDaysOfWeekAndCheckBoxesToWeek(Week week){
        week.setPromiseMondayTime(monday_hours);
        week.setPromiseTuesdayTime(tuesday_hours);
        week.setPromiseWednesdayTime(wednesday_hours);
        week.setPromiseThursdayTime(thursday_hours);
        week.setPromiseFridayTime(friday_hours);
        week.setPromiseSaturdayTime(saturday_hours);
        week.setPromiseSundayTime(sunday_hours);
        week.setShowCompensation(showCompensation);
        week.setSaturdayCompensation(saturdayCompensation);
        return week;
    }

    public SettingsDto copyDaysOfWeekToSettingsDto(Week week){
        monday_hours = week.getPromiseMondayTime();
        tuesday_hours = week.getPromiseTuesdayTime();
        wednesday_hours = week.getPromiseWednesdayTime();
        thursday_hours = week.getPromiseThursdayTime();
        friday_hours = week.getPromiseFridayTime();
        saturday_hours = week.getPromiseSaturdayTime();
        sunday_hours = week.getPromiseSundayTime();
        showCompensation = week.isShowCompensation();
        saturdayCompensation = week.isSaturdayCompensation();
        return this;
    }
}
