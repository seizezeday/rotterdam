package com.rotterdam.model.entity;


import com.rotterdam.model.dao.HibernateL2Cache;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Entity
@Table(name="USERROLE")
public class UserRole implements HibernateL2Cache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserRole;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> userList;

    @JsonIgnore
    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "RoleRule",
            joinColumns = {@JoinColumn(name = "idUserRole")},
            inverseJoinColumns = {@JoinColumn(name = "idRuleType")})
    private List<RuleType> rules;

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public ArrayList<User> getUserList(){
        return new ArrayList<User>(userList);
    }

   

    @JsonIgnore
    public List<RuleType> getRules() {
        return rules;
    }

  
    @Override
    public String toString() {
        return "UserRole{" +
                "idUserRole" + idUserRole +
                ", name='" + name + '\'' +
                ", rules='" + rules + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole that = (UserRole) o;

        if (idUserRole != that.idUserRole) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idUserRole;
        result = 31 * result + name.hashCode();
        return result;
    }
}
