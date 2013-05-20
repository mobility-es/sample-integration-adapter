package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * Data object for the train.
 *
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDO extends DataObject {
    private Long rev;
    private Long number;
    private TrainTypeDO trainTypeDO;

    public TrainDO(Long number, TrainTypeDO trainTypeDO) {
        this.rev = 1L;
        this.number = number;
        this.trainTypeDO = trainTypeDO;
    }

    public Long getNumber() {
        return number;
    }

    public TrainTypeDO getTrainTypeDO() {
        return trainTypeDO;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }
}
