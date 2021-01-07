package com.adc.da.sys.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.adc.da.base.entity.BaseEntity;

/**
 * <b>功能：</b>TS_USER UserEOEntity<br>
 * <b>日期：</b> 2017-12-18 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * <p>字段列表：</p>
 * <li>extInfo -> ext_info</li>
 * <li>usname -> usname</li>
 * <li>password -> password</li>
 * <li>delFlag -> del_flag</li>
 * <li>account -> account</li>
 * <li>usid -> usid</li>
 *
 * @author comments created by Lee Kwanho
 * date 2018-08-16
 * @see com.adc.da.sys.controller.UserEOController
 */
public class UserEO extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3658632939727891047L;
    
    /**
     *  头像
     *  date 2018-10-17
     *
     */
    
    private  String avatar;
    
    /**
     * 用户名
     */
    private String usname;

    /**
     * 密码
     */
    private String password;

    /**
     * 删除标记
     */
    private Integer delFlag;

    /**
     * 用户账户
     */
    private String account;

    /**
     * 用户id
     */
    private String usid;

    /**
     * 人员编号/工号
     */
    private String userCode;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 手机号码
     */
    private String cellPhoneNumber;
    /**
     * 家庭住址
     */
    private String homeAddress;
    /**
     * 邮政编码
     */
    private String postalCode;
    /**
     * 电子邮件
     */
    private String email;
    /**
     * 通信地址
     */
    private String contactAddress;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 用户状态-数据字典
     */
    private String userState;

    /**
     * 角色ID列表
     */
    private List<String> roleIdList = new ArrayList<>();

    /**
     * 角色EO列表
     */
    private List<RoleEO> roleEOList = new ArrayList<>();

    /**
     * 组织Id列表
     */
    private List<String> orgIdList = new ArrayList<>();

    /**
     * 组织EO列表
     */
    private List<OrgEO> orgEOList = new ArrayList<>();

    // 以下为扩展字段
    /**
     * 拓展字段——保留字段
     */
    private String extInfo;

    private String extInfo2;

    private String extInfo3;

    private String extInfo4;

    /**
     * 锁定用户状态
     * 0锁定 1激活可用
     */
    private String extInfo5;

    /**
     * 用户职位
     * 时间 20190717
     * 作者 张英慧
     */
    private  String usposition;
    /**
     * 授权签字领域  数据字典
     * 时间 20190717
     * 作者 张英慧
     */
    private  String ussigndomain;
    /**
     * 质资
     * 时间20190717
     * 作者张英慧
     */
    private String honor;
    /**
     * 质资描述
     * 时间20190717
     * 作者张英慧
     */
    private String honordescription;
    /**
     * 印章
     * 时间20190717
     * 作者张英慧
     */
    private  String seal;

    /**
     * 所有角色名称，逗号分隔
     */
    private String roleName;

    private String roleCode;
    
    /**
     * 头像名称
     */
    private String avatarName;
    /**
     * 质资名称
     */
    private String honorName;
    /**
     * 印章名称
     */
    private String sealName;
    
    

    public String getAvatarName() {
		return avatarName;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	public String getHonorName() {
		return honorName;
	}

	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}

	public String getSealName() {
		return sealName;
	}

	public void setSealName(String sealName) {
		this.sealName = sealName;
	}

	public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 组织机构
     */
    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }




    /**  **/
    public String getExtInfo() {
        return this.extInfo;
    }

    /**  **/
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }

    /**  **/
    public String getUsname() {
        return this.usname;
    }

    /**  **/
    public void setUsname(String usname) {
        this.usname = usname;
    }

    /**  **/
    public String getPassword() {
        return this.password;
    }

    /**  **/
    public void setPassword(String password) {
        this.password = password;
    }

    /**  **/
    public Integer getDelFlag() {
        return this.delFlag;
    }

    /**  **/
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**  **/
    public String getAccount() {
        return this.account;
    }

    /**  **/
    public void setAccount(String account) {
        this.account = account;
    }

    /**  **/
    public String getUsid() {
        return this.usid;
    }

    /**  **/
    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public List<String> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<RoleEO> getRoleEOList() {
        return roleEOList;
    }

    public void setRoleEOList(List<RoleEO> roleEOList) {
        this.roleEOList = roleEOList;
    }

    public List<String> getOrgIdList() {
        return orgIdList;
    }

    public void setOrgIdList(List<String> orgIdList) {
        this.orgIdList = orgIdList;
    }

    public List<OrgEO> getOrgEOList() {
        return orgEOList;
    }

    public void setOrgEOList(List<OrgEO> orgEOList) {
        this.orgEOList = orgEOList;
    }
    
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
    public String getExtInfo2() {
        return extInfo2;
    }

    public void setExtInfo2(String extInfo2) {
        this.extInfo2 = extInfo2;
    }

    public String getExtInfo3() {
        return extInfo3;
    }

    public void setExtInfo3(String extInfo3) {
        this.extInfo3 = extInfo3;
    }

    public String getExtInfo4() {
        return extInfo4;
    }

    public void setExtInfo4(String extInfo4) {
        this.extInfo4 = extInfo4;
    }

    public String getExtInfo5() {
        return extInfo5;
    }

    public void setExtInfo5(String extInfo5) {
        this.extInfo5 = extInfo5;
    }

    public String getUsposition() {
        return usposition;
    }

    public void setUsposition(String usposition) {
        this.usposition = usposition;
    }

    public String getUssigndomain() {
        return ussigndomain;
    }

    public void setUssigndomain(String ussigndomain) {
        this.ussigndomain = ussigndomain;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor;
    }

    public String getHonordescription() {
        return honordescription;
    }

    public void setHonordescription(String honordescription) {
        this.honordescription = honordescription;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }
}
