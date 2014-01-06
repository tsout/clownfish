package org.gem.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


public class Event implements Comparable<Event> {
    enum EventStatus {
        TENTATIVE, SCHEDULED, UNDERWAY, COMPLETE, RELEASED, DELAYED, PASTDUE, OVERTIME, UNSCHEDULED
    };


    private Calendar startDate;
    private Calendar endDate;
    final private boolean repeated = false;
    final private long durationBetweenRepeatedEvents;
    final private List<Person> requiredPeople;
    final private List<Person> optionalPeople;
    final private Place place;
    private final List<Thing> things;
    private EventStatus status;
    final private String eventName;
    private String subject;
    private String description;
    private UUID uuid;


    public EventStatus getStatus() {
        return status;
    }


    public void setStatus(final EventStatus status) {
        this.status = status;
    }


    public String getSubject() {
        return subject;
    }


    public void setSubject(final String subject) {
        this.subject = subject;
    }


    public Calendar getStartDate() {
        return startDate;
    }


    public Calendar getEndDate() {
        return endDate;
    }


    public boolean isRepeated() {
        return repeated;
    }


    public long getDurationBetweenRepeatedEvents() {
        return durationBetweenRepeatedEvents;
    }


    public Place getPlace() {
        return place;
    }


    public String getEventName() {
        return eventName;
    }


    Event(final Calendar startDate, final Calendar endDate, final String name) throws EventException {
        this.setUuid(UUID.randomUUID());
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
        this.place = new Place();
        this.requiredPeople = new ArrayList<Person>();
        this.optionalPeople = new ArrayList<Person>();
        this.things = new ArrayList<Thing>();
        this.eventName = name;
        this.status = EventStatus.UNSCHEDULED;
        this.durationBetweenRepeatedEvents = 0;

        setDates(startDate, endDate);


    }


    private void setDates(final Calendar startDate, final Calendar endDate) throws EventException {
        if (startDate == null || endDate == null) {
            throw new EventException("start or end date is null");
        }

        if (startDate.after(endDate)) {
            throw new EventException("Invalid Start Date: start date begins after end date");
        }

        if (endDate.before(startDate)) {
            throw new EventException("Invalid End Date: end date precedes start date");
        }

        this.startDate = (Calendar) (startDate.clone());
        this.endDate = (Calendar) (endDate.clone());
    }


    public void updateEndDate(final Calendar end) throws EventException {
        if (end != null) {
            Calendar s = Calendar.getInstance();
            final Calendar e = (Calendar) end.clone();
            s = (Calendar) this.startDate.clone();
            if (e.before(s)) {
                throw new EventException("End date precedes start date");
            }
            this.endDate = e;
        }
    }


    public void updateStartDate(final Calendar start) throws EventException {
        if (start != null) {
            final Calendar s = (Calendar) start.clone();
            final Calendar e = this.getEndDate();
            if (s.after(e)) {
                throw new EventException("�start date exceeds end date");
            }
            this.startDate = s;
        }
    }


    public Event(final String name, final String subject, final String description) {
        this.startDate = Calendar.getInstance();
        this.endDate = Calendar.getInstance();
        this.place = new Place();
        this.requiredPeople = new ArrayList<Person>();
        this.optionalPeople = new ArrayList<Person>();
        this.things = new ArrayList<Thing>();
        this.eventName = name;
        this.status = EventStatus.UNSCHEDULED;
        this.durationBetweenRepeatedEvents = 0;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("\n" + this.uuid);
        sb.append("\n[" + eventName + "]\t" + subject);
        sb.append("\n");
        sb.append("[Description:]");
        sb.append("\n");
        sb.append("\t" + description + "\n");
        return sb.toString();

    }


    public int compareTo(final Event otherMeeting) {
        return getStartTime().compareTo(otherMeeting.getStartTime());
    }


    public Calendar getStartTime() {
        return (Calendar) this.getStartDate().clone();
    }


    public UUID getUuid() {
        return uuid;
    }


    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }


    public void updateStatus(final EventStatus newStatus) {
        // add some biz logic
        this.status = newStatus;
    }
}
