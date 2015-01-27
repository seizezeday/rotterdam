package com.rotterdam.tools;


import com.rotterdam.security.SessionIdentifierGenerator;
import com.rotterdam.model.entity.Session;
import com.rotterdam.model.entity.User;
import com.rotterdam.service.SessionService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named
public class CookieUtil {

    @Inject
	private SessionIdentifierGenerator sessionIdentifierGenerator;

    @Inject
    private SessionService sessionService;

    public String getSessionIdFromRequest(HttpServletRequest hsr) {
        if (hsr == null) return null;
        Cookie[] cookies = hsr.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("sessionUID")) {
                return c.getValue();
            }
        }
        return null;
    }

    //inserts in database and cookie
    @Transactional
    public boolean insertSessionUID(HttpServletResponse rspn, User user) {
        String sessionUID =sessionIdentifierGenerator.nextSessionId();
        if (sessionService.insert(new Session(sessionUID, user)) != null) {
            Cookie newCookie = new Cookie("sessionUID", sessionUID);
            rspn.addCookie(newCookie);
            return true;
        } else return false;
    }

    @Transactional
    public boolean removeSessionUID(HttpServletRequest hsr, HttpServletResponse rspn){
        String sessionId = getSessionIdFromRequest(hsr);
        if(sessionId != null && sessionService.removeByStringId(sessionId)) {
            Cookie invalidateCookie = new Cookie("sessionUID", sessionId);
            invalidateCookie.setMaxAge(0);
            rspn.addCookie(invalidateCookie);
            return true;
        }
        return false;
    }
}
