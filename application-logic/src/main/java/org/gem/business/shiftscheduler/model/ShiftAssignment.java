package org.gem.business.shiftscheduler.model;


import org.gem.utils.AbstractPojo;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value="ShiftAssignment")
public class ShiftAssignment extends AbstractPojo {
	private Resource resource;
	private Shift shift;

	ShiftAssignment() {

	}

	public ShiftAssignment(Resource resource, Shift shift) {
		initialize();
		if (resource != null && shift != null) {
			this.setResource(resource);
			this.setShift(shift);
		}
	}

	private final void initialize() {

	};

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((resource == null) ? 0 : resource.hashCode());
		result = prime * result + ((shift == null) ? 0 : shift.hashCode());
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
		ShiftAssignment other = (ShiftAssignment) obj;
		if (resource == null) {
			if (other.resource != null)
				return false;
		} else if (!resource.equals(other.resource))
			return false;
		if (shift == null) {
			if (other.shift != null)
				return false;
		} else if (!shift.equals(other.shift))
			return false;
		return true;
	}
	
	

}
