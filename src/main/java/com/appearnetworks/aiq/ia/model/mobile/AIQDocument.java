package com.appearnetworks.aiq.ia.model.mobile;

/**
 * Document in AIQ data sync.
 *
 * @author Qambber Hussain, Appear Networks.
 */
public abstract class AIQDocument {
    private String _id;
    private long _rev;
    private String _type;

    /**
     * Needed for Jackson deserialization.
     */
    public AIQDocument() {}

    public AIQDocument(String _id, long _rev, String _type) {
        this._id = _id;
        this._rev = _rev;
        this._type = _type;
    }

    public String get_id() {
        return _id;
    }

    public long get_rev() {
        return _rev;
    }

    public String get_type() {
        return _type;
    }
}
