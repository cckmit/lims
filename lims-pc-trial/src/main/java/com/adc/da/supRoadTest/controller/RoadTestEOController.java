package com.adc.da.supRoadTest.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

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
import com.adc.da.supRoadTest.entity.RoadTestEO;
import com.adc.da.supRoadTest.page.RoadTestEOPage;
import com.adc.da.supRoadTest.service.RoadTestEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/${restPath}/supRoadTestEO/roadTest")
@Api(description = "|RoadTestEO|")
public class RoadTestEOController extends BaseController<RoadTestEO>{

    private static final Logger logger = LoggerFactory.getLogger(RoadTestEOController.class);

    @Autowired
    private RoadTestEOService roadTestEOService;

	@ApiOperation(value = "|RoadTestEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("supRoadTestEO:roadTest:page")
    public ResponseMessage<PageInfo<RoadTestEO>> page(
            RoadTestEOPage page,
            @RequestParam(value = "searchPhrase", required = false) String searchPhrase){
	    try{
            List<RoadTestEO> dataList = roadTestEOService.page(page,searchPhrase);
            int i = roadTestEOService.queryByCount(page);
            page.getPager().setRowCount(i);
            return Result.success("0", "查询成功",getPageInfo(page.getPager(), dataList));
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            return Result.error("-1","查询失败!");
        }
    }

	@ApiOperation(value = "|RoadTestEO|查询")
    @GetMapping("")
    @RequiresPermissions("supRoadTestEO:roadTest:list")
    public ResponseMessage<List<RoadTestEO>> list(RoadTestEOPage page) throws Exception {
        return Result.success(roadTestEOService.queryByList(page));
	}

    @ApiOperation(value = "|RoadTestEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("supRoadTestEO:roadTest:get")
    public ResponseMessage<RoadTestEO> find(@PathVariable String id) throws Exception {
        return Result.success(roadTestEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|RoadTestEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supRoadTestEO:roadTest:save")
    public ResponseMessage<RoadTestEO> create(@RequestBody RoadTestEO roadTestEO) throws Exception {
        roadTestEOService.insertSelective(roadTestEO);
        return Result.success(roadTestEO);
    }

    @ApiOperation(value = "|RoadTestEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("supRoadTestEO:roadTest:update")
    public ResponseMessage<RoadTestEO> update(@RequestBody RoadTestEO roadTestEO) throws Exception {
        roadTestEOService.updateByPrimaryKeySelective(roadTestEO);
        return Result.success(roadTestEO);
    }

    @ApiOperation(value = "|RoadTestEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("supRoadTestEO:roadTest:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        roadTestEOService.deleteByPrimaryKey(id);
        logger.info("delete from SUP_ROAD_TEST where id = {}", id);
        return Result.success();
    }

}
