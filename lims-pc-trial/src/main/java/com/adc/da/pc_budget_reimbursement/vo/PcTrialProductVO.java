package com.adc.da.pc_budget_reimbursement.vo;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/13 14:35
 * @description：
 */
public class PcTrialProductVO {

    /**
     * 当前登录人
     */
    private String onlineUserName;

    /**
     * 出差人部门
     */
    private String businessOrg;

    /**
     * 出差人
     */
    private String businessPeople;

    /**
     * 出差人编号
     */
    private String businessCode;

    /**
     * 出差任务
     */
    private String businessTask;

    /**
     * 费用报销编号
     */
    private String code;

    /**
     * 审核人
     */
    private String virifier;

    /**
     * 批准人
     */
    private String approver;

    /**
     * 是否退款
     */
    private String refund;

    /**
     * 请款人
     */
    private String requestName;

    /**
     * 请款金额
     */
    private String requestAmount;

    /**
     * 过路过桥费总计
     */
    private String passWayTotal;

    /**
     * 加油/充电/加气费
     */
    private String refuelChargeAirEntrappingTotal;
    /**
     * 停车费
     */
    private String parkTotal;

    /**
     * 维修费
     */
    private String maintainTotal;

    /**
     * 快递费
     */
    private String expressTotal;

    /**
     * 租赁费
     */
    private String hireTotal;

    /**
     * 其他
     */
    private String pcOtherTotal;

    /**
     * 票数（张）
     */
    private String pollNmber;

    /**
     * 金额总计
     */
    private String amountTotal;

    private List<PcTrialProductInfoVO> pcTrialProductInfoVOList;

    public String getOnlineUserName() {
        return onlineUserName;
    }

    public void setOnlineUserName(String onlineUserName) {
        this.onlineUserName = onlineUserName;
    }

    public String getBusinessOrg() {
        return businessOrg;
    }

    public void setBusinessOrg(String businessOrg) {
        this.businessOrg = businessOrg;
    }

    public String getBusinessPeople() {
        return businessPeople;
    }

    public void setBusinessPeople(String businessPeople) {
        this.businessPeople = businessPeople;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessTask() {
        return businessTask;
    }

    public void setBusinessTask(String businessTask) {
        this.businessTask = businessTask;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVirifier() {
        return virifier;
    }

    public void setVirifier(String virifier) {
        this.virifier = virifier;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getRefund() {
        return refund;
    }

    public void setRefund(String refund) {
        this.refund = refund;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getPassWayTotal() {
        return passWayTotal;
    }

    public void setPassWayTotal(String passWayTotal) {
        this.passWayTotal = passWayTotal;
    }

    public String getParkTotal() {
        return parkTotal;
    }

    public void setParkTotal(String parkTotal) {
        this.parkTotal = parkTotal;
    }

    public String getMaintainTotal() {
        return maintainTotal;
    }

    public void setMaintainTotal(String maintainTotal) {
        this.maintainTotal = maintainTotal;
    }

    public String getExpressTotal() {
        return expressTotal;
    }

    public void setExpressTotal(String expressTotal) {
        this.expressTotal = expressTotal;
    }

    public String getHireTotal() {
        return hireTotal;
    }

    public void setHireTotal(String hireTotal) {
        this.hireTotal = hireTotal;
    }

    public String getPcOtherTotal() {
        return pcOtherTotal;
    }

    public void setPcOtherTotal(String pcOtherTotal) {
        this.pcOtherTotal = pcOtherTotal;
    }

    public String getPollNmber() {
        return pollNmber;
    }

    public void setPollNmber(String pollNmber) {
        this.pollNmber = pollNmber;
    }

    public String getRefuelChargeAirEntrappingTotal() {
        return refuelChargeAirEntrappingTotal;
    }

    public void setRefuelChargeAirEntrappingTotal(String refuelChargeAirEntrappingTotal) {
        this.refuelChargeAirEntrappingTotal = refuelChargeAirEntrappingTotal;
    }

    public String getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(String amountTotal) {
        this.amountTotal = amountTotal;
    }

    public List<PcTrialProductInfoVO> getPcTrialProductInfoVOList() {
        return pcTrialProductInfoVOList;
    }

    public void setPcTrialProductInfoVOList(List<PcTrialProductInfoVO> pcTrialProductInfoVOList) {
        this.pcTrialProductInfoVOList = pcTrialProductInfoVOList;
    }
}
