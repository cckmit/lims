package com.adc.da.trial_execute.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.car.service.SaCarDataService;
import com.adc.da.car.vo.SaCarDataListVO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.common.utils.ZipUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.trial_execute.entity.TrialConnectEO;
import com.adc.da.trial_execute.page.TrialConnectEOPage;
import com.adc.da.trial_execute.page.TrialExecuteEOPage;
import com.adc.da.trial_execute.service.TrialConnectEOService;
import com.adc.da.trial_execute.service.TrialExecuteEOService;
import com.adc.da.trial_execute.vo.TrialExecuteListVO;
import com.adc.da.trial_execute.vo.TrialExecuteVO;
import com.adc.da.trial_report.entity.TrialReportEO;
import com.adc.da.trial_report.service.ReportArchivefileEOService;
import com.adc.da.trial_report.service.TrialReportEOService;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/${restPath}/evTrial/trialExecute")
@Api(tags = {"EvTrialTask-发动机试验任务执行模块"})
public class TrialExecuteEOController extends BaseController {

    /**
     * 用户记录日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TrialExecuteEOController.class);

    @Autowired
    private TrialExecuteEOService trialExecuteEOService;

    @Autowired
    private SaCarDataService saCarDataService;

    @Autowired
    private TrialConnectEOService trialConnectEOService;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private TrialReportEOService trialReportEOService;

    @Autowired
    private ReportArchivefileEOService reportArchivefileEOService;

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private BeanMapper beanMapper;

    @Value("${file.path}")
    private String filepath;

    @Autowired
    private UserEOService userEOService;
    
    /**
     * 发动机试验任务执行-分页查询
     *
     * @param page
     * @Title：page向老司机致敬
     * @return: ResponseMessage
     */
    @ApiOperation(value = "|发动机试验任务执行-分页查询")
    @BusinessLog(description = "发动机试验任务执行-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("ev:trialExecute:page")
    public ResponseMessage<PageInfo<TrialExecuteVO>> page(TrialExecuteEOPage page) {
        try {
            if (StringUtils.isNotBlank(page.getSearchPhrase())) {
                List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
                page.setKeyWords(list);
            }
            List<TrialExecuteVO> rows = trialExecuteEOService.selectByPage(page);
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), TrialExecuteVO.class));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.error("-1", "试验任务查询失败!");
        }
    }

    /**
     * 发动机试验任务执行-点击“+”查询
     *
     * @param id
     * @Title：page向老司机致敬
     * @return: ResponseMessage
     */
    @ApiOperation(value = "|发动机试验任务执行-点击“+”查询")
    @BusinessLog(description = "发动机试验任务执行-点击“+”查询")
    @GetMapping("/list/{id}")
    @RequiresPermissions("ev:trialExecute:page")
    public ResponseMessage<List<TrialExecuteListVO>> list(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            return Result.success(trialExecuteEOService.selectListById(id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.error("-1", "试验任务查询失败!");
        }
    }

    @ApiOperation(value = "|试验任务执行-试验数据上传")
    @BusinessLog(description = "试验任务执行-试验数据上传")
    @PostMapping("/trialData")
    @RequiresPermissions("ev:trialExecute:trialData")
    public ResponseMessage trialData(
            @ApiParam(value = "报告id", required = true) @RequestParam(value = "reportId") String reportId,
            @ApiParam(value = "大文件id", required = true) @RequestParam(value = "fileId") String fileId
    ) {
        try {
            // 获取试验报告信息
            TrialReportEO trialReportEO = trialReportEOService.selectByPrimaryKey(reportId);
            reportArchivefileEOService.saveReportAchivefile(ConstantUtils.EV_REPORT_DATA,
                    trialReportEO.getReportNo(), fileId, trialReportEO);
            return Result.success("0", "试验任务执行-试验数据上传成功");
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.error("-1", "试验任务执行-试验数据上传失败!");
        }
    }

    @ApiOperation(value = "|附件-删除")
    @BusinessLog(description = "附件-删除")
    @DeleteMapping("/trialData/{archiveFileId}")
    @RequiresPermissions("ev:trialExecute:trialData")
    public ResponseMessage delTrialData(
            @ApiParam(value = "附件ID", required = true) @PathVariable(value = "archiveFileId") String archiveFileId
    ) {
        return trialReportEOService.delTrialData(archiveFileId);
    }

    /**
     * 发动机试验任务执行-点击“样品信息”查询
     *
     * @param id
     * @Title：page向老司机致敬
     * @return: ResponseMessage
     */
    @ApiOperation(value = "|发动机试验任务执行-点击“样品信息”查询")
    @BusinessLog(description = "发动机试验任务执行-点击“样品信息”查询")
    @GetMapping("/sampleList/{id}")
    @RequiresPermissions("ev:trialExecute:page")
    public ResponseMessage<List<SaCarDataListVO>> sampleList(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            return Result.success(saCarDataService.selectSampleListById(id));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.error("-1", "试验任务查询失败!");
        }
    }


    @ApiOperation(value = "|TrialConnectEO|分页查询")
    @GetMapping("/connect/page")
    @RequiresPermissions("trial_execute:trialConnect:page")
    public ResponseMessage<PageInfo> page(TrialConnectEOPage page) {
        try {
            if (StringUtils.isNotBlank(page.getSearchPhrase())) {
                List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
                page.setKeyWords(list);
            }
            return Result.success(getPageInfo(page.getPager(), trialConnectEOService.queryConnectByPage(page)));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.error("-1", "交接班记录查询失败!");
        }
    }

    // @ApiOperation(value = "|TrialConnectEO|查询")
    // @GetMapping("/connect")
    // @RequiresPermissions("trial_execute:trialConnect:list")
    // public ResponseMessage<List<TrialConnectEO>> list(TrialConnectEOPage page) throws Exception {
    //     return Result.success(trialConnectEOService.queryByList(page));
    // }

    @ApiOperation(value = "|TrialConnectEO|详情")
    @GetMapping("/connect/{id}")
    @RequiresPermissions("trial_execute:trialConnect:get")
    public ResponseMessage<TrialConnectEO> find(@PathVariable String id) throws Exception {
    	TrialConnectEO eo = trialConnectEOService.selectByPrimaryKey(id);
    	eo.setCreateName(userEOService.selectByPrimaryKey(eo.getCreateBy()).getUsname());
        return Result.success("0", "查询成功", eo);
    }

    @ApiOperation(value = "|TrialConnectEO|新增")
    @PostMapping(value = "/connect")
    @RequiresPermissions("trial_execute:trialConnect:save")
    public ResponseMessage<TrialConnectEO> create(@RequestBody TrialConnectEO trialConnectEO) throws Exception {
        trialConnectEO.setId(UUID.randomUUID10());
        trialConnectEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String userId = UserUtils.getUserId();
        String s = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        trialConnectEO.setCreateBy(userId);
        trialConnectEO.setCreateTime(s);
        trialConnectEO.setUpdateBy(userId);
        trialConnectEO.setUpdateTime(s);
        // 0 未接班   1 已接班
        trialConnectEO.setExchangeStatus("0");
        trialConnectEOService.insertSelective(trialConnectEO);
        return Result.success("0", "新增成功");
    }

    @ApiOperation(value = "|TrialConnectEO|修改")
    @PutMapping(value = "/connect")
    @RequiresPermissions("trial_execute:trialConnect:update")
    public ResponseMessage<TrialConnectEO> update(@RequestBody TrialConnectEO trialConnectEO) throws Exception {
        if(StringUtils.isEmpty(trialConnectEO.getId())){
            return Result.error("-1", "id不能为空");
        }
        trialConnectEO.setUpdateBy(UserUtils.getUserId());
        trialConnectEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        trialConnectEOService.updateByPrimaryKeySelective(trialConnectEO);
        return Result.success("0", "修改成功");
    }

    @ApiOperation(value = "|TrialConnectEO|删除")
    @DeleteMapping("/connect/{id}")
    @RequiresPermissions("trial_execute:trialConnect:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        TrialConnectEO trialConnectEO = trialConnectEOService.selectByPrimaryKey(id);
        trialConnectEO.setUpdateBy(UserUtils.getUserId());
        trialConnectEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        trialConnectEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
        trialConnectEOService.updateByPrimaryKeySelective(trialConnectEO);
        return Result.success("0", "删除成功");
    }

    @ApiOperation(value = "|TrialConnectEO|查询台架下的工作人员")
    @GetMapping("/connect/user")
    @RequiresPermissions("trial_execute:trialConnect:delete")
    public ResponseMessage getPlatform(
            @ApiParam(value = "任务ID") @RequestParam("taskId") String taskId
    ) throws Exception {
        // 查询任务书下有多少台架
        List<String> ids = trialConnectEOService.selectTrialTaskIds(taskId);
        // 查询当前登录人的组织机构
        Set<String> orgIds = getStringsOrgIds(ids);
        // 查询改组织机构的工作人员
        List<UserEO> userEOList = new ArrayList<>();
        for (Iterator<String> orgId = orgIds.iterator(); orgId.hasNext(); ) {
            userEOList.addAll(orgEOService.listUserEOByOrgId(orgId.next()));
        }
        return Result.success("0", "查询成功", userEOList);
    }

    @ApiOperation(value = "|TrialConnectEO|导出交接班记录")
    @GetMapping("/connect/export")
    @RequiresPermissions("trial_execute:trialConnect:delete")
    public void downloadZip(HttpServletResponse response, TrialConnectEOPage page) {
        // 查询要导出的内容
        List<TrialConnectEO> eoList = trialConnectEOService.selectListByTaskId(page);
        if (eoList != null && !eoList.isEmpty()) {
            // 查询任务书下有多少台架
            List<String> ids = trialConnectEOService.selectTrialTaskIds(page.getTrialtaskId());
            if (ids != null && !ids.isEmpty()) {
                try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                            "交接班记录.zip", "UTF-8"));
                    List<File> fileList = new ArrayList<>();
                    // 查询当前登录人的组织机构
                    Set<String> orgIds = getStringsOrgIds(ids);
                    StringBuilder path = new StringBuilder();
                    path.append(DateUtils.dateToString(new Date(),
                            "yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator));
                    String tempPath = this.filepath;
                    if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
                        tempPath = this.filepath + File.separator;
                    }
                    String filePath = tempPath + path;
                    for (int i = 0; i < eoList.size(); i++) {
                        Map<String, Object> map = new HashMap<>();
                        if (orgIds != null && !orgIds.isEmpty()) {
                            List<String> orgIdList = new ArrayList<>(orgIds);
                            OrgEO orgEO = orgEOService.selectByPrimaryKey(orgIdList.get(0));
                            eoList.get(i).setScaffoldingName(orgEO.getName());
                        }
                        map.put("map", eoList.get(i));
                        String fileName;
                        if (i == 0) {
                            fileName = "交接班记录.doc";
                        } else {
                            fileName = "交接班记录(" + i + ").doc";
                        }
                        File file = docUtil.createDoc(map, "trialConnect", filePath +fileName);
                        fileList.add(file);
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
        }
    }

    public Set<String> getStringsOrgIds(List<String> ids) {
        List<String> idsByUserId = trialConnectEOService.selectOrgIdsByUserId(UserUtils.getUserId());
        if (idsByUserId == null) {
            return new HashSet<>();
        }
        // 判断当前登录人在任务下的组织机构
        Set<String> orgIds = new HashSet<>();
        for (int i = 0; i < ids.size(); i++) {
            for (int i1 = 0; i1 < idsByUserId.size(); i1++) {
                if (ids.get(i).equals(idsByUserId.get(i1))) {
                    orgIds.add(idsByUserId.get(i1));
                }
            }
        }
        return orgIds;
    }


    /**
     * 根据试验任务id获取实际台架搭建状态 actualScaffoldingStatus
     *
     * @param trialtaskId
     * @return
     * @Title：getActualScaffoldingStatus
     * @return: ResponseMessage<String>
     * @author： ljy
     * @date： 2019年9月24日
     */
    @ApiOperation(value = "|发动机试验任务执行-")
    @BusinessLog(description = "发动机试验任务执行-获取实际台架搭建状态")
    @GetMapping("/getActualScaffoldingStatus")
    @RequiresPermissions("ev:trialExecute:page")
    public ResponseMessage<String> getActualScaffoldingStatus(String trialtaskId) {
        try {
            return Result.success("0", "查询成功", trialConnectEOService.getActualScaffoldingStatus(trialtaskId));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return Result.error("-1", "查询失败!");
        }
    }

}
