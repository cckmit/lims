package com.adc.da.pc_announcement.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.pc_announcement.VO.AnnPlanAndProjectProgramVO;
import com.adc.da.pc_announcement.entity.AnnPlanEO;
import com.adc.da.pc_announcement.service.AnnPlanEOService;
import com.adc.da.pc_announcement.service.AnnPlanProgramEOService;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_announcement.entity.AnnPlanProjectEO;
import com.adc.da.pc_announcement.page.AnnPlanProjectEOPage;
import com.adc.da.pc_announcement.service.AnnPlanProjectEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_announcement/annPlanProject")
@Api(tags = "公告管理")
public class AnnPlanProjectEOController extends BaseController<AnnPlanProjectEO>{

    private static final Logger logger = LoggerFactory.getLogger(AnnPlanProjectEOController.class);

    @Autowired
    private AnnPlanProjectEOService annPlanProjectEOService;
    @Autowired
    private AnnPlanProgramEOService annPlanProgramEOService;
    @Autowired
    private AnnPlanEOService annPlanEOService;
    @Autowired
    private UserEOService userEOService;
    @Autowired
    private BeanMapper beanMapper;
    @ApiOperation(value = "查询对应计划的所有申报项目")
    @GetMapping("/getAnnPlanProjectList")
    public  List<AnnPlanProjectEO> getAnnPlanProjectList(@RequestBody AnnPlanEO annPlanEO){
        try{
            String planID = annPlanEO.getId();
            return annPlanProjectEOService.getAnnPlanProjectList(planID);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "得到角色所有为CV试验工程师的用户列表")
    @GetMapping("/getCVEngineerList")
    public  List<UserEO> getCVEngineerList(){
        try{
            return userEOService.getUserByCode("CV_TrialEngineer");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value = "删除对应的申报项目")
    @DeleteMapping("/delPlanProject/{projectID}")
    @BusinessLog(description = "删除对应的申报项目")
    @RequiresPermissions("pc_announcement:annPlan:delPlanProject")
    public ResponseMessage delPlanProject(@PathVariable String projectID){
        try{
            //删除申报项目下面的检验方案
            List<String> projectIDList = new ArrayList<>();
            projectIDList.add(projectID);
            List<String> idListByPjIDList = annPlanProgramEOService.getIdListByPjIDList(projectIDList);
            if(idListByPjIDList.size() != 0){
                annPlanProgramEOService.delByIdList(idListByPjIDList);
            }
            //删除申报项目
            annPlanProjectEOService.deleteByPrimaryKey(projectID);
            return Result.success("0","删除成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "删除失败！");
        }
    }
    @ApiOperation(value = "工程师对计划的检验项目进行编辑")
    @PutMapping("/updateEngineerProject")
    @BusinessLog(description = "工程师对计划的检验项目进行编辑")
    public ResponseMessage updateEngineerProject(@RequestBody List<AnnPlanProjectEO> annPlanProjectEOList){
        try{
            annPlanProjectEOService.updateEngineerProject(annPlanProjectEOList);
            return Result.success("0","编辑成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "编辑失败！");
        }
    }
    @ApiOperation("上传报告")
    @PutMapping("/saveReport")
    @BusinessLog(description = "上传报告")
    @RequiresPermissions("pc_announcement:annPlanProgram:saveReport")
    public ResponseMessage saveReport(@RequestBody AnnPlanProjectEO annPlanProjectEO){
        try{
            annPlanProjectEOService.saveReport(annPlanProjectEO);
            annPlanEOService.updatePlanStatusToFinish(annPlanProjectEO.getPlanId());
            return Result.success("0","上传成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "上传失败！");
        }
    }
    @ApiOperation("取消申报项目")
    @PutMapping("/cancelReport")
    @BusinessLog(description = "取消申报项目")
    @RequiresPermissions("pc_announcement:annPlanProgram:saveReport")
    public ResponseMessage updateReportToCancel(@RequestBody AnnPlanProjectEO annPlanProjectEO){
        try{
            annPlanProjectEOService.updateReportToCancel(annPlanProjectEO);
            annPlanEOService.updatePlanStatusToFinish(annPlanProjectEO.getPlanId());
            return Result.success("0","取消成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "取消失败！");
        }
    }
    @ApiOperation(value = "费用预算查看，实际费用查看")
    @GetMapping("/getProjectMes")
    public AnnPlanAndProjectProgramVO getProjectMes(String supId, String projectId){
        try{
            return annPlanProjectEOService.getProjectMes(supId,projectId);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
    @ApiOperation(value ="预算费用编辑")
    @PutMapping("/updateBudgetCost")
    @BusinessLog(description = "预算费用编辑")
    public ResponseMessage updateBudgetCost (@RequestBody AnnPlanAndProjectProgramVO annPlanAndProjectProgramVO){
        try{
            //费用预算编辑，同时会对检验方案表做出修改
            annPlanProjectEOService.updateBudgetCost(annPlanAndProjectProgramVO);
            return Result.success("0","编辑成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "编辑失败！");
        }
    }
    @ApiOperation(value ="实际费用编辑")
    @PutMapping("/updateActualCost")
    @BusinessLog(description = "实际费用编辑")
    public ResponseMessage updateActualCost (@RequestBody AnnPlanAndProjectProgramVO annPlanAndProjectProgramVO){
        try{
            annPlanProjectEOService.updateActualCost(annPlanAndProjectProgramVO);
            return Result.success("0","编辑成功!");
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "编辑失败！");
        }
    }
	@ApiOperation(value = "|AnnPlanProjectEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_announcement:annPlanProject:page")
    public ResponseMessage<PageInfo<AnnPlanProjectEO>> page(AnnPlanProjectEOPage page) throws Exception {
        List<AnnPlanProjectEO> rows = annPlanProjectEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|AnnPlanProjectEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_announcement:annPlanProject:list")
    public ResponseMessage<List<AnnPlanProjectEO>> list(AnnPlanProjectEOPage page) throws Exception {
        return Result.success(annPlanProjectEOService.queryByList(page));
	}

    @ApiOperation(value = "|AnnPlanProjectEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlanProject:get")
    public ResponseMessage<AnnPlanProjectEO> find(@PathVariable String id) throws Exception {
        return Result.success(annPlanProjectEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|AnnPlanProjectEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlanProject:save")
    public ResponseMessage<AnnPlanProjectEO> create(@RequestBody AnnPlanProjectEO annPlanProjectEO) throws Exception {
        annPlanProjectEOService.insertSelective(annPlanProjectEO);
        return Result.success(annPlanProjectEO);
    }

    @ApiOperation(value = "|AnnPlanProjectEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_announcement:annPlanProject:update")
    public ResponseMessage<AnnPlanProjectEO> update(@RequestBody AnnPlanProjectEO annPlanProjectEO) throws Exception {
        annPlanProjectEOService.updateByPrimaryKeySelective(annPlanProjectEO);
        return Result.success(annPlanProjectEO);
    }

    @ApiOperation(value = "|AnnPlanProjectEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_announcement:annPlanProject:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        annPlanProjectEOService.deleteByPrimaryKey(id);
        logger.info("delete from ANN_PLAN_PROJECT where id = {}", id);
        return Result.success();
    }

}
