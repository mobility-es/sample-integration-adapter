package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * Data object for the train.
 *
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDO {
    private String id;
    private Long number;
    private TrainTypeDO trainTypeDO;

    public TrainDO(String id, Long number, TrainTypeDO trainTypeDO) {
        this.id = id;
        this.number = number;
        this.trainTypeDO = trainTypeDO;
    }

    public String getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public TrainTypeDO getTrainTypeDO() {
        return trainTypeDO;
    }
}
