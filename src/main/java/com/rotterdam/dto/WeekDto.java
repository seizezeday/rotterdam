package com.rotterdam.dto;

import com.rotterdam.model.entity.RideType;
import com.rotterdam.tools.json.JsonCommands;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by root on 19.01.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeekDto {
    public Map<String, DayDto> days = new LinkedHashMap<>();
    public TotalTimeDto totalTime = new TotalTimeDto();

    @Override
    public String toString() {
        return "WeekDto{" +
                "days=" + days +
                '}';
    }

    public static WeekDto parseTimeTab(String genreJson) throws ParseException, IOException {
        WeekDto weekDto = new WeekDto();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(genreJson);
        Iterator<Map.Entry<String, JsonNode>> days = node.getFields();

        while (days.hasNext()) {
            DayDto dayDto = new DayDto();
            Map.Entry<String, JsonNode> day = days.next();
            String dayTitle = day.getKey();
            Iterator<JsonNode> dateAndWorkHours = day.getValue().getElements();
            dayDto.date = parseDate(dateAndWorkHours.next().get("date").asText());;
            while (dateAndWorkHours.hasNext()){
                JsonNode workHour = dateAndWorkHours.next();
                WorkHourDto workHourDto = new WorkHourDto();
                workHourDto.startWorkingTime = parseTime(workHour.get("startWorkingTime").asText());
                workHourDto.endWorkingTime = parseTime(workHour.get("endWorkingTime").asText());
                workHourDto.restTime = workHour.get("restTime").asInt();
                workHourDto.rideType = RideType.values()[workHour.get("dayType").asInt()-1];
                dayDto.workHours.add(workHourDto);
            }
            weekDto.days.put(dayTitle, dayDto);
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
}

