package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.adc.da.sys.constant.IsBelongEnum;
import com.adc.da.sys.vo.MenuVO;
import com.adc.da.util.utils.BeanMapper;

import com.adc.da.util.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.MenuEO;
import com.adc.da.sys.service.MenuEOService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 菜单管理模块相关接口.
 * 1.菜单详情
 * 2.新增菜单
 * 3.显示角色菜单
 * 4.修改菜单
 * 5.删除菜单
 * 6.显示用户菜单
 * 7.显示用户菜单-layui版
 * date 2018/08/10
 *
 * @author comments created by Lee Kwanho
 * @see MenuEOService
 * @see MenuVO
 * @see MenuEO
 * @see com.adc.da.sys.dao.MenuEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/sys/menu")
@Api(tags = {"Sys-菜单管理"})
public class MenuEOController extends BaseController<MenuEO> {

    /**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(MenuEOController.class);

    /**
     * 1.MenuEOService  用于菜单相关service方法.
     *
     * @see MenuEOService
     */
    @Autowired
    private MenuEOService menuEOService;

    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    /**
     * 显示菜单详情，需要输入菜单id.
     * 权限字段：sys:menu:get
     *
     * @param id 菜单ID
     * @return 成功返回菜单详情，失败返回异常信息
     */
    @ApiOperation(value = "|MenuEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:menu:get")
    public ResponseMessage<MenuVO> find(@NotNull @PathVariable("id") final String id) {

        try {
            return Result.success(beanMapper.map(menuEOService.selectByPrimaryKey(id), MenuVO.class));
        } catch (Exception e) {
            logger.error("菜单详情获取失败", e);
            return Result.error("表单详情获取失败:");
        }

    }

    /**
     * 新增菜单.若菜单名为空之间返回error.
     * 权限字段：sys:menu:save
     *
     * @param menuVO 菜单单信息
     * @return 成功返回菜单信息，失败返回异常信息
     */
    @ApiOperation(value = "|MenuEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:menu:save")
    public ResponseMessage<MenuVO> create(@RequestBody final MenuVO menuVO) {

        if (StringUtils.isEmpty(menuVO.getName())) {
            return Result.error("菜单名不能为空");
        }
        try {
            MenuEO menuEO = menuEOService.insertMenu(beanMapper.map(menuVO, MenuEO.class));
            return Result.success(beanMapper.map(menuEO, MenuVO.class));
        } catch (Exception e) {
            logger.error("新增菜单失败", e);
            return Result.error("新增菜单失败:");
        }

    }

    /**
     * 显示角对应菜单.
     * 权限字段：sys:menu:list
     * 路径"/"
     *
     * @param roleId 角色id
     * @return 菜单列表
     */
    @ApiOperation(value = "|MenuEO|列表 --> 角色对应的菜单")
    @GetMapping
    @RequiresPermissions("sys:menu:list")
    public ResponseMessage<List<MenuVO>> list(final String roleId) {
        List<MenuVO> menuVOs = beanMapper.mapList(menuEOService.findAll(), MenuVO.class);
        List<String> menuIds = new ArrayList<>();
        if (StringUtils.isNotEmpty(roleId)) {
            menuIds = menuEOService.findMenuIds(roleId);
            for (MenuVO menuVO : menuVOs) {
                if (menuIds.contains(menuVO.getId())) {
                    menuVO.setBelong(IsBelongEnum.BELONG.getValue());
                }
            }
        }
        return Result.success(menuVOs);
    }

    /**
     * 修改菜单信息.
     * 权限字段：sys:menu:update
     *
     * @param menuVO 菜单参数
     * @return 修改结果，和改动后的参数
     */
    @ApiOperation(value = "|MenuEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:menu:update")
    public ResponseMessage<MenuVO> update(@RequestBody final MenuVO menuVO) {
        try {
            menuEOService.updateMenu(menuVO);
        } catch (Exception e) {
            logger.error("修改菜单失败", e);
            return Result.error("-1", "修改菜单失败:", menuVO);
        }
        return Result.success(menuVO);
    }

