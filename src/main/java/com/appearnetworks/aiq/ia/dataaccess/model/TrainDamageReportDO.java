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

    /**
     * This constructor is used to create a new <code>TrainDamageReport</code> data object.
     * The revision is required by the platform integration protocol and is required field for all business documents.
     * The revision for newly create <code>TrainDamageReport</code> should be 1.
     */
    public TrainDamageReportDO() {
        this.rev = 1L;
    }

    public Long getRev() {
        return rev;
    }

    /**
     * To set updated revision value for a <code>TrainDamageReport</code> in case it is updated.
     * The setter is unused in current implementation since there is no use case that required updateing the revision.
     * @param rev the updated revision
     */
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
