package com.appearnetworks.aiq.ia.model.mobile;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrainDamageImage extends AIQDocument {
    public static final String DOC_TYPE = "TKAB.TrainDamageImage";

    private String damageId;
    private long creationDate;

    /*
     * Needed for Jackson deserialization.
     */
    public TrainDamageImage() { }

    public TrainDamageImage(String _id, long _rev, String defectId, long creationDate) {
        super(_id, _rev, DOC_TYPE);
        this.damageId = damageId;
        this.creationDate = creationDate;
    }


    public String getDamageId() {
        return damageId;
    }

    public void setDamageId(String damageId) {
        this.damageId = damageId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }
}
