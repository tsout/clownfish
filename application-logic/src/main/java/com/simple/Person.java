package com.simple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Person {
    private String name;
    private UUID uuid;
    Map<String, String> phoneNumbers;
    Map<String, String> emailAddresses;
    Map<String, Place> places;


    Person() {
        phoneNumbers = new HashMap<String, String>();
    }


    public void addPhoneNumber(final String key, final String number) {
        if (key != null && number != null) {
            phoneNumbers.put(key, number);
        }
    }


    public UUID getUuid() {
        return uuid;
    }


    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }

}
