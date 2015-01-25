package rotterdam;

import com.rotterdam.tools.DateTools;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
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

    @Test
    public void findFirstMondayOfCurrentMonth() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfFirstMonday = DateTools.getDateOfFirstMonday(dateFormatter.parse("02/15/2015"));

        System.out.println(dateFormatter.format(dateOfFirstMonday.getTime()));
    }

    @Test
    public void findFirstMondayOfCurrentYear() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfFirstMonday = DateTools.getDateOfFirstMondayInYear(dateFormatter.parse("02/15/2015"));

        System.out.println(dateFormatter.format(dateOfFirstMonday.getTime()));
    }

    @Test
    public void plusDays() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfFirstMonday = DateTools.getDatePlusDays(dateFormatter.parse("02/26/2015"), 5);

        String dateOfFirstMondayString = dateFormatter.format(dateOfFirstMonday.getTime());

        Assert.assertEquals("03/03/2015", dateOfFirstMondayString );
    }

    @Test
    public void isBefore() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date firstDate = DateTools.getDatePlusDays(dateFormatter.parse("02/15/2015"), 5);
        Date secondDate = DateTools.getDatePlusDays(dateFormatter.parse("02/26/2015"), 5);

        boolean before = DateTools.isBefore(firstDate, secondDate);

        Assert.assertEquals(true, before );
    }

    @Test
    public void isAfter() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date firstDate = DateTools.getDatePlusDays(dateFormatter.parse("02/15/2015"), 5);
        Date secondDate = DateTools.getDatePlusDays(dateFormatter.parse("02/26/2015"), 5);

        boolean after = DateTools.isAfter(secondDate, firstDate);

        Assert.assertEquals(true, after );
    }

    @Test
    public void PrevMonth() throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormatter.parse("02/15/2015");

        Date datePlusMonth = DateTools.getDatePlusMonth(date, -1);

        Assert.assertEquals("01/15/2015", dateFormatter.format(datePlusMonth));
    }

}
