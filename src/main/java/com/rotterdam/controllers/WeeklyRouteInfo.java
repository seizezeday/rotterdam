package com.rotterdam.controllers;

import com.rotterdam.dto.TotalTimeDto;
import com.rotterdam.dto.WeekDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.WeekService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author Anatolii
 */
@Path("/")
@PermitAll
@Named
public class WeeklyRouteInfo {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private WeekService weekService;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/time")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo(@Context HttpServletRequest hsr, String data) throws ParseException, IOException {
        WeekDto weekDto = WeekDto.parseTimeTab(data);
        long userId = jsonCommands.getUserFromRequest(hsr).getId();
        final TotalTimeDto totalTime = weekService.save(weekDto, userId);
        if (totalTime != null) {
//            final DecimalFormat df = new DecimalFormat("#.00");
//            final String stringTotalTime = df.format(totalTime);
//            Object obj = new Object(){
//                public String totalTime = stringTotalTime;
//            };
            return Response.ok(totalTime).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

//    @RolesAllowed({ "Driver" })
//    @POST
//    @Path("/time2")
//    @Produces({ MediaType.APPLICATION_JSON })
//    public Response getTimeInfo2(@Context HttpServletRequest hsr) throws ParseException {
//        JsonArray jsonData = jsonCommands.getUserTimeData(hsr, "2014-12-07");   //TODO: date -  will be string from front-end (Week number)
//        if (jsonData != null){
//            return Response.ok(jsonData).build();
//        } else {
//            return Response.status(Response.Status.UNAUTHORIZED).build();
//        }
//    }

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/week")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getCurrentWeek(@Context HttpServletRequest hsr, String data) throws JsonException, IOException, ParseException {
        Date date = jsonCommands.getDateFromJson(data);

        if (date != null){
            final List<Date> datesOfWeek = DateTools.getDatesOfWeekWithDate(date);
            Object obj = new Object(){
                public List<String> weekList = DateTools.formatDates(datesOfWeek);
            };
            return Response.ok(obj).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/timeTab")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeTabData(@Context HttpServletRequest hsr, String data) throws JsonException, IOException, ParseException {

        Date monday = DateTools.getDateOfPrevMonday(jsonCommands.getDateFromJson(data));

        User user = jsonCommands.getUserFromRequest(hsr);

        WeekDto weekDto = weekService.getWeekByStartDateAndUserId(monday, user.getId());

        return Response.ok(weekDto).build();
    }
    //{"days":{"Monday":{"date":"12-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":20,"dayType":"1"}]},"Tuesday":{"date":"13-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":0,"dayType":"2"}]},"Wednesday":{"date":"14-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":0,"dayType":"1"}]},"Thursday":{"date":"15-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":0,"dayType":"1"}]},"Friday":{"date":"16-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":10,"dayType":"1"},{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":30,"dayType":"1"}]},"Saturday":{"date":"17-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":0,"dayType":"1"}]},"Sunday":{"date":"18-01-2015","workHours":[{"startWorkingTime":"01-01-1970","endWorkingTime":"01-01-1970","restTime":0,"dayType":"1"}]}}}
}
