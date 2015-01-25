package com.rotterdam.tools;

/**
 * Created by Vasya on 21.01.2015.
 */

import com.rotterdam.model.entity.PeriodType;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
public class PeriodDefiner {


    public PeriodDefiner() {
    }



//    /**
//     * @param currentDate   - current date, to get previous payment period
//     * @param periodType - used profile for period definition: FOURWEEK or MONTH
//     * @return Previous period's weeks starting days (in a List)
//     */
//    public List<LocalDate> getStartingDaysOfWeeksOfCurrentPeriod(LocalDate currentDate, PeriodType periodType) {
//        List<LocalDate> startingDatesList = new ArrayList<>();
//        if (periodType == PeriodType.MONTH) {
//            LocalDate firstMonday = currentDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            if (currentDate.isBefore(firstMonday)) {
//                firstMonday = currentDate.minusMonths(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            }
//            Month currentMonth = firstMonday.getMonth();
//            LocalDate firstDayOfWeek = firstMonday;
//
//
//            while (firstDayOfWeek.getMonth() == currentMonth) {
//                startingDatesList.add(firstDayOfWeek);
//                firstDayOfWeek = firstDayOfWeek.plusWeeks(1);
//            }
//
//
//        } else if (periodType == PeriodType.FOURWEEK) {
//            LocalDate startDateOfPeriod;
//            LocalDate endDateOfPeriod;
//            LocalDate firstMondayOfYear = currentDate.withMonth(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            if (currentDate.isBefore(firstMondayOfYear)) {
//                firstMondayOfYear = currentDate.withMonth(1).minusYears(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            }
//
//            startDateOfPeriod = firstMondayOfYear;
//            endDateOfPeriod = firstMondayOfYear.plusDays(27);
//
//
//            while (!(((startDateOfPeriod.isBefore(currentDate)) || (startDateOfPeriod.isEqual(currentDate)))
//                    && ((endDateOfPeriod.isAfter(currentDate)) || (endDateOfPeriod.isEqual(currentDate))))) {
//                startDateOfPeriod = startDateOfPeriod.plusDays(28);
//                endDateOfPeriod = endDateOfPeriod.plusDays(28);
//            }
//            for (int i = 0; i < 4; i++) {
//                startingDatesList.add(startDateOfPeriod);
//                startDateOfPeriod = startDateOfPeriod.plusWeeks(1);
//            }
//
//        }
//        return startingDatesList;
//    }

