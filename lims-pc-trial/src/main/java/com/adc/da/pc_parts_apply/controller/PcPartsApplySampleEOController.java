package com.adc.da.pc_parts_apply.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.log.annotation.BusinessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_parts_apply.entity.PcPartsApplySampleEO;
import com.adc.da.pc_parts_apply.page.PcPartsApplySampleEOPage;
import com.adc.da.pc_parts_apply.service.PcPartsApplySampleEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_parts_apply/pcPartsApplySample")
@Api(tags = "|PcPartsApplySampleEO-领料单申请-子表|")
public class PcPartsApplySampleEOController extends BaseController<PcPartsApplySampleEO>{

    private static final Logger logger = LoggerFactory.getLogger(PcPartsApplySampleEOController.class);

    @Autowired
    private PcPartsApplySampleEOService pcPartsApplySampleEOService;

    @BusinessLog(description = "pc领料单物料分页查询")
	@ApiOperation(value = "|PcPartsApplySampleEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_parts_apply:pcPartsApplySample:page")
    public ResponseMessage<PageInfo<PcPartsApplySampleEO>> page(PcPartsApplySampleEOPage page) throws Exception {
        List<PcPartsApplySampleEO> rows = pcPartsApplySampleEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @BusinessLog(description = "pc领料单物料列表查询")
	@ApiOperation(value = "|PcPartsApplySampleEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_parts_apply:pcPartsApplySample:list")
    public ResponseMessage<List<PcPartsApplySampleEO>> list(PcPartsApplySampleEOPage page) throws Exception {
        return Result.success(pcPartsApplySampleEOService.queryByList(page));
	}

    @BusinessLog(description = "pc领料单物料详情")
    @ApiOperation(value = "|PcPartsApplySampleEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_parts_apply:pcPartsApplySample:get")
    public ResponseMessage<PcPartsApplySampleEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcPartsApplySampleEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "pc领料单物料新增")
    @ApiOperation(value = "|PcPartsApplySampleEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_parts_apply:pcPartsApplySample:save")
    public ResponseMessage<PcPartsApplySampleEO> create(@RequestBody PcPartsApplySampleEO pcPartsApplySampleEO) throws Exception {
        pcPartsApplySampleEOService.insertSelective(pcPartsApplySampleEO);
        return Result.success(pcPartsApplySampleEO);
    }

    @BusinessLog(description = "pc领料单物料修改")
    @ApiOperation(value = "|PcPartsApplySampleEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_parts_apply:pcPartsApplySample:update")
    public ResponseMessage<PcPartsApplySampleEO> update(@RequestBody PcPartsApplySampleEO pcPartsApplySampleEO) throws Exception {
        pcPartsApplySampleEOService.updateByPrimaryKeySelective(pcPartsApplySampleEO);
        return Result.success(pcPartsApplySampleEO);
    }

    @BusinessLog(description = "pc领料单物料删除")
    @ApiOperation(value = "|PcPartsApplySampleEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_parts_apply:pcPartsApplySample:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcPartsApplySampleEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_PARTS_APPLY_SAMPLE where id = {}", id);
        return Result.success();
    }

}
