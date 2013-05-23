package com.appearnetworks.aiq.ia.model.mobile;

import java.util.Date;

/**
 *
 */
public class TrainDamageReport extends AIQDocument {

    public static final String DOC_TYPE = "TD.TrainDamageReport";
    public static final String DOC_ID_PREFIX = "_TrainDamageReport_";

    private String trainId;
    private String description;
    private String reportedBy;
    private Date creationDateTime;

    public TrainDamageReport() {
    }

    public TrainDamageReport(String _id,
                             long _rev,
                             String trainId,
                             String description,
                             String reportedBy,
                             Date creationDateTime) {

        super(_id, _rev, DOC_TYPE);
        this.trainId = trainId;
        this.description = description;
        this.reportedBy = reportedBy;
        this.creationDateTime = creationDateTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getDescription() {
        return description;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }
}
