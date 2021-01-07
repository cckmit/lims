package com.adc.da.common.constant;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/9/3 10:36
 * @description：${description}
 */
public enum TaskStatusEnum {

    DRAFT(0, "草稿"),
    UNDERWAY(1, "进行中"),
    FINISH(2, "已完成"),
    CANCEL(3, "待确认"),
    END(4, "结束"),
    BACK(5, "不接受");

    private int value;
    private String label;

    TaskStatusEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getName(int index) {
        for (TaskStatusEnum c : TaskStatusEnum.values()) {
            if (c.getValue() == index) {
                return c.getLabel();
            }
        }
        return null;
    }

    public static Integer getIndex(String label) {
        for (TaskStatusEnum c : TaskStatusEnum.values()) {
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
