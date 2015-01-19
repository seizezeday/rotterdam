package com.rotterdam.model.dao;

import com.rotterdam.model.entity.Period;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.Date;

/**
 * Created by root on 17.01.15.
 */
@Named
@Scope("singleton")
public class PeriodDao extends AbstractGenericDao<Period> {
    public Period selectByDateBetweenAndUser(Date date, Long userId){
        Query query = entityManager.createQuery(
                "select period from Period period where :per between period.startDate and period.endDate and period.user.id = :userId");
        query.setParameter("per", date);
        query.setParameter("userId", userId);
        try{
            return (Period)query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    public Period selectLastPeriodByUser(Long userId){
        Query query = entityManager.createQuery(
                "select period from Period period where period.user.id = :userId and" +
                        " PERIOD.endDate = (select max(period.endDate) from Period period)");
        query.setParameter("userId", userId);
        try{
            return (Period)query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
