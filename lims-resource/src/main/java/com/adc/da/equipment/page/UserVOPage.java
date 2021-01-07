package com.adc.da.equipment.page;

import com.adc.da.base.page.BasePage;

public class UserVOPage extends BasePage {
    //通用查询条件
    private String searchPhrase;
    //用户名
    private String account;
    //所属部门
    private String orgName;
    //中文姓名
    private String usName;
    //所属角色
    private String roleName;
    //工号
    private String userCode;
    //职位
    private String usposition;
    //邮箱
    private String email;
    //手机号
    private String cellPhoneNumber;

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUsName() {
        return usName;
    }

    public void setUsName(String usName) {
        this.usName = usName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsposition() {
        return usposition;
    }

    public void setUsposition(String usposition) {
        this.usposition = usposition;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }
}
