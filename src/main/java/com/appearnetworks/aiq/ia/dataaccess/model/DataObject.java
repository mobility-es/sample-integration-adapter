package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.UUID;


public class DataObject {
    private final String id;
    public DataObject() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
