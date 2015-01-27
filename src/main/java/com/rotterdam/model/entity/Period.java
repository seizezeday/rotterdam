package com.rotterdam.model.entity;

import com.rotterdam.model.dao.HibernateL2Cache;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 18.01.15.
 */
@Entity
@Table(name = "PERIOD")
public class Period  implements HibernateL2Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPeriod;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;

    private double overTime;
    @Column(name = "periodType")
    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    @JsonIgnore
    @OneToMany(mappedBy = "period")
    private List<Week> weeks;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean calculated;

    public long getIdPeriod() {
        return idPeriod;
    }

    public void setIdPeriod(long idPeriod) {
        this.idPeriod = idPeriod;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PeriodType getPeriodType() {
        return periodType;
    }

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;
    }

    public double getOverTime() {
        return overTime;
    }

    public void setOverTime(double overTime) {
        this.overTime = overTime;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (idPeriod != period.idPeriod) return false;
        if (overTime != period.overTime) return false;
        if (endDate != null ? !endDate.equals(period.endDate) : period.endDate != null) return false;
        if (periodType != period.periodType) return false;
        if (startDate != null ? !startDate.equals(period.startDate) : period.startDate != null) return false;
        if (user != null ? !user.equals(period.user) : period.user != null) return false;
        if (weeks != null ? !weeks.equals(period.weeks) : period.weeks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = (int) (idPeriod ^ (idPeriod >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        temp = Double.doubleToLongBits(overTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (periodType != null ? periodType.hashCode() : 0);
        result = 31 * result + (weeks != null ? weeks.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (calculated ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "idPeriod=" + idPeriod +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", overTime=" + overTime +
                ", periodType=" + periodType +
                ", weeks=" + weeks +
                ", user=" + user +
                '}';
    }

    public boolean isCalculated() {
        return calculated;
    }

    public void setCalculated(boolean calculated) {
        this.calculated = calculated;
    }
}
