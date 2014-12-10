package DAL.Service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import basicas.person.Employee;
import basicas.service.Appointment;
import DAL.DAL_Generic;

public class DAL_Scheduling extends DAL_Generic<Appointment>{

	public DAL_Scheduling(EntityManager em) {
		super(em);
	}
		
	
	@SuppressWarnings("unchecked")
	public List<Appointment> listUnconfirmed(Employee emp){
		List<Appointment> retorno = null;		
		
		try {
			String query = "select sc from Appointment sc where sc.isConfirmed is false and sc.employee.login = :emp";
			Query q = getEntityManager().createQuery(query, Appointment.class);	
			q.setParameter("emp", emp.getLogin());	
			
			retorno = q.getResultList();
			
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}
	
	
}
