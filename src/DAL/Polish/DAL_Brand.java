package DAL.Polish;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;






import DAL.DAL_Generic;
import basicas.polish.Brand;


public class DAL_Brand extends DAL_Generic<Brand>{

	public DAL_Brand(EntityManager em) {
		super(em);
	}
	
	
	//DAL_Brand dao = DAL_Factory.getDALBrand();
	
	@SuppressWarnings("unchecked")
	public List<Brand> listAll(){
		List<Brand> retorno = null;
		
		try {
			Query q = getEntityManager().createNamedQuery("Brand.findAll");
			retorno = q.getResultList();
		} catch(PersistenceException e){
			System.out.println(e.getMessage());
		}		
		return  retorno;
	}
	
	public Brand findByName(String name){
		Brand retorno = null;
		
		try {
			Query q = getEntityManager().createNamedQuery("Brand.findByName");
		q.setParameter("name", name);
			retorno = (Brand)q.getSingleResult();
		} catch(PersistenceException e){
			System.out.println(e.getMessage());
		}		
		return  retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Brand> findByFilter(Brand b){
		List<Brand> retorno = null;
		String name = "";
		if (b.getName() != null) {
			name = b.getName();
		}		
		
		try {
			String query = "select b from Brand b where b.name like :name order by b.name";
			Query q = getEntityManager().createQuery(query, Brand.class);
			q.setParameter("name", "%" + name + "%");		
			
			retorno = q.getResultList();
			
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

}
