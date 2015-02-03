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
 * @author vasax32
 */
@Path("/")
@PermitAll
@Named
public class WeekController {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private WeekService weekService;

    //save timeTab and return totalTime
    @RolesAllowed({ "Driver" })
    @POST
    @Path("/time")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo(@Context HttpServletRequest hsr, String data) throws ParseException, IOException {
        WeekDto weekDto = WeekDto.parseTimeTab(data);
        long userId = jsonCommands.getUserFromRequest(hsr).getId();
        final TotalTimeDto totalTime = weekService.save(weekDto, userId);
        if (totalTime != null) {
            return Response.ok(totalTime).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    //return week dates from monday to sunday
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

    //return data for timeTab by passed date
    @RolesAllowed({ "Driver" })
    @POST
    @Path("/timeTab")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeTabData(@Context HttpServletRequest hsr, String data) throws JsonException, IOException, ParseException {

        Date monday = DateTools.getDateOfPrevMonday(jsonCommands.getDateFromJson(data));

        User user = jsonCommands.getUserFromRequest(hsr);

        WeekDto weekDto = weekService.getWeekByStartDateAndUserId(monday, user.getId());

        weekDto.totalTime = weekService.calculateTotalTime(weekDto, user.getId());

        weekDto.active = weekService.isActive(monday, user.getId());

        return Response.ok(weekDto).build();
    }
}