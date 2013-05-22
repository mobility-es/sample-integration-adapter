package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDamageReportDO extends DataObject {
    private Long rev;
    private TrainDO train;
    private DamageCodeDO damageCode;
    private TrainPartDO trainPart;
    private String heading;
    private String damageText;
    private String reportedBy;
    private Date damageDateTime;
    private String damageCause;
    private String operation;

    public TrainDamageReportDO() {
        this.rev = 1L;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }

    public TrainDO getTrain() {
        return train;
    }

    public void setTrain(TrainDO train) {
        this.train = train;
    }

    public TrainPartDO getTrainPart() {
        return trainPart;
    }

    public void setTrainPart(TrainPartDO trainPart) {
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

    public DamageCodeDO getDamageCode() {
        return damageCode;
    }

    public void setDamageCode(DamageCodeDO damageCode) {
        this.damageCode = damageCode;
    }
}
