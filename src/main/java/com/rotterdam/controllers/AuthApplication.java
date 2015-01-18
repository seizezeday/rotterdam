package com.rotterdam.controllers;

import com.rotterdam.controllers.auth.CookieUtil;
import com.rotterdam.model.dao.UserDao;
import com.rotterdam.model.entity.User;
import com.rotterdam.tools.SecuritySettings;
import org.json.JSONException;
import org.json.JSONObject;

import javax.annotation.security.PermitAll;
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
public class AuthApplication {

	@Inject
	public CookieUtil cookieUtil;

    @Inject
    public UserDao userDao;

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response loginAuth(@Context HttpServletRequest hsr,
			@Context HttpServletResponse rspn, String data)
			throws JSONException {
		JSONObject loginData = new JSONObject(data);
		User user = userDao
				.selectByEmailAndPass(loginData.getString("login"),
						SecuritySettings.code(loginData.getString("password")));
		if (user != null && cookieUtil.insertSessionUID(rspn, user))
			return Response.ok().build();
		else
			return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logoutAuth(@Context HttpServletRequest hsr,
			@Context HttpServletResponse rspn) {
		if (cookieUtil.removeSessionUID(hsr, rspn))
			return Response.ok().build();
		else
			return Response.status(Response.Status.NOT_FOUND).build();
	}

}
