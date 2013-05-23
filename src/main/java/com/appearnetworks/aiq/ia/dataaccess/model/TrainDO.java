package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * Data object for the train.
 *
 *
 */
public class TrainDO extends DataObject {
    private Long rev;
    private Long number;


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
