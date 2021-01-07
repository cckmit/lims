package com.adc.da.car.service;

import com.adc.da.car.dao.SaCarTrialapplyInsprojectDAO;
import com.adc.da.car.vo.TrialtaskSampleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/7/15 9:10
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaCarTrialapplyInsprojectService{

    @Autowired
    private SaCarTrialapplyInsprojectDAO saCarTrialapplyInsprojectDAO;

    /**
     * 获取试验委托和整车编号
     * @param id carId
     * @return
     */
    public List<TrialtaskSampleVO> selectListByCarId(String id) {
        return saCarTrialapplyInsprojectDAO.selectListByCarId(id);
    }

    /**
     * 通过carID删除关联关系
     * @param id
     */
    public void deleteByCarId(String id) {
        saCarTrialapplyInsprojectDAO.deleteByCarId(id);
    }
}
