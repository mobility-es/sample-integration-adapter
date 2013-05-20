package com.appearnetworks.aiq.ia.rest.model;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDamageReport {

    private String id;
    private Long rev;
    private String docType = "Sample.TrainDamageReport";

    private String trainId;
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
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

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDamageCause() {
        return damageCause;
    }

    public void setDamageCause(String damageCause) {
        this.damageCause = damageCause;
    }
}
