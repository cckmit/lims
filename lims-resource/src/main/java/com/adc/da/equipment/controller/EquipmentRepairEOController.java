package com.adc.da.equipment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.service.EquipmentEOService;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.equipment.entity.EquipmentRepairEO;
import com.adc.da.equipment.page.EquipmentRepairEOPage;
import com.adc.da.equipment.service.EquipmentRepairEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/equipment/equipmentRepair")
@Api(tags = "资源管理—设备管理")
public class EquipmentRepairEOController extends BaseController<EquipmentRepairEO>{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentRepairEOController.class);

    @Autowired
    private EquipmentRepairEOService equipmentRepairEOService;

    @ApiOperation(value = "设备维修")
    @BusinessLog(description = "设备管理-设备维修")
    @PutMapping(path ="/eqRepair")
    @RequiresPermissions("equipment:equipment:repair")
    public ResponseMessage<EquipmentRepairEO> approvalRepair(@RequestBody EquipmentRepairEO equipmentRepairEO){
        try{
            String eqId = equipmentRepairEO.getEqId();
            //根据维修设备ID得到维修设备的状态，从而更新设备状态
            equipmentRepairEOService.updateEqStatusByEqId(eqId);
            //在维修表中插入记录
            equipmentRepairEO.setId(UUID.randomUserId());
            equipmentRepairEOService.insertSelective(equipmentRepairEO);
            return Result.success("0","设备维修成功!",equipmentRepairEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "设备维修失败!");
        }
    }
    @ApiOperation(value = "查出设备的维修记录")
    @GetMapping("/getRepairRecordByPage")
    public ResponseMessage<PageInfo<EquipmentRepairEO>> getRepairRecordByPage(EquipmentRepairEOPage page){
        try {
            //查询
            List<EquipmentRepairEO> rows = equipmentRepairEOService.getRepairRecordByPage(page);
            //设置总条数
            Integer rowsCount = equipmentRepairEOService.queryCountForRepairRecord(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", getPageInfo(page.getPager(), rows));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
	@ApiOperation(value = "|EquipmentRepairEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("equipment:equipmentRepair:page")
    public ResponseMessage<PageInfo<EquipmentRepairEO>> page(EquipmentRepairEOPage page) throws Exception {
        List<EquipmentRepairEO> rows = equipmentRepairEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|EquipmentRepairEO|查询")
    @GetMapping("")
    @RequiresPermissions("equipment:equipmentRepair:list")
    public ResponseMessage<List<EquipmentRepairEO>> list(EquipmentRepairEOPage page) throws Exception {
        return Result.success(equipmentRepairEOService.queryByList(page));
	}

    @ApiOperation(value = "|EquipmentRepairEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("equipment:equipmentRepair:get")
    public ResponseMessage<EquipmentRepairEO> find(@PathVariable String id) throws Exception {
        return Result.success(equipmentRepairEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|EquipmentRepairEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentRepair:save")
    public ResponseMessage<EquipmentRepairEO> create(@RequestBody EquipmentRepairEO equipmentRepairEO) throws Exception {
        equipmentRepairEOService.insertSelective(equipmentRepairEO);
        return Result.success(equipmentRepairEO);
    }

    @ApiOperation(value = "|EquipmentRepairEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentRepair:update")
    public ResponseMessage<EquipmentRepairEO> update(@RequestBody EquipmentRepairEO equipmentRepairEO) throws Exception {
        equipmentRepairEOService.updateByPrimaryKeySelective(equipmentRepairEO);
        return Result.success(equipmentRepairEO);
    }

    @ApiOperation(value = "|EquipmentRepairEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("equipment:equipmentRepair:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        equipmentRepairEOService.deleteByPrimaryKey(id);
        logger.info("delete from RES_EQUIPMENT_REPAIR where id = {}", id);
        return Result.success();
    }

}
