package com.appearnetworks.aiq.ia.model.mobile;

import com.appearnetworks.aiq.ia.model.mobile.DamageCode;
import com.appearnetworks.aiq.ia.model.mobile.TrainPart;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainType extends AIQDocument {

    public static final String DOC_TYPE = "TD.TrainType";
    public static final String DOC_ID_PREFIX = "_TrainType_";

    private String name;
    private List<DamageCode> damageCodes;
    private List<TrainPart> trainParts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DamageCode> getDamageCodes() {
        return damageCodes;
    }

    public void setDamageCodes(List<DamageCode> damageCodes) {
        this.damageCodes = damageCodes;
    }

    public List<TrainPart> getTrainParts() {
        return trainParts;
    }

    public void setTrainParts(List<TrainPart> trainParts) {
        this.trainParts = trainParts;
    }
}
