package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.Date;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class TrainDamageImageDO {
    private String id;
    private long revision = 1;
    private byte[] attachment;
    private String name;
    private String contentType;
    private Date creationDate;
    private String damageId;
    private String userId;
    private String deviceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDamageId() {
        return damageId;
    }

    public void setDamageId(String damageId) {
        this.damageId = damageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
