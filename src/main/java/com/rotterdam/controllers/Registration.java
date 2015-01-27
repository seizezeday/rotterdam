package com.rotterdam.controllers;

import com.rotterdam.dto.UserDto;
import com.rotterdam.model.entity.UserRole;
import com.rotterdam.service.UserService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
//@PermitAll
@Named
public class Registration {

    @Inject
    private UserService userService;

    public static final int PARAM_ROLE_ID = 3;  // 1- Admin, 2 - Moderator, 3 - Driver, 4 - Unpaid

    @POST
	@Path("/registration")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response registerNewUser(UserDto userDto) {
        if (userService.save(userDto, UserRole.Driver))
            return Response.ok().build();
        else
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
	}
}
