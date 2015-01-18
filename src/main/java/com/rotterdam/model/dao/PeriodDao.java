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
    public Period selectByDateBetween(Date date){
        Query query = entityManager.createQuery("select period from Period period where :per between period.startDate and period.endDate");
        query.setParameter("per", date);
        try{
            return (Period)query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
