package com.adc.da.equipment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.equipment.VO.BorrowRecordVO;
import com.adc.da.equipment.VO.EquipmentBorrowRecordVO;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.equipment.entity.EquipmentBorrowRecordEO;
import com.adc.da.equipment.page.EquipmentBorrowRecordEOPage;
import com.adc.da.equipment.service.EquipmentBorrowRecordEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/${restPath}/equipment/equipmentBorrowRecord")
@Api(tags = "资源管理—设备管理")
public class EquipmentBorrowRecordEOController extends BaseController<EquipmentBorrowRecordEO>{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentBorrowRecordEOController.class);

    @Autowired
    private EquipmentBorrowRecordEOService equipmentBorrowRecordEOService;

    @ApiOperation(value = "设备借用")
    @BusinessLog(description = "设备管理-设备借用")
    @PutMapping(path ="/borrow")
    @RequiresPermissions("equipment:equipment:approvalBorrow")
    public ResponseMessage<EquipmentBorrowRecordEO> approvalBorrow(@RequestBody EquipmentBorrowRecordEO equipmentBorrowRecordEO){
        try{
            if (StringUtils.isEmpty(equipmentBorrowRecordEO.getBrwBorrowerName())){
                return Result.error("-1", "借用人不可为空！");
            }
            if (StringUtils.isEmpty(equipmentBorrowRecordEO.getBrwBorrowerTime())){
                return Result.error("-1", "借用时间不可为空");
            }
            equipmentBorrowRecordEOService.equipmentBorrow(equipmentBorrowRecordEO);
            return Result.success("0","借用流程发起成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "借用流程发起失败!");
        }
    }

    @ApiOperation(value = "|设备借用-流程审批")
    @BusinessLog(description = "设备借用-流程审批")
    @PostMapping("/approvalProcess")
    @EnableAccess
    public ResponseMessage approvalProcess(HttpServletRequest request, @RequestBody RequestEO requestEO) {
        try {
            //校验是否为空
            if(StringUtils.isEmpty(requestEO)){
                return Result.error("-1", "审批信息不可为空");
            }else {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())){
                    return Result.error( "-1","业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())){
                    return Result.error("-1","任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())){
                    return Result.error("-1","流程实例Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())){
                    return Result.error("-1","审批意见不可为空");
                }
            }
            equipmentBorrowRecordEOService.approvalProcess(request,  requestEO);
            return Result.success("0", "流程审核成功!");
        }catch(Exception e){
            logger.error("-1","流程审批失败！");
            return Result.error("-1","流程审批失败！");
        }
    }
    @ApiOperation(value = "设备归还")
    @BusinessLog(description = "设备管理-设备归还")
    @PutMapping(path ="/returned")
    @RequiresPermissions("equipment:equipment:approvalReturned")
    public ResponseMessage<EquipmentBorrowRecordEO> approvalReturned(@RequestBody EquipmentBorrowRecordEO equipmentBorrowRecordEO){
        try{
            if (StringUtils.isEmpty(equipmentBorrowRecordEO.getBrwBorrowerName())){
                return Result.error("-1", "归还人不可为空!");
            }

            if (StringUtils.isEmpty(equipmentBorrowRecordEO.getBrwReturnTime())){
                return Result.error("-1", "归还时间不可为空!");
            }
            //判断当前设备是否已经被借用
            if(equipmentBorrowRecordEOService.queryCountForEqBorrow(equipmentBorrowRecordEO) != 0){
                ResponseMessage<EquipmentBorrowRecordEO> result = equipmentBorrowRecordEOService.equipmentReturn(equipmentBorrowRecordEO,equipmentBorrowRecordEO.getBrwBorrowerId());
                return  result;
            }
            return Result.error("-1", "当前设备未被借出！");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "归还失败!");
        }
    }

    @ApiOperation(value = "|设备归还-流程审批")
    @BusinessLog(description = "设备归还-流程审批")
    @PostMapping("/approvalEquipmentReturnProcess")
    @EnableAccess
    public ResponseMessage approvalEquipmentReturnProcess(HttpServletRequest request, @RequestBody RequestEO requestEO) {
        try {
            //校验是否为空
            if(StringUtils.isEmpty(requestEO)){
                return Result.error("-1", "审批信息不可为空");
            }else {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())){
                    return Result.error( "-1","业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())){
                    return Result.error("-1","任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())){
                    return Result.error("-1","流程实例Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())){
                    return Result.error("-1","审批意见不可为空");
                }
            }
            equipmentBorrowRecordEOService.approvalEquipmentReturnProcess(request,  requestEO);
            return Result.success("0", "流程审核成功!");
        }catch(Exception e){
            logger.error("-1","流程审批失败！");
            return Result.error("-1","流程审批失败！");
        }
    }
    @ApiOperation(value = "查看借出设备借用人")
    @BusinessLog(description = "设备管理-查看借出设备借用人")
    @GetMapping(path ="/getBorrower")
    public ResponseMessage<EquipmentBorrowRecordEO> getBorrower(@RequestBody EquipmentBorrowRecordEO equipmentBorrowRecordEO){
        try{
            //根据归还设备的id，和数据库表中设备归还状态的标记。来唯一确定一条记录
            EquipmentBorrowRecordEO borrowRecordEO = equipmentBorrowRecordEOService.selectBorrowRecordByEqId(equipmentBorrowRecordEO.getEqId());
            return Result.error("0", "查询成功！",borrowRecordEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

	@ApiOperation(value = "设备借用归还记录分页查询")
    @GetMapping("/page")
    public ResponseMessage<PageInfo<EquipmentBorrowRecordEO>> page(EquipmentBorrowRecordEOPage page){
        try {
            List<EquipmentBorrowRecordEO> rows = equipmentBorrowRecordEOService.queryByPage(page);
            //设置条数
            int dataCount = equipmentBorrowRecordEOService.queryByCount(page);
            page.getPager().setRowCount(dataCount);
            return Result.success("0", "查询成功", getPageInfo(page.getPager(), rows));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @ApiOperation(value = "查看借出设备借用人及其部门")
    @BusinessLog(description = "设备管理-查看借出设备借用人及其部门")
    @GetMapping(path ="/getBorrowerMes")
    public ResponseMessage<EquipmentBorrowRecordVO> getBorrowerMes(@RequestBody EquipmentBorrowRecordEO equipmentBorrowRecordEO){
        try{
            //根据借用人ID，来查出借用人姓名和借用人部门
            EquipmentBorrowRecordVO borrowRecordVO = equipmentBorrowRecordEOService.getBorrowerMes(equipmentBorrowRecordEO);
            return Result.error("0", "查询成功！",borrowRecordVO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
    @ApiOperation(value = "根据设备id获取借用流程id")
    @GetMapping(path ="/getProcessId")
    public ResponseMessage getProcessId(String eqId){
        try{
            String borrowId = equipmentBorrowRecordEOService.getBorrowId(eqId);
            return Result.error("0", "查询成功！",borrowId);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
    @ApiOperation(value = "根据流程id获取借用信息")
    @GetMapping(path ="/getBorrowRecordEO")
    public ResponseMessage getBorrowRecordEO(String id){
        try{
            BorrowRecordVO borrowRecordVO = equipmentBorrowRecordEOService.getBorrowRecordVO(id);
            return Result.error("0", "查询成功！",borrowRecordVO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

	@ApiOperation(value = "|EquipmentBorrowRecordEO|查询")
    @GetMapping("")
    @RequiresPermissions("equipment:equipmentBorrowRecord:list")
    public ResponseMessage<List<EquipmentBorrowRecordEO>> list(EquipmentBorrowRecordEOPage page) throws Exception {
        return Result.success(equipmentBorrowRecordEOService.queryByList(page));
	}

    @ApiOperation(value = "|EquipmentBorrowRecordEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentBorrowRecord:save")
    public ResponseMessage<EquipmentBorrowRecordEO> create(@RequestBody EquipmentBorrowRecordEO equipmentBorrowRecordEO) throws Exception {
        equipmentBorrowRecordEOService.insertSelective(equipmentBorrowRecordEO);
        return Result.success(equipmentBorrowRecordEO);
    }
}
