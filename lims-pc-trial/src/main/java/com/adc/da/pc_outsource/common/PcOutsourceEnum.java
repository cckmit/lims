package com.adc.da.pc_outsource.common;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/10/23 10:46
 * @description：pc委外立项申请
 */
public enum PcOutsourceEnum {

    DRAFT("0", "草稿"),//pc
    UN_APPROVAL("1", "待审批"),//cv
    APPROVAL_ED("2", "已审批"),
    RECALL("3", "撤回中"),
    RECALL_ED("4", "已撤回"),
    FINISH("5", "已完成"),
    ROLLBACK("6", "退回");

    private String value;
    private String label;

    PcOutsourceEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public static String getName(int index) {
        for (PcOutsourceEnum c : PcOutsourceEnum.values()) {
            if (c.getValue().equals(index)) {
                return c.getLabel();
            }
        }
        return null;
    }

    public static String getIndex(String label) {
        for (PcOutsourceEnum c : PcOutsourceEnum.values()) {
            if (c.getLabel().equals(label)) {
                return c.getValue();
            }
        }
        return null;
    }


    public String getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
