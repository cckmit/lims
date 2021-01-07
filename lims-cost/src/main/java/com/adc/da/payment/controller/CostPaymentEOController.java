package com.adc.da.payment.controller;

import java.util.List;

import com.adc.da.common.ConstantUtils;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.RequestEO;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.payment.entity.CostPaymentEO;
import com.adc.da.payment.page.CostPaymentEOPage;
import com.adc.da.payment.service.CostPaymentEOService;
import com.adc.da.payment.vo.CostPaymentVO;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.summary.vo.CostSummaryVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("${restPath}/cm/payment")
@Api(tags = "CM-费用管理模块")
@SuppressWarnings("all")
public class CostPaymentEOController extends BaseController<CostPaymentEO> {
	/**
	 * 用户日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(CostPaymentEOController.class);
	
	
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private CostPaymentEOService costPaymentEOService;


    
    
    /**
     * 结算单-分页查询
     * @Title: page
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<CostSummaryVO>>
     * @author: ljy
     * @date: 2020年8月14日
     */
    @ApiOperation(value = "|结算单分页查询")
    @BusinessLog(description = "结算单-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("cm:payment:page")
    public ResponseMessage<PageInfo<CostPaymentVO>> page(CostPaymentEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
    	try {
			List<CostPaymentEO> rows =  costPaymentEOService.page(page, searchPhrase);
			//设置总条数
			Integer rowsCount = costPaymentEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), CostPaymentVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    
    
    /**
     * 结算单-新增
     * @Title: save
     * @param costPaymentVO
     * @return
     * @return ResponseMessage<CostPaymentVO>
     * @author: ljy
     * @date: 2020年8月14日
     */
    @ApiOperation(value = "|结算单保存")
    @BusinessLog(description = "结算单-新增")
    @PostMapping(path = "/save")
    @RequiresPermissions("cm:payment:save")
    public ResponseMessage<CostPaymentVO> save(@RequestBody CostPaymentVO costPaymentVO){
    	try {
    		//保存
    		costPaymentEOService.save(beanMapper.map(costPaymentVO, CostPaymentEO.class));
    		return Result.success("0","新增成功!",costPaymentVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "新增失败!");
		}
    }
    
    
    
    /**
     * 结算单-编辑
     * @Title: update
     * @param costPaymentVO
     * @return
     * @return ResponseMessage<CostPaymentVO>
     * @author: ljy
     * @date: 2020年8月14日
     */
    @ApiOperation(value = "|结算单编辑")
    @BusinessLog(description = "结算单-编辑")
    @PutMapping(path = "/edit")
    @RequiresPermissions("cm:payment:edit")
    public ResponseMessage<CostPaymentVO> update(@RequestBody CostPaymentVO costPaymentVO){
    	try {
    		//更新
    		costPaymentEOService.update(beanMapper.map(costPaymentVO, CostPaymentEO.class));
    		return Result.success("0","编辑成功!",costPaymentVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }
    
    
    /**
     * 结算单详情
     * @Title: getDetialById
     * @param id
     * @return
     * @return ResponseMessage<CostPaymentVO>
     * @author: ljy
     * @date: 2020年8月14日
     */
    @ApiOperation(value = "|结算单详情")
    @BusinessLog(description = "结算单-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("cm:payment:get")
    public ResponseMessage<CostPaymentVO> getDetialById(@PathVariable(value = "id") String id) {
        try {
            return Result.success("0", "查询成功", beanMapper.map(costPaymentEOService.selectByPrimaryKey(id), 
            		CostPaymentVO.class));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
        }
    }

    /**
     * 费用结算单发起流程
     */
    @ApiOperation(value = "|启动流程")
    @BusinessLog(description = "费用结算单-流程启动")
    @PostMapping("/activity")
    @RequiresPermissions("cm:summary:activity")
    public ResponseMessage activity(@RequestBody CostPaymentVO costPaymentVO){
        try {
            //判断下一节点是否有审批人
            JSONObject jsonObj = costPaymentEOService.getStartNextNodeInfo(ConstantUtils.Pc_Cost_Payment_TYPE,
                    ConstantUtils.Cv_Cost_Payment_TYPE);
            if("-1".equals(jsonObj.getString("isSuccess"))) {
                return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            costPaymentEOService.submitActivity(beanMapper.map(costPaymentVO, CostPaymentEO.class),jsonObj.getString("flag"));
            return Result.success("0","流程启动成功!");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "流程启动失败!");
        }
    }

    /**
     * 费用结算单流程审批
     */
    @ApiOperation(value = "|费用结算单流程审批")
    @BusinessLog(description = "费用结算单-流程审批")
    @PostMapping("/approvalProcess")
    @RequiresPermissions("cm:summary:approvalProcess")
    @EnableAccess
    public ResponseMessage approvalProcess(
            HttpServletRequest request, @RequestBody RequestEO requestEO) {
    	try {
            //校验是否为空
            if (StringUtils.isEmpty(requestEO)) {
                return Result.error("-1", "审批信息不可为空");
            } else {
                if (StringUtils.isEmpty(requestEO.getBaseBusId())) {
                    return Result.error("-1", "业务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getTaskId())) {
                    return Result.error("-1", "任务Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getProcId())) {
                    return Result.error("-1", "流程实例Id不可为空");
                }
                if (StringUtils.isEmpty(requestEO.getVariables())) {
                    return Result.error("-1", "审批意见不可为空");
                }
            }
            //判断下一节点是否有审批人
            String str = costPaymentEOService.getNextNodeInfo(requestEO);
            if("-1".equals(str)) {
            	return Result.error("-1", "下一节点没有审批人，请联系管理员配置后在进行审批");
            }
            costPaymentEOService.approvalProcess(request, requestEO);
            return Result.success("0", "流程审核成功!");
        } catch (Exception e) {
            logger.error("-1", "流程审批失败！");
            return Result.error("-1", "流程审批失败！");
        }

    }
    
    
    
    

}
