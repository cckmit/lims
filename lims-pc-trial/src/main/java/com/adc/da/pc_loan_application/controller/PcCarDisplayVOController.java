package com.adc.da.pc_loan_application.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_loan_application.entity.PcCarLoanApplicationEO;
import com.adc.da.pc_loan_application.page.PcCarLoanApplicationEOPage;
import com.adc.da.pc_loan_application.service.PcCarLoanApplicationEOService;
import com.adc.da.pc_loan_application.vo.PcDisPlayVo;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/${restPath}/pc_loan_application/pcCarDisplay")
@Api(description = "|PcCarDisplayVO|借车申请单-查询表")
public class PcCarDisplayVOController extends BaseController<PcDisPlayVo> {

    @Autowired
    private PcCarLoanApplicationEOService pcCarLoanApplicationEOService;
    @Autowired
    BeanMapper beanMapper;


    @BusinessLog(description = "借车申请--分页查询")
    @ApiOperation(value = "|PcCarReturnApplicationEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("pc_return_application:pcCarReturnApplication:page")
    public ResponseMessage<PageInfo<PcDisPlayVo>> page(PcCarLoanApplicationEOPage page) {
        List<PcDisPlayVo> rows = pcCarLoanApplicationEOService.findPcDisPlayVo(page);
        return Result.success("0","查询成功！", beanMapper.mapPage(getPageInfo(page.getPager(), rows),PcDisPlayVo.class));
    }



}
