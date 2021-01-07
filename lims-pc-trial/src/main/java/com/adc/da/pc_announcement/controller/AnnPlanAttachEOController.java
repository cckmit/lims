package com.adc.da.pc_announcement.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_announcement.entity.AnnPlanAttachEO;
import com.adc.da.pc_announcement.page.AnnPlanAttachEOPage;
import com.adc.da.pc_announcement.service.AnnPlanAttachEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_announcement/annPlanAttach")
public class AnnPlanAttachEOController extends BaseController<AnnPlanAttachEO>{

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanAttachEOController.class);

    @Autowired
    private AnnPlanAttachEOService annPlanAttachEOService;

	@ApiOperation(value = "|AnnPlanAttachEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_announcement:annPlanAttach:page")
    public ResponseMessage<PageInfo<AnnPlanAttachEO>> page(AnnPlanAttachEOPage page) throws Exception {
        List<AnnPlanAttachEO> rows = annPlanAttachEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|AnnPlanAttachEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_announcement:annPlanAttach:list")
    public ResponseMessage<List<AnnPlanAttachEO>> list(AnnPlanAttachEOPage page) throws Exception {
        return Result.success(annPlanAttachEOService.queryByList(page));
	}

    @ApiOperation(value = "|AnnPlanAttachEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlanAttach:get")
    public ResponseMessage<AnnPlanAttachEO> find(@PathVariable String id) throws Exception {
        return Result.success(annPlanAttachEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|AnnPlanAttachEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlanAttach:save")
    public ResponseMessage<AnnPlanAttachEO> create(@RequestBody AnnPlanAttachEO annPlanAttachEO) throws Exception {
        annPlanAttachEOService.insertSelective(annPlanAttachEO);
        return Result.success(annPlanAttachEO);
    }

    @ApiOperation(value = "|AnnPlanAttachEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlanAttach:update")
    public ResponseMessage<AnnPlanAttachEO> update(@RequestBody AnnPlanAttachEO annPlanAttachEO) throws Exception {
        annPlanAttachEOService.updateByPrimaryKeySelective(annPlanAttachEO);
        return Result.success(annPlanAttachEO);
    }

    @ApiOperation(value = "|AnnPlanAttachEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlanAttach:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        annPlanAttachEOService.deleteByPrimaryKey(id);
        logger.info("delete from ANN_PLAN_ATTACH where id = {}", id);
        return Result.success();
    }

}
