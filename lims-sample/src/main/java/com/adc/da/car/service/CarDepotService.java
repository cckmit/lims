package com.adc.da.car.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.car.dao.CarDepotDAO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.car.entity.SaCarDepotEO;
import com.adc.da.car.page.CarDepotEOPage;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/8 11:41
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class CarDepotService extends BaseService<SaCarDepotEO, String> {

    @Autowired
    private CarDepotDAO carDepotDao;

    public CarDepotDAO getDao() {
        return carDepotDao;
    }

    /**
     * 保存整车库房信息
     *
     * @param carDepotEO
     * @return
     */
    public SaCarDepotEO save(SaCarDepotEO carDepotEO) {
        carDepotEO.setId(UUID.randomUUID10());
        carDepotEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        carDepotEO.setCreateTime(date);
        carDepotEO.setCreateBy(userId);
        carDepotEO.setUpdateTime(date);
        carDepotEO.setUpdateBy(userId);
        // 是否是库房（0，库房；1，车位）
        carDepotEO.setCarSpaceStatus(0);
        carDepotDao.insertSelective(carDepotEO);
        return carDepotEO;
    }

    /**
     * 保存整车库房信息
     *
     * @param saCarDepotEO
     */
    public void edit(SaCarDepotEO saCarDepotEO) {
        saCarDepotEO.setUpdateBy(UserUtils.getUserId());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        saCarDepotEO.setUpdateTime(date);
        carDepotDao.updateByPrimaryKeySelective(saCarDepotEO);
    }

    /**
     * 校验库房编号是否存在
     *
     * @param depotNumber
     * @return
     */
    public List<SaCarDepotEO> queryByDepotNuhmber(String depotNumber) {
        return carDepotDao.queryByDepotNuhmber(depotNumber);
    }

    /**
     * 保存车位信息
     *
     * @param carDepotEO
     * @return
     */
    public SaCarDepotEO saveSpace(SaCarDepotEO carDepotEO) {
        carDepotEO.setId(UUID.randomUUID10());
        carDepotEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        carDepotEO.setCreateTime(date);
        carDepotEO.setCreateBy(UserUtils.getUserId());
        // 是否是库房（0，库房；1，车位）
        carDepotEO.setCarSpaceStatus(1);
        // 剩余车位数
        carDepotEO.setUnUseCarSpaceNumber(carDepotEO.getCarSpaceNumber());
        carDepotDao.insertSelective(carDepotEO);
        return carDepotEO;
    }

    /**
     * 分页查询车位
     *
     * @param page
     * @return
     */
    public List<SaCarDepotEO> getCarSpacePage(CarDepotEOPage page) {
        Integer rowCount = carDepotDao.getCarSpaceByCount(page);
        page.getPager().setRowCount(rowCount);
        return carDepotDao.getCarSpacePage(page);
    }

    /**
     * 新增车位校验行号是否存在
     *
     * @param rowNumber
     * @return
     */
    public List<SaCarDepotEO> queryByRowNumber(String carDepotId,Integer rowNumber) {
        return carDepotDao.queryByRowNumber(carDepotId,rowNumber);
    }

    /**
     * 通过ID获取详情
     *
     * @param id
     * @param i
     * @return
     */
    public SaCarDepotEO selectByParam(String id, int i) {
        return carDepotDao.selectByParam(id, i);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        carDepotDao.deletePartByParentId(id, userId, date);
        carDepotDao.deleteById(id, userId, date);
    }

    /**
     * 通过库房ID查询库房下未使用的车位
     *
     * @param id
     * @return
     */
    public Integer queryUnUseCarSpaceNumber(String id) {
        return carDepotDao.queryUnUseCarSpaceNumber(id);
    }

    /**
     * 获取库位状态
     *
     * @param carDepotId
     * @return
     */
    public List<SaCarDepotEO> getPartSpaceStatus(String carDepotId) {
        return carDepotDao.getPartSpaceStatus(carDepotId);
    }

    /**
     * 保存编辑之后的存放位置
     *
     * @param saCarDataEO
     */
    public void editUsedCarSpace(SaCarDataEO saCarDataEO) {
        if (StringUtils.isNotBlank(saCarDataEO.getCarDepotId())) {
            SaCarDepotEO saCarDepotEO = carDepotDao.selectByParam(saCarDataEO.getCarDepotId(), 1);
            if (saCarDepotEO != null) {
                if (StringUtils.isNotBlank(saCarDepotEO.getUsedCarSpace())) {
                    saCarDepotEO.setUsedCarSpace(
                            saCarDepotEO.getUsedCarSpace() + "," + saCarDataEO.getCarSpaceNumber());
                } else {
                    saCarDepotEO.setUsedCarSpace(saCarDataEO.getCarSpaceNumber().toString());
                }
                saCarDepotEO.setUnUseCarSpaceNumber(saCarDepotEO.getUnUseCarSpaceNumber() - 1);
                carDepotDao.updateByPrimaryKeySelective(saCarDepotEO);
            }
        }
    }

    /**
     * 取消之前的存放位置
     *
     * @param saCarDataEO
     */
    public void cancelBeforeUsedCarSpace(SaCarDataEO saCarDataEO) {
        if (StringUtils.isNotBlank(saCarDataEO.getCarDepotId())) {
            SaCarDepotEO saCarDepotEO1 = carDepotDao.selectByParam(saCarDataEO.getCarDepotId(), 1);
            // 判断之前是否选择了存放位置
            if (saCarDepotEO1 != null && StringUtils.isNotBlank(saCarDepotEO1.getUsedCarSpace())) {
                String usedCarSpace = saCarDepotEO1.getUsedCarSpace().replace(
                        saCarDataEO.getCarSpaceNumber().toString(), "");
                saCarDepotEO1.setUsedCarSpace(
                        usedCarSpace.indexOf(",,") != -1 ? usedCarSpace.replace(",,", ",") : usedCarSpace);
                saCarDepotEO1.setUnUseCarSpaceNumber(saCarDepotEO1.getUnUseCarSpaceNumber() + 1);
                carDepotDao.updateByPrimaryKeySelective(saCarDepotEO1);
            }
        }
    }

    /**
     * 通过停车场编号和行号获取停车场id
     *
     * @param parkName
     * @param rowNumber
     * @return
     */
    public SaCarDepotEO selectByParkNameAndRowNumber(String parkName, String rowNumber) {
        return carDepotDao.selectByParkNameAndRowNumber(parkName, rowNumber);
    }
}
