package com.rotterdam.model.dao;

import com.rotterdam.model.entity.User;
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
public class UserDao extends AbstractGenericDao<User> {
    public User selectByEmail(String email) {
        Query query = entityManager.createQuery("select user from User user where user.email = :email");
        query.setParameter("email", email);
        try {
            return (User) query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e){
            return null;
        }
    }

    public User selectByEmailAndPass(String email, String pass) {
        Query query = entityManager.createQuery("select user from User user where user.email = :email and user.password = :pass");
        query.setParameter("email", email);
        query.setParameter("pass", pass);
        try {
            return (User) query.getSingleResult();
        } catch (NonUniqueResultException | NoResultException e){
            return null;
        }
    }
}
