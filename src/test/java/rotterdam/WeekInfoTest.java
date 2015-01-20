package rotterdam;

import com.rotterdam.dto.WeekDto;
import com.rotterdam.service.WeekService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

/**
 * Created by root on 18.01.15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class WeekInfoTest {

    @Inject
    private WeekService weekService;

    private long userId = 5;

    @Test
    @Rollback(false)
    public void saveTimeTab() throws IOException, ParseException {
        String genreJson = readTempJson();
        WeekDto weekDto = WeekDto.parseTimeTab(genreJson);

        weekService.save(weekDto, userId);
    }

    @Test
    public void timeTabRead() throws IOException, ParseException {
        String genreJson = readTempJson();
        WeekDto weekDto = WeekDto.parseTimeTab(genreJson);
        System.out.println(weekDto);
    }



    public String readTempJson() throws IOException {
        String genreJson = "";
        List<String> lines = Files.readAllLines(Paths.get("timeTab.json"), Charset.defaultCharset());
        for(String s : lines)
            genreJson += s;
        return genreJson;
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



