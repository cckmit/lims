package com.adc.da.trial_execute.controller;

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
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.trial_execute.entity.ScaffoldingChangeEO;
import com.adc.da.trial_execute.page.ScaffoldingChangeEOPage;
import com.adc.da.trial_execute.service.ScaffoldingChangeEOService;
import com.adc.da.trial_execute.vo.ScaffoldingChangeDetailVO;
import com.adc.da.trial_execute.vo.ScaffoldingChangeSearchVO;
import com.adc.da.trial_execute.vo.ScaffoldingChangeVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 发动机台架变更
 * @author Administrator
 * @date 2019年9月25日
 */

@RestController
@RequestMapping("/${restPath}/evTrial/scaffoldingChange")
@Api(tags = "EvTrialTask-发动机试验任务执行模块")
@SuppressWarnings("all")
public class ScaffoldingChangeEOController extends BaseController<ScaffoldingChangeEO> {
	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(ScaffoldingChangeEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private ScaffoldingChangeEOService scaffoldingChangeEOService;
    
    
    /**
     * 台架变更记录-分页查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<PageInfo<ScaffoldingChangeVO>>
    * @author： ljy  
    * @date： 2019年9月25日
     */
    @ApiOperation(value = "|台架变更记录-分页查询")
    @BusinessLog(description = "台架变更记录-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("evTrial:scaffoldingChange:page")
    public ResponseMessage<PageInfo<ScaffoldingChangeSearchVO>> page(ScaffoldingChangeEOPage page){
    	try {
    		//查询
			List<ScaffoldingChangeEO> rows =  scaffoldingChangeEOService.page(page);
			//设置总条数
			Integer rowsCount = scaffoldingChangeEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					ScaffoldingChangeSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    /**
     * 台架变更记录-变更详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<ScaffoldingChangeVO>
    * @author： ljy  
    * @date： 2019年9月25日
     */
    @ApiOperation(value = "|台架变更记录-变更详情")
    @BusinessLog(description = "台架变更记录-变更详情")
    @GetMapping("/{id}")
    @RequiresPermissions("evTrial:scaffoldingChange:get")
    public ResponseMessage<ScaffoldingChangeVO>  getDetailById(
    		@PathVariable(value = "id") String id){
    	try {
    		return Result.success("0", "查询成功", 
    				beanMapper.map(scaffoldingChangeEOService.getDetailById(id), ScaffoldingChangeVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    /**
     * 台架变更记录-变更初始化信息
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<ScaffoldingChangeVO>
    * @author： ljy  
    * @date： 2019年9月25日
     */
    @ApiOperation(value = "|台架变更记录-变更初始化信息")
    @BusinessLog(description = "台架变更记录-变更初始化信息")
    @GetMapping("/init")
    @RequiresPermissions("evTrial:scaffoldingChange:init")
    public ResponseMessage<ScaffoldingChangeDetailVO> getInitDetail(String trialTaskId){
    	try {
    		return Result.success("0", "查询成功", 
    				beanMapper.map(scaffoldingChangeEOService.getInitDetail(trialTaskId), ScaffoldingChangeDetailVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
     
    
    /**
     * 台架变更-新增
    * @Title：save
    * @param scaffoldingChangeVO
    * @return
    * @return: ResponseMessage<ScaffoldingChangeVO>
    * @author： ljy  
    * @date： 2019年9月26日
     */
    @ApiOperation(value = "|台架变更记录-新增")
    @BusinessLog(description = "台架变更记录-新增")
    @PostMapping("/save")
    @RequiresPermissions("evTrial:scaffoldingChange:save")
    public ResponseMessage<ScaffoldingChangeVO> save(
    		@RequestBody ScaffoldingChangeVO scaffoldingChangeVO){
    	try {
    		//新增
        	ScaffoldingChangeEO eo = scaffoldingChangeEOService.save(
        			beanMapper.map(scaffoldingChangeVO, ScaffoldingChangeEO.class));
        	return Result.success("0", "新增成功", beanMapper.map(eo, ScaffoldingChangeVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    	
    }
    
    
    
}
