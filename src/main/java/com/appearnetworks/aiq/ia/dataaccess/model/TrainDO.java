package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * Data object for the train.
 */
public class TrainDO extends DataObject {
    private Long rev;
    private Long number;


    /**
     * This constructor is used to create a train data object.
     * The revision is required by the integration protocol and is required field for all business documents.
     * @param number the train number.
     */
    public TrainDO(Long number) {
        this.rev = 1L;
        this.number = number;
    }

    public Long getNumber() {
        return number;
    }

    public Long getRev() {
        return rev;
    }
}
