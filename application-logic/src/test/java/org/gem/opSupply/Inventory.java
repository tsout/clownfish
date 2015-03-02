package org.gem.opSupply;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends BaseInventory implements ManageableInventory{

	public Integer inventoryId; 
	List<ManagedItem> managedItems;
	
	Inventory(){
		managedItems= new ArrayList<ManagedItem>(); 
	}
	
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	public List<ManagedItem> getManagedItems() {
		return managedItems;
	}
	public void setManagedItems(List<ManagedItem> managedItems) {
		this.managedItems = managedItems;
	}
	public Integer numberOfManagedItems() {
		return managedItems.size();
	}

	public boolean addManagedItem(ManagedItem mi) {

		if(isValid(mi)){
			managedItems.add(mi);
			return true; 
		}
		return false;
	}

	private boolean isValid(ManagedItem mi) {
		return mi!=null;
	}


	public boolean hasManagedItem(ManagedItem mi) {
		
		return managedItems.contains(mi);
	}

	@Override
	public String toString() {
		return "Inventory [inventoryId=" + inventoryId + ", managedItems="
				+ managedItems + "]";
	} 
}
