package com.adc.da.pc_drive.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.pc_drive.vo.PcCurveVo;
import com.adc.da.pc_drive.vo.PcDrivingRecourdVo;
import com.adc.da.sys.annotation.EnableAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_drive.entity.PcDrivingRecordEO;
import com.adc.da.pc_drive.page.PcDrivingRecordEOPage;
import com.adc.da.pc_drive.service.PcDrivingRecordEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_drive/pcDrivingRecord")
@Api(tags = "PcTrialTask-乘用车/商用车试验任务模块")
public class PcDrivingRecordEOController extends BaseController<PcDrivingRecordEO>{

    private static final Logger logger = LoggerFactory.getLogger(PcDrivingRecordEOController.class);

    @Autowired
    private PcDrivingRecordEOService pcDrivingRecordEOService;

	@ApiOperation(value = "|PcDrivingRecordEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("pc_drive:pcDrivingRecord:page")
	@EnableAccess
    public ResponseMessage<PageInfo<PcDrivingRecordEO>> page(PcDrivingRecordEOPage page) throws Exception {
        List<PcDrivingRecordEO> rows = pcDrivingRecordEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }



    @ApiOperation(value = "|PcDrivingRecordEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_drive:pcDrivingRecord:save")
    @EnableAccess
    public ResponseMessage<PcDrivingRecordEO> create(@RequestBody PcDrivingRecordEO pcDrivingRecordEO) throws Exception {
        pcDrivingRecordEOService.insertSelective(pcDrivingRecordEO);
        return Result.success(pcDrivingRecordEO);
    }

    @ApiOperation(value = "|PcDrivingRecordEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_drive:pcDrivingRecord:update")
    @EnableAccess
    public ResponseMessage<PcDrivingRecordEO> update(@RequestBody PcDrivingRecordEO pcDrivingRecordEO) throws Exception {
        pcDrivingRecordEOService.updateByPrimaryKeySelective(pcDrivingRecordEO);
        return Result.success(pcDrivingRecordEO);
    }

    @ApiOperation(value = "|PcDrivingRecordEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_drive:pcDrivingRecord:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcDrivingRecordEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_DRIVING_RECORD where id = {}", id);
        return Result.success();
    }

    @ApiOperation(value = "|PcDrivingRecordVo|联合查询")
    @GetMapping("/record/{trialTaskID},{status}")
    @RequiresPermissions("pc_drive:pcDrivingRecord:get")
    @EnableAccess
    public ResponseMessage<PcDrivingRecourdVo> findDrivingRecord(@PathVariable String trialTaskID, @PathVariable String status) throws Exception {

        return Result.success(pcDrivingRecordEOService.findDrivingRecord(trialTaskID,status));

    }

    @ApiOperation(value = "|PcCurveVo|标准曲线")
    @GetMapping("/standard/{PcTrialTaskID}")
    @RequiresPermissions("pc_drive:pcDrivingRecord:get")
    public ResponseMessage<List<PcCurveVo> > findMileageTrack(@PathVariable String PcTrialTaskID) throws Exception {
        return Result.success(pcDrivingRecordEOService.findStandardCurve(PcTrialTaskID));

    }

    @ApiOperation(value = "|PcCurveVo|实际曲线")
    @GetMapping("/actual/{PcTrialTaskID}")
    @RequiresPermissions("pc_drive:pcDrivingRecord:get")
    public ResponseMessage<List<PcCurveVo> > findActualCurve(@PathVariable String PcTrialTaskID) throws Exception {
        return Result.success(pcDrivingRecordEOService.findActualCurve(PcTrialTaskID));

    }

    @ApiOperation(value = "|PcDrivingRecordEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_drive:pcDrivingRecord:get")
    @EnableAccess
    public ResponseMessage<PcDrivingRecordEO > find(@PathVariable String id) throws Exception {
        return Result.success(pcDrivingRecordEOService.selectByPrimaryKey(id));
    }
}
