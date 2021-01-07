package com.adc.da.part.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.part.page.SaPartDataEOPage;
import com.adc.da.part.vo.SaPartDataListVO;
import com.adc.da.part.vo.SaPartDataVO;
import com.adc.da.part.vo.SaPartsQRCodeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/29 15:59
 * @description：${description}
 */
public interface SaPartDataEODAO extends BaseDao<SaPartDataEO> {

    int deleteByPrimaryKey(@Param("id") String id);

    SaPartDataEO selectByPrimaryKey(@Param("id") String id);

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    SaPartDataVO getOne(@Param("id") String id);

    List<SaPartDataEO> selectList();

    SaPartDataEO selectByPartEngineNO(@Param("partEngineNO") String partEngineNO);

    /**
     * 分页查询
     *
     * @param eoPage
     * @return
     */
    List<SaPartDataListVO> selectByPage(SaPartDataEOPage eoPage);

    /**
     * 查询零部件导出数据
     *
     * @param eoPage
     * @return
     */
    List<SaPartDataVO> queryListForExcel(SaPartDataEOPage eoPage);

    /**
     * 变更零部件状态
     * @param id
     * @param status
     */
    void changeStatus(@Param("id") String id, @Param("status") Integer status);
    /**
     * 查询导出二维码信息
     *
     * @param id
     * @param status
     * @return
     */
    List<SaPartsQRCodeVO> barCode(@Param("id") String id, @Param("status") Integer status);

    /**
     * 通过库房ID和占用层数查询详情
     *
     * @param depotId
     * @param layerNumber
     * @return
     */
    SaPartDataVO getOneByDepotId(@Param("depotId") String depotId, @Param("layerNumber") Integer layerNumber);

    Integer getByCount(SaPartDataEOPage eoPage);

    /**
     * 分页查询
     *
     * @param eoPage
     * @return
     */
    List<SaPartDataListVO> getByPage(SaPartDataEOPage eoPage);
}