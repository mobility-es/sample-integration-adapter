package com.appearnetworks.aiq.ia.dataaccess.model;


import java.util.List;

public class SubCode1DO {
    private String name;
    private List<SubCode2DO> subCode2DOs;

    public SubCode1DO(String name, List<SubCode2DO> subCode2DOs) {
        this.name = name;
        this.subCode2DOs = subCode2DOs;
    }

    public String getName() {
        return name;
    }

    public List<SubCode2DO> getSubCode2DOs() {
        return subCode2DOs;
    }
}
