package com.rotterdam.tools.json;

import com.rotterdam.controllers.auth.CookieUtil;
import com.rotterdam.dto.UserInfoDto;
import com.rotterdam.model.dao.SessionDao;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.dao.WorkHoursDao;
import com.rotterdam.model.entity.RideType;
import com.rotterdam.model.entity.User;
import com.rotterdam.model.entity.WorkHour;
import com.rotterdam.tools.DateTools;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.json.*;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Anatolii
 */
@Named
@Scope("singleton")
public class JsonCommands {

    @Inject
    private SessionDao sessionDao;

    @Inject
    private UserDao userDao;

    @Inject
    private WorkHoursDao workHoursDao;

    public static final String PARAM_FIRSTNAME = "firstname";
    public static final String PARAM_EMAIL_FORGOT = "email_forgot";
    public static final String PARAM_AVL_TIME_FOR_TIME = "avl_time_for_time";
	public static final String PARAM_AVL_TIME_FOR_PAY = "avl_time_for_pay";
    public static final String PARAM_START_WORKING_TIME = "startWorkingTime";
    public static final String PARAM_END_WORKING_TIME = "endWorkingTime";
    public static final String PARAM_RIDE_TYPE = "rideType";
    public static final String PARAM_REST_TIME = "restTime";
    public static final String PARAM_DATE = "date";
    public static final String PARAM_WEEK_LIST = "weekList";
    public static final String PARAM_CURRENT_YEAR = "currentYear";
    public static final String PARAM_CURRENT_MONTH = "currentMonth";
    public static final String PARAM_CURRENT_WEEK_NUMBER = "currentWeekNumber";
    public static final String PARAM_DATE_PATTERN = "yyyy-MM-dd";
    public static final String PARAM_DATE_FULL_PATTERN = "yyyy/MM/dd HH:mm";
    public static final String PARAM_YEAR_PATTERN = "yyyy";
    public static final String PARAM_MONTH_PATTERN = "MM";
    public static final String PARAM_TIME_PATTERN = "HH-mm";


    private JsonCommands(){}

    /**
     * return null if user not exist in session
     */
    public JsonObject getUserHomeData (HttpServletRequest hsr) throws JsonException{
        CookieUtil cookieUtil = new CookieUtil();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        User user = getUserFromRequest(hsr);
        if (user != null){
            JsonObject resultJsonDate = Json.createObjectBuilder()
                    .add(PARAM_FIRSTNAME, user.getFirstname())
                    .add(PARAM_DATE, dateFormat.format(currentDate))
                    .build();
            return resultJsonDate;

        } else {
            return  null;
        }
    }

    /**
     * return null if email not exist in database
     */
    public User getRestoreData (String data) throws JSONException{
        JSONObject emailData = new JSONObject(data);
        User user = userDao
                .selectByEmail(emailData.getString(PARAM_EMAIL_FORGOT));
        if (user != null) {
            return user;
        } else {
            return null;
        }

    }

    /**
     *
     * @param hsr, useHours.
     * @return Jsonobject containing avaliable time-for-time hours after subtraction of used hours
     * @throws JsonException
     */

    public JsonObject getTimeForTimeHours(HttpServletRequest hsr, Long useHours) throws JsonException {
        CookieUtil cookieUtil = new CookieUtil();
        User user = getUserFromRequest(hsr);
        if (user != null) {
            //TODO: Implement real request to db and data retrieval
            JsonObject jsonAvaliableTimeForTime = Json.createObjectBuilder()
                    .add(PARAM_AVL_TIME_FOR_TIME,"available time-for-time minus " + useHours)
                    .build();
            return jsonAvaliableTimeForTime;
        } else {
            return null;
        }


    }

	/**
    *
    * @param hsr, newHours.
    * @return Jsonobject containing avaliable time-to-pay hours after subtraction of used hours
    * @throws JsonException
    */
	
	public JsonObject getTimeForPayHours(HttpServletRequest hsr, Long newHours) throws JsonException {
       CookieUtil cookieUtil = new CookieUtil();
       User user = getUserFromRequest(hsr);
       if (user != null) {
           JsonObject jsonAvaliableTimeForPay = Json.createObjectBuilder()
                   .add(PARAM_AVL_TIME_FOR_PAY, newHours.toString())
                   .build();
           return jsonAvaliableTimeForPay;
       } else {
           return null;
       }
   }
	
