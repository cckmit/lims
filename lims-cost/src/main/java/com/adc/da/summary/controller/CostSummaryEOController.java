package com.adc.da.summary.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.base.web.BaseController;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.summary.page.CostSummaryEOPage;
import com.adc.da.summary.service.CostSummaryEOService;
import com.adc.da.summary.vo.CostSummaryVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("${restPath}/cm/summary")
@Api(tags = "CM-费用管理模块")
@SuppressWarnings("all")
public class CostSummaryEOController extends BaseController<CostSummaryEO> {
	/**
	 * 用户日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(CostSummaryEOController.class);
	
    /**
     * eo vo 转换
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;
    
    @Autowired
    private CostSummaryEOService costSummaryEOService;
    
    
    
    /**
     * 费用结算-分页查询
     * @Title: page
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<CostSummaryVO>>
     * @author: ljy
     * @date: 2020年8月13日
     */
    @ApiOperation(value = "|费用结算分页查询")
    @BusinessLog(description = "费用结算-分页查询")
    @GetMapping("/page")
    @RequiresPermissions("cm:summary:page")
    public ResponseMessage<PageInfo<CostSummaryVO>> page(CostSummaryEOPage page,
    		@RequestParam(value = "searchPhrase", required = false) String searchPhrase) {
    	try {
			List<CostSummaryEO> rows =  costSummaryEOService.page(page, searchPhrase);
			//设置总条数
			Integer rowsCount = costSummaryEOService.queryByCount(page);
			page.getPager().setRowCount(rowsCount);
			return Result.success("0", "查询成功", beanMapper.mapPage(getPageInfo(page.getPager(), rows), CostSummaryVO.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "查询失败!");
		}
    }
    
    
    
    /**
     * 费用结算-详情
     * @Title: getDetialById
     * @param id
     * @return
     * @return ResponseMessage<CostSummaryVO>
     * @author: ljy
     * @date: 2020年8月13日
     */
    @ApiOperation(value = "|费用结算详情")
    @BusinessLog(description = "费用结算-详情")
    @GetMapping("/{id}")
    @RequiresPermissions("cm:summary:get")
    public ResponseMessage<CostSummaryVO> getDetialById(@PathVariable(value = "id") String id) {
        try {
            return Result.success("0", "查询成功", beanMapper.map(costSummaryEOService.selectByPrimaryKey(id), CostSummaryVO.class));
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
			return Result.error("-1","查询失败!");
        }
    }
    
    
    
    /**
     * 费用结算-编辑
     * @Title: update
     * @param costSummaryVO
     * @return
     * @return ResponseMessage<CostSummaryVO>
     * @author: ljy
     * @date: 2020年8月13日
     */
    @ApiOperation(value = "|费用结算编辑")
    @BusinessLog(description = "费用结算-编辑")
    @PutMapping(path = "/edit")
    @RequiresPermissions("cm:summary:edit")
    public ResponseMessage<CostSummaryVO> update(@RequestBody CostSummaryVO costSummaryVO){
    	try {
    		//更新
    		costSummaryEOService.updateByPrimaryKeySelective(beanMapper.map(costSummaryVO, CostSummaryEO.class));
    		return Result.success("0","编辑成功!",costSummaryVO);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Result.error("-1", "编辑失败!");
		}
    }

    /**
     * 获取结算单编号
     */
    @ApiOperation(value = "|费用结算单生成")
    @BusinessLog(description = "费用结算-结算单编号生成")
    @GetMapping(path = "/createNum")
    public ResponseMessage createNum(){
        try {
            String costSummaryNum = costSummaryEOService.getCostSummaryNum("SYZX-FYJS-", "CostSettlementNum");
            return Result.success("0","生成成功!",costSummaryNum);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return Result.error("-1", "生成失败!");
        }
    }
}
