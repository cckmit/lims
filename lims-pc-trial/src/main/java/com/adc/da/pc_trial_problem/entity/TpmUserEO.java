package com.adc.da.pc_trial_problem.entity;

public class TpmUserEO {

    //中文名
    private String tpmUserNameCN;

    //登录账号/域账号
    private String getTpmUserNameEN;

    //部门名
    private String tpmDepartName;

    public String getTpmUserNameCN() {
        return tpmUserNameCN;
    }

    public void setTpmUserNameCN(String tpmUserNameCN) {
        this.tpmUserNameCN = tpmUserNameCN;
    }

    public String getGetTpmUserNameEN() {
        return getTpmUserNameEN;
    }

    public void setGetTpmUserNameEN(String getTpmUserNameEN) {
        this.getTpmUserNameEN = getTpmUserNameEN;
    }

    public String getTpmDepartName() {
        return tpmDepartName;
    }

    public void setTpmDepartName(String tpmDepartName) {
        this.tpmDepartName = tpmDepartName;
    }
}
