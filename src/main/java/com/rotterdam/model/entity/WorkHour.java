package com.rotterdam.model.entity;

import com.rotterdam.model.dao.HibernateL2Cache;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Anatolii
 */
@Entity
@Table(name="WORKHOURS")
public class WorkHour implements HibernateL2Cache, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWorkHours;

    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date startWorkingTime;
    @Temporal(TemporalType.TIME)
    private Date endWorkingTime;
    private int restTime;
    @Column(name = "rideType")
    @Enumerated(EnumType.STRING)
    private RideType rideType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser" , referencedColumnName = "id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idWeek")
    private Week week;

    public WorkHour() {
    }

    public long getIdWorkHours() {
        return idWorkHours;
    }

    public void setIdWorkHours(long idWorkHours) {
        this.idWorkHours = idWorkHours;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartWorkingTime() {
        return startWorkingTime;
    }

    public void setStartWorkingTime(Date startWorkingTime) {
        this.startWorkingTime = startWorkingTime;
    }

    public Date getEndWorkingTime() {
        return endWorkingTime;
    }

    public void setEndWorkingTime(Date endWorkingTime) {
        this.endWorkingTime = endWorkingTime;
    }

    public int getRestTime() {
        return restTime;
    }

    public void setRestTime(int restTime) {
        this.restTime = restTime;
    }

    public RideType getRideType() {
        return rideType;
    }

    public void setRideType(RideType rideType) {
        this.rideType = rideType;
    }


    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    //    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public void setUser(User idUser) {
        this.user = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkHour workHours = (WorkHour) o;

        if (idWorkHours != workHours.idWorkHours) return false;
        if (restTime != workHours.restTime) return false;
        if (date != null ? !date.equals(workHours.date) : workHours.date != null) return false;
        if (endWorkingTime != null ? !endWorkingTime.equals(workHours.endWorkingTime) : workHours.endWorkingTime != null)
            return false;
        if (user != null ? !user.equals(workHours.user) : workHours.user != null) return false;
        if (rideType != workHours.rideType) return false;
        if (startWorkingTime != null ? !startWorkingTime.equals(workHours.startWorkingTime) : workHours.startWorkingTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idWorkHours ^ (idWorkHours >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (startWorkingTime != null ? startWorkingTime.hashCode() : 0);
        result = 31 * result + (endWorkingTime != null ? endWorkingTime.hashCode() : 0);
        result = 31 * result + restTime;
        result = 31 * result + (rideType != null ? rideType.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WorkHours{" +
                "idWorkHours=" + idWorkHours +
                ", date=" + date +
                ", startWorkingTime=" + startWorkingTime +
                ", endWorkingTime=" + endWorkingTime +
                ", restTime=" + restTime +
                ", rideType=" + rideType +
                ", idUser=" + user +
                '}';
    }
}
