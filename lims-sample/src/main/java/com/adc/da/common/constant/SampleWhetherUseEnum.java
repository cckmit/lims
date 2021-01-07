package com.adc.da.common.constant;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/16 11:48
 * @description：${description}
 */
public enum SampleWhetherUseEnum {
    NO(0, "否"),
    YES(1, "是");

    private int value;
    private String label;

    SampleWhetherUseEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getName(int index) {
        for (SampleWhetherUseEnum c : SampleWhetherUseEnum.values()) {
            if (c.getValue() == index) {
                return c.getLabel();
            }
        }
        return null;
    }

    public static Integer getIndex(String label) {
        for (SampleWhetherUseEnum c : SampleWhetherUseEnum.values()) {
            if (c.getLabel().equals(label)) {
                return c.getValue();
            }
        }
        return null;
    }


    public int getValue() {
        return value;
    }


    public String getLabel() {
        return label;
    }

}
