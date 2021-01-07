package com.adc.da.trial_execute.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.trial_execute.entity.TrialDataDetailEO;
import com.adc.da.trial_execute.entity.TrialDataEO;
import com.adc.da.trial_execute.page.TrialDataDetailEOPage;
import com.adc.da.trial_execute.page.TrialDataEOPage;
import com.adc.da.trial_execute.service.TrialDataDetailEOService;
import com.adc.da.trial_execute.vo.TrialDataDetailVO;
import com.adc.da.trial_execute.vo.TrialDataVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 试验数据记录
 * @author Administrator
 * @date 2019年9月16日
 */
@RestController
@RequestMapping("/${restPath}/evTrial/dataDetail")
@Api(tags = "EvTrialTask-发动机试验任务执行模块")
@SuppressWarnings("all")
public class TrialDataDetailEOController extends BaseController<TrialDataDetailEO> {
	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(TrialDataDetailEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private TrialDataDetailEOService trialDataDetailEOService;
    
    /**
     * 试验数据详情-分页查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<PageInfo<TrialDataDetailVO>>
    * @author： ljy  
    * @date： 2019年9月16日
     */
    @ApiOperation(value = "|试验数据详情-分页查询")
    @BusinessLog(description = "试验数据详情-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("evTrial:dataDetail:page")
    public ResponseMessage<PageInfo<TrialDataDetailVO>> page(TrialDataDetailEOPage page){
    	try {
    		//查询
			List<TrialDataDetailEO> rows =  trialDataDetailEOService.page(page);
			//设置总条数
			Integer rowsCount = trialDataDetailEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					TrialDataDetailVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    /**
     * 试验数据详情-导出
    * @Title：exportTrialDataDetail
    * @param trialDataDetailVO
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年9月16日
     */
    @ApiOperation(value = "|试验数据详情-导出")
    @BusinessLog(description = "试验数据详情-导出")
    @GetMapping("/export")
    @RequiresPermissions("evTrial:dataDetail:export")
    public ResponseMessage exportTrialDataDetail(
    		TrialDataDetailEOPage page,
    		HttpServletResponse response, HttpServletRequest request) {
    	try {
    		//导出
    		trialDataDetailEOService.exportTrialDataDetail(page, response, request);
    		return Result.success("0", "查询成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "导出失败!");
		}
    }
    
    
}
