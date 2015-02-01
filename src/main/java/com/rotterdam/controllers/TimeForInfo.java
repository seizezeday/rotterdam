package com.rotterdam.controllers;

import com.rotterdam.dto.OverTimeDto;
import com.rotterdam.dto.TimeForDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.TimeForService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
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

/**
 * Created by vasax32 on 21.01.15.
 */
@Path("/timeFor")
//@PermitAll
@Named
public class TimeForInfo {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private TimeForService timeForService;


    @RolesAllowed({ "Driver" })
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo(@Context HttpServletRequest hsr) throws ParseException, IOException {
        Date monday = DateTools.getDateOfPrevMonday(new Date());
        long userId = jsonCommands.getUserFromRequest(hsr).getId();
        TimeForDto timeForOfPrevPeriod = timeForService.getTimeForOfPrevPeriod(monday, userId);
        if (timeForOfPrevPeriod != null) {
            return Response.ok(timeForOfPrevPeriod).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        // {"timeForTime" : "2", "overTime" : "5"}
    }


    @RolesAllowed({"Driver"})
    @POST
    @Path("/setOverTime")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response setTimeInfo(@Context HttpServletRequest hsr, OverTimeDto overTimeParam) throws ParseException, IOException {
        User user = jsonCommands.getUserFromRequest(hsr);
        if (timeForService.saveOverTime(overTimeParam.overTime, user.getId()))
            return Response.ok().build();
        else return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        // {"overTime" : "5"}
    }

}
