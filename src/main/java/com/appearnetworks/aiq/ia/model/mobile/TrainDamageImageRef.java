package com.appearnetworks.aiq.ia.model.mobile;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TrainDamageImageRef extends AIQDocument {
    public static final String DOC_TYPE = "TD.TrainDamageImage";

    private String damageId;
    private Date creationDate;

    /*
     * Needed for Jackson deserialization.
     */
    public TrainDamageImageRef() { }

    public TrainDamageImageRef(String _id, long _rev, String damageId, Date creationDate) {
        super(_id, _rev, DOC_TYPE);
        this.damageId = damageId;
        this.creationDate = creationDate;
    }


    public String getDamageId() {
        return damageId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

}
