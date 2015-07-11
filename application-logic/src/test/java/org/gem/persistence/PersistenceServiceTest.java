package org.gem.persistence;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.gem.persistence.entities.TestEntity;
import org.gem.persistence.service.impl.MockTestEntityPeristenceService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistenceServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void test() {
		MockTestEntityPeristenceService svc = MockTestEntityPeristenceService.getService();
		TestEntity te = new TestEntity("LKJLDSJF",42);
		
		EntityManager em = svc.getEntityManager();		
		
		assertTrue(em instanceof EntityManager);
		assertTrue(em !=null);
		assertTrue(((EntityManager)em).isOpen());

		svc.save(te);
		Collection<TestEntity> c =svc.findAll();
		assertTrue(c.size()>0);
		
		svc.printAllData();
		//te.setId(234);  //can't alter the identifier of a persisted object
		te.setHello("yo mama2134");
		svc.save(te);
		
		svc.printAllData();
		
		
	}

	

}
