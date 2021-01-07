package com.adc.da.sys.page;

import com.adc.da.base.page.BasePage;

import java.util.List;

/**
 * <b>功能：</b>TS_USER UserEOPage<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 */
public class UserEOPage extends BasePage {

    //主键
    private String usid;

    private String usidOperator = "=";

    //登录账号
    private String account;

    private String accountOperator = "=";

    //删除标志0有效 1删除
    private String delFlag;

    private String delFlagOperator = "=";

    //用户姓名
    private String usname;

    private String usnameOperator = "=";


    private String password;

    //所属角色
    private String rolename;

    private String rolenameOperator = "=";

    private String roleId;

    private String roleIdOperator = "=";
    //所属机构
    private String orgName;

    private String orgNameOperator = "=";

    private String orgId;

    private String orgIdOperator = "=";

    /**
     * 新添加的查询条件
     * 时间20190717
     * 作者张英慧
     * @return
     */
    //工号
    private String userCode;

    private String userCodeOperator = "=";

    //职位

    private String usposition;

    private  String uspositionOpetator = "=";

    //邮箱
    private String email;
    private String emailOperator = "=";

    //手机号
    private String cellPhoneNumber;
    private String cellPhoneNumberOperator = "=";

    //通用查询
    private List<String> searchPhrase;
    private String searchPhraseOperation = "=";

    public String getUsid() {
        return this.usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getUsidOperator() {
        return this.usidOperator;
    }

    public void setUsidOperator(String usidOperator) {
        this.usidOperator = usidOperator;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountOperator() {
        return this.accountOperator;
    }

    public void setAccountOperator(String accountOperator) {
        this.accountOperator = accountOperator;
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

    public String getUsname() {
        return this.usname;
    }

    public void setUsname(String usname) {
        this.usname = usname;
    }

    public String getUsnameOperator() {
        return this.usnameOperator;
    }

    public void setUsnameOperator(String usnameOperator) {
        this.usnameOperator = usnameOperator;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getRolenameOperator() {
        return rolenameOperator;
    }

    public void setRolenameOperator(String rolenameOperator) {
        this.rolenameOperator = rolenameOperator;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleIdOperator() {
        return roleIdOperator;
    }

    public void setRoleIdOperator(String roleIdOperator) {
        this.roleIdOperator = roleIdOperator;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNameOperator() {
        return orgNameOperator;
    }

    public void setOrgNameOperator(String orgNameOperator) {
        this.orgNameOperator = orgNameOperator;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgIdOperator() {
        return orgIdOperator;
    }

    public void setOrgIdOperator(String orgIdOperator) {
        this.orgIdOperator = orgIdOperator;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserCodeOperator() {
        return userCodeOperator;
    }

    public void setUserCodeOperator(String userCodeOperator) {
        this.userCodeOperator = userCodeOperator;
    }

    public String getUsposition() {
        return usposition;
    }

    public void setUsposition(String usposition) {
        this.usposition = usposition;
    }

    public String getUspositionOpetator() {
        return uspositionOpetator;
    }

    public void setUspositionOpetator(String uspositionOpetator) {
        this.uspositionOpetator = uspositionOpetator;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailOperator() {
        return emailOperator;
    }

    public void setEmailOperator(String emailOperator) {
        this.emailOperator = emailOperator;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getCellPhoneNumberOperator() {
        return cellPhoneNumberOperator;
    }

    public void setCellPhoneNumberOperator(String cellPhoneNumberOperator) {
        this.cellPhoneNumberOperator = cellPhoneNumberOperator;
    }

    public List<String> getSearchPhrase() {
        return searchPhrase;
    }

    public void setSearchPhrase(List<String> searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public String getSearchPhraseOperation() {
        return searchPhraseOperation;
    }

    public void setSearchPhraseOperation(String searchPhraseOperation) {
        this.searchPhraseOperation = searchPhraseOperation;
    }
}
