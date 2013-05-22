package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainTypeDO extends DataObject {
    private String name;
    private List<DamageCodeDO> damageCodes;
    private List<TrainPartDO> trainParts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DamageCodeDO> getDamageCodes() {
        return damageCodes;
    }

    public void setDamageCodes(List<DamageCodeDO> damageCodes) {
        this.damageCodes = damageCodes;
    }

    public List<TrainPartDO> getTrainParts() {
        return trainParts;
    }

    public void setTrainParts(List<TrainPartDO> trainParts) {
        this.trainParts = trainParts;
    }
}