    /**
     * 删除菜单，可删除多个菜单.
     * 权限字段：sys:menu:delete
     *
     * @param ids 菜单组
     * @return 处理结果
     */
    @ApiOperation(value = "|MenuEO|删除")
    @DeleteMapping("/{ids}")
    @RequiresPermissions("sys:menu:delete")
    public ResponseMessage delete(@NotNull @PathVariable("ids") final String[] ids) {

        menuEOService.delete(ids);
        logger.info("log===>delete from TS_MENU where ids = {}", ids);
        return Result.success();
    }

    /**
     * 获取用户菜单列表.
     * 权限字段：sys:menu:listMenuByUserId
     *
     * @param userId 用户id
     * @return 菜单列表
     */
    @ApiOperation(value = "用户菜单列表|MenuEO|")
    @GetMapping(value = "/listMenuByUserId/{userId}")
    @RequiresPermissions("sys:menu:listMenuByUserId")
    public ResponseMessage<List<MenuVO>> listMenuByUserId(@NotNull @PathVariable("userId") final String userId) {
        return Result.success(beanMapper.mapList(menuEOService.listMenuEOByUserId(userId), MenuVO.class));
    }

    /**
     * 获取用户菜单列表，用于layui.
     * 权限字段：sys:menu:listMenuByUserId
     *
     * @param userId 用户id
     * @return 菜单
     * @author 梅俊宇
     */
    @ApiOperation(value = "用户菜单列表layui|MenuEO|")
    @GetMapping(value = "/listMenuByUserIdNew/{userId}", params = "version=2")
    @RequiresPermissions("sys:menu:listMenuByUserId")
    public ResponseMessage<HashMap<String, Object>> newListMenu(@NotNull @PathVariable("userId") final String userId) {

        List<MenuVO> menus = beanMapper.mapList(menuEOService.listMenuEOByUserId(userId), MenuVO.class);
        //同一父级的菜单整合
        HashMap<String, List<MenuVO>> listMenuByParent = new HashMap<>();
        for (MenuVO menuVO : menus) {
            String parentId = menuVO.getParentId();
            if (listMenuByParent.containsKey(parentId)) {
                List<MenuVO> list = listMenuByParent.get(parentId);
                list.add(menuVO);
                listMenuByParent.put(parentId, list);
            } else {
                List<MenuVO> list = new ArrayList<>();
                list.add(menuVO);
                listMenuByParent.put(parentId, list);
            }
        }
        HashMap<String, Object> menu = new HashMap<>();
        MenuVO first = listMenuByParent.get("0").get(0);
        menu.put("text", first.getName());
        menu.put("group", true);
        // 递归遍历一个父标签下面的所有儿子标签
        menu.put("children", menuEOService.recursionList(listMenuByParent.get(first.getId()), listMenuByParent));
        List<HashMap<String, Object>> menuList = new ArrayList<>();
        menuList.add(menu);

        HashMap<String, Object> app = new HashMap<>();
        app.put("name", "Typerx");
        app.put("description", "Ng-zorro admin panel front-end framework");
        HashMap<String, Object> result = new HashMap<>();
        result.put("app", app);
        result.put("menu", menuList);
        return Result.success(result);
    }

    /**
     * Controller 根据菜单名进行菜单查询，返回关联菜单以及父级菜单信息
     *
     * @param menuName 菜单名
     * @return 菜单信息
     * @author Lee Kwanho 李坤澔
     * date 2018-08-22
     **/
    @ApiOperation(value = "查询菜单|MenuEO|")
    @GetMapping("/listMenuByName")
    @RequiresPermissions("sys:menu:listByName")
    public ResponseMessage<List<MenuVO>> listOrgByOrgName(String menuName) {
        return Result.success(beanMapper.mapList(menuEOService.listMenuEOByMenuName(menuName), MenuVO.class));
    }

}
