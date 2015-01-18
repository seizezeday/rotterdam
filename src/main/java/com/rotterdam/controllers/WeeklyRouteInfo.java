package com.rotterdam.controllers;

import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

/**
 * @author Anatolii
 */
@Path("/")
@PermitAll
@Named
public class WeeklyRouteInfo {

    @Inject
    private JsonCommands jsonCommands;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/time")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo(@Context HttpServletRequest hsr, String data) throws ParseException {
        System.out.println(data);
//        WorkHours workHours = JsonCommands.parseTimeTab(hsr, data);
//        if (workHours != null && Factory.getInstance().getWorkHoursDAO().insert(workHours)) {
            return Response.ok().build();
//        } else {
//            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
//        }

    }

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/time2")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getTimeInfo2(@Context HttpServletRequest hsr) throws ParseException {
        JsonArray jsonData = jsonCommands.getUserTimeData(hsr, "2014-12-07");   //TODO: date -  will be string from front-end (Week number)
        if (jsonData != null){
            return Response.ok(jsonData).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/week")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response getCurrentWeek(@Context HttpServletRequest hsr, String data) throws JsonException {
        JsonObject jsonData = jsonCommands.getWeekData(data);
        if (jsonData != null){
            return Response.ok(jsonData).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
