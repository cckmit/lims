package com.adc.da.equipment.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.equipment.VO.EquipmentCheckVO;
import com.adc.da.equipment.entity.EquipmentEO;
import com.adc.da.equipment.service.EquipmentEOService;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.equipment.entity.EquipmentCheckEO;
import com.adc.da.equipment.page.EquipmentCheckEOPage;
import com.adc.da.equipment.service.EquipmentCheckEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/equipment/equipmentCheck")
@Api(tags = "资源管理—设备管理")
public class EquipmentCheckEOController extends BaseController<EquipmentCheckEO>{

    private static final Logger logger = LoggerFactory.getLogger(EquipmentCheckEOController.class);

    @Autowired
    private EquipmentCheckEOService equipmentCheckEOService;
    @Autowired
    private EquipmentEOService equipmentEOService;
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;

    @ApiOperation(value = "核检记录-新增核检计划")
    @BusinessLog(description = "设备管理-新增核检计划")
    @PutMapping(path ="/savePlanCheck")
    @RequiresPermissions("equipment:equipment:savePlanCheck")
    public ResponseMessage<EquipmentCheckEO> savePlanCheck(@RequestBody List<EquipmentCheckEO> equipmentCheckEOList){
        try{
            if(equipmentCheckEOList.size() == 0){
                return Result.error("2","没有数据进行保存！");
            }
            if(equipmentCheckEOService.savePlanCheck(equipmentCheckEOList)){
                return Result.success("0","保存成功!");
            }else {
                return Result.error("-1", "保存失败!请填写必填项！");
            }

        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "保存失败!");
        }
    }

    @ApiOperation(value = "核检记录-删除核检计划")
    @BusinessLog(description = "设备管理-删除核检计划")
    @DeleteMapping(path ="/delPlanCheck")
    public ResponseMessage<EquipmentCheckEO> delPlanCheck(@RequestBody EquipmentCheckEO equipmentCheckEO){
        try{
            equipmentCheckEOService.delPlanCheck(equipmentCheckEO);
            return Result.success("0","删除成功!",equipmentCheckEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "删除失败!");
        }
    }

    @ApiOperation(value = "核检记录-查出对应设备Id所有的核检计划")
    @BusinessLog(description = "设备管理-查出核检计划")
    @PutMapping(path ="/findPlanCheck")
    public  List<EquipmentCheckVO> findPlanCheck(@RequestBody EquipmentCheckEO equipmentCheckEO){
        try{
            String eqId = equipmentCheckEO.getEqId();
            return beanMapper.mapList(equipmentCheckEOService.findPlanCheck(eqId), EquipmentCheckVO.class);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @ApiOperation(value = "核检记录-查出对应设备Id所有的核检记录")
    @BusinessLog(description = "设备管理-查出核检记录")
    @PutMapping(path ="/findCheckRecord")
    public List<EquipmentCheckVO> findCheckRecord(@RequestBody EquipmentCheckEO equipmentCheckEO){
        try{
            String eqId = equipmentCheckEO.getEqId();
            return beanMapper.mapList(equipmentCheckEOService.findCheckRecord(eqId), EquipmentCheckVO.class);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "核检记录-查出对应设备Id所有的核检记录分页")
    @GetMapping("/findCheckRecordByPage")
    public ResponseMessage<PageInfo<EquipmentCheckVO>> findCheckRecordByPage(EquipmentCheckEOPage page){
        try {
            //查询
            List<EquipmentCheckEO> rows = equipmentCheckEOService.findCheckRecordByPage(page);
            //设置总条数
            Integer rowsCount = equipmentCheckEOService.queryForCheckRecordByPage(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), EquipmentCheckVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
    @ApiOperation(value = "核检记录-实施核检计划")
    @BusinessLog(description = "设备管理-实施核检计划")
    @PutMapping(path ="/updateCheckPlan")
    public ResponseMessage<EquipmentCheckEO> updateCheckPlan(@RequestBody EquipmentCheckEO equipmentCheckEO){
        try{
            //实施核检计划。若用户有更新，则同时更新计划信息
            equipmentCheckEOService.updateCheckPlan(equipmentCheckEO);
            //根据用户填写的完成日期和有效期。来自动生成下一条核检计划。新的核检计划信息除了计划核检日期外，其他与原计划相同
            equipmentCheckEOService.insertNewCheckPlan(equipmentCheckEO);
            //对该设备的状态进行判断。如果为超期未检，则需要判断是否改变设备状态。否则，直接返回
            String eqId = equipmentCheckEO.getEqId();
            EquipmentEO equipmentEO = equipmentEOService.selectByPrimaryKey(eqId);
            if ("3".equals(equipmentEO.getEqStatus())){
                //所有该设备的计划
                List<EquipmentCheckEO> planCheck = equipmentCheckEOService.findPlanCheck(eqId);
                //当前时间
                String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT4);
                System.out.println(date);
                boolean isFlag = true;
                //循环判断该设备所有的计划是否都在当前时间之后
                for (EquipmentCheckEO equipmentCheck : planCheck){
                    if((date.compareTo(equipmentCheck.getEqCkCheckTimePlan()))>0){
                        isFlag = false;
                        break;
                    }
                }
                //改变设备的状态从 超期未检到正常
                if (isFlag){
                    equipmentEOService.updateEquStatusById(eqId,"0");
                }
                return Result.success("0","核检成功!",equipmentCheckEO);
            }
            return Result.success("0","核检成功!",equipmentCheckEO);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "核检失败!");
        }
    }

	@ApiOperation(value = "|EquipmentCheckEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("equipment:equipmentCheck:page")
    public ResponseMessage<PageInfo<EquipmentCheckEO>> page(EquipmentCheckEOPage page) throws Exception {
        List<EquipmentCheckEO> rows = equipmentCheckEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|EquipmentCheckEO|查询")
    @GetMapping("")
    @RequiresPermissions("equipment:equipmentCheck:list")
    public ResponseMessage<List<EquipmentCheckEO>> list(EquipmentCheckEOPage page) throws Exception {
        return Result.success(equipmentCheckEOService.queryByList(page));
	}

    @ApiOperation(value = "|EquipmentCheckEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("equipment:equipmentCheck:get")
    public ResponseMessage<EquipmentCheckEO> find(@PathVariable String id) throws Exception {
        return Result.success(equipmentCheckEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|EquipmentCheckEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentCheck:save")
    public ResponseMessage<EquipmentCheckEO> create(@RequestBody EquipmentCheckEO equipmentCheckEO) throws Exception {
        equipmentCheckEOService.insertSelective(equipmentCheckEO);
        return Result.success(equipmentCheckEO);
    }

    @ApiOperation(value = "|EquipmentCheckEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("equipment:equipmentCheck:update")
    public ResponseMessage<EquipmentCheckEO> update(@RequestBody EquipmentCheckEO equipmentCheckEO) throws Exception {
        equipmentCheckEOService.updateByPrimaryKeySelective(equipmentCheckEO);
        return Result.success(equipmentCheckEO);
    }

    @ApiOperation(value = "|EquipmentCheckEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("equipment:equipmentCheck:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        equipmentCheckEOService.deleteByPrimaryKey(id);
        logger.info("delete from RES_EQUIPMENT_CHECK where id = {}", id);
        return Result.success();
    }

}
