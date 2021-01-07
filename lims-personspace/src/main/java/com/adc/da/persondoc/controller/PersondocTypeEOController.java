package com.adc.da.persondoc.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.login.util.UserUtils;
import com.adc.da.persondoc.service.PersondocEOService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.persondoc.entity.PersondocTypeEO;
import com.adc.da.persondoc.page.PersondocTypeEOPage;
import com.adc.da.persondoc.service.PersondocTypeEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/persondoc/persondocType")
@Api(tags = "PersonSpace-个人文档类型")
public class PersondocTypeEOController extends BaseController<PersondocTypeEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersondocTypeEOController.class);

    @Autowired
    private PersondocTypeEOService persondocTypeEOService;

    @Autowired
    private PersondocEOService persondocEOService;


	@ApiOperation(value = "|PersondocTypeEO|查询")
    @GetMapping("")
//    @RequiresPermissions("persondoc:persondocType:list")
    public ResponseMessage<List<PersondocTypeEO>> list(PersondocTypeEOPage page) throws Exception {
	    //获取当前登录人ID
        page.setCreateById(UserUtils.getUserId());
        List<PersondocTypeEO> persondocTypeEOList = persondocTypeEOService.queryByList(page);
        persondocTypeEOList.add(persondocTypeEOService.selectByPrimaryKey("1"));
        return Result.success("0","查询成功",persondocTypeEOList);
	}

    @ApiOperation(value = "|PersondocTypeEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("persondoc:persondocType:get")
    public ResponseMessage<PersondocTypeEO> find(@PathVariable String id) throws Exception {
        return Result.success("0","查询成功",persondocTypeEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PersondocTypeEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("persondoc:persondocType:save")
    public ResponseMessage<PersondocTypeEO> create(@RequestBody PersondocTypeEO persondocTypeEO) throws Exception {
        if(StringUtils.isNotEmpty(persondocTypeEO.getTypeCode())){
            //先验证code是否已存在
            int count = persondocTypeEOService.queryCountByCode(persondocTypeEO.getTypeCode());
            if(count>0){
                return Result.error("-1","类型编号已存在，请重新输入！！！");
            }
        }
	    persondocTypeEOService.insertSelective(persondocTypeEO);
        return Result.success("0","新增成功",persondocTypeEO);
    }

    @ApiOperation(value = "|PersondocTypeEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("persondoc:persondocType:update")
    public ResponseMessage<PersondocTypeEO> update(@RequestBody PersondocTypeEO persondocTypeEO) throws Exception {
        if(StringUtils.isNotEmpty(persondocTypeEO.getTypeCode())){
            //先验证code是否已存在
            int count = persondocTypeEOService.queryCountByCode(persondocTypeEO.getTypeCode());
            if(count>1){
                return Result.error("-1","类型编号已存在，请重新输入！！！");
            }
        }
	    persondocTypeEOService.updateByPrimaryKeySelective(persondocTypeEO);
        return Result.success("0","编辑成功",persondocTypeEO);
    }

    @ApiOperation(value = "|PersondocTypeEO|删除")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("persondoc:persondocType:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
	    int sonCount = persondocTypeEOService.querySonById(id);
	    int docCount = persondocEOService.queryDocByTypeId(id);
	    if(sonCount>0){
	        return Result.error("-1","该类型下有子类型，不能删除");
        }
        if(docCount>0){
            return Result.error("-1","该类型下有文档，不能删除");
        }
        persondocTypeEOService.deleteByPrimaryKey(id);
        logger.info("delete from TP_PERSONDOC_TYPE where id = {}", id);
        return Result.success("0","删除成功");
    }

    @ApiOperation(value = "|PersondocTypeEO|获取子ID")
    @GetMapping("/getAllTypes")
    public ResponseMessage<List<PersondocTypeEO>> getAllTypes (String id){
	    if(StringUtils.isNotEmpty(id)){
	        List<PersondocTypeEO> ids = persondocTypeEOService.getAllTypes(id);
            return Result.success(ids);
        }
        return  null;
    }

}
