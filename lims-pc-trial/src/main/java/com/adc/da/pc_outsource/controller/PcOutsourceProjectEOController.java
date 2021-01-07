package com.adc.da.pc_outsource.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_outsource.VO.PCOutsourceSupProVO;
import com.adc.da.pc_outsource.VO.PcOutsourceProjectVO;
import com.adc.da.pc_outsource.common.PcOutsourceEnum;
import com.adc.da.pc_outsource.entity.PCOutsourceSupProEO;
import com.adc.da.pc_outsource.entity.PcOutsourceProjectEO;
import com.adc.da.pc_outsource.page.PcOutsourceProjectEOPage;
import com.adc.da.pc_outsource.service.PcOutsourceProjectEOService;
import com.adc.da.pc_trust.entity.TrialTaskEO;
import com.adc.da.pc_trust.service.TrialTaskEOService;

import com.adc.da.sys.annotation.EnableAccess;import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/pc_outsource/pcOutsourceProject")
@Api(tags = "PC-委外立项申请")
public class PcOutsourceProjectEOController extends BaseController<PcOutsourceProjectEO> {

    private static final Logger logger = LoggerFactory.getLogger(PcOutsourceProjectEOController.class);

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private PcOutsourceProjectEOService pcOutsourceProjectEOService;

    @Autowired
    private TrialTaskEOService trialTaskEOService;

    @Value("${file.path}")
    private String filepath;
    
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private PVCVService PVCVService;
    
    @ApiOperation(value = "|PcOutsourceProjectEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_outsource:pcOutsourceProject:page")
    public ResponseMessage<PageInfo<PcOutsourceProjectEO>> page(PcOutsourceProjectEOPage page) throws Exception {
        if (StringUtils.isNotBlank(page.getSearchPhrase())) {
            List<String> list = TransformUtil.settingSearchPhrase(page.getSearchPhrase());
            page.setKeyWords(list);
        }
        List<PcOutsourceProjectEO> rows = pcOutsourceProjectEOService.query(page);
        /*if (rows.size() != 0){
            for (PcOutsourceProjectEO pcOutsourceProjectEO : rows){
                pcOutsourceProjectEO.setInsId(pcOutsourceProjectEO.getInsId().split(",")[0]);
                pcOutsourceProjectEO.setInsProject(pcOutsourceProjectEO.getInsProject().split(",")[0]);
                pcOutsourceProjectEO.setInsType(pcOutsourceProjectEO.getInsType().split(",")[0]);
            }
        }*/

        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|PcOutsourceProjectVO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_outsource:pcOutsourceProject:get")
    public ResponseMessage<PcOutsourceProjectVO> find(@PathVariable String id) {
        PcOutsourceProjectEO pcOutsourceProjectEO = pcOutsourceProjectEOService.find(id);
        PcOutsourceProjectVO pcOutsourceProjectVO = new PcOutsourceProjectVO();
        BeanUtils.copyProperties(pcOutsourceProjectEO,pcOutsourceProjectVO);
        //获取所有的供应商数据
        List<PCOutsourceSupProEO> supProjectVOList = pcOutsourceProjectEOService.findSupProject(id);
        //通过map分组
        Map<String, List<PCOutsourceSupProEO>> map = new HashMap<String, List<PCOutsourceSupProEO>>();
        for (PCOutsourceSupProEO eo : supProjectVOList) {
        	List<PCOutsourceSupProEO> list = map.get(eo.getGroupFlag());
        	if(CollectionUtils.isEmpty(list)) {
        		List<PCOutsourceSupProEO> newList = new ArrayList<>();
        		newList.add(eo);
        		map.put(eo.getGroupFlag(), newList);
        	}else {
        		list.add(eo);
        		map.put(eo.getGroupFlag(), list);
        	}
		}
        //封装返回
        List<List<PCOutsourceSupProEO>> returnList = new ArrayList<List<PCOutsourceSupProEO>>();
        for(List<PCOutsourceSupProEO> value : map.values()){
        	returnList.add(value);
        }
        pcOutsourceProjectVO.setSupProjectVOList(returnList);
        return Result.success(pcOutsourceProjectVO);
    }

    
    @ApiOperation(value = "|PcOutsourceProjectVO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_outsource:pcOutsourceProject:save")
    public ResponseMessage<PcOutsourceProjectVO> create(@RequestBody PcOutsourceProjectVO pcOutsourceProjectVO) throws Exception {
        if (StringUtils.isBlank(pcOutsourceProjectVO.getTrialId())) {
            return Result.success("-1", "trialId不能为空");
        }
        String trialId = pcOutsourceProjectVO.getTrialId();
        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialId);
        String taskNumber = trialTaskEO.getTaskNumber();
        //设置试验申请对应taskNumber方便试验申请变更后的委外立项查询
        pcOutsourceProjectVO.setTrialCode(taskNumber);
        pcOutsourceProjectVO.setId(UUID.randomUUID10());
        String userId = UserUtils.getUserId();
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        pcOutsourceProjectVO.setId(UUID.randomUUID10());
        pcOutsourceProjectVO.setCreateBy(userId);
        pcOutsourceProjectVO.setCreateTime(date);
        pcOutsourceProjectVO.setUpdateBy(userId);
        pcOutsourceProjectVO.setUpdateTime(date);
        pcOutsourceProjectVO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        pcOutsourceProjectVO.setStatus(PcOutsourceEnum.DRAFT.getValue());
        pcOutsourceProjectVO.setPvOrCv(PcOutsourceEnum.DRAFT.getValue());
        pcOutsourceProjectVO.setCode(pcOutsourceProjectEOService.getCode("DFLQOUTSOURCE-"));
        PcOutsourceProjectEO pcOutsourceProjectEO = new PcOutsourceProjectEO();
        BeanUtils.copyProperties(pcOutsourceProjectVO,pcOutsourceProjectEO);
        //关联保存供应商检验项目
        if(CollectionUtils.isNotEmpty(pcOutsourceProjectVO.getSupProjectVOList())) {
        	pcOutsourceProjectEOService.saveSupProject(
        			pcOutsourceProjectVO.getId(), pcOutsourceProjectVO.getSupProjectVOList());
        }
        
        pcOutsourceProjectEOService.insertSelective(pcOutsourceProjectEO);
        return Result.success("0", "新增成功", pcOutsourceProjectVO);
    }

