package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.UUID;


public class DataObject {
    private String id;
    public DataObject() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
