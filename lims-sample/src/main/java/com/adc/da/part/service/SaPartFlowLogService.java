package com.adc.da.part.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.login.util.UserUtils;
import com.adc.da.part.dao.SaPartFlowLogDAO;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.part.entity.SaPartFlowLogEO;
import com.adc.da.part.entity.SaPartSequenceEO;
import com.adc.da.part.vo.SaPartFlowLogVO;
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

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/29 16:01
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaPartFlowLogService extends BaseService<SaPartFlowLogEO, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaPartFlowLogService.class);

    @Resource
    private SaPartFlowLogDAO saPartFlowLogDAO;

    @Autowired
    private SaPartDataEOService saPartDataEOService;

    @Autowired
    private SaPartSequenceEOService saPartSequenceEOService;

    @Autowired
    private SaPartDepotService saPartDepotService;

    @Override
    public SaPartFlowLogDAO getDao() {
        return saPartFlowLogDAO;
    }

    /**
     * 通过序列ID删除日志
     *
     * @param id
     */
    public void deleteBySequenceId(String id) {
        saPartFlowLogDAO.deleteBySequenceId(id);
    }

    /**
     * 收样
     *
     * @param partId
     * @param receivUserId
     * @param receivTime
     * @param partSequences
     * @param remarks
     * @param attachmentId
     */
    public void addReceivedPart(String partId, String receivUserId, String receivTime, List<String> partSequences,
                                String remarks, String attachmentId) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 保存流转日志
                    SaPartFlowLogEO saPartFlowLogEO = new SaPartFlowLogEO();
                    saPartFlowLogEO.setId(UUID.randomUUID10());
                    saPartFlowLogEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                    String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    saPartFlowLogEO.setCreateTime(date);
                    saPartFlowLogEO.setCreateBy(UserUtils.getUserId());
                    saPartFlowLogEO.setPartSequenceId(partSequence);
                    saPartFlowLogEO.setLeaderId(receivUserId);
                    saPartFlowLogEO.setOperationDate(receivTime);
                    saPartFlowLogEO.setOperationContent("收样");
                    saPartFlowLogEO.setRemarks(remarks);
                    saPartFlowLogDAO.insertSelective(saPartFlowLogEO);
                    sequence.setStatus(0);
                    saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
        try {
            //查询当前所有序列号的状态，如全部是接收状态便修改零部件状态为接收
            int i = saPartSequenceEOService.selectByStatus(partId, 0);
            if (i == saPartSequenceEOList.size()){
                SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
                saPartDataEO.setStatus(0);
                saPartDataEO.setPartStatus("0");
                saPartDataEO.setReceivePartAttachmentId(attachmentId);
                saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 领样
     * @param partSequences
     * @param partId
     */
    public void addcollectPart(SaPartFlowLogEO logEO, List<String> partSequences, String partId) {
        //todo 查询试验委托编号是否存在，如果存在添加关联关系

        try {
            // 查询零部件信息
            SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
            // 设置领样人
            saPartDataEO.setGetPartUserId(logEO.getLeaderId());
            // 编辑库位信息
            saPartDataEO.setInShelf(saPartDataEO.getInShelf() - partSequences.size()<0?0:saPartDataEO.getInShelf() - partSequences.size());
            editSpace(saPartDataEO);
            // 查询所有的零部件序列号
            List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
            int index = 1;
            for (SaPartSequenceEO sequence : saPartSequenceEOList) {
                for (String partSequence : partSequences) {
                    if (partSequence.equals(sequence.getSampleSequence())) {
                        String partEngineNO = StringUtils.isNotEmpty(saPartDataEO.getPartEngineNo()) ?
                                saPartDataEO.getPartEngineNo() : "";
                        // 为序列号添加零部件号/发动机号
                        //sequence.setSampleSequence(partEngineNO + partSequence);
                        sequence.setStatus(1);
                        saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                        // 添加流转日志
                        logEO.setPartSequenceId(sequence.getId());
                        logEO.setId(UUID.randomUUID10());
                        saPartFlowLogDAO.insertSelective(logEO);

                        if (index == partSequences.size()) {
                            break;
                        }
                        index++;
                    }
                }
            }
            //查询领样状态的零部件序列号个数
            int i = saPartSequenceEOService.selectByStatus(partId, 1);
            if (i == saPartSequenceEOList.size()){
                saPartDataEO.setStatus(1);
                saPartDataEO.setPartStatus("0");
            }
            saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 流转
     *
     * @param partId
     * @param sendPartUserId
     * @param getPartUserId
     * @param remarks
     * @param exchangeTime
     * @param partStatus
     * @param partSequences
     */
    public void addFlowPart(String partId, String sendPartUserId, String getPartUserId, String remarks,
                            String exchangeTime, String partStatus, List<String> partSequences) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 添加流转日志
                    SaPartFlowLogEO logEO = new SaPartFlowLogEO();
                    logEO.setId(UUID.randomUUID10());
                    logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                    String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    logEO.setCreateTime(date);
                    logEO.setCreateBy(UserUtils.getUserId());
                    logEO.setPartSequenceId(sequence.getId());
                    logEO.setLeaderId(getPartUserId);
                    logEO.setOperationDate(exchangeTime);
                    logEO.setOperationContent("流转");
                    logEO.setOperatorId(sendPartUserId);
                    logEO.setRemarks(remarks);
                    StringBuilder sb = new StringBuilder();
                    sb.append("样品状态：").append(partStatus);
                    logEO.setOthers(sb.toString());
                    saPartFlowLogDAO.insertSelective(logEO);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
    }

    /**
     * 归还
     * @param partId
     * @param partSequences
     * @param partSpaceId
     * @param partSpaceNumber
     * @param partSpaceLocation
     */
    public void addReturnPart(String partId, SaPartFlowLogEO logEO, List<String> partSequences, String partSpaceId,
                              Integer partSpaceNumber, String partSpaceLocation) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 添加流转日志
                    logEO.setPartSequenceId(sequence.getId());
                    logEO.setId(UUID.randomUUID10());
                    saPartFlowLogDAO.insertSelective(logEO);
                    sequence.setStatus(2);
                    saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
        try {
            // 保存零部件库房信息
            SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
            //查询当前在库状态的零部件序列号
            int i = saPartSequenceEOService.selectByStatus(partId, 2);
            if (i == saPartSequenceEOList.size()){
                saPartDataEO.setStatus(2);
                saPartDataEO.setPartStatus("2");
            }
            // 设置在架数量
            saPartDataEO.setInShelf(saPartDataEO.getInShelf() + partSequences.size());
            if (saPartDataEO.getInShelf() == 0) {
                saPartDataEO.setPartDepotId(partSpaceId);
                saPartDataEO.setPartSpaceNumber(partSpaceNumber);
                saPartDataEO.setPartSpaceLocation(partSpaceLocation);
                saPartDepotService.editUsedCarSpace(saPartDataEO);
            }
            saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

    }

    /**
     * 退样
     *
     * @param partId
     * @param partSequences
     * @param attachmentId
     */
    public void addBackPart(String partId, List<String> partSequences, String attachmentId, SaPartFlowLogEO logEO) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 添加流转日志
                    logEO.setPartSequenceId(sequence.getId());
                    logEO.setId(UUID.randomUUID10());
                    saPartFlowLogDAO.insertSelective(logEO);
                    sequence.setStatus(3);
                    saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
        try {
            // 保存零部件库房信息
            SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
            //获取当前状态为退样状态的零部件序列号个数
            int i = saPartSequenceEOService.selectByStatus(partId, 3);
            if (i == saPartSequenceEOList.size()){
                saPartDataEO.setStatus(3);
                saPartDataEO.setPartStatus("3");
            }
            // 设置在架数量
            saPartDataEO.setInShelf(saPartDataEO.getInShelf() - partSequences.size()<0?0:saPartDataEO.getInShelf() - partSequences.size());
            saPartDataEO.setReportAssessAttachmentId(attachmentId);
            editSpace(saPartDataEO);
            saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * 报废
     *
     * @param partId
     * @param operatorId
     * @param scrapTime
     * @param processNO
     * @param remarks
     * @param partSequences
     */
    public void addScrapPart(String partId, String operatorId, String scrapTime, String processNO, String remarks,
                             List<String> partSequences) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 添加流转日志
                    SaPartFlowLogEO logEO = new SaPartFlowLogEO();
                    logEO.setId(UUID.randomUUID10());
                    logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                    String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    logEO.setCreateTime(date);
                    logEO.setCreateBy(UserUtils.getUserId());
                    logEO.setPartSequenceId(sequence.getId());
                    logEO.setOperationDate(scrapTime);
                    logEO.setOperationContent("报废");
                    logEO.setOperatorId(operatorId);
                    logEO.setRemarks(remarks);
                    StringBuilder sb = new StringBuilder();
                    sb.append("流程号：").append(processNO);
                    logEO.setOthers(sb.toString());
                    saPartFlowLogDAO.insertSelective(logEO);

                    sequence.setStatus(4);
                    saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
        try {
            // 保存零部件库房信息
            SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
            //查询当前状态为报废状态的零部件序列号个数
            int i = saPartSequenceEOService.selectByStatus(partId, 4);
            if (i == saPartSequenceEOList.size()){
                saPartDataEO.setStatus(4);
                saPartDataEO.setPartStatus("4");
            }
            // 设置在架数量
            saPartDataEO.setInShelf(saPartDataEO.getInShelf() - partSequences.size()<0?0:saPartDataEO.getInShelf() - partSequences.size());
            editSpace(saPartDataEO);
            saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void editSpace(SaPartDataEO saPartDataEO) {
        if (saPartDataEO.getInShelf() == 0) {
            saPartDepotService.cancelBeforeUsedCarSpace(saPartDataEO);
            saPartDataEO.setPartDepotId(null);
            saPartDataEO.setPartSpaceNumber(null);
            saPartDataEO.setPartSpaceLocation(null);
        }
    }

    /**
     * 封存
     *
     * @param partId
     * @param operatorId
     * @param remarks
     * @param exchangeTime
     * @param processNO
     * @param partSequences
     */
    public void addArchivePart(String partId, String operatorId, String remarks, String exchangeTime, String processNO,
                               List<String> partSequences) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 添加流转日志
                    SaPartFlowLogEO logEO = new SaPartFlowLogEO();
                    logEO.setId(UUID.randomUUID10());
                    logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                    String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    logEO.setCreateTime(date);
                    logEO.setCreateBy(UserUtils.getUserId());
                    logEO.setPartSequenceId(sequence.getId());
                    logEO.setOperationDate(exchangeTime);
                    logEO.setOperationContent("封存");
                    logEO.setOperatorId(operatorId);
                    logEO.setRemarks(remarks);
                    StringBuilder sb = new StringBuilder();
                    sb.append("流程号：").append(processNO);
                    logEO.setOthers(sb.toString());
                    saPartFlowLogDAO.insertSelective(logEO);
                    sequence.setStatus(5);
                    saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
        try {
            SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
            //查询封存状态的序列号
            int i = saPartSequenceEOService.selectByStatus(partId, 5);
            if (i == saPartSequenceEOList.size()){
                saPartDataEO.setPartStatus("5");
                saPartDataEO.setStatus(5);
                saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拆机
     *
     * @param partId
     * @param operatorId
     * @param remarks
     * @param exchangeTime
     * @param processNO
     * @param partSequences
     */
    public void addUnpackPart(String partId, String operatorId, String remarks, String exchangeTime, String processNO,
                              List<String> partSequences) {
        // 查询所有的零部件序列号
        List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(partId);
        int index = 1;
        for (SaPartSequenceEO sequence : saPartSequenceEOList) {
            for (String partSequence : partSequences) {
                if (partSequence.equals(sequence.getSampleSequence())) {
                    // 添加流转日志
                    SaPartFlowLogEO logEO = new SaPartFlowLogEO();
                    logEO.setId(UUID.randomUUID10());
                    logEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                    String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
                    logEO.setCreateTime(date);
                    logEO.setCreateBy(UserUtils.getUserId());
                    logEO.setPartSequenceId(sequence.getId());
                    logEO.setOperationDate(exchangeTime);
                    logEO.setOperationContent("拆机");
                    logEO.setOperatorId(operatorId);
                    logEO.setRemarks(remarks);
                    StringBuilder sb = new StringBuilder();
                    sb.append("流程号：").append(processNO);
                    logEO.setOthers(sb.toString());
                    saPartFlowLogDAO.insertSelective(logEO);
                    sequence.setStatus(6);
                    saPartSequenceEOService.updateByPrimaryKeySelective(sequence);
                    if (index == partSequences.size()) {
                        break;
                    }
                    index++;
                }
            }
        }
        try {
            SaPartDataEO saPartDataEO = saPartDataEOService.selectByPrimaryKey(partId);
            //查询拆机状态的序列号
            int i = saPartSequenceEOService.selectByStatus(partId, 6);
            if (i == saPartSequenceEOList.size()){
                saPartDataEO.setPartStatus("6");
                saPartDataEO.setStatus(6);
                saPartDataEOService.updateByPrimaryKeySelective(saPartDataEO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询退样打印单
     * @param id
     * @return
     */
    public List<SaPartFlowLogVO> selectByPartId(String id, String label) {
        return this.getDao().selectByPartId(id, label);
    }
}
