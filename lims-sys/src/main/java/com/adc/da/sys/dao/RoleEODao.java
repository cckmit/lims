package com.adc.da.sys.dao;

import java.util.List;

import com.adc.da.sys.page.RoleEOPage;
import com.adc.da.sys.page.UserRoleEOPage;
import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserRoleEO;

/**
 * <br>
 * <b>功能：</b>TS_ROLE RoleEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 *
 * @author comments created by Lee Kwanho
 * @see mybatis.mapper.sys
 */
public interface RoleEODao extends BaseDao<RoleEO> {
    
    /**
     * 保存角色信息
     *
     * @param sysRoleEO 角色信息
     */
    void save(RoleEO sysRoleEO);
    
    /**
     * 获取角色菜单
     *
     * @param id 角色id
     * @return 角色菜单
     */
    RoleEO getRoleWithMenus(String id);
    
    /**
     * 获取角色信息，可能有多角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<RoleEO> getRoleListByUserId(String userId);
    
    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void deleteLogic(String roleId);
    
    /**
     * 删除角色菜单关联
     *
     * @param roleId 角色id
     */
    void deleteRoleMenuByRoleId(String roleId);
    
    /**
     * 获取所有角色
     *
     * @return 全角色信息
     */
    List<RoleEO> findAll();

    /**
     * 查询角色所对应的用户
     * @param roleEOPage
     * @return
     */
    List<UserRoleEO> getUserRoleListPageByRoleId(RoleEOPage roleEOPage);

    /**
     * 查询角色所对应的用户
     * @param roleId
     * @return
     */
    List<UserRoleEO> getUserRoleListByRoleId(String roleId);


    /**
     * 获取菜单id组 ， 用角色id
     *
     * @param roleId 角色id
     * @return 菜单id组
     */
    List<String> getMenuIdListByRoleId(String roleId);
    
    /**
     * 新增角色菜单关联
     *
     * @param roleId 角色id
     * @param menuId 菜单id
     */
    void saveRoleMenu(@Param("roleId") String roleId, @Param("menuId") String menuId);
    
    /**
     * 判断角色是否属于相应用户
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 若属于返回1 ，否则返回0
     */
    int isBelong(@Param("userId") String userId, @Param("roleId") String roleId);

    List<RoleEO> getRoleByCode(String roleCode);

    /*
     * @Author syxy_zhangyinghui
     * @Description //根据角色名查询角色信息
     * @Date 10:58 2019/7/23
     * @Param
     * @return
     **/
    List<RoleEO> selectByRoleName(List<String> roleNames);
}
