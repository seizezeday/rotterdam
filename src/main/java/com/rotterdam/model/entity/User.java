package com.rotterdam.model.entity;

import com.rotterdam.model.dao.HibernateL2Cache;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USER")
public class User implements Principal, HibernateL2Cache {

	@Id
	@GeneratedValue
	private long id;
	private String firstname;
	private String surname;
	private String zipcode;
	private String email;
	private String password;
    private double timeForPay;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idUserRole")
	private UserRole role;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Session> sessions;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Period> periods;

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		// if nothing changed - exit
		if (sameAsBefore(role))
			return;
		// setting new category
		this.role = role;

	}

	private boolean sameAsBefore(UserRole newOwner) {
		return role == null ? newOwner == null : role.equals(newOwner);
	}

	@JsonIgnore
	public ArrayList<Session> getSessions() {
		return new ArrayList<Session>(sessions);
	}

	@Deprecated
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public void removeSession(Session session) {
		if (!sessions.contains(session))
			return;
		// removing user from current category
		sessions.remove(session);
		session.setUser(null);
	}

	public void addSession(Session session) {
		if (sessions.contains(session))
			return;
		// add new user to current category
		sessions.add(session);
		// setting category of user to this category
		session.setUser(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public double getTimeForPay() {
        return timeForPay;
    }

    public void setTimeForPay(double timeForPay) {
        this.timeForPay = timeForPay;
    }

    @Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", surname="
				+ surname + ", zipcode=" + zipcode + ", email=" + email
				+ ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String getName() {
		return "UserPrincipal";
	}

}
