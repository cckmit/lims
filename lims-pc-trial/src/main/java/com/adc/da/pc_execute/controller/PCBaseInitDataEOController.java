package com.adc.da.pc_execute.controller;

import java.util.List;

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
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_execute.entity.PCBaseInitDataEO;
import com.adc.da.pc_execute.page.PCBaseInitDataEOPage;
import com.adc.da.pc_execute.service.PCBaseInitDataEOService;
import com.adc.da.pc_execute.vo.PCBaseInitDataVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * PV/CV试验执行模块--PV/CV初始化数据
 * @author Administrator
 * @date 2019年10月18日
 */
@RestController
@RequestMapping("${restPath}/pcTrial/initData")
@Api(tags = "PcTrialTask-PV/CV试验执行模块")
@SuppressWarnings("all")
public class PCBaseInitDataEOController extends BaseController<PCBaseInitDataEO> {

	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(PCBaseInitDataEOController.class);
    
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private PCBaseInitDataEOService pcBaseInitDataEOService;

   /**
    * PV/CV初始化数据分页查询
    * @Title: page
    * @param page
    * @param searchPhrase
    * @return
    * @return ResponseMessage<PageInfo<PCBaseInitDataVO>>
    * @author: ljy
    * @date: 2019年10月18日
    */
    @ApiOperation(value = "|PV/CV初始化数据分页查询")
	@BusinessLog(description = "PV/CV初始化数据-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pcTrial:initData:page")
    public ResponseMessage<PageInfo<PCBaseInitDataVO>> page(
    		PCBaseInitDataEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
    		//查询列表
			List<PCBaseInitDataEO> rows = pcBaseInitDataEOService.page(page, searchPhrase);
			//设置总条数
			Integer rowsCount = pcBaseInitDataEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功!", beanMapper.mapPage(getPageInfo(page.getPager(), rows), PCBaseInitDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    
    
    /**
     * PV/CV初始化数据-获取详情
     * @Title: getDetailById
     * @param id
     * @return
     * @return ResponseMessage<PCBaseInitDataVO>
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|PV/CV初始化数据详情")
	@BusinessLog(description = "PV/CV初始化数据-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pcTrial:initData:get")
    public ResponseMessage<PCBaseInitDataVO> getDetailById(
    		@PathVariable(value = "id") String id){
    	try {
    		return Result.success("0", "查询成功", beanMapper.map(
    				pcBaseInitDataEOService.selectByPrimaryKey(id), PCBaseInitDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    
    
    
    /**
     * PV/CV初始化数据-新增
     * @Title: save
     * @param pcBaseInitDataVO
     * @return
     * @return ResponseMessage<PCBaseInitDataVO>
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|PV/CV初始化数据新增")
    @BusinessLog(description = "PV/CV初始化数据-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("pcTrial:initData:save")
    public ResponseMessage<PCBaseInitDataVO> save(
    		@RequestBody PCBaseInitDataVO pcBaseInitDataVO){
    	try {
    		PCBaseInitDataEO pcBaseInitDataEO = pcBaseInitDataEOService.save(
    				beanMapper.map(pcBaseInitDataVO, PCBaseInitDataEO.class));
    		return Result.success("0", "保存成功!", 
    				beanMapper.map(pcBaseInitDataEO, PCBaseInitDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","保存失败!");
		}
    }
    
    
    
    /**
     * PV/CV初始化数据-更新
     * @Title: save
     * @param pcBaseInitDataVO
     * @return
     * @return ResponseMessage<PCBaseInitDataVO>
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|PV/CV初始化数据更新")
    @BusinessLog(description = "PV/CV初始化数据-更新")
    @PutMapping(path = "/edit")
    @RequiresPermissions("pcTrial:initData:edit")
    public ResponseMessage<PCBaseInitDataVO> edit(
    		@RequestBody PCBaseInitDataVO pcBaseInitDataVO){
    	try {
    		PCBaseInitDataEO pcBaseInitDataEO = pcBaseInitDataEOService.edit(
    				beanMapper.map(pcBaseInitDataVO, PCBaseInitDataEO.class));
    		return Result.success("0", "更新成功!", 
    				beanMapper.map(pcBaseInitDataEO, PCBaseInitDataVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","更新失败!");
		}
    }
    
    
    /**
     * PV/CV初始化数据-删除
     * @Title: deleteById
     * @param id
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|PV/CV初始化数据删除")
    @BusinessLog(description = "PV/CV初始化数据-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pcTrial:initData:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
    		pcBaseInitDataEOService.deleteByPrimaryKey(id);
    		return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }
    
    
}
