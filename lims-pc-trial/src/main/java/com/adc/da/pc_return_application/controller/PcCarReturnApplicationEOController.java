package com.adc.da.pc_return_application.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;
import java.util.Map;

import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_execute.service.PVCVService;
import com.adc.da.pc_parts_apply.entity.PcPartsApplyEO;
import com.adc.da.pc_return_application.vo.PcCarReturnApplicationVO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.NextGroupUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_return_application.entity.PcCarReturnApplicationEO;
import com.adc.da.pc_return_application.page.PcCarReturnApplicationEOPage;
import com.adc.da.pc_return_application.service.PcCarReturnApplicationEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/${restPath}/pc_return_application/pcCarReturnApplication")
@Api(tags = "|PcCarReturnApplicationEO-还车申请|")
public class PcCarReturnApplicationEOController extends BaseController<PcCarReturnApplicationEO>{

    private static final Logger logger = LoggerFactory.getLogger(PcCarReturnApplicationEOController.class);

    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;

    @Autowired
    private PcCarReturnApplicationEOService pcCarReturnApplicationEOService;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ActivitiTaskService activitiTaskService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private com.adc.da.pc_execute.service.PVCVService PVCVService;

    @Autowired
    private OrgEOService orgEOService;


    @BusinessLog(description = "还车申请--分页查询")
	@ApiOperation(value = "|PcCarReturnApplicationEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:page")
    public ResponseMessage<PageInfo<PcCarReturnApplicationVO>> page(PcCarReturnApplicationEOPage page) throws Exception {
        List<PcCarReturnApplicationEO> rows = pcCarReturnApplicationEOService.queryByPage(page);
        return Result.success("0","查询成功！", beanMapper.mapPage(getPageInfo(page.getPager(), rows),PcCarReturnApplicationVO.class));
    }

    @BusinessLog(description = "还车申请--列表查询")
	@ApiOperation(value = "|PcCarReturnApplicationEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:list")
    public ResponseMessage<List<PcCarReturnApplicationEO>> list(PcCarReturnApplicationEOPage page) throws Exception {
        return Result.success(pcCarReturnApplicationEOService.queryByList(page));
	}

    @BusinessLog(description = "还车申请--详情")
    @ApiOperation(value = "|PcCarReturnApplicationEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:get")
    public ResponseMessage<PcCarReturnApplicationEO> find(@PathVariable String id) throws Exception {
        return Result.success("0","查询成功！",pcCarReturnApplicationEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "还车申请--新增")
    @ApiOperation(value = "|PcCarReturnApplicationEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:save")
    public ResponseMessage<PcCarReturnApplicationEO> create(@RequestBody PcCarReturnApplicationEO pcCarReturnApplicationEO) throws Exception {
        //默认状态为 0 ：草稿
        pcCarReturnApplicationEO.setStatus("0");
        pcCarReturnApplicationEOService.insertSelective(pcCarReturnApplicationEO);
        return Result.success("0","操作成功！",pcCarReturnApplicationEO);
    }

    @BusinessLog(description = "还车申请--启动流程")
    @ApiOperation(value = "|PcCarReturnApplicationEO|启动流程")
    @PostMapping("/startProcess")
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:startProcess")
    public ResponseMessage<PcCarReturnApplicationEO> startProcess(@RequestBody PcCarReturnApplicationEO pcCarReturnApplicationEO) throws Exception {
        //修改为根据发起人组织机构决定pv/cv流程
        UserEO curUser = userEOService.getUserWithRoles(UserUtils.getUser().getUsid());
        OrgEO curOrg = curUser.getOrgEOList().get(0);
        String flag = orgEOService.getByOrgId(curOrg.getId());
        String procDefKey = "";
        if ("0".equals(flag)) {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.CV_CAR_RETURN);
            procDefKey = dicTypeEO.getDicTypeName();
        } else {
            DicTypeEO dicTypeEO = dicTypeEOService.getDicTypeByDicCodeAndDicTypeId(ConstantUtils.PROCESS_CODE, ConstantUtils.PV_CAR_RETURN);
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
        pcCarReturnApplicationEOService.startProcess(pcCarReturnApplicationEO);
        return Result.success("0","操作成功！",pcCarReturnApplicationEO);
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
            pcCarReturnApplicationEOService.approvalProcess(request, requestEO, baseBusEO);
            pcCarReturnApplicationEOService.isFinishied(requestEO.getProcId(), baseBusEO.getBusinessId());
            return Result.success("0","操作成功！");
        }catch (Exception e){
            logger.error("-1",e.getStackTrace());
            return Result.error("-1","操作失败！");
        }
    }



    @BusinessLog(description = "还车申请--修改")
    @ApiOperation(value = "|PcCarReturnApplicationEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:update")
    public ResponseMessage<PcCarReturnApplicationEO> update(@RequestBody PcCarReturnApplicationEO pcCarReturnApplicationEO) throws Exception {
        pcCarReturnApplicationEOService.updateByPrimaryKeySelective(pcCarReturnApplicationEO);
        return Result.success("0","操作成功！",pcCarReturnApplicationEO);
    }

    @BusinessLog(description = "还车申请--删除")
    @ApiOperation(value = "|PcCarReturnApplicationEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_return_application:pcCarReturnApplication:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcCarReturnApplicationEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_CAR_RETURN_APPLICATION where id = {}", id);
        return Result.success("0","操作成功！");
    }

    @BusinessLog(description = "还车申请--导出")
    @ApiOperation(value = "|PcCarReturnApplicationEO|导出")
    @GetMapping("/exportDoc")
//    @RequiresPermissions("pc_return_application:pcCarReturnApplication:exportDoc")
    public ResponseMessage exportDoc(String id, HttpServletResponse response, HttpServletRequest request) throws Exception {
        try {
            PcCarReturnApplicationEO pcCarReturnApplicationEO = pcCarReturnApplicationEOService.selectByPrimaryKey(id);
            if(StringUtils.isEmpty(pcCarReturnApplicationEO)) {
                return Result.error("-1","导出失败！");
            }
            pcCarReturnApplicationEOService.exportDoc(pcCarReturnApplicationEO, response, request);
            return Result.success("0","操作成功！");
        }catch (Exception e){
            logger.error("-1",e.getStackTrace());
            return Result.error("-1","导出失败");
        }
    }

    @BusinessLog(description = "获取还车申请单编号")
    @ApiOperation(value = "|PcCarReturnApplicationEO|导出")
    @GetMapping("/findReturnCarCode")
    public ResponseMessage<String> findReturnCarCode(){
        try {
            String code = pcCarReturnApplicationEOService.findReturnCarCode();
            return Result.success("0", "获取成功！", code);
        }catch (Exception e){
            logger.error("-1",e.getStackTrace());
            return Result.error("-1","获取失败！");
        }
    }

}
