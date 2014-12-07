package basicas.person;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Type;



@Entity
@NamedQueries({
	@NamedQuery(name="Employee.findAll", query="select e from Employee e"),
	@NamedQuery(name="Employee.findByName", query="select e from Employee e "
			+ "where e.name = :name")
})
public class Employee extends Person{

	public Employee() {
		// TODO Auto-generated constructor stub
	}	
	
	String position;
	@Type(type="yes_no")
	boolean isAdmin;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Scheduling",
	joinColumns={@JoinColumn(name="id_employee")},
	inverseJoinColumns={@JoinColumn(name="id_client")})
	Collection<Client> clientList;
	
	

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Collection<Client> getClientList() {
		return clientList;
	}

	public void setClientList(Collection<Client> clientList) {
		this.clientList = clientList;
	}

}
