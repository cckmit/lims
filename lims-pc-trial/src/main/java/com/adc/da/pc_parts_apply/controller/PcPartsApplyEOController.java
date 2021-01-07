package com.adc.da.pc_parts_apply.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.part.dao.SaPartDataEODAO;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_parts_apply.entity.PcPartsApplySampleEO;
import com.adc.da.pc_parts_apply.service.PcPartsApplySampleEOService;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;
import net.sf.json.JSONObject;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_parts_apply.entity.PcPartsApplyEO;
import com.adc.da.pc_parts_apply.page.PcPartsApplyEOPage;
import com.adc.da.pc_parts_apply.service.PcPartsApplyEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/pc_parts_apply/pcPartsApply")
@Api(tags = "|PcPartsApplyEO-领料单申请-主表|")
public class PcPartsApplyEOController extends BaseController<PcPartsApplyEO>{

    private static final Logger logger = LoggerFactory.getLogger(PcPartsApplyEOController.class);

    @Autowired
    private PcPartsApplyEOService pcPartsApplyEOService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private PcPartsApplySampleEOService pcPartsApplySampleEOService;

    @Autowired
    private SaPartDataEODAO saPartDataEODAO;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private PVCVService PVCVService;

    @Autowired
    private OrgEOService orgEOService;

    @BusinessLog(description = "pc领料单分页查询")
	@ApiOperation(value = "|PcPartsApplyEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:page")
    public ResponseMessage<PageInfo<PcPartsApplyEO>> page(PcPartsApplyEOPage page) throws Exception {
        List<PcPartsApplyEO> rows = pcPartsApplyEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @BusinessLog(description = "pc领料单列表查询")
	@ApiOperation(value = "|PcPartsApplyEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:list")
    public ResponseMessage<List<PcPartsApplyEO>> list(PcPartsApplyEOPage page) throws Exception {
        return Result.success(pcPartsApplyEOService.queryByList(page));
	}

    @BusinessLog(description = "pc领料单详情")
    @ApiOperation(value = "|PcPartsApplyEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:get")
    public ResponseMessage<PcPartsApplyEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcPartsApplyEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "pc领料单编号")
    @ApiOperation(value = "|PcPartsApplyEO|领料单编号")
    @GetMapping("/queryPartsCode")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:queryPartsCode")
    public ResponseMessage<String> queryPartsCode() throws Exception {
        return Result.success("0","操作成功！",pcPartsApplyEOService.queryPartsCode());
    }

    @BusinessLog(description = "pc领料单新增")
    @ApiOperation(value = "|PcPartsApplyEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_parts_apply:pcPartsApply:save")
    public ResponseMessage<PcPartsApplyEO> create(@RequestBody PcPartsApplyEO pcPartsApplyEO) throws Exception {
        //设置流程状态为草稿
        pcPartsApplyEO.setStatus("0");
	    pcPartsApplyEOService.insertSelective(pcPartsApplyEO);
        return Result.success("0","操作成功！",pcPartsApplyEO);
    }

    @BusinessLog(description = "pc领料单启动流程")
    @ApiOperation(value = "|PcPartsApplyEO|启动流程")
    @PostMapping("/startProcess")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:startProcess")
    public ResponseMessage<PcPartsApplyEO> startProcess (@RequestBody PcPartsApplyEO pcPartsApplyEO){
	    try {
            //判断下一节点是否有审批人
            String procDefKey = null;
            //修改为根据发起人组织机构决定pv/cv流程
            UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
            OrgEO curOrg = curUser.getOrgEOList().get(0);
            String flag = orgEOService.getByOrgId(curOrg.getId());
            if ("0".equals(flag)) {
                DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_PARTS_APPLY);
                procDefKey = dicTypeEO.getDicTypeName();
            } else {
                DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PV_PARTS_APPLY);
                procDefKey = dicTypeEO.getDicTypeName();
            }
            NextGroupUserVO nextNodeInfo = activitiTaskService.getNextNodeInfo(procDefKey, null, null);
            String roleId = nextNodeInfo.getRoleId();
            String departId = nextNodeInfo.getDepartId();
            if(StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(departId) && "1".equals(departId)) {
                List<Map<String, Object>> usersByRoleAndOrg = userEOService.getUsersByRoleAndOrg(roleId, curOrg.getId());
                if(usersByRoleAndOrg.isEmpty())
                    return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            pcPartsApplyEOService.startProcess(pcPartsApplyEO);
            return Result.success("0","操作成功！");
        }catch (Exception e){
	        logger.error("-1",e.getStackTrace());
	        return Result.error("-1","操作失败！");
        }
    }

    @BusinessLog(description = "pc领料单审批流程")
    @ApiOperation(value = "|PcPartsApplyEO|审批流程")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:approvalProcess")
    @EnableAccess
    public ResponseMessage<PcPartsApplyEO> approvalProcess (HttpServletRequest request, @RequestBody RequestEO requestEO){
        try {
            //判断下一节点是否有审批人
            String nextNodeInfo = PVCVService.getNextNodeInfo(requestEO);
            if ("-1".equals(nextNodeInfo)){
                return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            BaseBusEO baseBusEO = baseBusEODao.selectByPrimaryKey(requestEO.getBaseBusId());
            pcPartsApplyEOService.approvalProcess(request, requestEO, baseBusEO);
            boolean flag = pcPartsApplyEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
            if(flag){
                List<PcPartsApplySampleEO> pcPartsApplySampleEOList = pcPartsApplySampleEOService.queryByApplyId(baseBusEO.getBusinessId());
                for(PcPartsApplySampleEO sampleEO : pcPartsApplySampleEOList){
                    saPartDataEODAO.changeStatus(sampleEO.getPartId(), 1);
                }

            }
            return Result.success("0","操作成功！");
        }catch (Exception e){
            logger.error("-1",e.getStackTrace());
            return Result.error("-1","操作失败！");
        }
    }

    @BusinessLog(description = "pc领料单修改")
    @ApiOperation(value = "|PcPartsApplyEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_parts_apply:pcPartsApply:update")
    public ResponseMessage<PcPartsApplyEO> update(@RequestBody PcPartsApplyEO pcPartsApplyEO) throws Exception {
        pcPartsApplyEOService.updateByPrimaryKeySelective(pcPartsApplyEO);
        return Result.success("0","操作成功！",pcPartsApplyEO);
    }

    @BusinessLog(description = "pc领料单删除")
    @ApiOperation(value = "|PcPartsApplyEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_parts_apply:pcPartsApply:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcPartsApplyEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_PARTS_APPLY where id = {}", id);
        return Result.success("0","操作成功！");
    }

    @BusinessLog(description = "pc领料单导出")
    @ApiOperation(value = "|PcPartsApplyEO|导出")
    @GetMapping("/exportDoc")
//    @RequiresPermissions("pc_parts_apply:pcPartsApply:exportDoc")
    public ResponseMessage<String> exportDoc(String id, HttpServletResponse response, HttpServletRequest request){
        try {
            PcPartsApplyEO pcPartsApplyEO = pcPartsApplyEOService.selectByPrimaryKey(id);
            boolean flag = pcPartsApplyEOService.exportDoc(response, request, pcPartsApplyEO);
            if(flag){
                return Result.success("0","导出成功！");
            }else{
                return Result.error("-1","导出失败！");
            }
        }catch (Exception e){
            logger.error("-1",e.getStackTrace());
            return Result.error("-1","导出失败！");
        }
    }

}
