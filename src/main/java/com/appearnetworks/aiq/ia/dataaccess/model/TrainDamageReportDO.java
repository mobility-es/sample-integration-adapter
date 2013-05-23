package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.Date;

/**
 * Data object for train damage report
 */
public class TrainDamageReportDO extends DataObject {
    private Long rev;
    private TrainDO train;
    private String description;
    private String reportedBy;
    private Date creationDateTime;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
