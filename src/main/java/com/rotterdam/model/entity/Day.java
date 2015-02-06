package com.rotterdam.model.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by vasax32 on 20.01.15.
 */
@Entity
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDay;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idWeek")
    private Week week;

    @OneToMany(mappedBy = "day")
    private List<WorkHour> workHours;

    @JsonIgnore
    @OneToMany(mappedBy = "day")
    //@LazyCollection(LazyCollectionOption.FALSE)
    private List<Declaration> declarations;

    public Day(Date date, Week week) {
        this.date = date;
        this.week = week;
    }

    public Day(){}

    public long getIdDay() {
        return idDay;
    }

    public void setIdDay(long id) {
        this.idDay = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public List<WorkHour> getWorkHours() {
        return workHours;
    }

    public void setWorkHours(List<WorkHour> workHours) {
        this.workHours = workHours;
    }

    public List<Declaration> getDeclarations() {
        return declarations;
    }

    public void setDeclarations(List<Declaration> declarations) {
        this.declarations = declarations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day = (Day) o;

        if (idDay != day.idDay) return false;
        if (date != null ? !date.equals(day.date) : day.date != null) return false;
        if (week != null ? !week.equals(day.week) : day.week != null) return false;
        if (workHours != null ? !workHours.equals(day.workHours) : day.workHours != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (idDay ^ (idDay >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (workHours != null ? workHours.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + idDay +
                ", date=" + date +
//                ", week=" + week +
//                ", workHours=" + workHours +
                '}';
    }
}
