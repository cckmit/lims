package com.adc.da.internalcost.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.internalcost.entity.InternalCostEO;
import com.adc.da.internalcost.page.InternalCostEOPage;
import com.adc.da.internalcost.service.InternalCostEOService;
import com.adc.da.internalcost.vo.InternalCostSearchVO;
import com.adc.da.internalcost.vo.InternalCostVO;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
/**
 * 费用管理模块--内部费用库
 * @author ljy
 * @date 2019年8月5日
 */
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping("${restPath}/cm/internalcost")
@Api(tags = "CM-费用管理模块")
@SuppressWarnings("all")
public class InternalCostEOController extends BaseController<InternalCostEO> {
	/**
	 * 用户记录日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(InternalCostEOController.class);
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private InternalCostEOService internalCostEOService;
    
    /**
     * 内部费用库分页查询
    * @Title：page
    * @param internalCostVO
    * @param pageNo
    * @param pageSize
    * @param searchPhrase
    * @return
    * @return: ResponseMessage<PageInfo<InternalCostSearchVO>>
    * @author： ljy  
    * @date： 2019年8月5日
     */
    @ApiOperation(value = "|内部费用库分页查询")
    @BusinessLog(description = "内部费用库-查询列表")
    @GetMapping("/page")
    @RequiresPermissions("cm:intercost:page")
    public ResponseMessage<PageInfo<InternalCostSearchVO>> page(
    		InternalCostEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
			List<InternalCostEO> rows =  internalCostEOService.page(page, searchPhrase);
			//设置总条数
			Integer rowsCount = internalCostEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), InternalCostSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    /**
     * 内部费用库详情
    * @Title：getDetialById
    * @param id
    * @return
    * @return: ResponseMessage<InternalCostVO>
    * @author： ljy  
    * @date： 2019年8月6日
     */
    @ApiOperation(value = "|内部费用库详情")
    @BusinessLog(description = "内部费用库-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("cm:intercost:get")
    public ResponseMessage<InternalCostSearchVO> getDetialById(@PathVariable(value = "id") String id) {
        try {
            return Result.success("0", "查询成功", beanMapper.map(internalCostEOService.selectByPrimaryKey(id), InternalCostSearchVO.class));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
        }
    }
    
    
	/**
	 * 内部费用库保存
	* @Title：save
	* @param internalCostEO
	* @return
	* @throws Exception
	* @return: InternalCostEO
	* @author： ljy  
	* @date： 2019年8月6日
	 */
    @ApiOperation(value = "|内部费用库保存")
    @BusinessLog(description = "内部费用库-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("cm:intercost:save")
    public ResponseMessage<InternalCostVO> save(@RequestBody InternalCostVO internalCostVO){
    	try {
    		//校验必填字段是否为空
    		if(StringUtils.isEmpty(internalCostVO.getInsproId())) {
    			return Result.error("-1", "试验项目不可为空");
    		}
    		if(StringUtils.isEmpty(internalCostVO.getCostPrice())) {
    			return Result.error("-1", "单价不可为空");
    		}
    		//保存
    		internalCostEOService.save(beanMapper.map(internalCostVO, InternalCostEO.class));
    		return Result.success("0","新增成功!",internalCostVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
 
	/**
	 * 内部费用库编辑
	* @Title：edit
	* @param internalCostEO
	* @return
	* @throws Exception
	* @return: InternalCostEO
	* @author： ljy  
	* @date： 2019年8月6日
	 */
    @ApiOperation(value = "|内部费用库编辑")
    @BusinessLog(description = "内部费用库-编辑")
    @PutMapping(path = "/edit")
    @RequiresPermissions("cm:intercost:edit")
    public ResponseMessage<InternalCostVO> update(@RequestBody InternalCostVO internalCostVO){
    	try {
    		//校验必填字段是否为空
    		if(StringUtils.isEmpty(internalCostVO.getInsproId())) {
    			return Result.error("-1", "试验项目不可为空");
    		}
    		if(StringUtils.isEmpty(internalCostVO.getCostPrice())) {
    			return Result.error("-1", "单价不可为空");
    		}
    		//保存
    		internalCostEOService.edit(beanMapper.map(internalCostVO, InternalCostEO.class));
    		return Result.success("0","编辑成功!",internalCostVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    
    /**
     * 内部费用库删除
    * @Title：deleteById
    * @param id
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年8月6日
     */
    @ApiOperation(value = "|内部费用库删除")
    @BusinessLog(description = "内部费用库-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("cm:intercost:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
    		internalCostEOService.deleteByPrimaryKey(id);
			return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }
    
    /**
     * 内部费用库导出
    * @Title：exportInternalCost
    * @param response
    * @param request
    * @return
    * @return: ResponseMessage
    * @author： ljy  
    * @date： 2019年8月8日
     */
    @ApiOperation(value = "|内部费用库导出")
    @GetMapping("/batchExport")
    @BusinessLog(description = "内部费用库-批量导出")
    @RequiresPermissions("cm:intercost:batchExport")
    public ResponseMessage exportInternalCost(HttpServletResponse response, HttpServletRequest request) {
    	try {
    		internalCostEOService.internalCostBatchExport(response, request);
			return Result.success("0", "导出成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error(e.getMessage());
		}
    }
}
