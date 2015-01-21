package com.rotterdam.controllers;

import com.rotterdam.dto.UserInfoDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.PeriodService;
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

    @Inject
    private PeriodService periodService;


    @RolesAllowed({ "Driver" })
    @POST
    @Path("/home")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getInfo(@Context HttpServletRequest hsr ) throws JsonException, ParseException {
//        UserInfoDto jsonData = jsonCommands.getInitAfterLoginData(hsr);
        User user = jsonCommands.getUserFromRequest(hsr);

//        System.out.println(jsonData);

        periodService.makePeriodCheck(jsonCommands.getUserFromRequest(hsr));

        if (user != null){
            UserInfoDto userInfoDto = UserInfoDto.formatForNow(user.getFirstname());
            return Response.ok(userInfoDto).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }



}
