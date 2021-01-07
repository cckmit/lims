package com.adc.da.pc_execute.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_execute.entity.PCQCDDInfoEO;
import com.adc.da.pc_execute.entity.PCReliableInitTaskEO;
import com.adc.da.pc_execute.page.PCQCDDInfoEOPage;
import com.adc.da.pc_execute.service.PCQCDDInfoEOService;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_execute.vo.PCQCDDInfoSearchVO;
import com.adc.da.pc_execute.vo.PCQCDDInfoVO;
import com.adc.da.pc_execute.vo.PCReliableInitTaskVO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


@RestController
@RequestMapping("${restPath}/pcqcdd/qcdd")
@Api(tags = "PcTrialTask-PV/CV试验执行模块")
@SuppressWarnings("all")
public class PCQCDDInfoEOController extends BaseController<PCQCDDInfoEO> {
	/**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(PCQCDDInfoEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private PCQCDDInfoEOService pcqcddInfoEOService;
    
    @Autowired
    private PVCVService PVCVService;
    
    
    
    
    
    
    /**
     * PV/CV-QCDD-分页查询
     * @Title: page
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<PCQCDDInfoVO>>
     * @author: ljy
     * @date: 2020年6月3日
     */
    @ApiOperation(value = "|PV/CV-QCDD分页查询")
	@BusinessLog(description = "PV/CV-QCDD-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pcqcdd:qcdd:page")
    public ResponseMessage<PageInfo<PCQCDDInfoSearchVO>> page(PCQCDDInfoEOPage page){
        try {
        	//查询列表
        	List<PCQCDDInfoEO> rows = pcqcddInfoEOService.queryByPage(page);
        	//设置总条数
			Integer rowsCount = pcqcddInfoEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功!",
                    beanMapper.mapPage(getPageInfo(page.getPager(), rows), PCQCDDInfoSearchVO.class));
			
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
	         return Result.error("-1", "查询失败!");
		}
        
    }
    
    
    /**
     * PV/CV-QCDD数据保存
     * @Title: save
     * @param pcqcddInfoVO
     * @return
     * @return ResponseMessage<PCQCDDInfoVO>
     * @author: ljy
     * @date: 2020年6月4日
     */
    @ApiOperation(value = "|PV/CV-QCDD数据保存")
   	@BusinessLog(description = "PV/CV-QCDD-数据保存")
    @PostMapping("/save")
    @RequiresPermissions("pcqcdd:qcdd:save")
    public ResponseMessage<PCQCDDInfoVO> save(@RequestBody PCQCDDInfoVO pcqcddInfoVO){
    	try {
			PCQCDDInfoEO eo = pcqcddInfoEOService.save(beanMapper.map(pcqcddInfoVO, PCQCDDInfoEO.class));
			return Result.success("0", "新增成功!", beanMapper.map(eo, PCQCDDInfoVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
	        return Result.error("-1", "新增失败!");
		}
    }
    
    
    
    /**
     * PV/CV-QCDD获取编号
     * @Title: getQcddCode
     * @return
     * @return ResponseMessage<String>
     * @author: ljy
     * @date: 2020年6月4日
     */
    @ApiOperation(value = "|PV/CV-QCDD获取编号")
   	@BusinessLog(description = "PV/CV-QCDD-获取编号")
    @GetMapping("/getQcddCode")
    @RequiresPermissions("pcqcdd:qcdd:save")
    public ResponseMessage<String> getQcddCode() {
    	try {
    		return Result.success("0", "获取编号成功!", pcqcddInfoEOService.getQcddCode());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
	        return Result.error("-1", "获取编号失败!");
		}
    }

    
    
    /**
     * PV/CV-QCDD数据编辑
     * @Title: edit
     * @param pcqcddInfoVO
     * @return
     * @return ResponseMessage<PCQCDDInfoVO>
     * @author: ljy
     * @date: 2020年6月4日
     */
    @ApiOperation(value = "|PV/CV-QCDD数据编辑")
   	@BusinessLog(description = "PV/CV-QCDD-数据编辑")
    @PutMapping("/edit")
    @RequiresPermissions("pcqcdd:qcdd:edit")
    public ResponseMessage<PCQCDDInfoVO> edit(@RequestBody PCQCDDInfoVO pcqcddInfoVO){
    	try {
			PCQCDDInfoEO eo = pcqcddInfoEOService.edit(beanMapper.map(pcqcddInfoVO, PCQCDDInfoEO.class));
			return Result.success("0", "更新成功!", beanMapper.map(eo, PCQCDDInfoVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
	        return Result.error("-1", "更新失败!");
		}
    }
    
    
    
    /**
     * PV/CV-QCDD数据详情
     * @Title: getDetailById
     * @param id
     * @return
     * @return ResponseMessage<PCQCDDInfoVO>
     * @author: ljy
     * @date: 2020年6月4日
     */
    @ApiOperation(value = "|PV/CV-QCDD数据详情")
   	@BusinessLog(description = "PV/CV-QCDD-数据详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pcqcdd:qcdd:get")
    public ResponseMessage<PCQCDDInfoVO> getDetailById(@PathVariable String id){
    	try {
    		return Result.success("0", "查询成功!", beanMapper.map(
    				pcqcddInfoEOService.selectByPrimaryKey(id), PCQCDDInfoVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
	        return Result.error("-1", "查询失败!");
		}
    }
    
    
    
    /**
     * PV/CV-QCDD数据删除
     * @Title: deleteById
     * @param id
     * @param qcddStatus
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年6月4日
     */
    @ApiOperation(value = "|PV/CV-QCDD数据删除")
   	@BusinessLog(description = "PV/CV-QCDD-数据删除")
    @DeleteMapping("/del")
    @RequiresPermissions("pcqcdd:qcdd:delete")
    public ResponseMessage deleteById(String id) {
    	try {
    		pcqcddInfoEOService.deleteById(id);
    		return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
	        return Result.error("-1", "删除失败!");
		}
    }
    
    
    
    /**
	 * QCDD流程启动	
	 * @Title: submitQCDD
	 * @param pcqcddInfoEO
	 * @param flag
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2020年6月8日
	 */
    @ApiOperation(value = "|PV/CV-QCDD流程启动")
   	@BusinessLog(description = "PV/CV-QCDD-流程启动")
    @PostMapping("/startProcess")
    @RequiresPermissions("pcqcdd:qcdd:startProcess")
    public ResponseMessage submitQCDD(@RequestBody PCQCDDInfoVO pcQCDDInfoVO) {
        try {
        	//判断下一节点是否有审批人
            JSONObject jsonObj = PVCVService.getStartNextNodeInfo(ConstantUtils.PV_QCDD_TYPE, 
            		ConstantUtils.CV_QCDD_TYPE);
            if("-1".equals(jsonObj.getString("isSuccess"))) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            pcqcddInfoEOService.submitQCDD(
                    beanMapper.map(pcQCDDInfoVO, PCQCDDInfoEO.class), jsonObj.getString("flag"));
            return Result.error("0", "流程启动成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "流程启动失败!");
        }
    }
    
    
    /**
     * QCDD流程审批
     * @Title: approvalProcess
     * @param request
     * @param requestEO
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2020年6月9日
     */
    @ApiOperation(value = "|PV/CV-QCDD流程审批")
   	@BusinessLog(description = "PV/CV-QCDD-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("pcqcdd:qcdd:approvalProcess")
    public ResponseMessage approvalProcess(
            HttpServletRequest request, @RequestBody RequestEO requestEO) {
        try {
            //校验是否为空
            if (StringUtils.isEmpty(requestEO)) {
                return Result.error("-1", "审批信息不可为空");
            } else {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-1", "任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())) {
                    return Result.error("-1", "流程实例Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-1", "审批意见不可为空");
                }
            }
            
            //判断下一节点是否有审批人
            String str = PVCVService.getNextNodeInfo(requestEO);
            if("-1".equals(str)) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            pcqcddInfoEOService.approvalProcess(request, requestEO);
            return Result.success("0", "流程审核成功!");
        } catch (Exception e) {
            logger.error("-1", "流程审批失败！");
            return Result.error("-1", "流程审批失败！");
        }
    }
    
    
    
}
