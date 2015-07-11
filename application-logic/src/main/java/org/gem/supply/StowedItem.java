package org.gem.supply;

import java.util.UUID;


public class StowedItem {
	
	private UUID uuid;  
	private Integer onHandQuantity;
	private String conditionCode; 
	private String location;
	private UUID parentManagedItemUuid; 
	
	StowedItem(){
		setUuid(UUID.randomUUID()); 
		onHandQuantity = -1; 
		conditionCode="F";
		location="nowhere";
	}
	public Integer getOnHandQuantity() {
		return onHandQuantity;
	}
	public void setOnHandQuantity(Integer onHandQuantity) {
		this.onHandQuantity = onHandQuantity;
	}
	public String getConditionCode() {
		return conditionCode;
	}
	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "StowedItem [onHandQuantity=" + onHandQuantity
				+ ", conditionCode=" + conditionCode + ", location=" + location
				+ "]";
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public ManagedItem getParentManagedItem() {
		
		return MockInventoryService.getManagedItem(parentManagedItemUuid);
		
	}
	public UUID getParentManagedItemUuid() {
		return parentManagedItemUuid;
	}
	public void setParentManagedItemUuid(UUID parentManagedItemUuid) {
		this.parentManagedItemUuid = parentManagedItemUuid;
	} 
	

}