    @ApiOperation(value = "|PcOutsourceProjectVO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_outsource:pcOutsourceProject:update")
    public ResponseMessage<PcOutsourceProjectVO> update(@RequestBody PcOutsourceProjectVO pcOutsourceProjectVO) throws Exception {
        PcOutsourceProjectEO pcOutsourceProjectEO = new PcOutsourceProjectEO();
        BeanUtils.copyProperties(pcOutsourceProjectVO,pcOutsourceProjectEO);
        //set其他的值
        pcOutsourceProjectEO.setUpdateBy(UserUtils.getUserId());
        pcOutsourceProjectEO.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //关联保存供应商检验项目
        if(CollectionUtils.isNotEmpty(pcOutsourceProjectVO.getSupProjectVOList())) {
        	pcOutsourceProjectEOService.saveSupProject(
        			pcOutsourceProjectVO.getId(), pcOutsourceProjectVO.getSupProjectVOList());
        }
        
        pcOutsourceProjectEOService.updateByPrimaryKeySelective(pcOutsourceProjectEO);
        return Result.success("0", "修改成功", pcOutsourceProjectVO);
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_outsource:pcOutsourceProject:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcOutsourceProjectEOService.deleteByPrimaryKey(id);
        pcOutsourceProjectEOService.deleteByOutSourceId(id);
        logger.info("delete from PC_OUTSOURCE_PROJECT where id = {}", id);
        return Result.success("0", "删除成功");
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|导出")
    @GetMapping("/exportFile")
    @RequiresPermissions("pc_outsource:pcOutsourceProject:exportFile")
    public void exportFile(HttpServletResponse response, HttpServletRequest request,
                           @ApiParam(value = "id", required = true) @RequestParam("id") String id
    ) {
        pcOutsourceProjectEOService.exportFile(request, response, id);
    }

    @ApiOperation(value = "|PcOutsourceProjectVO|启动流程")
    @PostMapping("/activity")
    @RequiresPermissions("pc_outsource:pcOutsourceProject:activity")
    public ResponseMessage activity(@RequestBody PcOutsourceProjectVO pcOutsourceProjectVO) throws Exception {
        PcOutsourceProjectEO pcOutsourceProjectEO = new PcOutsourceProjectEO();
//        String trialId = pcOutsourceProjectVO.getTrialId();
//        TrialTaskEO trialTaskEO = trialTaskEOService.selectByPrimaryKey(trialId);
//        String taskNumber = trialTaskEO.getTaskNumber();
        //设置试验申请对应taskNumber方便试验申请变更后的委外立项查询
        //pcOutsourceProjectVO.setTrialCode(pcOutsourceProjectVO.getTrialCode());
       //判断下一节点是否有审批人
        JSONObject jsonObj = PVCVService.getStartNextNodeInfo(ConstantUtils.PC_OUTSOURCE_PROJECT_TYPE, 
        		ConstantUtils.CV_OUTSOURCE_PROJECT_TYPE);
        if("-1".equals(jsonObj.getString("isSuccess"))) {
        	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
        }
        BeanUtils.copyProperties(pcOutsourceProjectVO,pcOutsourceProjectEO);
        pcOutsourceProjectEOService.submitActivity(pcOutsourceProjectEO, jsonObj.getString("flag"));
        //关联保存供应商检验项目
        if(CollectionUtils.isNotEmpty(pcOutsourceProjectVO.getSupProjectVOList())) {
        	pcOutsourceProjectEOService.saveSupProject(
        			pcOutsourceProjectEO.getId(), pcOutsourceProjectVO.getSupProjectVOList());
        }
        return Result.success("0", "流程启动成功");
    }

    @ApiOperation(value = "|PcOutsourceProjectEO|流程审批")
    @PostMapping("/approval")
    @RequiresPermissions("pc_outsource:pcOutsourceProject:approval")
    @EnableAccess
    public ResponseMessage approval(HttpServletRequest request, @RequestBody RequestEO requestEO) throws Exception {
            //校验是否为空
            ResponseMessage x = TransformUtil.verify(requestEO, "baseBusId",  "variables");
            if (x != null) return x;
            //判断下一节点是否有审批人
            String str = PVCVService.getNextNodeInfo(requestEO);
            if("-1".equals(str)) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            Map variables = requestEO.getVariables();
            String approveCode = (String) variables.get("approve");
            pcOutsourceProjectEOService.approvalActivity(request, requestEO);
            if("reback".equals(approveCode)){
                return Result.success("0", "撤回成功");
            }else{
                return Result.success("0", "流程审批成功");
            }
    }

    @ApiOperation(value = "|委外立项申请-pageoffice在线打开文档")
    @BusinessLog(description = "委外立项申请-pageoffice在线打开文档")
    @GetMapping(path = "/openAttachmentFile")
    public ModelAndView openAttachmentFile(@ApiParam(value = "附件ID", required = true) @RequestParam("fileId") String fileId,
                                           Map<String,Object> map,HttpServletRequest request) throws Exception{
        TsAttachmentEO attachmentEO = tsAttachmentEODao.selectByPrimaryKey(fileId);
        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
        poCtrl.setServerPage("/poserver.zz");//设置服务页面
        poCtrl.webOpen("file://" + filepath + attachmentEO.getSavePath(),
                OpenModeType.docReadOnly, "");
        map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
        ModelAndView mv = new ModelAndView("EVWord");
        return mv;
    }

    /**
     * 委外立项申请-供应商选择
     * @Title: choseSup
     * @param id
     * @return
     * @return ResponseMessage<List<PCOutsourceSupProVO>>
     * @author: ljy
     * @date: 2020年3月12日
     */
    @ApiOperation(value = "|委外立项申请-供应商选择")
    @BusinessLog(description = "委外立项申请-供应商选择")
    @GetMapping(path = "/choseSup/{id}")
    public ResponseMessage<List<PCOutsourceSupProVO>> choseSup(@PathVariable String id){
        try {
        	return Result.success("0", "查询委外立项申请的供应商成功!", 
        			beanMapper.mapList(pcOutsourceProjectEOService.choseSup(id), PCOutsourceSupProVO.class));
		} catch (Exception e) {
			return Result.error("-1", "查询委外立项申请的供应商失败!");
		}
    }
    
}
