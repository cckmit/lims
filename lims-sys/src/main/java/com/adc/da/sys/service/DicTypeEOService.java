package com.adc.da.sys.service;

import java.util.List;

import com.adc.da.sys.dao.DicEODao;
import com.adc.da.sys.entity.DictionaryEO;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.DicTypeEODao;
import com.adc.da.sys.entity.DicTypeEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.UUID;

/**
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * 数据字典类型Service层相关
 * date 2018-08-16
 *
 * @author comments created by Lee Kwanho
 * @see DicTypeEODao
 */
@Service("dicTypeEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class DicTypeEOService extends BaseService<DicTypeEO, String> {
    /**
     * Dao层相关注册
     *
     * @see DicTypeEODao
     */
    @Autowired
    private DicTypeEODao dicTypeEODao;

    public DicTypeEODao getDao() {
        return dicTypeEODao;
    }

    @Autowired
    private DicEODao dicEODao;

    /**
     * 新增字典类型，新增随机数，设置删除标识符为0。
     *
     * @param dicTypeEO 字典类型信息
     * @return 字典类型信息
     */
    public DicTypeEO save(DicTypeEO dicTypeEO) {
        dicTypeEO.setId(UUID.randomUUID10());
        dicTypeEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        dicTypeEODao.insert(dicTypeEO);
        return dicTypeEO;
    }

    /**
     * 查询字典详情
     *
     * @param id 字典类型id
     * @return 字典详情
     */
    @Transactional(readOnly = true)
    public DicTypeEO getDicTypeById(String id) {
        return dicTypeEODao.getDicTypeEOById(id);
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 字典类型组
     */

    /**
     * @param ids
     * @return
     * @author 作者信息
     * date 2018-08-21
     **/
    public void delete(List<String> ids) {
        dicTypeEODao.deleteDicTypeByIdInBatch(ids);
    }

    public DicTypeEO getDicTypeEOByCode(String dicTypeCode) {
        return dicTypeEODao.getDicTypeEOByCode(dicTypeCode);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据数据字典code查询数据字典详细类型列表
     * @Date 10:59 2019/8/2
     * @Param
     * @return
     **/
    public List<DicTypeEO> getDicTypeByDictionaryCode(String dictionaryCode){
        DictionaryEO dictionaryEO = dicEODao.getDictionaryEOByDicCode(dictionaryCode);
        String dicId = dictionaryEO.getId();
        return dicTypeEODao.getDicTypeByDicId(dicId);
    }
    
    
    public DicTypeEO getDicTypeByDicCodeAndDicTypeId(String dictionaryCode,
    		String dicTypeCode) {
    	return  dicTypeEODao.getDicTypeByDicCodeAndDicTypeId(dictionaryCode, dicTypeCode);
    }
}
