package com.appearnetworks.aiq.ia.rest.model;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class Train {
    private final String id;
    private final Long number;
    private final String trainType;

    public Train(String id, Long number, String trainType) {
        this.id = id;
        this.number = number;
        this.trainType = trainType;
    }

    public String getId() {
        return id;
    }


    public Long getNumber() {
        return number;
    }

    public String getTrainType() {
        return trainType;
    }
}
