package com.adc.da.pc_trust.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.adc.da.pc_trust.page.TrialTaskChangeEOPage;
import com.adc.da.pc_trust.vo.TrialTaskChangeVO;
import com.adc.da.sys.entity.RequestEO;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_execute.page.CostForCashOutPage;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.page.TrialTaskEOPage;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.pc_trust.vo.PCTrialTaskVO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController("pcTrialTaskEOController")
@RequestMapping("/${restPath}/pc_trust/trialTask")
@Api(tags = "PcTrialTask-乘用车/商用车试验任务模块")
public class TrialTaskEOController extends BaseController<TrialTaskEO>{

    private static final Logger logger = LoggerFactory.getLogger(TrialTaskEOController.class);

    @Autowired
    private TrialTaskEOService trialTaskEOService;
    
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao; 
    
    @Value("${file.path}")
    private String filepath;

    @BusinessLog(description = "pc委托分页查询")
	@ApiOperation(value = "|TrialTaskEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("pc_trust:trialTask:page")
    @EnableAccess
    public ResponseMessage<PageInfo<TrialTaskEO>> page(TrialTaskEOPage page) throws Exception {
	    try{
            List<TrialTaskEO> rows = trialTaskEOService.queryByPage(page);
            page.getPager().setRowCount(trialTaskEOService.queryByCount(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch(Exception e){
	        logger.error(e.getMessage());
	        return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "pc委托列表查询")
	@ApiOperation(value = "|TrialTaskEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_trust:trialTask:list")
    public ResponseMessage<List<TrialTaskEO>> list(TrialTaskEOPage page) throws Exception {
        return Result.success(trialTaskEOService.queryByList(page));
	}

    @BusinessLog(description = "pc委托详情")
    @ApiOperation(value = "|TrialTaskEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_trust:trialTask:get")
    public ResponseMessage<TrialTaskEO> find(@PathVariable String id) throws Exception {
        return Result.success(trialTaskEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "pc委托新增")
    @ApiOperation(value = "|TrialTaskEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("pc_trust:trialTask:save")
    public ResponseMessage<TrialTaskEO> create(@RequestBody TrialTaskEO trialTaskEO) throws Exception {
        trialTaskEOService.insertSelective(trialTaskEO);
        return Result.success("0","操作成功！",trialTaskEO);
    }

    /**
     * 试验任务申请变更
     * @param taskChangeVO
     * @return
     * @throws Exception
     */
    @BusinessLog(description = "试验任务申请变更")
    @ApiOperation(value = "|TrialTaskEO|试验任务申请变更")
    @PostMapping("/changeTask")
    public ResponseMessage<TrialTaskChangeVO> changeTask(@RequestBody TrialTaskChangeVO taskChangeVO) throws Exception {
        trialTaskEOService.changeTask(taskChangeVO);
        return Result.success("0","操作成功！",taskChangeVO);
    }

    /**
     * 试验任务申请变更-流程审批
     * @param request
     * @param requestEO
     */
    @ApiOperation(value = "|试验任务申请变更-流程审批")
    @BusinessLog(description = "试验任务申请变更-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("trialTask:TrialTaskEO:approvalProcess")
    public ResponseMessage approvalProcess(
            HttpServletRequest request, @RequestBody RequestEO requestEO) {
        try {
            //校验是否为空
            if (StringUtils.isEmpty(requestEO)) {
                return Result.error("-1", "审批信息不可为空");
            } else {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-1", "任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())) {
                    return Result.error("-1", "流程实例Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-1", "审批意见不可为空");
                }
            }
            trialTaskEOService.approvalProcess(request, requestEO);
            return Result.success("0", "流程审核成功!");
        } catch (Exception e) {
            logger.error("-1", "流程审批失败！");
            return Result.error("-1", "流程审批失败！");
        }
    }

    /**
     * 试验任务申请变更分页查询
     */
    @BusinessLog(description = "试验任务申请变更分页查询")
    @ApiOperation(value = "|TrialTaskEO|试验任务申请变更分页查询")
    @ApiModelProperty("任务编号")
    @GetMapping("/changeTaskPage")
    public ResponseMessage<PageInfo<TrialTaskEO>> changeTaskPage(
            @ApiParam(value = "试验任务编号唯一标识", required = true) @RequestParam(value = "taskNumber") String taskNumber,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) throws Exception{
        try{
            TrialTaskChangeEOPage trialTaskChangeEOPage = new TrialTaskChangeEOPage();
            trialTaskChangeEOPage.setTaskNumber(taskNumber);
            trialTaskChangeEOPage.setPage(page);
            trialTaskChangeEOPage.setPageSize(pageSize);
            List<TrialTaskEO> rows = trialTaskEOService.changeTaskByPage(trialTaskChangeEOPage);
            trialTaskChangeEOPage.getPager().setRowCount(trialTaskEOService.changeTaskByCount(trialTaskChangeEOPage));
            return Result.success(getPageInfo(trialTaskChangeEOPage.getPager(), rows));
        }catch(Exception e){
            logger.error(e.getMessage());
            return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "pc委托提交申请")
    @ApiOperation(value = "|TrialTaskEO|提交申请")
    @PutMapping("/applyTask")
//    @RequiresPermissions("pc_trust:trialTask:applyTask")
    public ResponseMessage<String> applyTask(String id, String status){
	    trialTaskEOService.applyTask(id, status);
	    return Result.success("0","操作成功！");
    }

    @BusinessLog(description = "pc委托修改")
    @ApiOperation(value = "|TrialTaskEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_trust:trialTask:update")
    public ResponseMessage<TrialTaskEO> update(@RequestBody TrialTaskEO trialTaskEO) throws Exception {
        trialTaskEOService.updateByPrimaryKeySelective(trialTaskEO);
        return Result.success("0","操作成功！",trialTaskEO);
    }

    @BusinessLog(description = "pc委托删除")
    @ApiOperation(value = "|TrialTaskEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_trust:trialTask:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        trialTaskEOService.logicDel(id);
        logger.info("delete from PC_TRIAL_TASK where id = {}", id);
        return Result.success("0","操作成功！");
    }
    
    
    /**
     * PC试验任务-pageoffice在线打开文档
     * @Title: openEVReportWord
     * @param trialTaskId
     * @param map
     * @param request
     * @return
     * @throws Exception
     * @return ModelAndView
     * @author: ljy
     * @date: 2020年1月8日
     */
    @ApiOperation(value = "|PC试验任务-pageoffice在线打开文档")
    @BusinessLog(description = "PC试验任务-pageoffice在线打开文档")
    @GetMapping(path = "/openPCTrialWord")
	public ModelAndView openPCTrialWord(String trialTaskId, Map<String,Object> map,
			HttpServletRequest request) throws Exception{
    	TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialTaskId);
    	//任务书文件
    	TsAttachmentEO	attachmentEO = tsAttachmentEODao.selectByPrimaryKey(trialTaskEO.getTaskFileId());
		PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
		poCtrl.setServerPage("/poserver.zz");//设置服务页面
		poCtrl.webOpen("file://" + filepath + attachmentEO.getSavePath(),
				OpenModeType.docReadOnly, "");
		map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
		ModelAndView mv = new ModelAndView("EVWord");
		return mv;
	}
    
    
    /**
     * 性能/PSQC-费用请款申请费用自行支付查询
     * @Title: costPSQCForCashOut
     * @param page
     * @return
     * @return ResponseMessage<List<PcAutoPayForEO>>
     * @author: ljy
     * @date: 2020年1月9日
     */
    @ApiOperation(value = "|性能/PSQC-费用请款申请费用自行支付查询")
    @BusinessLog(description = "性能/PSQC费用请款申请-费用自行支付查询")
    @GetMapping("/costPSQCForCashOut")
    // @RequiresPermissions("pc_trust:trialTask:costPSQCForCashOut")
    public ResponseMessage<List<PcAutoPayForEO>> costPSQCForCashOut(CostForCashOutPage page) {
        try {
            if (page != null && StringUtils.isNotBlank(page.getCode())) {
                String[] codeArray = page.getCode().split(",");
                page.setCodeList(Arrays.asList(codeArray));
                List<PcAutoPayForEO> budgetList = trialTaskEOService.costPSQCForCashOut(page);
                List<PcAutoPayForEO> person = trialTaskEOService.costPSQCForCashOutPerson(page);
                budgetList.addAll(person);
                return Result.success("0", "查询成功!", budgetList);
            }
            return Result.success("-1", "查询失败，费用code不能为空");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }
    
    @ApiOperation("PV/CV试验变更流程发起人重新提交-提交表单")
    @BusinessLog(description = "PV/CV试验变更流程发起人重新提交-提交表单")
    @PostMapping("/reSubmitUpdate")
    public ResponseMessage reSubmitUpdate(@RequestBody TrialTaskEO trialTaskEO) {
    	try {
			trialTaskEOService.reSubmitUpdate(trialTaskEO);
			return Result.success("0", "更新成功");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "更新失败");
		}
    }

}
