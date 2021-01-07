package com.adc.da.part.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.entity.TrialtaskSample;
import com.adc.da.car.vo.TrialSampleVO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.GenerateQrCode;
import com.adc.da.common.constant.SampleStatusEnum;
import com.adc.da.login.util.UserUtils;
import com.adc.da.part.dao.SaPartDataEODAO;
import com.adc.da.part.dto.SaPartDataDTO;
import com.adc.da.part.entity.SaPartDataEO;
import com.adc.da.part.entity.SaPartFlowLogEO;
import com.adc.da.part.entity.SaPartSequenceEO;
import com.adc.da.part.page.SaPartDataEOPage;
import com.adc.da.part.vo.SaBackPartDataVO;
import com.adc.da.part.vo.SaPartDataListVO;
import com.adc.da.part.vo.SaPartDataVO;
import com.adc.da.part.vo.SaPartFlowLogVO;
import com.adc.da.part.vo.SaPartsQRCodeVO;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.*;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.sys.service.ParamEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.DicTypeVO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/29 15:59
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaPartDataEOService extends BaseService<SaPartDataEO, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaPartDataEOService.class);

    @Autowired
    private SaPartDataEODAO saPartDataEODAO;

    @Autowired
    private SaPartDepotService saPartDepotService;

    @Autowired
    private SaPartFlowLogService saPartFlowLogService;

    @Autowired
    private SaPartSequenceEOService saPartSequenceEOService;

    @Autowired
    private SaPartTrialapplyInsprojectService saPartTrialapplyInsprojectService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private SaCarDataDAO saCarDataDAO;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private DicTypeEOService dicTypeEOService;

    @Autowired
    private ParamEOService paramEOService;

    @Value("${barCode.path}")
    private String barCodePath;

    @Override
    public SaPartDataEODAO getDao() {
        return saPartDataEODAO;
    }

    /**
     * 保存零部件
     *
     * @param saPartDataDTO
     */
    public void save(SaPartDataDTO saPartDataDTO) throws Exception {
        SaPartDataEO saPartDataEO = beanMapper.map(saPartDataDTO, SaPartDataEO.class);
        saPartDataEO.setId(UUID.randomUUID10());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        saPartDataEO.setCreateTime(date);
        saPartDataEO.setUpdateTime(date);
        String userId = UserUtils.getUserId();
        saPartDataEO.setCreateBy(userId);
        saPartDataEO.setSampleNO(saPartDataDTO.getPartNO());
        //saPartDataEO.setUpdateBy(userId);
        saPartDataEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        if (saPartDataEO.getStatus() == null) {
            saPartDataEO.setStatus(0);
        }
        saPartDataEO.setInShelf(saPartDataEO.getPartSampleNumber());
        saPartDataEODAO.insertSelective(saPartDataEO);
        // 添加库房信息
        if (StringUtils.isNotBlank(saPartDataEO.getPartDepotId())) {
            saPartDepotService.editUsedCarSpace(saPartDataEO);
        }
        //todo 校验试验委托是否存在，如果存在保存关联关系
        //TrialSampleVO trialApply = saCarDataDAO.selectByTrialApplyNo(saPartDataDTO.getTrialApplyNO());
        TrialSampleVO trialApply = null;
        //todo 存在保存关联关系
        if (trialApply != null) {   //需求变更后此逻辑作废
            TrialtaskSample sampleEO = new TrialtaskSample();
            sampleEO.setId(UUID.randomUUID10());
            // 任务id
            sampleEO.setTrialtaskId(trialApply.getId());
            // 样品ID
            sampleEO.setSampleId(saPartDataEO.getId());
            // 样品类型
            sampleEO.setSampleType("part");
            // 发动机型号
            sampleEO.setSampleEngineType(saPartDataEO.getPartEngineNo());
            // 样品编号
            String applyNo = getApplyNo(saPartDataDTO.getTrialApplyNO());
            sampleEO.setSampleNo(applyNo);
            saCarDataDAO.insertTrialtaskSample(sampleEO);
        }
        // 保存样品序列号，流转日志
        saveSequenceAndLog(saPartDataDTO, saPartDataEO, date, userId);

    }

    /**
     * 获取委托单编号接口
     *
     * @return
     */
    public String getApplyNo(String applyNo) {
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SA_PART_SAMPLE);
        String date = DateUtils.dateToString(new Date(), "yyyy");
        // 如果查不到流水号新增
        if (pipelineNumberEO == null) {
            PipelineNumberEO eo = new PipelineNumberEO();
            // 为了保证查询时的流水号，和保存委托时的流水号相同。未查询到流水号时返回0001，然后将当前流水号+1，保存数据库。
            eo.setTally(2);
            eo.setParticularYear(date);
            eo.setType(ConstantUtils.SA_PART_SAMPLE);
            pipelineNumberEODao.insertSelective(eo);
            return applyNo + date + "-0001";
        }
        // 如果当前年份不等于数据库中以保存的年份，重新开始
        if (!date.equals(pipelineNumberEO.getParticularYear())) {
            // 为了保证查询时的流水号，和保存委托时的流水号相同。年份不相等时重新计算流水号，返回0001，然后将当前流水号+1，保存数据库。
            pipelineNumberEO.setParticularYear(date);
            pipelineNumberEO.setTally(2);
            pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
            return applyNo + date + "-0001";
        }
        //最大流水号
        String num = pipelineNumberEO.getTally().toString();
        //自动补全4位
        StringBuilder sb = new StringBuilder(num);
        while (sb.length() < 4) {
            sb.insert(0, "0");
        }
        // 为了保证查询时的流水号，和保存委托时的流水号相同。查询返回数据库当前流水号，然后将当前流水号+1，保存数据库。
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
        return applyNo + date + "-" + sb.toString();
    }

    /**
     * 编辑零部件
     *
     * @param saPartDataDTO
     */
    public void edit(SaPartDataDTO saPartDataDTO) throws Exception {
        SaPartDataEO dataEO = saPartDataEODAO.selectByPrimaryKey(saPartDataDTO.getId());
        if (dataEO != null) {
            // 编辑库房信息
            if (StringUtils.isNotBlank(saPartDataDTO.getPartDepotId()) && isaBoolean(saPartDataDTO, dataEO)) {
                // 如果之前的存放位置和编辑后的存放位置不同，需要将之前的存放位置取消，并保存编辑之后的存放位置。
                // 取消之前的存放位置
                saPartDepotService.cancelBeforeUsedCarSpace(dataEO);
                dataEO.setPartDepotId(saPartDataDTO.getPartDepotId());
                dataEO.setPartSpaceNumber(saPartDataDTO.getPartSpaceNumber());
                // 保存库位信息
                saPartDepotService.editUsedCarSpace(dataEO);
            }
            SaPartDataEO saPartDataEO = beanMapper.map(saPartDataDTO, SaPartDataEO.class);
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            saPartDataEO.setUpdateTime(date);
            String userId = UserUtils.getUserId();
            saPartDataEO.setUpdateBy(userId);
            saPartDataEO.setSampleNO(saPartDataDTO.getPartNO());
            saPartDataEO.setInShelf(saPartDataEO.getPartSampleNumber());
            saPartDataEODAO.updateByPrimaryKeySelective(saPartDataEO);
            //判断样品数量是否被修改
            if(saPartDataDTO.getEditStatus().equals(0)){
                // 删除序列和日志
                List<SaPartSequenceEO> saPartSequenceEOList =
                        saPartSequenceEOService.selectByPartDataId(saPartDataEO.getId());
                if (saPartSequenceEOList != null && !saPartSequenceEOList.isEmpty()) {
                    for (SaPartSequenceEO eo : saPartSequenceEOList) {
                        // 删除日志
                        saPartFlowLogService.deleteBySequenceId(eo.getId());
                        // 删除序列
                        saPartSequenceEOService.deleteById(eo.getId());
                    }
                }
                // 保存样品序列号，流转日志
                saveSequenceAndLog(saPartDataDTO, saPartDataEO, date, userId);
            }
        }
    }

    public boolean isaBoolean(SaPartDataDTO saPartDataDTO, SaPartDataEO dataEO) {
        return !saPartDataDTO.getPartDepotId().equals(dataEO.getPartDepotId())
                || saPartDataDTO.getPartSpaceNumber() != dataEO.getPartSpaceNumber();
    }

    public void saveSequenceAndLog(SaPartDataDTO saPartDataDTO, SaPartDataEO saPartDataEO, String date, String userId)
            throws Exception {
        for (String sequence : saPartDataDTO.getSampleSequence()) {
            SaPartSequenceEO sequenceEO = new SaPartSequenceEO();
            sequenceEO.setId(UUID.randomUUID10());
            sequenceEO.setSampleSequence(sequence);
            sequenceEO.setPartId(saPartDataEO.getId());
            sequenceEO.setDel(DeleteFlagEnum.NORMAL.getValue());
            sequenceEO.setStatus(Integer.parseInt(saPartDataEO.getPartStatus()));
            saPartSequenceEOService.insertSelective(sequenceEO);
            if (saPartDataEO.getStatus() != null && 7 != saPartDataEO.getStatus()) {
                SaPartFlowLogEO saPartFlowLogEO = new SaPartFlowLogEO();
                saPartFlowLogEO.setId(UUID.randomUUID10());
                saPartFlowLogEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                saPartFlowLogEO.setCreateTime(date);
                saPartFlowLogEO.setCreateBy(userId);
                saPartFlowLogEO.setPartSequenceId(sequenceEO.getId());
                saPartFlowLogEO.setLeaderId(saPartDataEO.getReceivedUserId());
                saPartFlowLogEO.setOperationDate(date);
                saPartFlowLogEO.setOperationContent("收样");
                saPartFlowLogEO.setTrialApplyNo(saPartDataDTO.getTrialApplyNO());
                saPartFlowLogService.insertSelective(saPartFlowLogEO);
            }
        }
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    public SaPartDataVO getOne(String id) {
        SaPartDataVO saPartDataVO = saPartDataEODAO.getOne(id);
        settingUserInfo(saPartDataVO);
        return saPartDataVO;
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        // 查询零部件是否存在
        SaPartDataEO saPartDataEO = saPartDataEODAO.selectByPrimaryKey(id);
        if (saPartDataEO != null) {
            // 删除库位
            saPartDepotService.cancelBeforeUsedCarSpace(saPartDataEO);
            // 删除关联关系
            saPartTrialapplyInsprojectService.deteleByPartId(id);
            // 查询序列，如果序列存在删除，并删除流转日志
            List<SaPartSequenceEO> saPartSequenceEOList = saPartSequenceEOService.selectByPartDataId(id);
            if (saPartSequenceEOList != null && !saPartSequenceEOList.isEmpty()) {
                for (SaPartSequenceEO eo : saPartSequenceEOList) {
                    // 删除日志
                    saPartFlowLogService.deleteBySequenceId(eo.getId());
                    // 删除序列
                    saPartSequenceEOService.deleteById(eo.getId());
                }
            }
            // 删除零部件
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            saPartDataEO.setUpdateTime(date);
            saPartDataEO.setUpdateBy(UserUtils.getUserId());
            saPartDataEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
            saPartDataEODAO.updateByPrimaryKeySelective(saPartDataEO);
        }
    }

    /**
     * 导入
     *
     * @param success
     */
    public void saveForList(List<ArrayList<String>> success) {
        for (ArrayList<String> list : success) {
            // 如果用户没有填零部件号，系统自动生成
            String partEngineNO = "";
            if (StringUtils.isBlank(list.get(3))) {
                partEngineNO = generatePartNumber("VCT");
            }
            // 判断零部件号是否存在
            SaPartDataEO saPartDataEO = saPartDataEODAO.selectByPartEngineNO(partEngineNO);
            if (saPartDataEO == null) {
                SaPartDataEO eo = new SaPartDataEO();
                eo.setId(UUID.randomUUID10());
                eo.setCreateBy(UserUtils.getUserId());
                eo.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
                settingPartData(list, eo);

                saPartDataEODAO.insertSelective(eo);
            } else {
                // 创建时间
                settingPartData(list, saPartDataEO);
                saPartDataEODAO.updateByPrimaryKeySelective(saPartDataEO);
            }

        }
    }

    public void settingPartData(List<String> list, SaPartDataEO eo) {
/*        try {
            // 试验任务书编号
            eo.setTrialTaskBookNO(list.get(20));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }*/
/*        try {
            // 创建时间
            eo.setCreateTime(list.get(14));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 创建人
            eo.setCreateByName(list.get(13));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            eo.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            eo.setUpdateBy(UserUtils.getUserId());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }*/

/*        try {
            // 样品名称
            eo.setPartName(list.get(0));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 样品编号
            if (StringUtils.isNotBlank(list.get(21))) {
                eo.setPartSampleNumber(Integer.parseInt(list.get(21)));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        // 发动机号/零部件号
        eo.setPartEngineNo(list.get(3));
        try {
            // 样品阶段
            if (StringUtils.isNotBlank(list.get(7))) {
                eo.setPartStage(Integer.parseInt(list.get(7)));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 生产厂家
            eo.setProducedFactory(list.get(4));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 零部件状态
            eo.setPartStatus(list.get(15));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 接收时间
            eo.setReceivedTime(list.get(12));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 样品所在地
            eo.setPartLocation(list.get(17));

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 是否退样
            eo.setWhetherReturn(list.get(5));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 试验类型
            eo.setTrialType(list.get(16));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 所在位置
            getCarDepot(list, eo);
            if (StringUtils.isNotBlank(list.get(2))) {
                // 在架数量
                eo.setInShelf(Integer.parseInt(list.get(2)));
                // 样品数量
                eo.setPartSpaceNumber(Integer.parseInt(list.get(2)));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            //todo 状态
            if (StringUtils.isNotBlank(list.get(19))) {
                eo.setStatus(Integer.parseInt(list.get(19)));
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 送样人姓名
            eo.setSendPartUserName(list.get(8));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 送样人电话
            eo.setSendPartUserPhone(list.get(9));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 送样人部门
            eo.setSendPartUserOrg(list.get(10));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 接收人
            eo.setReceivedUserName(list.get(11));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        try {
            // 样品所在地外场管理责任人
            eo.setPartLocationManagerName(list.get(18));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }*/
        eo.setCreateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        eo.setCreateByName(UserUtils.getUserId());
        eo.setUpdateTime(DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        eo.setUpdateBy(UserUtils.getUserId());
        eo.setPartPlanNumber(list.get(0));    //样件计划单号
        eo.setPartName(list.get(1));    //样品名称
        eo.setPartSampleNumber(Integer.parseInt(list.get(2)));   //样品数量
        eo.setPartEngineNo(list.get(3));  //零部件号
        eo.setPartSpaceLocation(list.get(4));  //存放位置
        eo.setProducedFactory(list.get(5));    //生产厂家
        eo.setRequestArrivalTime(list.get(6));     //要求到货时间
        eo.setActualArrivalTime(list.get(7));       //实际到货时间
        if("否".equals(list.get(8)))  eo.setWhetherScrap(0);     //是否报废
        else if("是".equals(list.get(8))) eo.setWhetherScrap(1);
        eo.setScrapNumber(list.get(9));   //报废单号
        eo.setSettlementNumber(list.get(10));   //结算单号
        eo.setSettlementDate(list.get(11));    //结算日期


    }

    public void getCarDepot(List<String> list, SaPartDataEO eo) {
        // 判断存放位置填写是否规范（停车场x第a排b号位）
        String carSpaceLocation = list.get(11);
        String pattern = "^停车场.+第\\d+排\\d+号位$";
        boolean isMatch = Pattern.matches(pattern, carSpaceLocation);
        if (isMatch) {
            // 存放位置填写规范
            String parkName = carSpaceLocation.substring(3, carSpaceLocation.lastIndexOf('第'));
            String rowNumber = carSpaceLocation.substring(carSpaceLocation.lastIndexOf('第') + 1,
                    carSpaceLocation.lastIndexOf('排'));
            String number = carSpaceLocation.substring(carSpaceLocation.lastIndexOf('排') + 1,
                    carSpaceLocation.lastIndexOf("号位"));
            // 通过停车场编号和行号获取停车场id
            SaPartDataEO saPartDataEO = saPartDepotService.selectByParkNameAndRowNumber(parkName, rowNumber);
            eo.setPartDepotId(saPartDataEO.getId());
            eo.setPartSpaceNumber(Integer.parseInt(number));
        }
    }

    private String generatePartNumber(String vct) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        //获取所有零部件
        List<SaPartDataEO> saPartDataEOS = saPartDataEODAO.selectList();
        int max = 0;
        if (saPartDataEOS != null && !saPartDataEOS.isEmpty()) {
            //循环取序列号的最大值
            for (SaPartDataEO part : saPartDataEOS) {
                String pNumber = part.getPartEngineNo();
                if (StringUtils.isNotBlank(pNumber) && pNumber.contains(dateStr)) {
                    String serialStr = pNumber.substring(pNumber.lastIndexOf('-') + 1);
                    try {
                        int serialInt = Integer.parseInt(serialStr);
                        if (serialInt > max) {
                            max = serialInt;
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.error(e.getMessage());
                    }
                }
            }
        }
        DecimalFormat dFormat = new DecimalFormat("000");
        String serial = dFormat.format(max + 1L);
        return vct + "-" + dateStr + "-" + serial;
    }

    /**
     * 查询零部件导出数据
     *
     * @param eoPage
     * @return
     */
    public List<SaPartDataVO> queryListForExcel(SaPartDataEOPage eoPage) {
        return saPartDataEODAO.queryListForExcel(eoPage);
    }

    /**
     * 分页查询
     *
     * @param eoPage
     * @return
     */
    public List<SaPartDataListVO> selectByPage(SaPartDataEOPage eoPage) {
        Integer rowCount = saPartDataEODAO.queryByCount(eoPage);
        eoPage.getPager().setRowCount(rowCount);
        return saPartDataEODAO.selectByPage(eoPage);
    }

    /**
     * 查询生成二维码信息
     *
     * @param ids
     * @return
     */
    public List<SaPartsQRCodeVO> barCode(String[] ids) throws Exception {
        List<SaPartsQRCodeVO> saPartsQRCodeVOList = new ArrayList<>();
        for (String id : ids) {
            // 通过id查询零部件信息
            SaPartDataEO saPartDataEO = this.selectByPrimaryKey(id);
            if (saPartDataEO != null) {
                List<SaPartsQRCodeVO> saPartsQRCodeVO = this.getDao().barCode(id, saPartDataEO.getStatus());
                // 生成二维码
                try {
                    BufferedImage bufferedImage = GenerateQrCode.init().width(80).height(80).content(id).generate();
                    File file = new File(barCodePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    ImageIO.write(bufferedImage, "png",
                            new File(barCodePath + id.replace("/", "-") + ".png"));
                    saPartsQRCodeVO.get(0).setQrCode(barCodePath + id.replace("/", "-") + ".png");
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
                saPartsQRCodeVOList.add(saPartsQRCodeVO.get(0));
            }
        }
        return saPartsQRCodeVOList;
    }

    /**
     * 通过库房ID和占用层数查询详情
     *
     * @param depotId
     * @param layerNumber
     * @return
     */
    public SaPartDataVO getOneByDepotId(String depotId, Integer layerNumber) {
        SaPartDataVO saPartDataVO = saPartDataEODAO.getOneByDepotId(depotId, layerNumber);
        settingUserInfo(saPartDataVO);
        return saPartDataVO;
    }

    public void settingUserInfo(SaPartDataVO saPartDataVO) {
        if (saPartDataVO != null && StringUtils.isNotBlank(saPartDataVO.getSendPartUserId())) {
            UserEO userEO = userEOService.getUserWithRoles(saPartDataVO.getSendPartUserId());
            if (userEO != null && !userEO.getOrgEOList().isEmpty()) {
                StringBuilder sb = new StringBuilder();
                for (OrgEO orgEO : userEO.getOrgEOList()) {
                    sb.append(orgEO.getName()).append(",");
                }
                saPartDataVO.setSendPartUserPhone(userEO.getCellPhoneNumber());
                saPartDataVO.setSendPartUserOrg(sb.substring(0, sb.lastIndexOf(",")));
            }
        }
        //todo 获取附件名称
        StringBuilder sb = new StringBuilder();
        if (saPartDataVO != null && StringUtils.isNotBlank(saPartDataVO.getAssessAttachmentId())) {
            String[] split = saPartDataVO.getAssessAttachmentId().split(",");
            getAttachmentName(sb, split);
            if (StringUtils.isNotBlank(sb.toString())) {
                saPartDataVO.setAssessAttachmentName(sb.toString().substring(0, sb.length() - 1));
            }
        }
        if (saPartDataVO != null && StringUtils.isNotBlank(saPartDataVO.getReportAssessAttachmentId())) {
            sb.setLength(0);
            String[] split = saPartDataVO.getReportAssessAttachmentId().split(",");
            getAttachmentName(sb, split);
            if (StringUtils.isNotBlank(sb.toString())) {
                saPartDataVO.setReportAssessAttachmentName(sb.toString().substring(0, sb.length() - 1));
            }
        }

    }

    public void getAttachmentName(StringBuilder sb, String[] split) {
        for (int i = 0; i < split.length; i++) {
            TsAttachmentEO eo = tsAttachmentEODao.selectByPrimaryKey(split[i]);
            if (eo != null) {
                sb.append(eo.getAttachmentName()).append(",");
            }
        }
    }

    /**
     * 查询退样打印单
     *
     * @param id
     * @return
     */
    public SaBackPartDataVO backPart(String id) {
        SaPartDataVO one = this.getOne(id);
        SaBackPartDataVO map = beanMapper.map(one, SaBackPartDataVO.class);
        // 通过零部件ID查询退样日志
        List<SaPartFlowLogVO> flowLogVOS = saPartFlowLogService.selectByPartId(id, SampleStatusEnum.BACK.getLabel());
        if (flowLogVOS != null && !flowLogVOS.isEmpty()) {
            map.setBackPartUser(flowLogVOS.get(0).getLeaderName());
            map.setBackPartUserOrg(flowLogVOS.get(0).getLeaderOrg());
            map.setOperator(flowLogVOS.get(0).getOperatorName());
            map.setOperatorOrg(flowLogVOS.get(0).getOperatorOrg());
            map.setReceivTime(flowLogVOS.get(0).getOperationDate());
            map.setRemarks(flowLogVOS.get(0).getRemarks());
            String other = flowLogVOS.get(0).getOthers();
            if (StringUtils.isNotBlank(other)) {
                other = other.substring("报告结果确认：".length());
                if ("null".equals(other)){
                    other = "";
                }
                map.setReportResult(other);
            }
        }
        return map;
    }

    public List<DicTypeVO> getDicTypeByDictionaryCode(String dictionaryCode){
        List<DicTypeEO> resultList = dicTypeEOService.getDicTypeByDictionaryCode(dictionaryCode);
        List<DicTypeVO> dicTypeVOList = beanMapper.mapList(resultList, DicTypeVO.class);
        return dicTypeVOList;
    }

    public String statusValue(String code,String dicCode){
        String name = "";
        List<DicTypeVO> dicTypeVOList = getDicTypeByDictionaryCode(dicCode);
        for(int i = 0;i < dicTypeVOList.size();i++){
            if(code.equals(dicTypeVOList.get(i).getDicTypeCode())){
                name = dicTypeVOList.get(i).getDicTypeName();
            }
        }
        return name;
    }

    /**
     * 分页查询
     *
     * @param eoPage
     * @return
     */
    public List<SaPartDataListVO> partList(SaPartDataEOPage eoPage) {
        Integer rowCount = saPartDataEODAO.getByCount(eoPage);
        eoPage.getPager().setRowCount(rowCount);
        return saPartDataEODAO.getByPage(eoPage);
    }
    public String createNum(String paramValue,String trialTaskBookNO,String id){
        int i = Integer.parseInt(paramValue);
        //流水号
        String newSerialNumber = null;
        if (i < 10)
        {
            newSerialNumber = "00" + i;
        }
        else if (i >= 10 && i < 100)
        {
            newSerialNumber = "0" + i;
        }
        else
        {
            newSerialNumber = Integer.toString(i);
        }
        String num = trialTaskBookNO + newSerialNumber;
        int newNum = 0;
        if(999 == i ){
            newNum = 0;
        }else {
            newNum = i + 1;
        }
        String s = Integer.toString(newNum);
        paramEOService.updateParamValue(id,s);
        return num;
    }
}
