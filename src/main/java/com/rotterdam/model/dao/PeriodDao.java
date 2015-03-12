package com.rotterdam.model.dao;

import com.rotterdam.dto.StartEndDto;
import com.rotterdam.model.entity.Period;
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

    public Period selectByStartAndEndDate(Date startDate, Date endDate, Long userId){
        Query query = entityManager.createQuery(
                "select period from Period period where period.startDate = :startDate and period.endDate = :endDate and period.user.id = :userId");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("userId", userId);
        try{
            return (Period)query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    public Period selectByStartDate(Date startDate, Long userId){
        Query query = entityManager.createQuery(
                "select period from Period period where period.startDate = :startDate and period.user.id = :userId");
        query.setParameter("startDate", startDate);
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
                        " PERIOD.endDate = (select max(period.endDate) from Period period where period.user.id = :userId)");
        query.setParameter("userId", userId);
        try{
            return (Period)query.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    public Period selectPrevPeriodByUser(Long userId){
        Query nativeQuery = entityManager.createNativeQuery(
                "SELECT * FROM PERIOD where PERIOD.endDate = (" +
                    "select max(period.endDate) from (" +
                        "select * from PERIOD where PERIOD.idUser = ? and PERIOD.idPeriod <> " +
                            "(SELECT PERIOD.idPeriod FROM PERIOD " +
                                "where PERIOD.idUser = ? and PERIOD.endDate = (" +
                                    "select max(period.endDate) from PERIOD period))" +
                    ") period) and PERIOD.idUser = ?", Period.class);
        nativeQuery.setParameter(1, userId);
        nativeQuery.setParameter(2, userId);
        nativeQuery.setParameter(3, userId);
        return (Period)nativeQuery.getSingleResult();
    }

    public StartEndDto selectMinMaxDatesByUser(long userId){
        Query query = entityManager.createQuery("SELECT NEW com.rotterdam.dto.StartEndDto(min(p.startDate), max(p.endDate)) FROM Period p where p.user.id =:userId");
//        Query query = entityManager.createQuery("SELECT NEW com.rotterdam.dto.StartEndDto(min(w.startDate), max(w.endDate)) FROM Week w where w.period.user.id =:userId");
        query.setParameter("userId", userId);
        try{
            return (StartEndDto)query.getSingleResult();
        } catch(NoResultException | NonUniqueResultException e){
            return null;
        }
    }
}
