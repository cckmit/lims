package com.adc.da.part.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.part.dao.SaPartDepotDAO;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.part.entity.SaPartDepotEO;
import com.adc.da.part.page.SaPartDepotEOPage;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.BeanMapper;
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
 * @date ：Created in 2019/7/9 16:24
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaPartDepotService extends BaseService<SaPartDepotEO, String> {

    @Autowired
    private SaPartDepotDAO saPartDepotDAO;

    @Autowired
    private BeanMapper beanMapper;

    @Override
    public SaPartDepotDAO getDao() {
        return saPartDepotDAO;
    }

    /**
     * 保存零部件库房
     *
     * @param saPartDepotEO
     * @return
     */
    public SaPartDepotEO save(SaPartDepotEO saPartDepotEO) {
        saPartDepotEO.setId(UUID.randomUUID10());
        saPartDepotEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        saPartDepotEO.setCreateTime(date);
        saPartDepotEO.setCreateBy(userId);
        saPartDepotEO.setUpdateBy(userId);
        saPartDepotEO.setUpdateTime(date);
        // 是否是库房（0，库房；1，车位）
        saPartDepotEO.setPartSpaceStatus(0);
        saPartDepotDAO.insertSelective(saPartDepotEO);
        return saPartDepotEO;
    }

    /**
     * 获取详情
     *
     * @param id
     * @param i
     * @return
     */
    public SaPartDepotEO selectByParam(String id, int i) {
        return saPartDepotDAO.selectByParam(id, i);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        saPartDepotDAO.deleteByParentId(id, userId, date);
        saPartDepotDAO.deleteById(id, userId, date);
    }

    /**
     * 新增校验库房编号是否存在
     *
     * @param depotNumber
     * @return
     */
    public List<SaPartDepotEO> queryByDepotNuhmber(String depotNumber) {
        return saPartDepotDAO.queryByDepotNuhmber(depotNumber);
    }

    /**
     * 更新零部件库房
     *
     * @param saPartDepotEO
     */
    public void edit(SaPartDepotEO saPartDepotEO) {
        saPartDepotEO.setUpdateBy(UserUtils.getUserId());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        saPartDepotEO.setUpdateTime(date);
        saPartDepotDAO.updateByPrimaryKeySelective(saPartDepotEO);
    }

    /**
     * 新增库位
     *
     * @param saPartDepotEO
     * @return
     */
    public SaPartDepotEO saveSpace(SaPartDepotEO saPartDepotEO) {
        saPartDepotEO.setId(UUID.randomUUID10());
        saPartDepotEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String userId = UserUtils.getUserId();
        saPartDepotEO.setCreateBy(userId);
        saPartDepotEO.setUpdateBy(userId);
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        saPartDepotEO.setCreateTime(date);
        saPartDepotEO.setUpdateTime(date);
        // 是否是库位（0，库房；1，库位）
        saPartDepotEO.setPartSpaceStatus(1);
        // 剩余库位数
        saPartDepotEO.setUnUsePartSpaceNumber(saPartDepotEO.getLayerNumber());
        saPartDepotDAO.insertSelective(saPartDepotEO);
        return saPartDepotEO;
    }

    /**
     * 编辑库位
     *
     * @param saPartDepotEO
     */
    public void editSpace(SaPartDepotEO saPartDepotEO) {
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        saPartDepotEO.setUpdateTime(date);
        saPartDepotEO.setUpdateBy(UserUtils.getUserId());
        saPartDepotDAO.updateByPrimaryKeySelective(saPartDepotEO);
    }

    /**
     * 分页查询库位
     *
     * @param page
     * @return
     */
    public List<SaPartDepotEO> getPartSpacePage(SaPartDepotEOPage page) {
        return saPartDepotDAO.getPartSpacePage(page);
    }

    /**
     * 校验货架号是否存在
     *
     * @param shelfNumber
     * @return
     */
    public List<SaPartDepotEO> queryByShelfNumber(Integer shelfNumber) {
        return saPartDepotDAO.queryByShelfNumber(shelfNumber);
    }

    /**
     * 校验货架号是否存在
     *
     * @param shelfNumber
     * @param partDepotId
     * @return
     */
    public List<SaPartDepotEO> queryByShelfNumberAndPart(Integer shelfNumber,String partDepotId) {
        return saPartDepotDAO.queryByShelfNumberAndPart(shelfNumber,partDepotId);
    }

    /**
     * 通过id查询子级的剩余库位数
     *
     * @param id
     * @return
     */
    public Integer queryUnUsePartSpaceNumber(String id) {
        return saPartDepotDAO.queryUnUsePartSpaceNumber(id);
    }

    /**
     * 获取库位状态
     *
     * @param partDepotId
     * @return
     */
    public List<SaPartDepotEO> getPartSpaceStatus(String partDepotId) {
        return saPartDepotDAO.getPartSpaceStatus(partDepotId);
    }

    /**
     * 取消之前选择的库位
     *
     * @param saPartDataEO
     */
    public void cancelBeforeUsedCarSpace(SaPartDataEO saPartDataEO) {
        if (StringUtils.isNotBlank(saPartDataEO.getPartDepotId())) {
            SaPartDepotEO saPartDepotEO = saPartDepotDAO.selectByParam(saPartDataEO.getPartDepotId(), 1);
            if (StringUtils.isNotBlank(saPartDepotEO.getUsedPartSpace())) {
                String usedCarSpace = saPartDepotEO.getUsedPartSpace().replace(
                        saPartDataEO.getPartSpaceNumber().toString(), "");
                saPartDepotEO.setUsedPartSpace(
                        usedCarSpace.indexOf(",,") != -1 ? usedCarSpace.replace(",,", ",") : usedCarSpace);
                saPartDepotEO.setUnUsePartSpaceNumber(saPartDepotEO.getUnUsePartSpaceNumber() + 1);
                saPartDepotDAO.updateByPrimaryKeySelective(saPartDepotEO);
            }
        }
    }

    /**
     * 保存选择的库位
     *
     * @param dataEO
     */
    public void editUsedCarSpace(SaPartDataEO dataEO) {
        if (StringUtils.isNotBlank(dataEO.getPartDepotId())) {
            SaPartDepotEO saPartDepotEO = saPartDepotDAO.selectByParam(dataEO.getPartDepotId(), 1);
            if (saPartDepotEO != null) {
                if (StringUtils.isNotBlank(saPartDepotEO.getUsedPartSpace())) {
                    saPartDepotEO.setUsedPartSpace(
                            saPartDepotEO.getUsedPartSpace() + "," + dataEO.getPartSpaceNumber());
                } else {
                    saPartDepotEO.setUsedPartSpace(dataEO.getPartSpaceNumber().toString());
                }
                saPartDepotEO.setUnUsePartSpaceNumber(saPartDepotEO.getUnUsePartSpaceNumber() - 1);
                saPartDepotDAO.updateByPrimaryKeySelective(saPartDepotEO);
            }
        }
    }

    /**
     * 通过停车场编号和行号查询
     *
     * @param parkName
     * @param rowNumber
     * @return
     */
    public SaPartDataEO selectByParkNameAndRowNumber(String parkName, String rowNumber) {
        return saPartDepotDAO.selectByParkNameAndRowNumber(parkName, rowNumber);
    }

    /**
     * 判断库房是否被使用
     * @param partDepotId
     * @return
     */
    public boolean judgePartIsUsed(String partDepotId){
        //是为true  否为false
        boolean flag = false;
        List<SaPartDepotEO> saPartDataEOList = saPartDepotDAO.getPart(partDepotId);
        for(int i = 0;i < saPartDataEOList.size();i++){
            if(saPartDataEOList.get(i).getUsedPartSpace() != null && !saPartDataEOList.get(i).getUsedPartSpace().isEmpty()){
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 判断库位是否被使用
     * @return
     */
    public boolean judgePartSpaceIsUsed(String partDepotId,Integer areaNumber,Integer rowNumber,Integer shelfNumber){
        //是为true  否为false
        boolean flag = false;
        SaPartDepotEO saPartDepotEO = saPartDepotDAO.getPartSpace(partDepotId, areaNumber, rowNumber, shelfNumber);
        if(saPartDepotEO.getUsedPartSpace() != null && !saPartDepotEO.getUsedPartSpace().isEmpty()){
            flag = true;
        }
        return flag;
    }

}
