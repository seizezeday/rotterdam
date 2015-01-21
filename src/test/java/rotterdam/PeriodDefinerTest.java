package rotterdam;

import com.rotterdam.model.entity.PeriodType;
import com.rotterdam.tools.PeriodDefiner;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Vasya on 21.01.2015.
 */
public class PeriodDefinerTest {
    List<LocalDate> julyMonthPeriod = null;
    List<LocalDate> juneMonthPeriod = null;
    List<LocalDate> mayMonthPeriod = null;
    List<LocalDate> january4WeekPeriod = null;
    List<LocalDate> december2014_4WeekPeriod = null;


    {

        julyMonthPeriod = new ArrayList<>();
        julyMonthPeriod.add(LocalDate.of(2015, Month.JULY, 6));
        julyMonthPeriod.add(LocalDate.of(2015, Month.JULY, 13));
        julyMonthPeriod.add(LocalDate.of(2015, Month.JULY, 20));
        julyMonthPeriod.add(LocalDate.of(2015, Month.JULY, 27));

        juneMonthPeriod = new ArrayList<>();
        juneMonthPeriod.add(LocalDate.of(2015, Month.JUNE, 1));
        juneMonthPeriod.add(LocalDate.of(2015, Month.JUNE, 8));
        juneMonthPeriod.add(LocalDate.of(2015, Month.JUNE, 15));
        juneMonthPeriod.add(LocalDate.of(2015, Month.JUNE, 22));
        juneMonthPeriod.add(LocalDate.of(2015, Month.JUNE, 29));

        mayMonthPeriod = new ArrayList<>();
        mayMonthPeriod.add(LocalDate.of(2015, Month.MAY, 4));
        mayMonthPeriod.add(LocalDate.of(2015, Month.MAY, 11));
        mayMonthPeriod.add(LocalDate.of(2015, Month.MAY, 18));
        mayMonthPeriod.add(LocalDate.of(2015, Month.MAY, 25));

        january4WeekPeriod = new ArrayList<>();
        january4WeekPeriod.add(LocalDate.of(2015, Month.JANUARY, 5));
        january4WeekPeriod.add(LocalDate.of(2015, Month.JANUARY, 12));
        january4WeekPeriod.add(LocalDate.of(2015, Month.JANUARY, 19));
        january4WeekPeriod.add(LocalDate.of(2015, Month.JANUARY, 26));

        december2014_4WeekPeriod = new ArrayList<>();
        december2014_4WeekPeriod.add(LocalDate.of(2014, Month.DECEMBER, 8));
        december2014_4WeekPeriod.add(LocalDate.of(2014, Month.DECEMBER, 15));
        december2014_4WeekPeriod.add(LocalDate.of(2014, Month.DECEMBER, 22));
        december2014_4WeekPeriod.add(LocalDate.of(2014, Month.DECEMBER, 29));




    }

    @Test
    public void testCurrentMonthPeriod() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfCurrentPeriod(LocalDate.of(2015, Month.JULY, 7), PeriodType.MONTH);
        assertEquals(list, julyMonthPeriod);

    }

    @Test
    public void testCurrentMonthPeriod2() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfCurrentPeriod(LocalDate.of(2015, Month.JULY, 1), PeriodType.MONTH);
        assertEquals(list, juneMonthPeriod);

    }

    @Test
    public void testPreviousMonthPeriod() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfPreviousPeriod(LocalDate.of(2015, Month.JULY, 1), PeriodType.MONTH);
        assertEquals(list, mayMonthPeriod);

    }

    @Test
    public void testCurrent4WeekPeriod() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfCurrentPeriod(LocalDate.of(2015, Month.JANUARY, 6), PeriodType.FOURWEEK);
        assertEquals(list, january4WeekPeriod);

    }

    @Test
    public void testPrevious4WeekPeriod() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfPreviousPeriod(LocalDate.of(2015, Month.FEBRUARY, 2), PeriodType.FOURWEEK);
        assertEquals(list, january4WeekPeriod);

    }

    @Test
    public void test2Previous4WeekPeriod() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfPreviousPeriod(LocalDate.of(2015, Month.FEBRUARY, 1), PeriodType.FOURWEEK);
        assertEquals(list, december2014_4WeekPeriod);

    }

    @Test
    public void test2Current4WeekPeriod() {
        List<LocalDate> list = PeriodDefiner.
                getPeriodDefiner().
                getStartingDaysOfWeeksOfCurrentPeriod(LocalDate.of(2015, Month.FEBRUARY, 1), PeriodType.FOURWEEK);
        assertEquals(list, january4WeekPeriod);

    }



}
