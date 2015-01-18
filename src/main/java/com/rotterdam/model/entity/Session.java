package com.rotterdam.model.entity;


import com.rotterdam.model.dao.HibernateL2Cache;

import org.codehaus.jackson.annotate.JsonIgnore;


import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Calendar;


/**
 * @author Roman
 *
 */
@Entity
@Table(name = "SESSION")
public class Session implements HibernateL2Cache {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sessionId;

    //private int IdUser;      // user

    private Timestamp createTime;
    private Timestamp lastAccessedTime;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser")
    private User user;

    public Session(){}

    public Session(String sessionId, User user){
        this.sessionId = sessionId;
        this.user = user;
        createTime = new Timestamp(Calendar.getInstance().getTime().getTime());
        lastAccessedTime = new Timestamp(Calendar.getInstance().getTime().getTime());
    }
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Timestamp lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        //if nothing changed - exit
        if(sameAsBefore(user))
            return;
        //setting new user
        User oldUser = this.user;
        this.user = user;
        //remove user form old user
        if(oldUser != null)
            oldUser.removeSession(this);
        //and add it to new user
        if(user != null)
            user.addSession(this);
    }

    private boolean sameAsBefore(User newOwner) {
        return user ==null? newOwner == null : user.equals(newOwner);
    }


    @Override
    public String toString() {
        return "Session{" +
                "  sessionId='" + sessionId + '\'' +
                //", IdUser=" + IdUser +
                ", createTime=" + createTime +
                ", lastAccessedTime=" + lastAccessedTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        //if (IdUser != session.IdUser) return false;
        if (createTime != null ? !createTime.equals(session.createTime) : session.createTime != null) return false;
        if (lastAccessedTime != null ? !lastAccessedTime.equals(session.lastAccessedTime) : session.lastAccessedTime != null)
            return false;
        if (!sessionId.equals(session.sessionId)) return false;
        if (!user.equals(session.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionId.hashCode();
        //result = 31 * result + IdUser;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastAccessedTime != null ? lastAccessedTime.hashCode() : 0);
        result = 31 * result + user.hashCode();
        return result;
    }
}
