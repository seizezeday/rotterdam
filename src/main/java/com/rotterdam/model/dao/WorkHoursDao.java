package com.rotterdam.model.dao;

import com.rotterdam.model.entity.User;
import com.rotterdam.model.entity.WorkHour;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 17.01.15.
 */
@Named
@Scope("singleton")
public class WorkHoursDao extends AbstractGenericDao<WorkHour> {

    public List<WorkHour> selectAllByUser(User user) {
        Query query = entityManager.createQuery(
                "select workHours from WorkHour workHours where workHours.user.id = :userId");
        query.setParameter("userId", user.getId());
        return (List<WorkHour>)query.getResultList();
    }

    public List<WorkHour> selectByUserAndDate(Date date, User user) {
        Query query = entityManager.createQuery(
                "select workHours from WorkHour workHours where workHours.user.id = :userId and workHours.date = :date");
        query.setParameter("userId", user.getId());
        query.setParameter("date", date);
        return (List<WorkHour>)query.getResultList();
    }
}
