package com.adc.da.car.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.car.entity.TrialtaskSample;
import com.adc.da.car.page.SaCarBrrowEOPage;
import com.adc.da.car.page.SaCarDataEOPage;
import com.adc.da.car.page.SaCarReturnEOPage;
import com.adc.da.car.vo.CarBarcodeVO;
import com.adc.da.car.vo.SaCarDataListVO;
import com.adc.da.car.vo.TrialSampleVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:10
 * @description：${description}
 */
public interface SaCarDataDAO extends BaseDao<SaCarDataEO> {

    /**
     * 导出二维码
     *
     * @param id
     * @param carStatus
     * @return
     */
    List<CarBarcodeVO> selectCarBarCode(@Param("id") String id, @Param("carStatus") Integer carStatus);

    /**
     * 查询要导出的数据
     *
     * @param page
     * @return
     */
    List<SaCarDataEO> queryListForExcel(SaCarDataEOPage page);

    /**
     * 通过发动机号查询样车是否存在
     *
     * @param carEngineNo
     * @return
     */
    SaCarDataEO selectByCarEngineNo(@Param("carEngineNo") String carEngineNo);

    List<SaCarDataListVO> selectByPage(SaCarDataEOPage eoPage);

    /**
     * 通过停车场ID和车位号查询详情
     *
     * @param depotId
     * @param carSpaceNumber
     * @return
     */
    SaCarDataEO getOneByDepotId(@Param("depotId") String depotId, @Param("carSpaceNumber") String carSpaceNumber);

    /**
     * 发动机试验任务执行-点击“样品信息”查询
     *
     * @param id
     * @Title：page向老司机致敬
     */
    List<SaCarDataListVO> selectSampleListById(@Param("id") String id);

    TrialSampleVO selectByTrialApplyNo(@Param("trialApplyNO") String trialApplyNO);

    void insertTrialtaskSample(TrialtaskSample sampleEO);

    void changeStatus(@Param("id") String id, @Param("status") String status);
    /**
     * 借车单数据列表查询
     * @param page
     * @return
     */

	List<SaCarBrrowEOPage> queryBrrowCarByPage(SaCarBrrowEOPage page);
	
	/**
	 * 查询借用总数
	 * @param page
	 * @return
	 */
	Integer queryBrrowByCount(SaCarBrrowEOPage page);

	/**
     * 还车单数据列表查询
     * @param page
     * @return
     */
	List<SaCarReturnEOPage> queryReturnCarByPage(SaCarReturnEOPage page);

	/**
	 * 还车单数据总数查询
	 * @param page
	 * @return
	 */

	Integer queryReturnByCount(SaCarReturnEOPage page);

    /**
     * 根据发动机编号或者底盘号查询整车
     */
    List<SaCarDataEO> selectByNo(@Param("carEngineNo") String carEngineNo,@Param("chassisNumber") String chassisNumber);

    /**
	 * 校验底盘号唯一
	 * @Title: checkNo
	 * @param id
	 * @param chassisNumber
	 * @return
	 * @return boolean
	 * @author: ljy
	 * @date: 2020年4月21日
	 */
    public List<String> checkNo(@Param("id") String id);
    
    public SaCarDataEO selectByChassisNumber(String chassisNumber);

    public List<SaCarDataEO> selectCarByInfo(@Param("chassisNumber") String chassisNumber,@Param("createByDepart") String createByDepart);

	/**
	 * 格局任务id集合查询激活的人员
	 * @return
	 */
	List<String> selectByKeyArray(String[] userName);
	/**
	 *
	 * @return
	 */
	List<SaCarDataEO> getCarByIds(Map<String, Object> params);
}