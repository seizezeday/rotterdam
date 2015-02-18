package com.rotterdam.controllers;

import com.paypal.core.rest.PayPalRESTException;
import com.rotterdam.dto.UserDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.model.entity.UserRole;
import com.rotterdam.service.UserService;
import com.rotterdam.tools.CookieUtil;
import com.rotterdam.tools.EmailSender;
import com.rotterdam.tools.SecuritySettings;
import com.rotterdam.tools.json.JsonCommands;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
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
public class AuthController {

	@Inject
	public CookieUtil cookieUtil;

    @Inject
    private UserService userService;

    @Inject
    private JsonCommands jsonCommands;

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response loginAuth(@Context HttpServletRequest hsr,
			@Context HttpServletResponse rspn, String data)
			throws JSONException {
		JSONObject loginData = new JSONObject(data);
		User user = userService
				.getByEmailAndPass(loginData.getString("login"), loginData.getString("password"));
		if (user != null && cookieUtil.insertSessionUID(rspn, user))
			return Response.ok().build();
		else
			return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@POST
	@Path("/logout")
    @RolesAllowed({ "Driver" })
	@Produces(MediaType.APPLICATION_JSON)
	public Response logoutAuth(@Context HttpServletRequest hsr,
			@Context HttpServletResponse rspn) {
		if (cookieUtil.removeSessionUID(hsr, rspn))
			return Response.ok().build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

    @POST
    @Path("/ok")
    @RolesAllowed({ "Driver" })
    public Response ok(){
        return Response.ok().build();
    }

    @POST
    @Path("/registration")
    @Consumes({ MediaType.APPLICATION_JSON })
    public Response registerNewUser(UserDto userDto) throws PayPalRESTException {
        try{
            if(userService.save(userDto, UserRole.Driver))
                return Response.ok().build();
            else return Response.status(401).build();
        } catch (PayPalRESTException e){
            return Response.status(405).build();
        } catch (Exception e){
            return Response.status(401).build();
        }

    }

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
