package com.appearnetworks.aiq.ia.model.mobile;

import com.appearnetworks.aiq.integrationframework.integration.AIQDocument;

/**
 * This object represent train data and acts as the data transfer object in the system. Jackson is used to convert this
 * object to JSON and vice versa.
 */
public class Train extends AIQDocument {
    public static final String DOC_TYPE = "TD.Train";

    private long number;

    /**
     * Needed for Jackson de-serialization
     */
    public Train() {}

    public Train(String _id, long _rev, long number) {
        super(_id, _rev, DOC_TYPE);
        this.number = number;
    }

    public long getNumber() {
        return number;
    }
}
