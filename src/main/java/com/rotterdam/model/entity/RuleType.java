package com.rotterdam.model.entity;


import com.rotterdam.model.dao.HibernateL2Cache;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

/**
 */
@Entity
@Table(name = "RULETYPE")
public class RuleType implements HibernateL2Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRuleType;

    private String name;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;

    @JsonIgnore
    @ManyToMany(mappedBy = "rules")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<UserRole> userRoles;

    public int getIdRuleType() {
        return idRuleType;
    }

    public void setIdRuleType(int idRuleType) {
        this.idRuleType = idRuleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonIgnore
    public List<UserRole> getUserCategories() {
        return userRoles;
    }

    @Deprecated
    public void setUserCategories(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public String toString() {
        return "RuleType{" +
                "idRuleType=" + idRuleType +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleType ruleType = (RuleType) o;

        if (enabled != ruleType.enabled) return false;
        if (idRuleType != ruleType.idRuleType) return false;
        if (!name.equals(ruleType.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idRuleType;
        result = 31 * result + name.hashCode();
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
