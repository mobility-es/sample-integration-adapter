package com.appearnetworks.aiq.ia.dataaccess.model;

/**
 * @author Qambber Hussain, Appear Networks.
 */
public class DefectCodeDO {
    private String level1;
    private String level2;
    private String level3;

    public DefectCodeDO(String level1, String level2, String level3) {
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
    }

    public String getLevel1() {
        return level1;
    }

    public void setLevel1(String level1) {
        this.level1 = level1;
    }

    public String getLevel3() {
        return level3;
    }

    public void setLevel3(String level3) {
        this.level3 = level3;
    }

    public String getLevel2() {
        return level2;
    }

    public void setLevel2(String level2) {
        this.level2 = level2;
    }
}
