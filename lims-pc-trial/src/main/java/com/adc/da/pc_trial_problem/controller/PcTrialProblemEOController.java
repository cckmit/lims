package com.adc.da.pc_trial_problem.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.List;

import com.adc.da.login.util.UserUtils;
import com.adc.da.pc_trial_problem.entity.TpmUserEO;
import com.adc.da.util.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_trial_problem.entity.PcTrialProblemEO;
import com.adc.da.pc_trial_problem.entity.TpmOrgEO;
import com.adc.da.pc_trial_problem.page.PcTrialProblemEOPage;
import com.adc.da.pc_trial_problem.service.PcTrialProblemEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/pc_trial_problem/pcTrialProblem")
@Api(description = "|PcTrialProblemEO|")
public class PcTrialProblemEOController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(PcTrialProblemEOController.class);

    @Autowired
    private PcTrialProblemEOService pcTrialProblemEOService;

	@ApiOperation(value = "|PcTrialProblemEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:page")
    public ResponseMessage<PageInfo<PcTrialProblemEO>> page(PcTrialProblemEOPage page) throws Exception {
        List<PcTrialProblemEO> rows = pcTrialProblemEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PcTrialProblemEO|查询")
    @GetMapping("")
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:list")
    public ResponseMessage<List<PcTrialProblemEO>> list(PcTrialProblemEOPage page) throws Exception {
        return Result.success(pcTrialProblemEOService.queryByList(page));
	}

    @ApiOperation(value = "|PcTrialProblemEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:get")
    public ResponseMessage<PcTrialProblemEO> find(@PathVariable String id) throws Exception {
        return Result.success(pcTrialProblemEOService.selectByPrimaryKey(id));
    }

    @ApiOperation(value = "|PcTrialProblemEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:save")
    public ResponseMessage<PcTrialProblemEO> create(@RequestBody PcTrialProblemEO pcTrialProblemEO) throws Exception {
        pcTrialProblemEO.setStartuser(UserUtils.getUser().getAccount());
	    pcTrialProblemEOService.insertSelective(pcTrialProblemEO);
        return Result.success("0", "新增成功!", pcTrialProblemEO);
    }

    @ApiOperation(value = "|PcTrialProblemEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:update")
    public ResponseMessage<PcTrialProblemEO> update(@RequestBody PcTrialProblemEO pcTrialProblemEO) throws Exception {
        pcTrialProblemEO.setStartuser(UserUtils.getUser().getAccount());
        pcTrialProblemEOService.updateByPrimaryKeySelective(pcTrialProblemEO);
        return Result.success("0", "修改成功!", pcTrialProblemEO);
    }

    @ApiOperation(value = "|PcTrialProblemEO|提交给新品协同系统")
    @GetMapping("/putToTPM")
//    @RequiresPermissions("pc_trial_problem:pcTrialProblem:putToTPM")
    public ResponseMessage<PcTrialProblemEO> putToTPM(String id) {
	    try {
            //查询到对应的试验问题实体
            PcTrialProblemEO pcTrialProblemEO = pcTrialProblemEOService.selectByPrimaryKey(id);
            if (StringUtils.isNotEmpty(pcTrialProblemEO)) {
                String flag = pcTrialProblemEOService.putToTPM(pcTrialProblemEO);
                if (StringUtils.isNotEmpty(flag)) {
                    return Result.success("-1", flag, pcTrialProblemEO);
                }
            } else {
                return Result.success("-1", "提交内容为空!", pcTrialProblemEO);
            }
            return Result.success("0", "提交成功!", pcTrialProblemEO);
        }catch (Exception e){
        	logger.error(e.getMessage(), e);
            return Result.error("-1", "提交失败!");
        }
    }

    @ApiOperation(value = "|PcTrialProblemEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        pcTrialProblemEOService.deleteByPrimaryKey(id);
        logger.info("delete from PC_TRIAL_PROBLEM where id = {}", id);
        return Result.success("0", "删除成功!");
    }

    @ApiOperation(value = "|PcTrialProblemEO|查询人员")
    @GetMapping("/findUserFromTpm")
    @RequiresPermissions("pc_trial_problem:pcTrialProblem:findUserFromTpm")
    public ResponseMessage<List<TpmUserEO>> findUserFromTpm(String departName) throws Exception {
        return Result.success(pcTrialProblemEOService.findUserFromTpm(departName));
    }

    
    @ApiOperation(value = "|PcTrialProblemEO|新品查询人员")
    @GetMapping("/findUserFromTpmNew")
    public ResponseMessage<List<TpmUserEO>> findUserFromTpmNew() throws Exception {
        return Result.success(pcTrialProblemEOService.findUserFromTpmNew());
    }
 
    
    @ApiOperation(value = "|PcTrialProblemEO|新品查询组织机构")
    @GetMapping("/findOrgFromTpm")
    public ResponseMessage<List<TpmOrgEO>> findOrgFromTpm() throws Exception {
        return Result.success(pcTrialProblemEOService.findOrgFromTpm());
    }
    
}
