package com.adc.da.trial_execute.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.trial_execute.entity.TrialDataEO;
import com.adc.da.trial_execute.page.TrialDataEOPage;
import com.adc.da.trial_execute.service.TrialDataEOService;
import com.adc.da.trial_execute.vo.TrialDataVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 试验数据记录
 * @author Administrator
 * @date 2019年9月11日
 */
@RestController
@RequestMapping("/${restPath}/evTrial/data")
@Api(tags = "EvTrialTask-发动机试验任务执行模块")
@SuppressWarnings("all")
public class TrialDataEOController extends BaseController<TrialDataEO> {
	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(TrialDataEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private TrialDataEOService trialDataEOService;
    
    
    
    /**
     * 试验数据记录-分页查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<PageInfo<TrialDataVO>>
    * @author： ljy  
    * @date： 2019年9月12日
     */
    @ApiOperation(value = "|试验数据记录-分页查询")
    @BusinessLog(description = "试验数据记录-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("evTrial:data:page")
    public ResponseMessage<PageInfo<TrialDataVO>> page(TrialDataEOPage page){
    	try {
    		//查询
			List<TrialDataEO> rows =  trialDataEOService.page(page);
			//设置总条数
			Integer rowsCount = trialDataEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					TrialDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    /**
     * 试验数据记录-新增
    * @Title：save
    * @param trialDataVO
    * @param file
    * @return
    * @return: ResponseMessage<TrialDataVO>
    * @author： ljy  
    * @date： 2019年9月12日
     */
    @ApiOperation(value = "|试验数据记录-新增")
    @BusinessLog(description = "试验数据记录-新增")
    @PostMapping("/save")
    @RequiresPermissions("evTrial:data:save")
    public ResponseMessage<TrialDataVO> save(TrialDataVO trialDataVO, MultipartFile file){
    	try {
    		if(StringUtils.isEmpty(trialDataVO.getTrialtaskId())) {
    			return Result.error("-1", "试验任务id不可为空");
    		}
    		if(StringUtils.isEmpty(file)) {
    			return Result.error("-1", "试验数据记录附件不可为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileReportExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileReportExtension)) {
    				return Result.error("-1", "试验数据记录模板只支持上传Excel格式的文件!");
        		}
    		}
    		//保存
    		TrialDataEO trialDataEO = trialDataEOService.
    				save(beanMapper.map(trialDataVO, TrialDataEO.class), file);
    		return Result.success("0","新增成功!",beanMapper.map(trialDataEO, TrialDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
    
    
    /**
     * 试验数据记录-编辑
    * @Title：update
    * @param trialDataVO
    * @param file
    * @return
    * @return: ResponseMessage<TrialDataVO>
    * @author： ljy  
    * @date： 2019年9月16日
     */
    @ApiOperation(value = "|试验数据记录-编辑")
    @BusinessLog(description = "试验数据记录-编辑")
    @PostMapping("/edit")
    @RequiresPermissions("evTrial:data:edit")
    public ResponseMessage<TrialDataVO> update(TrialDataVO trialDataVO, MultipartFile file){
    	try {
    		if(StringUtils.isEmpty(file)) {
    			return Result.error("-1", "试验数据记录附件不可为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileReportExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileReportExtension)) {
    				return Result.error("-1", "试验数据记录模板只支持上传Excel格式的文件!");
        		}
    		}
    		//保存
    		TrialDataEO trialDataEO = trialDataEOService.
    				edit(beanMapper.map(trialDataVO, TrialDataEO.class), file);
    		return Result.success("0","编辑成功!",beanMapper.map(trialDataEO, TrialDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }


}
