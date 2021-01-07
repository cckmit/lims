package com.adc.da.sys.service;

import com.adc.da.base.page.BasePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.DicEODao;
import com.adc.da.sys.entity.DictionaryEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.UUID;

import java.util.List;
import java.util.Map;

/**
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * 数据字典Service层相关
 * date 2018-08-16

 *
 * @author comments created by Lee Kwanho
 * @see DicEODao
 */
@Service("dicEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class DicEOService extends BaseService<DictionaryEO, String> {

    /**
     * dao层相关注册
     *
     * @see DicEODao
     */
    @Autowired
    private DicEODao dicEODao;

    public DicEODao getDao() {
        return dicEODao;
    }

    /**
     * 新增字典信息，生成uuid
     *
     * @param dicEO 字典信息
     * @return 新增信息
     */
    public DictionaryEO save(DictionaryEO dicEO) {
        dicEO.setId(UUID.randomUUID10());
        dicEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        dicEODao.insert(dicEO);
        return dicEO;
    }

    /**
     * 查询字典详情，用字典id
     *
     * @param id 字典id
     * @return 字典信息
     */
    @Transactional(readOnly = true)
    public DictionaryEO getDictionaryById(String id) {
        return dicEODao.getDictionaryEOById(id);
    }

    /**
     * 查询字典详情，用字典编码
     *
     * @param dictionaryCode 字典编码
     * @return 字典信息
     */
    @Transactional(readOnly = true)
    public DictionaryEO getDictionaryByDicCode(String dictionaryCode) {
        return dicEODao.getDictionaryEOByDicCode(dictionaryCode);
    }

    /**
     * 查询字典，用字典名
     *
     * @param dictionaryName 字典名
     * @return 字典信息
     */
    @Transactional(readOnly = true)
    public DictionaryEO getDictionaryByDicName(String dictionaryName) {
        return dicEODao.getDictionaryEOByDicName(dictionaryName);
    }

    /**
     * 删除字典,以及字典类型
     *
     * @param id 字典id
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        dicEODao.deleteDic(id);
        dicEODao.deleteDicAndDicType(id);
    }

    /**
     * 通用查询数据字典信息并且进行分页
     * @param basePage
     * @return
     */
    @Transactional(readOnly = true)
    public List<DictionaryEO> getDictionaryEOBySearchPhrase(BasePage basePage){
        Integer rowsCount = dicEODao.getDictionaryEOCountBySearchPhrase(basePage);
        basePage.getPager().setRowCount(rowsCount);
        List<DictionaryEO> list = dicEODao.getDictionaryEOBySearchPhrase(basePage);
        return list;
    }
    
    /**
     * 通用查询时输入关键字查询关联数据字典的列（如：设备状态），将类型中包含该关键字的类型code全部查出来，方便后续的查询<br/>
     * 例如：输入“常”，查询设备状态列（可选项有：正常、异常、停用、超期未检），此方法会得到“正常”、“异常”的类型code，从而后续查询时将会查询出设备状态为“正常”或“异常”的记录
     * @param dicCode
     * @param search
     * @return
     */
    public List<String> getTypeCodesBySearch(String dicCode, String search){
		return dicEODao.getTypeCodesBySearch(dicCode, search);
    }
}
