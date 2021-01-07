package com.adc.da.pc_loan_application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.pc_loan_application.dao.PcCarLoanInformationEODao;
import com.adc.da.pc_loan_application.entity.PcCarLoanInformationEO;

import java.util.List;


/**
 *
 * <br>
 * <b>功能：</b>PC_CAR_LOAN_INFORMATION PcCarLoanInformationEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("pcCarLoanInformationEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PcCarLoanInformationEOService extends BaseService<PcCarLoanInformationEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PcCarLoanInformationEOService.class);

    @Autowired
    private PcCarLoanInformationEODao dao;

    public PcCarLoanInformationEODao getDao() {
        return dao;
    }


    /**
     * 插入数据
     * @param list
     */
    public void insertPcCarLoadInformationEO(List<PcCarLoanInformationEO> list) {
        //改变整车状态
        changeSaCarDataStatus(list);
        if (list.size() != 0) {
            //先删除所有数据
            dao.deleteLoanInformation(list.get(0).getLoadApplicationId());
            for (PcCarLoanInformationEO pcCarLoanInformationEO : list) {
                try {
                    dao.insertSelective(pcCarLoanInformationEO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 改变整车状态
     * @param list
     */
    public void changeSaCarDataStatus(List<PcCarLoanInformationEO> list) {
        if (list.size() != 0) {
            for (PcCarLoanInformationEO pcCarLoanInformationEO : list) {
                dao.updCarStatusById(pcCarLoanInformationEO.getSaCarDataId());
            }
        }
    }

    /**
     * 根据主表id删除数据
     */
    public void deleteLoanInformation(String loadApplicationId) {
        dao.deleteLoanInformation(loadApplicationId);
    }

    public List<PcCarLoanInformationEO> findAllEOByLoanID(String pcLoadId) {
        return dao.findAllEOByLoanID(pcLoadId);
    }
}
