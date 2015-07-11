package org.gem.persistence;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;

public interface DAO <I> extends Serializable {
	public I getId();
}
