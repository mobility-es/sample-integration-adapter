package com.appearnetworks.aiq.ia.model.mobile;

import com.appearnetworks.aiq.integrationframework.integration.BusinessDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * This object represent train data and acts as the data transfer object in the system. Jackson is used to convert this
 * object to JSON and vice versa.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrainDamageReport extends BusinessDocument {
    public static final String DOC_TYPE = "TD.DamageReport";

    private String trainId;
    private String description;
    private String reportedBy;
    private Date creationDateTime;

    /**
     * Needed for Jackson de-serialization
     */
    public TrainDamageReport() {}

    public TrainDamageReport(String _id,
                             long _rev,
                             String trainId,
                             String description,
                             String reportedBy,
                             Date creationDateTime) {

        super(_id, DOC_TYPE, _rev);
        this.trainId = trainId;
        this.description = description;
        this.reportedBy = reportedBy;
        this.creationDateTime = creationDateTime;
    }

    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
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
