package org.gem.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class EntityManagerUtil {
	private static final EntityManagerFactory emf;
	private static EntityManager entityManager;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("ClownFishDS");

		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

}
