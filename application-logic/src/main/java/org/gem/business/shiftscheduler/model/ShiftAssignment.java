package org.gem.business.shiftscheduler.model;

public class ShiftAssignment {
	private Resource resource;
	private Shift shift;

	public ShiftAssignment(Resource resource, Shift shift) {
		initialize();
		if (resource != null && shift != null) {
			this.setResource(resource);
			this.setShift(shift);
		}
	}

	private final void initialize(){
		
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
	public String toString() {
		return "ShiftAssignment [resource=" + resource.getFirstName() + ", shift=" + shift.getDescription() +" "+shift.getStartDateTime()
				+ "]";
	}

}
