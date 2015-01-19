package rotterdam;

import com.rotterdam.tools.DateTools;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 18.01.15.
 */
public class DateTest {
    @Test
    public void nestFourWeekTest(){
        Date dateAfterFourWeeks = DateTools.getDateAfterFourWeeks(new Date());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss z");

        System.out.println(dateFormatter.format(dateAfterFourWeeks.getTime()));
    }
}
