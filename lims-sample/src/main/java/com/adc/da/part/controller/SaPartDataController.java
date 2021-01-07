package com.adc.da.part.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ExportDataUtil;
import com.adc.da.common.PDFUtil;
import com.adc.da.common.utils.ExcelDataUtil;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.login.util.UserUtils;
import com.adc.da.part.dto.SaPartDataDTO;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.part.entity.SaPartFlowLogEO;
import com.adc.da.part.entity.SaPartSequenceEO;
import com.adc.da.part.page.SaPartDataEOPage;
import com.adc.da.part.page.SaPartFlowLogPage;
import com.adc.da.part.service.SaPartDataEOService;
import com.adc.da.part.service.SaPartFlowLogService;
import com.adc.da.part.service.SaPartSequenceEOService;
import com.adc.da.part.vo.SaBackPartDataVO;
import com.adc.da.part.vo.SaPartDataListVO;
import com.adc.da.part.vo.SaPartDataVO;
import com.adc.da.part.vo.SaPartFlowLogVO;
import com.adc.da.part.vo.SaPartSequenceVO;
import com.adc.da.part.vo.SaPartsQRCodeVO;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.ParamEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.ParamEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/31 15:01
 * @description：${description}
 */
@RestController
@RequestMapping("/${restPath}/sa/partData")
@Api(
        tags = {"Sa-零部件管理"}
)
public class SaPartDataController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(SaPartDataController.class);

    @Autowired
    private SaPartDataEOService saPartDataEOService;

    @Autowired
    private SaPartFlowLogService saPartFlowLogService;

    @Autowired
    private SaPartSequenceEOService saPartSequenceEOService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private ParamEOService paramEOService;

    @ApiOperation(value = "|查询列表")
    @GetMapping("")
    @RequiresPermissions("part:data:page")
    public ResponseMessage<PageInfo<SaPartDataListVO>> getList(
            @ApiParam(value = "检验委托合同编号") @RequestParam(value = "trialTaskBookNO", required = false) String trialApplyNO,
            @ApiParam(value = "样品名称") @RequestParam(value = "partName", required = false) String partName,
            @ApiParam(value = "零部件号/发动机号") @RequestParam(value = "partEngineNo", required = false) String partEngineNo,
            @ApiParam(value = "样件数量") @RequestParam(value = "sampleNumber", required = false) String sampleNumber,
            @ApiParam(value = "在架") @RequestParam(value = "inShelf", required = false) Integer inShelf,
            @ApiParam(value = "生产厂家") @RequestParam(value = "producedFactory", required = false) String producedFactory,
            @ApiParam(value = "送样人") @RequestParam(value = "sendPartUserName", required = false)
                    String sendPartUserName,
            @ApiParam(value = "领养人") @RequestParam(value = "getPartUserName", required = false) String getPartUserName,
            @ApiParam(value = "所在位置") @RequestParam(value = "PartSpaceLocation", required = false) String
                    partSpaceLocation,
            @ApiParam(value = "创建时间开始") @RequestParam(value = "createTimeStart", required = false) String createTimeStart,
            @ApiParam(value = "创建时间结束") @RequestParam(value = "createTimeEnd", required = false) String createTimeEnd,
            @ApiParam(value = "通用查询") @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
            @ApiParam(value = "是否是领料查询") @RequestParam(value = "flag", required = false) Integer flag,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            SaPartDataEOPage eoPage = new SaPartDataEOPage();
            if (StringUtils.isNotBlank(trialApplyNO)) {
                eoPage.setTrialApplyNO(trialApplyNO.trim());
            }
            if (StringUtils.isNotBlank(partName)) {
                eoPage.setPartName(partName.trim());
            }
            if (StringUtils.isNotBlank(partEngineNo)) {
                eoPage.setPartEngineNo(partEngineNo.trim());
            }
            if (StringUtils.isNotBlank(sampleNumber)) {
                eoPage.setSampleNumber(sampleNumber.trim());
            }
            if (StringUtils.isNotBlank(producedFactory)) {
                eoPage.setProducedFactory(producedFactory.trim());
            }
            if (StringUtils.isNotBlank(sendPartUserName)) {
                eoPage.setSendPartUserName(sendPartUserName.trim());
            }
            if (StringUtils.isNotBlank(getPartUserName)) {
                eoPage.setGetPartUserName(getPartUserName.trim());
            }
            if (StringUtils.isNotBlank(partSpaceLocation)) {
                eoPage.setPartSpaceLocation(partSpaceLocation.trim());
            }
            if (StringUtils.isNotBlank(createTimeStart)) {
                eoPage.setCreateTimeStart(createTimeStart.trim());
            }
            if (StringUtils.isNotBlank(createTimeEnd)) {
                eoPage.setCreateTimeEnd(createTimeEnd.trim());
            }
            if (inShelf != null && inShelf > 0) {
                eoPage.setInShelf(inShelf);
            }
            if (StringUtils.isNotBlank(searchPhrase)) {
                List<String> list = TransformUtil.settingSearchPhrase(searchPhrase);
                eoPage.setKeyWords(list);
            }
            eoPage.setPage(page);
            eoPage.setPageSize(pageSize);
            List<SaPartDataListVO> saPartDataListVOS = null;
            if (null == flag){
                saPartDataListVOS = saPartDataEOService.selectByPage(eoPage);

            }else {
                saPartDataListVOS = saPartDataEOService.partList(eoPage);
            }
            return Result.success(getPageInfo(eoPage.getPager(), saPartDataListVOS));
        } catch (Exception e) {
            logger.error("查询列表异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|保存零部件")
    @PostMapping("")
    @RequiresPermissions("part:data:save")
    public ResponseMessage save(@RequestBody SaPartDataDTO saPartDataDTO) {
        try {
            ResponseMessage x = getResponseMessage(saPartDataDTO);
            if (x != null) {
                return x;
            }
            saPartDataEOService.save(saPartDataDTO);
            return Result.success("0", "保存成功");
        } catch (Exception e) {
            logger.error("保存零部件异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑零部件")
    @PutMapping("")
    @RequiresPermissions("part:data:edit")
    public ResponseMessage edit(@RequestBody SaPartDataDTO saPartDataDTO) {
        try {
            ResponseMessage x = getResponseMessage(saPartDataDTO);
            if (x != null) {
                return x;
            }
            saPartDataEOService.edit(saPartDataDTO);
            return Result.success("0", "编辑成功");
        } catch (Exception e) {
            logger.error("编辑零部件异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    public ResponseMessage getResponseMessage(@RequestBody SaPartDataDTO saPartDataDTO) {
        if (StringUtils.isBlank(saPartDataDTO.getPartName())) {
            return Result.success("-1", "参数异常: partName 不能为null", null);
        }
        if (saPartDataDTO.getPartSampleNumber() != null && saPartDataDTO.getPartSampleNumber() <= 0) {
            return Result.success("-1", "参数异常: partSampleNumber 不能为null", null);
        }
        return null;
    }

    @ApiOperation(value = "|通过库房ID和占用层数查询详情")
    @GetMapping("/depot")
    @RequiresPermissions("part:data:getOne")
    public ResponseMessage<SaPartDataVO> getOneByDepotId(
            @ApiParam(value = "库房ID", required = true) @RequestParam(value = "depotId") String depotId,
            @ApiParam(value = "层数", required = true) @RequestParam("layerNumber") Integer layerNumber
    ) {
        try {
            return Result.success(saPartDataEOService.getOneByDepotId(depotId, layerNumber));
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|查询详情")
    @GetMapping("/{id}")
    @RequiresPermissions("part:data:getOne")
    public ResponseMessage<SaPartDataVO> getOne(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            return Result.success(saPartDataEOService.getOne(id));
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|查询退样打印单")
    @GetMapping("/back/{id}")
    @RequiresPermissions("part:data:getOne")
    public ResponseMessage<SaBackPartDataVO> backPart(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            return Result.success(saPartDataEOService.backPart(id));
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("part:data:delete")
    public ResponseMessage deleteById(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            saPartDataEOService.deleteById(id);
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|批量删除")
    @DeleteMapping("/ids")
    @RequiresPermissions("part:data:delete")
    public ResponseMessage deleteByIds(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String[] ids
    ) {
        try {
            for (String id : ids) {
                saPartDataEOService.deleteById(id);
            }
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation("|查询零部件序列号")
    @GetMapping("/partSequence")
    public ResponseMessage<List<SaPartSequenceVO>> partSequence(
            @ApiParam(value = "partId", required = true) @RequestParam("partId") String partId,
            @ApiParam(value = "partStatus (1.收样；2.领样；3.退样；4.归还；5.报废；6.封存；7.拆机；8.流转;)", required = true)
            @RequestParam("partStatus") Integer partStatus
    ) {
        try {
            List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataIdAndStatus(partId,partStatus);
            return Result.success(beanMapper.mapList(saPartSequenceEOList, SaPartSequenceVO.class));
        } catch (Exception e) {
            logger.error("查询零部件序列号异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|查看流转日志")
    @GetMapping("/logList")
    @RequiresPermissions("part:data:log")
    public ResponseMessage<PageInfo<SaPartFlowLogVO>> logList(
            @ApiParam(value = "partId", required = true) @RequestParam(value = "partId") String partId
//            @ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
//            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            SaPartFlowLogPage saPartFlowLogPage = new SaPartFlowLogPage();
            saPartFlowLogPage.setPartId(partId);
//            saPartFlowLogPage.setPage(page);
//            saPartFlowLogPage.setPageSize(pageSize);
            List<SaPartFlowLogEO> rows = saPartFlowLogService.queryByPage(saPartFlowLogPage);
            PageInfo<SaPartFlowLogVO> pageInfo = beanMapper.mapPage(getPageInfo(saPartFlowLogPage.getPager(), rows),
                    SaPartFlowLogVO.class);
            if (pageInfo != null && pageInfo.getList() != null && !pageInfo.getList().isEmpty()) {
                for (SaPartFlowLogVO vo : pageInfo.getList()) {
                    StringBuilder sb = new StringBuilder();
                    // 获取负责人信息
                    settingUserINfo(vo, sb);
                }
            }
            return Result.success(pageInfo);
        } catch (Exception e) {
            logger.error("查看流转日志异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    public void settingUserINfo(SaPartFlowLogVO vo, StringBuilder sb) {
        if (StringUtils.isNotBlank(vo.getLeaderId())) {
            UserEO userEO = userEOService.getUserWithRoles(vo.getLeaderId());
            if (userEO != null) {
                vo.setLeaderName(userEO.getUsname());
                for (OrgEO eo : userEO.getOrgEOList()) {
                    sb.append(eo.getName()).append(",");
                }
                vo.setLeaderOrg(sb.substring(0, sb.lastIndexOf(",")));
            }
        }
        // 获取经办人信息
        if (StringUtils.isNotBlank(vo.getOperatorId())) {
            UserEO userEO = userEOService.getUserWithRoles(vo.getOperatorId());
            if (userEO != null) {
                vo.setOperatorName(userEO.getUsname());
                sb.setLength(0);
                for (OrgEO eo : userEO.getOrgEOList()) {
                    sb.append(eo.getName()).append(",");
                }
                vo.setOperatorOrg(sb.substring(0, sb.lastIndexOf(",")));
            }
        }
    }

    @ApiOperation(value = "|收样")
    @PostMapping("/receivedPart")
    @RequiresPermissions("part:data:receivedPart")
    public ResponseMessage receivedPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "收样人ID", required = true) @RequestParam(value = "receivUserId") String receivUserId,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "receivTime") String receivTime,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequenceIds")
                    List<String> partSequenceIds,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "附件ID") @RequestParam(value = "attachmentId", required = false) String attachmentId
    ) {
        try {
            saPartFlowLogService.addReceivedPart(partId, receivUserId, receivTime, partSequenceIds,
                    remarks, attachmentId);
            return Result.success("0", "收样成功");
        } catch (Exception e) {
            logger.error("收样异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|领样")
    @PostMapping("/collectPart")
    @RequiresPermissions("part:data:collectPart")
    public ResponseMessage collectPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "领样人ID", required = true) @RequestParam(value = "getPartUserId") String getPartUserId,
            @ApiParam(value = "操作人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "检验委托合同编号", required = true) @RequestParam(value = "trialApplyNO") String trialApplyNO,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "样品状态") @RequestParam(value = "partStatus", required = false) String partStatus,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "是否退样") @RequestParam(value = "whetherReturn", required = false) String whetherReturn,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences
    ) {
        try {
            SaPartFlowLogEO logEO = new SaPartFlowLogEO();
            logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            logEO.setCreateTime(date);
            logEO.setCreateBy(UserUtils.getUserId());
            logEO.setLeaderId(getPartUserId);
            logEO.setOperationDate(exchangeTime);
            logEO.setOperationContent("领样");
            logEO.setOperatorId(operatorId);
            logEO.setTrialApplyNo(trialApplyNO);
            logEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            String partStatusName = saPartDataEOService.statusValue(String.valueOf(partStatus),"partStatus");
            String whetherReturnName = saPartDataEOService.statusValue(String.valueOf(whetherReturn),"ccReturnSimple");
            sb.append("样品状态：").append(partStatusName).append(";").append("是否退样：").append(whetherReturnName);
            logEO.setOthers(sb.toString());
            saPartFlowLogService.addcollectPart(logEO, partSequences, partId);
            return Result.success("0", "领样成功");
        } catch (Exception e) {
            logger.error("领样异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|流转")
    @PostMapping("/flowPart")
    @RequiresPermissions("part:data:flowPart")
    public ResponseMessage flowPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "交样人ID", required = true) @RequestParam(value = "sendPartUserId") String sendPartUserId,
            @ApiParam(value = "领养人ID", required = true) @RequestParam(value = "getPartUserId") String getPartUserId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "样品状态") @RequestParam(value = "partStatus", required = false) String partStatus,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences
    ) {
        try {
            saPartFlowLogService.addFlowPart(partId, sendPartUserId, getPartUserId, remarks, exchangeTime, partStatus,
                    partSequences);
            return Result.success("0", "流转成功");
        } catch (Exception e) {
            logger.error("流转异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|归还")
    @PostMapping("/returnPart")
    @RequiresPermissions("part:data:returnPart")
    public ResponseMessage returnPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "归还人ID", required = true) @RequestParam(value = "returnPartUserId")
                    String returnPartUserId,
            @ApiParam(value = "经办人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "留样截止时间") @RequestParam(value = "reserveTime", required = false) String reserveTime,
            @ApiParam(value = "车位ID") @RequestParam(value = "partSpaceId", required = false) String partSpaceId,
            @ApiParam(value = "车位号") @RequestParam(value = "partSpaceNumber", required = false) Integer partSpaceNumber,
            @ApiParam(value = "所在位置") @RequestParam(value = "partSpaceLocation", required = false)
                    String partSpaceLocation,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences
    ) {
        try {
            SaPartFlowLogEO logEO = new SaPartFlowLogEO();
            logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            logEO.setCreateTime(date);
            logEO.setCreateBy(UserUtils.getUserId());
            logEO.setLeaderId(returnPartUserId);
            logEO.setOperationDate(exchangeTime);
            logEO.setOperationContent("归还");
            logEO.setOperatorId(operatorId);
            logEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("留样截止时间：").append(reserveTime);
            logEO.setOthers(sb.toString());
            saPartFlowLogService.addReturnPart(partId, logEO, partSequences, partSpaceId, partSpaceNumber,
                    partSpaceLocation);
            return Result.success("0", "归还成功");
        } catch (Exception e) {
            logger.error("归还异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|退样")
    @PostMapping("/backPart")
    @RequiresPermissions("part:data:backPart")
    public ResponseMessage backPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "退样人ID", required = true) @RequestParam(value = "backPartUserId") String backPartUserId,
            @ApiParam(value = "经办人Id", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "receivTime") String receivTime,
            @ApiParam(value = "报告结果确认") @RequestParam(value = "reportResult", required = false) String reportResult,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "附件ID") @RequestParam(value = "attachmentId", required = false) String attachmentId
    ) {
        try {
            SaPartFlowLogEO logEO = new SaPartFlowLogEO();
            logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            logEO.setCreateTime(date);
            logEO.setCreateBy(UserUtils.getUserId());
            logEO.setLeaderId(backPartUserId);
            logEO.setOperationDate(receivTime);
            logEO.setOperationContent("退样");
            logEO.setOperatorId(operatorId);
            logEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("报告结果确认：").append(reportResult);
            logEO.setOthers(sb.toString());
            saPartFlowLogService.addBackPart(partId, partSequences, attachmentId, logEO);
            return Result.success("0", "退样成功");
        } catch (Exception e) {
            logger.error(" 退样异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|报废")
    @PostMapping("/scrapPart")
    @RequiresPermissions("part:data:scrapPart")
    public ResponseMessage scrapPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "经办人Id", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "报废日期", required = true) @RequestParam(value = "scrapTime") String scrapTime,
            @ApiParam(value = "流程号") @RequestParam(value = "processNO", required = false) String processNO,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences
    ) {
        try {
            saPartFlowLogService.addScrapPart(partId, operatorId, scrapTime, processNO, remarks, partSequences);
            return Result.success("0", "报废成功");
        } catch (Exception e) {
            logger.error(" 报废异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|封存")
    @PostMapping("/archivePart")
    @RequiresPermissions("part:data:returnPart")
    public ResponseMessage archivePart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "经办人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "流程号") @RequestParam(value = "processNO", required = false) String processNO,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences
    ) {
        try {
            saPartFlowLogService.addArchivePart(partId, operatorId, remarks, exchangeTime, processNO, partSequences);
            return Result.success("0", "封存成功");
        } catch (Exception e) {
            logger.error("封存异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|拆机")
    @PostMapping("/unpackPart")
    @RequiresPermissions("part:data:returnPart")
    public ResponseMessage unpackPart(
            @ApiParam(value = "零部件ID", required = true) @RequestParam(value = "partId") String partId,
            @ApiParam(value = "经办人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "拆机时间", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "流程号") @RequestParam(value = "processNO", required = false) String processNO,
            @ApiParam(value = "样品序列号", required = true) @RequestParam(value = "partSequences")
                    List<String> partSequences
    ) {
        try {
            saPartFlowLogService.addUnpackPart(partId, operatorId, remarks, exchangeTime, processNO, partSequences);
            return Result.success("0", "拆机成功");
        } catch (Exception e) {
            logger.error("拆机异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|导出二维码")
    @GetMapping("/barCode")
    @RequiresPermissions("part:data:barCode")
    public ResponseMessage barCode(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String[] ids,
            @ApiParam(hidden = true) HttpServletResponse response
    ) {
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            List<SaPartsQRCodeVO> partBarcodeVOList = saPartDataEOService.barCode(ids);
            if (!partBarcodeVOList.isEmpty()) {
                PDFUtil.partQRCodePdf(partBarcodeVOList, response);
                return Result.success("0", "导出二维码成功");
            }
            return Result.error("-1", "零部件不存在", null);
        } catch (Exception e) {
            logger.error("封存异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|导入")
    @PostMapping("/importFile")
    @RequiresPermissions("part:data:importFile")
    public ResponseMessage importFile(
            @ApiParam(value = "file", required = true) MultipartFile file
    ) {
        try {
            Map<String, Object> read = ExcelDataUtil.read(file, 2);
            if (read.get("failed") != null) {
                return Result.error("-1", "第" + read.get("failed") + "行数据格式错误，上传失败", null);
            } else if(read.get("nullAll") != null){
                return Result.error(read.get("nullAll").toString());
            }else {
                List<ArrayList<String>> result = (List<ArrayList<String>>)read.get("success");
                for(ArrayList<String> arr:result ){
                    String partName = arr.get(1);   //样品名称
                    String partSampleNumber = arr.get(2);   //样件数量
                    if(!StringUtils.isNotBlank(partName) || !StringUtils.isNotBlank(partSampleNumber)) {
                        return Result.error("-1", "导入错误：导入的数据中存在非空字段为空", null);
                    }
                }
                saPartDataEOService.saveForList((List<ArrayList<String>>) read.get("success"));
            }
            return Result.success("0", "导入成功");
        } catch (Exception e) {
            logger.error("导入异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|导出")
    @GetMapping("/exportFile")
    @RequiresPermissions("part:data:exportFile")
    public ResponseMessage exportFile(
            @ApiParam(value = "试验任务书编号") @RequestParam(value = "trialTaskBookNO", required = false) String trialApplyNO,
            //@ApiParam(value = "检验委托合同编号") @RequestParam(value = "trialApplyNO", required = false) String trialApplyNO,
            @ApiParam(value = "样品名称") @RequestParam(value = "partName", required = false) String partName,
            @ApiParam(value = "零部件号/发动机号") @RequestParam(value = "partEngineNo", required = false) String partEngineNo,
            @ApiParam(value = "样件数量") @RequestParam(value = "sampleNumber", required = false) String sampleNumber,
            @ApiParam(value = "在架") @RequestParam(value = "inShelf", required = false) Integer inShelf,
            @ApiParam(value = "生产厂家") @RequestParam(value = "producedFactory", required = false) String producedFactory,
            @ApiParam(value = "送样人") @RequestParam(value = "sendPartUserName", required = false)
                    String sendPartUserName,
            @ApiParam(value = "领养人") @RequestParam(value = "getPartUserName", required = false) String getPartUserName,
            @ApiParam(value = "所在位置") @RequestParam(value = "PartSpaceLocation", required = false)
                    String partSpaceLocation,
            @ApiParam(value = "创建时间开始") @RequestParam(value = "createTimeStart", required = false) String createTimeStart,
            @ApiParam(value = "创建时间结束") @RequestParam(value = "createTimeEnd", required = false) String createTimeEnd,
            @ApiParam(value = "通用查询") @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
            @ApiParam(hidden = true) HttpServletResponse response
    ) {
        try {
            SaPartDataEOPage eoPage = new SaPartDataEOPage();
            if (StringUtils.isNotBlank(trialApplyNO)) {
                eoPage.setTrialApplyNO(trialApplyNO.trim());
            }
            if (StringUtils.isNotBlank(partName)) {
                eoPage.setPartName(partName.trim());
            }
            if (StringUtils.isNotBlank(partEngineNo)) {
                eoPage.setPartEngineNo(partEngineNo.trim());
            }
            if (StringUtils.isNotBlank(sampleNumber)) {
                eoPage.setSampleNumber(sampleNumber.trim());
            }
            if (StringUtils.isNotBlank(producedFactory)) {
                eoPage.setProducedFactory(producedFactory.trim());
            }
            if (StringUtils.isNotBlank(sendPartUserName)) {
                eoPage.setSendPartUserName(sendPartUserName.trim());
            }
            if (StringUtils.isNotBlank(getPartUserName)) {
                eoPage.setGetPartUserName(getPartUserName.trim());
            }
            if (StringUtils.isNotBlank(partSpaceLocation)) {
                eoPage.setPartSpaceLocation(partSpaceLocation.trim());
            }
            if (StringUtils.isNotBlank(createTimeStart)) {
                eoPage.setCreateTimeStart(createTimeStart.trim());
            }
            if (StringUtils.isNotBlank(createTimeEnd)) {
                eoPage.setCreateTimeEnd(createTimeEnd.trim());
            }
            if (inShelf != null && inShelf > 0) {
                eoPage.setInShelf(inShelf);
            }
            if (StringUtils.isNotBlank(searchPhrase)) {
                List<String> list = TransformUtil.settingSearchPhrase(searchPhrase);
                eoPage.setKeyWords(list);
            }
            List<SaPartDataVO> saPartDataVOS = saPartDataEOService.queryListForExcel(eoPage);
            if (saPartDataVOS != null && !saPartDataVOS.isEmpty()) {
                for(int i = 0;i < saPartDataVOS.size();i++){
                    if(saPartDataVOS.get(i).getSendPartUserId() != null && !saPartDataVOS.get(i).getSendPartUserId().isEmpty()){
                        saPartDataVOS.get(i).setSendPartUserName(userEOService.getUserById(saPartDataVOS.get(i).getSendPartUserId()).getUsname());
                        saPartDataVOS.get(i).setSendPartUserPhone(userEOService.getUserById(saPartDataVOS.get(i).getSendPartUserId()).getCellPhoneNumber());
                        saPartDataVOS.get(i).setSendPartUserOrg(userEOService.getUserById(saPartDataVOS.get(i).getSendPartUserId()).getOrgName());
                    }
                    if(saPartDataVOS.get(i).getCreateBy() != null && !saPartDataVOS.get(i).getCreateBy().isEmpty()){
                        saPartDataVOS.get(i).setCreateByName(userEOService.getUserById(saPartDataVOS.get(i).getCreateBy()).getUsname());
                    }
                    saPartDataVOS.get(i).setPartStageName(saPartDataEOService.statusValue(String.valueOf(saPartDataVOS.get(i).getPartStage()),"planWorkStage"));
                    saPartDataVOS.get(i).setPartStatusName(saPartDataEOService.statusValue(String.valueOf(saPartDataVOS.get(i).getPartStatus()),"partStatus"));
                    saPartDataVOS.get(i).setTrialTypeName(saPartDataEOService.statusValue(String.valueOf(saPartDataVOS.get(i).getTrialType()),"business_type"));
                    saPartDataVOS.get(i).setStatusName(saPartDataEOService.statusValue(String.valueOf(saPartDataVOS.get(i).getStatus()),"sampleFlag"));
                }
                ExportDataUtil.exportPartData(saPartDataVOS, response);
            }
            return Result.success("0", "导出成功");
        } catch (Exception e) {
            logger.error("导入异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    /**
     * 零部件生成样品编号
     * @param trialTaskBookNO
     * @return
     */
    @ApiOperation(value = "|零部件生成样品编号")
    @GetMapping(path ="/createNum")
    public ResponseMessage createNum(String trialTaskBookNO,String id) {
        try {
            //零部件流水号参数管理code:PartSerialNumber
            ParamEO paramByCode = paramEOService.getParamByCode("PartSerialNumber");
            String paramValue = paramByCode.getParamValue();
            String num = "";
            if (StringUtils.isEmpty(trialTaskBookNO)){
                return Result.success("0", "生成成功",num);
            }
            if (StringUtils.isEmpty(id)){
                num = saPartDataEOService.createNum(paramValue, trialTaskBookNO, paramByCode.getId());
            }else {
                SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(id);
                String sampleNO = saPartDataEO.getSampleNO();
                if (StringUtils.isEmpty(sampleNO)){
                    num = saPartDataEOService.createNum(paramValue, trialTaskBookNO, paramByCode.getId());
                }else{
                    //编辑试验任务书编号，后面流水号不变
                    String oldSerialNumber = sampleNO.substring(sampleNO.length()-3,sampleNO.length());
                    num = trialTaskBookNO + oldSerialNumber;
                }
            }
            return Result.success("0", "生成成功",num);
        } catch (Exception e) {
            logger.error("生成异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }
}
