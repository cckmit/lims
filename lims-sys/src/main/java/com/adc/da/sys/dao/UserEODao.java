package com.adc.da.sys.dao;

import java.util.List;

import com.adc.da.sys.page.UserEOPage;
import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.UserEO;

/**
 * <br>
 * <b>功能：</b>TS_USER UserEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-12-18 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 *
 * @author comments created by Lee Kwanho
 * @see mybatis.mapper.sys
 */
public interface UserEODao extends BaseDao<UserEO> {
    
    /**
     * 更新密码，@Param("String") 对应xml文件中的字段
     *
     * @param userId      用户id
     * @param newPassword 新密码
     */
    void updatePassword(@Param("userId") String userId, @Param("newPassword") String newPassword);
    
    /**
     * 更新用户信息
     *
     * @param userEO 用户信息
     */
    void updateUserEO(UserEO userEO);
    
    /**
     * 获取角色信息
     *
     * @param usid 用户id
     * @return 用户角色id组
     */
    List<String> getRoleIdListByUserId(String usid);

    /*
     * @Author syxy_zhangyinghui
     * @Description 获取组织机构id信息
     * @Date 16:52 2019/8/8
     * @Param 
     * @return 
     **/
    List<String> getOrgIdListByUserId(String usid);
    
    /**
     * 保存用户角色
     *
     * @param usid   用户id
     * @param roleId 角色id
     */
    void saveUserRole(@Param("usid") String usid, @Param("roleId") String roleId);
    
    /**
     * 保存用户组织
     *
     * @param usid  用户id
     * @param orgId 组织id
     */
    void saveUserOrg(@Param("usid") String usid, @Param("orgId") String orgId);
    
    /**
     * 物理删除用户角色关联
     *
     * @param usid 用户ID
     */
    void deleteUserRoleByUsid(String usid);
    
    /**
     * 物理删除用户组织机构关联
     *
     * @param usid 用户ID
     */
    void deleteUserOrgByUsid(String usid);
    
    /**
     * 批量删除用户
     *
     * @param usids 用户id组
     */
    void deleteLogicInBatch(List<String> usids);
    
    /**
     * 批量删除用户角色关联
     *
     * @param usids 用户ID集合
     */
    void deleteUserRoleByUsidInBatch(List<String> usids);
    
    /**
     * 批量删除用户组织机构关联
     *
     * @param usids 用户ID集合
     */
    void deleteUserOrgByUsidInBatch(List<String> usids);
    
    /**
     * 根据账号获取用户信息
     *
     * @param account 账号
     * @return 用户信息，若无则返回空
     */
    UserEO getUserEOByAccount(String account);
    
    // 根据account获取未逻辑删除的UserEO
    UserEO getUserEOByAccountNotDeleted(String account);
    
    /**
     * 查询用户及用户所对应的角色
     *
     * @param id 用户id
     * @return 用户信息
     */
    UserEO getUserWithRoles(String id);
    
    /**
     * 查询用户不包括所对应角色
     *
     * @param id 用户id
     * @return 角色信息
     */
    UserEO get(String id);
    
    /**
     * 批量设置用户角色关联 @Param 对应xml中的字段
     *
     * @param userIds 用户id组
     * @param roleId  角色id
     * @author Lee Kwanho 李坤澔
     */
    void saveMultiUserRole(@Param("usIds") List<String> userIds, @Param("roleId") String roleId);
    
    /**
     * 批量设置用户组织关联 @Param 对应xml中的字段
     *
     * @param userIds 用户id组
     * @param orgId   组织id
     * @author Lee Kwanho 李坤澔
     */
    void saveMultiUserOrg(@Param("usIds") List<String> userIds, @Param("orgId") String orgId);
    
    /**
     * 批量删除用户角色关联 @Param 对应xml中的字段
     *
     * @param userIds 用户id组
     * @param roleId  角色id
     * @author Lee Kwanho 李坤澔
     */
    void deleteMultiUserRoleByRoleId(@Param("usIds") List<String> userIds, @Param("roleId") String roleId);
    
