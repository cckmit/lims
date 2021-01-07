package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.MenuEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>TS_MENU MenuEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 *
 * @author comments created by Lee Kwanho
 * @see mybatis.mapper.sys
 */
public interface MenuEODao extends BaseDao<MenuEO> {

    /**
     * 获取所有的菜单
     *
     * @return 所有菜单
     */
    List<MenuEO> findAll();

    /**
     * 获取角色对应的所有菜单
     *
     * @param roleId 角色ID
     * @return 角色对应的所有菜单
     */
    List<MenuEO> listMenuEOByRoleID(String roleId);

    /**
     * 获取用户的所有菜单
     *
     * @param userId 用户ID
     * @return 用户的所有菜单
     */
    List<MenuEO> listMenuEOByUserId(String userId);

    /**
     * 获取当前节点的所有子节点
     *
     * @param parentId 当前节点
     * @return 返回所有子节点
     */
    List<MenuEO> getChildMenus(String parentId);

    /**
     * 逻辑删除所有菜单
     *
     * @param ids 菜单ID
     */
    void deleteMenuLogic(String[] ids);

    /**
     * 删除角色与当前菜单以及子菜单关系
     *
     * @param ids 菜单id组
     */
    void deleteRoleMenus(String[] ids);

    /**
     * 判定菜单是否属于角色
     *
     * @param roleId 角色ID
     * @param menuId 菜单ID
     * @return 如果>0说明存在记录，菜单属于角色
     */
    int isBelong(@Param("roleId") String roleId, @Param("menuId") String menuId);

    /**
     * Dao 根据菜单名进行菜单查询，返回关联菜单以及父级菜单信息
     *
     * @param menuName 菜单名
     * @return 查询结果
     * @author Lee Kwanho 李坤澔
     * date 2018-08-22
     **/
      List<MenuEO> listMenuEOByMenuName(@Param("menuName") String menuName);

      List<String> findMenuIds(String reloId);

}
