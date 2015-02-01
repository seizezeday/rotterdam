package com.rotterdam.model.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;

/**
 * Created by vasax32 on 29.01.15.
 */
@Entity
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long idDeclaration;

    private String costType;

    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idWeek")
    @JsonIgnore
    private Week week;

    public long getIdDeclaration() {
        return idDeclaration;
    }

    public void setIdDeclaration(long idDeclaration) {
        this.idDeclaration = idDeclaration;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Declaration that = (Declaration) o;

        if (idDeclaration != that.idDeclaration) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (costType != null ? !costType.equals(that.costType) : that.costType != null) return false;
        if (week != null ? !week.equals(that.week) : that.week != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idDeclaration ^ (idDeclaration >>> 32));
        result = 31 * result + (costType != null ? costType.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (week != null ? week.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Declaration{" +
                "idDeclaration=" + idDeclaration +
                ", costType='" + costType + '\'' +
                ", price=" + price +
                ", week=" + week +
                '}';
    }
}
