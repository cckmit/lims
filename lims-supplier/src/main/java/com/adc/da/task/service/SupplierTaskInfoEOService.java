package com.adc.da.task.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.task.dao.SupplierTaskInfoEODao;
import com.adc.da.task.entity.SupplierTaskInfoEO;
import com.adc.da.task.page.SupplierTaskStatisticsPage;
import com.adc.da.task.vo.SupplierTaskInfoVO;
import com.adc.da.task.vo.SupplierTaskStatisticsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * <br>
 * <b>功能：</b>SUP_SUPPLIER_TASK_INFO SupplierTaskInfoEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-19 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("supplierTaskInfoEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SupplierTaskInfoEOService extends BaseService<SupplierTaskInfoEO, String> {

    @Autowired
    private SupplierTaskInfoEODao dao;

    public SupplierTaskInfoEODao getDao() {
        return dao;
    }

    /**
     * 通过任务委托删除任务信息
     * @param id
     */
    public void deleteByApplyId(String id) {
        this.getDao().deleteByApplyId(id);
    }

    /**
     * 外包供应商任务执行|委托单的任务信息
     * @param id
     * @return
     */
    public SupplierTaskInfoVO getTaskInfo(String id) {
        return this.getDao().getTaskInfo(id);
    }

    /**
     * |外包供应商|任务统计分页查询
     * @param page
     * @return
     */
    public List<SupplierTaskStatisticsVO> getSupplierTaskStatisticsByPage(SupplierTaskStatisticsPage page) {
        Integer rowCount = this.getDao().getSupplierTaskStatisticsByCount(page);
        page.getPager().setRowCount(rowCount);
        return this.getDao().getSupplierTaskStatisticsByPage(page);
    }

    /**
     * |外包供应商|任务统计导出
     * @param page
     * @return
     */
    public List<SupplierTaskStatisticsVO> queryListForExcel(SupplierTaskStatisticsPage page) {
        return this.getDao().queryListForExcel(page);
    }

    /**
     * 更新状态
     * @param applyId
     */
    public void updateTaskInfoStatus(String applyId){
        dao.updateTaskInfoStatus(applyId);
    }


    /**
     * 外包供应商任务执行|根据applyNo查询对应的委托单任务信息
     * @param appNos
     * @return
     */
    public List<SupplierTaskStatisticsVO> getTaskInfoByApplyNo(SupplierTaskStatisticsPage page,List<String> appNos) {
        Map<String, Object> params = new HashMap<>();
        params.put("appNos",appNos);
        Integer rowCount = this.getDao().getTaskInfoByApplyNosCount(params);
        page.getPager().setRowCount(rowCount);
        params.put("page",page);
        return this.getDao().getTaskInfoByApplyNos(params);
    }
}
