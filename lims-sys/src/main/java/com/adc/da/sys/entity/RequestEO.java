package com.adc.da.sys.entity;

import java.util.Map;

public class RequestEO {

    /**
     * 流程实例Id
     */
    private String procId;

    /**
     * BaseBusid
     */
    private String baseBusId;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 审批意见
     */
    private String comment;

    /**
     * 是否同意
     */
    private Map variables;

    /**
     * 任务类型   0：任务   1：候选任务
     */
    private String type;
    
    /**
     * 下一节点审批人，用于审批时指定下一节点审批人
     */
    private String nextAssignee;

    public RequestEO() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Map getVariables() {
        return variables;
    }

    public void setVariables(Map variables) {
        this.variables = variables;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public String getBaseBusId() {
        return baseBusId;
    }

    public void setBaseBusId(String baseBusId) {
        this.baseBusId = baseBusId;
    }

	public String getNextAssignee() {
		return nextAssignee;
	}

	public void setNextAssignee(String nextAssignee) {
		this.nextAssignee = nextAssignee;
	}
    
    
}
