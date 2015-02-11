package com.rotterdam.dto;

import com.rotterdam.model.entity.Day;
import com.rotterdam.model.entity.RideType;
import com.rotterdam.model.entity.WorkHour;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;
import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by vasax32 on 19.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeekDto {
    public List<DayDto> days = new ArrayList<>();
    public TotalTimeDto totalTime = new TotalTimeDto();
    public List<Integer> promisedTime = new ArrayList<>();
    public boolean active = false;
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Date date;

    @Override
    public String toString() {
        return "WeekDto{" +
                "days=" + days +
                '}';
    }

    public void calculateTotal() {
        for(DayDto dayDto : days){
            double total = 0;
            for (WorkHourDto workHourDto : dayDto.workHours){
                double endTime = DateTools.getDoubleFormatHours(workHourDto.endWorkingTime);
                double startTime = DateTools.getDoubleFormatHours(workHourDto.startWorkingTime);
                int restTime = workHourDto.restTime / 60;
                total += endTime - startTime - restTime;
            }
            dayDto.total =  DateTools.getDateFromDouble(total);
        }
    }

    public static WeekDto parseTimeTab(String genreJson) throws ParseException, IOException {
        WeekDto weekDto = new WeekDto();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(genreJson);
        Iterator<Map.Entry<String, JsonNode>> days = node.getFields();

        while (days.hasNext()) {
            DayDto dayDto = new DayDto();
            Map.Entry<String, JsonNode> day = days.next();
            //String dayTitle = day.getKey();
            Iterator<JsonNode> dateAndWorkHours = day.getValue().getElements();
            String date = dateAndWorkHours.next().get("date").asText();
            dayDto.date = parseDate(date);
            while (dateAndWorkHours.hasNext()){
                JsonNode workHour = dateAndWorkHours.next();
                WorkHourDto workHourDto = new WorkHourDto();
                workHourDto.startWorkingTime = parseTime(workHour.get("startWorkingTime").asText());
                workHourDto.endWorkingTime = parseTime(workHour.get("endWorkingTime").asText());
                workHourDto.restTime = workHour.get("restTime").asInt();
                workHourDto.rideType = RideType.values()[workHour.get("dayType").asInt()-1];
                dayDto.workHours.add(workHourDto);
            }
//            weekDto.days.put(dayTitle, dayDto);
            weekDto.days.add(dayDto);
        }
        return weekDto;
    }

    private static Date parseTime(String time) throws ParseException {
        try {
            return new SimpleDateFormat("HH:mm").parse(time);
        } catch (ParseException e) {
            return new SimpleDateFormat("hh:mm").parse("00:00");
        }
    }

    private static Date parseDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN);
        return format.parse(dateString);
    }

    public void checkRestTime(){
        for (DayDto dayDto : days)
            for (WorkHourDto workHourDto : dayDto.workHours){
                if(workHourDto.restTime == 0){

                    double endTime = DateTools.getDoubleFormatHours(workHourDto.endWorkingTime);
                    double startTime = DateTools.getDoubleFormatHours(workHourDto.startWorkingTime);
                    double time = endTime - startTime;

                    if (time >=  4.5 && time <   7.5) workHourDto.restTime = 30;
                    else if (time >=  7.5 && time <  10.5) workHourDto.restTime = 60;
                    else if (time >= 10.5 && time <  13.5) workHourDto.restTime = 90;
                    else if (time >= 13.5 && time <  16.5) workHourDto.restTime = 120;
                    else if (time >= 16.5) workHourDto.restTime = 150;

                }
            }
    }

    public static WeekDto convertDaysToWorkHourDto(List<Day> days){
        WeekDto weekDto = new WeekDto();
        for(Day day : days){
            DayDto dayDto = new DayDto();
            dayDto.date = day.getDate();
            for (WorkHour workHour : day.getWorkHours()) {
                WorkHourDto workHourDto = new WorkHourDto();
                workHourDto.startWorkingTime = workHour.getStartWorkingTime();
                workHourDto.endWorkingTime = workHour.getEndWorkingTime();
                workHourDto.restTime = workHour.getRestTime();
                workHourDto.rideType = workHour.getRideType();
                dayDto.workHours.add(workHourDto);
            }
//            weekDto.days.put(DateTools.getWeekDayTitle(dayDto.date), dayDto);
            weekDto.days.add(dayDto);
        }
        return weekDto;
    }
}

