package org.gem.supply;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Kit extends BaseInventory implements ManageableInventory {

	private String kitIdentifier;
	private List<StowedItem> stowedItems;
	private UUID warehouseStock;

	public Kit() {
		super();
		stowedItems = new ArrayList<StowedItem>();
	}

	public Kit(Inventory inv) {
		super();
		stowedItems = new ArrayList<StowedItem>();
		warehouseStock = inv.getUuid();
	}

	public List<ManagedItem> getManagedItems() {
		// TODO Auto-generated method stub
		for (StowedItem si : stowedItems) {
			si.getParentManagedItem();
		}
		return null;
	}

	public Integer numberOfManagedItems() {
		return null;
	}

	public String getKitIdentifier() {
		return kitIdentifier;
	}

	public void setKitIdentifier(String kitIdentifier) {
		this.kitIdentifier = kitIdentifier;
	}

	public List<StowedItem> getStowedItems() {
		return stowedItems;
	}

	public void setStowedItems(List<StowedItem> stowedItems) {
		this.stowedItems = stowedItems;
	}

	public boolean addManagedItem(ManagedItem mi) {
		return false;
	}

	public boolean hasManagedItem(ManagedItem mi) {
		return false;
	}

	public UUID getWarehouseStock() {
		return warehouseStock;
	}

	public void setWarehouseStock(UUID warehouseStock) {
		this.warehouseStock = warehouseStock;
	}

	@Override
	public String toString() {
		return "Kit [kitIdentifier=" + kitIdentifier + ", stowedItems="
				+ stowedItems + ", warehouseStock=" + warehouseStock + "]";
	}
	
}
