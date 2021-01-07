package com.adc.da.pc_trial_problem.service;

import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_trial_problem.entity.TpmUserEO;
import com.adc.da.pc_trial_problem.webServiceClient.ProblemService;
import com.adc.da.pc_trial_problem.webServiceClient.ProblemServiceService;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.utils.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_trial_problem.dao.PcTrialProblemEODao;
import com.adc.da.pc_trial_problem.entity.PcTrialProblemEO;
import com.adc.da.pc_trial_problem.entity.TpmOrgEO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <br>
 * <b>功能：</b>PC_TRIAL_PROBLEM PcTrialProblemEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-01-02 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcTrialProblemEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcTrialProblemEOService extends BaseService<PcTrialProblemEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcTrialProblemEOService.class);

    @Autowired
    private PcTrialProblemEODao dao;

    public PcTrialProblemEODao getDao() {
        return dao;
    }

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;


    /**
     * 将试验问题发送给新品协同系统
     * @param pcTrialProblemEO
     */
    public String putToTPM(PcTrialProblemEO pcTrialProblemEO) throws Exception {
        String dataStr = parseJsonProblem(pcTrialProblemEO);
        ProblemServiceService problemServiceService = new ProblemServiceService();
        ProblemService problemService = problemServiceService.getProblemService();
        System.out.println("---------------------------webservice----------------------------");
        System.out.println("传入值："+dataStr);
        String rs = problemService.startProblem(dataStr);
        System.out.println("传出值："+rs);
        rs = rs.substring(1, rs.length() - 1);
        Map maps = (Map)JSON.parse(rs);
        String flag = (String) maps.get("flag");
        if("-1".equals(flag)){
            return maps.get("msg").toString();
        }else{
            //回填试验问题编号
            pcTrialProblemEO.setProblemCode(maps.get("PROBLEM_CODE").toString());
        }
        //试验问题状态变为进行中
        pcTrialProblemEO.setStatus("1");
        dao.updateByPrimaryKeySelective(pcTrialProblemEO);
        return "";
    }

    /**
     * 拼接试验问题
     * @param pcTrialProblemEO
     * @return
     */
    public String parseJsonProblem(PcTrialProblemEO pcTrialProblemEO) throws Exception {
        Map<String,Object> dataMap = new HashMap();
        dataMap.put("PRO_PROPERTY",pcTrialProblemEO.getProProperty());
        dataMap.put("PRO_PROPERTY_TWO",pcTrialProblemEO.getProPropertyTwo());
        dataMap.put("EVALUATE_PERSON",pcTrialProblemEO.getEvaluatePerson());
        dataMap.put("TPP_CODE",pcTrialProblemEO.getTppCode());
        dataMap.put("PROBLEM_TYPE",pcTrialProblemEO.getProblemType());
        dataMap.put("EVALUATION_DATE",pcTrialProblemEO.getEvaluationDate());
        dataMap.put("ASK_TIME",pcTrialProblemEO.getAskTime());
        dataMap.put("PART_NO",
        		StringUtils.isEmpty(pcTrialProblemEO.getPartNo()) ? "" : pcTrialProblemEO.getPartNo());
        dataMap.put("PART_NAME",
        		StringUtils.isEmpty(pcTrialProblemEO.getPartName()) ? "" : pcTrialProblemEO.getPartName());
        dataMap.put("VIN_CODE",pcTrialProblemEO.getVinCode());
        dataMap.put("MODEL_NO",
        		StringUtils.isEmpty(pcTrialProblemEO.getModelNo()) ? "" : pcTrialProblemEO.getModelNo());
        dataMap.put("PROBLEM_LEVEL",pcTrialProblemEO.getProblemLevel());
        dataMap.put("MAIN_DEPT",pcTrialProblemEO.getMainDept());
        dataMap.put("MAIN_USER",pcTrialProblemEO.getMainUser());
        dataMap.put("PRO_TITLE",pcTrialProblemEO.getProTitle());
        dataMap.put("FAULT_MILEAGE",pcTrialProblemEO.getFaultMileage());
        dataMap.put("DISTINGUISH",pcTrialProblemEO.getDistinguish());
        dataMap.put("PSQC_HS",pcTrialProblemEO.getPsqcHs());
        dataMap.put("PSQC_HW",pcTrialProblemEO.getPsqcHw());
        dataMap.put("PSQC_HD",pcTrialProblemEO.getPsqcHd());
        dataMap.put("PSQC_ZML",pcTrialProblemEO.getPsqcZml());
        dataMap.put("PSQC_FSTJ",pcTrialProblemEO.getPsqcFstj());
        dataMap.put("PSQC_JCR",pcTrialProblemEO.getPsqcJcr());
        dataMap.put("PSQC_CBYY",pcTrialProblemEO.getPsqcCbyy());
        dataMap.put("PSQC_XSLC",pcTrialProblemEO.getPsqcXslc());
        dataMap.put("KKX_SYZLC",pcTrialProblemEO.getKkxSyzlc());
        dataMap.put("KKX_DQSYZZL",pcTrialProblemEO.getKkxDqsyzzl());
        dataMap.put("KKX_DQSYLK",pcTrialProblemEO.getKkxDqsylk());
        dataMap.put("KKX_CXGZLC",pcTrialProblemEO.getKkxCxgzlc());
        dataMap.put("KKX_GZMS",pcTrialProblemEO.getKkxGzms());
        dataMap.put("TESTPROBLEM_ID",pcTrialProblemEO.getId());
        dataMap.put("STARTUSER", UserUtils.getUser().getAccount());
        dataMap.put("PROCESSCOMMENTS",
        		StringUtils.isEmpty(pcTrialProblemEO.getProcesscomments()) ? "" : pcTrialProblemEO.getProcesscomments());
        dataMap.put("PRO_QXXPD", pcTrialProblemEO.getProQxxpd());
        dataMap.put("PRO_PIC_IMGURL","");
        dataMap.put("XCCLGZ_PERSON",
        		StringUtils.isEmpty(pcTrialProblemEO.getXcclgzPerson()) ? "" : pcTrialProblemEO.getXcclgzPerson());
        
        if(StringUtils.isNotEmpty(pcTrialProblemEO.getImageIds())) {
            dataMap.put("PRO_PIC_IMGURL",findImageUrls(pcTrialProblemEO.getImageIds()));
        }
        JSONObject jsonObj=new JSONObject(dataMap);
        return "["+jsonObj+"]";
    }

    /**
     * 拼接图片路径
     * @param imageIds
     * @return
     */
    public String findImageUrls(String imageIds){
        String imagePaths = "";
        String[] ids = imageIds.split(",");
        for(String imageId:ids){
            if(StringUtils.isNotEmpty(imageId)){
                TsAttachmentEO tsAttachmentEO = tsAttachmentEODao.selectByPrimaryKey(imageId);
                imagePaths += tsAttachmentEO.getSavePath()+",";
            }
        }
        return imagePaths.substring(0,imagePaths.length()-1);
    }

    /**
     * 人员查询
     * @param departName
     * @return
     */
    public List<TpmUserEO> findUserFromTpm(String departName){
        return dao.findUserFromTpm(departName);
    }

    
    /**
     * 新品人员查询
     * @return
     */
    public List<TpmUserEO> findUserFromTpmNew(){
        return dao.findUserFromTpmNew();
    }
    
    
    /**
     * 新品组织机构查询
     * @Title: findOrgFromTpm
     * @return
     * @return List<TpmOrgEO>
     */
    public List<TpmOrgEO> findOrgFromTpm(){
    	return dao.findOrgFromTpm();
    }
}
