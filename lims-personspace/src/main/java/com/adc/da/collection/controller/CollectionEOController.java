package com.adc.da.collection.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.base.page.Pager;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.collection.entity.CollectionEO;
import com.adc.da.collection.page.CollectionEOPage;
import com.adc.da.collection.service.CollectionEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/collection/collection")
@Api(tags = { "PersonSpace-我的收藏" })
public class CollectionEOController extends BaseController<CollectionEO>{

    private static final Logger logger = LoggerFactory.getLogger(CollectionEOController.class);

    @Autowired
    private CollectionEOService collectionEOService;

    @BusinessLog(description = "个人空间-我的收藏-分页查询")
	@ApiOperation(value = "|CollectionEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("collection:collection:page")
    public ResponseMessage<PageInfo<CollectionEO>> page(CollectionEOPage page, String searchfiled) throws Exception {
	        try{
                List<CollectionEO> rows = collectionEOService.queryByPage(page,searchfiled);
                //总条数
                page.getPager().setRowCount(collectionEOService.queryByCount(page));
                return Result.success("0","查询成功",getPageInfo(page.getPager(), rows));
            }catch(Exception e){
                logger.error(e.getMessage(),CollectionEO.class);
                return Result.error("查询失败！！！");
            }
    }

    @BusinessLog(description = "个人空间-我的收藏-查看")
    @ApiOperation(value = "|CollectionEO|详情")
    @GetMapping("/{id}")
//    @RequiresPermissions("collection:collection:get")
    public ResponseMessage<CollectionEO> find(@PathVariable String id) throws Exception {
        return Result.success("0","查询成功",collectionEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "个人空间-我的收藏-新增")
    @ApiOperation(value = "|CollectionEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("collection:collection:save")
    public ResponseMessage<CollectionEO> create(@RequestBody CollectionEO collectionEO) throws Exception {
        collectionEOService.insertSelective(collectionEO);
        return Result.success("0","新增成功",collectionEO);
    }

    @BusinessLog(description = "个人空间-我的收藏-修改")
    @ApiOperation(value = "|CollectionEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("collection:collection:update")
    public ResponseMessage<CollectionEO> update(@RequestBody CollectionEO collectionEO) throws Exception {
        collectionEOService.updateByPrimaryKeySelective(collectionEO);
        return Result.success("0","编辑成功",collectionEO);
    }

    @BusinessLog(description = "个人空间-我的收藏-取消收藏")
    @ApiOperation(value = "|CollectionEO|取消收藏")
    @DeleteMapping("/{id}")
//    @RequiresPermissions("collection:collection:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        collectionEOService.deleteByPrimaryKey(id);
        logger.info("delete from TP_COLLECTION where id = {}", id);
        return Result.success("0","取消收藏成功");
    }

}
