package org.gem.persistence;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import org.gem.persistence.entities.TestEntity;
import org.gem.persistence.service.impl.MockTestEntityPeristenceService;
import org.gem.utils.EntityManagerUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;


public class JPATest {

	private static final int TEST_RECORD_INDEX = 23;

	private static final String BLAHBLAHBLAH = "blahblahblah";

	private static Logger logger = Logger.getLogger(JPATest.class.getName());

	private static EntityManager em; 

	@BeforeClass
	public static void beforeClass() throws Exception{
		
	}
	
	@AfterClass
	public static void afterClass() throws Exception {
		
	}
	
	private static MockTestEntityPeristenceService svc= null;  
	
	@Before
	public void setup() throws Exception{
		
		 svc = MockTestEntityPeristenceService.getService(); 
		
		em = svc.getEntityManager();
		initialize();
	}

	private static void initialize() {
		generateSeedData();
		reportSeededData();
	}

	@After
	public void tearDown() throws Exception {
		em.close(); 
	}

	@Test
	public void testDBFindMethod() throws Exception {
		Collection<TestEntity> te = svc.findAll();
		assertTrue(te.size()==25);
	}
	@Test
	public void testPersistDuplicate() throws Exception {
		
		TestEntity t = new TestEntity();
		t.setHello(BLAHBLAHBLAH);
		svc.save(t);
		t.setHello(BLAHBLAHBLAH+"12321");
		svc.save(t);
		t.setHello(BLAHBLAHBLAH);
		svc.save(t);
		assertTrue(t.getHello(), t.getHello().equals(BLAHBLAHBLAH));
	}
	@Test 
	public void testMerge() {
		TestEntity t = em.find(TestEntity.class, TEST_RECORD_INDEX);
		t.setHello(BLAHBLAHBLAH);
		svc.save(t);
		
		TestEntity x = svc.find(TestEntity.class,TEST_RECORD_INDEX); 
		assertTrue("Value should be"+BLAHBLAHBLAH+" but is "+x.getHello(), x.getHello().equals(BLAHBLAHBLAH));
		
	}

	private static void generateSeedData() {
		
		
		for (int i = 0; i< 25; i ++){
			TestEntity test = new TestEntity();
			test.setHello("Hi_"+i);			
			svc.save(test);
		}
		
	}

	private static void reportSeededData() {
		TypedQuery<TestEntity> q = svc.getEntityManager().createQuery("from TestEntity", TestEntity.class);
		List<TestEntity> results =  q.getResultList();
		
		for (TestEntity t: results){
			logger.info(t.toString());
		}
	}

}
