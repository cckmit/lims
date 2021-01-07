package com.adc.da.materiel.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.materiel.entity.BorrowRecordEO;
import com.adc.da.materiel.page.BorrowRecordEOPage;
import com.adc.da.materiel.service.BorrowRecordEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/materiel/borrowRecord")
@Api(description = "|BorrowRecordEO|")
public class BorrowRecordEOController extends BaseController<BorrowRecordEO>{

    private static final Logger logger = LoggerFactory.getLogger(BorrowRecordEOController.class);

    @Autowired
    private BorrowRecordEOService borrowRecordEOService;

	@ApiOperation(value = "|BorrowRecordEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("materiel:borrowRecord:page")
    public ResponseMessage<PageInfo<BorrowRecordEO>> page(BorrowRecordEOPage page) throws Exception {
        List<BorrowRecordEO> rows = borrowRecordEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|BorrowRecordEO|查询")
    @GetMapping("")
    @RequiresPermissions("materiel:borrowRecord:list")
    public ResponseMessage<List<BorrowRecordEO>> list(BorrowRecordEOPage page) throws Exception {
        return Result.success(borrowRecordEOService.queryByList(page));
	}

    @ApiOperation(value = "|BorrowRecordEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("materiel:borrowRecord:get")
    public ResponseMessage<BorrowRecordEO> find(@PathVariable String id) throws Exception {
        return Result.success(borrowRecordEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|BorrowRecordEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("materiel:borrowRecord:save")
    public ResponseMessage<BorrowRecordEO> create(@RequestBody BorrowRecordEO borrowRecordEO) throws Exception {
        borrowRecordEOService.insertSelective(borrowRecordEO);
        return Result.success(borrowRecordEO);
    }

    @ApiOperation(value = "|BorrowRecordEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("materiel:borrowRecord:update")
    public ResponseMessage<BorrowRecordEO> update(@RequestBody BorrowRecordEO borrowRecordEO) throws Exception {
        borrowRecordEOService.updateByPrimaryKeySelective(borrowRecordEO);
        return Result.success(borrowRecordEO);
    }

    @ApiOperation(value = "|BorrowRecordEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("materiel:borrowRecord:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        borrowRecordEOService.deleteByPrimaryKey(id);
        logger.info("delete from RES_BORROW_RECORD where id = {}", id);
        return Result.success();
    }

}
