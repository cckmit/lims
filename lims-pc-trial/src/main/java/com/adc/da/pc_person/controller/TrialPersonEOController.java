package com.adc.da.pc_person.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.log.annotation.BusinessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_person.page.TrialPersonEOPage;
import com.adc.da.pc_person.service.TrialPersonEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_person/trialPerson")
@Api(tags = "PcTrialTask-乘用车/商用车试验任务模块")
public class TrialPersonEOController extends BaseController<TrialPersonEO>{

    private static final Logger logger = LoggerFactory.getLogger(TrialPersonEOController.class);

    @Autowired
    private TrialPersonEOService trialPersonEOService;

    @BusinessLog(description = "pc委托人员分页查询")
	@ApiOperation(value = "|TrialPersonEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_person:trialPerson:page")
    public ResponseMessage<PageInfo<TrialPersonEO>> page(TrialPersonEOPage page) throws Exception {
        List<TrialPersonEO> rows = trialPersonEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @BusinessLog(description = "pc委托人员列表查询")
	@ApiOperation(value = "|TrialPersonEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_person:trialPerson:list")
    public ResponseMessage<List<TrialPersonEO>> list(TrialPersonEOPage page) throws Exception {
        return Result.success(trialPersonEOService.queryByList(page));
	}

    @BusinessLog(description = "pc委托人员详情")
    @ApiOperation(value = "|TrialPersonEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_person:trialPerson:get")
    public ResponseMessage<TrialPersonEO> find(@PathVariable String id) throws Exception {
        return Result.success(trialPersonEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "pc委托人员xinzeng ")
    @ApiOperation(value = "|TrialPersonEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_person:trialPerson:save")
    public ResponseMessage<TrialPersonEO> create(@RequestBody TrialPersonEO trialPersonEO) throws Exception {
        trialPersonEOService.insertSelective(trialPersonEO);
        return Result.success(trialPersonEO);
    }

    @BusinessLog(description = "pc委托人员修改")
    @ApiOperation(value = "|TrialPersonEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_person:trialPerson:update")
    public ResponseMessage<TrialPersonEO> update(@RequestBody TrialPersonEO trialPersonEO) throws Exception {
        trialPersonEOService.updateByPrimaryKeySelective(trialPersonEO);
        return Result.success(trialPersonEO);
    }

    @BusinessLog(description = "pc委托人员删除")
    @ApiOperation(value = "|TrialPersonEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_person:trialPerson:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        trialPersonEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_TRIAL_PERSON where id = {}", id);
        return Result.success();
    }

}
