package com.adc.da.sys.constant;

/**
 * 用於菜單和角色判斷
 *
 * @author comments created by Lee Kwanho
 * date 2018-08-15
 * @see com.adc.da.sys.controller.MenuEOController
 * @see com.adc.da.sys.controller.RoleEOController
 */
public enum IsBelongEnum {
    BELONG(1, "属于");

    /**
     * 对应数值
     */
    private int value;

    /**
     * 代表意义
     */
    private String label;

    IsBelongEnum(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

}
