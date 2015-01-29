package com.rotterdam.model.dao;

import com.rotterdam.model.entity.Declaration;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by root on 17.01.15.
 */
@Named
@Scope("singleton")
public class DeclarationDao extends AbstractGenericDao<Declaration> {
    public List<Declaration> selectByStartDateAndUser(long weekId, long userId){
        Query query = entityManager.createQuery("select declaration from Declaration declaration where" +
                " declaration.week.idWeek = :weekId" +
                " and declaration.week.period.user.id =:userId");
        query.setParameter("weekId", weekId);
        query.setParameter("userId", userId);

        return (List<Declaration>)query.getResultList();
    }

    public void delete(Declaration declaration){
        entityManager.remove(declaration);
    }
}
