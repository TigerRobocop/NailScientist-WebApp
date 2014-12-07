package DAL.Polish;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;


import DAL.DAL_Generic;
import basicas.polish.Polish;

public class DAL_Polish extends DAL_Generic<Polish> {

	public DAL_Polish(EntityManager em) {
		super(em);
	}

	@SuppressWarnings("unchecked")
	public List<Polish> listAll() {
		List<Polish> retorno = null;

		try {
			Query q = getEntityManager().createNamedQuery("Polish.findAll");
			retorno = q.getResultList();
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

	public Polish findByName(String name) {
		Polish retorno = null;

		try {
			Query q = getEntityManager().createNamedQuery("Polish.findByName");
			q.setParameter("name", name);
			retorno = (Polish) q.getSingleResult();
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<Polish> findByFilters(Polish p){
		List<Polish> retorno = null;
		String name = "", color = "", brand = "", finish = "";
		if (p.getName() != null) {
			name = p.getName();
		}if (p.getColor() != null) {
			color = p.getColor();
		}if (p.getBrand() != null) {
			if (p.getBrand().getName() != null) {
				brand = p.getBrand().getName();
			}			
		}if (p.getFinish() != null) {
			if (p.getFinish().getName() != null) {
				finish = p.getFinish().getName();
			}
		}
		
		try {
			String query = "select p from Polish p where p.name like :name and p.color like :color and p.brand.name like :brand and p.finish.name like :finish";
			Query q = getEntityManager().createQuery(query, Polish.class);
			q.setParameter("name", "%" + name + "%");
			q.setParameter("color", "%"+ color +"%");
			q.setParameter("brand", "%"+ brand +"%");
			q.setParameter("finish", "%"+ finish +"%");
			
			retorno = q.getResultList();
			
		} catch (PersistenceException e) {
			System.out.println(e.getMessage());
		}
		return retorno;
	}

}
