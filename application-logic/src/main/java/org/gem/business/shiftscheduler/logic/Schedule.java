package org.gem.business.shiftscheduler.logic;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resources;

import org.gem.business.shiftscheduler.model.Resource;
import org.gem.business.shiftscheduler.model.BlackoutDate;
import org.gem.business.shiftscheduler.model.Shift;
import org.gem.business.shiftscheduler.model.ShiftAssignment;
import org.gem.utils.AbstractPojo;
import org.gem.utils.PojoUtils;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Schedule extends AbstractPojo {
	@JsonIgnore
	Logger logger = Logger.getLogger(Schedule.class);

	private Set<Resource> importedResources;
	private Set<BlackoutDate> importedBlackoutDates;
	private Set<ShiftAssignment> importedShiftAssignments;
	private Set<Shift> importedShifts;
	private Set<ShiftAssignment> processedShiftAssignments;

	Schedule() {
			importedResources = new HashSet<Resource>();
			importedBlackoutDates = new HashSet<BlackoutDate>();
			importedShiftAssignments = new HashSet<ShiftAssignment>();
			importedShifts = new HashSet<Shift>();
	}

	public Set<Shift> importShiftData(Set<Shift> shifts) {
		importedShifts.addAll(shifts);
		return importedShifts;
	};

	public Set<ShiftAssignment> importShiftAssignments() {
		return importedShiftAssignments;
	};

	public Set<BlackoutDate> importShiftResourceBlackouts(Set<BlackoutDate> blackouts) {
		importedBlackoutDates.addAll(blackouts);
		return importedBlackoutDates;
	};

	public Set<ShiftAssignment> generateSchedules(Set<Resource> resources,
			Set<Shift> shifts) {
		Set<ShiftAssignment> assignments = new HashSet<ShiftAssignment>();
		logger.info("test");
		for (Shift s : shifts) {
			for (Resource r : resources) {
				assignResource(r, s, assignments);
			}
		}

		logger.info("Resources \n" + resources);
		logger.debug("Shifts \n" + shifts);
		logger.info("Assignments \n" + assignments);
		processedShiftAssignments = assignments;
		return processedShiftAssignments;
	};

	private void assignResource(Resource r, Shift s,
			Set<ShiftAssignment> assignments) {
		if (!shiftConflictsWithBlackout(r, s)
				&& !r.resourceAssignmentFrequencyExceeded()
				&& resourceIsQualified(r, s) 
				&& !s.isCovered()) {
			// assign a shift to a resource
			ShiftAssignment shiftAssignment = new ShiftAssignment(r, s);
			assignments.add(shiftAssignment);
			r.getAssignments().add(s);
			if (isRequiredShiftCoverageSatisfied(s, assignments)) {
				s.setCovered(true);
			}
		}
	}

	private boolean isRequiredShiftCoverageSatisfied(Shift s,
			Set<ShiftAssignment> assignments) {
		int coveredQty = 0;
		for (ShiftAssignment sa : assignments) {
			if (sa.getShift().getShiftId().equals(s.getShiftId())) {
				coveredQty++;
			}
		}
		return coveredQty >= s.getQtyResourcesRequired();
	}

	private boolean resourceIsQualified(Resource r, Shift s) {

		boolean resourcesIsQualified = r.getQualifiedForJobTypes().contains(
				s.getJobType());
		logger.debug(r.getPerson().getFirstName() + " is qualified " + resourcesIsQualified);
		return resourcesIsQualified;
	}

	private boolean shiftConflictsWithBlackout(Resource r, Shift s) {
		if (!hasBlackoutDates(r)) {
			return false;
		}
		for (BlackoutDate blackout : r.getBlackOutDates()) {
			if (s.getTimePeriod().conflictsWith(blackout.getTimePeriod())) {
				logger.info("\t" + r.getPerson().getFirstName() + "'s blackout date "
						+ blackout.getTimePeriod() + "  conflicts with " + s);
				return true;
			}
			logger.debug("\t" + r.getPerson().getFirstName()
					+ " has no blackout date conflicts "
					+ blackout.getTimePeriod() + " with " + s);
		}
		return false;
	}

	private boolean hasBlackoutDates(Resource r) {
		return r.getBlackOutDates().size() > 0;
	}

	public Set<Resource> importResources(Set<Resource> rSet) { 
		importedResources.addAll(rSet) ;
		return importedResources;
	}

	public Set<ShiftAssignment> getImportedShiftAssignments(){
		return this.importedShiftAssignments;
	}
	public Set<ShiftAssignment> getShiftAssignments() {
		return this.processedShiftAssignments;
	};
	
	@JsonDeserialize(as= HashSet.class)
	public void setShiftAssignments(Set<ShiftAssignment> assignments){
		this.importedShiftAssignments = assignments;
		
	}

	public Set<Resource> getImportedResources() {
		return importedResources;
	}

	public void setImportedResources(Set<Resource> importedResources) {
		this.importedResources = importedResources;
	}

	public Set<BlackoutDate> getImportedBlackoutDates() {
		return importedBlackoutDates;
	}

	public void setImportedBlackoutDates(Set<BlackoutDate> importedBlackoutDates) {
		this.importedBlackoutDates = importedBlackoutDates;
	}

	public Set<Shift> getImportedShifts() {
		return importedShifts;
	}

	public void setImportedShifts(Set<Shift> importedShifts) {
		this.importedShifts = importedShifts;
	}

	public void setImportedShiftAssignments(
			Set<ShiftAssignment> importedShiftAssignments) {
		this.importedShiftAssignments = importedShiftAssignments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((importedBlackoutDates == null) ? 0 : importedBlackoutDates
						.hashCode());
		result = prime
				* result
				+ ((importedResources == null) ? 0 : importedResources
						.hashCode());
		result = prime
				* result
				+ ((importedShiftAssignments == null) ? 0
						: importedShiftAssignments.hashCode());
		result = prime * result
				+ ((importedShifts == null) ? 0 : importedShifts.hashCode());
		result = prime
				* result
				+ ((processedShiftAssignments == null) ? 0
						: processedShiftAssignments.hashCode());
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
		Schedule other = (Schedule) obj;
		if (importedBlackoutDates == null) {
			if (other.importedBlackoutDates != null)
				return false;
		} else if (!importedBlackoutDates.equals(other.importedBlackoutDates))
			return false;
		if (importedResources == null) {
			if (other.importedResources != null)
				return false;
		} else if (!importedResources.equals(other.importedResources))
			return false;
		if (importedShiftAssignments == null) {
			if (other.importedShiftAssignments != null)
				return false;
		} else if (!importedShiftAssignments
				.equals(other.importedShiftAssignments))
			return false;
		if (importedShifts == null) {
			if (other.importedShifts != null)
				return false;
		} else if (!importedShifts.equals(other.importedShifts))
			return false;
		if (processedShiftAssignments == null) {
			if (other.processedShiftAssignments != null)
				return false;
		} else if (!processedShiftAssignments
				.equals(other.processedShiftAssignments))
			return false;
		return true;
	}
	
	

}
