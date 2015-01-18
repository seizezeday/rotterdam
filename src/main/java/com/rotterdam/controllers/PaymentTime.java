package com.rotterdam.controllers;

import org.json.JSONObject;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
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
@Named
public class PaymentTime {

    @Inject
    private JsonCommands jsonCommands;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/paymenttime")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({MediaType.APPLICATION_JSON})
    public Response payTime(@Context HttpServletRequest hsr, @Context HttpServletResponse rspn, String data) throws JsonException {
        
		JSONObject paymentTimeData = new JSONObject(data);
        Long availableHours = paymentTimeData.getLong("avl_time_for_pay");
		Long payHours = 10l/*paymentTimeData.getLong("use_time_for_pay")*/;
        Long newHours = availableHours - payHours;
        //TODO:work with db, update available time-for-time hours, Somehow use used hours as a free time
        
        JsonObject jsonData;
        if(newHours >= 0) {
        	jsonData = jsonCommands.getTimeForPayHours(hsr, newHours);
        }
        else {
        	jsonData = null;
        }

        if (jsonData != null){
            return Response.ok(jsonData).build();
        } else {
            return Response.status(401).build();
        }
    }
    
}