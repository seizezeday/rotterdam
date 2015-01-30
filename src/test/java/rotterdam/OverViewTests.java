package rotterdam;

import com.rotterdam.dto.OverViewDto;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Created by root on 30.01.15.
 */
public class OverViewTests {
    @Test
    public void serializeWeekDto() throws IOException, ParseException {

        OverViewDto overViewDto = new OverViewDto();
        overViewDto.usedWeeks.addAll(Arrays.asList(new Boolean[]{true, true, true, true}));

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(overViewDto);
        System.out.println(json);
    }

    //{"date":"29.01.2015", "usedWeeks":[true,true,true,true] }

    @Test
    public void deserialzeWeekDto() throws IOException, ParseException {


        ObjectMapper mapper = new ObjectMapper();
        OverViewDto overViewDto = mapper.readValue("{\"date\":\"29.01.2015\", \"usedWeeks\":[true,true,true,true] }", OverViewDto.class);
//        String json = overViewDto
        System.out.println(overViewDto);
    }
}
