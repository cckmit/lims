package com.adc.da.car.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.constant.SampleStatusEnum;
import com.adc.da.car.dao.SaCarFlowLogDAO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.car.entity.SaCarFlowLogEO;
import com.adc.da.car.vo.SaCarFlowLogVO;
import com.adc.da.login.util.UserUtils;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:10
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaCarFlowLogService extends BaseService<SaCarFlowLogEO, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaCarFlowLogService.class);

    @Autowired
    private SaCarFlowLogDAO saCarFlowLogDAO;

    @Autowired
    private SaCarDataService saCarDataService;

    @Autowired
    private CarDepotService carDepotService;

    @Override
    public SaCarFlowLogDAO getDao() {
        return saCarFlowLogDAO;
    }

    /**
     * 删除流转日志
     *
     * @param id
     */
    public void deleteByCarId(String id) {
        saCarFlowLogDAO.deleteByCarId(id);
    }

    /**
     * 保存流转日志
     *
     * @param saCarFlowLogEO
     */
    private void add(SaCarFlowLogEO saCarFlowLogEO) {
        saCarFlowLogEO.setId(UUID.randomUUID10());
        saCarFlowLogEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        saCarFlowLogEO.setCreateTime(date);
        saCarFlowLogEO.setCreateBy(UserUtils.getUserId());
        saCarFlowLogDAO.insertSelective(saCarFlowLogEO);
    }

    /**
     * 新增收样日志
     *
     * @param attachmentId
     */
    public void addReceivedCar(SaCarFlowLogEO saCarFlowLogEO, String attachmentId) {

        add(saCarFlowLogEO);
        try {
            SaCarDataEO saCarDataEO = saCarDataService.selectByPrimaryKey(saCarFlowLogEO.getCarDataId());
            saCarDataEO.setCarStatus(SampleStatusEnum.RECEIVED.getValue());
            saCarDataEO.setReceiveCarAttachmentId(attachmentId);
            saCarDataService.updateByPrimaryKeySelective(saCarDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 新增领样日志
     */
    public void addCollectCar(SaCarFlowLogEO saCarFlowLogEO) {

        add(saCarFlowLogEO);
        try {
            // 修改整车信息
            SaCarDataEO saCarDataEO = saCarDataService.selectByPrimaryKey(saCarFlowLogEO.getCarDataId());
            // 取消之前的存放位置
            carDepotService.cancelBeforeUsedCarSpace(saCarDataEO);
            saCarDataEO.setCarStatus(SampleStatusEnum.COLLECT.getValue());
            // 保存领样人id
            saCarDataEO.setGetCarUserId(saCarFlowLogEO.getLeaderId());
            // 将整车停车信息置空
            saCarDataEO.setCarDepotId(null);
            saCarDataEO.setCarSpaceNumber(null);
            saCarDataService.updateByPrimaryKeySelective(saCarDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        //todo 判断试验委托编号是否存在
        if (StringUtils.isNotBlank(saCarFlowLogEO.getTrialApplyNo())) {
            //todo 存在保存关联关系
        }

    }

    /**
     * 新增流转日志
     *
     * @param carId
     * @param sendCarUserId
     * @param getCarUserId
     * @param remarks
     * @param exchangeTime
     * @param carStatus
     */
    public void addFlowCar(String carId, String sendCarUserId, String getCarUserId, String remarks, String exchangeTime,
                           String carStatus) {
        SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
        saCarFlowLogEO.setCarDataId(carId);
        saCarFlowLogEO.setLeaderId(getCarUserId);
        saCarFlowLogEO.setOperationContent("流转");
        saCarFlowLogEO.setOperatorId(sendCarUserId);
        saCarFlowLogEO.setOperationDate(exchangeTime);
        saCarFlowLogEO.setRemarks(remarks);
        StringBuilder sb = new StringBuilder();
        sb.append("样品状态：").append(carStatus);
        saCarFlowLogEO.setOthers(sb.toString());
        add(saCarFlowLogEO);
    }

    /**
     * 新增归还日志
     *
     * @param carSpaceId
     * @param carSpaceNO
     */
    public void addReturnCar(SaCarFlowLogEO saCarFlowLogEO, String carSpaceId, Integer carSpaceNO) {

        add(saCarFlowLogEO);
        try {
            // 修改整车信息
            SaCarDataEO saCarDataEO = saCarDataService.selectByPrimaryKey(saCarFlowLogEO.getCarDataId());
            saCarDataEO.setCarStatus(SampleStatusEnum.RETURN.getValue());
            if (StringUtils.isNotBlank(carSpaceId)) {
                // 保存停车位信息
                saCarDataEO.setCarDepotId(carSpaceId);
                saCarDataEO.setCarSpaceNumber(carSpaceNO);
                // 保存库房中已使用的车位 USED_CAR_SPACE
                carDepotService.editUsedCarSpace(saCarDataEO);
            }
            saCarDataService.updateByPrimaryKeySelective(saCarDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 新增退样日志
     *
     * @param attachmentId
     */
    public void addBackCar(SaCarFlowLogEO saCarFlowLogEO, String attachmentId) {
        add(saCarFlowLogEO);
        try {
            // 修改整车信息
            SaCarDataEO saCarDataEO = saCarDataService.selectByPrimaryKey(saCarFlowLogEO.getCarDataId());
            // 取消之前的存放位置
            carDepotService.cancelBeforeUsedCarSpace(saCarDataEO);
            // 将整车停车信息置空
            saCarDataEO.setCarDepotId(null);
            saCarDataEO.setCarSpaceNumber(null);
            saCarDataEO.setCarSpaceLocation(null);
            saCarDataEO.setCarStatus(SampleStatusEnum.BACK.getValue());
            saCarDataEO.setReturnCarAttachmentId(attachmentId);
            saCarDataService.updateByPrimaryKeySelective(saCarDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 新增报废日志
     */
    public void addScrapCar(SaCarFlowLogEO saCarFlowLogEO) {

        add(saCarFlowLogEO);
        try {
            // 修改整车信息
            SaCarDataEO saCarDataEO = saCarDataService.selectByPrimaryKey(saCarFlowLogEO.getCarDataId());
            saCarDataEO.setCarStatus(SampleStatusEnum.SCRAP.getValue());
            saCarDataService.updateByPrimaryKeySelective(saCarDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 新增封存日志
     *
     * @param carSpaceId
     * @param carSpaceNO
     */
    public void addArchiveCar(SaCarFlowLogEO saCarFlowLogEO, String carSpaceId, Integer carSpaceNO) {

        add(saCarFlowLogEO);
        try {
            // 修改整车信息
            SaCarDataEO saCarDataEO = saCarDataService.selectByPrimaryKey(saCarFlowLogEO.getCarDataId());
            saCarDataEO.setCarStatus(SampleStatusEnum.ARCHIVE.getValue());
            // 保存停车位信息
            if (StringUtils.isNotBlank(carSpaceId) && carSpaceNO != null && carSpaceNO > 0) {
                saCarDataEO.setCarDepotId(carSpaceId);
                saCarDataEO.setCarSpaceNumber(carSpaceNO);
                // 保存库房中已使用的车位 USED_CAR_SPACE
                carDepotService.editUsedCarSpace(saCarDataEO);
            }
            saCarDataService.updateByPrimaryKeySelective(saCarDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 通过整车id获取退样日志
     * @param id
     * @param label
     * @return
     */
    public List<SaCarFlowLogVO> selectByCarId(String id, String label) {
        return this.getDao().selectByCarId(id, label);
    }
}
