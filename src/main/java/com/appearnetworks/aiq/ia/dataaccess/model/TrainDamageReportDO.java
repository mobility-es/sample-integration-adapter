package com.appearnetworks.aiq.ia.dataaccess.model;

import com.appearnetworks.aiq.ia.rest.model.Train;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDamageReportDO extends DataObject {
    private TrainDO train;
    private String level1;
    private String level2;
    private String level3;
    private String damageCode;
    private String trainPart;
    private String heading;
    private String damageText;
    private String reportedBy;
    private Date damageDateTime;
    private String damageCause;
    private String operation;

    public TrainDO getTrain() {
        return train;
    }

    public void setTrain(TrainDO train) {
        this.train = train;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getDamageCode() {
        return damageCode;
    }

    public void setDamageCode(String damageCode) {
        this.damageCode = damageCode;
    }

    public String getTrainPart() {
        return trainPart;
    }

    public void setTrainPart(String trainPart) {
        this.trainPart = trainPart;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDamageText() {
        return damageText;
    }

    public void setDamageText(String damageText) {
        this.damageText = damageText;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Date getDamageDateTime() {
        return damageDateTime;
    }

    public void setDamageDateTime(Date damageDateTime) {
        this.damageDateTime = damageDateTime;
    }

    public String getDamageCause() {
        return damageCause;
    }

    public void setDamageCause(String damageCause) {
        this.damageCause = damageCause;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
