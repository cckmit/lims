package com.adc.da.pc_return_application.service;

import com.adc.da.acttask.service.ActProcessService;
import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_return_application.dao.PcCarReturnApplicationEODao;
import com.adc.da.pc_return_application.entity.PcCarReturnApplicationEO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <br>
 * <b>功能：</b>PC_CAR_RETURN_APPLICATION PcCarReturnApplicationEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcCarReturnApplicationEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcCarReturnApplicationEOService extends BaseService<PcCarReturnApplicationEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcCarReturnApplicationEOService.class);

    @Autowired
    private PcCarReturnApplicationEODao dao;

    public PcCarReturnApplicationEODao getDao() {
        return dao;
    }

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private SaCarDataDAO saCarDataDAO;

    @Autowired
    private PrimaryGenerater primaryGenerater;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    /**
     * 保存
     * @param pcCarReturnApplicationEO ：还车单实体
     * @return
     */
    public int insertSelective(PcCarReturnApplicationEO pcCarReturnApplicationEO) throws Exception {
        //判断流水号是否已被占用
        String codeNum = primaryGenerater.queryLLDCode("HCD", ConstantUtils.PC_CAR_RETURN_CODE);
        if (!codeNum.equals(pcCarReturnApplicationEO.getReturnCarCode())) {
            pcCarReturnApplicationEO.setReturnCarCode(codeNum);
        }
        String currDate = DateUtils.dateToString(new Date(), "yyyy-MM-dd");
        //创建人id
        pcCarReturnApplicationEO.setCreateBy(UserUtils.getUserId());
//        //创建人组织机构
//        pcCarReturnApplicationEO.setCreateByOrg(UserUtils.getUser().getOrgName());
        //创建人姓名
        pcCarReturnApplicationEO.setCreateByName(UserUtils.getUser().getUsname());
        //申请时间
        pcCarReturnApplicationEO.setCreateTime(currDate);
        //还车--样品状态变更为在库
        if(StringUtils.isNotEmpty(pcCarReturnApplicationEO.getCarId())) {
            saCarDataDAO.changeStatus(pcCarReturnApplicationEO.getCarId(), "3");
        }
        //更新流水号
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_CAR_RETURN_CODE);
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
        //保存还车单
        return dao.insertSelective(pcCarReturnApplicationEO);
    }

    /**
     *  *
     *      * 审批流程
     * @param request
     * @param requestEO
     * @param baseBusEO
     * @throws Exception
     */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO, BaseBusEO baseBusEO) throws Exception {
        //审批
        actProcessService.approvalProcess(request,requestEO,baseBusEO,dao);
    }

    /**
     * 判断流程是否结束，结束的话，变更业务状态
     * @param procId  流程实例id
     * @param id  业务id
     */
    public void isFinishied(String procId, String id) {
        if (baseBusEODao.isFinishied(procId) == 1) {
            if (com.adc.da.util.utils.StringUtils.isNotEmpty(id)) {
                //已审批
                dao.changeStatus(id, "5");
            }
        }
    }

    /**
     * 启动流程
     * @param pcCarReturnApplicationEO
     * @throws Exception
     */
    public void startProcess(PcCarReturnApplicationEO pcCarReturnApplicationEO) throws Exception {
        //设置流程状态：待审批
        pcCarReturnApplicationEO.setStatus("1");
        if(StringUtils.isEmpty(pcCarReturnApplicationEO.getId())){
            insertSelective(pcCarReturnApplicationEO);
        }else{
            dao.updateByPrimaryKeySelective(pcCarReturnApplicationEO);
        }
        String pvOrcv = "";
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if(org.apache.commons.lang.StringUtils.isNotEmpty(flag)){
            if ("1".equals(flag)){
                pvOrcv = ConstantUtils.PV_CAR_RETURN;
            }else if("0".equals(flag)){
                pvOrcv = ConstantUtils.CV_CAR_RETURN;
            }
        }
		//0-试验任务；1-执行计划
		String taskOrPlan = "";
		if("1".equals(pcCarReturnApplicationEO.getTaskOrPlan())) {
			taskOrPlan = "执行计划-";
		}else {
			taskOrPlan = "试验任务-";
		}
        actProcessService.startProcess(pcCarReturnApplicationEO.getId(), pvOrcv, taskOrPlan + "还车申请审批流程");
    }

    /**
     * 导出doc
     * @param eo ： 还车单实体
     * @param response  ：请求体
     * @param request  ：响应体
     */
    public void exportDoc(PcCarReturnApplicationEO eo, HttpServletResponse response, HttpServletRequest request){
        //封装数据
        Map<String, Object> dataMap = new HashMap();
        String currTime = DateUtils.dateToString(new Date(), "yyyymmdd");
        //todo
        String loanType = eo.getLoanType();
        List<DicTypeEO> borrowTheCarAlone = dicTypeEOService.getDicTypeByDictionaryCode("borrowTheCarAlone");
        for(DicTypeEO dicTypeEO : borrowTheCarAlone){
            if (dicTypeEO.getDicTypeCode().trim().equals(loanType.trim())){
                loanType = dicTypeEO.getDicTypeName();
            }
        }
        dataMap.put("LOAN_TYPE",StringUtils.isNotEmpty(loanType)?loanType:"");
        dataMap.put("CREATE_BY_NAME",StringUtils.isNotEmpty(eo.getCreateByName())?eo.getCreateByName():"");
        dataMap.put("CREATE_BY_ORG",StringUtils.isNotEmpty(eo.getCreateByOrg())?eo.getCreateByOrg():"");
        dataMap.put("CREATE_BY_PHONE",StringUtils.isNotEmpty(eo.getCreateByPhone())?eo.getCreateByPhone():"");
        dataMap.put("USE_FOR",StringUtils.isNotEmpty(eo.getUseFor())?eo.getUseFor():"");
        dataMap.put("CHASSIS_CODE",StringUtils.isNotEmpty(eo.getChassisCode())?eo.getChassisCode():"");
        dataMap.put("ENGINE_CODE",StringUtils.isNotEmpty(eo.getEngineCode())?eo.getEngineCode():"");
        dataMap.put("CAR_CODE",StringUtils.isNotEmpty(eo.getCarCode())?eo.getCarCode():"");
        dataMap.put("BRAND_MODEL",StringUtils.isNotEmpty(eo.getBrandModel())?eo.getBrandModel():"");
        //todo
        dataMap.put("DISCHARGE_TYPE",StringUtils.isNotEmpty(eo.getDischargeType())?eo.getDischargeType():"");
        dataMap.put("PRODUCT_TIME",StringUtils.isNotEmpty(eo.getProductTime())?eo.getProductTime():"");
        dataMap.put("BUY_TIME",StringUtils.isNotEmpty(eo.getBuyTime())?eo.getBuyTime():"");
        //todo
        String carStatus = eo.getCarStatus();
        if (org.apache.commons.lang.StringUtils.isNotEmpty(carStatus)){
            if (carStatus.trim().equals("0")){
                carStatus = "退样";
            }else if (carStatus.trim().equals("1")){
                carStatus = "接收";
            }else if (carStatus.trim().equals("2")){
                carStatus = "领样";
            }else if (carStatus.trim().equals("3")){
                carStatus = "在库";
            }else if (carStatus.trim().equals("4")){
                carStatus = "报废";
            }else if (carStatus.trim().equals("5")){
                carStatus = "待收样";
            }else {
                carStatus = "封存";
            }
        }
        dataMap.put("CAR_STATUS",StringUtils.isNotEmpty(carStatus)?carStatus:"");
        dataMap.put("PLAN_RETURN_TIME",StringUtils.isNotEmpty(eo.getPlanReturnTime())?eo.getPlanReturnTime():"");
        //todo
        dataMap.put("INSPROJECT_TYPE",StringUtils.isNotEmpty(eo.getInsprojectType())?eo.getInsprojectType():"");
        dataMap.put("TEST_AREA",StringUtils.isNotEmpty(eo.getTestArea())?eo.getTestArea():"");
        //todo
        String TEST_TYPE = "";
        switch (eo.getTestType()) {
		case "0":
			TEST_TYPE = "全新试验";
			break;
		case "1":
			TEST_TYPE = "搭载试验";
			break;
		default:
			break;
		}
        dataMap.put("TEST_TYPE", TEST_TYPE);
        dataMap.put("TEST_START_TIME",StringUtils.isNotEmpty(eo.getTestStartTime())?eo.getTestStartTime():"");
        dataMap.put("TEST_END_TIME",StringUtils.isNotEmpty(eo.getTestEndTime())?eo.getTestEndTime():"");
        //todo
        String CAR_TYPE = "";
        switch (eo.getCarType()) {
		case "0":
			CAR_TYPE = "试验试制车";
			break;
		case "1":
			CAR_TYPE = "量产车";
			break;
		default:
			break;
		}
        dataMap.put("CAR_TYPE", CAR_TYPE);
        dataMap.put("PLAN_CODE",StringUtils.isNotEmpty(eo.getPlanCode())?eo.getPlanCode():"");
        dataMap.put("TEST_LINE",StringUtils.isNotEmpty(eo.getTestLine())?eo.getTestLine():"");
        dataMap.put("TEST_KM",StringUtils.isNotEmpty(eo.getTestKm())?eo.getTestKm():"");
        dataMap.put("TOTAL_KM",StringUtils.isNotEmpty(eo.getTotalKm())?eo.getTotalKm():"");
        dataMap.put("WITH_TOOL",StringUtils.isNotEmpty(eo.getWithTool())?eo.getWithTool():"");
        dataMap.put("APPEARANCE_STATE",StringUtils.isNotEmpty(eo.getAppearanceState())?eo.getAppearanceState():"");
        dataMap.put("TECHNICAL_ORDER_NAME",StringUtils.isNotEmpty(eo.getTechnicalOrderName())?eo.getTechnicalOrderName():"");
        dataMap.put("CHANGE_PARTS_NAME",StringUtils.isNotEmpty(eo.getChangePartsName())?eo.getChangePartsName():"");
        dataMap.put("BEAM_CODE_NUM",StringUtils.isNotEmpty(eo.getBeamCodeNum())?eo.getBeamCodeNum():"");
        dataMap.put("WELDING_SPOT_NUM",StringUtils.isNotEmpty(eo.getWeldingSpotNum())?eo.getWeldingSpotNum():"");
        //文件名
        String fileName = "还车单-"+currTime;
        //文件类型
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
        docUtil.createDoc(dataMap, "PcCarReturnDoc", response, request,
                fileName, fileExtend);
    }

    /**
     * 还车单编号
     * @return
     */
    public String findReturnCarCode(){
        return primaryGenerater.queryLLDCode("HCD",ConstantUtils.PC_CAR_RETURN_CODE);
    }

}
