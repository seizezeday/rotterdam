package com.rotterdam.model.dao;

import com.rotterdam.dto.StartEndDto;
import com.rotterdam.model.entity.Week;
import org.hibernate.NonUniqueResultException;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Date;

/**
 * Created by vasax32 on 17.01.15.
 */
@Named
@Scope("singleton")
public class WeekDao extends AbstractGenericDao<Week> {

    public Week selectByStartDateAndUser(Date startDate, long userId){
        Query query = entityManager.createQuery("select week from Week week where week.startDate = :startDate and week.period.user.id =:userId");
        query.setParameter("startDate", startDate);
        query.setParameter("userId", userId);
        try{
            return (Week)query.getSingleResult();
        } catch(NoResultException | NonUniqueResultException e){
            return null;
        }
    }

    public Week selectByDateBetweenAndUser(Date date, long userId){
        Query query = entityManager.createQuery("select week from Week week where :date between week.startDate and week.endDate and week.period.user.id =:userId");
        query.setParameter("date", date);
        query.setParameter("userId", userId);
        try{
            return (Week)query.getSingleResult();
        } catch(NoResultException | NonUniqueResultException e){
            return null;
        }
    }



}
