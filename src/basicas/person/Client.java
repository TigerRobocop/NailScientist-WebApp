package basicas.person;

import java.sql.Date;
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
	@NamedQuery(name="Client.findAll", query="select c from Client c"),
	@NamedQuery(name="Client.findByName", query="select c from Client c "
			+ "where c.name = :name")
})
public class Client extends Person{

	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	Date signUp;
	
	@Type(type="yes_no")
	boolean isPremium;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="Scheduling",
	joinColumns={@JoinColumn(name="id_client")},
	inverseJoinColumns={@JoinColumn(name="id_employee")})
	Collection<Employee> employees;
	
	

	public Date getSignUp() {
		return signUp;
	}

	public void setSignUp(Date signUp) {
		this.signUp = signUp;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public void setPremium(boolean isPremium) {
		this.isPremium = isPremium;
	}

	public Collection<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Collection<Employee> employees) {
		this.employees = employees;
	}

}
