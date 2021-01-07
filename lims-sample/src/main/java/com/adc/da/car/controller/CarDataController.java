package com.adc.da.car.controller;

import com.adc.da.TimeUtil.KCarInfoSchedulingConfig;
import com.adc.da.base.web.BaseController;
import com.adc.da.car.dto.SaCarDataDTO;
import com.adc.da.car.entity.SaCarFlowLogEO;
import com.adc.da.car.page.SaCarBrrowEOPage;
import com.adc.da.car.page.SaCarDataEOPage;
import com.adc.da.car.page.SaCarFlowLogEOPage;
import com.adc.da.car.page.SaCarReturnEOPage;
import com.adc.da.car.service.SaCarDataService;
import com.adc.da.car.service.SaCarFlowLogService;
import com.adc.da.car.vo.CarBarcodeVO;
import com.adc.da.car.vo.SaBackCarDataVO;
import com.adc.da.car.vo.SaCarDataListVO;
import com.adc.da.car.vo.SaCarDataVO;
import com.adc.da.car.vo.SaCarFlowLogVO;
import com.adc.da.car.vo.TrialSampleVO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.ExportDataUtil;
import com.adc.da.common.PDFUtil;
import com.adc.da.common.utils.ExcelDataUtil;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.FileUtil;
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
import java.util.List;
import java.util.Map;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:29
 * @description：${description}
 */

