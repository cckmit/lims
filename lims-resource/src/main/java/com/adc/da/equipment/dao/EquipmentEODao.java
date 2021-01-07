package com.adc.da.equipment.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.equipment.VO.EqBarcodeVo;
import com.adc.da.equipment.VO.EquipmentVO;
import com.adc.da.equipment.entity.EquipmentEO;
import com.adc.da.equipment.entity.EquipmentPartsEO;
import com.adc.da.equipment.entity.EquipmentUseLogEO;
import com.adc.da.equipment.page.EquipmentEOPage;
import com.adc.da.sys.vo.UserVO;

/**
 *
 * <br>
 * <b>功能：</b>RES_EQUIPMENT EquipmentEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-11-27 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface EquipmentEODao extends BaseDao<EquipmentEO> {
    /**
     * 根据设备编号获取设备实体类
     * @parem code
     */
    List<EquipmentEO> selectByNo(String code);
    /**
     * 根据id获取对应设备的副设备id的list
     *
     * @param id
     */
    List<String> getViceEqList(String id);

	/**
	 * 分页查询
	 * @param page
	 */
	List<EquipmentVO> queryByPage(EquipmentEOPage page);
	
	/**
	 * 根据条件查询全部数据
	 * @param page
	 */
	List<EquipmentVO> queryByList(EquipmentEOPage page);

	/**
	 * 校验设备编号是否唯一
	 * @param param
	 * @param id
	 */
	EquipmentEO checkNo(@Param("param")String param, @Param("id")String id);
    /**
     * 根据设备id的list删除设备
     * @parem List
     */
    void deleteByIdS(@Param("idS")List<String> idS);
    
    /**
     * 新增设备配件
     * @param e
     */
    void addParts(EquipmentPartsEO e);
    
    /**
     * 查询设备的配件列表
     * @param id
     */
	List<EquipmentPartsEO> getPartsByEqId(String id);
	
	/**
	 * 批量删除设备配件
	 * @param partsList
	 */
	void deletePartsList(List<EquipmentPartsEO> partsList);
	
	/**
	 * 根据id获取所有子设备列表
	 * @param eqId
	 * @return
	 */
	public List<EquipmentVO> getChildrenByParentId(String eqId);

    /**
     * 根据设备id的list获取对应设备的核检记录
     * @param ids
     */
    List<EquipmentEO> getCheckList(String[] ids);

    /**
     * 根据id获取二维码设备信息
     * @param id
     */
    EquipmentEO selectEqBarCode(String id);

    /**
     * 新增设备使用记录
     * @param useLog
     */
	void addUseLog(EquipmentUseLogEO useLog);

	/**
	 * 更新设备使用记录
	 * @param ul
	 */
	void updateUseLog(EquipmentUseLogEO ul);

	/**
	 * 设备使用日志分页查询
	 * @param paramMap
	 * @return
	 */
	List<EquipmentUseLogEO> getUseLogPage(Map<String, Object> paramMap);
	

	/**
	 * 设备使用日志查询总数
	 * @param paramMap
	 * @return
	 */
	Integer getUseLogCount(Map<String, Object> paramMap);

    /**
     * 根据设备id的list获取对应设备的使用记录
     * @param ids
     */
    List<EquipmentEO> getUseLogList(String[] ids);
    
    /**
     * 新增、修改时选择管理人分页查询（选择范围为当前登录用户所属部门下的设备管理员）
     * @param paramMap
     * @return
     */
	List<UserVO> getManagerPage(Map<String, Object> paramMap);
	
	/**
	 * 新增、修改时选择管理人查询总数（选择范围为当前登录用户所属部门下的设备管理员）
	 * @param paramMap
	 * @return
	 */
	Integer getManagerCount(Map<String, Object> paramMap);

	/**
	 * 根据用户ID，来查出用户所属组织机构
	 * @param brwBorrowerId
	 * @return
	 */
    String getOrgNameByUserId(String brwBorrowerId);

	/**
	 * 查询今天超期未检的设备，修改其设备状态为超期未检
	 * @return
	 */
	public void changeStateOfOverdueEquipment();

	/**
	 * 获取最近的一条设备使用记录
	 * @param eqId
	 * @return
	 */
	EquipmentUseLogEO getLastUseLog(String eqId);

    /**
     * 获取所有设备的借用记录
     */
    List<EquipmentEO> getBorrowRecord();
}
