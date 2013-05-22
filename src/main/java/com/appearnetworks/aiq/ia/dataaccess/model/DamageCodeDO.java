package com.appearnetworks.aiq.ia.dataaccess.model;

import java.util.List;

public class DamageCodeDO extends DataObject {
    private String name;
    private List<SubCode1DO> subCode1DOs;

    public DamageCodeDO(String name, List<SubCode1DO> subCode1DOs) {
        this.name = name;
        this.subCode1DOs = subCode1DOs;
    }

    public List<SubCode1DO> getSubCode1DOs() {
        return subCode1DOs;
    }

    public String getName() {
        return name;
    }
}
