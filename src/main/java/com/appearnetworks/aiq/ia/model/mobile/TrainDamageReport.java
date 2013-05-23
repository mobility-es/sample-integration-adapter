package com.appearnetworks.aiq.ia.model.mobile;

import java.util.Date;

/**
 *
 */
public class TrainDamageReport extends AIQDocument {

    public static final String DOC_TYPE = "TD.TrainDamageReport";
    public static final String DOC_ID_PREFIX = "_TrainDamageReport_";

    private String trainId;
    private String damageText;
    private String reportedBy;
    private Date damageDateTime;

    public TrainDamageReport() {
    }

    public TrainDamageReport(String _id,
                             long _rev,
                             String trainId,
                             String damageText,
                             String reportedBy,
                             Date damageDateTime) {

        super(_id, _rev, DOC_TYPE);
        this.trainId = trainId;
        this.damageText = damageText;
        this.reportedBy = reportedBy;
        this.damageDateTime = damageDateTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getDamageText() {
        return damageText;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public Date getDamageDateTime() {
        return damageDateTime;
    }
}
