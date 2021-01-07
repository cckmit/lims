package com.adc.da.pc_announcement.VO;
//公告生成委托单的申报项目VO
public class AnnPlanProjectExportVO {
    //车型
    private String pjModel;
    //立项申请表
    private String pjApprovalForm;
    //申报来源
    private String plSource;
    //申报来源编号
    private String plNo;
    //预算
    private String budget;

    public String getPjModel() {
        return pjModel;
    }

    public void setPjModel(String pjModel) {
        this.pjModel = pjModel;
    }

    public String getPjApprovalForm() {
        return pjApprovalForm;
    }

    public void setPjApprovalForm(String pjApprovalForm) {
        this.pjApprovalForm = pjApprovalForm;
    }

    public String getPlSource() {
        return plSource;
    }

    public void setPlSource(String plSource) {
        this.plSource = plSource;
    }

    public String getPlNo() {
        return plNo;
    }

    public void setPlNo(String plNo) {
        this.plNo = plNo;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
