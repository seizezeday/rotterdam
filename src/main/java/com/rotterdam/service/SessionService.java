package com.rotterdam.service;

import com.rotterdam.model.dao.SessionDao;
import com.rotterdam.model.entity.Session;
import com.rotterdam.tools.DateTools;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 27.01.15.
 */
@Named
public class SessionService {

    @Inject
    private SessionDao sessionDao;

    @Transactional
    public List<Session> selectAll(){
        return sessionDao.selectAll();
    }

    @Transactional
    public void update(Session session){
        sessionDao.update(session);
    }

    @Transactional
    public Session insert(Session session){
       return sessionDao.insert(session);
    }

    @Transactional
    public Session getByStringId(String id){
        return sessionDao.selectBySessionId(id);
    }

    @Transactional
    public boolean removeByStringId(String id){
        return sessionDao.deleteBySessionId(id);
    }

    @Transactional
    public void clearTimeout(double h){
        List<Session> sessions = selectAll();
        //int countOfDeletedSessions = 0;
        Date time = Calendar.getInstance().getTime();
        double currentHours = DateTools.getDoubleFormatHours(time);
        for(int i = 0; i < sessions.size(); i++){
            double difference = currentHours - DateTools.getDoubleFormatHours(sessions.get(i).getLastAccessedTime());
            if(difference > h){
                removeByStringId(sessions.get(i).getSessionId());
                //countOfDeletedSessions++;
            }
        }
        //System.out.println("Count of deleted sessions: " + countOfDeletedSessions);
    }
}
