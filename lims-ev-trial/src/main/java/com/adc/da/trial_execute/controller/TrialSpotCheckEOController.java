package com.adc.da.trial_execute.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.trial_execute.entity.TrialSpotCheckEO;
import com.adc.da.trial_execute.page.TrialSpotCheckEOPage;
import com.adc.da.trial_execute.service.TrialSpotCheckEOService;
import com.adc.da.trial_execute.vo.TrialSpotCheckVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/evTrial/spotCheck")
@Api(tags = "EvTrialTask-发动机试验任务执行模块")
@SuppressWarnings("all")
public class TrialSpotCheckEOController extends BaseController<TrialSpotCheckEO> {
	/**
	 * 用户记录日志
	 */
    private static final Logger logger = LoggerFactory.getLogger(TrialSpotCheckEOController.class);
    
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Value("${file.path}")
    private String filepath;
    
    @Autowired
    private TrialSpotCheckEOService trialSpotCheckEOService;
    
    
    /**
     * 试验点检-分页查询
    * @Title：page
    * @param page
    * @return
    * @return: ResponseMessage<PageInfo<TrialSpotCheckVO>>
    * @author： ljy  
    * @date： 2019年9月11日
     */
    @ApiOperation(value = "|试验点检-分页查询")
    @BusinessLog(description = "试验点检-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("evTrial:spotCheck:page")
    @EnableAccess
    public ResponseMessage<PageInfo<TrialSpotCheckVO>> page(TrialSpotCheckEOPage page){
    	try {
    		//查询
			List<TrialSpotCheckEO> rows =  trialSpotCheckEOService.page(page);
			//设置总条数
			Integer rowsCount = trialSpotCheckEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), 
					TrialSpotCheckVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    /**
     * 试验点检-详情
    * @Title：getDetailById
    * @param id
    * @return
    * @return: ResponseMessage<TrialSpotCheckVO>
    * @author： ljy  
    * @date： 2019年9月30日
     */
    @ApiOperation(value = "|试验点检-详情")
    @BusinessLog(description = "试验点检-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("evTrial:spotCheck:get")
    @EnableAccess
    public ResponseMessage<TrialSpotCheckVO> getDetailById(@PathVariable String id){
    	try {
    		return Result.success("0", "查询成功", beanMapper.map(
    				trialSpotCheckEOService.getDetailById(id),
					TrialSpotCheckVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    
    /**
     * 试验点检-新增
    * @Title：save
    * @param trialSpotCheckVO
    * @param file
    * @return
    * @return: ResponseMessage<TrialSpotCheckVO>
    * @author： ljy  
    * @date： 2019年9月11日
     */
    @ApiOperation(value = "|试验点检-新增")
    @BusinessLog(description = "试验点检-新增")
    @PostMapping("/save")
    @RequiresPermissions("evTrial:spotCheck:save")
    @EnableAccess
    public ResponseMessage<TrialSpotCheckVO> save(@RequestBody TrialSpotCheckVO trialSpotCheckVO) {
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(trialSpotCheckVO.getType())) {
    			return Result.error("-1", "点检类型不可为空");
    		}

    		if(StringUtils.isEmpty(trialSpotCheckVO.getFileId())) {
    			return Result.error("-1", "点检附件不可为空");
    		}
    		//校验文件上传的格式
//			if(StringUtils.isNotEmpty(file)) {
//				//文件后缀
//				String fileReportExtension = FileUtil.getFileExtension(file.getOriginalFilename());
//				if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileReportExtension)) {
//					return Result.error("-1", "报告模板只支持上传Excel格式的文件!");
//				}
//			}
    		//保存
    		TrialSpotCheckEO trialSpotCheckEO = trialSpotCheckEOService.
    				save(beanMapper.map(trialSpotCheckVO, TrialSpotCheckEO.class));
    		return Result.success("0","新增成功!",beanMapper.map(trialSpotCheckEO, TrialSpotCheckVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
    
    
	/**
	 * 试验点检-编辑
	* @Title：edit
	* @param trialSpotCheckEO
	* @param file
	* @return
	* @throws Exception
	* @return: TrialSpotCheckEO
	* @author： ljy  
	* @date： 2019年9月11日
	 */
    @ApiOperation(value = "|试验点检-编辑")
    @BusinessLog(description = "试验点检-编辑")
    @PutMapping("/edit")
    @RequiresPermissions("evTrial:spotCheck:update")
    @EnableAccess
    public ResponseMessage<TrialSpotCheckVO> update(@RequestBody TrialSpotCheckVO trialSpotCheckVO) {
    	try {
    		//校验是否为空
    		if(StringUtils.isEmpty(trialSpotCheckVO.getType())) {
    			return Result.error("-1", "点检类型不可为空");
    		}
    		//校验附件
    		if(StringUtils.isEmpty(trialSpotCheckVO.getFileId())) {
    			return Result.error("-1", "点检附件不可为空");
    		}
    		/*if(StringUtils.isEmpty(file) && StringUtils.isEmpty(trialSpotCheckVO.getFileId())) {
    			return Result.error("-1", "点检附件不可为空");
    		}
    		//校验文件上传的格式
    		if(StringUtils.isNotEmpty(file)) {
    			//文件后缀
    			String fileReportExtension = FileUtil.getFileExtension(file.getOriginalFilename());
    			if(!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileReportExtension)) {
    				return Result.error("-1", "报告模板只支持上传Excel格式的文件!");
        		}
    		}*/
    		//编辑
    		TrialSpotCheckEO trialSpotCheckEO = trialSpotCheckEOService.
    				edit(beanMapper.map(trialSpotCheckVO, TrialSpotCheckEO.class));
    		return Result.success("0","编辑成功!",beanMapper.map(trialSpotCheckEO, TrialSpotCheckVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    
    /**
	 * 点检记录导出
	* @Title：exportTrialSpotCheck
	* @param response
	* @param request
	* @param page
	* @throws IOException
	* @return: void
	* @author： ljy  
	* @date： 2019年9月12日
	 */
    @ApiOperation(value = "|试验点检-导出")
    @BusinessLog(description = "试验点检-导出")
    @GetMapping("/export")
    @RequiresPermissions("evTrial:spotCheck:export")
    public ResponseMessage exportTrialSpotCheck(HttpServletRequest request,
    		HttpServletResponse response, TrialSpotCheckEOPage page) {
    	try {
    		//按类型导出
    		trialSpotCheckEOService.exportTrialSpotCheck(response, request, page);
    		return Result.error("-1", "导出成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "导出失败!");
		}
    }
    
    
    
    
    
    
    
}
