package com.rotterdam.controllers;

import com.rotterdam.dto.UserInfoDto;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/")
@PermitAll
@Named
public class UserInfo {

    @Inject
    private JsonCommands jsonCommands;

    @RolesAllowed({ "Driver" })
    @POST
    @Path("/home")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getInfo(@Context HttpServletRequest hsr ) throws JsonException, ParseException {
//        JsonObject jsonData = JsonCommands.getUserHomeData(hsr);
//        JsonObject jsonData = jsonCommands.getInitAfterLoginData(hsr);
        UserInfoDto jsonData = jsonCommands.getInitAfterLoginData(hsr);
        System.out.println(jsonData);
        //now we need to deal with period check

        if (jsonData != null){
            return Response.ok(jsonData).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

}
