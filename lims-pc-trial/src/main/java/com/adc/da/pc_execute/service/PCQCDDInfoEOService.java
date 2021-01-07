package com.adc.da.pc_execute.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.rest.service.api.engine.variable.RestVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.acttask.service.LimsFileService;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PrimaryGenerater;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.dao.PCQCDDInfoEODao;
import com.adc.da.pc_execute.dao.PCQCDDSupEODao;
import com.adc.da.pc_execute.entity.PCQCDDInfoEO;
import com.adc.da.pc_execute.entity.PCQCDDSupEO;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiProcessService;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiProcessInstanceVO;
import com.adc.da.workflow.vo.ActivitiTaskRequestVO;
import com.adc.da.workflow.vo.ProcessInstanceCreateRequestVO;



@Service("PCQCDDInfoEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PCQCDDInfoEOService extends BaseService<PCQCDDInfoEO, String> {
	@Autowired
	private PCQCDDInfoEODao pcqcddInfoEODao;
	
	public PCQCDDInfoEODao getDao() {
		return pcqcddInfoEODao;
	}
	
	
    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;
    
    @Autowired
    private HistoryService historyService;
    
    @Autowired
    private PCQCDDSupEODao pcqcddSupEODao;
    
    @Autowired
    private LimsFileService limsFileService;

    @Autowired
    private ActivitiProcessService activitiProcessService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private UserEODao userEODao;
    
    @Autowired
    private DicTypeEODao dicTypeEODao;
    
    /**
     * PV/CV-QCDD数据保存
     * @Title: save
     * @param pcqcddInfoVO
     * @return
     * @return ResponseMessage<PCQCDDInfoVO>
     * @author: ljy
     * @date: 2020年6月4日
     */
	public PCQCDDInfoEO save(PCQCDDInfoEO eo) {
		eo.setId(UUID.randomUUID());
		//设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        eo.setCreateTime(date);
        eo.setCreateBy(UserUtils.getUserId());
        eo.setUpdateBy(UserUtils.getUserId());
        eo.setUpdateTime(date);
        //删除标记 0 未删除;  1 删除
        eo.setDelFlag("0");
        //eo.setQcddNo(getQcddCode());
        //0-草稿  1-审批中   2-已审批   3-退回  
        eo.setQcddStatus("0");
        pcqcddInfoEODao.insert(eo);
        //保存供应商信息
        if(CollectionUtils.isNotEmpty(eo.getSupList())) {
        	saveQCDDSup(eo.getSupList(), eo.getId());
        }
        return eo;
	}
	
	
	/**
	 * 保存供应商id
	 * @Title: saveQCDDSup
	 * @param list
	 * @param qcddId
	 * @return void
	 * @author: ljy
	 * @date: 2020年6月8日
	 */
	public void saveQCDDSup(List<PCQCDDSupEO> list, String qcddId) {
		for (int i = 0; i < (list.size()); i++) {
			PCQCDDSupEO sup = new PCQCDDSupEO();
			sup.setId(UUID.randomUUID());
			//成本
			sup.setCost(list.get(i).getCost());
			sup.setDelFlag("0");
			//交付
			sup.setDeliver(list.get(i).getDeliver());
			//研发
			sup.setDevelopment(list.get(i).getDevelopment());
			//QCDD主键
			sup.setQcddId(qcddId);
			//质量
			sup.setQuality(list.get(i).getQuality());
			//供应商id
			sup.setSupId(list.get(i).getSupId());
			//供应商名字
			sup.setSupName(list.get(i).getSupName());
			pcqcddSupEODao.insert(sup);
		}
	}
	
	
	
    /**
     * PV/CV-QCDD获取编号
     * @Title: getQcddCode
     * @return
     * @return ResponseMessage<String>
     * @author: ljy
     * @date: 2020年6月4日
     */
    public String getQcddCode() {
        String qcddCode = "";
        //获取当前年份
        String dateYear = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT3);
        //获取流水号相关信息
        PipelineNumberEO pNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.PC_QCDD_NO);
        //如果数据库没有改类型code,则新增一个
        if (StringUtils.isEmpty(pNumberEO)) {
            pNumberEO = new PipelineNumberEO();
            pNumberEO.setType(ConstantUtils.PC_QCDD_NO);
            pNumberEO.setTally(0);
            pNumberEO.setParticularYear(dateYear);
            pipelineNumberEODao.insert(pNumberEO);
        }
        //判断数据取出来的最后一个业务单号是不是当年的,如果是当年的 直接+1
        if (dateYear.equals(pNumberEO.getParticularYear())) {
            //获取流水号
        	qcddCode = PrimaryGenerater.getPCQCDDNo(
                    pNumberEO.getTally(), pNumberEO.getParticularYear());
            //更新计数
            pNumberEO.setTally(pNumberEO.getTally() + 1);
        } else { //不是当年的,从0开始
            //获取流水号
        	qcddCode = PrimaryGenerater.getPCQCDDNo(0, dateYear);
            //更新计数及新的年份
            pNumberEO.setTally(1);
            pNumberEO.setParticularYear(dateYear);
        }
        //更新数据库
        pipelineNumberEODao.updateByPrimaryKeySelective(pNumberEO);
        return qcddCode;
    }
	
    
    /**
     * PV/CV-QCDD数据编辑
     * @Title: edit
     * @param eo
     * @return
     * @return PCQCDDInfoEO
     * @author: ljy
     * @date: 2020年6月4日
     */
	public PCQCDDInfoEO edit(PCQCDDInfoEO eo) {
		//设置时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        eo.setUpdateBy(UserUtils.getUserId());
        eo.setUpdateTime(date);
        pcqcddInfoEODao.updateByPrimaryKeySelective(eo);
        //先删除
        pcqcddSupEODao.deleteByQcddId(eo.getId());
        //保存供应商信息
        if(CollectionUtils.isNotEmpty(eo.getSupList())) {
        	saveQCDDSup(eo.getSupList(), eo.getId());
        }
        return eo;
	}
    
	
	/**
	 * PV/CV-QCDD数据删除
	 * @Title: deleteById
	 * @param id
	 * @return void
	 * @author: ljy
	 * @date: 2020年6月4日
	 */
	public void deleteById(String id) {
		//删除子表格数据
		pcqcddSupEODao.deleteByQcddId(id);
        //业务删除
        pcqcddInfoEODao.deleteByPrimaryKey(id);
	}
    
	
	/**
	 * QCDD流程启动	
	 * @Title: submitQCDD
	 * @param pcqcddInfoEO
	 * @param flag
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2020年6月8日
	 */
	public void submitQCDD(PCQCDDInfoEO pcqcddInfoEO, String flag) throws Exception {
        //查询数据库是否有该数据
		PCQCDDInfoEO eo = pcqcddInfoEODao.selectByPrimaryKey(pcqcddInfoEO.getId());
        //存在即更新, 否则新增
        if (StringUtils.isNotEmpty(eo)) {
            edit(pcqcddInfoEO);
        } else {
            save(pcqcddInfoEO);
        }
        
        //0-试验任务；1-执行计划
  		String taskOrPlan = "";
  		if("1".equals(pcqcddInfoEO.getTaskOrPlan())) {
  			taskOrPlan = "执行计划-";
  		}else {
  			taskOrPlan = "试验任务-";
  		}
        //启动流程
        //保存业务BASEBUS
        String title = taskOrPlan + "QCDD单【" + pcqcddInfoEO.getQcddNo() + "】审批流程";
        String pvorcv = "";
        if ("1".equals(flag)) {
            pvorcv = ConstantUtils.PV_QCDD_TYPE;
        } else {
            pvorcv = ConstantUtils.CV_QCDD_TYPE;
        }
        String baseBusId = limsFileService.saveBaseBus(pcqcddInfoEO.getId(), pvorcv, title);
        //流程实例保存
        ProcessInstanceCreateRequestVO processInstanceVO = new ProcessInstanceCreateRequestVO();
        processInstanceVO.setInitiator(UserUtils.getUserId());
        processInstanceVO.setBusinessKey(baseBusId);
        
        //设置组织机构
        List<RestVariable> variables = new ArrayList<>();
        String orgId = userEODao.getOrgIdByUserId(UserUtils.getUserId());
        RestVariable rv = new RestVariable();
    	rv.setName("specialOrgId");
    	rv.setValue(orgId);
    	variables.add(rv);
    	processInstanceVO.setVariables(variables);
        
        //根据数据字典获取流程定义id
        DicTypeEO dicTypeEO = dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(
                ConstantUtils.PROCESS_CODE, pvorcv);
        processInstanceVO.setProcessDefinitionKey(dicTypeEO.getDicTypeName());
        //启动流程
        ActivitiProcessInstanceVO activityVO = activitiProcessService.startProcess(processInstanceVO);
        //修改qcdd单状态为审批中  1-审批中
        pcqcddInfoEO.setQcddStatus("1");
        pcqcddInfoEODao.updateByPrimaryKeySelective(pcqcddInfoEO);
        //根据流程实例ID,获取流程当前所有办理人
        String ids = baseBusEODao.fingNextUserId(activityVO.getProcessInstanceId());
        //获取流程当前所有办理人, 给待办人发送消息/邮件/工作日历
        if (StringUtils.isNotEmpty(ids)) {
            String[] splits = ids.split(ConstantUtils.COMMA);
            Set<String> userArray = new HashSet<String>(Arrays.asList(splits));
            //循环设置当前待办人的id和name
            List<String> userNames = new ArrayList<>();
            for (String usid : userArray) {
                UserEO user = userEODao.selectByPrimaryKey(usid);
                if (StringUtils.isNotEmpty(user.getUsname())) {
                    userNames.add(user.getUsname());
                }
            }
            //链接到QCDD单页
            String link = pvorcv;
            limsFileService.processSendMessages(splits, title, link, pcqcddInfoEO.getId());
        }

    }
	
	
	
	
	
	/**
	 * QCDD流程审批
	 * @Title: approvalProcess
	 * @param request
	 * @param requestEO
	 * @throws Exception
	 * @return void
	 * @author: ljy
	 * @date: 2020年6月8日
	 */
    public void approvalProcess(HttpServletRequest request, RequestEO requestEO) throws Exception {
        //获取业务与流程关联表信息
        BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
        //获取QCDD业务信息
        PCQCDDInfoEO eo = pcqcddInfoEODao.selectByPrimaryKey(baseBusEO.getBusinessId());
        //获取审批按钮值,用于判断用户操作
        Map variables = requestEO.getVariables();
        String approveCode = (String) variables.get("approve");
        //退回
        if ("rollback".equals(approveCode)) {
            //3-退回
        	eo.setQcddStatus("3");
        }
        //撤回
        else if ("reback".equals(approveCode)) {
            //4-撤回
        	eo.setQcddStatus("4");
            //审批意见与退回保持一致，为了流程顺利走下去
            requestEO.getVariables().put("approve", "rollback");
        } 
        else {
            //修改QCDD为审批中  1-审批中
        	eo.setQcddStatus("1");
        }
        pcqcddInfoEODao.updateByPrimaryKeySelective(eo);
        //流程任务实例
        ActivitiTaskRequestVO activitiTaskRequestVO = new ActivitiTaskRequestVO();
        //审批意见
        activitiTaskRequestVO.setComment(requestEO.getComment());
        //审批任务id
        activitiTaskRequestVO.setTaskId(requestEO.getTaskId());
        //审批code及其他信息
        activitiTaskRequestVO.setVariables(requestEO.getVariables());
        //审批人
        activitiTaskRequestVO.setAssignee(UserUtils.getUserId());
        //这个字段必须传值，不然审批会空指针（后面代码会对它的内容转义）
        activitiTaskRequestVO.setFormContent("");
        //任务
        if ("0".equals(requestEO.getType())) {
            activitiTaskService.completeTask(activitiTaskRequestVO, request);
            //候选任务
        } else if ("1".equals(requestEO.getType())) {
            if (!"1".equals(requestEO.getType())) {
                throw new AdcDaBaseException("任务类型不正确，请联系系统管理员！");
            }
            activitiTaskService.completeCandidateTask(activitiTaskRequestVO);
        }
        //0-试验任务；1-执行计划
  		String taskOrPlan = "";
  		if("1".equals(eo.getTaskOrPlan())) {
  			taskOrPlan = "执行计划-";
  		}else {
  			taskOrPlan = "试验任务-";
  		}
        //消息链接
        String link = baseBusEO.getBusinessType();
        //判断流程是否结束，结束的话，变更业务状态
        if (baseBusEODao.isFinishied(requestEO.getProcId()) == 1) {
            if (StringUtils.isNotEmpty(baseBusEO) && StringUtils.isNotEmpty(eo)) {
                //已审批  2-已审批
            	eo.setQcddStatus("2");
            	pcqcddInfoEODao.updateByPrimaryKeySelective(eo);
                //完成给相关人员发送消息
                String title = taskOrPlan + "QCDD单【" + eo.getQcddNo() + "】审批流程完成";
                limsFileService.processSendMessages(limsFileService.
                                getProcessUsersByTaskId(requestEO.getTaskId()),
                        title, link, eo.getId());
            }
        } else {
            //获取试验任务业务信息
            String title = taskOrPlan + "QCDD单【" + eo.getQcddNo() + "】审批流程";
            //获取下一节点人
            if (StringUtils.isNotEmpty(baseBusEODao.fingNextUserId(requestEO.getProcId()))) {
                String[] ids = baseBusEODao.fingNextUserId(requestEO.getProcId()).split(ConstantUtils.COMMA);
                //发送消息通知、工作日历、邮件
                limsFileService.processSendMessages(ids, title, link, eo.getId());
            }
        }
    }
	
	
	
	
	
}
