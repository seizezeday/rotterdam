package com.rotterdam.controllers;

import com.paypal.core.rest.PayPalRESTException;
import com.rotterdam.dto.UserDto;
import com.rotterdam.model.entity.User;
import com.rotterdam.model.entity.UserRole;
import com.rotterdam.service.UserService;
import com.rotterdam.service.payment.PaymentService;
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
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

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

    @Inject
    private PaymentService paymentService;

	@POST
	@Path("/login")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response loginAuth(@Context HttpServletRequest hsr,
			@Context HttpServletResponse rspn, String data, @HeaderParam("Origin") String domain)
            throws JSONException, PayPalRESTException {
		JSONObject loginData = new JSONObject(data);
		User user = userService
				.getByEmailAndPass(loginData.getString("login"), loginData.getString("password"));
		if (user != null) {
            final boolean userPayed = userService.isUserPayed(user);
            final String approvalLink;
            if(userPayed) {
                cookieUtil.insertSessionUID(rspn, user);
                approvalLink = null;
            }
            else {
                approvalLink = userService.doPaymentLogin(user, domain);
            }
            return Response.ok(
                    new Object(){
                        public boolean payed = userPayed;
                        public String link = approvalLink;
                    }).build();
        }
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerNewUser(UserDto userDto, @HeaderParam("Origin") String domain) throws PayPalRESTException {
        try{
            final String approvalUrl = userService.save(userDto, UserRole.Driver, domain);
            if(approvalUrl != null)
                return Response.ok(new Object(){public String link = approvalUrl;}).build();
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

    @GET
    @Consumes({ MediaType.TEXT_PLAIN })
    @Path("/finishPayment")
    public Response finishPayment(
            @QueryParam("paymentId")String paymentId,
            @QueryParam("token")String token,
            @QueryParam("PayerID")String PayerID) throws PayPalRESTException, URISyntaxException {

        paymentService.finishPayment(paymentId, PayerID);

        URI location = new URI("/");

        return Response.temporaryRedirect(location).build();
    }

}
