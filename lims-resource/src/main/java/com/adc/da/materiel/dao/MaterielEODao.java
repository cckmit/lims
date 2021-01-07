package com.adc.da.materiel.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.materiel.entity.MaterielEO;
import com.adc.da.materiel.page.MaterielEOPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;



/**
  *物料管理dao
 */
public interface MaterielEODao extends BaseDao<MaterielEO> {
    /**
     * 查询出入库的List
     * @param page
     * @return
     */
    List<MaterielEO> getInventoryByPage(MaterielEOPage page);

    /**
     * 查询出入库的列表总数
     * @param page
     * @return
     */
    Integer queryForCount(MaterielEOPage page);

    /**
     * 查询出出库列表总数
     * @param page
     * @return
     */
    Integer queryForOutCount(MaterielEOPage page);

    /**
     * 查询出，出库List
     * @return
     */
    List<MaterielEO> getOutventoryByPage(MaterielEOPage page);

    /**
     * 通过materielCode查询出对应的实体列表
     * @param materielCode
     * @return
     */
    List<MaterielEO> getMaterielListByCode(String materielCode);

    /**
     *
     * 通过id（主键），查询出materielEo实体
     * @param id
     * @return
     */
    MaterielEO selectByPrimaryKey (String id);

    /**
     * 通过ids数组，批量删除物料
     * @param ids
     * @return
     */
    void deleteByPrimaryKeyArray(String[] ids);
    /**
     * 验证物料编码数 据库是否存在
     * @param materielCode
     */
    Integer checkNum( @Param("code") String materielCode,@Param("id") String id);
    /**
     * 查询物料的原始的样件数量
     * @param id
     */
    Double queryInventorySurpius(String id);
    /**
     * 查询物料的原始样件数量
     * @param id
     */
    MaterielEO SampleAndStock(String id);
    /**
     * 物料报废
     * @param id
     */
    int scrapByPrimaryKey(String id);
    /**
     * 查询所有未删除的物料的有效期以及生产日期
     */
    List<MaterielEO> queryValidity();
    /**
     * 查询所有今天超期的物料
     * @return
     */
    List<MaterielEO> dueOnMateriel();
}
