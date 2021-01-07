package com.adc.da.pc_parts_apply.entity;

import com.adc.da.base.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * <b>功能：</b>PC_PARTS_APPLY PcPartsApplyEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class PcPartsApplyEO extends BaseEntity {

    private String id;
    @ApiModelProperty("领料单编号")
    private String pickingCode;
    @ApiModelProperty("任务编号")
    private String torCode;
    @ApiModelProperty("申请日期")
    private String applyDate;
    @ApiModelProperty("是否委外")
    private String ifSup;
    @ApiModelProperty("申请人名")
    private String applyUserName;
    @ApiModelProperty("申请人id")
    private String applyUserId;
    @ApiModelProperty("发料人id")
    private String sendUserId;
    @ApiModelProperty("发料人名")
    private String sendUserName;
    @ApiModelProperty("领料人id")
    private String receiveUserId;
    @ApiModelProperty("领料人名称")
    private String receiveUserName;
    @ApiModelProperty("用途")
    private String useFor;
    private String delFlag;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("任务id")
    private String taskId;
    //0:cv 1:pv
    private String pvOrcv;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("领料地点")
    private String receiveLocation;
    private List<PcPartsApplySampleEO> pcPartsApplySampleEOList;
    @ApiModelProperty("试验任务编号")
    private String taskNumber;

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

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>pickingCode -> picking_code</li>
     * <li>torCode -> tor_code</li>
     * <li>applyDate -> apply_date</li>
     * <li>ifSup -> if_sup</li>
     * <li>applyUserName -> apply_user_name</li>
     * <li>applyUserId -> apply_user_id</li>
     * <li>sendUserId -> send_user_id</li>
     * <li>sendUserName -> send_user_name</li>
     * <li>receiveUserId -> receive_user_id</li>
     * <li>receiveUserName -> receive_user_name</li>
     * <li>useFor -> use_for</li>
     * <li>delFlag -> del_flag</li>
     * <li>remark -> remark</li>
     * <li>taskId -> task_id</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "pickingCode": return "picking_code";
            case "torCode": return "tor_code";
            case "applyDate": return "apply_date";
            case "ifSup": return "if_sup";
            case "applyUserName": return "apply_user_name";
            case "applyUserId": return "apply_user_id";
            case "sendUserId": return "send_user_id";
            case "sendUserName": return "send_user_name";
            case "receiveUserId": return "receive_user_id";
            case "receiveUserName": return "receive_user_name";
            case "useFor": return "use_for";
            case "delFlag": return "del_flag";
            case "remark": return "remark";
            case "taskId": return "task_id";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>picking_code -> pickingCode</li>
     * <li>tor_code -> torCode</li>
     * <li>apply_date -> applyDate</li>
     * <li>if_sup -> ifSup</li>
     * <li>apply_user_name -> applyUserName</li>
     * <li>apply_user_id -> applyUserId</li>
     * <li>send_user_id -> sendUserId</li>
     * <li>send_user_name -> sendUserName</li>
     * <li>receive_user_id -> receiveUserId</li>
     * <li>receive_user_name -> receiveUserName</li>
     * <li>use_for -> useFor</li>
     * <li>del_flag -> delFlag</li>
     * <li>remark -> remark</li>
     * <li>task_id -> taskId</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "picking_code": return "pickingCode";
            case "tor_code": return "torCode";
            case "apply_date": return "applyDate";
            case "if_sup": return "ifSup";
            case "apply_user_name": return "applyUserName";
            case "apply_user_id": return "applyUserId";
            case "send_user_id": return "sendUserId";
            case "send_user_name": return "sendUserName";
            case "receive_user_id": return "receiveUserId";
            case "receive_user_name": return "receiveUserName";
            case "use_for": return "useFor";
            case "del_flag": return "delFlag";
            case "remark": return "remark";
            case "task_id": return "taskId";
            default: return null;
        }
    }

    public String getPvOrcv() {
        return pvOrcv;
    }

    public void setPvOrcv(String pvOrcv) {
        this.pvOrcv = pvOrcv;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PcPartsApplySampleEO> getPcPartsApplySampleEOList() {
        return pcPartsApplySampleEOList;
    }

    public void setPcPartsApplySampleEOList(List<PcPartsApplySampleEO> pcPartsApplySampleEOList) {
        this.pcPartsApplySampleEOList = pcPartsApplySampleEOList;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

    /**  **/
    public String getPickingCode() {
        return this.pickingCode;
    }

    /**  **/
    public void setPickingCode(String pickingCode) {
        this.pickingCode = pickingCode;
    }

    /**  **/
    public String getTorCode() {
        return this.torCode;
    }

    /**  **/
    public void setTorCode(String torCode) {
        this.torCode = torCode;
    }

    /**  **/
    public String getApplyDate() {
        return this.applyDate;
    }

    /**  **/
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    /**  **/
    public String getIfSup() {
        return this.ifSup;
    }

    /**  **/
    public void setIfSup(String ifSup) {
        this.ifSup = ifSup;
    }

    /**  **/
    public String getApplyUserName() {
        return this.applyUserName;
    }

    /**  **/
    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    /**  **/
    public String getApplyUserId() {
        return this.applyUserId;
    }

    /**  **/
    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    /**  **/
    public String getSendUserId() {
        return this.sendUserId;
    }

    /**  **/
    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**  **/
    public String getSendUserName() {
        return this.sendUserName;
    }

    /**  **/
    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }

    /**  **/
    public String getReceiveUserId() {
        return this.receiveUserId;
    }

    /**  **/
    public void setReceiveUserId(String receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    /**  **/
    public String getReceiveUserName() {
        return this.receiveUserName;
    }

    /**  **/
    public void setReceiveUserName(String receiveUserName) {
        this.receiveUserName = receiveUserName;
    }

    /**  **/
    public String getUseFor() {
        return this.useFor;
    }

    /**  **/
    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    /**  **/
    public String getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getRemark() {
        return this.remark;
    }

    /**  **/
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**  **/
    public String getTaskId() {
        return this.taskId;
    }

    /**  **/
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReceiveLocation() {
        return receiveLocation;
    }

    public void setReceiveLocation(String receiveLocation) {
        this.receiveLocation = receiveLocation;
    }

    public String getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }
}
