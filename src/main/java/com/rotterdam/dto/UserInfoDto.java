package com.rotterdam.dto;

import java.util.List;

/**
 * Created by root on 17.01.15.
 */
public class UserInfoDto {

    public String firstname;
    public String date;
    public String currentYear;
    public String currentMonth;
    public int currentWeekNumber;
    public List<String> weekList;

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "firstname='" + firstname + '\'' +
                ", date='" + date + '\'' +
                ", currentYear='" + currentYear + '\'' +
                ", currentMonth='" + currentMonth + '\'' +
                ", currentWeekNumber=" + currentWeekNumber +
                ", weekList=" + weekList +
                '}';
    }
}
