package com.rotterdam.model.entity;

import com.rotterdam.tools.json.deserializer.JsonDateDeserializer;
import com.rotterdam.tools.json.serializer.JsonDateSerializer;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by vasax32 on 29.01.15.
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long idDeclaration;

    private String costType;

    private double price;

    @Temporal(TemporalType.DATE)
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDay")
    @JsonIgnore
    private Day day;

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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Declaration that = (Declaration) o;

        if (idDeclaration != that.idDeclaration) return false;
        if (Double.compare(that.price, price) != 0) return false;
        if (costType != null ? !costType.equals(that.costType) : that.costType != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;

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
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (day != null ? day.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Declaration{" +
                "idDeclaration=" + idDeclaration +
                ", costType='" + costType + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", day=" + day +
                '}';
    }
}
