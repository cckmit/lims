package com.adc.da.materiel.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.materiel.entity.InventoryRecordEO;
import com.adc.da.materiel.page.InventoryRecordEOPage;
import com.adc.da.materiel.service.InventoryRecordEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/materiel/inventoryRecord")
@Api(description = "|InventoryRecordEO|")
public class InventoryRecordEOController extends BaseController<InventoryRecordEO>{

    private static final Logger logger = LoggerFactory.getLogger(InventoryRecordEOController.class);

    @Autowired
    private InventoryRecordEOService inventoryRecordEOService;

	@ApiOperation(value = "|InventoryRecordEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("materiel:inventoryRecord:page")
    public ResponseMessage<PageInfo<InventoryRecordEO>> page(InventoryRecordEOPage page) throws Exception {
        List<InventoryRecordEO> rows = inventoryRecordEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|InventoryRecordEO|查询")
    @GetMapping("")
    @RequiresPermissions("materiel:inventoryRecord:list")
    public ResponseMessage<List<InventoryRecordEO>> list(InventoryRecordEOPage page) throws Exception {
        return Result.success(inventoryRecordEOService.queryByList(page));
	}

    @ApiOperation(value = "|InventoryRecordEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("materiel:inventoryRecord:get")
    public ResponseMessage<InventoryRecordEO> find(@PathVariable String id) throws Exception {
        return Result.success(inventoryRecordEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|InventoryRecordEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("materiel:inventoryRecord:save")
    public ResponseMessage<InventoryRecordEO> create(@RequestBody InventoryRecordEO inventoryRecordEO) throws Exception {
        inventoryRecordEOService.insertSelective(inventoryRecordEO);
        return Result.success(inventoryRecordEO);
    }

    @ApiOperation(value = "|InventoryRecordEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("materiel:inventoryRecord:update")
    public ResponseMessage<InventoryRecordEO> update(@RequestBody InventoryRecordEO inventoryRecordEO) throws Exception {
        inventoryRecordEOService.updateByPrimaryKeySelective(inventoryRecordEO);
        return Result.success(inventoryRecordEO);
    }

    @ApiOperation(value = "|InventoryRecordEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("materiel:inventoryRecord:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        inventoryRecordEOService.deleteByPrimaryKey(id);
        logger.info("delete from RES_INVENTORY_RECORD where id = {}", id);
        return Result.success();
    }

}
