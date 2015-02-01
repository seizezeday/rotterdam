package com.rotterdam.security;

import com.rotterdam.model.entity.Session;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.SessionService;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by vasax32 on 27.01.15.
 */
@Provider    // register as jersey's provider
@PreMatching
@Named
@Scope("singleton")
public class SecurityContextFilter implements ContainerRequestFilter {

    public static final double timeout = 0.5; // 30 minutes

    @Inject
    private SessionService sessionService;

    private static boolean started = false;

    @PostConstruct
    public void init(){
        if(!started) {
            (new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            sessionService.clearTimeout(timeout);
                            Thread.sleep(1800000);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            })).start();
            started = true;
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        User user = null;
        Session session = null;

        // Get session id from request header
        java.util.Map<String, Cookie> cookies = requestContext.getCookies();

        if (cookies != null) {

            //Cookie sessionCookie = cookies.get("JSESSIONID");
            Cookie sessionCookie = cookies.get("sessionUID");
            //System.out.println(sessionCookie);
            if (sessionCookie != null) {

                final String sessionId = sessionCookie.getValue();
                //System.out.println("Find cookie: " + sessionId);

                if (sessionId != null && sessionId.length() > 0) {
                    // Load session object from repository
                    session = sessionService.getByStringId(sessionId);

                    // Load associated user from session
                    if (null != session) {
                        session.setLastAccessedTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
                        sessionService.update(session);
                        user = session.getUser();
                    }
                }
            }
        }

        // Set security context
        requestContext.setSecurityContext(new MySecurityContext(session, user));
        //return request;
    }
}
