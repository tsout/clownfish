package org.gem;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.javadocmd.simplelatlng.LatLng;

public class Place {

    private final UUID uuid;
    private String streetAddress1;
    private String streetAddress2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String description;
    private LatLng location;
    private String locale;
    private Map<String, URL> urlDescriptions;


    Place() {
        uuid = UUID.randomUUID();
        urlDescriptions = new HashMap<String, URL>();
    }


    public String getStreetAddress1() {
        return streetAddress1;
    }


    public void setStreetAddress1(final String streetAddress1) {
        this.streetAddress1 = streetAddress1;
    }


    public String getStreetAddress2() {
        return streetAddress2;
    }


    public void setStreetAddress2(final String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }


    public String getCity() {
        return city;
    }


    public void setCity(final String city) {
        this.city = city;
    }


    public String getState() {
        return state;
    }


    public void setState(final String state) {
        this.state = state;
    }


    public String getZip() {
        return zipCode;
    }


    public void setZip(final String zip) {
        this.zipCode = zip;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(final String description) {
        this.description = description;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(final String country) {
        this.country = country;
    }


    public String getZipCode() {
        return zipCode;
    }


    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }


    public String getLocale() {
        return locale;
    }


    public void setLocale(final String locale) {
        this.locale = locale;
    }


    public LatLng getLocation() {
        return location;
    }


    public void setLocation(final LatLng location) {
        this.location = location;
    }


    public Map<String, URL> getUrlDescriptions() {
        return urlDescriptions;
    }


    public void setUrlDescriptions(final Map<String, URL> urlDescriptions) {
        this.urlDescriptions = urlDescriptions;
    }


}
