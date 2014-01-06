package org.gem.event;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.gem.event.Event;
import org.gem.event.utils.CommandLineUtility;

class MeetingManagerTest {
    public static void main(final String args[]) {
        testCommandLineUtility(args);
        testCreateEvent();
        // testCreateSchedule();


    }


    public static void testCreateEvent() {
        final Event m = new Event("TestName", "TestSubject",
                "Test Description Test Description Test DescriptionTest DescriptionTest DescriptionTest Description Test Descriptionv");
        System.out.println(m);
    }


    public static void testCreateSchedule() {

        final TreeMap<Integer, Event> events = new TreeMap<Integer, Event>();
        for (int i = 0; i < 500; i++) {
            final Event m = new Event("TestName_" + i, "TestSubject",
                    "Test Description Test Description Test DescriptionTest DescriptionTest DescriptionTest Description Test Descriptionv");
            events.put(Integer.valueOf(i), m);
        }

        for (final Entry<Integer, Event> e : events.entrySet()) {
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            System.out.println();
        }
    }


    public static void testCommandLineUtility(final String args[]) {
        CommandLineUtility.showArgs(args);
        CommandLineUtility.showSystemProperties();
        final String prop = CommandLineUtility.getProperty("user.home");
        System.out.println("----------------[" + prop + "]----------------");
    }


}