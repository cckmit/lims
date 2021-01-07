package com.adc.da.calender.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.adc.da.calender.activeMQ.ProducerMQ;
import com.adc.da.calender.util.ExcelUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.util.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.converter.ExcelToFoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.page.PersonCalenderEOPage;
import com.adc.da.calender.service.PersonCalenderEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/calender/personCalender")
@Api(tags = "PersonSpace-工作日历")
public class PersonCalenderEOController extends BaseController<PersonCalenderEO>{

    private static final Logger logger = LoggerFactory.getLogger(PersonCalenderEOController.class);

    @Autowired
    private PersonCalenderEOService personCalenderEOService;

    

    @BusinessLog(description="个人空间-工作日历-查询")
	@ApiOperation(value = "|PersonCalenderEO|查询")
    @GetMapping("")
    @RequiresPermissions("calender:personCalender:list")
    public ResponseMessage<List<PersonCalenderEO>> list(PersonCalenderEOPage page) throws Exception {
        page.setCcCreateById(UserUtils.getUser().getUsid());
        return Result.success(personCalenderEOService.queryByList(page));
	}

    @BusinessLog(description="个人空间-工作日历-查看")
    @ApiOperation(value = "|PersonCalenderEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("calender:personCalender:get")
    public ResponseMessage<PersonCalenderEO> find(@PathVariable String id) throws Exception {
        return Result.success(personCalenderEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description="个人空间-工作日历-新增")
    @ApiOperation(value = "|PersonCalenderEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("calender:personCalender:save")
    public ResponseMessage<PersonCalenderEO> create(@RequestBody PersonCalenderEO personCalenderEO) throws Exception {
    	personCalenderEO.setCcCreateById(UserUtils.getUserId());
    	personCalenderEOService.insertSelective(personCalenderEO);
        return Result.success("0","添加成功",personCalenderEO);
    }

    @BusinessLog(description="个人空间-工作日历-修改")
    @ApiOperation(value = "|PersonCalenderEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("calender:personCalender:update")
    public ResponseMessage<PersonCalenderEO> update(@RequestBody PersonCalenderEO personCalenderEO) throws Exception {
        personCalenderEOService.updateByPrimaryKeySelective(personCalenderEO);
        return Result.success("0","修改成功",personCalenderEO);
    }

    @BusinessLog(description="个人空间-工作日历-删除")
    @ApiOperation(value = "|PersonCalenderEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("calender:personCalender:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        personCalenderEOService.deleteByPrimaryKey(id);
        logger.info("delete from TP_PERSON_CALENDER where id = {}", id);
        return Result.success("0","删除成功");
    }

    /**
     * 工作日历导出
     */
    @BusinessLog(description="个人空间-工作日历-导出")
    @ApiOperation(value = "|PersonCalenderEO|导出")
    @GetMapping("/exportExcel")
    @RequiresPermissions("calender:personCalender:exportExcel")
    public void exportExcel(HttpServletResponse response, HttpServletRequest request, PersonCalenderEOPage page) throws Exception {
        try {
            page.setCcCreateById(UserUtils.getUserId());
            List<PersonCalenderEO> personCalenderEOList = personCalenderEOService.queryByList(page);
            ExcelUtils.excelFile(personCalenderEOList,response,request);
        }catch(Exception e){
            logger.error("-1","导出失败！");
        }

    }

}
