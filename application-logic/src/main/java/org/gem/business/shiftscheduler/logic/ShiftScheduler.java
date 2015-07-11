package org.gem.business.shiftscheduler.logic;

import java.util.HashSet;
import java.util.Set;

import org.gem.business.shiftscheduler.model.Resource;
import org.gem.business.shiftscheduler.model.BlackoutDate;
import org.gem.business.shiftscheduler.model.Shift;
import org.gem.business.shiftscheduler.model.ShiftAssignment;
import org.jboss.logging.Logger;

public class ShiftScheduler {
	Logger logger = Logger.getLogger(ShiftScheduler.class);

	private Set<Resource> importedResources;
	private Set<BlackoutDate> importedBlackoutDates;
	private Set<ShiftAssignment> importedShiftAssignments;
	private Set<Shift> importedShifts;
	private Set<ShiftAssignment> processedShiftAssignments;

	ShiftScheduler() {
		if (importedResources == null) {
			importedResources = new HashSet<Resource>();
		}
		if (importedBlackoutDates == null) {
			importedBlackoutDates = new HashSet<BlackoutDate>();
		}

		if (importedShiftAssignments == null) {
			importedShiftAssignments = new HashSet<ShiftAssignment>();
		}

		if (importedShifts == null) {
			importedShifts = new HashSet<Shift>();
		}
	}

	public Set<Shift> importShiftData() {
		return importedShifts;
	};

	public Set<ShiftAssignment> importShiftAssignments() {
		return importedShiftAssignments;
	};

	public Set<BlackoutDate> importShiftResourceBlackouts() {
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

		logger.debug("Resources \n" + resources);
		logger.debug("Shifts \n" + shifts);
		logger.info("Assignments \n" + assignments);
		return assignments;
	};

	private void assignResource(Resource r, Shift s,
			Set<ShiftAssignment> assignments) {
		if (!shiftConflictsWithBlackout(r, s)
				&& r.resourceAssignmentFrequencyNotExceeded()
				&& resourceIsQualified(r, s) && !s.isCovered()) {
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
		logger.debug(r.getFirstName() + " is qualified " + resourcesIsQualified);
		return resourcesIsQualified;
	}

	private boolean shiftConflictsWithBlackout(Resource r, Shift s) {
		if (!hasBlackoutDates(r)) {
			return false;
		}
		for (BlackoutDate blackout : r.getBlackOutDates()) {
			if (s.getTimePeriod().conflictsWith(blackout.getTimePeriod())) {
				logger.info("\t" + r.getFirstName() + "'s blackout date "
						+ blackout.getTimePeriod() + "  conflicts with " + s);
				return true;
			}
			logger.debug("\t" + r.getFirstName()
					+ " has no blackout date conflicts "
					+ blackout.getTimePeriod() + " with " + s);
		}
		return false;
	}

	private boolean hasBlackoutDates(Resource r) {
		return r.getBlackOutDates().size() > 0;
	}

	public Set<ShiftAssignment> exportShiftSchedule() {
		return processedShiftAssignments;
	}

	public Set<Resource> importResources() {
		return importedResources;
	};

}
