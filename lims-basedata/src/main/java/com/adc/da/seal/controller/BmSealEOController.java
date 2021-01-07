package com.adc.da.seal.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.log.annotation.BusinessLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.seal.entity.BmSealEO;
import com.adc.da.seal.page.BmSealEOPage;
import com.adc.da.seal.service.BmSealEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/${restPath}/seal/bmSeal")
@Api(tags = "BM-基础数据模块")
@SuppressWarnings("all")
public class BmSealEOController extends BaseController<BmSealEO>{

    private static final Logger logger = LoggerFactory.getLogger(BmSealEOController.class);

    @Autowired
    private BmSealEOService bmSealEOService;

    @BusinessLog(description = "公章签名-分页查询")
	@ApiOperation(value = "|BmSealEO|公章签名-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("seal:bmSeal:page")
    public ResponseMessage<PageInfo<BmSealEO>> page(BmSealEOPage page,String searchFeild) throws Exception {
	    try{
            List<BmSealEO> rows = bmSealEOService.queryByPage(page,searchFeild);
            page.getPager().setRowCount(bmSealEOService.queryByCount(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch(Exception e){
	        logger.error("-1","查询失败！");
	        return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "公章签名-分页查询")
	@ApiOperation(value = "|BmSealEO|公章签名-查询")
    @GetMapping("")
    @RequiresPermissions("seal:bmSeal:list")
    public ResponseMessage<List<BmSealEO>> list(BmSealEOPage page) throws Exception {
        return Result.success(bmSealEOService.queryByList(page));
	}

    @BusinessLog(description = "公章签名-详情")
    @ApiOperation(value = "|BmSealEO|公章签名-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("seal:bmSeal:get")
    public ResponseMessage<BmSealEO> find(@PathVariable String id) throws Exception {
        return Result.success(bmSealEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "公章签名-新增")
    @ApiOperation(value = "|BmSealEO|公章签名-新增")
    @PostMapping("")
    @RequiresPermissions("seal:bmSeal:save")
    public ResponseMessage<BmSealEO> create(BmSealEO bmSealEO, @RequestParam("file") MultipartFile file) throws Exception {
        if(file==null){
            return Result.error("-1","请上传图片！");
        }
        if("-1".equals(bmSealEOService.savefile(file, bmSealEO))){
            return Result.error("-1","公章编号已存在，请输入新的编号！");
        }
        return Result.success(bmSealEO);
    }

    @BusinessLog(description = "公章签名-修改")
    @ApiOperation(value = "|BmSealEO|公章签名-修改")
    @PostMapping("/edit")
    @RequiresPermissions("seal:bmSeal:update")
    public ResponseMessage<BmSealEO> update(MultipartFile file, BmSealEO bmSealEO) throws Exception {
        if("-1".equals(bmSealEOService.savefile(file, bmSealEO))){
            return Result.error("-1","公章编号已存在，请输入新的编号！");
        }
        return Result.success(bmSealEO);
    }

    @BusinessLog(description = "公章签名-删除")
    @ApiOperation(value = "|BmSealEO|公章签名-删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("seal:bmSeal:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        bmSealEOService.deleteByPrimaryKey(id);
        logger.info("delete from BM_SEAL where id = {}", id);
        return Result.success();
    }

    @BusinessLog(description = "公章签名-状态修改")
    @ApiOperation(value = "|BmSealEO|公章签名-状态修改")
    @PutMapping("/updateSealStatus")
    @RequiresPermissions("seal:bmSeal:updateSealStatus")
    public ResponseMessage<BmSealEO> updateSealStatus(String id, String status) throws Exception {
        int result = bmSealEOService.updateSealStatus(id,status);
        if(result!=-1){
            return Result.success();
        }else{
            return Result.error("-1","状态更新失败！");
        }
    }

}
