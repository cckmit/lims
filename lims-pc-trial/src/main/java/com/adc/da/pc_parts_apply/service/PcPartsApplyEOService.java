package com.adc.da.pc_parts_apply.service;

import com.adc.da.acttask.service.ActProcessService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.DocUtil;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_outtrust.service.PcOutsourceEntrustEOService;
import com.adc.da.pc_parts_apply.dao.PcPartsApplySampleEODao;
import com.adc.da.pc_parts_apply.entity.PcPartsApplySampleEO;
import com.adc.da.pc_parts_apply.page.PcPartsApplySampleEOPage;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_parts_apply.dao.PcPartsApplyEODao;
import com.adc.da.pc_parts_apply.entity.PcPartsApplyEO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <br>
 * <b>功能：</b>PC_PARTS_APPLY PcPartsApplyEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-13 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcPartsApplyEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcPartsApplyEOService extends BaseService<PcPartsApplyEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcPartsApplyEOService.class);

    @Autowired
    private PcPartsApplyEODao dao;

    public PcPartsApplyEODao getDao() {
        return dao;
    }

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private PcOutsourceEntrustEOService pcOutsourceEntrustEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private TrialTaskEOService trialTaskEOService;

    @Autowired
    private DocUtil docUtil;

    @Autowired
    private ActProcessService actProcessService;

    @Autowired
    private PcPartsApplySampleEODao pcPartsApplySampleEODao;

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
     * * 导出
     *      * @param pcOutsourceEntrustEO
     * @param response
     * @param request
     * @param pcPartsApplyEO
     */
    public boolean exportDoc(HttpServletResponse response, HttpServletRequest request, PcPartsApplyEO pcPartsApplyEO) throws Exception {
        //委托实验类型：包含类型、样品、标准，逗号分隔
        //物料子表
        List<PcPartsApplySampleEO> pcPartsApplySampleEOList = pcPartsApplyEO.getPcPartsApplySampleEOList();
        Map<String, Object> dataMap = new HashMap();
        dataMap.put("sampleEOList", pcPartsApplySampleEOList);
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(pcPartsApplyEO.getTaskId());
        //领料单编号
        dataMap.put("pickingCode",StringUtils.isNotEmpty(pcPartsApplyEO.getPickingCode())?pcPartsApplyEO.getPickingCode():"");
        dataMap.put("torCode",StringUtils.isNotEmpty(pcPartsApplyEO.getTorCode())?pcPartsApplyEO.getTorCode():"");
        dataMap.put("applyDate",StringUtils.isNotEmpty(pcPartsApplyEO.getApplyDate())?pcPartsApplyEO.getApplyDate():"");
        dataMap.put("taskName",StringUtils.isNotEmpty(trialTaskEO.getTaskName())?trialTaskEO.getTaskName():"");
        dataMap.put("doOrg",StringUtils.isNotEmpty(trialTaskEO.getTrustorOrgName())?trialTaskEO.getTrustorOrgName():"");
        dataMap.put("itemsCode",StringUtils.isNotEmpty(trialTaskEO.getTaskCode())?trialTaskEO.getTaskCode():"");
        dataMap.put("taskCreateDate",StringUtils.isNotEmpty(trialTaskEO.getCreateTime())?trialTaskEO.getCreateTime():"");
        dataMap.put("finishDate",StringUtils.isNotEmpty(trialTaskEO.getPlanFinishTime())?trialTaskEO.getPlanFinishTime():"");
        List<DicTypeEO> turgency = dicTypeEOService.getDicTypeByDictionaryCode("Turgency");
        String urgency = trialTaskEO.getUrgency();
        for (DicTypeEO dicTypeEO : turgency){
            if (urgency.trim().equals(dicTypeEO.getDicTypeCode().trim())){
                urgency = dicTypeEO.getDicTypeName();
            }
        }
        dataMap.put("urgency",StringUtils.isNotEmpty(urgency)?urgency:"");
        //样品类型
        dataMap.put("sampleType","");
        dataMap.put("according",StringUtils.isNotEmpty(trialTaskEO.getAccording())?trialTaskEO.getAccording():"");
        dataMap.put("taskPurpose",StringUtils.isNotEmpty(trialTaskEO.getTaskPurpose())?trialTaskEO.getTaskPurpose():"");
        dataMap.put("requirement",StringUtils.isNotEmpty(trialTaskEO.getTaskRequirement())?trialTaskEO.getTaskRequirement():"");
        dataMap.put("useFor",StringUtils.isNotEmpty(pcPartsApplyEO.getUseFor())?pcPartsApplyEO.getUseFor():"");
        dataMap.put("sendUserName",StringUtils.isNotEmpty(pcPartsApplyEO.getSendUserName())?pcPartsApplyEO.getSendUserName():"");//
        dataMap.put("manager","");
        dataMap.put("receiveUserName",StringUtils.isNotEmpty(pcPartsApplyEO.getReceiveUserName())?pcPartsApplyEO.getReceiveUserName():"");
        dataMap.put("applyUserName",StringUtils.isNotEmpty(pcPartsApplyEO.getApplyUserName())?pcPartsApplyEO.getApplyUserName():"");
        //流程审批结束，获取审批人员
        String actProcId = pcOutsourceEntrustEOService.findActProcId(pcPartsApplyEO.getId());
        if(StringUtils.isNotEmpty(actProcId) && baseBusEODao.isFinishied(actProcId) == 1){
            List<ActivitiTaskVO> list = activitiTaskService.getProcessRecords(actProcId, "");
            dataMap.put("manager",list.get(0).getAssigneeName());
        }
        String fileName = "领料单-"+pcPartsApplyEO.getPickingCode();
        String fileExtend = ConstantUtils.SPOT + ConstantUtils.FILE_WORD_DOC;
        //填充数据 并导出
        docUtil.createDoc(dataMap, "PcPartsApply", response, request,
                fileName, fileExtend);
        return true;
    }

    /**
     * 编辑
     * @param pcPartsApplyEO
     * @return
     */
    public int updateByPrimaryKeySelective(PcPartsApplyEO pcPartsApplyEO){
        //删除子表，重新添加
        pcPartsApplySampleEODao.delByApplyId(pcPartsApplyEO.getId());
        //新增子表
        addPartSamples(pcPartsApplyEO);
        //更新
        return dao.updateByPrimaryKeySelective(pcPartsApplyEO);
    }

    /**
     * 新增
     * @param pcPartsApplyEO
     * @return
     */
    public int insertSelective(PcPartsApplyEO pcPartsApplyEO){
        //验证编码是否被占用
        String pickingCode = queryPartsCode();
        if(!StringUtils.endsWith(pcPartsApplyEO.getPickingCode(), pickingCode)){
            pcPartsApplyEO.setPickingCode(pickingCode);
        }
        //回填编码
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_PARTS_APPLY);
        pipelineNumberEO.setTally(pipelineNumberEO.getTally()+1);
        pipelineNumberEODao.updateByPrimaryKeySelective(pipelineNumberEO);
        //新增领料单
        dao.insertSelective(pcPartsApplyEO);
        //新增子表
        addPartSamples(pcPartsApplyEO);
        return 1;
    }

    /**
     * 新增子表数据
     * @param pcPartsApplyEO
     */
    public void addPartSamples(PcPartsApplyEO pcPartsApplyEO){
        List<PcPartsApplySampleEO> pcPartsApplySampleEOList = pcPartsApplyEO.getPcPartsApplySampleEOList();
        for(PcPartsApplySampleEO pcPartsApplySampleEO : pcPartsApplySampleEOList){
            pcPartsApplySampleEO.setDelFlag("0");
            pcPartsApplySampleEO.setPartApplyId(pcPartsApplyEO.getId());
            pcPartsApplySampleEODao.insertSelective(pcPartsApplySampleEO);
        }
    }

    /**
     * 启动流程
     * @param pcPartsApplyEO
     * @throws Exception
     */
    public String startProcess(PcPartsApplyEO pcPartsApplyEO) throws Exception {
        //将流程状态设置为审批中
        pcPartsApplyEO.setStatus("1");
        //判断是新增提交还是编辑提交
        if(StringUtils.isEmpty(pcPartsApplyEO.getId())) {
            insertSelective(pcPartsApplyEO);
        }else{
            updateByPrimaryKeySelective(pcPartsApplyEO);
        }
        String pvOrcv = "";
        //修改为根据发起人组织机构决定pv/cv流程
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        if(StringUtils.isNotEmpty(flag)){
            if ("1".equals(flag)){
                pvOrcv = ConstantUtils.PV_PARTS_APPLY;
            }else if("0".equals(flag)){
                pvOrcv = ConstantUtils.CV_PARTS_APPLY;
            }
        }
        //0-试验任务；1-执行计划
  		String taskOrPlan = "";
  		if("1".equals(pcPartsApplyEO.getTaskOrPlan())) {
  			taskOrPlan = "执行计划-";
  		}else {
  			taskOrPlan = "试验任务-";
  		}
        return actProcessService.startProcess(pcPartsApplyEO.getId(),pvOrcv, taskOrPlan + "领料单审批流程");
    }


    /**
     * 审批流程
     * @param requestEO
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
    public boolean isFinishied(String procId, String id) {
        if (baseBusEODao.isFinishied(procId) == 1) {
            if (com.adc.da.util.utils.StringUtils.isNotEmpty(id)) {
                //已审批
                dao.changeStatus(id, "5");
            }
            return true;
        }
        return false;
    }

    /**
     * 获取领料单编号
     * @return
     */
    public String queryPartsCode(){
        return primaryGenerater.queryLLDCode(ConstantUtils.PC_PARTS_APPLY_CODE, ConstantUtils.PC_PARTS_APPLY);
    }

    /**
     * 根据领料单编号查询领料信息
     * @param applyNo
     * @return
     */
    public List<PcPartsApplyEO> queryByApplyNo(String applyNo){
        return dao.queryByApplyNo(applyNo);
    }

}
