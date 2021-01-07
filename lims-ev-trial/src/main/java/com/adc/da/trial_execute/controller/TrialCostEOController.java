package com.adc.da.trial_execute.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.exception.AdcDaBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.trial_execute.entity.TrialCostEO;
import com.adc.da.trial_execute.page.TrialCostEOPage;
import com.adc.da.trial_execute.service.TrialCostEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/${restPath}/evTrial/trialCost")
@Api(tags = "EvTrialTask-发动机试验任务执行模块")
public class TrialCostEOController extends BaseController<TrialCostEO>{

    private static final Logger logger = LoggerFactory.getLogger(TrialCostEOController.class);

    @Autowired
    private TrialCostEOService trialCostEOService;

    @BusinessLog(description = "发动机模块费用分页管理查询")
	@ApiOperation(value = "|TrialCostEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("trial_execute:trialCost:page")
    public ResponseMessage<PageInfo<TrialCostEO>> page(TrialCostEOPage page) throws Exception {
        try{
            List<TrialCostEO> rows = trialCostEOService.queryByVOPage(page);
            page.getPager().setRowCount(trialCostEOService.queryByVOCount(page));
            return Result.success("0","查询成功！", getPageInfo(page.getPager(), rows));
        }catch (AdcDaBaseException e){
            logger.error(e.getMessage());
            return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "发动机模块费用管理列表查询")
	@ApiOperation(value = "|TrialCostEO|查询")
    @GetMapping("")
    @RequiresPermissions("trial_execute:trialCost:list")
    public ResponseMessage<List<TrialCostEO>> list(TrialCostEOPage page) throws Exception {
        return Result.success("0","查询成功！",trialCostEOService.queryByList(page));
	}

    @BusinessLog(description = "发动机模块费用管理详情")
    @ApiOperation(value = "|TrialCostEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("trial_execute:trialCost:get")
    public ResponseMessage<TrialCostEO> find(@PathVariable String id) throws Exception {
        return Result.success("0","查询成功！",trialCostEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "发动机模块费用管理新增")
    @ApiOperation(value = "|TrialCostEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("trial_execute:trialCost:save")
    public ResponseMessage<TrialCostEO> create(@RequestBody TrialCostEO trialCostEO) throws Exception {
        trialCostEOService.insertSelective(trialCostEO);
        return Result.success("0","新增成功！",trialCostEO);
    }

    @BusinessLog(description = "发动机模块费用管理修改")
    @ApiOperation(value = "|TrialCostEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("trial_execute:trialCost:update")
    public ResponseMessage<TrialCostEO> update(@RequestBody TrialCostEO trialCostEO) throws Exception {
        trialCostEOService.updateByPrimaryKeySelective(trialCostEO);
        return Result.success("0","更新成功！",trialCostEO);
    }

    @BusinessLog(description = "发动机模块费用管理导出")
    @ApiOperation(value = "|TrialCostEO|导出")
    @GetMapping("/exportExcel")
//    @RequiresPermissions("trial_execute:trialCost:exportExcel")
    public ResponseMessage<String> exportExcel(HttpServletResponse response, HttpServletRequest request, TrialCostEOPage page){
	    try{
            trialCostEOService.exportExcel(request, response, page);
            return Result.success("0","导出成功！");
        }catch (Exception e){
	        logger.error(e.getMessage());
	        return Result.error("-1","导出失败！");
        }

    }

}
