package com.adc.da.pc_execute.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_execute.dao.PCTrialExecuteDao;
import com.adc.da.pc_execute.vo.PCTrialExecuteListVO;
import com.adc.da.pc_person.entity.TrialPersonEO;
import com.adc.da.pc_person.service.TrialPersonEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;
import com.adc.da.pc_trust.vo.PCTrialTaskVO;
import com.adc.da.trial_report.dao.TrialReportProcessEODao;
import com.adc.da.trial_report.vo.TrialScfeduleVO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;


@Service
@Transactional(value = "transactionManager", readOnly = false,
        rollbackFor = Throwable.class)
public class PCTrialExecuteService extends BaseService<TrialTaskEO, String>{
	
	@Autowired
	private PCTrialExecuteDao pcTrialExecuteDao;
	
	public PCTrialExecuteDao getDao() {
		return pcTrialExecuteDao;
	}
	
    @Autowired
    private TrialTaskEOService trialTaskEOService;

    @Autowired
    private TrialPersonEOService trialPersonEOService;
    
    @Autowired
    private TrialReportProcessEODao trialreportProcessEODao;
	
    /**
     * 试验人员管理-修改
     * @Title: personManage
     * @param pcTrialTaskVO
     * @return void
     * @author: ljy
     * @date: 2019年10月28日
     */
    public void updatePersonManage(PCTrialTaskVO pcTrialTaskVO) throws Exception {
    	//删除人员子表，重新添加
    	trialPersonEOService.batchDelete(pcTrialTaskVO.getId());
        //批量添加人员
        if(CollectionUtils.isNotEmpty(pcTrialTaskVO.getTrialPersonEOList())){
            trialPersonEOService.batchSave(pcTrialTaskVO.getTrialPersonEOList(), pcTrialTaskVO.getId());
        }
        //反向更新主表
        trialTaskEOService.updatePersons(pcTrialTaskVO.getId());
    }
    
    
    /**
     * 试验人员管理-查询
     * @Title: getPersonManage
     * @param id
     * @return
     * @return PCTrialTaskVO
     * @author: ljy
     * @throws Exception 
     * @date: 2019年10月28日
     */
    public PCTrialTaskVO getPersonManage(String id) throws Exception {
    	PCTrialTaskVO vo = new PCTrialTaskVO();
    	//根据试验任务书id 查询人员列表
    	TrialTaskEO eo = trialTaskEOService.selectByPrimaryKey(id);
    	if(StringUtils.isNotEmpty(eo)) {
    		vo.setId(eo.getId());
    		vo.setTrialPersonEOList(eo.getTrialPersonEOList());
    	}
    	return vo;
    }
	
    
    /**
     * PC试验执行-激活的试验人员查询
     * @Title: getPersonList
     * @param id
     * @return
     * @throws Exception
     * @return List<TrialPersonEO>
     * @author: ljy
     * @date: 2020年5月26日
     */
    public List<TrialPersonEO> getPersonList(String id) throws Exception {
    	//设置返回值
    	List<TrialPersonEO> personList = new ArrayList<>();
    	//根据试验任务书id 查询人员列表
    	TrialTaskEO eo = trialTaskEOService.selectByPrimaryKey(id);
    	if(StringUtils.isNotEmpty(eo) && 
    			CollectionUtils.isNotEmpty(eo.getTrialPersonEOList())) {
    		for (TrialPersonEO person : eo.getTrialPersonEOList()) {
    			//筛选激活人员
				if("0".equals(person.getPersonStatus())) {
					personList.add(person);
				}
			}
    	}
    	return personList;
    }
    
    
    
    /**
     * PC试验执行 点击"+"查询
     * @Title: itemsList
     * @param id
     * @return
     * @return ResponseMessage<List<PCTrialExecuteListVO>>
     * @author: ljy
     * @date: 2019年10月31日
     */
    public List<PCTrialExecuteListVO> selectListById(String id){
    	List<PCTrialExecuteListVO> trialExecuteList = pcTrialExecuteDao.selectListById(id);
		//关联查询试验进度 --TODO
    	if (trialExecuteList != null) {
            for (PCTrialExecuteListVO vo : trialExecuteList) {
                // 关联查询试验进度
                List<TrialScfeduleVO> scfeduleVO = trialreportProcessEODao.selectScheduleList(vo.getId());
                if (CollectionUtils.isNotEmpty(scfeduleVO)) {
                    // 试验进度
                    vo.setTrialprojectRate(scfeduleVO.get(0).getTrialprojectRate());
                }
            }
        }
    	return trialExecuteList;
	}
}
