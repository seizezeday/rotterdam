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
@Table(name = "WEEK")
public class Week implements HibernateL2Cache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWeek;
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

    @JsonIgnore
    @OneToMany(mappedBy = "week")
    private List<WorkHour> workHours;

    @ManyToOne
    @JoinColumn(name = "idPeriod")
    private Period period;


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

    public long getIdWeek() {

        return idWeek;
    }

    public void setIdWeek(long idWeek) {
        this.idWeek = idWeek;
    }

    public List<WorkHour> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkHour> workHours) {
        this.workHours = workHours;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Week{" +
                "idWeek=" + idWeek +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", promiseMondayTime=" + promiseMondayTime +
                ", promiseTuesdayTime=" + promiseTuesdayTime +
                ", promiseWednesdayTime=" + promiseWednesdayTime +
                ", promiseThursdayTime=" + promiseThursdayTime +
                ", promiseFridayTime=" + promiseFridayTime +
                ", promiseSaturdayTime=" + promiseSaturdayTime +
                ", promiseSundayTime=" + promiseSundayTime +
                ", workHours=" + workHours +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Week week = (Week) o;

        if (idWeek != week.idWeek) return false;
        if (endDate != null ? !endDate.equals(week.endDate) : week.endDate != null) return false;
        if (promiseFridayTime != null ? !promiseFridayTime.equals(week.promiseFridayTime) : week.promiseFridayTime != null)
            return false;
        if (promiseMondayTime != null ? !promiseMondayTime.equals(week.promiseMondayTime) : week.promiseMondayTime != null)
            return false;
        if (promiseSaturdayTime != null ? !promiseSaturdayTime.equals(week.promiseSaturdayTime) : week.promiseSaturdayTime != null)
            return false;
        if (promiseSundayTime != null ? !promiseSundayTime.equals(week.promiseSundayTime) : week.promiseSundayTime != null)
            return false;
        if (promiseThursdayTime != null ? !promiseThursdayTime.equals(week.promiseThursdayTime) : week.promiseThursdayTime != null)
            return false;
        if (promiseTuesdayTime != null ? !promiseTuesdayTime.equals(week.promiseTuesdayTime) : week.promiseTuesdayTime != null)
            return false;
        if (promiseWednesdayTime != null ? !promiseWednesdayTime.equals(week.promiseWednesdayTime) : week.promiseWednesdayTime != null)
            return false;
        if (startDate != null ? !startDate.equals(week.startDate) : week.startDate != null) return false;
        if (workHours != null ? !workHours.equals(week.workHours) : week.workHours != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idWeek ^ (idWeek >>> 32));
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (promiseMondayTime != null ? promiseMondayTime.hashCode() : 0);
        result = 31 * result + (promiseTuesdayTime != null ? promiseTuesdayTime.hashCode() : 0);
        result = 31 * result + (promiseWednesdayTime != null ? promiseWednesdayTime.hashCode() : 0);
        result = 31 * result + (promiseThursdayTime != null ? promiseThursdayTime.hashCode() : 0);
        result = 31 * result + (promiseFridayTime != null ? promiseFridayTime.hashCode() : 0);
        result = 31 * result + (promiseSaturdayTime != null ? promiseSaturdayTime.hashCode() : 0);
        result = 31 * result + (promiseSundayTime != null ? promiseSundayTime.hashCode() : 0);
        result = 31 * result + (workHours != null ? workHours.hashCode() : 0);
        return result;
    }
}
