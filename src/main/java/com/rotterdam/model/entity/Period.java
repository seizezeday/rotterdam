package com.rotterdam.model.entity;

import com.rotterdam.model.dao.HibernateL2Cache;
import org.codehaus.jackson.annotate.JsonIgnore;

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
    @Temporal(TemporalType.TIME)
    private Date promiseMondayTime;
    @Temporal(TemporalType.TIME)
    private Date promiseTuesdayTime;
    @Temporal(TemporalType.TIME)
    private Date promiseWednesdayTime;
    @Temporal(TemporalType.TIME)
    private Date promiseThursdayTime;
    @Temporal(TemporalType.TIME)
    private Date promiseFridayTime;
    @Temporal(TemporalType.TIME)
    private Date promiseSaturdayTime;
    @Temporal(TemporalType.TIME)
    private Date promiseSundayTime;
    private int timeForTime;
    @Column(name = "periodType")
    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    @JsonIgnore
    @OneToMany(mappedBy = "period")
    private List<WorkHour> workHours;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "id")
    private User user;

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

    public Date getPromiseMondayTime() {
        return promiseMondayTime;
    }

    public void setPromiseMondayTime(Date promiseMondayTime) {
        this.promiseMondayTime = promiseMondayTime;
    }

    public Date getPromiseTuesdayTime() {
        return promiseTuesdayTime;
    }

    public void setPromiseTuesdayTime(Date promiseTuesdayTime) {
        this.promiseTuesdayTime = promiseTuesdayTime;
    }

    public Date getPromiseWednesdayTime() {
        return promiseWednesdayTime;
    }

    public void setPromiseWednesdayTime(Date promiseWednesdayTime) {
        this.promiseWednesdayTime = promiseWednesdayTime;
    }

    public Date getPromiseThursdayTime() {
        return promiseThursdayTime;
    }

    public void setPromiseThursdayTime(Date promiseThursdayTime) {
        this.promiseThursdayTime = promiseThursdayTime;
    }

    public Date getPromiseFridayTime() {
        return promiseFridayTime;
    }

    public void setPromiseFridayTime(Date promiseFridayTime) {
        this.promiseFridayTime = promiseFridayTime;
    }

    public Date getPromiseSaturdayTime() {
        return promiseSaturdayTime;
    }

    public void setPromiseSaturdayTime(Date promiseSaturdayTime) {
        this.promiseSaturdayTime = promiseSaturdayTime;
    }

    public Date getPromiseSundayTime() {
        return promiseSundayTime;
    }

    public void setPromiseSundayTime(Date promiseSundayTime) {
        this.promiseSundayTime = promiseSundayTime;
    }

    public int getTimeForTime() {
        return timeForTime;
    }

    public void setTimeForTime(int timeForTime) {
        this.timeForTime = timeForTime;
    }

    public List<WorkHour> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkHour> workHours) {
        this.workHours = workHours;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Period period = (Period) o;

        if (idPeriod != period.idPeriod) return false;
        if (timeForTime != period.timeForTime) return false;
        if (endDate != null ? !endDate.equals(period.endDate) : period.endDate != null) return false;
        if (promiseFridayTime != null ? !promiseFridayTime.equals(period.promiseFridayTime) : period.promiseFridayTime != null)
            return false;
        if (promiseMondayTime != null ? !promiseMondayTime.equals(period.promiseMondayTime) : period.promiseMondayTime != null)
            return false;
        if (promiseSaturdayTime != null ? !promiseSaturdayTime.equals(period.promiseSaturdayTime) : period.promiseSaturdayTime != null)
            return false;
        if (promiseSundayTime != null ? !promiseSundayTime.equals(period.promiseSundayTime) : period.promiseSundayTime != null)
            return false;
        if (promiseThursdayTime != null ? !promiseThursdayTime.equals(period.promiseThursdayTime) : period.promiseThursdayTime != null)
            return false;
        if (promiseTuesdayTime != null ? !promiseTuesdayTime.equals(period.promiseTuesdayTime) : period.promiseTuesdayTime != null)
            return false;
        if (promiseWednesdayTime != null ? !promiseWednesdayTime.equals(period.promiseWednesdayTime) : period.promiseWednesdayTime != null)
            return false;
        if (startDate != null ? !startDate.equals(period.startDate) : period.startDate != null) return false;
        if (workHours != null ? !workHours.equals(period.workHours) : period.workHours != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idPeriod ^ (idPeriod >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (promiseMondayTime != null ? promiseMondayTime.hashCode() : 0);
        result = 31 * result + (promiseTuesdayTime != null ? promiseTuesdayTime.hashCode() : 0);
        result = 31 * result + (promiseWednesdayTime != null ? promiseWednesdayTime.hashCode() : 0);
        result = 31 * result + (promiseThursdayTime != null ? promiseThursdayTime.hashCode() : 0);
        result = 31 * result + (promiseFridayTime != null ? promiseFridayTime.hashCode() : 0);
        result = 31 * result + (promiseSaturdayTime != null ? promiseSaturdayTime.hashCode() : 0);
        result = 31 * result + (promiseSundayTime != null ? promiseSundayTime.hashCode() : 0);
        result = 31 * result + timeForTime;
        result = 31 * result + (workHours != null ? workHours.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Period{" +
                "idPeriod=" + idPeriod +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", promiseMondayTime=" + promiseMondayTime +
                ", promiseTuesdayTime=" + promiseTuesdayTime +
                ", promiseWednesdayTime=" + promiseWednesdayTime +
                ", promiseThursdayTime=" + promiseThursdayTime +
                ", promiseFridayTime=" + promiseFridayTime +
                ", promiseSaturdayTime=" + promiseSaturdayTime +
                ", promiseSundayTime=" + promiseSundayTime +
                ", timeForTime=" + timeForTime +
                ", workHours=" + workHours +
                '}';
    }
}
