package com.adc.da.sys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.page.UserRoleEOPage;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.utils.BeanMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.page.Pager;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.sys.dao.RoleEODao;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserRoleEO;
import com.adc.da.sys.page.RoleEOPage;
import com.adc.da.sys.vo.RoleVO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.UUID;

/**
 * <br>
 * <b>功能：</b>TS_ROLE RoleEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2017-11-06 <br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * date 2018-08-17
 *
 * @author comments created by Lee Kwanho
 * @see RoleEODao
 * @see com.adc.da.sys.controller.RoleEOController
 */
@Service("roleEOService")
@Transactional(value = "transactionManager",
    readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoleEOService extends BaseService<RoleEO, String> {

    /**
     * Dao层自动装配
     *
     * @see RoleEODao
     */
    @Autowired
    private RoleEODao dao;

    public RoleEODao getDao() {
        return dao;
    }

    @Autowired
    private UserEOService userEOService;
    /**
     * 分页查询
     * @param page
     * @param roleVO
     * @return
     */
    public List<RoleEO> queryByPage(RoleEOPage page, RoleVO roleVO){
        //配置查询条件
        page = pageSet(page,roleVO);
		return dao.queryByPage(page);
    }

    @Autowired
    private BeanMapper beanMapper;

    /**
     * 配置查询条件
     * @param page
     * @param roleVO
     * @return
     */
    public RoleEOPage pageSet(RoleEOPage page, RoleVO roleVO){
        //角色名
        if (StringUtils.isNotEmpty(roleVO.getRname())) {
            page.setName(roleVO.getRname());
            page.setNameOperator("LIKE");
        }
        //角色编码
        if (StringUtils.isNotEmpty(roleVO.getRcode())) {
            page.setRcode(roleVO.getRcode());
            page.setRcodeOperator("LIKE");
        }
        //通用查询
        if(StringUtils.isNotEmpty(roleVO.getSearchPhrase()) &&
                StringUtils.isNotEmpty(roleVO.getSearchPhrase().trim())){
            String searchPhrase = roleVO.getSearchPhrase().trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<String>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
        page.setDelFlag("0");//没有删除
        page.setPager(new Pager());

        return page;
    }

    /**
     * 保存角色信息，生成uuid，删除标识位
     *
     * @param sysRoleEO 角色信息
     * @return 完整的角色信息
     */
    public RoleEO save(RoleEO sysRoleEO) {
        sysRoleEO.setId(UUID.randomUUID10());
        sysRoleEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        dao.insertSelective(sysRoleEO);
        return sysRoleEO;
    }

    /**
     * 获取角色id
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public RoleEO getRoleWithMenus(String id) {
        return dao.getRoleWithMenus(id);
    }

    @BusinessLog(description = "获取用户角色信息")
    @Transactional(readOnly = true)
    public List<RoleEO> getSysRoleListByUserId(String userId) {
        return dao.getRoleListByUserId(userId);
    }

    /**
     * 删除角色及角色菜单关联(角色有对应用户则不能被删除)
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String roleId) {
        dao.deleteLogic(roleId);
        dao.deleteRoleMenuByRoleId(roleId);
    }

    @Transactional(readOnly = true)
    public List<RoleEO> findAll() {
        return dao.findAll();
    }

    /**
     * 查询角色所对应的用户
     *
     * @param roleId 角色id
     * @return 角色信息
     */
    @Transactional(readOnly = true)
    public List<UserRoleEO> getUserRoleListPageByRoleId(RoleEOPage roleEOPage) {
        roleEOPage.setPager(new Pager());
        return dao.getUserRoleListPageByRoleId(roleEOPage);
    }

    /**
     * 查询角色所对应的用户
     *
     * @param roleId 角色id
     * @return 角色信息
     */
    @Transactional(readOnly = true)
    public List<UserRoleEO> getUserRoleListByRoleId(String roleId) {
        return dao.getUserRoleListByRoleId(roleId);
    }

    /**
     * 根据角色用户关系表获取用户集合
     * @param userRoleEOList
     * @return
     */
    @Transactional(readOnly = true)
    public List<UserVO> findUserList(List<UserRoleEO> userRoleEOList) throws Exception {
        //用户集合
        List<UserVO> userVOList = new ArrayList();
        UserEO userEO = new UserEO();
        for(UserRoleEO userRoleEO:userRoleEOList){
            userEO = userEOService.selectByPrimaryKey(userRoleEO.getUserId());
            //EO VO转 换   并判断是否为空
            if(com.adc.da.util.utils.StringUtils.isNotEmpty(userEO)){
                userVOList.add(beanMapper.map(userEO, UserVO.class));
            }
        }
        return userVOList;
    }

    /**
     * 设置角色菜单关联
     *
     * @param roleEO 角色信息
     * @return 角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    public RoleEO saveRoleMenu(RoleEO roleEO) {
        if (CollectionUtils.isNotEmpty(roleEO.getMenuEOIdList())) {
            /*删除旧角色关联*/
            dao.deleteRoleMenuByRoleId(roleEO.getId());
            for (String menuId : roleEO.getMenuEOIdList()) {
                /*新增角色关联*/
                dao.saveRoleMenu(roleEO.getId(), menuId);
            }
        }
        return roleEO;
    }

    /**
     * 判断角色是否属于相应用户
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 返回布尔值，确认是否有该角色信息
     */
    public boolean isBelong(String userId, String roleId) {
        int count = dao.isBelong(userId, roleId);
        return count > 0;
    }

    public List<RoleEO> getRoleByCode(String roleCode) {
        return dao.getRoleByCode(roleCode);
    }
}
