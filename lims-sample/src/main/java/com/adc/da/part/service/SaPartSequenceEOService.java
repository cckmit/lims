package com.adc.da.part.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.adc.da.part.entity.SaPartSequenceEO;
import com.adc.da.part.dao.SaPartSequenceDAO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author     ：fengzhiwei
 * @date       ：Created in 2019/8/5 15:20
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaPartSequenceEOService{

    @Resource
    private SaPartSequenceDAO saPartSequenceDAO;

    
    public int insertSelective(SaPartSequenceEO record) {
        return saPartSequenceDAO.insertSelective(record);
    }

    
    public SaPartSequenceEO selectByPrimaryKey(String id) {
        return saPartSequenceDAO.selectByPrimaryKey(id);
    }

    
    public int updateByPrimaryKeySelective(SaPartSequenceEO record) {
        return saPartSequenceDAO.updateByPrimaryKeySelective(record);
    }

    /**
     * 通过零部件ID查询序列
     * @param id
     * @return
     */
    public List<SaPartSequenceEO> selectByPartDataId(String id) {
        return saPartSequenceDAO.selectByPartDataId(id);
    }

    /**
     * 通过零部件ID和状态查询序列
     * @param id
     * @return
     */
    public List<SaPartSequenceEO> selectByPartDataIdAndStatus(String id , Integer partStatus) {
        return saPartSequenceDAO.selectByPartDataIdAndStatus(id,partStatus);
    }

    public void deleteById(String id) {
        saPartSequenceDAO.deleteById(id);
    }

    /**
     * 查询当前序列号状态的个数
     * @param partId
     * @param status
     * @return
     */
    public int selectByStatus(String partId,Integer status){
        return saPartSequenceDAO.selectByStatus(partId,status);
    }
}
