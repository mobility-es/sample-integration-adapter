package com.appearnetworks.aiq.ia.model.mobile;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDamageReport extends AIQDocument {

    public static final String DOC_TYPE = "TD.TrainDamageReport";
    public static final String DOC_ID_PREFIX = "_TrainDamageReport_";

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

    public TrainDamageReport(String _id,
                             long _rev,
                             String trainId,
                             String level1,
                             String level2,
                             String level3,
                             String trainPart,
                             String damageCode,
                             String heading,
                             String damageText,
                             String reportedBy,
                             Date damageDateTime,
                             String damageCause,
                             String operation) {

        super(_id, _rev, DOC_TYPE);
        this.trainId = trainId;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.trainPart = trainPart;
        this.damageCode = damageCode;
        this.heading = heading;
        this.damageText = damageText;
        this.reportedBy = reportedBy;
        this.damageDateTime = damageDateTime;
        this.damageCause = damageCause;
        this.operation = operation;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getLevel1() {
        return level1;
    }

    public String getLevel2() {
        return level2;
    }

    public String getLevel3() {
        return level3;
    }

    public String getDamageCode() {
        return damageCode;
    }

    public String getTrainPart() {
        return trainPart;
    }

    public String getHeading() {
        return heading;
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

    public String getOperation() {
        return operation;
    }

    public String getDamageCause() {
        return damageCause;
    }
}
