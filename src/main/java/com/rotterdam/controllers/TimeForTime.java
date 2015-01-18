package com.rotterdam.controllers;

/**
 * Created by Vasya on 02.12.2014.
 */



import org.json.JSONObject;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import javax.inject.Inject;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@PermitAll
public class TimeForTime {

    @Inject
    private JsonCommands jsonCommands;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/usetimefortime")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({MediaType.APPLICATION_JSON})
    public Response useTime(@Context HttpServletRequest hsr, @Context HttpServletResponse rspn, String data) throws JsonException {
        JSONObject useTimeForTimeData = new JSONObject(data);
        Long useHours = useTimeForTimeData.getLong("use_time_for_time");

        //TODO:work with db, update available time-for-time hours, Somehow use used hours as a free time

        JsonObject jsonData = jsonCommands.getTimeForTimeHours(hsr, useHours);

        if (jsonData != null){
            return Response.ok(jsonData).build();
        } else {
            return Response.status(401).build();
        }
    }

}
