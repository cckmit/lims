package com.adc.da.internalcost.controller;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.common.DocUtil;
import com.adc.da.common.utils.ZipUtils;
import com.adc.da.internalcost.page.CostStatisticsPage;
import com.adc.da.internalcost.service.CostStatisticsService;
import com.adc.da.internalcost.vo.CostApplyVO;
import com.adc.da.internalcost.vo.CostBudgetVO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tools.zip.ZipOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/14 16:23
 * @description：费用统计
 */
@RestController
@RequestMapping("${restPath}/cm/costStatistics")
@Api(tags = "CM-费用管理模块")
public class CostStatisticsController extends BaseController<CostBudgetVO> {

    @Autowired
    private DocUtil docUtil;

    @Value("${file.path}")
    private String filepath;

    @Autowired
    private CostStatisticsService costStatisticsService;

    @ApiOperation(value = "|costStatistics|费用预算统计（对公）查询")
    @GetMapping("/budgetPublicPage")
    // @RequiresPermissions("costStatistics:budgetPublic:get")
    public ResponseMessage<PageInfo<CostBudgetVO>> budgetPublic(CostStatisticsPage page) {
        List<CostBudgetVO> rows = costStatisticsService.budgetPublic(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|costStatistics|费用预算统计（对公）导出")
    @GetMapping("/budgetPublic")
    // @RequiresPermissions("costStatistics:budgetPublic:export")
    public void budgetPublic(HttpServletResponse response, CostStatisticsPage page) {
        // 查询要导出的内容
        List<CostBudgetVO> rows = costStatisticsService.budgetPublic(page);
        if (rows != null) {
            download(response, rows, "费用预算统计（对公）", "cost_budget_public");
        }
    }

    @ApiOperation(value = "|costStatistics|费用预算统计（对私）查询")
    @GetMapping("/budgetPrivatePage")
    // @RequiresPermissions("costStatistics:budgetPrivate:get")
    public ResponseMessage<PageInfo<CostBudgetVO>> budgetPrivate(CostStatisticsPage page) {
        List<CostBudgetVO> rows = costStatisticsService.budgetPrivate(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

    @ApiOperation(value = "|costStatistics|费用预算统计（对私）导出")
    @GetMapping("/budgetPrivate")
    // @RequiresPermissions("costStatistics:budgetPrivate:export")
    public void budgetPrivate(HttpServletResponse response, CostStatisticsPage page) {
        // 查询要导出的内容
        List<CostBudgetVO> rows = costStatisticsService.budgetPrivate(page);
        if (rows != null) {
            download(response, rows, "费用预算统计（对私）", "cost_budget_private");
        }
    }

    @ApiOperation(value = "|costStatistics|费用结算统计（对私）查询")
    @GetMapping("/settlePrivatePage")
    // @RequiresPermissions("costStatistics:settlePrivate:get")
    public ResponseMessage<PageInfo<CostBudgetVO>> settlePrivate(CostStatisticsPage page) {
        // 费用结算统计（对私）查询 --- 得到实际费用集合
        List<CostBudgetVO> actual = costStatisticsService.settlePrivate("0", page);  // 0，统计；1，结算
        // 费用预算统计（对私）查询 --- 得到预算费用集合
        List<CostBudgetVO> budget = costStatisticsService.budgetPrivate(page);

        // 得到实际与预算两个集合的并集
        List<CostBudgetVO> union = new ArrayList<>();
        union.addAll(actual);
        union.addAll(budget);

        // 并集去重
        // 新建list集合，存放去重后的元素
        List<CostBudgetVO> newList = new ArrayList<>();
        // 遍历并集
        for (int i = 0; i < union.size(); i++) {
            //判断新建集合是否包含有并集中的元素，如果不包含，则存入
            boolean isContains = newList.contains(union.get(i));
            if (!isContains) {
                newList.add(union.get(i));
            }
        }
        // 清空并集
        union.clear();
        union.addAll(newList);

        List<CostBudgetVO> result = new ArrayList<>();
        for (CostBudgetVO index : union) {
            CostBudgetVO costBudgetVO = new CostBudgetVO();

            costBudgetVO.setTrialTaskId(index.getTrialTaskId()); // 任务书ID
            costBudgetVO.setTrialTaskCode(index.getTrialTaskCode()); // 任务书编号
            costBudgetVO.setApplyUser(index.getApplyUser()); // 申请人
            costBudgetVO.setApplyTime(index.getApplyTime()); // 申请日期
            costBudgetVO.setUnderpanNo(index.getUnderpanNo()); // 车辆底盘号

            // set实际费用
            costBudgetVO.setPassWayBudget(index.getPassWayBudget()); // 过路过桥费
            costBudgetVO.setRefuelChargeAirEntrappingBudget(index.getRefuelChargeAirEntrappingBudget()); // 加油/充电/加气费
            costBudgetVO.setMaintainBudget(index.getMaintainBudget()); // 维修费
            costBudgetVO.setExpressBudget(index.getExpressBudget()); // 快递费/物流费
            costBudgetVO.setHireBudget(index.getHireBudget()); // 租赁费
            costBudgetVO.setAccommodationBudget(index.getAccommodationBudget()); // 住宿费
            costBudgetVO.setTravelBudget(index.getTravelBudget()); // 差旅交通费
            costBudgetVO.setPcOtherBudget(index.getPcOtherBudget()); // 其他
            costBudgetVO.setTotalBudget(index.getTotalBudget()); // 总计

            // set预算费用
            costBudgetVO.setPassWayBudgetPrivate(index.getPassWayBudget()); // 过路过桥费
            costBudgetVO.setRefuelChargeAirEntrappingBudgetPrivate(index.getRefuelChargeAirEntrappingBudget()); // 加油/充电/加气费
            costBudgetVO.setMaintainBudgetPrivate(index.getMaintainBudget()); // 维修费
            costBudgetVO.setExpressBudgetPrivate(index.getExpressBudget()); // 快递费/物流费
            costBudgetVO.setHireBudgetPrivate(index.getHireBudget()); // 租赁费
            costBudgetVO.setAccommodationBudgetPrivate(index.getAccommodationBudget()); // 住宿费
            costBudgetVO.setTravelBudgetPrivate(index.getTravelBudget()); // 差旅交通费
            costBudgetVO.setPcOtherBudgetPrivate(index.getPcOtherBudget()); // 其他
            costBudgetVO.setTotalBudgetPrivate(index.getTotalBudget()); // 总计

            // 最终的结果集
            result.add(costBudgetVO);
        }
        return Result.success(getPageInfo(page.getPager(), result));
    }

    @ApiOperation(value = "|costStatistics|费用结算统计（对公）查询")
    @GetMapping("/settlePublicPage")
    // @RequiresPermissions("costStatistics:settlePublic:get")
    public ResponseMessage<PageInfo<CostBudgetVO>> settlePublic(CostStatisticsPage page) {
        // 费用结算统计（对公）查询 --- 得到实际费用集合
        List<CostBudgetVO> actual = costStatisticsService.settlePublic("0", page);
        // 费用预算统计（对公）查询 --- 得到预算费用集合
        List<CostBudgetVO> budget = costStatisticsService.budgetPublic(page);

        // 得到实际与预算两个集合的并集
        List<CostBudgetVO> union = new ArrayList<>();
        union.addAll(actual);
        union.addAll(budget);

        // 并集去重
        // 新建list集合，存放去重后的元素
        List<CostBudgetVO> newList = new ArrayList<>();
        // 遍历并集
        for (int i = 0; i < union.size(); i++) {
            //判断新建集合是否包含有并集中的元素，如果不包含，则存入
            boolean isContains = newList.contains(union.get(i));
            if (!isContains) {
                newList.add(union.get(i));
            }
        }
        // 清空并集
        union.clear();
        union.addAll(newList);

        List<CostBudgetVO> result = new ArrayList<>();
        for (CostBudgetVO index : union) {
            CostBudgetVO costBudgetVO = new CostBudgetVO();

            costBudgetVO.setTrialTaskId(index.getTrialTaskId()); // 任务书ID
            costBudgetVO.setTrialTaskCode(index.getTrialTaskCode()); // 任务书编号
            costBudgetVO.setApplyUser(index.getApplyUser()); // 申请人
            costBudgetVO.setApplyTime(index.getApplyTime()); // 申请日期
            costBudgetVO.setUnderpanNo(index.getUnderpanNo()); // 车辆底盘号

            // set实际费用
            costBudgetVO.setRefuelChargeAirEntrappingBudget(index.getRefuelChargeAirEntrappingBudget()); // 加油/充电/加气费
            costBudgetVO.setMaintainBudget(index.getMaintainBudget()); // 维修费
            costBudgetVO.setExpressBudget(index.getExpressBudget()); // 快递费/物流费
            costBudgetVO.setOutSourceBudget(index.getOutSourceBudget()); // 委外试验费
            costBudgetVO.setPlaceBudget(index.getPlaceBudget()); // 场地费
            costBudgetVO.setTotalBudget(index.getTotalBudget()); // 总计

            // set预算费用
            costBudgetVO.setRefuelChargeAirEntrappingBudgetPublic(index.getRefuelChargeAirEntrappingBudget()); // 加油/充电/加气费
            costBudgetVO.setMaintainBudgetPublic(index.getMaintainBudget()); // 维修费
            costBudgetVO.setExpressBudgetPublic(index.getExpressBudget()); // 快递费/物流费
            costBudgetVO.setOutSourceBudgetPublic(index.getOutSourceBudget()); // 委外试验费
            costBudgetVO.setPlaceBudgetPublic(index.getPlaceBudget()); // 场地费
            costBudgetVO.setTotalBudgetPublic(index.getTotalBudget()); // 总计

            // 最终的结果集
            result.add(costBudgetVO);
        }
        return Result.success(getPageInfo(page.getPager(), result));
    }

    @ApiOperation(value = "|costStatistics|费用结算统计（对私）导出")
    @GetMapping("/settlePrivate")
    // @RequiresPermissions("costStatistics:settlePrivate:export")
    public void settlePrivate(HttpServletResponse response, CostStatisticsPage page) {
        // 查询要导出的内容
        // 0，统计；1，结算
        List<CostBudgetVO> rows = costStatisticsService.settlePrivate("0", page);
        if (rows != null) {
            download(response, rows, "费用结算统计（对私）", "cost_budget_private");
        }
    }

    @ApiOperation(value = "|costStatistics|费用结算统计（对公）导出")
    @GetMapping("/settlePublic")
    // @RequiresPermissions("costStatistics:settlePublic:export")
    public void settlePublic(HttpServletResponse response, CostStatisticsPage page) {
        // 查询要导出的内容
        List<CostBudgetVO> rows = costStatisticsService.settlePublic("0", page);
        if (rows != null) {
            download(response, rows, "费用结算统计（对公）", "cost_settle_public");
        }
    }

    @ApiOperation(value = "|costStatistics|费用申请统计查询")
    @GetMapping("/costApplyPage")
    // @RequiresPermissions("costStatistics:costApply:get")
    public ResponseMessage<PageInfo<CostApplyVO>> costApply(CostStatisticsPage page) {
        List<CostApplyVO> rows = costStatisticsService.costApply(page);
        return Result.success(page(page.getPager(), rows));
    }

    @ApiOperation(value = "|costStatistics|费用申请统计导出")
    @GetMapping("/costApply")
    // @RequiresPermissions("costStatistics:costApply:export")
    public void costApply(HttpServletResponse response, CostStatisticsPage page) {
        // 查询要导出的内容
        List<CostApplyVO> rows = costStatisticsService.costApply(page);
        if (rows != null) {
            download(response, rows, "费用申请统计", "cost_apply");
        }
    }

    private void download(HttpServletResponse response, Object vo, String fileName, String type) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(
                    fileName + ".zip", "UTF-8"));
            StringBuilder path = new StringBuilder();
            path.append(DateUtils.dateToString(new Date(),
                    "yyyy" + File.separator + "MM" + File.separator + "dd" + File.separator));
            String tempPath = this.filepath;
            if (!this.filepath.endsWith("\\") && !this.filepath.endsWith("/")) {
                tempPath = this.filepath + File.separator;
            }
            String filePath = tempPath + path;
            Map<String, Object> map = new HashMap<>();
            map.put("vos", vo);
            map.put("userName", UserUtils.getUser().getUsname());
            map.put("newTime", DateUtils.dateToString(new Date(), "yyyy-MM-dd"));
            File file = docUtil.createDoc(map, type, filePath + fileName + ".doc");
            zipOutputStream.setEncoding("GBK");
            ZipUtils.compress(file, zipOutputStream, "");
            // 冲刷输出流
            zipOutputStream.flush();
            zipOutputStream.close();
            if (file != null) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PageInfo<CostApplyVO> page(Pager pager, List<CostApplyVO> rows) {
        PageInfo<CostApplyVO> pageInfo = new PageInfo();
        pageInfo.setList(rows);
        pageInfo.setCount((long) pager.getRowCount());
        pageInfo.setPageSize(pager.getPageSize());
        pageInfo.setPageCount((long) pager.getPageCount());
        pageInfo.setPageNo(pager.getPageId());
        return pageInfo;
    }

}
