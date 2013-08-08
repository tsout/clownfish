package com.simple;

import org.junit.Before;
import org.junit.Test;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class PlaceTest {

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void printLatLngTest() {
        final Place p1 = new Place();
        final Place p2 = new Place();

        p1.setLocation(LatLng.random());
        p2.setLocation(LatLng.random());

        System.out.println(p1.getLocation());
        System.out.println(p2.getLocation());

        System.out.println(LatLngTool.distance(p1.getLocation(), p2.getLocation(), LengthUnit.MILE));
    }

}
