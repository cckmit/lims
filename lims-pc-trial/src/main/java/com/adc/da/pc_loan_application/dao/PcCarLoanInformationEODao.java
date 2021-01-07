package com.adc.da.pc_loan_application.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.pc_loan_application.entity.PcCarLoanInformationEO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>PC_CAR_LOAN_INFORMATION PcCarLoanInformationEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-26 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface PcCarLoanInformationEODao extends BaseDao<PcCarLoanInformationEO> {
    /*根据样车id变更样车状态*/
    int updCarStatusById(String id);

    int deleteLoanInformation(String ApplicationID);

    List<PcCarLoanInformationEO> findAllEOByLoanID(@Param("pcLoadId") String pcLoadId);


}
