package com.rotterdam.controllers;

import com.rotterdam.dto.TimeForDto;
import com.rotterdam.service.TimeForService;
import com.rotterdam.tools.DateTools;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
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
 * Created by root on 21.01.15.
 */
@Path("/")
@PermitAll
@Named
public class TimeForInfo {

    @Inject
    private JsonCommands jsonCommands;

    @Inject
    private TimeForService timeForService;


    @RolesAllowed({ "Driver" })
    @POST
    @Path("/timeFor")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo(@Context HttpServletRequest hsr, String data) throws ParseException, IOException {
        Date monday = DateTools.getDateOfPrevMonday(jsonCommands.getDateFromJson(data));
        long userId = jsonCommands.getUserFromRequest(hsr).getId();
        TimeForDto timeForOfPrevPeriod = timeForService.getTimeForOfPrevPeriod(DateTools.convertToLocalDate(monday), userId);
        if (timeForOfPrevPeriod != null) {
            return Response.ok(timeForOfPrevPeriod).build();
        } else {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

}
