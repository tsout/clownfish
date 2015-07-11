package org.gem.supply;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.gem.persistence.PersistableEntity;
import org.gem.utils.BusinessKey;

public class Inventory extends BaseInventory implements ManageableInventory{

	private UUID organizationUuid; 
	private String materialType; 
	
	private UUID inventoryUuid;
	public Integer inventoryId; 
	List<ManagedItem> managedItems;
	
	public Inventory(BusinessKey bk){
		super(bk);
			
		
	}
	public Inventory(){
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
