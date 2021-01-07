package com.adc.da.trial_report.controller;

import java.util.List;

import com.adc.da.trial_report.page.TrialReportProcessEOPage;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.utils.BeanMapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.trial_report.entity.TrialReportProcessEO;
import com.adc.da.trial_report.service.TrialReportProcessEOService;
import com.adc.da.trial_report.vo.TrialScfeduleVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@RequestMapping("${restPath}/report/schedule")
@Api(tags = "TrialReport-试验报告模块")
@SuppressWarnings("all")
public class TrialReportProcessEOController extends BaseController<TrialScfeduleVO> {
	
	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(TrialReportProcessEOController.class);

	@Autowired
	private TrialReportProcessEOService trialReportProcessEOService;

	@Autowired
	private BeanMapper beanMapper;
    
	@ApiOperation(value = "|任务执行--进度日志")
	@BusinessLog(description = "任务执行--进度日志")
	@GetMapping("/scheduleList")
	@RequiresPermissions("report:trialReport:scheduleList")
	public ResponseMessage<PageInfo<TrialScfeduleVO>> scheduleList(
	        @ApiParam("试验任务检验项目ID") @RequestParam(value = "trialtaskInsproId") String trialtaskInsproId,
			@ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
			@ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
		try {
			TrialReportProcessEOPage eoPage = new TrialReportProcessEOPage();
			eoPage.setPage(page);
			eoPage.setPageSize(pageSize);
			eoPage.setTrialtaskInsproId(trialtaskInsproId);
			List<TrialScfeduleVO> rows = trialReportProcessEOService.getTrialReportProcessByPage(eoPage);
			return Result.success(beanMapper.mapPage(getPageInfo(eoPage.getPager(), rows), TrialScfeduleVO.class));
		}catch(Exception e){
			logger.error(e.getMessage());
			return Result.error("-1","查询失败！");
		}
	}

    @ApiOperation(value = "|任务执行--查询当前进度")
    @BusinessLog(description = "任务执行--查询当前进度")
    @GetMapping("/schedule/{trialtaskInsproId}")
    @RequiresPermissions("report:trialReport:schedule")
    public ResponseMessage<TrialScfeduleVO> schedule(
            @ApiParam("试验任务检验项目ID") @PathVariable(value = "trialtaskInsproId") String trialtaskInsproId
    ) {
        try {
            return Result.success(trialReportProcessEOService.selectSchedule(trialtaskInsproId));
        }catch(Exception e){
            logger.error(e.getMessage());
            return Result.error("-1","查询失败！");
        }
    }
}
