package com.adc.da.roadtestcost.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.roadtestcost.entity.RoadTestCostEo;
import com.adc.da.roadtestcost.page.RoadTestCostEoPage;
import com.adc.da.roadtestcost.service.RoadTestCostService;
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

@RestController
@RequestMapping("/${restPath}/bm/testcost")
@Api(tags = "BM-基础数据模块")
public class RoadTestCostController extends BaseController<RoadTestCostEo> {

    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(RoadTestCostController.class);

    @Autowired
    private RoadTestCostService roadTestCostService;

    @ApiOperation(value = "|路试单价分页查询")
    @BusinessLog(description = "路试单价-分页查询")
    @GetMapping("/page")
    public ResponseMessage<PageInfo<RoadTestCostEo>> page(
            RoadTestCostEoPage page,
            @RequestParam(value = "searchPhrase", required = false) String searchPhrase){
        try {
            List<RoadTestCostEo> rows =  roadTestCostService.getRoadTestCostEntityPage(page, searchPhrase);
            //设置总条数
            Integer rowsCount = roadTestCostService.queryByCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功",getPageInfo(page.getPager(), rows));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @BusinessLog(description = "路试单价-新增")
    @ApiOperation(value = "|RoadCostEo|路试单价-新增")
    @PostMapping("")
    public ResponseMessage<RoadTestCostEo> create(@RequestBody RoadTestCostEo roadTestCostEo) throws Exception {
        roadTestCostService.getDao().insertSelective(roadTestCostEo);
        return Result.success("0","新增成功",roadTestCostEo);
    }

    @BusinessLog(description = "路试单价-修改")
    @ApiOperation(value = "|RoadCostEo|路试单价-修改")
    @PostMapping("/edit")
    public ResponseMessage<RoadTestCostEo> update(@RequestBody RoadTestCostEo roadTestCostEo) throws Exception {
        roadTestCostService.getDao().updateByPrimaryKeySelective(roadTestCostEo);
        return Result.success("0","修改成功",roadTestCostEo);
    }

    @BusinessLog(description = "路试单价-删除")
    @ApiOperation(value = "|RoadCostEo|路试单价-删除")
    @DeleteMapping("/{id}")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        roadTestCostService.deleteByPrimaryKey(id);
        logger.info("delete from BM_ROAD_COST where id = {}", id);
        return Result.success("0","删除成功");
    }

}
