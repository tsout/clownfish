package org.gem.persistence.service.api;

import java.util.Collection;

import org.gem.persistence.DAO;

public interface CommonPersistenceRemote <T extends DAO<K>,K> {
	public void save(T entity);
	public Collection <T> findAll();
	public T find (Class<T> e, K key);
	
}
