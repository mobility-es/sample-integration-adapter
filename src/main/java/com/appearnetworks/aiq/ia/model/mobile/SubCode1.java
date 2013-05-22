package com.appearnetworks.aiq.ia.model.mobile;


import java.util.List;

public class SubCode1 {
    private String name;
    private List<SubCode2> subCodes;

    public SubCode1() {
    }

    public SubCode1(String name, List<SubCode2> subCodes) {
        this.name = name;
        this.subCodes = subCodes;
    }

    public String getName() {
        return name;
    }

    public List<SubCode2> getSubCodes() {
        return subCodes;
    }
}
