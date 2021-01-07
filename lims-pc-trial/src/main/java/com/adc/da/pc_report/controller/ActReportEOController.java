package com.adc.da.pc_report.controller;


import com.adc.da.acttask.entity.ActTaskEO;
import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_report.service.PcReportEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${restPath}/pc_act_report/acttask")
@Api(tags = "pc_act_report-报告审批模块")
public class ActReportEOController extends BaseController<ActTaskEO> {

    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ActReportEOController.class);

    @Autowired
    private PcReportEOService pcReportEOService;


    @BusinessLog(description = "报告审批分页查询")
    @ApiOperation(value = "|PcReportEO|报告审批分页查询")
    @GetMapping("/reportTaskPage")
//    @RequiresPermissions("ActTaskEO:ActTaskEO:reportTaskPage")
    public ResponseMessage<PageInfo<ActTaskEO>> reportTaskPage(ActTaskEOPage page, String searchField){
        try{
            List<ActTaskEO> actTaskEOList = pcReportEOService.reportTaskPage(page, searchField);
            page.getPager().setRowCount(pcReportEOService.reportTaskCount(page));
            return Result.success("0","查询成功",getPageInfo(page.getPager(), actTaskEOList));
        }catch (Exception e){
            logger.error("-1", e.getMessage());
            return Result.error("-1", "查询失败！");
        }
    }

}
