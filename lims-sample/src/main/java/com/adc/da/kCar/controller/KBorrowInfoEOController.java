package com.adc.da.kCar.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.util.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.kCar.entity.KBorrowInfoEO;
import com.adc.da.kCar.page.KBorrowInfoEOPage;
import com.adc.da.kCar.service.KBorrowInfoEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
//整车样品对接借用历史
@RestController
@RequestMapping("/${restPath}/KBorrowInfoEO/kBorrowInfo")
@Api(description = "|KBorrowInfoEO|")
public class KBorrowInfoEOController extends BaseController<KBorrowInfoEO>{

    private static final Logger logger = LoggerFactory.getLogger(KBorrowInfoEOController.class);

    @Autowired
    private KBorrowInfoEOService kBorrowInfoEOService;

    @Autowired
    private BeanMapper beanMapper;

	@ApiOperation(value = "|KBorrowInfoEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("KBorrowInfoEO:kBorrowInfo:page")
    public ResponseMessage<PageInfo<KBorrowInfoEO>> page(KBorrowInfoEOPage page) {
        try {
            //查询
            List<KBorrowInfoEO> rows =  kBorrowInfoEOService.queryByPage(page);
            //设置总条数
            Integer rowsCount = kBorrowInfoEOService.queryByCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows),KBorrowInfoEO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

	@ApiOperation(value = "|KBorrowInfoEO|查询")
    @GetMapping("")
    @RequiresPermissions("KBorrowInfoEO:kBorrowInfo:list")
    public ResponseMessage<List<KBorrowInfoEO>> list(KBorrowInfoEOPage page) throws Exception {
        return Result.success(kBorrowInfoEOService.queryByList(page));
	}

    @ApiOperation(value = "|KBorrowInfoEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("KBorrowInfoEO:kBorrowInfo:get")
    public ResponseMessage<KBorrowInfoEO> find(@PathVariable String id) throws Exception {
        return Result.success(kBorrowInfoEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|KBorrowInfoEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("KBorrowInfoEO:kBorrowInfo:save")
    public ResponseMessage<KBorrowInfoEO> create(@RequestBody KBorrowInfoEO kBorrowInfoEO) throws Exception {
        kBorrowInfoEOService.insertSelective(kBorrowInfoEO);
        return Result.success(kBorrowInfoEO);
    }

    @ApiOperation(value = "|KBorrowInfoEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("KBorrowInfoEO:kBorrowInfo:update")
    public ResponseMessage<KBorrowInfoEO> update(@RequestBody KBorrowInfoEO kBorrowInfoEO) throws Exception {
        kBorrowInfoEOService.updateByPrimaryKeySelective(kBorrowInfoEO);
        return Result.success(kBorrowInfoEO);
    }

    @ApiOperation(value = "|KBorrowInfoEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("KBorrowInfoEO:kBorrowInfo:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        kBorrowInfoEOService.deleteByPrimaryKey(id);
        logger.info("delete from K_BORROW_INFO where id = {}", id);
        return Result.success();
    }

}
