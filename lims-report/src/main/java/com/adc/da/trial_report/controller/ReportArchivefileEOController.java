package com.adc.da.trial_report.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.trial_report.entity.ReportArchivefileEO;
import com.adc.da.trial_report.page.ReportArchivefileEOPage;
import com.adc.da.trial_report.service.ReportArchivefileEOService;
import com.adc.da.trial_report.vo.ReportArchivefileVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * EV-发动机试验管理-试验报告模块
 * @author Administrator
 * @date 2019年8月28日
 */
@RestController
@RequestMapping("/${restPath}/report/archive")
@Api(tags = "TrialReport-试验报告模块")
@SuppressWarnings("all")
public class ReportArchivefileEOController extends BaseController<ReportArchivefileEO> {

	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(ReportArchivefileEOController.class);
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private ReportArchivefileEOService reportArchivefileEOService;
    
    /**
     * 根据报告id查询该报告查询下的所有附件
    * @Title：findList
    * @param reportId
    * @return
    * @return: ResponseMessage<List<ReportArchivefileVO>>
    * @author： ljy  
    * @date： 2019年8月28日
     */
    @ApiOperation(value = "|试验报告-报告文件查询")
    @BusinessLog(description = "试验报告-报告文件查询")
    @GetMapping("/list")
    @RequiresPermissions("report:archive:list")
	public ResponseMessage<PageInfo<ReportArchivefileVO>> findList(ReportArchivefileEOPage page){
		try {
			//根据报告id查询该报告附件
			List<ReportArchivefileEO> rows = reportArchivefileEOService.queryByPage(page);
			page.getPager().setPageCount(reportArchivefileEOService.queryByCount(page));
			return Result.success("0", "查询成功", beanMapper.mapPage(
					getPageInfo(page.getPager(), rows), ReportArchivefileVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
	}
    
	/**
	 * 报告文件归档操作
	* @Title：reportFileArchive
	* @param reportArchivefileVO
	* @return
	* @return: ResponseMessage<ReportArchivefileVO>
	* @author： ljy  
	* @date： 2019年8月28日
	 */
    @ApiOperation(value = "|试验报告-报告归档操作")
    @BusinessLog(description = "试验报告-报告归档操作")
    @PostMapping(path = "/save")
    @RequiresPermissions("report:archive:operate")
    public ResponseMessage<ReportArchivefileVO> reportFileArchive(
    		@RequestBody ReportArchivefileVO reportArchivefileVO) {
    	try {
    		ReportArchivefileEO reportArchivefileEO = reportArchivefileEOService.
    				reportFileArchive(beanMapper.map(reportArchivefileVO, ReportArchivefileEO.class));
    		return Result.success("0", "操作成功", beanMapper.map(reportArchivefileEO, ReportArchivefileVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","操作失败!");
		}
    }
    
    /**
     * 报告文件取消归档操作
    * @Title：reportFileCancelArchive
    * @param reportArchivefileVO
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年9月26日
     */
    @ApiOperation(value = "|试验报告-报告文件取消归档操作")
    @BusinessLog(description = "试验报告-报告文件取消归档操作")
    @PostMapping(path = "/cancel")
    @RequiresPermissions("report:archive:operate")
    public ResponseMessage reportFileCancelArchive(@RequestBody ReportArchivefileVO reportArchivefileVO) {
    	try {
    		ReportArchivefileEO reportArchivefileEO = reportArchivefileEOService.
    				reportFileCancelArchive(beanMapper.map(reportArchivefileVO, ReportArchivefileEO.class));
    		return Result.success("0", "操作成功", beanMapper.map(reportArchivefileEO, ReportArchivefileVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","操作失败!");
		}
    }
    
    /**
     * 根据附件id 获取路径
    * @Title：getPath
    * @return
    * @return: ResponseMessage<String>
    * @author： ljy  
    * @date： 2019年9月26日
     */
    @ApiOperation(value = "|试验报告-获取文件路径")
    @BusinessLog(description = "试验报告-获取文件路径")
    @PostMapping(path = "/getPath")
    @RequiresPermissions("report:archive:operate")
    public ResponseMessage<String> getPath(String attachId, String isWaterMark){
    	try {
    		//根据附件id 获取文件路径
    		return Result.success("0", "查询成功",reportArchivefileEOService.getPath(attachId, isWaterMark));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
}
