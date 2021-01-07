package com.adc.da.acttask.controller;

import com.adc.da.acttask.entity.ActTaskEO;
import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.acttask.service.ActTaskEOService;
import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${restPath}/task/task/")
@Api(tags = "PersonSpace-工作流任务管理")
public class ActTaskEOController extends BaseController<ActTaskEO> {

    /**
     * 用户记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(ActTaskEOController.class);

    @Autowired
    private ActTaskEOService actTaskEOService;

    @BusinessLog(description = "待办查询")
    @ApiOperation(value = "|ActTaskEO|待办查询")
    @GetMapping("/ruTaskPage")
//    @RequiresPermissions("ActTaskEO:ActTaskEO:ruTaskPage")
    @EnableAccess
    public ResponseMessage<PageInfo<ActTaskEO>> ruTaskPage(ActTaskEOPage page, String searchField){
        try{
            List<ActTaskEO> actTaskEOList = actTaskEOService.ruTaskPage(page, searchField);
            page.getPager().setRowCount(actTaskEOService.queryRuCount(page));
            return Result.success("0","查询成功",getPageInfo(page.getPager(), actTaskEOList));
        }catch (Exception e){
            logger.error("-1", e.getMessage());
            return Result.error("-1", "查询失败！");
        }
    }

    @BusinessLog(description = "已办查询")
    @ApiOperation(value = "|ActTaskEO|已办查询")
    @GetMapping("/hiTaskPage")
//    @RequiresPermissions("ActTaskEO:ActTaskEO:hiTaskPage")
    @EnableAccess
    public ResponseMessage<PageInfo<ActTaskEO>> hiTaskPage(ActTaskEOPage page, String searchField){
        try{
            List<ActTaskEO> actTaskEOList = actTaskEOService.hiTaskPage(page, searchField);
            page.getPager().setRowCount(actTaskEOService.queryHiCount(page));
            return Result.success("0","查询成功",getPageInfo(page.getPager(), actTaskEOList));
        }catch (Exception e){
            logger.error("-1", e.getMessage());
            return Result.error("-1", "查询失败！");
        }
    }

    @BusinessLog(description = "跟踪查询")
    @ApiOperation(value = "|ActTaskEO|跟踪查询")
    @GetMapping("/trackTaskPage")
//    @RequiresPermissions("ActTaskEO:ActTaskEO:trackTaskPage")
    @EnableAccess
    public ResponseMessage<PageInfo<ActTaskEO>> trackTaskPage(ActTaskEOPage page, String searchField){
        try{
            List<ActTaskEO> actTaskEOList = actTaskEOService.trackTaskPage(page, searchField);
            page.getPager().setRowCount(actTaskEOService.queryTrackCount(page));
            return Result.success("0","查询成功",getPageInfo(page.getPager(), actTaskEOList));
        }catch (Exception e){
            logger.error("-1", e.getMessage());
            return Result.error("-1", "查询失败！");
        }
    }

    /**
     * 根据baseBusId获取流程信息
    * @Title：getApprovalInfoById
    * @param baseBusId
    * @return
    * @return: ResponseMessage<ActTaskEO>
    * @author： ljy  
    * @date： 2019年9月20日
     */
    @BusinessLog(description = "撤回信息查询")
    @ApiOperation(value = "|ActTaskEO|撤回信息查询")
    @GetMapping("/getApprovalInfoById")
    public ResponseMessage<ActTaskEO> getApprovalInfoById(String baseBusId){
    	try {
    		ActTaskEO act = actTaskEOService.getApprovalInfoById(baseBusId);
    		if(StringUtils.isEmpty(act)) {
    			return Result.success("-1","查询结果为空");
    		}
    		return Result.success("0","查询成功", act);
		} catch (Exception e) {
			logger.error("-1", e.getMessage());
            return Result.error("-1", "查询失败！");
		}
    }

    @BusinessLog(description = "获取任务Id")
    @ApiOperation(value = "|ActTaskEO|获取任务Id")
    @GetMapping("/findRequestEOByBusinessId")
    public ResponseMessage<String> findRequestEOByBusinessId(String id){
        String taskId = actTaskEOService.findRequestEOByBusinessId(id);
        if("Error".equals(taskId)){
            return Result.error("-1","查询失败！");
        }
        return Result.success("0","查询成功！", taskId);
    }

    @BusinessLog(description = "获取流程实例Id")
    @ApiOperation(value = "|ActTaskEO|获取流程实例Id")
    @GetMapping("/findProcIdByBusinessId")
    public ResponseMessage<String> findProcIdByBusinessId(String id){
        String taskId = actTaskEOService.findProcIdByBusinessId(id);
        if("Error".equals(taskId)){
            return Result.error("-1","查询失败！");
        }
        return Result.success("0","查询成功！", taskId);
    }
    
}
