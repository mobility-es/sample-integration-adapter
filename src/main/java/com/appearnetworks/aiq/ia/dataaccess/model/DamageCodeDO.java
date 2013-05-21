package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class DamageCodeDO extends DataObject {
    private String level1;
    private String level2;
    private String level3;
    private String code;

    public DamageCodeDO(String level1, String level2, String level3, String code) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.code = code;
    }

    public String getLevel1() {
        return level1;
    }

    public String getLevel3() {
        return level3;
    }

    public String getLevel2() {
        return level2;
    }

    public String getCode() {
        return code;
    }
}
