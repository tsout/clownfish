package org.gem.supply;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SupplyTest {

	private static final String TOA = "TOA";
	private static final String EOD_3 = "EOD 3";
	private static final String EOD_2 = "EOD 2";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		MockInventoryService service = MockInventoryService.getInstance();
		
		Inventory i1 = new Inventory();
		i1.setBenefittingOrganization(EOD_3);
		i1.setManagingOrganization(EOD_2);
		i1.setDescription("Inventory 1");
		i1.setMaterialType(TOA);
		
		Inventory i2 = new Inventory(); 
		i2.setManagingOrganization(EOD_2);
		i2.setBenefittingOrganization(EOD_2);
		i1.setDescription("Inventory 2");
		
		service.addBaseInventory(i1);
		service.addBaseInventory(i2);
		Kit k = service.createAKitFromInventory(i2);
		k.setKitIdentifier("1234");
		k.setDescription("Kit 1234");
		k.setManagingOrganization(EOD_2);
		k.setBenefittingOrganization(EOD_3);
		service.addBaseInventory(k);
		
		service.showReadiness(); 
	}

}

