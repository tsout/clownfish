package org.gem.persistence;

import org.gem.utils.BusinessKey;

public class EntityFactory<E extends PersistableEntity> {
	E entity;

	public E create(Class<E> clazz, BusinessKey businessKey)
			throws InstantiationException, IllegalAccessException {
		E tmpEntity = clazz.newInstance();
		tmpEntity.setBusinessKey(businessKey);
		return tmpEntity;
	}

}
