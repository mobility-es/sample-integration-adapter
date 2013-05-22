package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainPartDO {
    private String name;
    private String value;

    public TrainPartDO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
