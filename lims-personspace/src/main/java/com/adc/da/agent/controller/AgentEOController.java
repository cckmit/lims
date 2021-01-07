package com.adc.da.agent.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.Date;
import java.util.List;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.agent.entity.AgentEO;
import com.adc.da.agent.page.AgentEOPage;
import com.adc.da.agent.service.AgentEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;

@RestController
@RequestMapping("/${restPath}/agent/agent")
@Api(tags = "PersonSpace-代理人管理")
public class AgentEOController extends BaseController<AgentEO>{

    private static final Logger logger = LoggerFactory.getLogger(AgentEOController.class);

    @Autowired
    private AgentEOService agentEOService;

    @BusinessLog(description = "代理人查询")
	@ApiOperation(value = "|AgentEO|分页查询")
    @GetMapping("/page")
    @RequiresPermissions("agent:agent:page")
    public ResponseMessage<PageInfo<AgentEO>> page(AgentEOPage page, String searchField) throws Exception {
	    try{
            List<AgentEO> rows = agentEOService.queryByPage(page,searchField);
            page.getPager().setRowCount(agentEOService.queryByCount(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch (Exception e){
            logger.error("-1","查询失败！");
            return Result.error("-1","查询失败！");
        }
    }

    @BusinessLog(description = "委托人分页查询")
	@ApiOperation(value = "|AgentEO|委托人分页查询")
    @GetMapping("/trustor")
    @RequiresPermissions("agent:agent:trustor")
    public ResponseMessage<PageInfo<AgentEO>> list(AgentEOPage page,String searchField) throws Exception {
        try{
            List<AgentEO> rows = agentEOService.queryByPage2(page,searchField);
            page.getPager().setRowCount(agentEOService.queryByCount2(page));
            return Result.success(getPageInfo(page.getPager(), rows));
        }catch (Exception e){
            logger.error("-1","查询失败！");
            return Result.error("-1","查询失败！");
        }
	}

	@BusinessLog(description = "查看代理人")
    @ApiOperation(value = "|AgentEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("agent:agent:get")
    public ResponseMessage<AgentEO> find(@PathVariable String id) throws Exception {
        return Result.success(agentEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "新增代理人")
    @ApiOperation(value = "|AgentEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("agent:agent:save")
    public ResponseMessage<AgentEO> create(@RequestBody AgentEO agentEO) throws Exception {
	    agentEO.setCreateByName(UserUtils.getUser().getUsname());
        agentEO.setCreateById(UserUtils.getUserId());
        agentEO.setCreateDate(DateUtils.dateToString(new Date(),"yyyy-MM-dd" ));
        agentEO.setDelFlag("0");
        agentEOService.insertSelective(agentEO);
        return Result.success("0","操作成功！",agentEO);
    }

    @BusinessLog(description = "编辑代理人")
    @ApiOperation(value = "|AgentEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("agent:agent:update")
    public ResponseMessage<AgentEO> update(@RequestBody AgentEO agentEO) throws Exception {
        agentEOService.updateByPrimaryKeySelective(agentEO);
        return Result.success("0","操作成功！",agentEO);
    }

    @BusinessLog(description = "删除代理人")
    @ApiOperation(value = "|AgentEO|删除")
    @DeleteMapping("/{id}")
    @RequiresPermissions("agent:agent:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
        agentEOService.deleteByPrimaryKey(id);
        logger.info("delete from TP_AGENT where id = {}", id);
        return Result.success("0","操作成功！");
    }

}
