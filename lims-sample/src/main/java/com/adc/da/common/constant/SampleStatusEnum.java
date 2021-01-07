package com.adc.da.common.constant;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/8/14 16:48
 * @description：${description}
 */
public enum SampleStatusEnum {
    BACK(0, "退样"),
    RECEIVED(1, "接收"),
    COLLECT(2, "领样"),
    RETURN(3, "在库"),
    SCRAP(4, "报废"),
    NOTRECEIVED(5, "待收样"),
    ARCHIVE(6, "封存"),
    UNPACK(7, "拆机");

    private int value;
    private String label;

    SampleStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getName(int index) {
        for (SampleStatusEnum c : SampleStatusEnum.values()) {
            if (c.getValue() == index) {
                return c.getLabel();
            }
        }
        return null;
    }

    public static Integer getIndex(String label) {
        for (SampleStatusEnum c : SampleStatusEnum.values()) {
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
