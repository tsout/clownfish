package org.gem.supply;

import java.util.UUID;

import org.gem.persistence.PersistableEntity;
import org.gem.utils.BusinessKey;

public class BaseInventory extends PersistableEntity {
	private String description;
	private String managingOrganization;
	private String benefittingOrganization;
	private String materialType;
	private UUID uuid; 
	
	BaseInventory(){
		uuid = UUID.randomUUID();
		description="default description";
		managingOrganization= "no managing org";
		benefittingOrganization = "no benefitting org";
		materialType ="n/a";
	}
	
	public BaseInventory(BusinessKey bk) {
		super(bk);
		String managingOrganization = bk.getBusinessKeyValue("orgUuid");
		String materialType = bk.getBusinessKeyValue("materialType");
		setMaterialType(materialType);
		setManagingOrganization(managingOrganization);
		setMaterialType(materialType);

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManagingOrganization() {
		return managingOrganization;
	}

	public void setManagingOrganization(String managingOrganization) {
		this.managingOrganization = managingOrganization;
	}

	public String getBenefittingOrganization() {
		return benefittingOrganization;
	}

	public void setBenefittingOrganization(String benefittingOrganization) {
		this.benefittingOrganization = benefittingOrganization;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

}