    /**
     * return null if any data not exist in database for this date
     */
    public JsonArray getUserTimeData(HttpServletRequest hsr, String data) throws ParseException {
        JsonArray workHoursArray = null;
        User user = getUserFromRequest(hsr);
        DateFormat sdf = new SimpleDateFormat(PARAM_DATE_PATTERN);
        Date date = sdf.parse(data);
        List<WorkHour> workHours = workHoursDao
                .selectByUserAndDate(date, user);
        if (workHours != null){
            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
            for (WorkHour wh : workHours){
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                jsonObjectBuilder.add(PARAM_DATE, wh.getDate().toString());
                jsonObjectBuilder.add(PARAM_START_WORKING_TIME, wh.getStartWorkingTime().toString());
                jsonObjectBuilder.add(PARAM_END_WORKING_TIME, wh.getEndWorkingTime().toString());
                jsonObjectBuilder.add(PARAM_REST_TIME, wh.getRestTime());
                jsonObjectBuilder.add(PARAM_RIDE_TYPE, wh.getRideType().toString());
                jsonArray.add(jsonObjectBuilder);
            }
            workHoursArray= jsonArray.build();
        }

        if (workHoursArray != null) {
            return workHoursArray;
        } else {
            return null;
        }
    }
    /**
     * return null if any data not exist in database for this date
     */
    public WorkHour parseTimeTab (HttpServletRequest hsr, String data) throws ParseException {
        JSONObject timeTabData = new JSONObject(data);
        WorkHour workHours = null;
        DateFormat dateFormat = null;
        DateFormat timeFormat = null;
        User user = getUserFromRequest(hsr);
        if (user != null) {
            workHours = new WorkHour();
            dateFormat = new SimpleDateFormat(PARAM_DATE_PATTERN);
            timeFormat = new SimpleDateFormat(PARAM_TIME_PATTERN);

            Date date = dateFormat.parse(timeTabData.getString(PARAM_DATE));    //TODO: check actual date
            Date startWorkingTime = timeFormat.parse(timeTabData.getString(PARAM_START_WORKING_TIME));  //TODO: Check if start working time earlier then end
            Date endWorkingTime = timeFormat.parse(timeTabData.getString(PARAM_END_WORKING_TIME));

            workHours.setRideType(RideType.valueOf(timeTabData.getString(PARAM_RIDE_TYPE)));
            workHours.setStartWorkingTime(startWorkingTime);
            workHours.setEndWorkingTime(endWorkingTime);
            workHours.setDate(date);
            workHours.setRestTime(timeTabData.getInt(PARAM_REST_TIME));
            workHours.setUser(user);

            return workHours;
        } else {
            return null;
        }
    }
    /**
     * return null if user not exist in session
     */
    public UserInfoDto getInitAfterLoginData (HttpServletRequest hsr) throws JsonException, ParseException {
        User user = getUserFromRequest(hsr);
        if (user != null){
            Date currentDate = new Date();
            DateFormat yearFormat = new SimpleDateFormat(PARAM_YEAR_PATTERN);
            DateFormat monthFormat = new SimpleDateFormat(PARAM_MONTH_PATTERN);
            DateFormat dateFormat = new SimpleDateFormat(PARAM_DATE_FULL_PATTERN);
            DateFormat simpleDateFormat = new SimpleDateFormat(PARAM_DATE_PATTERN);

//            JsonObjectBuilder resultJsonDate = Json.createObjectBuilder()
//                    .add(PARAM_FIRSTNAME, user.getFirstname())
//                    .add(PARAM_DATE, dateFormat.format(currentDate))
//                    .add(PARAM_CURRENT_YEAR, yearFormat.format(currentDate))
//                    .add(PARAM_CURRENT_MONTH, monthFormat.format(currentDate))
//                    .add(PARAM_CURRENT_WEEK_NUMBER, DateTools.getCurrentWeekNumber(currentDate));
//
//            JsonArrayBuilder jsonArray = Json.createArrayBuilder();
//            List<Date> daysOfWeek = DateTools.getDateForWeekMonthYear(currentDate);
//            for (Date date : daysOfWeek) {
//                jsonArray.add(simpleDateFormat.format(date));
//            }
//            resultJsonDate.add(PARAM_WEEK_LIST, jsonArray);
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.firstname = user.getFirstname();
            userInfoDto.date = dateFormat.format(currentDate);
            userInfoDto.currentYear = yearFormat.format(currentDate);
            userInfoDto.currentMonth = monthFormat.format(currentDate);
            userInfoDto.currentWeekNumber = DateTools.getCurrentWeekNumber(currentDate);
            userInfoDto.weekList = new ArrayList<>();
            List<Date> daysOfWeek = DateTools.getDateForWeekMonthYear(currentDate);
            for (Date date : daysOfWeek) {
                userInfoDto.weekList.add(simpleDateFormat.format(date));
            }
//            return resultJsonDate.build();
            return userInfoDto;

        } else {
            return  null;
        }
    }

    public  User getUserFromRequest(HttpServletRequest hsr) {
        CookieUtil cookieUtil = new CookieUtil();
        return sessionDao
                .selectBySessionId(cookieUtil.getSessionIdFromRequest(hsr))
                .getUser();
    }


    public JsonObject getWeekData (String data) throws JsonException{
        JSONObject timeTabData = new JSONObject(data);
        DateFormat simpleDateFormat = new SimpleDateFormat(PARAM_DATE_PATTERN);
        JsonObjectBuilder resultJsonDate = Json.createObjectBuilder();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        List<Date> daysOfWeek = DateTools.getDateForWeekMonthYear(convertWeekNameToNumber(timeTabData.getString(PARAM_CURRENT_WEEK_NUMBER)),
                convertMonthNameToNumber(timeTabData.getString(PARAM_CURRENT_MONTH)),
                timeTabData.getInt(PARAM_CURRENT_YEAR));
        for (Date date : daysOfWeek) {
            jsonArray.add(simpleDateFormat.format(date));
        }
        resultJsonDate.add(PARAM_WEEK_LIST, jsonArray);
        return resultJsonDate.build();
    }

    public int convertMonthNameToNumber(String month) {
        int result= -1;
        switch (month) {
            case "January" :
                result = 1;
                break;
            case "February" :
                result = 2;
                break;
            case "March" :
                result = 3;
                break;
            case "April" :
                result = 4;
                break;
            case "May" :
                result = 5;
                break;
            case "June" :
                result = 6;
                break;
            case "July" :
                result = 7;
                break;
            case "August" :
                result = 8;
                break;
            case "September" :
                result = 9;
                break;
            case "October" :
                result = 10;
                break;
            case "November" :
                result = 11;
                break;
            case "December" :
                result = 12;
                break;

        }
        return result;
    }

    public int convertWeekNameToNumber(String month) {
        int result= -1;
        switch (month) {
            case "Week 1" :
                result = 1;
                break;
            case "Week 2" :
                result = 2;
                break;
            case "Week 3" :
                result = 3;
                break;
            case "Week 4" :
                result = 4;
                break;

        }
        return result;
    }


}
