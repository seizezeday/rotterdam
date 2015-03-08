package com.rotterdam.dto;

import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 17.01.15.
 */
public class UserInfoDto {

    public String firstname;
    public String date;
    public String currentYear;
    public String currentMonth;
    public int currentWeekNumber;
    public List<String> weekList;
    public String regNum;

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "firstname='" + firstname + '\'' +
                ", date='" + date + '\'' +
                ", currentYear='" + currentYear + '\'' +
                ", currentMonth='" + currentMonth + '\'' +
                ", currentWeekNumber=" + currentWeekNumber +
                ", regNum=" + regNum +
                ", weekList=" + weekList +
                '}';
    }

    public static UserInfoDto formatForNow(String userName, String regNum){
        Date currentDate = new Date();
        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.firstname = userName;
        userInfoDto.regNum = regNum;
        userInfoDto.date = new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN).format(currentDate);
        userInfoDto.currentYear = new SimpleDateFormat(JsonCommands.PARAM_YEAR_PATTERN).format(currentDate);
        userInfoDto.currentMonth = new SimpleDateFormat(JsonCommands.PARAM_MONTH_PATTERN).format(currentDate);

        userInfoDto.currentWeekNumber = DateTools.getCurrentWeekNumber(currentDate);

        userInfoDto.weekList = new ArrayList<>();
        List<Date> daysOfWeek = DateTools.getDatesOfWeekWithDate(currentDate);

        userInfoDto.weekList = DateTools.formatDates(daysOfWeek);
        return userInfoDto;
    }
}
