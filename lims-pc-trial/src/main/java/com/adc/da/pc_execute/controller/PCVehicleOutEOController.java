package com.adc.da.pc_execute.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adc.da.sys.annotation.EnableAccess;
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
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_execute.entity.PCVehicleOutEO;
import com.adc.da.pc_execute.page.PCVehicleOutEOPage;
import com.adc.da.pc_execute.service.PCVehicleOutEOService;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_execute.vo.PCVehicleOutSearchVO;
import com.adc.da.pc_execute.vo.PCVehicleOutVO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * PC整车出门申请单
 * @author Administrator
 * @date 2019年11月26日
 */
@RestController
@RequestMapping("${restPath}/pcTrial/vehicleOut")
@Api(tags = "PcTrialTask-PV/CV试验执行模块")
@SuppressWarnings("all")
public class PCVehicleOutEOController extends BaseController<PCVehicleOutEO> {
	/**
	 * 用户记录日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(PCVehicleOutEOController.class);
	
	/**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private PCVehicleOutEOService pcVehicleOutEOService;
    
    @Autowired
    private PVCVService PVCVService;
    
    
    /**
     * 整车出门单-分页查询
     * @Title: page
     * @param page
     * @param searchPhrase
     * @return
     * @return ResponseMessage<PageInfo<PCVehicleOutVO>>
     * @author: ljy
     * @date: 2019年11月26日
     */
    @ApiOperation(value = "|整车出门单分页查询")
    @BusinessLog(description = "整车出门单-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pcTrial:vehicleOut:page")
    public ResponseMessage<PageInfo<PCVehicleOutSearchVO>> page(
    		PCVehicleOutEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
			List<PCVehicleOutEO> rows =  pcVehicleOutEOService.page(page, searchPhrase);
			//设置总条数
			Integer rowsCount = pcVehicleOutEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(
					getPageInfo(page.getPager(), rows), PCVehicleOutSearchVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
	/**
	 *  整车出门单-保存
	 * @Title: save
	 * @param pcVehicleOutEO
	 * @return
	 * @return PCVehicleOutEO
	 * @author: ljy
	 * @date: 2019年11月26日
	 */
    @ApiOperation(value = "|整车出门单保存")
    @BusinessLog(description = "整车出门单-保存")
    @PostMapping("/save")
    @RequiresPermissions("pcTrial:vehicleOut:save")
    public ResponseMessage<PCVehicleOutVO> save(@RequestBody PCVehicleOutVO pcVehicleOutVO){
    	try {
			PCVehicleOutEO pcVehicleOutEO = pcVehicleOutEOService
					.save(beanMapper.map(pcVehicleOutVO, PCVehicleOutEO.class));
    		return Result.success("0", "新增成功!", beanMapper.map(pcVehicleOutEO, PCVehicleOutVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
    
	/**
	 *  整车出门单-编辑
	 * @Title: edit
	 * @param pcVehicleOutEO
	 * @return
	 * @return PCVehicleOutEO
	 * @author: ljy
	 * @date: 2019年11月26日
	 */
    @ApiOperation(value = "|整车出门单编辑")
    @BusinessLog(description = "整车出门单-编辑")
    @PutMapping("/edit")
    @RequiresPermissions("pcTrial:vehicleOut:save")
    public ResponseMessage<PCVehicleOutVO> edit(@RequestBody PCVehicleOutVO pcVehicleOutVO){
    	try {
			PCVehicleOutEO pcVehicleOutEO = pcVehicleOutEOService
					.edit(beanMapper.map(pcVehicleOutVO, PCVehicleOutEO.class));
    		return Result.success("0", "编辑成功!", beanMapper.map(pcVehicleOutEO, PCVehicleOutVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    
    /**
     * 整车出门单-详情
     * @Title: getDetialById
     * @param id
     * @return
     * @return ResponseMessage<PCVehicleOutVO>
     * @author: ljy
     * @date: 2019年11月26日
     */
    @ApiOperation(value = "|整车出门单详情")
    @BusinessLog(description = "整车出门单-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pcTrial:vehicleOut:save")
    public ResponseMessage<PCVehicleOutVO> getDetialById(@PathVariable(value = "id") String id){
    	try {
    		return Result.success("0", "查询成功", 
    				beanMapper.map(pcVehicleOutEOService.selectByPrimaryKey(id), PCVehicleOutVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
		}
    }
    
    
    
    /**
     * 整车出门单-删除
     * @Title: deleteById
     * @param id
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年11月26日
     */
    @ApiOperation(value = "|整车出门单删除")
    @BusinessLog(description = "整车出门单-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pcTrial:vehicleOut:delete")
    public ResponseMessage deleteById(@PathVariable(value = "id") String id) {
    	try {
    		pcVehicleOutEOService.deleteById(id);
			return Result.success("0", "删除成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","删除失败!");
		}
    }
    
    
    /**
     * 整车出门单-导出
     * @Title: exportPCVehicleOut
     * @param response
     * @param request
     * @param id
     * @return
     * @return ResponseMessage
     * @author: ljy
     * @date: 2019年11月27日
     */
    @ApiOperation(value = "|整车出门单导出")
    @BusinessLog(description = "整车出门单-导出")
    @GetMapping("/export")
    @RequiresPermissions("pcTrial:vehicleOut:export")
    public  ResponseMessage exportPCVehicleOut(HttpServletResponse response, 
    		HttpServletRequest request, String id){
    	try {
    		pcVehicleOutEOService.exportPCVehicleOut(response, request, id);
			return Result.success("0", "导出成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","导出失败!");
		}
    }
    
    
    /**
	 * 整车出门单-流程启动
	 * @Title: submitVehicleOut
	 * @param pcVehicleOutEO
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2019年11月27日
	 */
    @ApiOperation(value = "|整车出门单-流程启动")
    @BusinessLog(description = "整车出门单-流程启动")
    @PostMapping("/startProcess")
    @RequiresPermissions("pcTrial:vehicleOut:startProcess")
    public ResponseMessage submitVehicleOut(
            @RequestBody PCVehicleOutVO pcVehicleOutVO) {
        try {
        	//判断下一节点是否有审批人
            JSONObject jsonObj = PVCVService.getStartNextNodeInfo(ConstantUtils.PV_VEHICLE_OUT_TYPE, 
            		ConstantUtils.CV_VEHICLE_OUT_TYPE);
            if("-1".equals(jsonObj.getString("isSuccess"))) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
        	pcVehicleOutEOService.submitVehicleOut(
                    beanMapper.map(pcVehicleOutVO, PCVehicleOutEO.class), jsonObj.getString("flag"));
            return Result.success("0", "流程启动成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "流程启动失败!");
        }
    }
    
    
    
    /**
     * 整车出门单-流程审批
     * @Title: approvalProcess
     * @param request
     * @param requestEO
     * @return
     * @return ResponseMessage<PCReliableInitTaskVO>
     * @author: ljy
     * @date: 2019年11月27日
     */
    @ApiOperation(value = "|整车出门单流程审批")
    @BusinessLog(description = "整车出门单-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("pcTrial:vehicleOut:approvalProcess")
    @EnableAccess
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
            pcVehicleOutEOService.approvalProcess(request, requestEO);
            return Result.success("0", "流程审核成功!");
        } catch (Exception e) {
            logger.error("-1", "流程审批失败！");
            return Result.error("-1", "流程审批失败！");
        }
    }
    
    
    
}
