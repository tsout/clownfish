package org.gem.supply;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManagedItem {
	private UUID uuid;
	private String mmr;
	private Integer allowedQuantity;
	private List<StowedItem> stowedItems;

	ManagedItem() {
		setUuid(UUID.randomUUID());
		stowedItems = new ArrayList<StowedItem>();
	}

	public String getMmr() {
		return mmr;
	}

	public void setMmr(String mmr) {
		this.mmr = mmr;
	}

	public Integer getAllowedQuantity() {
		return allowedQuantity;
	}

	public void setAllowedQuantity(Integer allowedQuantity) {
		this.allowedQuantity = allowedQuantity;
	}

	@Override
	public String toString() {
		return "ManagedItem [mmr=" + mmr + ", allowedQuantity="
				+ allowedQuantity + "]";
	}

	public boolean addStowedItem(StowedItem si) {
		if (isValid(si)) {
			si.setParentManagedItemUuid(this.getUuid());
			stowedItems.add(si);

			return true;
		}
		return false;

	}

	private boolean isValid(StowedItem si) {
		return si != null;
	}

	public List<StowedItem> getStowedItems() {
		return stowedItems;
	}

	public void setStowedItems(List<StowedItem> stowedItems) {
		this.stowedItems = stowedItems;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagedItem other = (ManagedItem) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}

}
