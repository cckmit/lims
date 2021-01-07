package com.adc.da.pc_execute.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.utils.ZipUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_budget_cash_out.entity.PcAutoPayForEO;
import com.adc.da.pc_execute.entity.PCReliableInitTaskEO;
import com.adc.da.pc_execute.entity.PCTrialLineEO;
import com.adc.da.pc_execute.page.CostForCashOutPage;
import com.adc.da.pc_execute.page.PCReliableInitTaskEOPage;
import com.adc.da.pc_execute.service.PCReliableInitTaskEOService;
import com.adc.da.pc_execute.service.PCTrialLineEOService;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_execute.vo.PCBudgetCostVO;
import com.adc.da.pc_execute.vo.PCReliableInitTaskSearchVO;
import com.adc.da.pc_execute.vo.PCReliableInitTaskVO;
import com.adc.da.pc_execute.vo.PCTrialLineSearchVO;
import com.adc.da.pc_execute.vo.PCTrialLineVO;
import com.adc.da.pc_execute.vo.PcReliableInitTaskRiskVO;
import com.adc.da.pc_execute.vo.PersonAccommodationCostVO;
import com.adc.da.pc_execute.vo.RiskSubsidyCostVO;
import com.adc.da.pc_execute.vo.TrialCostVO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.activiti.engine.RuntimeService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * PV/CV试验执行模块--可靠性立项单
 *
 * @author Administrator
 * @date 2019年10月21日
 */
@RestController
@RequestMapping("${restPath}/pcTrial/pcReliableInitTask")
@Api(tags = "PcTrialTask-PV/CV试验执行模块")
@SuppressWarnings("all")
public class PCReliableInitTaskEOController extends BaseController {

    /**
     * 用于记录日志.
     */
    private static final Logger logger = LoggerFactory.getLogger(PCReliableInitTaskEOController.class);

