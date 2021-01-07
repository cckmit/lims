package com.adc.da.equipment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.equipment.VO.EquipmentScrapRecordVO;
import com.adc.da.equipment.service.EquipmentEOService;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.equipment.entity.EquipmentScrapRecordEO;
import com.adc.da.equipment.page.EquipmentScrapRecordEOPage;
import com.adc.da.equipment.service.EquipmentScrapRecordEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/equipment/equipmentScrapRecord")
@Api(tags = "资源管理—设备管理")
public class EquipmentScrapRecordEOController extends BaseController<EquipmentScrapRecordEO>{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentScrapRecordEOController.class);

    @Autowired
    private EquipmentScrapRecordEOService equipmentScrapRecordEOService;
    @Autowired
    private EquipmentEOService equipmentEOService;
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    @ApiOperation(value = "设备报废")
    @BusinessLog(description = "设备管理-设备报废")
    @PutMapping(path ="/scrap")
    @RequiresPermissions("equipment:equipment:scrap")
    public ResponseMessage<EquipmentScrapRecordEO> approvalScrap(@RequestBody EquipmentScrapRecordEO equipmentScrapRecordEO){
        try{
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrApplicantName())){
                return Result.error("-1", "申请人不可为空！");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrApplicantDepartment())){
                return Result.error("-1", "申请部门不可为空！");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrScrapTime())){
                return Result.error("-1", "报废时间不可为空！");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrScrapReason())){
                return Result.error("-1", "报废原因不可为空！");
            }
            //判断当前设备是否处于报废状态
            if(equipmentScrapRecordEOService.queryCountForEqScrap(equipmentScrapRecordEO) != 0){
                return Result.error("-1", "当前设备已经处于报废状态！");
            }
            equipmentScrapRecordEOService.equipmentScrap(equipmentScrapRecordEO);
            return Result.success("0","报废成功!",equipmentScrapRecordEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "报废失败!");
        }
    }

    @ApiOperation(value = "设备批量报废")
    @BusinessLog(description = "设备管理-设备批量报废")
    @PutMapping(path ="/batchScrap")
    @RequiresPermissions("equipment:equipment:batchScrap")
    public ResponseMessage<EquipmentScrapRecordEO> approvalBatchScrap(@RequestBody EquipmentScrapRecordEO equipmentScrapRecordEO){
        try{
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrApplicantName())){
                return Result.error("-1", "申请人不可为空！");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrApplicantDepartment())){
                return Result.error("-1", "申请部门不可为空！");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrScrapTime())){
                return Result.error("-1", "报废时间不可为空！");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrScrapReason())){
                return Result.error("-1", "报废原因不可为空！");
            }
            String idsString = equipmentScrapRecordEO.getId();
            String[] ids = idsString.split(",");
            if (ids.length == 0){
                return Result.error("-1", "请选择报废设备！");
            }
            List<EquipmentScrapRecordEO> equipmentScrapRecordEOList = new ArrayList<>();
            //设置判断变量
            boolean isDel = true;
            //循环校验ids数组里面是否有非空闲状态，如果有，则不允许此次批量报废
            for(String id : ids){
                if (!equipmentEOService.queryUseStatusByIds(id)){
                    isDel = false;
                    break;
                }else {
                    //将ids数组中所有的id创建一个对象，存在list中
                    EquipmentScrapRecordEO equipmentScrapRecord = new EquipmentScrapRecordEO();
                    BeanUtils.copyProperties(equipmentScrapRecordEO,equipmentScrapRecord);
                    equipmentScrapRecord.setEqId(id);
                    equipmentScrapRecordEOList.add(equipmentScrapRecord);
                }
            }
            if(!isDel) {
                return Result.error("-1", "被报废设备存在非空闲情况,不可被报废！");
            }
            for(EquipmentScrapRecordEO equipmentScrapRecord : equipmentScrapRecordEOList){
                equipmentScrapRecordEOService.equipmentScrap(equipmentScrapRecord);
            }
            return Result.success("0","报废成功!",equipmentScrapRecordEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "报废失败!");
        }
    }

    @ApiOperation(value = "设备还原")
    @BusinessLog(description = "设备管理-设备还原")
    @PutMapping(path ="/recover")
    @RequiresPermissions("equipment:equipment:recover")
    public ResponseMessage<EquipmentScrapRecordEO> approvalRecover(@RequestBody EquipmentScrapRecordEO equipmentScrapRecordEO){
        try{
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrApplicantName())){
                return Result.error("-1", "还原人不可为空!");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrRecoverReason())){
                return Result.error("-1", "还原原因不可为空!");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrRecoverTime())){
                return Result.error("-1", "还原时间不可为空!");
            }
            if (StringUtils.isEmpty(equipmentScrapRecordEO.getScrApplicantDepartment())){
                return Result.error("-1", "申请部门不可为空!");
            }
            if(equipmentScrapRecordEOService.queryCountForEqScrap(equipmentScrapRecordEO) != 0){
                ResponseMessage<EquipmentScrapRecordEO> result = equipmentScrapRecordEOService.equipmentRecover(equipmentScrapRecordEO,equipmentScrapRecordEO.getScrApplicantId());
                return  result;
            }
            return Result.error("-1", "当前设备未处于报废状态！");
            //return Result.success("0","归还成功!",equipmentBorrowRecordEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "还原失败!");
        }
    }

    @ApiOperation(value = "查出设备的报废/还原记录")
    @GetMapping("/getScrapRecordByPage")
    public ResponseMessage<PageInfo<EquipmentScrapRecordEO>> getScrapRecordByPage(EquipmentScrapRecordEOPage page){
        try {
            //查询
            List<EquipmentScrapRecordEO> rows = equipmentScrapRecordEOService.getScrapRecordByPage(page);
            //设置总条数
            Integer rowsCount = equipmentScrapRecordEOService.queryCountForScrapRecord(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", getPageInfo(page.getPager(), rows));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @ApiOperation(value = "查看设备报废人姓名及其部门")
    @BusinessLog(description = "设备管理-查看设备报废人及其部门")
    @GetMapping(path ="/getScraperMes")
    public ResponseMessage<EquipmentScrapRecordVO> getScraperMes(@RequestBody EquipmentScrapRecordEO equipmentScrapRecordEO){
        try{
            //根据报废人ID，来查出报废人姓名和报废人部门
            EquipmentScrapRecordVO scrapRecordVO = equipmentScrapRecordEOService.getScraperMes(equipmentScrapRecordEO);
            return Result.error("0", "查询成功！",scrapRecordVO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @ApiOperation(value = "|EquipmentScrapRecordEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("equipment:equipmentScrapRecord:page")
    public ResponseMessage<PageInfo<EquipmentScrapRecordEO>> page(EquipmentScrapRecordEOPage page) throws Exception {
        List<EquipmentScrapRecordEO> rows = equipmentScrapRecordEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|EquipmentScrapRecordEO|查询")
    @GetMapping("")
    @RequiresPermissions("equipment:equipmentScrapRecord:list")
    public ResponseMessage<List<EquipmentScrapRecordEO>> list(EquipmentScrapRecordEOPage page) throws Exception {
        return Result.success(equipmentScrapRecordEOService.queryByList(page));
	}

    @ApiOperation(value = "|EquipmentScrapRecordEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentScrapRecord:save")
    public ResponseMessage<EquipmentScrapRecordEO> create(@RequestBody EquipmentScrapRecordEO equipmentScrapRecordEO) throws Exception {
        equipmentScrapRecordEOService.insertSelective(equipmentScrapRecordEO);
        return Result.success(equipmentScrapRecordEO);
    }

}
