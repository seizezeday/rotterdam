package com.rotterdam.model.dao;

import com.rotterdam.model.entity.Session;
import org.hibernate.NonUniqueResultException;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Created by root on 17.01.15.
 */
@Named
@Scope("singleton")
public class SessionDao extends AbstractGenericDao<Session> {

    public Session selectBySessionId(String sessionId) {
        Query query = entityManager.createQuery("select session from Session session where session.sessionId = :sessionId");
        query.setParameter("sessionId", sessionId);
        try {
            return (Session) query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e){
            return null;
        }
    }

    public boolean deleteBySessionId(String sessionid) {
        Session sessionobj = selectBySessionId(sessionid);
        try {
            entityManager.remove(sessionobj);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
