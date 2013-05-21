package com.appearnetworks.aiq.ia.model.mobile;

import com.appearnetworks.aiq.ia.dataaccess.model.DataObject;

import java.util.List;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class DamageCode {
    private String name;
    private List<DamageCode> subCodes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DamageCode> getSubCodes() {
        return subCodes;
    }

    public void setSubCodes(List<DamageCode> subCodes) {
        this.subCodes = subCodes;
    }
}
