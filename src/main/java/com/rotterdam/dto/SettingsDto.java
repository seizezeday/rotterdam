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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 21.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SettingsDto {

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date currentDate;

//    @JsonDeserialize(contentUsing = JsonTimeHourDeserializer.class)
//    @JsonSerialize(contentUsing = JsonTimeHourSerializer.class)
    public List<DateWrapper> promisedHours = new ArrayList<>();

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
                "startDate='" + dateFormat.format(startDate) + '\'' +
                "showCompensation='" + showCompensation + '\'' +
                "saturdayCompensation='" + saturdayCompensation + '\'' +
                '}';
    }

    public Week copyDaysOfWeekAndCheckBoxesToWeek(Week week){
        week.setPromiseMondayTime(promisedHours.get(0).date);
        week.setPromiseTuesdayTime(promisedHours.get(1).date);
        week.setPromiseWednesdayTime(promisedHours.get(2).date);
        week.setPromiseThursdayTime(promisedHours.get(3).date);
        week.setPromiseFridayTime(promisedHours.get(4).date);
//        week.setPromiseSaturdayTime(promisedHours.get(5).date);
//        week.setPromiseSundayTime(promisedHours.get(6).date);
        week.setShowCompensation(showCompensation);
        week.setSaturdayCompensation(saturdayCompensation);
        return week;
    }

    public SettingsDto copyDaysOfWeekToSettingsDto(Week week){
        promisedHours.add(new DateWrapper(week.getPromiseMondayTime()));
        promisedHours.add(new DateWrapper(week.getPromiseTuesdayTime()));
        promisedHours.add(new DateWrapper(week.getPromiseWednesdayTime()));
        promisedHours.add(new DateWrapper(week.getPromiseThursdayTime()));
        promisedHours.add(new DateWrapper(week.getPromiseFridayTime()));
//        promisedHours.add(new DateWrapper(week.getPromiseSaturdayTime()));
//        promisedHours.add(new DateWrapper(week.getPromiseSundayTime()));
        showCompensation = week.isShowCompensation();
        saturdayCompensation = week.isSaturdayCompensation();
        return this;
    }

    public static class DateWrapper{
        @JsonDeserialize(using = JsonTimeHourDeserializer.class)
        @JsonSerialize(using = JsonTimeHourSerializer.class)
        public Date date;

        public DateWrapper() {
        }

        public DateWrapper(Date date) {
            this.date = date;
        }
    }
}
