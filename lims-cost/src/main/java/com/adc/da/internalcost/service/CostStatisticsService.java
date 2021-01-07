package com.adc.da.internalcost.service;

import com.adc.da.internalcost.dao.CostStatisticsDao;
import com.adc.da.internalcost.entity.PcBudgetSettleStatusEO;
import com.adc.da.internalcost.page.CostStatisticsPage;
import com.adc.da.internalcost.vo.*;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/11/15 14:14
 * @description：
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CostStatisticsService {

    @Autowired
    private CostStatisticsDao costStatisticsDao;

    /**
     * 费用申请统计查询
     * @param page
     * @return
     */
    public List<CostApplyVO> costApply(CostStatisticsPage page) {
        // 查询任务书和费用预算信息
        List<TrialTaskCostBudgetVO> vos = costStatisticsDao.getTrialTaskBudget(page);
        List<CostApplyVO> costApplyVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vos)) {
            for (TrialTaskCostBudgetVO vo : vos) {
                // 通过任务书ID查询费用申请明细信息
                List<PcBudgetUseInfoVO> infoVOS = costStatisticsDao.getBudgetUseInfo(vo.getId());
                if (CollectionUtils.isNotEmpty(infoVOS)) {
                    for (PcBudgetUseInfoVO infoVO : infoVOS) {
                        CostApplyVO costApplyVO = new CostApplyVO();
                        costApplyVO.setTrialTaskCode(vo.getTrialTaskCode());
                        costApplyVO.setUnderpanNo("");
                        costApplyVO.setProject(infoVO.getBuProject());
                        costApplyVO.setContent(infoVO.getBuContent());
                        costApplyVO.setUnitPrice(infoVO.getBuUnitPrice());
                        costApplyVO.setPcNumber(infoVO.getBuCount());
                        costApplyVO.setPriceTotal(infoVO.getBuSubtotal());
                        costApplyVO.setSupplierName(infoVO.getBuSupplier());
                        costApplyVO.setApplyTime(vo.getApplyTime());
                        costApplyVO.setApplyUser(vo.getApplyUser());
                        costApplyVOS.add(costApplyVO);
                    }
                }
            }
        }
        page.getPager().setRowCount(costApplyVOS.size());
        return costApplyVOS;
    }

    /**
     * 费用结算统计（对公）查询
     *
     * @param page
     * @return
     */
    public List<CostBudgetVO> settlePublic(String type, CostStatisticsPage page) {
        // 查询任务书和费用预算信息
        List<TrialTaskCostBudgetVO> vos = costStatisticsDao.getTrialTaskBudget(page);
        Integer rowCount = costStatisticsDao.getTrialTaskBudgetCount(page);
        page.getPager().setRowCount(rowCount);
        List<CostBudgetVO> costBudgetVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vos)) {
            for (TrialTaskCostBudgetVO vo : vos) {
                CostBudgetVO costBudgetVO = new CostBudgetVO();
                // 任务书ID
                costBudgetVO.setTrialTaskId(vo.getId());
                // 任务书编号
                costBudgetVO.setTrialTaskCode(vo.getTrialTaskCode());
                // 申请人
                costBudgetVO.setApplyUser(vo.getApplyUser());
                // 申请时间
                costBudgetVO.setApplyTime(vo.getApplyTime());
                // 通过任务书ID查询样品信息
                String underpan = costStatisticsDao.selectSampleByTrialId(vo.getId());
                // 底盘号
                costBudgetVO.setUnderpanNo(underpan);
                // 如果是结算模块，需要查询结算状态, 通过任务书ID车讯供应商信息
                // type为 0，统计；1，结算
                if ("1".equals(type)) {
                    // type对公对私类型，0对公；1对私
                    String status = costStatisticsDao.getStatusByTrialId("0", vo.getId());
                    if (StringUtils.isNotBlank(status)) {
                        costBudgetVO.setStatus(status);
                    } else {
                        costBudgetVO.setStatus("1");
                    }
                    // todo 查询供应商信息
                }
                // 总计
                Double total = 0.0;
                // 通过任务书ID获取费用预算
                List<TrialTaskCostBudgetVO> trialTaskCostBudgetVOList =
                        costStatisticsDao.getBudgetByTaskId(vo.getId());
                if (CollectionUtils.isNotEmpty(trialTaskCostBudgetVOList)) {
                    for (TrialTaskCostBudgetVO trialTaskCostBudgetVO : trialTaskCostBudgetVOList) {
                        // 加油、加气、充电
                        if ("ServiceCharge".equals(trialTaskCostBudgetVO.getItemsCode())
                                || "ChargeForPublicCharging".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 加油、充电、加气
                            if (StringUtils.isNotBlank(costBudgetVO.getRefuelChargeAirEntrappingBudget())) {
                                Double total1 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getRefuelChargeAirEntrappingBudget());
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(total1.toString());
                                total += total1;
                            } else {
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("CorporateRepairFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 维修
                            if (StringUtils.isNotBlank(costBudgetVO.getMaintainBudget())) {
                                Double total2 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getMaintainBudget());
                                costBudgetVO.setMaintainBudget(total2.toString());
                                total += total2;
                            } else {
                                costBudgetVO.setMaintainBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("CorporateMaintenanceFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 保养
                            if (StringUtils.isNotBlank(costBudgetVO.getUpkeepBudget())) {
                                Double total3 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getUpkeepBudget());
                                costBudgetVO.setUpkeepBudget(total3.toString());
                                total += total3;
                            } else {
                                costBudgetVO.setUpkeepBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("HainanTestSiteCost".equals(trialTaskCostBudgetVO.getItemsCode())
                                || "XiangyangTestSiteCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 场地费
                            if (StringUtils.isNotBlank(costBudgetVO.getPlaceBudget())) {
                                Double total4 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getPlaceBudget());
                                costBudgetVO.setPlaceBudget(total4.toString());
                                total += total4;
                            } else {
                                costBudgetVO.setPlaceBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("OutsourcingFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 委外费
                            if (StringUtils.isNotBlank(costBudgetVO.getOutSourceBudget())) {
                                Double total5 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getOutSourceBudget());
                                costBudgetVO.setOutSourceBudget(total5.toString());
                                total += total5;
                            } else {
                                costBudgetVO.setOutSourceBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        // settingBudgetVO(costBudgetVO, trialTaskCostBudgetVO);
                    }
                }
                // 通过任务书ID获取费用报销
                List<PcTrialProductVO> reimbursement = costStatisticsDao.getReimbursement(vo.getId());
                // 快递费
                if (CollectionUtils.isNotEmpty(reimbursement)) {
                    String expressTotal = reimbursement.get(reimbursement.size() - 1).getExpressTotal();
                    costBudgetVO.setExpressBudget(expressTotal);
                    if(expressTotal != null){
                        total = total + Double.parseDouble(expressTotal);
                    }
                }
                // 总计
                costBudgetVO.setTotalBudget(total.toString());
                costBudgetVOS.add(costBudgetVO);
            }
        }
        return costBudgetVOS;
    }

    /**
     * 费用结算统计（对私）查询
     *
     * @param page
     * @return
     */
    public List<CostBudgetVO> settlePrivate(String type, CostStatisticsPage page) {
        // 查询任务书和费用预算信息
        List<TrialTaskCostBudgetVO> vos = costStatisticsDao.getTrialTaskBudget(page);
        Integer rowCount = costStatisticsDao.getTrialTaskBudgetCount(page);
        page.getPager().setRowCount(rowCount);
        List<CostBudgetVO> costBudgetVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vos)) {
            for (TrialTaskCostBudgetVO vo : vos) {
                CostBudgetVO costBudgetVO = new CostBudgetVO();
                // 任务书ID
                costBudgetVO.setTrialTaskId(vo.getId());
                // 任务书编号
                costBudgetVO.setTrialTaskCode(vo.getTrialTaskCode());
                // 申请人
                costBudgetVO.setApplyUser(vo.getApplyUser());
                // 申请时间
                costBudgetVO.setApplyTime(vo.getApplyTime());
                // 通过任务书ID查询样品信息
                String underpan = costStatisticsDao.selectSampleByTrialId(vo.getId());
                // 底盘号
                costBudgetVO.setUnderpanNo(underpan);
                // 总计
                Double total = 0.0;
                // 如果是结算模块，需要查询结算状态
                // type为 0，统计；1，结算
                if ("1".equals(type)) {
                    // type对公对私类型，0对公；1对私
                    String status = costStatisticsDao.getStatusByTrialId("1", vo.getId());
                    if (StringUtils.isNotBlank(status)) {
                        costBudgetVO.setStatus(status);
                    } else {
                        costBudgetVO.setStatus("1");
                    }
                }
                // 查询人员及住宿预算费用
                List<TrialTaskCostBudgetVO> person =
                        costStatisticsDao.getBudgetPersonByTaskId(vo.getId());
                if (CollectionUtils.isNotEmpty(person)) {
                    for (TrialTaskCostBudgetVO trialTaskCostBudgetVO : person) {
                        if ("AccommodationCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 住宿费
                            if (StringUtils.isNotBlank(costBudgetVO.getAccommodationBudget())) {
                                Double total1 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getAccommodationBudget());
                                costBudgetVO.setAccommodationBudget(total1.toString());
                                total += total1;
                            } else {
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                                costBudgetVO.setAccommodationBudget(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("TravelAllowance".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 差旅费
                            if (StringUtils.isNotBlank(costBudgetVO.getTravelBudget())) {
                                Double total2 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getTravelBudget());
                                costBudgetVO.setTravelBudget(total2.toString());
                                total += total2;
                            } else {
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                                costBudgetVO.setTravelBudget(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                    }
                }
                // 通过委外立项ID获取费用预算
                List<TrialTaskCostBudgetVO> trialTaskCostBudgetVOList =
                        costStatisticsDao.getBudgetByTaskId(vo.getId());
                if (CollectionUtils.isNotEmpty(trialTaskCostBudgetVOList)) {
                    for (TrialTaskCostBudgetVO trialTaskCostBudgetVO : trialTaskCostBudgetVOList) {
                        // 加油、加气、充电
                        if ("CashPlusPostage".equals(trialTaskCostBudgetVO.getItemsCode())
                                || "CashCharge".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            if (StringUtils.isNotBlank(costBudgetVO.getRefuelChargeAirEntrappingBudget())) {
                                Double total3 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getRefuelChargeAirEntrappingBudget());
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(total3.toString());
                                total += total3;
                            } else {
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("CashRepairFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 维修
                            if (StringUtils.isNotBlank(costBudgetVO.getMaintainBudget())) {
                                Double total4 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getMaintainBudget());
                                costBudgetVO.setMaintainBudget(total4.toString());
                                total += total4;
                            } else {
                                costBudgetVO.setMaintainBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        if ("CashMaintenanceFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 保养费
                            costBudgetVO.setUpkeepBudget(trialTaskCostBudgetVO.getTotal());
                            if (StringUtils.isNotBlank(costBudgetVO.getUpkeepBudget())) {
                                Double total5 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getUpkeepBudget());
                                costBudgetVO.setUpkeepBudget(total5.toString());
                                total += total5;
                            } else {
                                costBudgetVO.setUpkeepBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        // settingBudgetVO(costBudgetVO, trialTaskCostBudgetVO);
                    }
                }
                // 通过任务书ID获取费用报销
                List<PcTrialProductVO> reimbursement = costStatisticsDao.getReimbursement(vo.getId());
                if (CollectionUtils.isNotEmpty(reimbursement)) {
                    // 快递费
                    String expressTotal = reimbursement.get(reimbursement.size() - 1).getExpressTotal();
                    costBudgetVO.setExpressBudget(expressTotal);
                    // 过路过桥费
                    String passWayTotal = reimbursement.get(reimbursement.size() - 1).getPassWayTotal();
                    costBudgetVO.setPassWayBudget(passWayTotal);
                    // 租赁费
                    String hireTotal = reimbursement.get(reimbursement.size() - 1).getHireTotal();
                    costBudgetVO.setHireBudget(hireTotal);
                    // 其他
                    String pcOtherTotal = reimbursement.get(reimbursement.size() - 1).getPcOtherTotal();
                    costBudgetVO.setPcOtherBudget(pcOtherTotal);
                    if(expressTotal != null && passWayTotal != null && hireTotal != null && pcOtherTotal != null){
                        total = total + Double.parseDouble(expressTotal) + Double.parseDouble(passWayTotal) +
                                Double.parseDouble(hireTotal) + Double.parseDouble(pcOtherTotal);
                    }
                }
                // 总计
                costBudgetVO.setTotalBudget(total.toString());
                costBudgetVOS.add(costBudgetVO);
            }
        }
        return costBudgetVOS;
    }

    /**
     * 费用预算统计（对私）查询
     *
     * @param page
     * @return
     */
    public List<CostBudgetVO> budgetPrivate(CostStatisticsPage page) {

        // 查询任务书ID（主键）、任务书编号（试验任务编号）、申请时间（创建时间）、申请人（真实姓名）
        List<TrialTaskCostBudgetVO> vos = costStatisticsDao.getTrialTaskBudget(page); // size = 10
        Integer rowCount = costStatisticsDao.getTrialTaskBudgetCount(page); // 查询所有行数：112
        page.getPager().setRowCount(rowCount);
        List<CostBudgetVO> costBudgetVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vos)) {
            for (TrialTaskCostBudgetVO vo : vos) {
                CostBudgetVO costBudgetVO = new CostBudgetVO();
                // 任务书编号
                costBudgetVO.setTrialTaskCode(vo.getTrialTaskCode());
                // 申请人
                costBudgetVO.setApplyUser(vo.getApplyUser());
                // 申请时间
                costBudgetVO.setApplyTime(vo.getApplyTime());
                // 通过任务书ID得到底盘号（发动机编号）
                costBudgetVO.setUnderpanNo(costStatisticsDao.selectSampleByTrialId(vo.getId()));
                // 初始化总计
                Double total = 0.0;

                // 通过任务书ID获取 “PC_BUDGET_PERSON   PC试验人员及住宿预算”
                // 关联表 “PC_RELIABLE_INITTASK   PC试验可靠性立项单”
                // 查询 “名称code、名称、小计”
                List<TrialTaskCostBudgetVO> person = costStatisticsDao.getBudgetPersonByTaskId(vo.getId()); // size = 10
                if (CollectionUtils.isNotEmpty(person)) {
                    for (TrialTaskCostBudgetVO trialTaskCostBudgetVO : person) {
                        if ("AccommodationCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 费用预算统计表格字段：住宿费（itemsName：住宿费用）
                            if (StringUtils.isNotBlank(costBudgetVO.getAccommodationBudget())) {
                                Double total1 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getAccommodationBudget());
                                costBudgetVO.setAccommodationBudget(total1.toString());
                                total += total1;
                            } else {
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal()); // total = 0
                                costBudgetVO.setAccommodationBudget(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("TravelAllowance".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 差旅交通费（出差补助）
                            if (StringUtils.isNotBlank(costBudgetVO.getTravelBudget())) {
                                Double total2 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getTravelBudget());
                                costBudgetVO.setTravelBudget(total2.toString());
                                total += total2;
                            } else {
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal()); // total = 0
                                costBudgetVO.setTravelBudget(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                    }
                }

                // 通过任务书ID获取 “PC_BUDGET_TEST   PC试验费用预算”
                // 关联表 “PC_RELIABLE_INITTASK   PC试验可靠性立项单”
                // 查询 “名称code、名称、小计”
                List<TrialTaskCostBudgetVO> trialTaskCostBudgetVOList = costStatisticsDao.getBudgetByTaskId(vo.getId());
                if (CollectionUtils.isNotEmpty(trialTaskCostBudgetVOList)) {
                    for (TrialTaskCostBudgetVO trialTaskCostBudgetVO : trialTaskCostBudgetVOList) {
                        // 加油/充电/加气费（现金加油费、现金充电费）
                        if ("CashPlusPostage".equals(trialTaskCostBudgetVO.getItemsCode())
                                || "CashCharge".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            if (StringUtils.isNotBlank(costBudgetVO.getRefuelChargeAirEntrappingBudget())) {
                                Double total3 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getRefuelChargeAirEntrappingBudget());
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(total3.toString());
                                total += total3;
                            } else {
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("CashRepairFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 维修（现金维修费）
                            if (StringUtils.isNotBlank(costBudgetVO.getMaintainBudget())) {
                                Double total4 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getMaintainBudget());
                                costBudgetVO.setMaintainBudget(total4.toString());
                                total += total4;
                            } else {
                                costBudgetVO.setMaintainBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("CashMaintenanceFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 保养费（现金保养费）
                            costBudgetVO.setUpkeepBudget(trialTaskCostBudgetVO.getTotal());
                            if (StringUtils.isNotBlank(costBudgetVO.getUpkeepBudget())) {
                                Double total5 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getUpkeepBudget());
                                costBudgetVO.setUpkeepBudget(total5.toString());
                                total += total5;
                            } else {
                                costBudgetVO.setUpkeepBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        // 获取试验费用预算中新增项目的试验费用预算
                        if ("PersonTransitionFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 人员过渡费
                            costBudgetVO.setPersonTransitionFee(trialTaskCostBudgetVO.getTotal());
                            if (StringUtils.isNotBlank(costBudgetVO.getPersonTransitionFee())) {
                                Double total6 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getPersonTransitionFee());
                                costBudgetVO.setPersonTransitionFee(total6.toString());
                                total += total6;
                            } else {
                                costBudgetVO.setPersonTransitionFee(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("HandlingChargesFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 装卸费
                            costBudgetVO.setHandlingChargesFee(trialTaskCostBudgetVO.getTotal());
                            if (StringUtils.isNotBlank(costBudgetVO.getHandlingChargesFee())) {
                                Double total7 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getHandlingChargesFee());
                                costBudgetVO.setHandlingChargesFee(total7.toString());
                                total += total7;
                            } else {
                                costBudgetVO.setHandlingChargesFee(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("ParkingRate".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 停车费
                            costBudgetVO.setParkingRate(trialTaskCostBudgetVO.getTotal());
                            if (StringUtils.isNotBlank(costBudgetVO.getParkingRate())) {
                                Double total8 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getParkingRate());
                                costBudgetVO.setParkingRate(total8.toString());
                                total += total8;
                            } else {
                                costBudgetVO.setParkingRate(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("TestFuelCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 试验场燃料费
                            costBudgetVO.setTestFuelCost(trialTaskCostBudgetVO.getTotal());
                            if (StringUtils.isNotBlank(costBudgetVO.getTestFuelCost())) {
                                Double total9 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getTestFuelCost());
                                costBudgetVO.setTestFuelCost(total9.toString());
                                total += total9;
                            } else {
                                costBudgetVO.setTestFuelCost(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        // settingBudgetVO(costBudgetVO, trialTaskCostBudgetVO);
                    }
                }

                // 通过任务书ID获取 “PC_BUDGET_SUBSIDY   PC试验补贴预算”
                // 关联表 “PC_RELIABLE_INITTASK   PC试验可靠性立项单”
                // 查询 “名称code、名称、每公里补助标准、试验里程”
                List<PCBudgetSubsidyVO> trialBudgetSubsidyList = costStatisticsDao.getSubsidyByTaskId(vo.getId());
                if (CollectionUtils.isNotEmpty(trialBudgetSubsidyList)) {
                    for (PCBudgetSubsidyVO pCBudgetSubsidyVO : trialBudgetSubsidyList) {
                        // 微丘高速
                        if ("HillockHigh".equals(pCBudgetSubsidyVO.getItemsCode())) {
                            Double StdPrice = Double.parseDouble(pCBudgetSubsidyVO.getStdPrice());
                            Double TrialKm = Double.parseDouble(pCBudgetSubsidyVO.getTrialKm());
                            Double multiply = StdPrice * TrialKm;
                            String result = Double.toString(multiply);
                            costBudgetVO.setHillockHigh(result);
                            if (StringUtils.isNotBlank(costBudgetVO.getHillockHigh())) {
                                Double total10 = Double.parseDouble(costBudgetVO.getHillockHigh());
                                costBudgetVO.setHillockHigh(total10.toString());
                                total += total10;
                            }
                        }

                        // 山区重丘高速
                        if ("MountainousHilly".equals(pCBudgetSubsidyVO.getItemsCode())) {
                            Double StdPrice = Double.parseDouble(pCBudgetSubsidyVO.getStdPrice());
                            Double TrialKm = Double.parseDouble(pCBudgetSubsidyVO.getTrialKm());
                            Double multiply = StdPrice * TrialKm;
                            String result = Double.toString(multiply);
                            costBudgetVO.setMountainousHilly(result);
                            if (StringUtils.isNotBlank(costBudgetVO.getMountainousHilly())) {
                                Double total11 = Double.parseDouble(costBudgetVO.getMountainousHilly());
                                costBudgetVO.setMountainousHilly(total11.toString());
                                total += total11;
                            }
                        }
                    }
                }

                // 通过任务书ID获取费用报销
                // 关联表 “PC_BUDGET_REIMBURSEMENT    费用报销申请单”
                // 查询 “PC_TRIAL_PRODUCT 产品试验报销明细” 所有字段
                List<PcTrialProductVO> reimbursement = costStatisticsDao.getReimbursement(vo.getId()); // size = 16
                if (CollectionUtils.isNotEmpty(reimbursement)) {
                    // 快递费
                    String expressTotal = reimbursement.get(reimbursement.size() - 1).getExpressTotal();
                    costBudgetVO.setExpressBudget(expressTotal);
                    // 过路过桥费
                    String passWayTotal = reimbursement.get(reimbursement.size() - 1).getPassWayTotal();
                    costBudgetVO.setPassWayBudget(passWayTotal);
                    // 租赁费
                    String hireTotal = reimbursement.get(reimbursement.size() - 1).getHireTotal();
                    costBudgetVO.setHireBudget(hireTotal);
                    // 其他
                    String pcOtherTotal = reimbursement.get(reimbursement.size() - 1).getPcOtherTotal();
                    costBudgetVO.setPcOtherBudget(pcOtherTotal);

                    if(expressTotal != null && passWayTotal != null && hireTotal != null && pcOtherTotal != null){
                        total = total + Double.parseDouble(expressTotal) + Double.parseDouble(passWayTotal) +
                                Double.parseDouble(hireTotal) + Double.parseDouble(pcOtherTotal);
                    }
                }

                // 总计
                costBudgetVO.setTotalBudget(total.toString());
                costBudgetVOS.add(costBudgetVO);
            }
        }
        return costBudgetVOS;
    }

    /**
     * 费用预算统计（对公）查询
     *
     * @param page
     * @return
     */
    public List<CostBudgetVO> budgetPublic(CostStatisticsPage page) {
        // 查询任务书和费用预算信息
        List<TrialTaskCostBudgetVO> vos = costStatisticsDao.getTrialTaskBudget(page);
        Integer rowCount = costStatisticsDao.getTrialTaskBudgetCount(page);
        page.getPager().setRowCount(rowCount);

        List<CostBudgetVO> costBudgetVOS = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(vos)) {
            for (TrialTaskCostBudgetVO vo : vos) {
                CostBudgetVO costBudgetVO = new CostBudgetVO();
                // 任务书编号
                costBudgetVO.setTrialTaskCode(vo.getTrialTaskCode());
                // 申请人
                costBudgetVO.setApplyUser(vo.getApplyUser());
                // 申请时间
                costBudgetVO.setApplyTime(vo.getApplyTime());
                // 底盘号
                costBudgetVO.setUnderpanNo(costStatisticsDao.selectSampleByTrialId(vo.getId()));
                // 初始化总计
                Double total = 0.0;

                // 通过任务书ID获取费用预算
                List<TrialTaskCostBudgetVO> trialTaskCostBudgetVOList = costStatisticsDao.getBudgetByTaskId(vo.getId());
                if (CollectionUtils.isNotEmpty(trialTaskCostBudgetVOList)) {
                    for (TrialTaskCostBudgetVO trialTaskCostBudgetVO : trialTaskCostBudgetVOList) {
                        if ("ServiceCharge".equals(trialTaskCostBudgetVO.getItemsCode())
                                || "ChargeForPublicCharging".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 加油、充电、加气（对公加油费、对公充电费）
                            if (StringUtils.isNotBlank(costBudgetVO.getRefuelChargeAirEntrappingBudget())) {
                                Double total1 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getRefuelChargeAirEntrappingBudget());
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(total1.toString());
                                total += total1;
                            } else {
                                costBudgetVO.setRefuelChargeAirEntrappingBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("CorporateRepairFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 维修费（对公维修费）
                            if (StringUtils.isNotBlank(costBudgetVO.getMaintainBudget())) {
                                Double total2 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getMaintainBudget());
                                costBudgetVO.setMaintainBudget(total2.toString());
                                total += total2;
                            } else {
                                costBudgetVO.setMaintainBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("CorporateMaintenanceFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 保养费（对公保养费） --- 费用预算统计表格中没有此字段
                            if (StringUtils.isNotBlank(costBudgetVO.getUpkeepBudget())) {
                                Double total3 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getUpkeepBudget());
                                costBudgetVO.setUpkeepBudget(total3.toString());
                                total += total3;
                            } else {
                                costBudgetVO.setUpkeepBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("HainanTestSiteCost".equals(trialTaskCostBudgetVO.getItemsCode())
                                || "XiangyangTestSiteCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 场地费（海南试验场费用、襄阳试验场费用）
                            if (StringUtils.isNotBlank(costBudgetVO.getPlaceBudget())) {
                                Double total4 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getPlaceBudget());
                                costBudgetVO.setPlaceBudget(total4.toString());
                                total += total4;
                            } else {
                                costBudgetVO.setPlaceBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("OutsourcingFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 委外试验费（委外费）
                            if (StringUtils.isNotBlank(costBudgetVO.getOutSourceBudget())) {
                                Double total5 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getOutSourceBudget());
                                costBudgetVO.setOutSourceBudget(total5.toString());
                                total += total5;
                            } else {
                                costBudgetVO.setOutSourceBudget(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        // 获取试验费用预算中新增项目的试验费用预算
                        if ("PubTrailerRentalCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 挂车租赁费用
                            if (StringUtils.isNotBlank(costBudgetVO.getPubTrailerRentalCost())) {
                                Double total6 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getPubTrailerRentalCost());
                                costBudgetVO.setPubTrailerRentalCost(total6.toString());
                                total += total6;
                            } else {
                                costBudgetVO.setPubTrailerRentalCost(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("RoadServiceOutsource".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 路送劳务外包费
                            if (StringUtils.isNotBlank(costBudgetVO.getRoadServiceOutsource())) {
                                Double total7 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getRoadServiceOutsource());
                                costBudgetVO.setRoadServiceOutsource(total7.toString());
                                total += total7;
                            } else {
                                costBudgetVO.setRoadServiceOutsource(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("RoadTestLaborOutsource".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 路试劳务外包费
                            if (StringUtils.isNotBlank(costBudgetVO.getRoadTestLaborOutsource())) {
                                Double total8 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getRoadTestLaborOutsource());
                                costBudgetVO.setRoadTestLaborOutsource(total8.toString());
                                total += total8;
                            } else {
                                costBudgetVO.setRoadTestLaborOutsource(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }

                        if ("OtherTestSiteCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
                            // 其他试验场场地费
                            if (StringUtils.isNotBlank(costBudgetVO.getOtherTestSiteCost())) {
                                Double total9 = Double.parseDouble(trialTaskCostBudgetVO.getTotal()) +
                                        Double.parseDouble(costBudgetVO.getOtherTestSiteCost());
                                costBudgetVO.setOtherTestSiteCost(total9.toString());
                                total += total9;
                            } else {
                                costBudgetVO.setOtherTestSiteCost(trialTaskCostBudgetVO.getTotal());
                                total += Double.parseDouble(trialTaskCostBudgetVO.getTotal());
                            }
                        }
                        // settingBudgetVO(costBudgetVO, trialTaskCostBudgetVO);
                    }
                }

                // 通过任务书ID获取费用报销
                List<PcTrialProductVO> reimbursement = costStatisticsDao.getReimbursement(vo.getId());
                // 快递费/物流费费
                if (CollectionUtils.isNotEmpty(reimbursement)) {
                    String expressTotal = reimbursement.get(reimbursement.size() - 1).getExpressTotal();
                    costBudgetVO.setExpressBudget(expressTotal);

                    if(expressTotal != null){
                        total = total + Double.parseDouble(expressTotal);
                    }
                }

                // 总计
                costBudgetVO.setTotalBudget(total.toString());
                costBudgetVOS.add(costBudgetVO);
            }
        }
        return costBudgetVOS;
    }

    private void settingBudgetVO(CostBudgetVO costBudgetVO, TrialTaskCostBudgetVO trialTaskCostBudgetVO) {
        if ("TransitionFee".equals(trialTaskCostBudgetVO.getItemsCode())) {
            // 过渡费
            costBudgetVO.setTransitionFee(trialTaskCostBudgetVO.getTotal());
        }
        if ("InsuranceCost".equals(trialTaskCostBudgetVO.getItemsCode())) {
            // 保险费用
            costBudgetVO.setInsuranceCost(trialTaskCostBudgetVO.getTotal());
        }
        if ("TemporaryCard".equals(trialTaskCostBudgetVO.getItemsCode())) {
            // 临牌费用
            costBudgetVO.setTemporaryCard(trialTaskCostBudgetVO.getTotal());
        }
        if ("HighSpeedToll".equals(trialTaskCostBudgetVO.getItemsCode())) {
            // 高速收费
            costBudgetVO.setHighSpeedToll(trialTaskCostBudgetVO.getTotal());
        }
        if ("RiskSubsidy".equals(trialTaskCostBudgetVO.getItemsCode())) {
            // 风险补贴
            costBudgetVO.setRiskSubsidy(trialTaskCostBudgetVO.getTotal());
        }
    }

    public void postSettlePublic(String s, PcBudgetSettleStatusEO pcBudgetSettleStatusEO) {
        String[] split = pcBudgetSettleStatusEO.getTrialId().split(",");
        for (int i = 0; i < split.length; i++) {
            pcBudgetSettleStatusEO.setId(UUID.randomUUID10());
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            String userId = UserUtils.getUserId();
            pcBudgetSettleStatusEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            pcBudgetSettleStatusEO.setCreateTime(date);
            pcBudgetSettleStatusEO.setCreateBy(userId);
            pcBudgetSettleStatusEO.setUpdateTime(date);
            pcBudgetSettleStatusEO.setUpdateBy(userId);
            pcBudgetSettleStatusEO.setStatus("0");
            pcBudgetSettleStatusEO.setTrialId(split[i]);
            pcBudgetSettleStatusEO.setType(s);
            costStatisticsDao.insertSettleStatus(pcBudgetSettleStatusEO);
        }
    }

    public String getStatusByTrialId(String s, String id) {
        return costStatisticsDao.getStatusByTrialId(s, id);
    }
}
