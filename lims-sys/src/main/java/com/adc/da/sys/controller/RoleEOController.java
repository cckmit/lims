package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.NotNull;

import com.adc.da.sys.page.UserRoleEOPage;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.utils.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.sys.constant.IsBelongEnum;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.entity.UserRoleEO;
import com.adc.da.sys.page.RoleEOPage;
import com.adc.da.sys.service.RoleEOService;
import com.adc.da.sys.vo.RoleVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色管理模块相关接口.
 * 1.分页查询
 * 2.角色详情
 * 3.角色列表
 * 4.新增角色
 * 5.修改角色
 * 6.删除角色
 * 7.配置角色菜单
 * date 2018/08/15
 *
 * @author comments created by Lee Kwanho
 * @see RoleEOService
 * @see RoleEO
 * @see RoleVO
 * @see RoleEOPage
 * @see com.adc.da.sys.dao.RoleEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/sys/role")
@Api(tags = {"Sys-角色管理"})
public class RoleEOController extends BaseController<RoleEO> {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RoleEOController.class);

    /**
     * 角色相关Service
     */
    @Autowired
    private RoleEOService roleEOService;

    /**
     * dozer相关，EO间VO转换
     */
    @Autowired
    private BeanMapper beanMapper;

    /**
     * 分页查询角色列表，可以根据角色名称进行查询
     * 权限字段：sys:role:pageList
     *
     * @param pageNo   页码
     * @param pageSize 分页大小
     * @param roleName 角色名称，非id
     * @return 查询结果分页，异常会返回异常信息
     */
    @ApiOperation(value = "|RoleEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("sys:role:pageList")
    public ResponseMessage<PageInfo<RoleVO>> page(Integer pageNo, Integer pageSize, RoleVO roleVO) {
        RoleEOPage page = new RoleEOPage();
        if(com.adc.da.util.utils.StringUtils.isNotEmpty(pageNo)){
            page.setPage(pageNo);
        }
        if(com.adc.da.util.utils.StringUtils.isNotEmpty(pageSize)){
            page.setPageSize(pageSize);
        }
        try {
            List<RoleEO> rows = roleEOService.queryByPage(page,roleVO);
            int count = roleEOService.queryByCount(page);
            page.getPager().setRowCount(count);
            return Result.success("0","查询成功",beanMapper.mapPage(getPageInfo(page.getPager(), rows), RoleVO.class));
        } catch (Exception e) {
            logger.error("Role Page Error：", e);
            return Result.error("参数异常" + e.getMessage());
        }
    }

    /**
     * 根据角色id查询角色信息
     * 权限字段：sys:role:get
     *
     * @param id 角色id
     * @return 角色详情
     */
    @ApiOperation(value = "|RoleEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:role:get")
    public ResponseMessage<RoleVO> getById(@NotNull @PathVariable("id") String id) {
        RoleEO roleEO = roleEOService.getRoleWithMenus(id);
        return Result.success("0","查询成功",beanMapper.map(roleEO, RoleVO.class));
    }

    /**
     * 根据角色编码查询角色信息
     * 权限字段：sys:role:get
     *
     * @param roleCode 角色编码
     * @return 角色详情
     */
    @ApiOperation(value = "|RoleEO|详情")
    @GetMapping("getRoleByCode/{roleCode}")
    //@RequiresPermissions("sys:role:get")
    public ResponseMessage<List<RoleVO>> getRoleByCode(@NotNull @PathVariable("roleCode") String roleCode) {
        List<RoleEO> roleEO = roleEOService.getRoleByCode(roleCode);
        return Result.success("0","查询成功",beanMapper.mapList(roleEO, RoleVO.class));
    }

    /**
     * 显示角色列表，同时对该用户已有角色进行belong字段标注
     * 权限字段：sys:role:list
     *
     * @param userId 用户id
     * @return 角色列表，该用户角色身份会的belong字段会标注
     */
    @ApiOperation(value = "|RoleEO|列表")
    @GetMapping("")
    @RequiresPermissions("sys:role:list")
    public ResponseMessage<List<RoleVO>> list(String userId) {
        List<RoleVO> roleVOs = beanMapper.mapList(roleEOService.findAll(), RoleVO.class);
        if (userId != null) {
            for (RoleVO roleVO : roleVOs) {
                if (roleEOService.isBelong(userId, roleVO.getRid())) {
                    roleVO.setBelong(IsBelongEnum.BELONG.getValue());
                }
            }
        }
        return Result.success("0","查询成功",roleVOs);
    }

    /**
     * 新增角色
     * 权限字段：sys:role:save
     *
     * @param roleVO 角色信息
     * @return 角色信息
     */
    @ApiOperation(value = "|RoleEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:role:save")
    public ResponseMessage<RoleVO> create(@RequestBody RoleVO roleVO) {
        RoleEO roleEO = roleEOService.save(beanMapper.map(roleVO, RoleEO.class));
        roleVO.setRid(roleEO.getId());
        return Result.success("0","新增成功",roleVO);
    }

    /**
     * 修改角色信息
     * 权限字段：sys:role:update
     *
     * @param roleVO 角色信息
     * @return 若成功，返回角色信息；否则记录日志，返回异常信息
     */
    @ApiOperation(value = "|RoleEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:role:update")
    public ResponseMessage<RoleVO> update(@RequestBody RoleVO roleVO) {
        try {
            roleEOService.updateByPrimaryKeySelective(beanMapper.map(roleVO, RoleEO.class));
            return Result.success("0","编辑成功",roleVO);
        } catch (Exception e) {
            logger.error("角色修改异常", e);
            return Result.error("角色修改异常：" + e.getMessage());
        }

    }

    /**
     * 删除角色，逻辑删除，若角色有对应用户则不能删除
     * 权限字段：sys:role:delete
     *
     * @param id 角色id
     * @return 处理结果
     */
    @ApiOperation(value = "|RoleEO|删除")
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:role:delete")
    public ResponseMessage delete(@NotNull @PathVariable("id") String id) {
        List<UserRoleEO> list = roleEOService.getUserRoleListByRoleId(id);
        // 如果角色有对应用户，则不允许删除
        if (CollectionUtils.isNotEmpty(list)) {
            return Result.error("r0031", "该角色有对应用户，不能删除");
        }
        roleEOService.delete(id);
        return Result.success("1","删除成功",true);
    }

    /**
     * 配置角色菜单
     * 权限字段：sys:role:saveRoleMenu
     *
     * @param roleVO 角色信息
     * @return 处理结果，返回角色VO
     */
    @ApiOperation(value = "配置角色菜单|RoleEO|")
    @PostMapping("/saveRoleMenu")
    @RequiresPermissions("sys:role:saveRoleMenu")
    public ResponseMessage<RoleVO> saveRoleMenu(@RequestBody RoleVO roleVO) {
        roleEOService.saveRoleMenu(beanMapper.map(roleVO, RoleEO.class));
        return Result.success("0","配置成功",roleVO);
    }

    /**
     * 根据角色查询用户
     *
     * @param roleVO 角色信息
     * @return 处理结果，返回角色VO
     */
    @ApiOperation(value = "根据角色查询用户|RoleEO|")
    @GetMapping("/getUserListByRole")
//    @RequiresPermissions("sys:role:getUserListByRole")
    public ResponseMessage<List<UserVO>> getUserListByRole(String roleId) throws Exception {
        try{
            if(com.adc.da.util.utils.StringUtils.isNotEmpty(roleId)){
                List<UserRoleEO> userRoleEOList =  roleEOService.getUserRoleListByRoleId(roleId);
                return Result.success("0","查询成功",roleEOService.findUserList(userRoleEOList));
            }else{
                return Result.error("-1","请选择角色！");
            }
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            return Result.error("-1","查询用户失败！");
        }
    }
}

