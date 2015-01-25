package rotterdam;

import com.rotterdam.model.entity.PeriodType;
import com.rotterdam.tools.PeriodDefiner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Vasya on 21.01.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/app-context.xml")
public class PeriodDefinerTestJava7 {
    @Inject
    PeriodDefiner periodDefiner;

    List<Date> julyMonthPeriod = null;
    List<Date> juneMonthPeriod = null;
    List<Date> mayMonthPeriod = null;
    List<Date> january4WeekPeriod = null;
    List<Date> december2014_4WeekPeriod = null;

    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");


    {
        try {
            julyMonthPeriod = new ArrayList<>();
            julyMonthPeriod.add(df.parse("2015/07/06"));
            julyMonthPeriod.add(df.parse("2015/07/13"));
            julyMonthPeriod.add(df.parse("2015/07/20"));
            julyMonthPeriod.add(df.parse("2015/07/27"));

            juneMonthPeriod = new ArrayList<>();
            juneMonthPeriod.add(df.parse("2015/06/01"));
            juneMonthPeriod.add(df.parse("2015/06/08"));
            juneMonthPeriod.add(df.parse("2015/06/15"));
            juneMonthPeriod.add(df.parse("2015/06/22"));
            juneMonthPeriod.add(df.parse("2015/06/29"));

            mayMonthPeriod = new ArrayList<>();
            mayMonthPeriod.add(df.parse("2015/05/04"));
            mayMonthPeriod.add(df.parse("2015/05/11"));
            mayMonthPeriod.add(df.parse("2015/05/18"));
            mayMonthPeriod.add(df.parse("2015/05/25"));

            january4WeekPeriod = new ArrayList<>();
            january4WeekPeriod.add(df.parse("2015/01/05"));
            january4WeekPeriod.add(df.parse("2015/01/12"));
            january4WeekPeriod.add(df.parse("2015/01/19"));
            january4WeekPeriod.add(df.parse("2015/01/26"));

            december2014_4WeekPeriod = new ArrayList<>();
            december2014_4WeekPeriod.add(df.parse("2014/12/08"));
            december2014_4WeekPeriod.add(df.parse("2014/12/15"));
            december2014_4WeekPeriod.add(df.parse("2014/12/22"));
            december2014_4WeekPeriod.add(df.parse("2014/12/29"));


        } catch (ParseException e){
            e.printStackTrace();
        }

    }




    @Test
    public void testCurrentMonthPeriod() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfCurrentPeriod(df.parse("2015/07/07"), PeriodType.MONTH);
        assertEquals(list, julyMonthPeriod);

    }



    @Test
    public void testCurrentMonthPeriod2() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfCurrentPeriod(df.parse("2015/07/01"), PeriodType.MONTH);
        assertEquals(list, juneMonthPeriod);

    }

    @Test
    public void testPreviousMonthPeriod() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfPreviousPeriod(df.parse("2015/07/01"), PeriodType.MONTH);
        assertEquals(list, mayMonthPeriod);

    }

    @Test
    public void testCurrent4WeekPeriod() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfCurrentPeriod(df.parse("2015/01/06"), PeriodType.FOURWEEK);
        assertEquals(list, january4WeekPeriod);

    }

    @Test
    public void testPrevious4WeekPeriod() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfPreviousPeriod(df.parse("2015/02/02"), PeriodType.FOURWEEK);
        assertEquals(list, january4WeekPeriod);

    }

    @Test
    public void test2Previous4WeekPeriod() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfPreviousPeriod(df.parse("2015/02/01"), PeriodType.FOURWEEK);
        assertEquals(list, december2014_4WeekPeriod);

    }

    @Test
    public void test2Current4WeekPeriod() throws ParseException {
        List<Date> list = periodDefiner.
                getStartingDaysOfWeeksOfCurrentPeriod(df.parse("2015/02/01"), PeriodType.FOURWEEK);
        assertEquals(list, january4WeekPeriod);

    }



}
