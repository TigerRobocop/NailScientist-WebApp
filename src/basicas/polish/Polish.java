package basicas.polish;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import basicas.service.Appointment;


@Entity
@NamedQueries({
	@NamedQuery(name="Polish.findAll", query="select p from Polish p"),
	@NamedQuery(name="Polish.findByName", query="select p from Polish p "
			+ "where p.name = :name"),
	@NamedQuery(name="Polish.findByBrand", query="select p from Polish p"
			+ " where p.brand.name = :brand")
})
public class Polish {

	public Polish() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	String name;
	String color;
	
	@OneToOne
	@JoinColumn(name="id_brand")
	Brand brand;
	
	@OneToOne
	@JoinColumn(name="id_finish")
	Finish finish;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="inventory_control",
	joinColumns={@JoinColumn(name="id_polish")},
	inverseJoinColumns={@JoinColumn(name="id_schedule")})
	Collection<Appointment> appointmentsUsed;
	
	//
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Finish getFinish() {
		return finish;
	}
	public void setFinish(Finish finish) {
		this.finish = finish;
	}


	

}
