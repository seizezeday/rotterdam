package com.rotterdam.controllers;

import com.rotterdam.dto.StartEndDto;
import com.rotterdam.dto.TotalTimeDto;
import com.rotterdam.dto.WeekDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.PeriodService;
import com.rotterdam.service.WeekService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.DateParameter;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @author vasax32
 */
@Path("/timeTab")
@PermitAll
@Named
public class WeekController {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private WeekService weekService;

    @Inject
    private PeriodService periodService;

    //save timeTab and return totalTime
    @RolesAllowed({ "Driver" })
    @POST
    //@Path("/time")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo(@Context HttpServletRequest hsr, WeekDto weekDto) throws ParseException, IOException {
        //WeekDto weekDto = WeekDto.parseTimeTab(data);
        long userId = jsonCommands.getUserFromRequest(hsr).getId();
        final TotalTimeDto totalTime = weekService.save(weekDto, userId);
        if (totalTime != null) {
            return Response.ok(totalTime).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }

    }

    //return data for timeTab by passed date
    @RolesAllowed({ "Driver" })
    @GET
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeTabData(@Context HttpServletRequest hsr, @QueryParam("date") DateParameter date) throws JsonException, IOException, ParseException {

        Date monday = DateTools.getDateOfPrevMonday(date.getDate());

        User user = jsonCommands.getUserFromRequest(hsr);

        WeekDto weekDto = weekService.getWeekByStartDateAndUserId(monday, user.getId());

        if(weekDto == null)
            weekDto = new WeekDto();

        weekDto.totalTime = weekService.calculateTotalTime(weekDto, user.getId());

        weekDto.active = weekService.isActive(monday, user.getId());


        weekDto.promisedTime = weekService.getPromisedWeekTime(monday, user.getId());

        if(weekDto.days == null || weekDto.days.size() == 0){
            //we need to make fake days
            weekDto.days = weekService.getFakeDays(monday);
        }

        if(!weekDto.active){
            weekService.ensureNullableOneWorkHour(weekDto);
        }

        if(weekDto.totalTime == null || weekDto.totalTime.days.size() == 0){
            //we need to make fake total-time
            weekDto.totalTime = weekService.getFakeTotalTime();
        }

        if(weekDto.promisedTime == null){
            weekDto.promisedTime = weekService.getFakePromisedTime();
        }

        if(weekDto.startEnd == null){
            weekDto.startEnd = new StartEndDto(monday, DateTools.getDateOf7DayAfter(monday));
        }

        return Response.ok(weekDto).build();
    }

    @RolesAllowed({"Driver"})
    @GET
    @Path("/getAvailableDates")
    public Response getAvailableDates(@Context HttpServletRequest hsr){
        User user = jsonCommands.getUserFromRequest(hsr);

        StartEndDto availableDates = periodService.getAvailableDates(user.getId());
        return Response.ok(availableDates).build();
    }
}
