package com.appearnetworks.aiq.ia.model.mobile;

/**
 *
 */
public class Train extends AIQDocument {

    public static final String DOC_TYPE = "TD.Train";
    public static final String DOC_ID_PREFIX = "_Train_";

    private Long number;
    private String trainType;

    /**
     * Needed for Jackson deserialization
     */
    public Train() {}

    public Train(String _id, long _rev, Long number, String trainType) {
        super(_id, _rev, DOC_TYPE);
        this.number = number;
        this.trainType = trainType;
    }

    public Long getNumber() {
        return number;
    }

    public String getTrainType() {
        return trainType;
    }
}