    public List<Date> getStartingDaysOfWeeksOfCurrentPeriod(Date currentDate, PeriodType periodType) {
        List<Date> startingDatesList = new ArrayList<>();
        if (periodType == PeriodType.MONTH) {
            Date firstMonday = DateTools.getDateOfFirstMonday(currentDate);
            if (DateTools.isBefore(currentDate,firstMonday)) {
                firstMonday = DateTools.getDateOfFirstMonday(DateTools.getDatePlusMonth(currentDate, -1));
            }
            int currentMonth =  DateTools.getMonth(firstMonday);
            Date firstDayOfWeek = firstMonday;


            while (DateTools.getMonth(firstDayOfWeek) == currentMonth) {
                startingDatesList.add(firstDayOfWeek);
                firstDayOfWeek = DateTools.getDatePlusWeek(firstDayOfWeek);
            }


        } else if (periodType == PeriodType.FOURWEEK) {
            Date startDateOfPeriod;
            Date endDateOfPeriod;
            Date firstMondayOfYear = DateTools.getDateOfFirstMondayInYear(currentDate);
//            if (currentDate.isBefore(firstMondayOfYear)) {
//                firstMondayOfYear = currentDate.withMonth(1).minusYears(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            }

            startDateOfPeriod = firstMondayOfYear;
            endDateOfPeriod = DateTools.getDatePlusDays(firstMondayOfYear, 27);


            while (!(((DateTools.isBefore(startDateOfPeriod,currentDate)) || (startDateOfPeriod.equals(currentDate)))
                    && ((DateTools.isAfter(endDateOfPeriod, currentDate)) || (endDateOfPeriod.equals(currentDate))))) {
                startDateOfPeriod = DateTools.getDatePlusDays(startDateOfPeriod, 28);
                endDateOfPeriod = DateTools.getDatePlusDays(endDateOfPeriod, 28);
            }
            for (int i = 0; i < 4; i++) {
                startingDatesList.add(startDateOfPeriod);
                startDateOfPeriod = DateTools.getDatePlusWeek(startDateOfPeriod);
            }

        }
        return startingDatesList;
    }

//    /**
//     * @param currentDate   date in current period
//     * @param periodType period profile
//     * @return list of starting days of full weeks in PREVIOUS, "payment" period
//     */
//    public List<LocalDate> getStartingDaysOfWeeksOfPreviousPeriod(LocalDate currentDate, PeriodType periodType) {
//        List<LocalDate> startingDatesList = new ArrayList<>();
//        if (periodType == PeriodType.MONTH) {
//            LocalDate dayInPreviousPeriod;
//            LocalDate firstMondayInThisPeriod = currentDate.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
//            if (currentDate.isBefore(firstMondayInThisPeriod)) {
//                dayInPreviousPeriod = currentDate.minusMonths(1).minusDays(15);
//            } else {
//                dayInPreviousPeriod = currentDate.minusMonths(1);
//            }
//            startingDatesList = getStartingDaysOfWeeksOfCurrentPeriod(dayInPreviousPeriod, PeriodType.MONTH);
//
//
//        } else if (periodType == PeriodType.FOURWEEK) {
//            LocalDate startDateOfCurrentPeriod = getStartingDaysOfWeeksOfCurrentPeriod(currentDate, PeriodType.FOURWEEK).get(0);
//            LocalDate startDateOfPreviousPeriod = startDateOfCurrentPeriod.minusDays(28);
//            for (int i = 0; i < 4; i++) {
//                startingDatesList.add(startDateOfPreviousPeriod);
//                startDateOfPreviousPeriod = startDateOfPreviousPeriod.plusDays(7);
//
//            }
//
//
//        }
//        return startingDatesList;
//    }

    /**
     * @param currentDate   date in current period
     * @param periodType period profile
     * @return list of starting days of full weeks in PREVIOUS, "payment" period
     */
    public List<Date> getStartingDaysOfWeeksOfPreviousPeriod(Date currentDate, PeriodType periodType) {
        List<Date> startingDatesList = new ArrayList<>();
        if (periodType == PeriodType.MONTH) {
            Date dayInPreviousPeriod;
            Date firstMondayInThisPeriod = DateTools.getDateOfFirstMonday(currentDate);
            if (DateTools.isBefore(currentDate,firstMondayInThisPeriod)) {
                dayInPreviousPeriod = DateTools.getDatePlusDays(DateTools.getDatePlusMonth(currentDate,-1), -15);
            } else {
                dayInPreviousPeriod = DateTools.getDatePlusMonth(currentDate, -1);
            }
            startingDatesList = getStartingDaysOfWeeksOfCurrentPeriod(dayInPreviousPeriod, PeriodType.MONTH);


        } else if (periodType == PeriodType.FOURWEEK) {
            Date startDateOfCurrentPeriod = getStartingDaysOfWeeksOfCurrentPeriod(currentDate, PeriodType.FOURWEEK).get(0);
            Date startDateOfPreviousPeriod = DateTools.getDatePlusDays(startDateOfCurrentPeriod, -28);
            for (int i = 0; i < 4; i++) {
                startingDatesList.add(startDateOfPreviousPeriod);
                startDateOfPreviousPeriod = DateTools.getDatePlusDays(startDateOfPreviousPeriod, 7);

            }


        }
        return startingDatesList;
    }


//


}

