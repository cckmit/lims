package com.adc.da.agent.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TP_AGENT AgentEOEntity<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public class AgentEO extends BaseEntity {

    private String id;
    private String createByName;
    private String createById;
    private String orgId;
    private String agentId;
    private String createDate;
    private String delFlag;
    private String endDate;
    private String startDate;
    private String agentName;

    /**
     * java字段名转换为原始数据库列名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>createById -> create_by_id</li>
     * <li>orgId -> org_id</li>
     * <li>agentId -> agent_id</li>
     * <li>createDate -> create_date</li>
     * <li>delFlag -> del_flag</li>
     * <li>endDate -> end_date</li>
     * <li>startDate -> start_date</li>
     * <li>agentName -> agent_name</li>
     */
    public static String fieldToColumn(String fieldName) {
        if (fieldName == null) return null;
        switch (fieldName) {
            case "id": return "id";
            case "createById": return "create_by_id";
            case "orgId": return "org_id";
            case "agentId": return "agent_id";
            case "createDate": return "create_date";
            case "delFlag": return "del_flag";
            case "endDate": return "end_date";
            case "startDate": return "start_date";
            case "agentName": return "agent_name";
            default: return null;
        }
    }

    /**
     * 原始数据库列名转换为java字段名。<b>如果不存在则返回null</b><br>
     * <p>字段列表：</p>
     * <li>id -> id</li>
     * <li>create_by_id -> createById</li>
     * <li>org_id -> orgId</li>
     * <li>agent_id -> agentId</li>
     * <li>create_date -> createDate</li>
     * <li>del_flag -> delFlag</li>
     * <li>end_date -> endDate</li>
     * <li>start_date -> startDate</li>
     * <li>agent_name -> agentName</li>
     */
    public static String columnToField(String columnName) {
        if (columnName == null) return null;
        switch (columnName) {
            case "id": return "id";
            case "create_by_id": return "createById";
            case "org_id": return "orgId";
            case "agent_id": return "agentId";
            case "create_date": return "createDate";
            case "del_flag": return "delFlag";
            case "end_date": return "endDate";
            case "start_date": return "startDate";
            case "agent_name": return "agentName";
            default: return null;
        }
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
    public String getCreateById() {
        return this.createById;
    }

    /**  **/
    public void setCreateById(String createById) {
        this.createById = createById;
    }

    /**  **/
    public String getOrgId() {
        return this.orgId;
    }

    /**  **/
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**  **/
    public String getAgentId() {
        return this.agentId;
    }

    /**  **/
    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    /**  **/
    public String getCreateDate() {
        return this.createDate;
    }

    /**  **/
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
    public String getEndDate() {
        return this.endDate;
    }

    /**  **/
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**  **/
    public String getStartDate() {
        return this.startDate;
    }

    /**  **/
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**  **/
    public String getAgentName() {
        return this.agentName;
    }

    /**  **/
    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCreateByName() {
        return createByName;
    }

    public void setCreateByName(String createByName) {
        this.createByName = createByName;
    }
}
