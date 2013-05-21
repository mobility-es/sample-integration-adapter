package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainTypeDO extends DataObject {
    private String type;
    private List<DamageCodeDO> defectCodes;
    private List<TrainPartDO> trainParts;

    public TrainTypeDO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public List<DamageCodeDO> getDefectCodes() {
        return defectCodes;
    }

    public void setDefectCodes(List<DamageCodeDO> defectCodes) {
        this.defectCodes = defectCodes;
    }

    public List<TrainPartDO> getTrainParts() {
        return trainParts;
    }

    public void setTrainParts(List<TrainPartDO> trainParts) {
        this.trainParts = trainParts;
    }
}
