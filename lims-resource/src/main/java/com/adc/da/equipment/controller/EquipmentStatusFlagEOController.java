package com.adc.da.equipment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.entity.EquipmentStatusFlagEO;
import com.adc.da.equipment.page.EquipmentStatusFlagEOPage;
import com.adc.da.equipment.service.EquipmentEOService;
import com.adc.da.equipment.service.EquipmentStatusFlagEOService;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/equipment/equipmentStatusFlag")
@Api(tags = "资源管理—设备管理")
public class EquipmentStatusFlagEOController extends BaseController<EquipmentStatusFlagEO>{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentStatusFlagEOController.class);

    @Autowired
    private EquipmentStatusFlagEOService equipmentStatusFlagEOService;
    @Autowired
    private EquipmentEOService equipmentEOService;

    @ApiOperation(value = "状态标记")
    @BusinessLog(description = "设备管理-设备状态标记")
    @PutMapping(path ="/statusFlag")
    @RequiresPermissions("equipment:equipment:statusFlag")
    public ResponseMessage<EquipmentStatusFlagEO> approvalStatusFlag(@RequestBody EquipmentStatusFlagEO equipmentStatusFlagEO){
        try{
            //判断变更前后状态是否相同。相同则不允许变更
            if (equipmentStatusFlagEO.getStaBeforeStatus().trim().equals(equipmentStatusFlagEO.getStaAfterStatus().trim())){
                return Result.error("-1", "相同状态不可转换！");
            }
            //设置ID
            equipmentStatusFlagEO.setId(UUID.randomUserId());
            //设置变更时间为当前时间
            equipmentStatusFlagEO.setStaChangeTime(DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT));
            //设置变更人为当前用户的ID
            equipmentStatusFlagEO.setStaChangePerson(UserUtils.getUserId());
            //将设备状态变更存入到设备变更表中
            equipmentStatusFlagEOService.insertSelective(equipmentStatusFlagEO);
            //在设备表中，将对应设备的状态改为变更后的状态
            equipmentEOService.updateEquStatusById(equipmentStatusFlagEO.getEqId(),equipmentStatusFlagEO.getStaAfterStatus());
            return Result.success("0","状态标记成功!",equipmentStatusFlagEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "状态标记失败!");
        }
    }

	@ApiOperation(value = "|EquipmentStatusFlagEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("equipment:equipmentStatusFlag:page")
    public ResponseMessage<PageInfo<EquipmentStatusFlagEO>> page(EquipmentStatusFlagEOPage page) throws Exception {
        List<EquipmentStatusFlagEO> rows = equipmentStatusFlagEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|EquipmentStatusFlagEO|查询")
    @GetMapping("")
    @RequiresPermissions("equipment:equipmentStatusFlag:list")
    public ResponseMessage<List<EquipmentStatusFlagEO>> list(EquipmentStatusFlagEOPage page) throws Exception {
        return Result.success(equipmentStatusFlagEOService.queryByList(page));
	}

    @ApiOperation(value = "|EquipmentStatusFlagEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("equipment:equipmentStatusFlag:get")
    public ResponseMessage<EquipmentStatusFlagEO> find(@PathVariable String id) throws Exception {
        return Result.success(equipmentStatusFlagEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|EquipmentStatusFlagEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentStatusFlag:save")
    public ResponseMessage<EquipmentStatusFlagEO> create(@RequestBody EquipmentStatusFlagEO equipmentStatusFlagEO) throws Exception {
        equipmentStatusFlagEOService.insertSelective(equipmentStatusFlagEO);
        return Result.success(equipmentStatusFlagEO);
    }

    @ApiOperation(value = "|EquipmentStatusFlagEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentStatusFlag:update")
    public ResponseMessage<EquipmentStatusFlagEO> update(@RequestBody EquipmentStatusFlagEO equipmentStatusFlagEO) throws Exception {
        equipmentStatusFlagEOService.updateByPrimaryKeySelective(equipmentStatusFlagEO);
        return Result.success(equipmentStatusFlagEO);
    }

    @ApiOperation(value = "|EquipmentStatusFlagEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("equipment:equipmentStatusFlag:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        equipmentStatusFlagEOService.deleteByPrimaryKey(id);
        logger.info("delete from RES_EQUIPMENT_STATUS_FLAG where id = {}", id);
        return Result.success();
    }

}
