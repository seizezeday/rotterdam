package com.rotterdam.security;

import com.rotterdam.controllers.auth.MySecurityContext;
import com.rotterdam.model.dao.SessionDao;
import com.rotterdam.model.entity.Session;
import com.rotterdam.model.entity.User;
import com.rotterdam.tools.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * This interceptor verify the access permissions for a user based on sessionID
 * stored in cookie
 * */
@Provider
@Component
public class SecurityInterceptor implements
		javax.ws.rs.container.ContainerRequestFilter {

    @Autowired
    private SessionDao sessionDao;

    @Autowired
    private SessionUtil sessionUtil;

	@Override
	public void filter(ContainerRequestContext request) {
		User user = null;
		Session session = null;
		// Get session id from request header
		java.util.Map<String, Cookie> cookies = request.getCookies();
		if (cookies != null) {
			Cookie sessionCookie = cookies.get("sessionUID");
			if (sessionCookie != null) {
				final String sessionId = sessionCookie.getValue();
				if (sessionId != null && sessionId.length() > 0) {
					// Load session object from repository
					session = sessionDao
							.selectBySessionId(sessionId);
					// Load associated user from session
					if (null != session) {
						checkRoleAllowed(request, session);
						session.setLastAccessedTime(new Timestamp(Calendar
								.getInstance().getTime().getTime()));
                        sessionUtil.update(session);
						user = session.getUser();
					} else {
						request.abortWith(ACCESS_DENIED);
					}
				}
			}
		}
		// Set com.rotterdam.security context
		request.setSecurityContext(new MySecurityContext(session, user));
	}

	private static final Response ACCESS_DENIED;// = new Response(
//			"Access denied for this resource", 401, new Headers<Object>());;
	private static final Response ACCESS_FORBIDDEN;// = new ServerResponse(
//			"Nobody can access this resource", 403, new Headers<Object>());;
	private static final Response SERVER_ERROR;// = new ServerResponse(
//			"INTERNAL SERVER ERROR", 500, new Headers<Object>());;

    static {
        ACCESS_DENIED = Response.status(401).build();
        ACCESS_FORBIDDEN = Response.status(403).build();
        SERVER_ERROR = Response.status(500).build();
    }

	private void checkRoleAllowed(ContainerRequestContext request,
			Session session) {
//		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) request
//				.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
//		Method method = methodInvoker.getMethod();
//		checkAllowedForAll(method);
//		checkDenyAll(request, method);
//		checkRolesAllowed(request, session, method);

	}

	private void checkAllowedForAll(Method method) {
		if (method.isAnnotationPresent(PermitAll.class)) {

		}
	}

	private void checkDenyAll(ContainerRequestContext request, Method method) {
		if (method.isAnnotationPresent(DenyAll.class)) {
			request.abortWith(ACCESS_FORBIDDEN);
		}
	}

	private void checkRolesAllowed(ContainerRequestContext request,
			Session session, Method method) {
		if (method.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed rolesAnnotation = method
					.getAnnotation(RolesAllowed.class);
			Set<String> rolesSet = new HashSet<String>(
					Arrays.asList(rolesAnnotation.value()));
			// Is user valid?
			if (!isUserAllowed(session.getUser(), rolesSet)) {
				request.abortWith(ACCESS_DENIED);
			}
		}
	}

	private boolean isUserAllowed(User user, Set<String> roleSet) {
		if (user == null)
			return false;
		return roleSet.contains(user.getRole().getName());
	}
}