@RestController
@RequestMapping("/${restPath}/sa/carData")
@Api(
        tags = {"Sa-整车管理"}
)
public class CarDataController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CarDataController.class);

    @Autowired
    private SaCarDataService saCarDataService;

    @Autowired
    private SaCarFlowLogService saCarFlowLogService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private BeanMapper beanMapper;
    
    @Autowired
    private KCarInfoSchedulingConfig KCarInfoSchedulingConfig;
    

    @ApiOperation(value = "|查询列表")
    @GetMapping("")
    @RequiresPermissions("car:data:page")
    public ResponseMessage<PageInfo<SaCarDataListVO>> getList(
            @ApiParam(value = "检验委托合同编号") @RequestParam(value = "trialApplyNO", required = false) String trialApplyNO,
            @ApiParam(value = "车辆名称") @RequestParam(value = "carName", required = false) String carName,
            @ApiParam(value = "项目平台") @RequestParam(value = "projectName", required = false) String projectName,
            @ApiParam(value = "VIN码") @RequestParam(value = "carVin", required = false) String carVin,
            @ApiParam(value = "生产厂家") @RequestParam(value = "producedFactory", required = false) String producedFactory,
            @ApiParam(value = "送样人") @RequestParam(value = "sendCarUserName", required = false) String sendCarUserName,
            @ApiParam(value = "领养人") @RequestParam(value = "getCarUserName", required = false) String getCarUserName,
            @ApiParam(value = "所在位置") @RequestParam(value = "carSpaceLocation", required = false)
                    String carSpaceLocation,
            @ApiParam(value = "状态") @RequestParam(value = "carStatus", required = false) String carStatus,
            
            @ApiParam(value = "发动机编号") @RequestParam(value = "carEngineNo", required = false) String carEngineNo,
            @ApiParam(value = "发动机型号") @RequestParam(value = "carEngineType", required = false) String carEngineType,
            @ApiParam(value = "底盘号") @RequestParam(value = "chassisNumber", required = false) String chassisNumber,
            
            @ApiParam(value = "通用查询") @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
            @ApiParam(value = "页数", required = true) @RequestParam(value = "page") Integer page,
            @ApiParam(value = "每页大小", required = true) @RequestParam(value = "pageSize") Integer pageSize
    ) {
        try {
            SaCarDataEOPage eoPage = new SaCarDataEOPage();
            if (StringUtils.isNotBlank(trialApplyNO)) {
                eoPage.setTrialApplyNO(trialApplyNO.trim());
            }
            if (StringUtils.isNotBlank(carName)) {
                eoPage.setCarName(carName.trim());
            }
            if (StringUtils.isNotBlank(projectName)) {
                eoPage.setProjectName(projectName.trim());
            }
            if (StringUtils.isNotBlank(carVin)) {
                eoPage.setCarVin(carVin.trim());
            }
            if (StringUtils.isNotBlank(producedFactory)) {
                eoPage.setProducedFactory(producedFactory.trim());
            }
            if (StringUtils.isNotBlank(sendCarUserName)) {
                eoPage.setSendCarUserName(sendCarUserName.trim());
            }
            if (StringUtils.isNotBlank(getCarUserName)) {
                eoPage.setGetCarUserName(getCarUserName.trim());
            }
            if (StringUtils.isNotBlank(carSpaceLocation)) {
                eoPage.setCarSpaceLocation(carSpaceLocation.trim());
            }
            if (StringUtils.isNotBlank(carStatus)) {
                eoPage.setCarStatus(carStatus.trim());
            }
            
            if (StringUtils.isNotBlank(carEngineNo)) {
                eoPage.setCarEngineNo(carEngineNo.trim());
            }
            if (StringUtils.isNotBlank(carEngineType)) {
                eoPage.setCarEngineType(carEngineType.trim());
            }
            if (StringUtils.isNotBlank(chassisNumber)) {
                eoPage.setChassisNumber(chassisNumber.trim());
            }
            
            if (StringUtils.isNotBlank(searchPhrase)) {
                List<String> list = TransformUtil.settingSearchPhrase(searchPhrase);
                eoPage.setKeyWords(list);
            }
            eoPage.setPage(page);
            eoPage.setPageSize(pageSize);
            List<SaCarDataListVO> saCarDataEOS = saCarDataService.selectByPage(eoPage);
            return Result.success(getPageInfo(eoPage.getPager(), saCarDataEOS));
        } catch (Exception e) {
            logger.error("查询列表异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }
    
    @ApiOperation(value = "|借车单分页查询")
    @GetMapping("brrow")
    public ResponseMessage<PageInfo<SaCarBrrowEOPage>> brrowPage(
    		SaCarBrrowEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
			List<SaCarBrrowEOPage> rows =  saCarDataService.brrowPage(page, searchPhrase);
			//设置总条数
			Integer rowsCount = saCarDataService.queryBrrowByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), SaCarBrrowEOPage.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    @ApiOperation(value = "|还车单分页查询")
    @GetMapping("return")
    public ResponseMessage<PageInfo<SaCarReturnEOPage>> returnPage(
    		SaCarReturnEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase){
    	try {
			List<SaCarReturnEOPage> rows =  saCarDataService.returnPage(page, searchPhrase);
			//设置总条数
			Integer rowsCount = saCarDataService.queryReturnByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), SaCarReturnEOPage.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }

    @ApiOperation(value = "|保存整车")
    @PostMapping("")
    @RequiresPermissions("car:data:save")
    public ResponseMessage save(@RequestBody SaCarDataDTO saCarDataDTO) {
        try {
            if (StringUtils.isBlank(saCarDataDTO.getCarName())) {
                return Result.success("-1", "参数异常: 名称 不能为空", null);
            }
            if (StringUtils.isBlank(saCarDataDTO.getCarVin())) {
                return Result.success("-1", "参数异常: vin码 不能为空", null);
            }
            if (StringUtils.isBlank(saCarDataDTO.getReceivedUserId())){
                return Result.success("-1","参数异常: 接收人 不能为空");
            }
            ResponseMessage message = TransformUtil.verifyMatcher(saCarDataDTO, "passengerNumber", "carMilieage",
                    "carWeight");
            if (message != null) {
                return message;
            }
            saCarDataService.save(saCarDataDTO);
            return Result.success("0", "保存成功");
        } catch (Exception e) {
            logger.error("保存整车异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|编辑整车")
    @PutMapping("")
    @RequiresPermissions("car:data:edit")
    public ResponseMessage edit(@RequestBody SaCarDataDTO saCarDataDTO) {
        try {
            if (StringUtils.isBlank(saCarDataDTO.getCarName())) {
                return Result.success("-1", "参数异常: 名称 不能为空", null);
            }
            if (StringUtils.isBlank(saCarDataDTO.getCarVin())) {
                return Result.success("-1", "参数异常: vin码 不能为空", null);
            }
            if (StringUtils.isBlank(saCarDataDTO.getReceivedUserId())){
                return Result.success("-1","参数异常: 接收人 不能为空");
            }
            ResponseMessage message = TransformUtil.verifyMatcher(saCarDataDTO, "passengerNumber", "carMilieage",
                    "carWeight");
            if (message != null) {
                return message;
            }
            saCarDataService.edit(saCarDataDTO);
            return Result.success("0", "编辑成功");
        } catch (Exception e) {
            logger.error("编辑整车异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|查询详情")
    @GetMapping("/{id}")
    @RequiresPermissions("car:data:getOne")
    public ResponseMessage<SaCarDataVO> getOne(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            return Result.success(saCarDataService.getOne(id));
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|校验试验委托合同")
    @GetMapping("/verify")
    @RequiresPermissions("car:data:getOne")
    public ResponseMessage<SaCarDataVO> verifyTrialNo(
            @ApiParam(value = "trialNo", required = true) @RequestParam(value = "trialNo") String trialNo
    ) {
        try {
            TrialSampleVO trialSampleVO = saCarDataService.verifyTrialNo(trialNo);
            if (trialSampleVO == null) {
                return Result.error("-1", "试验委托编号不存在");
            } else {
                return Result.success();
            }
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|通过停车场ID和车位号查询详情")
    @GetMapping("/depot")
    @RequiresPermissions("car:data:getOne")
    public ResponseMessage<SaCarDataVO> getOneByDepotId(
            @ApiParam(value = "depotId", required = true) @RequestParam(value = "depotId") String depotId,
            @ApiParam(value = "carSpaceNumber", required = true) @RequestParam(value = "carSpaceNumber")
                    String carSpaceNumber
    ) {
        try {
            return Result.success(saCarDataService.getOneByDepotId(depotId, carSpaceNumber));
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("car:data:delete")
    public ResponseMessage deleteById(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            saCarDataService.deleteById(id);
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|批量删除")
    @DeleteMapping("/ids")
    @RequiresPermissions("car:data:delete")
    public ResponseMessage deleteByIds(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String[] ids
    ) {
        try {
            for (String id : ids) {
                saCarDataService.deleteById(id);
            }
            return Result.success("0", "删除成功");
        } catch (Exception e) {
            logger.error("删除异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|查看流转日志")
    @GetMapping("/logList")
    @RequiresPermissions("car:data:log")
    public ResponseMessage<PageInfo<SaCarFlowLogVO>> logList(
            @ApiParam(value = "carId", required = true) @RequestParam(value = "carId") String carId
    ) {
        try {
            SaCarFlowLogEOPage page = new SaCarFlowLogEOPage();
            page.setCarId(carId);
            List<SaCarFlowLogEO> rows = saCarFlowLogService.queryByPage(page);
            PageInfo<SaCarFlowLogVO> pageInfo = beanMapper.mapPage(getPageInfo(page.getPager(), rows),
                    SaCarFlowLogVO.class);
            if (pageInfo.getList() != null && !pageInfo.getList().isEmpty()) {
                for (SaCarFlowLogVO vo : pageInfo.getList()) {
                    settingUserInfo(vo);
                }
            }
            return Result.success(pageInfo);
        } catch (Exception e) {
            logger.error("查看流转日志异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    public void settingUserInfo(SaCarFlowLogVO vo) {
        StringBuilder sb = new StringBuilder();
        // 获取负责人信息
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
    @PostMapping("/receivedCar")
    @RequiresPermissions("car:data:receivedCar")
    public ResponseMessage receivedCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "收样人ID", required = true) @RequestParam(value = "receivUserId") String receivUserId,
            @ApiParam(value = "样品管理员Id", required = true) @RequestParam(value = "sampleMasterId") String sampleMasterId,
            @ApiParam(value = "委托人Id", required = true) @RequestParam(value = "entrustUserId") String entrustUserId,
            @ApiParam(value = "收样日期", required = true) @RequestParam(value = "receivTime") String receivTime,
            @ApiParam(value = "报告结果确认") @RequestParam(value = "reportResult", required = false) String reportResult,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "附件ID") @RequestParam(value = "attachmentId", required = false) String attachmentId
    ) {
        try {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setCarDataId(carId);
            saCarFlowLogEO.setLeaderId(receivUserId);
            saCarFlowLogEO.setOperationDate(receivTime);
            saCarFlowLogEO.setOperationContent("收样");
            saCarFlowLogEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("报告结果确认：").append(reportResult);
            saCarFlowLogEO.setOthers(sb.toString());
            saCarFlowLogEO.setEntrustUserId(entrustUserId);
            saCarFlowLogEO.setSampleUserId(sampleMasterId);
            saCarFlowLogService.addReceivedCar(saCarFlowLogEO, attachmentId);
            return Result.success("0", "收样成功");
        } catch (Exception e) {
            logger.error("收样异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|领样")
    @PostMapping("/collectCar")
    @RequiresPermissions("car:data:collectCar")
    public ResponseMessage collectCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "领样人ID", required = true) @RequestParam(value = "getCarUserId") String getCarUserId,
            @ApiParam(value = "操作人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "检验委托合同编号", required = true) @RequestParam(value = "trialApplyNO") String trialApplyNO,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "样品状态") @RequestParam(value = "carStatus", required = false) String carStatus,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "是否退样") @RequestParam(value = "whetherReturn", required = false) String whetherReturn
    ) {
        try {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setCarDataId(carId);
            saCarFlowLogEO.setLeaderId(getCarUserId);
            saCarFlowLogEO.setOperationContent("领样");
            saCarFlowLogEO.setOperatorId(operatorId);
            saCarFlowLogEO.setOperationDate(exchangeTime);
            saCarFlowLogEO.setTrialApplyNo(trialApplyNO);
            saCarFlowLogEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("样品状态：").append(carStatus).append("是否退样：").append(whetherReturn);
            saCarFlowLogEO.setOthers(sb.toString());
            saCarFlowLogService.addCollectCar(saCarFlowLogEO);
            return Result.success("0", "领样成功");
        } catch (Exception e) {
            logger.error("领样异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|流转")
    @PostMapping("/flowCar")
    @RequiresPermissions("car:data:flowCar")
    public ResponseMessage flowCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "交样人ID", required = true) @RequestParam(value = "sendCarUserId") String sendCarUserId,
            @ApiParam(value = "领养人ID", required = true) @RequestParam(value = "getCarUserId") String getCarUserId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "样品状态") @RequestParam(value = "carStatus", required = false) String carStatus
    ) {
        try {
            saCarFlowLogService.addFlowCar(carId, sendCarUserId, getCarUserId, remarks, exchangeTime, carStatus);
            return Result.success("0", "流转成功");
        } catch (Exception e) {
            logger.error("流转异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|归还")
    @PostMapping("/returnCar")
    @RequiresPermissions("car:data:returnCar")
    public ResponseMessage returnCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "归还人ID", required = true) @RequestParam(value = "returnCarUserId") String returnCarUserId,
            @ApiParam(value = "经办人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "留样截止时间") @RequestParam(value = "reserveTime", required = false) String reserveTime,
            @ApiParam(value = "车位ID") @RequestParam(value = "carSpaceId", required = false) String carSpaceId,
            @ApiParam(value = "车位号") @RequestParam(value = "carSpaceNO", required = false) Integer carSpaceNO
    ) {
        try {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setCarDataId(carId);
            saCarFlowLogEO.setLeaderId(returnCarUserId);
            saCarFlowLogEO.setOperationContent("归还");
            saCarFlowLogEO.setOperatorId(operatorId);
            saCarFlowLogEO.setOperationDate(exchangeTime);
            saCarFlowLogEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("留样截止时间：").append(reserveTime);
            saCarFlowLogEO.setOthers(sb.toString());
            saCarFlowLogService.addReturnCar(saCarFlowLogEO, carSpaceId, carSpaceNO);
            return Result.success("0", "归还成功");
        } catch (Exception e) {
            logger.error("归还异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|退样")
    @PostMapping("/backCar")
    @RequiresPermissions("car:data:backCar")
    public ResponseMessage backCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "退样人ID", required = true) @RequestParam(value = "backCarUserId") String backCarUserId,
            @ApiParam(value = "经办人Id", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "委托人Id", required = true) @RequestParam(value = "entrustUserId") String entrustUserId,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "receivTime") String receivTime,
            @ApiParam(value = "报告结果确认") @RequestParam(value = "reportResult", required = false) String reportResult,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "附件ID") @RequestParam(value = "attachmentId", required = false) String attachmentId
    ) {
        try {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setCarDataId(carId);
            saCarFlowLogEO.setLeaderId(backCarUserId);
            saCarFlowLogEO.setOperationDate(receivTime);
            saCarFlowLogEO.setOperatorId(operatorId);
            saCarFlowLogEO.setOperationContent("退样");
            saCarFlowLogEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("报告结果确认：").append(reportResult);
            saCarFlowLogEO.setOthers(sb.toString());
            saCarFlowLogEO.setEntrustUserId(entrustUserId);
            saCarFlowLogService.addBackCar(saCarFlowLogEO, attachmentId);
            return Result.success("0", "退样成功");
        } catch (Exception e) {
            logger.error(" 退样异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|报废")
    @PostMapping("/scrapCar")
    @RequiresPermissions("car:data:scrapCar")
    public ResponseMessage scrapCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "经办人Id", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "报废日期", required = true) @RequestParam(value = "scrapTime") String scrapTime,
            @ApiParam(value = "流程号") @RequestParam(value = "processNO", required = false) String processNO,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks
    ) {
        try {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setCarDataId(carId);
            saCarFlowLogEO.setOperationDate(scrapTime);
            saCarFlowLogEO.setOperatorId(operatorId);
            saCarFlowLogEO.setOperationContent("报废");
            saCarFlowLogEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("流程号：").append(processNO);
            saCarFlowLogEO.setOthers(sb.toString());
            saCarFlowLogService.addScrapCar(saCarFlowLogEO);
            return Result.success("0", "报废成功");
        } catch (Exception e) {
            logger.error(" 报废异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|封存")
    @PostMapping("/archiveCar")
    @RequiresPermissions("car:data:returnCar")
    public ResponseMessage archiveCar(
            @ApiParam(value = "整车ID", required = true) @RequestParam(value = "carId") String carId,
            @ApiParam(value = "封存人ID", required = true) @RequestParam(value = "returnCarUserId")
                    String archiveCarUserId,
            @ApiParam(value = "经办人ID", required = true) @RequestParam(value = "operatorId") String operatorId,
            @ApiParam(value = "备注") @RequestParam(value = "remarks", required = false) String remarks,
            @ApiParam(value = "交接日期", required = true) @RequestParam(value = "exchangeTime") String exchangeTime,
            @ApiParam(value = "封存截止时间") @RequestParam(value = "reserveTime", required = false) String archiveTime,
            @ApiParam(value = "车位ID") @RequestParam(value = "carSpaceId", required = false) String carSpaceId,
            @ApiParam(value = "车位号") @RequestParam(value = "carSpaceNO", required = false) Integer carSpaceNO
    ) {
        try {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setCarDataId(carId);
            saCarFlowLogEO.setLeaderId(archiveCarUserId);
            saCarFlowLogEO.setOperationContent("封存");
            saCarFlowLogEO.setOperatorId(operatorId);
            saCarFlowLogEO.setOperationDate(exchangeTime);
            saCarFlowLogEO.setRemarks(remarks);
            StringBuilder sb = new StringBuilder();
            sb.append("封存截止时间：").append(archiveTime);
            saCarFlowLogEO.setOthers(sb.toString());
            saCarFlowLogService.addArchiveCar(saCarFlowLogEO, carSpaceId, carSpaceNO);
            return Result.success("0", "封存成功");
        } catch (Exception e) {
            logger.error("封存异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|导出二维码")
    @GetMapping("/barCode")
    @RequiresPermissions("car:data:barCode")
    public ResponseMessage barCode(
            @ApiParam(value = "ids", required = true) @RequestParam(value = "ids") String[] ids,
            @ApiParam(hidden = true) HttpServletResponse response
    ) {
        try {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            List<CarBarcodeVO> carBarcodeVOList = saCarDataService.barCode(ids);
            if (!carBarcodeVOList.isEmpty()) {
                PDFUtil.generalBarcodePdf(carBarcodeVOList, response);
                return Result.success("0", "导出二维码成功");
            }
            return Result.error("-1", "整车不存在", null);
        } catch (Exception e) {
            logger.error("导出二维码异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|导入")
    @PostMapping(path = "/importFile")
    @RequiresPermissions("car:data:importFile")
    public ResponseMessage<String> importFile(
            @RequestParam("file") MultipartFile file) {
        try {
            if(com.adc.da.util.utils.StringUtils.isNotEmpty(file)) {
                //得到该文件后缀
                String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
                if (!ConstantUtils.FILE_EXTEND_XLS.equalsIgnoreCase(fileExtension)
                        &&!ConstantUtils.FILE_EXTEND_XLSX.equalsIgnoreCase(fileExtension)){
                    return Result.error( "-1","整车样品信息的批量导入只支持上传Excel格式的文件!");
                }
            }
            ResponseMessage result  = saCarDataService.importMateriel(file);
            return result;
        } catch (Exception e) {
            logger.error("导入异常：", e.getMessage());
            return Result.error("-1", "上传失败", null);
        }
    }

    @ApiOperation(value = "|导出")
    @PostMapping("/exportFile")
    @RequiresPermissions("car:data:exportFile")
    public ResponseMessage exportFile(
            @ApiParam(value = "检验委托合同编号") @RequestParam(value = "trialApplyNO", required = false) String trialApplyNO,
            @ApiParam(value = "车辆名称") @RequestParam(value = "carName", required = false) String carName,
            @ApiParam(value = "项目平台") @RequestParam(value = "projectName", required = false) String projectName,
            @ApiParam(value = "VIN码") @RequestParam(value = "carVin", required = false) String carVin,
            @ApiParam(value = "生产厂家") @RequestParam(value = "producedFactory", required = false) String producedFactory,
            @ApiParam(value = "送样人") @RequestParam(value = "sendCarUserName", required = false) String sendCarUserName,
            @ApiParam(value = "领养人") @RequestParam(value = "getCarUserName", required = false) String getCarUserName,
            @ApiParam(value = "所在位置") @RequestParam(value = "carSpaceLocation", required = false)
                    String carSpaceLocation,
            @ApiParam(value = "状态") @RequestParam(value = "carStatus", required = false) String carStatus,
            @ApiParam(value = "通用查询") @RequestParam(value = "searchPhrase", required = false) String searchPhrase,
            @ApiParam(hidden = true) HttpServletResponse response
    ) {
        try {
            SaCarDataEOPage page = new SaCarDataEOPage();
            if (StringUtils.isNotBlank(trialApplyNO)) {
                page.setTrialApplyNO(trialApplyNO.trim());
            }
            if (StringUtils.isNotBlank(carName)) {
                page.setCarName(carName.trim());
            }
            if (StringUtils.isNotBlank(projectName)) {
                page.setProjectName(projectName.trim());
            }
            if (StringUtils.isNotBlank(carVin)) {
                page.setCarVin(carVin.trim());
            }
            if (StringUtils.isNotBlank(producedFactory)) {
                page.setProducedFactory(producedFactory.trim());
            }
            if (StringUtils.isNotBlank(sendCarUserName)) {
                page.setSendCarUserName(sendCarUserName.trim());
            }
            if (StringUtils.isNotBlank(getCarUserName)) {
                page.setGetCarUserName(getCarUserName.trim());
            }
            if (StringUtils.isNotBlank(carSpaceLocation)) {
                page.setCarSpaceLocation(carSpaceLocation.trim());
            }
            if (StringUtils.isNotBlank(carStatus)) {
                page.setCarStatus(carStatus.trim());
            }
            if (StringUtils.isNotBlank(searchPhrase)) {
                List<String> list = TransformUtil.settingSearchPhrase(searchPhrase);
                page.setKeyWords(list);
            }
            List<SaCarDataVO> saCarDataVOS = saCarDataService.queryListForExcel(page);
            if (saCarDataVOS != null && !saCarDataVOS.isEmpty()) {
                ExportDataUtil.exportCarData(saCarDataVOS, response);
            }
            return Result.success("0", "导出成功");
        } catch (Exception e) {
            logger.error("导出异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    @ApiOperation(value = "|查询退样打印单")
    @GetMapping("backCar/{id}")
    @RequiresPermissions("car:data:backCar")
    public ResponseMessage<SaBackCarDataVO> backCar(
            @ApiParam(value = "id", required = true) @PathVariable(value = "id") String id
    ) {
        try {
            return Result.success(saCarDataService.backCar(id));
        } catch (Exception e) {
            logger.error("查询详情异常：", e);
            return Result.error("-1", "参数异常", null);
        }
    }

    
    /**
	 * 校验底盘号唯一
	 * @Title: checkNo
	 * @param id
	 * @param chassisNumber
	 * @return
	 * @return boolean
	 * @author: ljy
	 * @date: 2020年4月21日
	 */
    @ApiOperation(value = "|校验底盘号唯一")
    @GetMapping("/checkNo")
    @RequiresPermissions("car:data:page")
    public ResponseMessage checkNo(String id, String chassisNumber) {
    	try {
    		if(saCarDataService.checkNo(id, chassisNumber)) {
    			return Result.success("1", "该底盘号已存在!", chassisNumber);
    		}else {
    			return Result.success("0", "校验通过!", chassisNumber);
    		}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","校验失败!");
		}
    }
    
    
    @ApiOperation(value = "|测试K库样品同步")
    @GetMapping("/testKCarInfo")
    public ResponseMessage testKCarInfo() {
    	try {
    		KCarInfoSchedulingConfig.getKCarInfo();
    		return Result.error("0","同步成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1","同步失败!");
		}
    }
}
