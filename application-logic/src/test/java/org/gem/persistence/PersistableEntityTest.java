package org.gem.persistence;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.UUID;

import org.gem.supply.Inventory;
import org.gem.utils.BusinessKey;
import org.junit.Before;
import org.junit.Test;

public class PersistableEntityTest {
	private static final UUID ORG_UUID = UUID.randomUUID();
	private static final String MATERIAL_TYPE = "TOA";
	private static BusinessKey validBusinessKey = null; 
	
	public void intializeData() throws InstantiationException{
		LinkedHashMap<String,String> map = new LinkedHashMap<String,String>();
		map.put("orgUuid", ORG_UUID.toString());
		map.put("materialType", MATERIAL_TYPE);
		validBusinessKey = new  BusinessKey (map);
		
	}

	@Before
	public void setup() throws InstantiationException{
		intializeData(); 
	}
	@Test
	public void testEntityFactory() throws InstantiationException, IllegalAccessException{
		EntityFactory<Inventory> ef = new EntityFactory<Inventory>();
		assertNotNull(ef.create(Inventory.class,validBusinessKey).getBusinessKey());
	}

	@Test
	public void testInventoryWithValidBusinessKey() throws Exception {
		Inventory inv = new Inventory(validBusinessKey);
		assertEquals(UUID.fromString(inv.getManagingOrganization()), ORG_UUID);
		assertEquals(inv.getMaterialType(),MATERIAL_TYPE);
		
	}
	
	@Test
	public void testHashcode() throws Exception {
		fail("not yet implemented");
	}
	
	@Test
	public void testHashCodeCannotBeOveridden() throws Exception {
		Method m =PersistableEntity.class.getMethod("hashCode");
		assertTrue(Modifier.isFinal(m.getModifiers()));
	}
	
	@Test
	public void testEquals() throws Exception {
		fail("not yet implemented");
		
	}

	@Test
	public void testEqualsCannotBeOverriden() throws Exception {
		Method m =PersistableEntity.class.getMethod("equals", Object.class);
		assertTrue(Modifier.isFinal(m.getModifiers()));
	}
}
