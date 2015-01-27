package com.rotterdam.service;

import com.rotterdam.model.dao.SessionDao;
import com.rotterdam.model.entity.Session;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by root on 27.01.15.
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
    public void clearTimeout(long msec){
        List<Session> sessions = selectAll();
        int countOfDeletedSessions = 0;
        int nanos = (new Timestamp(Calendar.getInstance().getTime().getTime())).getNanos();
        for(int i = 0; i < sessions.size(); i++){
            long difference = nanos - sessions.get(i).getLastAccessedTime().getNanos();
            if(difference > msec){
                removeByStringId(sessions.get(i).getSessionId());
                countOfDeletedSessions++;
            }
        }
        //System.out.println("Count of deleted sessions: " + countOfDeletedSessions);
    }
}
