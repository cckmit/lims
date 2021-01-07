package com.adc.da.project.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.log.annotation.BusinessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.project.entity.ItemsDetailsEO;
import com.adc.da.project.page.ItemsDetailsEOPage;
import com.adc.da.project.service.ItemsDetailsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/project/itemsDetails")
@Api(tags = "Sup-供应商项目管理-检验项目")
public class ItemsDetailsEOController extends BaseController<ItemsDetailsEO>{

    private static final Logger logger = LoggerFactory.getLogger(ItemsDetailsEOController.class);

    @Autowired
    private ItemsDetailsEOService itemsDetailsEOService;

    @BusinessLog(description = "供应商项目详情分页查询")
	@ApiOperation(value = "|ItemsDetailsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("project:itemsDetails:page")
    public ResponseMessage<PageInfo<ItemsDetailsEO>> page(ItemsDetailsEOPage page) throws Exception {
        List<ItemsDetailsEO> rows = itemsDetailsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @BusinessLog(description = "供应商项目详情列表查询")
	@ApiOperation(value = "|ItemsDetailsEO|查询")
    @GetMapping("")
    @RequiresPermissions("project:itemsDetails:list")
    public ResponseMessage<List<ItemsDetailsEO>> list(ItemsDetailsEOPage page) throws Exception {
        return Result.success(itemsDetailsEOService.queryByList(page));
	}

    @BusinessLog(description = "供应商项目详情")
    @ApiOperation(value = "|ItemsDetailsEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("project:itemsDetails:get")
    public ResponseMessage<ItemsDetailsEO> find(@PathVariable String id) throws Exception {
        return Result.success(itemsDetailsEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "供应商项目新增")
    @ApiOperation(value = "|ItemsDetailsEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("project:itemsDetails:save")
    public ResponseMessage<ItemsDetailsEO> create(@RequestBody ItemsDetailsEO itemsDetailsEO) throws Exception {
        itemsDetailsEOService.insertSelective(itemsDetailsEO);
        return Result.success(itemsDetailsEO);
    }

    @BusinessLog(description = "供应商项目修改")
    @ApiOperation(value = "|ItemsDetailsEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("project:itemsDetails:update")
    public ResponseMessage<ItemsDetailsEO> update(@RequestBody ItemsDetailsEO itemsDetailsEO) throws Exception {
        itemsDetailsEOService.updateByPrimaryKeySelective(itemsDetailsEO);
        return Result.success(itemsDetailsEO);
    }

    @BusinessLog(description = "供应商项目删除")
    @ApiOperation(value = "|ItemsDetailsEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("project:itemsDetails:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        itemsDetailsEOService.deleteByPrimaryKey(id);
        logger.info("delete from SUP_ITEMS_DETAILS where id = {}", id);
        return Result.success();
    }

}
