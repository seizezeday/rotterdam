package com.rotterdam.tools;

import com.rotterdam.model.dao.SessionDao;
import com.rotterdam.model.entity.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by root on 17.01.15.
 */
@Named
public class SessionUtil {

    @Inject
    SessionDao sessionDao;

    @Transactional
    public void update(Session session){
        sessionDao.update(session);
    }
}
