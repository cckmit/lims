package com.adc.da.pc_loan_application.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_loan_application.entity.PcCarLoanInformationEO;
import com.adc.da.pc_loan_application.page.PcCarLoanInformationEOPage;
import com.adc.da.pc_loan_application.service.PcCarLoanInformationEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_loan_application/pcCarLoanInformation")
@Api(description = "|PcCarLoanInformationEO|借车申请单-从表")
public class PcCarLoanInformationEOController extends BaseController<PcCarLoanInformationEO>{

    private static final Logger logger = LoggerFactory.getLogger(PcCarLoanInformationEOController.class);

    @Autowired
    private PcCarLoanInformationEOService pcCarLoanInformationEOService;

	@ApiOperation(value = "|PcCarLoanInformationEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_loan_application:pcCarLoanInformation:page")
    public ResponseMessage<PageInfo<PcCarLoanInformationEO>> page(PcCarLoanInformationEOPage page) throws Exception {
        List<PcCarLoanInformationEO> rows = pcCarLoanInformationEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PcCarLoanInformationEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_loan_application:pcCarLoanInformation:list")
    public ResponseMessage<List<PcCarLoanInformationEO>> list(PcCarLoanInformationEOPage page) throws Exception {
        return Result.success(pcCarLoanInformationEOService.queryByList(page));
	}

    @ApiOperation(value = "|PcCarLoanInformationEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_loan_application:pcCarLoanInformation:get")
    public ResponseMessage<PcCarLoanInformationEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcCarLoanInformationEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PcCarLoanInformationEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_loan_application:pcCarLoanInformation:save")
    public ResponseMessage<List<PcCarLoanInformationEO>> create(@RequestBody List<PcCarLoanInformationEO>  pcCarLoanInformationEOs) throws Exception {
        pcCarLoanInformationEOService.insertPcCarLoadInformationEO(pcCarLoanInformationEOs);
        return Result.success(pcCarLoanInformationEOs);
    }

    @ApiOperation(value = "|PcCarLoanInformationEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_loan_application:pcCarLoanInformation:update")
    public ResponseMessage<PcCarLoanInformationEO> update(@RequestBody PcCarLoanInformationEO pcCarLoanInformationEO) throws Exception {
        pcCarLoanInformationEOService.updateByPrimaryKeySelective(pcCarLoanInformationEO);
        return Result.success(pcCarLoanInformationEO);
    }

    @ApiOperation(value = "|PcCarLoanInformationEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_loan_application:pcCarLoanInformation:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcCarLoanInformationEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_CAR_LOAN_INFORMATION where id = {}", id);
        return Result.success();
    }

}
