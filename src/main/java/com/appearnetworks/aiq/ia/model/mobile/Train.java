package com.appearnetworks.aiq.ia.model.mobile;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class Train {

    public static final String DOC_TYPE = "Sample.Train";
    public static final String DOC_ID_PREFIX = "_Train_";

    private String id;
    private Long rev;

    private Long number;
    private String trainType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getTrainType() {
        return trainType;
    }

    public void setTrainType(String trainType) {
        this.trainType = trainType;
    }
}
