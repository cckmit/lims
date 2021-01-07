package com.adc.da.pc_parts_apply.page;

import com.adc.da.base.page.BasePage;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * <b>功能：</b>PC_PARTS_APPLY PcPartsApplyEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcPartsApplyEOPage extends BasePage {

    private String id;
    private String idOperator = "=";
    private String pickingCode;
    private String pickingCodeOperator = "=";
    private String torCode;
    private String torCodeOperator = "=";
    private String applyDate;
    private String applyDate1;
    private String applyDate2;
    private String applyDateOperator = "=";
    private String ifSup;
    private String ifSupOperator = "=";
    private String applyUserName;
    private String applyUserNameOperator = "=";
    private String applyUserId;
    private String applyUserIdOperator = "=";
    private String sendUserId;
    private String sendUserIdOperator = "=";
    private String sendUserName;
    private String sendUserNameOperator = "=";
    private String receiveUserId;
    private String receiveUserIdOperator = "=";
    private String receiveUserName;
    private String receiveUserNameOperator = "=";
    private String useFor;
    private String useForOperator = "=";
    private String delFlag;
    private String delFlagOperator = "=";
    private String remark;
    private String remarkOperator = "=";
    private String taskId;
    private String taskIdOperator = "=";
    private String receiveLocation;
    private String receiveLocationOperator = "=";
    private String taskNumber;
    private String taskNumberOperator = "=";
    
	/**
	 * 区分试验任务还是执行计划
	 * 0-试验任务；1-执行计划
	 */
	private String taskOrPlan;
	
	
	

    public String getTaskOrPlan() {
		return taskOrPlan;
	}

	public void setTaskOrPlan(String taskOrPlan) {
		this.taskOrPlan = taskOrPlan;
	}

	public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOperator() {
        return this.idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getPickingCode() {
        return this.pickingCode;
    }

    public void setPickingCode(String pickingCode) {
        this.pickingCode = pickingCode;
    }

    public String getPickingCodeOperator() {
        return this.pickingCodeOperator;
    }

    public void setPickingCodeOperator(String pickingCodeOperator) {
        this.pickingCodeOperator = pickingCodeOperator;
    }

    public String getTorCode() {
        return this.torCode;
    }

    public void setTorCode(String torCode) {
        this.torCode = torCode;
    }

    public String getTorCodeOperator() {
        return this.torCodeOperator;
    }

    public void setTorCodeOperator(String torCodeOperator) {
        this.torCodeOperator = torCodeOperator;
    }

    public String getApplyDate() {
        return this.applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApplyDate1() {
        return this.applyDate1;
    }

    public void setApplyDate1(String applyDate1) {
        this.applyDate1 = applyDate1;
    }

    public String getApplyDate2() {
        return this.applyDate2;
    }

    public void setApplyDate2(String applyDate2) {
        this.applyDate2 = applyDate2;
    }

    public String getApplyDateOperator() {
        return this.applyDateOperator;
    }

    public void setApplyDateOperator(String applyDateOperator) {
        this.applyDateOperator = applyDateOperator;
    }

    public String getIfSup() {
        return this.ifSup;
    }

    public void setIfSup(String ifSup) {
        this.ifSup = ifSup;
    }

    public String getIfSupOperator() {
        return this.ifSupOperator;
    }

    public void setIfSupOperator(String ifSupOperator) {
        this.ifSupOperator = ifSupOperator;
    }

    public String getApplyUserName() {
        return this.applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getApplyUserNameOperator() {
        return this.applyUserNameOperator;
    }

    public void setApplyUserNameOperator(String applyUserNameOperator) {
        this.applyUserNameOperator = applyUserNameOperator;
    }

    public String getApplyUserId() {
        return this.applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getApplyUserIdOperator() {
        return this.applyUserIdOperator;
    }

    public void setApplyUserIdOperator(String applyUserIdOperator) {
        this.applyUserIdOperator = applyUserIdOperator;
    }

    public String getSendUserId() {
        return this.sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getSendUserIdOperator() {
        return this.sendUserIdOperator;
    }

    public void setSendUserIdOperator(String sendUserIdOperator) {
        this.sendUserIdOperator = sendUserIdOperator;
    }

    public String getSendUserName() {
        return this.sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    public String getSendUserNameOperator() {
        return this.sendUserNameOperator;
    }

    public void setSendUserNameOperator(String sendUserNameOperator) {
        this.sendUserNameOperator = sendUserNameOperator;
    }

    public String getReceiveUserId() {
        return this.receiveUserId;
    }

    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getReceiveUserIdOperator() {
        return this.receiveUserIdOperator;
    }

    public void setReceiveUserIdOperator(String receiveUserIdOperator) {
        this.receiveUserIdOperator = receiveUserIdOperator;
    }

    public String getReceiveUserName() {
        return this.receiveUserName;
    }

    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    public String getReceiveUserNameOperator() {
        return this.receiveUserNameOperator;
    }

    public void setReceiveUserNameOperator(String receiveUserNameOperator) {
        this.receiveUserNameOperator = receiveUserNameOperator;
    }

    public String getUseFor() {
        return this.useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public String getUseForOperator() {
        return this.useForOperator;
    }

    public void setUseForOperator(String useForOperator) {
        this.useForOperator = useForOperator;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlagOperator() {
        return this.delFlagOperator;
    }

    public void setDelFlagOperator(String delFlagOperator) {
        this.delFlagOperator = delFlagOperator;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarkOperator() {
        return this.remarkOperator;
    }

    public void setRemarkOperator(String remarkOperator) {
        this.remarkOperator = remarkOperator;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskIdOperator() {
        return this.taskIdOperator;
    }

    public void setTaskIdOperator(String taskIdOperator) {
        this.taskIdOperator = taskIdOperator;
    }

    public String getReceiveLocation() {
        return receiveLocation;
    }

    public void setReceiveLocation(String receiveLocation) {
        this.receiveLocation = receiveLocation;
    }

    public String getReceiveLocationOperator() {
        return receiveLocationOperator;
    }

    public void setReceiveLocationOperator(String receiveLocationOperator) {
        this.receiveLocationOperator = receiveLocationOperator;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskNumberOperator() {
        return taskNumberOperator;
    }

    public void setTaskNumberOperator(String taskNumberOperator) {
        this.taskNumberOperator = taskNumberOperator;
    }
}
