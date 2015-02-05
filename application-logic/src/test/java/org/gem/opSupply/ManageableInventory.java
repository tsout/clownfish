package org.gem.opSupply;

import java.util.List;

public interface ManageableInventory {
	
	public List<ManagedItem> getManagedItems();
//	public Integer getOnHandQuantityOfManagedItem(ManagedItem mi); 
	public Integer numberOfManagedItems(); 
	public boolean addManagedItem(ManagedItem mi); 
	public boolean hasManagedItem(ManagedItem mi); 
}
