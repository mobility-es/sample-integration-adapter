package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.UUID;

/**
 * Base class for all the data objects. It generates a UUID for each data object which acts as a primary key.
 */
public abstract class DataObject {
    private final String id;
    public DataObject() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * The ID for the data object
     * @return UUID of the data object
     */
    public String getId() {
        return id;
    }
}
