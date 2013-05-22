package com.appearnetworks.aiq.ia.model.mobile;

import java.util.List;


public class DamageCode {
    private String name;
    private List<SubCode1> subCodes;

    public DamageCode() {
    }

    public DamageCode(String name, List<SubCode1> subCodes) {
        this.name = name;
        this.subCodes = subCodes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubCode1> getSubCodes() {
        return subCodes;
    }
}
