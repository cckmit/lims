package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.vo.UserVO;
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

import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.vo.OrgVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 组织机构管理模块相关接口
 * 1.组织机构列表
 * 2.新增组织
 * 3.修改组织
 * 4.组织详情
 * 5.删除组织
 * 6.查询组织下所有用户 @author 李坤澔 date 2018-08-31
 *
 * @author 蔡建军
 * @author comments created by Lee Kwanho
 * date 2018/08/10
 * @see OrgEOService
 * @see OrgEO
 * @see OrgVO
 * @see com.adc.da.sys.dao.OrgEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/sys/org")
@Api(tags = {"Sys-组织机构管理"})
public class OrgEORestController extends BaseController<OrgEO> {

    /**
     * 日志服务
     */
    private static final Logger logger = LoggerFactory.getLogger(OrgEORestController.class);

    /**
     * 组织机构Service
     *
     * @see OrgEOService
     */
    @Autowired
    private OrgEOService orgEOService;

    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    /**
     * 组织机构列表.
     * 权限字段：sys:org:list
     *
     * @param orgName 组织名称
     * @return 组织机构信息
     */
    @ApiOperation(value = "组织机构列表|OrgEO|")
    @GetMapping("/listOrgByOrgName")
    //@RequiresPermissions("sys:org:page")
    public ResponseMessage<List<OrgVO>> listOrgByOrgName(String orgName,String orgCode,String orgType) {
        return Result.success(beanMapper.mapList(orgEOService.listOrgEOByOrgName(orgName,orgCode,orgType), OrgVO.class));
    }
    
    /**
     * 递归获取所有下级部门
     * @param id 组织机构id
     * @param self 得到的列表中是否包含自身 n-不包含 默认包含
     * @return
     */
    @ApiOperation(value = "组织机构列表|OrgEO|")
    @GetMapping("/getChildren")
    //@RequiresPermissions("sys:org:page")
    public ResponseMessage<List<OrgVO>> getChildren(String orgId) {
        return Result.success(beanMapper.mapList(orgEOService.getChildren(orgId), OrgVO.class));
    }
    

    /**
     * 新增组织.
     * 权限字段：sys:org:save
     *
     * @param orgVO 组织信息
     * @return 返回结果，若组织名称为空返回r0014，若组织名存在，返回r0015
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "|OrgEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:org:save")
    public ResponseMessage<OrgVO> create(@RequestBody OrgVO orgVO) {
        if (StringUtils.isBlank(orgVO.getName())) {
            return Result.error("r0014", "组织机构名称不能为空");
        } else if (orgEOService.getOrgEOByNameAndPid(orgVO.getName(), orgVO.getParentId()) != null) {
            return Result.error("r0015", "组织机构名称已存在");
        } else if (com.adc.da.util.utils.StringUtils.isNotEmpty(orgVO.getOrgCode())){
            List<OrgEO> list = orgEOService.getOrgEOByOrgCode(orgVO.getOrgCode());
            if(!list.isEmpty()){
                return Result.error("r0015", "组织机构代码已存在");
            }
        }
        OrgEO orgEO = orgEOService.save(beanMapper.map(orgVO, OrgEO.class));
        return Result.success("0",orgEO.getName()+"添加成功！",beanMapper.map(orgEO, OrgVO.class));
    }

    /**
     * 修改组织.
     * 权限字段：sys:org:update
     *
     * @param orgVO 组织信息
     * @return 修改结果
     */
    @ApiOperation(value = "|OrgEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:org:update")
    public ResponseMessage<OrgVO> update(@RequestBody OrgVO orgVO) {
        try {
            orgEOService.updateByPrimaryKeySelective(beanMapper.map(orgVO, OrgEO.class));
        } catch (Exception e) {
            logger.error("修改组织失败", e);
            return Result.error("-1", "修改组织失败:", orgVO);
        }

        return Result.success("0",orgVO.getName()+"修改成功！",orgVO);
    }

    /**
     * 显示组织详情
     * 权限字段：sys:org:get
     *
     * @param id 组织机构id
     * @return 处理结果
     */
    @ApiOperation(value = "|OrgEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:org:get")
    public ResponseMessage<OrgVO> getById(@NotNull @PathVariable("id") String id) {
        OrgVO orgVO = beanMapper.map(orgEOService.getOrgEOById(id), OrgVO.class);
        return Result.success(orgVO);
    }

    /**
     * 删除组织机构
     * 权限字段： sys:org:delete
     *
     * @param id 组织机构id
     * @return 处理结果，若组织下有子机构，返回r0031
     */
    @ApiOperation(value = "|OrgEO|删除")
    @DeleteMapping("/delete/{id}")
    @RequiresPermissions("sys:org:delete")
    public ResponseMessage delete(@NotNull @PathVariable("id") String id) {
        List<OrgEO> list = orgEOService.getOrgEOByPid(id);
        // 如果该组织机构下有子机构，则不允许删除
        if (list != null && !list.isEmpty()) {
            return Result.error("r0031", "该组织机构下有子机构，不能删除");
        }
        orgEOService.delete(id);
        return Result.success("0","删除成功！");
    }

    /**
     * 查询组织下所有用户，包括下级组织。
     *
     * @param orgId 根据组织id查询用户信息
     * @return 用户列表，包含角色信息
     * @author Lee Kwanho 李坤澔
     * date 2018-08-31
     **/
    @ApiOperation(value = "组织用户列表|OrgEO|")
    @GetMapping("/listUserEOByOrgId")
    @RequiresPermissions("sys:org:list")
    public ResponseMessage<List<UserVO>> listUserEOByOrgId(String orgId) {
        List<UserEO> userEOList = orgEOService.listUserEOByOrgId(orgId);
        return Result.success(beanMapper.mapList(userEOList, UserVO.class));

    }
    
    /**
     * 获取父机构信息,用于判断当前机构属于PV还是CV还是EV
     * @Title: getByOrgId
     * @param orgId
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年1月9日
     */
    @ApiOperation(value = "获取父机构信息,用于判断当前机构属于PV还是CV")
    @GetMapping("/getByOrgId")
    public ResponseMessage getByOrgId(String orgId) {
    	try {
    		return Result.success("0", "查询成功!", orgEOService.getByOrgId(orgId));
		} catch (Exception e) {
			logger.error("-1",e.getMessage());
            return Result.error("-1", "查询失败!");
		}
    }
    
    

}
