package org.gem;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Thing {

    private UUID uuid;
    private String name;
    private String description;
    private int count;
    private Map<String, URL> urlDescriptions;
    private final Map<String, URI> imageURIs;


    Thing(final String name, final String description, final int count) {
        uuid = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.count = count;
        urlDescriptions = new HashMap<String, URL>();
        imageURIs = new HashMap<String, URI>();

    }


    public UUID getUuid() {
        return uuid;
    }


    public void setUuid(final UUID uuid) {
        this.uuid = uuid;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(final String description) {
        this.description = description;
    }


    public int getCount() {
        return count;
    }


    public void setCount(final int count) {
        this.count = count;
    }


    public String getName() {
        return name;
    }


    public void setName(final String name) {
        this.name = name;
    }


    public Map<String, URL> getUrlDescriptions() {
        return urlDescriptions;
    }


    public void setUrlDescriptions(final Map<String, URL> urlDescriptions) {
        this.urlDescriptions = urlDescriptions;
    }
}
