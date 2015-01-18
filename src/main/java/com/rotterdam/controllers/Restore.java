package com.rotterdam.controllers;

import com.rotterdam.model.entity.User;
import com.rotterdam.tools.EmailSender;
import com.rotterdam.tools.SecuritySettings;
import com.rotterdam.tools.json.JsonCommands;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@PermitAll
@Named
public class Restore {

    @Inject
    private JsonCommands jsonCommands;

    @POST
	@Path("/restore")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response restorePassword(@Context HttpServletRequest hsr,
			@Context HttpServletResponse rspn, String data) {
        User user = jsonCommands.getRestoreData(data);
        if (user != null && user.getEmail() != null){
            EmailSender.sendForgotPassword(user.getFirstname(), user.getEmail(), SecuritySettings.decode(user.getPassword()));
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
	}

}
