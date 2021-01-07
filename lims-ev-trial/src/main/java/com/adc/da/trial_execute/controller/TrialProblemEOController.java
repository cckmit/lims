package com.adc.da.trial_execute.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.trial_execute.entity.TrialProblemEO;
import com.adc.da.trial_execute.page.TrialProblemEOPage;
import com.adc.da.trial_execute.service.TrialProblemEOService;
import com.adc.da.trial_execute.vo.TrialProblemVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 试验问题记录
 * @author Administrator
 * @date 2019年9月18日
 */
@RestController
@RequestMapping("/${restPath}/evTrial/problem")
@Api(tags = "EvTrialTask-发动机试验任务执行模块")
@SuppressWarnings("all")
public class TrialProblemEOController extends BaseController<TrialProblemEO> {
	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(TrialProblemEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private TrialProblemEOService trialProblemEOService;
    
    
    /**
     * 试验问题记录-分页查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<PageInfo<TrialProblemVO>>
    * @author： ljy  
    * @date： 2019年9月18日
     */
    @ApiOperation(value = "|试验问题记录-分页查询")
    @BusinessLog(description = "试验问题记录-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("evTrial:problem:page")
    public ResponseMessage<PageInfo<TrialProblemVO>> page(TrialProblemEOPage page){
    	try {
    		//查询
			List<TrialProblemEO> rows =  trialProblemEOService.page(page);
			//设置总条数
			Integer rowsCount = trialProblemEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					TrialProblemVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    /**
     * 试验问题记录-详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<TrialProblemVO>
    * @author： ljy  
    * @date： 2019年9月26日
     */
    @ApiOperation(value = "|试验问题记录-详情")
    @BusinessLog(description = "试验问题记录-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("evTrial:problem:get")
    public ResponseMessage<TrialProblemVO> getDetailById(@PathVariable(value = "id") String id){
    	try {
    		//查询详情
    		return Result.success("0", "查询成功", beanMapper.map(
    				trialProblemEOService.selectByPrimaryKey(id), TrialProblemVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    
    /**
     * 试验问题记录-新增
    * @Title：save
    * @param trialProblemVO
    * @return
    * @return: ResponseMessage<TrialProblemVO>
    * @author： ljy  
    * @date： 2019年9月18日
     */
    @ApiOperation(value = "|试验问题记录-新增")
    @BusinessLog(description = "试验问题记录-新增")
    @PostMapping("/save")
    @RequiresPermissions("evTrial:problem:save")
    public ResponseMessage<TrialProblemVO> save(@RequestBody TrialProblemVO trialProblemVO){
    	try {
    		//保存
    		TrialProblemEO trialProblemEO = trialProblemEOService.
    				save(beanMapper.map(trialProblemVO, TrialProblemEO.class));
			return Result.success("0", "新增成功!", 
					beanMapper.map(trialProblemEO, TrialProblemVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
    
    /**
     * 试验问题记录-编辑
    * @Title：update
    * @param trialProblemVO
    * @return
    * @return: ResponseMessage<TrialProblemVO>
    * @author： ljy  
    * @date： 2019年9月18日
     */
    @ApiOperation(value = "|试验问题记录-编辑")
    @BusinessLog(description = "试验问题记录-编辑")
    @PutMapping("/edit")
    @RequiresPermissions("evTrial:problem:edit")
    public ResponseMessage<TrialProblemVO> update(@RequestBody TrialProblemVO trialProblemVO){
    	try {
    		//保存
    		TrialProblemEO trialProblemEO = trialProblemEOService.
    				edit(beanMapper.map(trialProblemVO, TrialProblemEO.class));
			return Result.success("0", "编辑成功!", 
					beanMapper.map(trialProblemEO, TrialProblemVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    
    /**
     * 试验问题记录导出
    * @Title：exportTrialProblem
    * @param page
    * @param response
    * @param request
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年9月19日
     */
    @ApiOperation(value = "|试验问题记录-导出")
    @BusinessLog(description = "试验问题记录-导出")
    @GetMapping("/export")
    @RequiresPermissions("evTrial:problem:export")
    public ResponseMessage exportTrialProblem(TrialProblemEOPage page,
    		HttpServletResponse response, HttpServletRequest request) {
    	try {
    		trialProblemEOService.exportTrialProblem(page, response, request);
    		return Result.success("0", "导出成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "导出失败!");
		}
    	
    }
    /**
     * 试验问题上传图片
     * @Title：updatePicture
     * @param page
     * @param response
     * @param request
     * @return
     * @return: ResponseMessage
     * @author： lcx
     * @date： 2020年01月18日
     */
    @ApiOperation(value = "|试验问题图片上传")
    @BusinessLog(description = "试验问题-图片上传")
    @GetMapping(path = "/updatePicture")
    public ResponseMessage updatePicture(String id,String pictureName,String pictureId){
        try {
            trialProblemEOService.updatePicture(id,pictureName,pictureId);
            return Result.success("0", "上传成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "上传失败!");
        }
    }
    
}
