package org.gem.persistence.service.impl;

import java.util.Collection;

import org.gem.persistence.entities.TestEntity;
import org.gem.persistence.service.api.CommonPersistenceRemote;

public class MockTestEntityPeristenceService  extends AbstractPersistenceService<TestEntity, Integer>implements CommonPersistenceRemote<TestEntity, Integer> {

	public static MockTestEntityPeristenceService getService(){
		return new MockTestEntityPeristenceService();
	}
	
	public Collection<TestEntity> findAll() {
		return findAll(TestEntity.class);
	
}
	public void printAllData(){
		printAllData(TestEntity.class);
	}

	public TestEntity find(Class<TestEntity> te, Integer key) {
		return getEntityManager().find(te, key);
	}
}
