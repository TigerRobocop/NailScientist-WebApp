package DAL.Polish;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import DAL.DAL_Generic;

import basicas.polish.Finish;

public class DAL_Finish extends DAL_Generic<Finish>{

	public DAL_Finish(EntityManager em) {
		super(em);
	}
	
	@SuppressWarnings("unchecked")
	public List<Finish> listAll(){
		List<Finish> retorno = null;
		
		try {
			Query q = getEntityManager().createNamedQuery("Finish.findAll");
			retorno = q.getResultList();
		} catch(PersistenceException e){
			System.out.println(e.getMessage());
		}		
		return  retorno;
	}
	
	public Finish findByName(String name){
		Finish retorno = null;
		
		try {
			Query q = getEntityManager().createNamedQuery("Finish.findByName");
		q.setParameter("name", name);
			retorno = (Finish)q.getSingleResult();
		} catch(PersistenceException e){
			System.out.println(e.getMessage());
		}		
		return  retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Finish> findByFilter(Finish f){
		List<Finish> retorno = null;
		String name = "";
		if (f.getName() != null) {
			name = f.getName();
		}		
		
		try {
			String query = "select f from Finish f where f.name like :name order by f.name";
			Query q = getEntityManager().createQuery(query, Finish.class);
			q.setParameter("name", "%" + name + "%");		
			
			retorno = q.getResultList();
			
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

}
