package com.adc.da.internalcost.vo;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/19 11:51
 * @description：
 */
public class TrialTaskCostBudgetVO {

    // 任务书ID
    private String id;

    // 任务书编号
    private String trialTaskCode;
    // 申请人
    private String applyUser;
    // 申请时间
    private String applyTime;

    // 可靠性立项单ID
    private String pcReliableInittask;

    // 名称
    private String itemsName;

    // code
    private String itemsCode;

    // 小计
    private String total;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrialTaskCode() {
        return trialTaskCode;
    }

    public void setTrialTaskCode(String trialTaskCode) {
        this.trialTaskCode = trialTaskCode;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getPcReliableInittask() {
        return pcReliableInittask;
    }

    public void setPcReliableInittask(String pcReliableInittask) {
        this.pcReliableInittask = pcReliableInittask;
    }

    public String getItemsName() {
        return itemsName;
    }

    public void setItemsName(String itemsName) {
        this.itemsName = itemsName;
    }

    public String getItemsCode() {
        return itemsCode;
    }

    public void setItemsCode(String itemsCode) {
        this.itemsCode = itemsCode;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
