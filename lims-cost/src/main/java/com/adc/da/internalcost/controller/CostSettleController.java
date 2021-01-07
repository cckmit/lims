package com.adc.da.internalcost.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.utils.TransformUtil;
import com.adc.da.internalcost.entity.PcBudgetSettleStatusEO;
import com.adc.da.internalcost.page.CostStatisticsPage;
import com.adc.da.internalcost.service.CostStatisticsService;
import com.adc.da.internalcost.vo.CostBudgetVO;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/20 15:44
 * @description：
 */
@RestController
@RequestMapping("${restPath}/cm/costSettle")
@Api(tags = "CM-费用管理模块")
public class CostSettleController extends BaseController<CostBudgetVO> {

    @Autowired
    private CostStatisticsService costStatisticsService;

    @ApiOperation(value = "|costStatistics|费用结算（对公）结算")
    @PostMapping("/public")
    @RequiresPermissions("costStatistics:budgetPublic:public")
    public ResponseMessage settlePublic(@RequestBody PcBudgetSettleStatusEO pcBudgetSettleStatusEO) {
        //校验是否为空
        ResponseMessage x = TransformUtil.verify(pcBudgetSettleStatusEO, "trialId",  "settleTime", "settleUserId");
        if (x != null) return x;
        // 0，对公；1，对私
        if (settingStatus("0", pcBudgetSettleStatusEO)) return Result.success("-1", "已经结算过");
        costStatisticsService.postSettlePublic("0", pcBudgetSettleStatusEO);
        return Result.success("0", "结算成功");
    }

    @ApiOperation(value = "|costStatistics|费用结算（对私）结算")
    @PostMapping("/private")
    @RequiresPermissions("costStatistics:budgetPublic:private")
    public ResponseMessage settleprivate(@RequestBody PcBudgetSettleStatusEO pcBudgetSettleStatusEO ) {
        //校验是否为空
        ResponseMessage x = TransformUtil.verify(pcBudgetSettleStatusEO, "trialId",  "settleTime", "settleUserId");
        if (x != null) return x;
        // 0，对公；1，对私
        if (settingStatus("1", pcBudgetSettleStatusEO)) return Result.success("-1", "已经结算过");
        costStatisticsService.postSettlePublic("1", pcBudgetSettleStatusEO);
        return Result.success("0", "结算成功");
    }

    private boolean settingStatus(String type, PcBudgetSettleStatusEO pcBudgetSettleStatusEO) {
        String status = costStatisticsService.getStatusByTrialId(type, pcBudgetSettleStatusEO.getTrialId());
        if (StringUtils.isNotBlank(status)) {
            return true;
        }
        return false;
    }

    @ApiOperation(value = "|costStatistics|费用结算（对私）查询")
    @GetMapping("/settlePrivatePage")
    @RequiresPermissions("costStatistics:settlePrivate:get")
    public ResponseMessage<PageInfo<CostBudgetVO>> settlePrivate(CostStatisticsPage page) {
        // 0，统计；1，结算
        List<CostBudgetVO> rows = costStatisticsService.settlePrivate("1", page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|costStatistics|费用结算（对公）查询")
    @GetMapping("/settlePublicPage")
    @RequiresPermissions("costStatistics:settlePublic:get")
    public ResponseMessage<PageInfo<CostBudgetVO>> settlePublic(CostStatisticsPage page) {
        // 0，统计；1，结算
        List<CostBudgetVO> rows = costStatisticsService.settlePublic("1", page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

}
