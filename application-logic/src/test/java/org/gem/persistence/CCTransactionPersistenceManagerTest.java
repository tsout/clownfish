package org.gem.persistence;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.gem.persistence.entities.Category;
import org.gem.utils.EntityManagerUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CCTransactionPersistenceManagerTest {

	Logger logger = Logger.getLogger(CCTransactionPersistenceManagerTest.class.getName());
	CCTransactionPersistanceManager uut = null;

	@Before
	public void setUp() throws Exception {

		uut = new CCTransactionPersistanceManager();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {

		String tableNames = "Transactions,Users,UserProfile,Statements,Categories";

		List<String> tables = new ArrayList<String>();
		tables.addAll(Arrays.asList(tableNames.split(",")));
		System.out.println(tables);
		
//		uut.executeQuery("insert into categories (categoryname,categorydescription ) values('that','stuuff')");
		Category c  = new Category(); 
		c.setCategoryName("stuff");
		c.setCategoryDescription("this is a description of stuff");
		EntityManager em = EntityManagerUtil.getEntityManager();
		em.persist(c);
		
		for (String t : tables) {

			System.out.println("Table<" + t + ">");
			System.out.println("------------");
		}
		
		Query q = em.createNativeQuery("select * from Categories");
		List <Category> categories =  (List<Category>) q.getResultList();
		logger.info(Integer.toString(categories.size()));
		
		//uut.executeQuery("delete from categories where categoryname='that'");
		
		//TODO: create DAO's for expensedb, create CSV importer, switch out CSV db for java db

	}

}
