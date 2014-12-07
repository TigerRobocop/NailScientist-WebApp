package DAL.Person;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import basicas.person.Employee;

import DAL.DAL_Generic;

public class DAL_Employee extends DAL_Generic<Employee>{

	public DAL_Employee(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> listAll(){
		List<Employee> retorno = null;
		
		try {
			Query q = getEntityManager().createNamedQuery("Employee.findAll");
			retorno = q.getResultList();
		} catch(PersistenceException e){
			System.out.println(e.getMessage());
		}		
		return  retorno;
	}
	
	public Employee findByName(String name){
		Employee retorno = null;
		
		try {
			Query q = getEntityManager().createNamedQuery("Employee.findByName");
		q.setParameter("name", name);
			retorno = (Employee)q.getSingleResult();
		} catch(PersistenceException e){
			System.out.println(e.getMessage());
		}		
		return  retorno;
	}
	
	public Employee isLoginValid(Employee e){
		Employee retorno = null;
		
		try {
			String query = "select e from Employee e where e.login = :login and e.password = :password";
			Query q = getEntityManager().createQuery(query, Employee.class);
			q.setParameter("login", e.getLogin());	
			q.setParameter("password", e.getPassword());
			
			retorno = (Employee)q.getSingleResult();
				
			
		} catch (PersistenceException ex) {
			System.out.println(ex.getMessage());
		}
		
		
		return retorno;
	}
	
	public Employee findByLogin(String login){
		Employee retorno = null;
		try {
			String query = "select e from Employee e where e.login = :login";
			Query q = getEntityManager().createQuery(query, Employee.class);
			q.setParameter("login", login);	
			retorno = (Employee)q.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> findByFilters(Employee e){
		List<Employee> retorno = null;
		String name = "";
		if (e.getName() != null) {
			name = e.getName();
		}
		
		try {
			String query = "select e from Employee e where e.name like :name";
			Query q = getEntityManager().createQuery(query, Employee.class);
			q.setParameter("name", "%" + name + "%");
			/*q.setParameter("color", "%"+ color +"%");
			q.setParameter("brand", "%"+ brand +"%");
			q.setParameter("finish", "%"+ finish +"%");
			*/
			retorno = q.getResultList();
			
		} catch (PersistenceException ex) {
			System.out.println(ex.getMessage());
		}
		return retorno;
	}

}