    /**
     * 批量删除用户组织关联 @Param 对应xml中的字段
     *
     * @param userIds 用户id组
     * @param org     组织id
     * @author Lee Kwanho 李坤澔
     */
    void deleteMultiUserOrgByOrgId(@Param("usIds") List<String> userIds, @Param("orgId") String org);
    
    /**
     * 上传头像
     *
     * @param userID 用户id
     * @param fileID 头像id
     * @author Lee Kwanho 李坤澔
     * date 2018-08-22
     **/
    void saveUserAvatar(@Param("id") String id, @Param("userID") String userID, @Param("fileID") String fileID);
    
    /**
     * 删除头像
     *
     * @param userID 用户id
     * @author Lee Kwanho 李坤澔
     * date 2018-10-18
     **/
    void deleteUserAvatar(@Param("userID") String userID);
    /*
     * @Author syxy_zhangyinghui
     * @Description 删除资质
     * @Date 13:35 2019/7/22
     * @Param [userId] 用户id
     * @return void
     **/
    void deleteUserHonor(@Param("userId") String userId);

    /*
     * @Author syxy_zhangyinghui
     * @Description //保存资质
     * @Date 13:35 2019/7/22
     * @Param
     * @return
     **/
    void saveUserHonor(@Param("id") String id ,@Param("userId") String userId,@Param("fileId") String fileId,
                       @Param("description") String description);

    /*
     * @Author syxy_zhangyinghui
     * @Description //更新资质根据用户id
     * @Date 14:25 2019/7/22
     * @Param
     * @return
     **/
    void updateUserHonor(@Param("userId") String userId,@Param("fileId") String fileId,
                         @Param("description") String description);

    /*
     * @Author syxy_zhangyinghui
     * @Description //删除印章
     * @Date 14:04 2019/7/22
     * @Param
     * @return
     **/
    void  deleteUserSeal(@Param("userId") String userId);

    /*
     * @Author syxy_zhangyinghui
     * @Description //保存印章
     * @Date 14:20 2019/7/22
     * @Param
     * @return
     **/
    void saveUserSeal(@Param("id") String id ,@Param("userId") String userId,@Param("fileId") String fileId);

    /*
     * @Author syxy_zhangyinghui
     * @Description //更新用户印章 根据用户id
     * @Date 14:26 2019/7/22
     * @Param
     * @return
     **/
    void  updateUserSeal(@Param("userId") String userId,@Param("fileId") String fileId);

    /*
     * @Author syxy_zhangyinghui
     * @Description //根据用户id锁定用户   0为锁定  1位激活成功可用
     * @Date 16:20 2019/7/22
     * @Param
     * @return
     **/
    void lockUser(@Param("userId") String userId,@Param("lockStatus") String extInfo5);

    /**
     * 判断代理人是否在允许代理期间
     * @param agentId
     * @return
     */
    public int findAgent(@Param("agentId") String agentId, @Param("userId") String userId);

    /**
     * 根据ids查询用户
     * @param ids
     * @return
     */
    List<UserEO> findUserByIds(String[] ids);

    /**
     * 根据code查询用户
     * @param code
     * @return
     */
    List<UserEO> getUser(String code);

    void deleteRelationByUserId(String userId);

    /**
     * 根据用户真实姓名查用户
     * @param name
     * @return
     */
    List<UserEO> getUserByUserName(String name);

    /**
     * 查出对应角色里面的用户列表
     * @param page
     * @return
     */
    List<UserEO> queryByPageByRole(UserEOPage page);

    Integer queryByCountByRole(UserEOPage page);

    /**
     * 根据用户ID查询用户
     * @param usId
     * @return
     */
    UserEO getUserById(String usId);

    /**
     *  根据用户ID查询部门ID
     * @param usid
     * @return
     */
    String getOrgIdByUserId(String usid);

    UserEO findUserByAccount(String account);

    /**
     * 查找指定组织机构下拥有指定角色的用户
     * @param roleId
     * @param orgId
     * @return
     */
    List<UserEO> queryByRoleAndOrg(String roleId, String orgId);
}
