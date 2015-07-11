package org.gem.supply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class MockInventoryService {

	private static MockInventoryService instance = null;
	private static Map<UUID, BaseInventory> baseInventories = null;

	private MockInventoryService() { 
		baseInventories = new HashMap<UUID,BaseInventory>();
	}

	public static final ManagedItem getManagedItem(UUID parentManagedItemUuid) {

		for (Entry<UUID, BaseInventory> bi : baseInventories.entrySet()) {
			if (bi instanceof Inventory) {
				Inventory i = (Inventory) bi;
				for (ManagedItem mi : i.getManagedItems()) {
					if (mi.getUuid().equals(parentManagedItemUuid))
						;
					return mi;
				}
			}
		}
		return null;
	}

	// candidate for a service
	public final Integer getOnHandQuantityOfManagedItemInInventory(
			Inventory inv, ManagedItem mi) {

		if (isValid(mi)
				&& isValid(inv)) {
			Integer totalQuantity = 0;

			for (StowedItem si : mi.getStowedItems()) {
				totalQuantity += si.getOnHandQuantity();
			}
			return totalQuantity;
		}
		return -1;
	}

	public final boolean isValid(Inventory inv) {
		return inv != null;
	}

	public final boolean isValid(ManagedItem mi) {
		return mi != null;
	}

	public  final Kit createAKitFromInventory(Inventory inv) {
		if (isValid(inv)) {
			return new Kit(inv);
		}
		return null;
	}

	public final boolean addBaseInventory(BaseInventory inv) {
		if (isValid(inv)) {
			baseInventories.put(inv.getUuid(), inv);
			return true;
		}
		return false;
	}

	private boolean isValid(BaseInventory inv) {
		return inv!=null;
	}

	public static MockInventoryService getInstance() {
		if (instance == null)
			return new MockInventoryService();
		return instance;
	}

	public void showReadiness() {
		for (Entry<UUID,BaseInventory> bi : baseInventories.entrySet()) {
						
			BaseInventory b = bi.getValue();
			StringBuffer sb = new StringBuffer();
			sb.append("------------Readiness Report for '"); 
			sb.append(b.getDescription());
			sb.append("' --------------");
			sb.append(b.getUuid()).append("\n");;
			sb.append(b.getMaterialType()).append("\n");
			sb.append(b.getUuid()).append("\n");;
			sb.append(b.getManagingOrganization()).append("\n");
			sb.append("Number of managed items: ").append(MockInventoryService.getManagedItemCount(b.getUuid())).append("\n");
			System.out.println(sb);
			
		}
	}

	private static Integer getManagedItemCount(UUID uuid) {

		BaseInventory bi = baseInventories.get(uuid); 
		
		if(bi instanceof Kit){
			Kit k = (Kit) bi;
			Set<ManagedItem> kitManagedItem = new HashSet<ManagedItem>();
			for(StowedItem si : k.getStowedItems())
			{
				ManagedItem mi = MockInventoryService.getManagedItem(si.getParentManagedItemUuid());
				kitManagedItem.add(mi);
			}
			return kitManagedItem.size();
		}
		else if(bi instanceof Inventory){
			Inventory i = (Inventory) bi; 
			return i.getManagedItems().size();	
		}
		else
		{
			System.err.println("Type of base inventory could not be resolved");
			return -1; 
		}
			
		
	}

}
