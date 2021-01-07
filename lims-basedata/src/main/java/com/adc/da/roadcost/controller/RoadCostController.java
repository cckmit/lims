package com.adc.da.roadcost.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.roadcost.RoadCostPage;
import com.adc.da.roadcost.entity.RoadCostEo;
import com.adc.da.roadcost.service.RoadCostService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础数据管理-路送单价表
 */

@RestController
@RequestMapping("${restPath}/bm/cost")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class RoadCostController extends BaseController<RoadCostEo> {
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RoadCostController.class);

    @Autowired
    private RoadCostService roadCostService;

    @ApiOperation(value = "|路送单价分页查询")
    @BusinessLog(description = "路送单价-分页查询")
    @GetMapping("/page")
    public ResponseMessage<PageInfo<RoadCostEo>> page(RoadCostPage roadCostPage , String searchPhrase){

        try {
            List<RoadCostEo> rows = roadCostService.page(roadCostPage,searchPhrase);
            Integer count = roadCostService.queryByCount(roadCostPage);
            roadCostPage.getPager().setRowCount(count);
            return Result.success("0","查询成功",getPageInfo(roadCostPage.getPager(), rows));
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return Result.error("-1","查询失败");
        }
    }

    @BusinessLog(description = "路送单价-新增")
    @ApiOperation(value = "|RoadCostEo|路送单价-新增")
    @PostMapping("")
    public ResponseMessage<RoadCostEo> create(@RequestBody RoadCostEo roadCostEo) throws Exception {
        roadCostService.getDao().insertSelective(roadCostEo);
        return Result.success("0","新增成功",roadCostEo);
    }

    @BusinessLog(description = "路送单价-修改")
    @ApiOperation(value = "|RoadCostEo|路送单价-修改")
    @PostMapping("/edit")
    public ResponseMessage<RoadCostEo> update(@RequestBody RoadCostEo roadCostEo) throws Exception {
        roadCostService.getDao().updateByPrimaryKeySelective(roadCostEo);
        return Result.success("0","修改成功",roadCostEo);
    }

    @BusinessLog(description = "路送单价-删除")
    @ApiOperation(value = "|RoadCostEo|路送单价-删除")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        roadCostService.deleteByPrimaryKey(id);
        logger.info("delete from BM_ROAD_COST where id = {}", id);
        return Result.success("0","删除成功");
    }

}

