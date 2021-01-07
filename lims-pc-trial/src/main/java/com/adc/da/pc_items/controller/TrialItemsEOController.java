package com.adc.da.pc_items.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.car.service.SaCarDataService;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_items.entity.TrialItemsEO;
import com.adc.da.pc_items.page.TrialItemsEOPage;
import com.adc.da.pc_items.service.TrialItemsEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_items/trialItems")
@Api(tags = "PcTrialTask-乘用车/商用车试验任务模块")
public class TrialItemsEOController extends BaseController<TrialItemsEO>{

    private static final Logger logger = LoggerFactory.getLogger(TrialItemsEOController.class);

    @Autowired
    private TrialItemsEOService trialItemsEOService;

    @Autowired
    private SaCarDataService carDataService;

    @BusinessLog(description = "pc检验项目分页查询")
	@ApiOperation(value = "|TrialItemsEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_items:trialItems:page")
    public ResponseMessage<PageInfo<TrialItemsEO>> page(TrialItemsEOPage page) throws Exception {
        List<TrialItemsEO> rows = trialItemsEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @BusinessLog(description = "pc检验项目列表查询")
	@ApiOperation(value = "|TrialItemsEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_items:trialItems:list")
    public ResponseMessage<List<TrialItemsEO>> list(TrialItemsEOPage page) throws Exception {
        return Result.success(trialItemsEOService.queryByList(page));
	}

    @BusinessLog(description = "pc根据委托Id获取检验项目")
    @ApiOperation(value = "|TrialItemsEO|根据委托Id获取检验项目")
    @GetMapping("/selectByTaskId")
    @RequiresPermissions("pc_items:trialItems:selectByTaskId")
	public ResponseMessage<List<TrialItemsEO>> selectByTaskId(String taskId) throws Exception {
	    return Result.success(trialItemsEOService.selectByTaskId(taskId));
    }

    @BusinessLog(description = "pc检验项目详情")
    @ApiOperation(value = "|TrialItemsEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_items:trialItems:get")
    public ResponseMessage<TrialItemsEO> find(@PathVariable String id) throws Exception {
        return Result.success(trialItemsEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "pc检验项目新增")
    @ApiOperation(value = "|TrialItemsEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_items:trialItems:save")
    public ResponseMessage<TrialItemsEO> create(@RequestBody TrialItemsEO trialItemsEO) throws Exception {
        trialItemsEOService.insertSelective(trialItemsEO);
        return Result.success(trialItemsEO);
    }

    @BusinessLog(description = "pc检验项目修改")
    @ApiOperation(value = "|TrialItemsEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_items:trialItems:update")
    public ResponseMessage<TrialItemsEO> update(@RequestBody TrialItemsEO trialItemsEO) throws Exception {
        trialItemsEOService.updateByPrimaryKeySelective(trialItemsEO);
        return Result.success(trialItemsEO);
    }

    @BusinessLog(description = "pc检验项目删除")
    @ApiOperation(value = "|TrialItemsEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_items:trialItems:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        trialItemsEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_TRIAL_ITEMS where id = {}", id);
        return Result.success();
    }

    /**
     * 根据任务id 查询该任务下所有车辆，并且去重
     * @Title: getCarByTaskId
     * @param taskId 任务id
     * @return
     * @return List
     * @author: rf
     * @date: 2020年10月19日
     */
    @ApiOperation(value = "查询任务下所有车辆")
    @GetMapping("/getCarByTaskId/{taskId}")
    public ResponseMessage getCarByTaskId(@PathVariable String taskId) {
        try {
            List<TrialItemsEO> itemList = trialItemsEOService.getCarByTaskId(taskId);
            List<String> idsList = itemList.stream().map(trialItemsEO -> trialItemsEO.getCarId()).collect(Collectors.toList());
            List<String> idList=new ArrayList<>();
            for (String ids : idsList) {
                if(StringUtils.isNotEmpty(ids)){
                    String[] carId = ids.split(",");
                    for (int i = 0; i < carId.length; i++) {
                        idList.add(carId[i]);
                    }
                }
            }
            List<SaCarDataEO> carDataEOList=null;
            if(idList.size()>0){
                carDataEOList=carDataService.getCarList(idList);
                return Result.error("0", "查询成功!",carDataEOList);
            }
            return Result.error("-1", "未找到车辆!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
}
