package rotterdam;

import com.rotterdam.dto.DayDto;
import com.rotterdam.dto.WeekDto;
import com.rotterdam.dto.WorkHourDto;
import com.rotterdam.model.entity.RideType;
import com.rotterdam.tools.json.JsonCommands;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by root on 18.01.15.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class WeekInfoTest {
    @Test
    public void timeTabRead() throws IOException, ParseException {
        String genreJson = "";
        List<String> lines = Files.readAllLines(Paths.get("timeTab.json"), Charset.defaultCharset());
        for(String s : lines)
            genreJson += s;

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
        System.out.println(weekDto);
    }

    public Date parseTime(String time) throws ParseException {
        try {
            return new SimpleDateFormat("hh:mm").parse(time);
        } catch (ParseException e) {
            return new SimpleDateFormat("hh:mm").parse("00:00");
        }
    }

    public Date parseDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN);
        return format.parse(dateString);
    }

}
//////////////////////////////////////////////
//////////////////////////////////////////////
//{"Monday":[
//      {"date":"2015-01-12"},
//      {"startWorkingTime":"01:00","endWorkingTime":"12:00","restTime":"25","dayType":"1"}],
// "Tuesday":[
//      {"date":"2015-01-13"},
//      {"startWorkingTime":"13:50","endWorkingTime":"20:00","restTime":"10","dayType":"1"},
//      {"startWorkingTime":"20:50","endWorkingTime":"22:00","restTime":"25","dayType":"1"}],
// "Wednesday":[
//      {"date":"2015-01-14"},
//      {"startWorkingTime":"00:00","endWorkingTime":"00:00","restTime":"00:00","dayType":"2"}],
// "Thursday":[
//      {"date":"2015-01-15"},
//      {"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],
// "Friday":[
//      {"date":"2015-01-16"},
//      {"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],
// "Saturday":[
//      {"date":"2015-01-17"},
//      {"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}],
// "Sunday":[
//      {"date":"2015-01-18"},
//      {"startWorkingTime":"","endWorkingTime":"","restTime":"","dayType":"1"}]}



