package org.gem.persistence.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import org.gem.persistence.DAO;
import org.gem.persistence.service.api.CommonPersistenceRemote;
import org.gem.utils.EntityManagerUtil;

public abstract class AbstractPersistenceService<T extends DAO<K>, K> {

	private static final long serialVersionUID = 5065886356736288593L;
	EntityManager em = null;
	
	public AbstractPersistenceService() {
		initialize();
	}


	private void initialize() {
		em=getEntityManager();
	}


	public final  EntityManager getEntityManager(){
		if(em!=null && em.isOpen())
			return em; 
		return EntityManagerUtil.getEntityManager();
	}
	
	
	//YUCK: force client to specify parameter for similarly typed service? 
	public final Collection<T> findAll(Class<T> clazz){
		
		CriteriaQuery<T> query  = (CriteriaQuery<T>) getEntityManager().getCriteriaBuilder().createQuery(clazz);
		query.from(clazz);
		return getEntityManager().createQuery(query).getResultList();
	}

	
	//YUCK:  Force the commit for each save?  
	public void save(T e) {
		em.getTransaction().begin();
		if ((e.getId()!=null) && (em.find(e.getClass(), e.getId()) != null)) {
			em.merge(e);
		}
		else{
			em.persist(e);
		}
		em.getTransaction().commit();
	} 
	
	protected final void printAllData(Class<T> e) {
		List<T> list = (ArrayList<T>) findAll(e); 		
		for (T t : list)
			System.out.println(t);
	}
	
}
