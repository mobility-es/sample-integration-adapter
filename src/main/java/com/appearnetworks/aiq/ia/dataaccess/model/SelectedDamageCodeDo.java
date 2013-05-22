package com.appearnetworks.aiq.ia.dataaccess.model;


public class SelectedDamageCodeDO extends DataObject {
    private String level1;
    private String level2;
    private String level3;
    private String name;

    public SelectedDamageCodeDO(String level1, String level2, String level3, String name) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.name = name;
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

    public String getName() {
        return name;
    }
}
