package org.gem.business.shiftscheduler.model;

import java.util.Formatter;

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
	public String toString() {

		Formatter f = new Formatter();
		try {
			f.format("Shift Assignment: %10s %10s,%30s\t%s\n",
					resource.getFirstName(), resource.getLastName(),
					shift.getDescription(), shift.getStartDateTime());
			return f.toString();
		} finally {
			f.close();
		}

		// return "ShiftAssignment [resource=" + resource.getFirstName() +
		// ", shift=" + shift.getDescription() +" "+shift.getStartDateTime()
		// + "]";

	}

}