    /**
     * eo vo 转换
     *
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private PCReliableInitTaskEOService pcReliableInitTaskEOService;

    @Autowired
    private PCTrialLineEOService pcTrialLineEOService;

    @Value("${file.path}")
    private String filepath;

    @Autowired
    private DocUtil docUtil;


    @Autowired
    private PVCVService PVCVService;
    
    
    
    /**
     * 获取UUID
     *
     * @return ResponseMessage<String>
     * @Title: getUUID
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|获取UUID")
    @BusinessLog(description = "获取UUID")
    @GetMapping("/getUUID")
    public ResponseMessage<String> getUUID() {
        return Result.success("0", "查询成功!", UUID.randomUUID());
    }

    /**
     * 可靠性立项单-分页查询
     *
     * @param page
     * @return ResponseMessage<PageInfo < PCReliableInitTaskSearchVO>>
     * @Title: list
     * @author: ljy
     * @date: 2019年10月23日
     */
    @ApiOperation(value = "|可靠性立项单分页查询")
    @BusinessLog(description = "可靠性立项单-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pcTrial:pcReliableInitTask:page")
    public ResponseMessage<PageInfo<PCReliableInitTaskSearchVO>> page(PCReliableInitTaskEOPage page) {
        try {
            //查询列表
            List<PCReliableInitTaskEO> rows = pcReliableInitTaskEOService.queryByPage(page);
            //设置总条数
            Integer rowsCount = pcReliableInitTaskEOService.queryByCount(page);
            page.getPager().setRowCount(rowsCount);
            return Result.success("0", "查询成功!",
                    beanMapper.mapPage(getPageInfo(page.getPager(), rows), PCReliableInitTaskSearchVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @ApiOperation(value = "|通过试验任务书ID查询可靠性立项单")
    @BusinessLog(description = "通过试验任务书ID查询可靠性立项单")
    @GetMapping("/trialTaskId")
    @RequiresPermissions("pcTrial:pcReliableInitTask:page")
    public ResponseMessage<PCReliableInitTaskSearchVO> page(
            @ApiParam("试验任务书ID") @RequestParam("trialTaskId") String trialTaskId
    ) {
        try {
            PCReliableInitTaskEO eo = pcReliableInitTaskEOService.findOne(trialTaskId);
            return Result.success("0", "查询成功!", beanMapper.map(eo, PCReliableInitTaskSearchVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }


    /**
     * 可靠性立项单-保存
     *
     * @param pcReliableInitTaskVO
     * @return ResponseMessage<PCReliableInitTaskVO>
     * @Title: save
     * @author: ljy
     * @date: 2019年10月23日
     */
    @ApiOperation(value = "|可靠性立项单保存")
    @BusinessLog(description = "可靠性立项单-保存")
    @PostMapping("/save")
    @RequiresPermissions("pcTrial:pcReliableInitTask:save")
    public ResponseMessage<PCReliableInitTaskVO> save(
            @RequestBody PCReliableInitTaskVO pcReliableInitTaskVO) {
        try {
            PCReliableInitTaskEO eo = pcReliableInitTaskEOService.save(
                    beanMapper.map(pcReliableInitTaskVO, PCReliableInitTaskEO.class));
            return Result.success("0", "保存成功!", beanMapper.map(eo, PCReliableInitTaskVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "保存失败!");
        }
    }


    /**
     * 可靠性立项单-编辑
     *
     * @param pcReliableInitTaskVO
     * @return ResponseMessage<PCReliableInitTaskVO>
     * @Title: updatePCReliableInitTask
     * @author: ljy
     * @date: 2019年10月24日
     */
    @ApiOperation(value = "|可靠性立项单编辑")
    @BusinessLog(description = "可靠性立项单-编辑")
    @PutMapping("/edit")
    @RequiresPermissions("pcTrial:pcReliableInitTask:save")
    public ResponseMessage<PCReliableInitTaskVO> updatePCReliableInitTask(
            @RequestBody PCReliableInitTaskVO pcReliableInitTaskVO) {
        try {
            PCReliableInitTaskEO eo = pcReliableInitTaskEOService.edit(
                    beanMapper.map(pcReliableInitTaskVO, PCReliableInitTaskEO.class));
            return Result.success("0", "更新成功!", beanMapper.map(eo, PCReliableInitTaskVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "更新失败!");
        }
    }


    /**
     * 可靠性立项单-详情
     *
     * @param id
     * @return ResponseMessage<PCReliableInitTaskVO>
     * @Title: getDetailById
     * @author: ljy
     * @date: 2019年10月24日
     */
    @ApiOperation(value = "|可靠性立项单详情")
    @BusinessLog(description = "可靠性立项单-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pcTrial:pcReliableInitTask:save")
    public ResponseMessage<PCReliableInitTaskVO> getDetailById(@PathVariable String id) {
        try {
            return Result.success("0", "查询成功!",
                    beanMapper.map(pcReliableInitTaskEOService.selectByPrimaryKey(id), PCReliableInitTaskVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    /**
     * 可靠性立项单-删除
     *
     * @param id
     * @param initStatus
     * @return void
     * @Title: deleteById
     * @author: ljy
     * @date: 2019年10月24日
     */
    @ApiOperation(value = "|可靠性立项单删除")
    @BusinessLog(description = "可靠性立项单-删除")
    @DeleteMapping("/del")
    @RequiresPermissions("pcTrial:pcReliableInitTask:delete")
    public ResponseMessage deleteById(String id, String initStatus) {
        try {
            pcReliableInitTaskEOService.deleteById(id, initStatus);
            return Result.success("0", "删除成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "删除失败!");
        }
    }


    /**
     * 可靠性立项单-流程启动
     *
     * @param pcReliableInitTaskVO
     * @return ResponseMessage
     * @Title: submitReliableInitTask
     * @author: ljy
     * @date: 2019年10月24日
     */
    @ApiOperation(value = "|可靠性立项单-流程启动")
    @BusinessLog(description = "可靠性立项单-流程启动")
    @PostMapping("/startProcess")
    @RequiresPermissions("pcTrial:pcReliableInitTask:startProcess")
    public ResponseMessage submitReliableInitTask(
            @RequestBody PCReliableInitTaskVO pcReliableInitTaskVO) {
        try {
        	//判断下一节点是否有审批人
            JSONObject jsonObj = PVCVService.getStartNextNodeInfo(ConstantUtils.PV_RELIABLE_INITTASK_TYPE, 
            		ConstantUtils.CV_RELIABLE_INITTASK_TYPE);
            if("-1".equals(jsonObj.getString("isSuccess"))) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            pcReliableInitTaskEOService.submitReliableInitTask(
                    beanMapper.map(pcReliableInitTaskVO, PCReliableInitTaskEO.class), jsonObj.getString("flag"));
            return Result.error("0", "流程启动成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "流程启动失败!");
        }
    }


    /**
     * 可靠性立项单-流程审批
     *
     * @param request
     * @param requestEO
     * @return ResponseMessage<PCReliableInitTaskVO>
     * @Title: approvalProcess
     * @author: ljy
     * @date: 2019年10月24日
     */
    @ApiOperation(value = "|可靠性立项单-流程审批")
    @BusinessLog(description = "可靠性立项单-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("pcTrial:pcReliableInitTask:approvalProcess")
    @EnableAccess
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
            
            //判断下一节点是否有审批人
            String str = PVCVService.getNextNodeInfo(requestEO);
            if("-1".equals(str)) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            pcReliableInitTaskEOService.approvalProcess(request, requestEO);
            return Result.success("0", "流程审核成功!");
        } catch (Exception e) {
            logger.error("-1", "流程审批失败！");
            return Result.error("-1", "流程审批失败！");
        }
    }


    /**
     * 可靠性立项单-导出
     *
     * @param response
     * @param request
     * @param reliableInitTaskId
     * @return ResponseMessage
     * @Title: exportTrialTask
     * @author: ljy
     * @date: 2019年10月25日
     */
    @ApiOperation(value = "|可靠性立项单-导出")
    @BusinessLog(description = "可靠性立项单-导出")
    @GetMapping("/exportPCReliableInitTask")
    @RequiresPermissions("pcTrial:pcReliableInitTask:export")
    public ResponseMessage exportTrialTask(HttpServletResponse response,
                                           HttpServletRequest request, String reliableInitTaskId) {
        try {
            pcReliableInitTaskEOService.exportPCReliableInitTask(response, request, reliableInitTaskId);
            return Result.success("0", "导出成功");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "导出失败!");
        }
    }


    /**
     * 获取可靠性立项单中的 路线策划数据
     *
     * @param initTaskId
     * @return List<PCTrialLineEO>
     * @Title: findByList
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|可靠性立项单路线策划数据查询")
    @BusinessLog(description = "可靠性立项单路线策划数据-查询")
    @GetMapping("/pcTrialLineList")
    @RequiresPermissions("pcTrial:pcReliableInitTask:page")
    public ResponseMessage<List<PCTrialLineVO>> pcTrialLineList(String initTaskId) {
        try {
            List<PCTrialLineEO> list = pcTrialLineEOService.findByList(initTaskId);
            return Result.success("0", "查询成功!", beanMapper.mapList(list, PCTrialLineVO.class));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }


    /**
     * 保存可靠性立项单中的 路线策划数据
     *
     * @param pcTrialLineSearchVO
     * @return void
     * @Title: save
     * @author: ljy
     * @date: 2019年10月21日
     */
    @ApiOperation(value = "|保存可靠性立项单路线策划数据")
    @BusinessLog(description = "可靠性立项单路线策划数据-保存")
    @PostMapping("/pcTrialLineSave")
    @RequiresPermissions("pcTrial:pcReliableInitTask:save")
    public ResponseMessage pcTrialLineSave(
            @RequestBody PCTrialLineSearchVO pcTrialLineSearchVO) {
        try {
            if (StringUtils.isEmpty(pcTrialLineSearchVO.getInitTaskId())) {
                return Result.error("-1", "可靠性立项单id不可为空!");
            }
            pcTrialLineEOService.save(pcTrialLineSearchVO);
            return Result.success("0", "保存成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "保存失败!");
        }
    }

    /**
     * 费用表单页数据查询
     *
     * @param initTaskId
     * @return PCBudgetCostVO
     * @Title: formList
     * @author: ljy
     * @date: 2019年10月23日
     */
    @ApiOperation(value = "|可靠性立项单费用表单页数据查询")
    @BusinessLog(description = "可靠性立项单费用表单页数据查询")
    @GetMapping("/costFormData")
    @RequiresPermissions("pcTrial:pcReliableInitTask:page")
    public ResponseMessage<PCBudgetCostVO> costFormData(String initTaskId) {
        //先按initTaskId 分别查三个,没有就查 基础   然后返回, 主表附件等信息 一并返回
        try {
            PCBudgetCostVO vo = pcReliableInitTaskEOService.costFormData(initTaskId);
            return Result.success("0", "查询成功!", vo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }


    /**
     * 保存费用表单页数据
     *
     * @param pcBudgetCostVO
     * @return ResponseMessage<PCBudgetCostVO>
     * @Title: saveCostData
     * @author: ljy
     * @date: 2019年10月23日
     */
    @ApiOperation(value = "|可靠性立项单费用表单页数据保存")
    @BusinessLog(description = "可靠性立项单费用表单页数据保存")
    @PostMapping("/saveCostData")
    @RequiresPermissions("pcTrial:pcReliableInitTask:save")
    public ResponseMessage<PCBudgetCostVO> costDataSave(
            @RequestBody PCBudgetCostVO pcBudgetCostVO) {
        try {
            if (StringUtils.isEmpty(pcBudgetCostVO.getInitTaskId())) {
                return Result.error("-1", "可靠性立项单id不可为空!");
            }
            pcReliableInitTaskEOService.costDataSave(pcBudgetCostVO);
            return Result.success("0", "保存成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "保存失败!");
        }
    }

    @ApiOperation(value = "|费用请款申请费用自行支付查询")
    @BusinessLog(description = "费用请款申请费用自行支付查询")
    @GetMapping("/costForCashOut")
    // @RequiresPermissions("pcTrial:pcReliableInitTask:costForCashOut")
    public ResponseMessage<List<PcAutoPayForEO>> costForCashOut(CostForCashOutPage page) {
        try {
            if (page != null && StringUtils.isNotBlank(page.getCode())) {
                String[] codeArray = page.getCode().split(",");
                page.setCodeList(Arrays.asList(codeArray));
                List<PcAutoPayForEO> budgetList = pcReliableInitTaskEOService.costForCashOut(page);
                List<PcAutoPayForEO> person = pcReliableInitTaskEOService.costForCashOutPerson(page);
                budgetList.addAll(person);
                return Result.success("0", "查询成功!", budgetList);
            }
            return Result.success("-1", "查询失败，费用code不能为空");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

    @ApiOperation(value = "|试验跟踪-风险补贴费用跟踪")
    @BusinessLog(description = "试验跟踪-风险补贴费用跟踪")
    @GetMapping("/riskSubsidy")
    @RequiresPermissions("pcTrial:pcReliableInitTask:riskSubsidy")
    public ResponseMessage<List<RiskSubsidyCostVO>> riskSubsidy(
            @ApiParam("可靠性立项单id") @RequestParam("initTaskId") String initTaskId
    ) {
        return Result.success("0", "查询成功!", pcReliableInitTaskEOService.riskSubsidy(initTaskId));
    }

    @ApiOperation(value = "|试验跟踪-试验费用跟踪")
    @BusinessLog(description = "试验跟踪-试验费用跟踪")
    @GetMapping("/trialCost")
    @RequiresPermissions("pcTrial:pcReliableInitTask:trialCost")
    public ResponseMessage<List<TrialCostVO>> trialCost(
            @ApiParam("可靠性立项单id") @RequestParam("initTaskId") String initTaskId
    ) {
        return Result.success("0", "查询成功!", pcReliableInitTaskEOService.trialCost(initTaskId));
    }

    @ApiOperation(value = "|试验跟踪-人员及住宿费用跟踪")
    @BusinessLog(description = "试验跟踪-人员及住宿费用跟踪")
    @GetMapping("/personAccommodationCost")
    @RequiresPermissions("pcTrial:pcReliableInitTask:personAccommodationCost")
    public ResponseMessage<List<PersonAccommodationCostVO>> personAccommodationCost(
            @ApiParam("可靠性立项单id") @RequestParam("initTaskId") String initTaskId
    ) {
        return Result.success("0", "查询成功!",
                pcReliableInitTaskEOService.personAccommodationCost(initTaskId));
    }

    // @ApiOperation(value = "|试验跟踪-风险评估变更")
    // @BusinessLog(description = "试验跟踪-风险评估变更")
    // @PostMapping("/reliableInitTaskRisk")
    // @RequiresPermissions("pcTrial:pcReliableInitTask:personAccommodationCost")
    // public ResponseMessage create(@RequestBody PcReliableInitTaskRiskVO vo) {
    //     pcReliableInitTaskEOService.create(vo);
    //     return Result.success("0", "变更成功!");
    // }

    @ApiOperation(value = "|试验跟踪-风险评估变更")
    @BusinessLog(description = "试验跟踪-风险评估变更")
    @PutMapping("/reliableInitTaskRisk")
    @RequiresPermissions("pcTrial:pcReliableInitTask:personAccommodationCost")
    public ResponseMessage update(@RequestBody PcReliableInitTaskRiskVO vo) {
        pcReliableInitTaskEOService.update(vo);
        return Result.success("0", "变更成功!");
    }

    @ApiOperation(value = "|试验跟踪-导出")
    @BusinessLog(description = "试验跟踪-导出")
    @GetMapping("/download")
    @RequiresPermissions("pcTrial:pcReliableInitTask:personAccommodationCost")
    public void download(HttpServletResponse response,
                         @ApiParam("可靠性立项单id") @RequestParam("initTaskId") String initTaskId
    ) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                    "试验跟踪.zip", "UTF-8"));
            List<File> fileList = new ArrayList<>();
            StringBuilder path = new StringBuilder();
            path.append(DateUtils.dateToString(new Date(),
                    "yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator));
            String tempPath = this.filepath;
            if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
                tempPath = this.filepath + File.separator;
            }
            String filePath = tempPath + path;
            Map<String, Object> map = new HashMap<>();
            // 风险补贴费用跟踪
            List<RiskSubsidyCostVO> riskSubsidyCostVOS = pcReliableInitTaskEOService.riskSubsidy(initTaskId);
            if (riskSubsidyCostVOS != null) {
                map.put("obj", riskSubsidyCostVOS);
                fileList.add(docUtil.createDoc(map, "riskCost",
                        filePath + "风险补贴费用跟踪.doc"));
            }
            // 人员及住宿费用跟踪
            List<PersonAccommodationCostVO> personAccommodationCostVOS =
                    pcReliableInitTaskEOService.personAccommodationCost(initTaskId);
            if (personAccommodationCostVOS != null) {
                map.put("obj", personAccommodationCostVOS);
                fileList.add(docUtil.createDoc(map, "personCost",
                        filePath + "人员及住宿费用跟踪.doc"));
            }
            // 试验费用跟踪
            List<TrialCostVO> trialCostVOS = pcReliableInitTaskEOService.trialCost(initTaskId);
            if (trialCostVOS != null) {
                map.put("obj", trialCostVOS);
                fileList.add(docUtil.createDoc(map, "trialCost",
                        filePath + "试验费用跟踪.doc"));
            }
            zipOutputStream.setEncoding("GBK");
            if (CollectionUtils.isEmpty(fileList) == false) {
                for (int i = 0, size = fileList.size(); i < size; i++) {
                    ZipUtils.compress(fileList.get(i), zipOutputStream, "");
                }
            }
            // 冲刷输出流
            zipOutputStream.flush();
            zipOutputStream.close();
            if (fileList != null && !fileList.isEmpty()) {
                for (File file : fileList) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "判断可靠性试验立项申请流程是否已完成审批")
    @GetMapping("/isCompleted")
    @RequiresPermissions("pcTrial:pcReliableInitTask:isCompleted")
    public ResponseMessage<String> isCompleted(String trialTaskId) {
        try {
            PCReliableInitTaskEOPage page = new PCReliableInitTaskEOPage();
            page.setTrialTaskId(trialTaskId);
            List<PCReliableInitTaskEO> list = pcReliableInitTaskEOService.queryByList(page);
            String r = "y";
            if(list != null && !list.isEmpty()){
                for(PCReliableInitTaskEO entity : list){
                    if("1".equals(entity.getInitStatus()) || "3".equals(entity.getInitStatus()) || "4".equals(entity.getInitStatus())){
                        //存在状态为审批中、退回、撤回的流程
                        r = "n";
                        break;
                    }
                };
            }else{
            	r = "n";
            }
            return Result.success("0", "查询成功!", r);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

}
