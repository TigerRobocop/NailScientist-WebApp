package basicas.service;


import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import basicas.person.Client;
import basicas.person.Employee;
import basicas.polish.Polish;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;

	@OneToOne
	@JoinColumn(name = "id_client")
	Client client;

	@OneToOne
	@JoinColumn(name = "id_employee")
	Employee employee;

	Date timeScheduling;
	Date startAppointment;
	public Date getStartAppointment() {
		return startAppointment;
	}

	public void setStartAppointment(Date startAppointment) {
		this.startAppointment = startAppointment;
	}

	public Date getEndAppointment() {
		return endAppointment;
	}

	public void setEndAppointment(Date endAppointment) {
		this.endAppointment = endAppointment;
	}

	Date endAppointment;

	@Type(type = "yes_no")
	boolean isConfirmed;

	String comments;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "inventory_control", 
	joinColumns = { @JoinColumn(name = "id_appointment") }, 
	inverseJoinColumns = { @JoinColumn(name = "id_polish") 
	})
	
	Collection<Polish> polishList;

	public Date getTimeScheduling() {
		return timeScheduling;
	}

	public void setTimeScheduling(Date timeScheduling) {
		this.timeScheduling = timeScheduling;
	}

	

	public boolean isConfirmed() {
		return isConfirmed;
	}

	public void setConfirmed(boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Collection<Polish> getPolishList() {
		return polishList;
	}

	public void setPolishList(Collection<Polish> polishList) {
		this.polishList = polishList;
	}

}
