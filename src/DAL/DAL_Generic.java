package DAL;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

public abstract class DAL_Generic<Ent> {

	private EntityManager entityManager;
	private Class<Ent> classPersistence;
	
	@SuppressWarnings("unchecked")
	public DAL_Generic(EntityManager em) {
		this.setEntityManager(em);
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		classPersistence = (Class<Ent>)parameterizedType.getActualTypeArguments()[0];

	}
	
	
	
	public void insert(Ent obj){
		EntityTransaction tx = getEntityManager().getTransaction();
		try{
			tx.begin();
			getEntityManager().persist(obj);
			tx.commit();
		}catch(PersistenceException e){
			tx.rollback();
		}	
	}
	
	public void insertCollection(Collection<Ent> col){
		EntityTransaction tx = getEntityManager().getTransaction();
		try{
			tx.begin();
			for(Ent item: col){
				getEntityManager().persist(item);
			}			
			tx.commit();
		}catch(PersistenceException e){
			tx.rollback();
		}
	}
	
	public Ent update(Ent obj){
		EntityTransaction tx = getEntityManager().getTransaction();
		try {			
			tx.begin();			
			obj = getEntityManager().merge(obj);
			tx.commit();
			
		
		} catch(PersistenceException e){
			tx.rollback();
		}		
		return obj;
	}
	
	public void delete(Ent obj){
		EntityTransaction tx = getEntityManager().getTransaction();
		try {
			tx.begin();
			getEntityManager().remove(obj);
			tx.commit();
		} catch(PersistenceException e){
			tx.rollback();
		}
	}
	
	public final Ent findByID(Serializable key){
		Ent instance = null;
		try{
			instance = (Ent) getEntityManager().find(getClassPersistence(), key);
		}catch (PersistenceException e){
			e.printStackTrace();
		}
		return instance;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Class<Ent> getClassPersistence() {
		return classPersistence;
	}

	public void setClassPersistence(Class<Ent> classPersistence) {
		this.classPersistence = classPersistence;
	}

}
