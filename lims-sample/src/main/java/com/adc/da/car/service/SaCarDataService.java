package com.adc.da.car.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.car.dao.SaCarDataDAO;
import com.adc.da.car.dto.SaCarDataDTO;
import com.adc.da.car.entity.SaCarDataEO;
import com.adc.da.car.entity.SaCarDepotEO;
import com.adc.da.car.entity.SaCarFlowLogEO;
import com.adc.da.car.entity.TrialtaskSample;
import com.adc.da.car.page.SaCarBrrowEOPage;
import com.adc.da.car.page.SaCarDataEOPage;
import com.adc.da.car.page.SaCarReturnEOPage;
import com.adc.da.car.vo.CarBarcodeVO;
import com.adc.da.car.vo.SaBackCarDataVO;
import com.adc.da.car.vo.SaCarDataListVO;
import com.adc.da.car.vo.SaCarDataVO;
import com.adc.da.car.vo.SaCarFlowLogVO;
import com.adc.da.car.vo.TrialtaskSampleVO;
import com.adc.da.car.vo.TrialSampleVO;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.GenerateQrCode;
import com.adc.da.common.constant.SampleStatusEnum;
import com.adc.da.common.constant.SampleWhetherUseEnum;
import com.adc.da.login.util.UserUtils;
import com.adc.da.project.entity.ProjectEO;
import com.adc.da.project.service.ProjectEOService;
import com.adc.da.sys.dao.PipelineNumberEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.PipelineNumberEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.adc.da.sys.service.UserEOService.getCellValueByCell;

/**
 * @author ：fengzhiwei
 * @date ：Created in 2019/7/15 9:10
 * @description：${description}
 */
@Service
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class SaCarDataService extends BaseService<SaCarDataEO, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaCarDataService.class);

    @Autowired
    private SaCarDataDAO saCarDataDAO;

    @Autowired
    private CarDepotService carDepotService;

    @Autowired
    private SaCarFlowLogService saCarFlowLogService;

    @Autowired
    private SaCarTrialapplyInsprojectService saCarTrialapplyInsprojectService;

    @Autowired
    private UserEOService userEOService;

    @Autowired
    private ProjectEOService projectEOService;

    @Autowired
    private PipelineNumberEODao pipelineNumberEODao;

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private UserEODao userEODao;


    @Value("${barCode.path}")
    private String barCodePath;
    @Override
    public SaCarDataDAO getDao() {
        return saCarDataDAO;
    }

    /**
     * 保存整车
     *
     * @param saCarDataDTO
     * @return
     */
    public SaCarDataEO save(SaCarDataDTO saCarDataDTO) throws Exception {
        SaCarDataEO saCarDataEO = beanMapper.map(saCarDataDTO, SaCarDataEO.class);
        // 保存整车| SA_CAR_DATA
        saCarDataEO.setId(UUID.randomUUID10());
        saCarDataEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        String userId = UserUtils.getUserId();
        saCarDataEO.setUpdateBy(userId);
        saCarDataEO.setUpdateTime(date);
        List<String> orgEOList = userEODao.getOrgIdListByUserId(userId);
        if (CollectionUtils.isNotEmpty(orgEOList)){
            String userDepartName = getUserDepartName(orgEOList.get(0));
            saCarDataEO.setCreateByDepart(userDepartName);
        }
        saCarDataDAO.insertSelective(saCarDataEO);
        // 修改整车库房车位占用情况 | SA_CAR_DEPOT
        if (carSpaceExists(saCarDataEO)) {
            carDepotService.editUsedCarSpace(saCarDataEO);
        }
        // todo 判断试验申请是否存在
        if (StringUtils.isNotBlank(saCarDataDTO.getTrialApplyNO())) {
            TrialSampleVO trialApply = saCarDataDAO.selectByTrialApplyNo(saCarDataDTO.getTrialApplyNO());
            //todo 存在保存关联关系
            if (trialApply != null) {
                TrialtaskSample sampleEO = new TrialtaskSample();
                sampleEO.setId(UUID.randomUUID10());
                // 任务id
                sampleEO.setTrialtaskId(trialApply.getId());
                // 样品ID
                sampleEO.setSampleId(saCarDataEO.getId());
                // 样品类型
                sampleEO.setSampleType("car");
                // 发动机型号
                sampleEO.setSampleEngineType(saCarDataEO.getCarEngineType());
                // 生产日期
                sampleEO.setSampleProduceTime(saCarDataEO.getProducedTime());
                // 样品编号
                String applyNo = getApplyNo(saCarDataDTO.getTrialApplyNO());
                sampleEO.setSampleNo(applyNo);
                saCarDataDAO.insertTrialtaskSample(sampleEO);
            }
        }
        // 添加流转日志
        if (saCarDataDTO.getCarStatus() != null && 5 != saCarDataDTO.getCarStatus()) {
            SaCarFlowLogEO saCarFlowLogEO = new SaCarFlowLogEO();
            saCarFlowLogEO.setId(UUID.randomUUID10());
            saCarFlowLogEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
            saCarFlowLogEO.setCreateTime(date);
            saCarFlowLogEO.setCreateBy(userId);
            saCarFlowLogEO.setCarDataId(saCarDataEO.getId());
            saCarFlowLogEO.setLeaderId(saCarDataDTO.getSendCarUserId());
            saCarFlowLogEO.setOperationDate(date);
            saCarFlowLogEO.setOperationContent("收样");
            saCarFlowLogEO.setTrialApplyNo(saCarDataDTO.getTrialApplyNO());
            try {
                saCarFlowLogService.insertSelective(saCarFlowLogEO);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return saCarDataEO;
    }

    /**
     * 获取委托单编号接口
     *
     * @return
     */
    public String getApplyNo(String applyNo) {
        PipelineNumberEO pipelineNumberEO = pipelineNumberEODao.selectByPrimaryKey(ConstantUtils.SA_CAR_SAMPLE);
        String date = DateUtils.dateToString(new Date(), "yyyy");
        // 如果查不到流水号新增
        if (pipelineNumberEO == null) {
            PipelineNumberEO eo = new PipelineNumberEO();
            // 为了保证查询时的流水号，和保存委托时的流水号相同。未查询到流水号时返回0001，然后将当前流水号+1，保存数据库。
            eo.setTally(2);
            eo.setParticularYear(date);
            eo.setType(ConstantUtils.SA_CAR_SAMPLE);
            pipelineNumberEODao.insertSelective(eo);
            return applyNo + date + "-0001";
        }
        // 如果当前年份不等于数据库中以保存的年份，重新开始
        if (!date.equals(pipelineNumberEO.getParticularYear())) {
            // 为了保证查询时的流水号，和保存委托时的流水号相同。年份不相等时重新计算流水号，返回0001，然后将当前流水号+1，保存数据库。
            pipelineNumberEO.setTally(2);
            pipelineNumberEO.setParticularYear(date);
            pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
            return applyNo + date + "-0001";
        }
        //最大流水号
        String num = pipelineNumberEO.getTally().toString();
        StringBuilder sb = new StringBuilder(num);
        //自动补全4位
        while (sb.length() < 4) {
            sb.insert(0, "0");
        }
        // 为了保证查询时的流水号，和保存委托时的流水号相同。查询返回数据库当前流水号，然后将当前流水号+1，保存数据库。
        pipelineNumberEO.setTally(pipelineNumberEO.getTally() + 1);
        pipelineNumberEODao.updateByPrimaryKey(pipelineNumberEO);
        return applyNo + date + "-" + sb.toString();
    }

    /**
     * 编辑整车
     *
     * @param saCarDataDTO
     */
    public SaCarDataEO edit(SaCarDataDTO saCarDataDTO) {
        SaCarDataEO saCarDataEO = saCarDataDAO.selectByPrimaryKey(saCarDataDTO.getId());
        if (saCarDataEO == null) {
            return null;
        }
        if (StringUtils.isNotBlank(saCarDataDTO.getCarDepotId()) && saCarDataDTO.getCarSpaceNumber() != null
                && saCarDataDTO.getCarSpaceNumber() != 0 && isEqualsDepotAndCarSpaceNumer(saCarDataDTO, saCarDataEO)) {
            // 如果之前的存放位置和编辑后的存放位置不同，需要将之前的存放位置取消，并保存编辑之后的存放位置。
            // 取消之前的存放位置
            carDepotService.cancelBeforeUsedCarSpace(saCarDataEO);
            saCarDataEO.setCarSpaceNumber(saCarDataDTO.getCarSpaceNumber());
            saCarDataEO.setCarDepotId(saCarDataDTO.getCarDepotId());
            // 保存编辑之后的存放位置
            carDepotService.editUsedCarSpace(saCarDataEO);
        }
        SaCarDataEO dataEO = beanMapper.map(saCarDataDTO, saCarDataEO.getClass());
        String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        dataEO.setUpdateTime(date);
        dataEO.setUpdateBy(UserUtils.getUserId());
        saCarDataDAO.updateByPrimaryKeySelective(dataEO);
        return dataEO;
    }

    private boolean isEqualsDepotAndCarSpaceNumer(SaCarDataDTO saCarDataDTO, SaCarDataEO saCarDataEO) {
        return !saCarDataDTO.getCarDepotId().equals(saCarDataEO.getCarDepotId())
                || saCarDataDTO.getCarSpaceNumber() != saCarDataEO.getCarSpaceNumber();
    }

    /**
     * 查询详情
     *
     * @param id
     * @return
     */
    public SaCarDataVO getOne(String id) {
        SaCarDataEO saCarDataEO = saCarDataDAO.selectByPrimaryKey(id);
        return getSaCarDataVO(saCarDataEO);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        // 查询样车是否存在
        SaCarDataEO saCarDataEO = saCarDataDAO.selectByPrimaryKey(id);
        if (saCarDataEO != null) {
            // 删除车位
            carDepotService.cancelBeforeUsedCarSpace(saCarDataEO);
            // 删除关联关系，整车、试验委托
            saCarTrialapplyInsprojectService.deleteByCarId(id);
            // 删除流转日志
            saCarFlowLogService.deleteByCarId(id);
            // 删除整车
            String date = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            saCarDataEO.setUpdateTime(date);
            saCarDataEO.setUpdateBy(UserUtils.getUserId());
            saCarDataEO.setDelFlag(DeleteFlagEnum.DELETE.getValue());
            saCarDataDAO.updateByPrimaryKeySelective(saCarDataEO);
        }
    }

    /**
     * 生成二维码
     *
     * @param ids
     * @return
     */
    public List<CarBarcodeVO> barCode(String[] ids) {
        List<CarBarcodeVO> carBarcodeVOList = new ArrayList<>();
        for (String id : ids) {
            SaCarDataEO saCarDataEO = saCarDataDAO.selectByPrimaryKey(id);
            if (saCarDataEO != null) {
                List<CarBarcodeVO> barcodeVOS = saCarDataDAO.selectCarBarCode(id, saCarDataEO.getCarStatus());
                // 生成二维码
                try {
                    BufferedImage bufferedImage = null;
                    bufferedImage = GenerateQrCode.init().width(80).height(80).content(id).generate();
                    File file = new File(barCodePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    ImageIO.write(
                            bufferedImage,
                            "png",
                            new File(barCodePath + barcodeVOS.get(0).getCarVin().replace("/", "-") + ".png"));
                    barcodeVOS.get(0).setBarCode(
                            barCodePath + barcodeVOS.get(0).getCarVin().replace("/", "-") + ".png");
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
                carBarcodeVOList.add(barcodeVOS.get(0));
            }
        }
        return carBarcodeVOList;
    }
    public boolean carSpaceExists(SaCarDataEO saCarDataEO) {
        return StringUtils.isNotBlank(saCarDataEO.getCarDepotId()) && saCarDataEO.getCarSpaceNumber() != null
                && saCarDataEO.getCarSpaceNumber() > 0;
    }

    /**
     * 查询需要到处去的数据
     *
     * @param page
     * @return
     */
    public List<SaCarDataVO> queryListForExcel(SaCarDataEOPage page) {
        List<SaCarDataEO> saCarDataEOS = saCarDataDAO.queryListForExcel(page);
        List<SaCarDataVO> saCarDataVOS = new ArrayList<>();
        if (saCarDataEOS != null && !saCarDataEOS.isEmpty()) {
            for (SaCarDataEO eo : saCarDataEOS) {
                SaCarDataVO saCarDataVO = beanMapper.map(eo, SaCarDataVO.class);
                // 通过整车id查询试验任务编号和样品编号
                saCarDataVO.setTrialtaskSampleVOS(
                        saCarTrialapplyInsprojectService.selectListByCarId(saCarDataVO.getId()));
                eoToVoList(eo, saCarDataVO);
                saCarDataVOS.add(saCarDataVO);
            }
        }
        return saCarDataVOS;
    }

    // 获取各种人员信息
    public void eoToVoList(SaCarDataEO eo, SaCarDataVO saCarDataVO) {

        // 获取送样人信息
        if (StringUtils.isNotBlank(eo.getSendCarUserId())) {
            UserEO userEO = userEOService.getUserById(eo.getSendCarUserId());
            if (userEO != null) {
                saCarDataVO.setSendCarUserPhone(userEO.getCellPhoneNumber());
                saCarDataVO.setSendCarUserName(userEO.getUsname());
                StringBuilder sb = new StringBuilder();
                for (OrgEO orgEO : userEO.getOrgEOList()) {
                    sb.append(orgEO.getName()).append(",");
                }
                if (StringUtils.isNotBlank(sb)) {
                    saCarDataVO.setSendCarUserOrg(sb.substring(0, sb.lastIndexOf(",")));
                }
            }
        }
        // 获取接收人信息
        if (StringUtils.isNotBlank(eo.getReceivedUserId())) {
            UserEO userEO = userEOService.getUserById(eo.getReceivedUserId());
            if (userEO != null) {
                saCarDataVO.setReceivedUserName(userEO.getUsname());
            }
        }
        // 样车所在地外场管理责任人信息
        if (StringUtils.isNotBlank(eo.getCarLocationManagerId())) {
            UserEO userEO = userEOService.getUserById(eo.getCarLocationManagerId());
            if (userEO != null) {
                saCarDataVO.setCarLocationManagerName(userEO.getUsname());
            }
        }
        //  获取项目平台
        if (StringUtils.isNotBlank(eo.getBmProjectId())) {
            try {
                ProjectEO projectEO = projectEOService.selectByPrimaryKey(eo.getBmProjectId());
                if (projectEO != null) {
                    saCarDataVO.setBmProjectName(projectEO.getName());
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    public List<SaCarDataListVO> selectByPage(SaCarDataEOPage eoPage) {
        Integer rowCount = saCarDataDAO.queryByCount(eoPage);
        eoPage.getPager().setRowCount(rowCount);
        List<SaCarDataListVO> resultList =  saCarDataDAO.selectByPage(eoPage);
        for(SaCarDataListVO vo:resultList){   //任务id查此任务人员，取录入时的第一个
            String person="";
            String code = vo.getTrialApplyId();
            if(StringUtils.isBlank(code)) continue;
            String num[] = code.split(",");  //被使用样品的所有任务id
   //         List<String> params = new ArrayList(Arrays.asList(num));
            List<String> personList  = saCarDataDAO.selectByKeyArray(num);
            if(personList == null || personList.size()==0) continue;
   //        personList.forEach(param ->{ });
            for(String param:personList){
                if(param.indexOf(",")<0)  person+=param;
                else person+=param.substring(0,param.indexOf(","));
            }
            vo.setUserName(person);
        }
        return resultList;
    }

    /**
     * 查询退样单详情
     *
     * @param id
     * @return
     */
    public SaBackCarDataVO backCar(String id) {
        SaCarDataVO one = this.getOne(id);
        SaBackCarDataVO map = beanMapper.map(one, SaBackCarDataVO.class);
        // 通过整车id查询退样日志
        List<SaCarFlowLogVO> flowLogVO = saCarFlowLogService.selectByCarId(id, SampleStatusEnum.BACK.getLabel());
        if (flowLogVO != null && !flowLogVO.isEmpty()) {
            map.setBackCarUser(flowLogVO.get(0).getLeaderName());
            map.setBackCarUserOrg(flowLogVO.get(0).getLeaderOrg());
            map.setOperator(flowLogVO.get(0).getOperatorName());
            map.setOperatorOrg(flowLogVO.get(0).getOperatorOrg());
            map.setReceivTime(flowLogVO.get(0).getOperationDate());
            map.setRemarks(flowLogVO.get(0).getRemarks());
            String other = flowLogVO.get(0).getOthers();
            if (StringUtils.isNotBlank(other)) {
                other = other.substring("报告结果确认：".length());
                map.setReportResult(other);
            }
        }
        return map;
    }

    /**
     * 通过停车场ID和车位号查询详情
     *
     * @param depotId
     * @param carSpaceNumber
     * @return
     */
    public SaCarDataVO getOneByDepotId(String depotId, String carSpaceNumber) {
        SaCarDataEO saCarDataEO = saCarDataDAO.getOneByDepotId(depotId, carSpaceNumber);
        return getSaCarDataVO(saCarDataEO);
    }

    public SaCarDataVO getSaCarDataVO(SaCarDataEO saCarDataEO) {
        if (saCarDataEO == null) {
            return null;
        }
        SaCarDataVO saCarDataVO = beanMapper.map(saCarDataEO, SaCarDataVO.class);
        // 获取试验委托和整车编号
        List<TrialtaskSampleVO> vo = saCarTrialapplyInsprojectService.selectListByCarId(saCarDataVO.getId());
        saCarDataVO.setTrialtaskSampleVOS(vo);
        if (vo != null && !vo.isEmpty()) {
            StringBuilder carNo = new StringBuilder();
            StringBuilder applyId = new StringBuilder();
            StringBuilder applyNo = new StringBuilder();
            for (int i = 0; i < vo.size(); i++) {
                if (i == vo.size() - 1) {
                    String carNO = vo.get(i).getCarNO();
                    if (com.adc.da.util.utils.StringUtils.isEmpty(carNO)){
                        carNO = "";
                    }
                    carNo.append(carNO);
                    String trialApplyId = vo.get(i).getTrialApplyId();
                    if (com.adc.da.util.utils.StringUtils.isEmpty(trialApplyId)){
                        trialApplyId = "";
                    }
                    applyId.append(trialApplyId);
                    String trialApplyNO = vo.get(i).getTrialApplyNO();
                    if (com.adc.da.util.utils.StringUtils.isEmpty(trialApplyNO)){
                        trialApplyNO = "";
                    }
                    applyNo.append(trialApplyNO);
                } else {
                    String carNO = vo.get(i).getCarNO();
                    if (com.adc.da.util.utils.StringUtils.isEmpty(carNO)){
                        carNO = "";
                    }
                    String trialApplyId = vo.get(i).getTrialApplyId();
                    if (com.adc.da.util.utils.StringUtils.isEmpty(trialApplyId)){
                        trialApplyId = "";
                    }
                    String trialApplyNO = vo.get(i).getTrialApplyNO();
                    if (com.adc.da.util.utils.StringUtils.isEmpty(trialApplyNO)){
                        trialApplyNO = "";
                    }
                    carNo.append(carNO).append(",");
                    applyId.append(trialApplyId).append(",");
                    applyNo.append(trialApplyNO).append(",");
                }
            }
            saCarDataVO.setCarNO(carNo.toString());
            saCarDataVO.setTrialApplyId(applyId.toString());
            saCarDataVO.setTrialApplyNO(applyNo.toString());
        }
        // 获取各种人员信息
        eoToVoList(saCarDataEO, saCarDataVO);
        return saCarDataVO;
    }

    /**
     * 发动机试验任务执行-点击“样品信息”查询
     *
     * @param id
     * @Title：page向老司机致敬
     */
    public List<SaCarDataListVO> selectSampleListById(String id) {
        return this.getDao().selectSampleListById(id);
    }

    public TrialSampleVO verifyTrialNo(String trialNo) {
        return saCarDataDAO.selectByTrialApplyNo(trialNo);
    }

	public List<SaCarBrrowEOPage> brrowPage(SaCarBrrowEOPage page, String searchPhrase) {
		//通用查询的参数不为空即为通用查询
				if(StringUtils.isNotEmpty(searchPhrase) && 
					StringUtils.isNotEmpty(searchPhrase.trim())){
		            searchPhrase = searchPhrase.trim();
		            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
		            Matcher dateMatcher = datePattern.matcher(searchPhrase);
		            List<String> list = new ArrayList<>();
		            while (dateMatcher.find()) {
		                String search = dateMatcher.group();
		                list.add(search);
		            }
		            page.setSearchPhrase(list);
		        }
				//--------------单条件查询-------------//
				
				//任务单号
				if(StringUtils.isNotEmpty(page.getTaskNum())) {
					page.setTaskNum(page.getTaskNum());
				}
				//借车单编号
				if(StringUtils.isNotEmpty(page.getBrrowNum())) {
					page.setBrrowNum(page.getBrrowNum());
				}
				//申请人
				if(StringUtils.isNotEmpty(page.getApplicant())) {
					page.setApplicant(page.getApplicant());
				}
				//完成时间
				if(StringUtils.isNotEmpty(page.getEndTime1())) {
					page.setEndTime1(page.getEndTime1());
				}
				if(StringUtils.isNotEmpty(page.getEndTime2())) {
					page.setEndTime2(page.getEndTime2());
				}
				List<SaCarBrrowEOPage> lists = saCarDataDAO.queryBrrowCarByPage(page);
				//查询
				return lists;
			}

	public Integer queryBrrowByCount(SaCarBrrowEOPage page) {
		return saCarDataDAO.queryBrrowByCount(page);
	}

	public List<SaCarReturnEOPage> returnPage(SaCarReturnEOPage page, String searchPhrase) {
		//通用查询的参数不为空即为通用查询
				if(StringUtils.isNotEmpty(searchPhrase) && 
					StringUtils.isNotEmpty(searchPhrase.trim())){
		            searchPhrase = searchPhrase.trim();
		            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
		            Matcher dateMatcher = datePattern.matcher(searchPhrase);
		            List<String> list = new ArrayList<>();
		            while (dateMatcher.find()) {
		                String search = dateMatcher.group();
		                list.add(search);
		            }
		            page.setSearchPhrase(list);
		        }
				//--------------单条件查询-------------//
				
				List<SaCarReturnEOPage> lists = saCarDataDAO.queryReturnCarByPage(page);
				//查询
				return lists;
	}

	public Integer queryReturnByCount(SaCarReturnEOPage page) {
		return saCarDataDAO.queryReturnByCount(page);
	}
    
	
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
	public boolean checkNo(String id, String chassisNumber) {
		//获取所有底盘号集合
		List<String> numList = saCarDataDAO.checkNo(id);
		return numList.contains(chassisNumber);
	}

    /**
     * 获取当前用户的组织机构
     */
    public String getUserDepartName(String departId){
        OrgEO orgEO = orgEOService.getOrgEOById(departId);
        if (orgEO != null){
            String parentId = orgEO.getParentId();
            if ("VYGKQ98GS6".equals(parentId)) {
                return orgEO.getName();
            } else {
                return getUserDepartName(orgEO.getParentId());
            }
        }
        return "";
    }

    /**
     * 批量导入整车样品
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseMessage importMateriel(MultipartFile file) throws Exception {
        //时间
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        ResponseMessage result = new ResponseMessage();
        if (file.isEmpty()) {
            LOGGER.info("文件不存在，导入失败......", new FileNotFoundException());
            result = Result.error("-1", "文件不存在，导入失败");
            return result;
        }
        FileInputStream fis = (FileInputStream) file.getInputStream();
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheetAt(0);
        if (sheet.getLastRowNum() <= 1){
            return Result.error("-1", "请不要导入空模板！");
        }
        //记录错误信息。
        StringBuilder errorMess = new StringBuilder();
        //获取东风柳汽下面所有部门类型的机构名称
        String orgName = orgEOService.getOrgName();
        ArrayList<SaCarDataEO> eoList = new ArrayList<>();
        //从第三行开始解析
        outLoop:for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            try{
                Row row = sheet.getRow(i);
                //项目平台
                String bmProjectName = getCellValueByCell(row.getCell(0));
                //名称
                String carName = getCellValueByCell(row.getCell(1));
                //车型号
                String carModel = getCellValueByCell(row.getCell(2));
                //底盘号
                String chassisNumber = getCellValueByCell(row.getCell(3));
                //VIN码
                String carVin = getCellValueByCell(row.getCell(4));
                //车型
                String carType = getCellValueByCell(row.getCell(5));
                //发动号
                String carEngineNo = getCellValueByCell(row.getCell(6));
                //发动机型号
                String carEngineType = getCellValueByCell(row.getCell(7));
                //整备质量
                String carWeight = getCellValueByCell(row.getCell(8));
                //乘员数量
                String passengerNumber = getCellValueByCell(row.getCell(9));
                //里程表数
                String carMilieage = getCellValueByCell(row.getCell(10));
                //配置信息
                String producedFactory = getCellValueByCell(row.getCell(11));
                //零部件状态
                String partStatus = getCellValueByCell(row.getCell(12));
                //轮胎厂家
                String tyreFactory = getCellValueByCell(row.getCell(13));
                //轮胎型号
                String tyreType = getCellValueByCell(row.getCell(14));
                //驱动轮胎胎压
                String tyrePressure = getCellValueByCell(row.getCell(15));
                //创建人部门
                String createByDepart = getCellValueByCell(row.getCell(16));
                if (StringUtils.isEmpty(carName)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行名称不能为空!<br> ");
                }
                if (StringUtils.isEmpty(carVin)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行VIN不能为空!<br> ");
                }
                if (StringUtils.isEmpty(chassisNumber)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行底盘号不能为空!<br> ");
                }
                if (StringUtils.isEmpty(createByDepart)){
                    errorMess.append("导入失败！第" +  (i + 1) + "行创建人部门不能为空!<br> ");
                }else {
                    if (!orgName.contains(createByDepart)){
                        errorMess.append("导入失败！第" +  (i + 1) + "行创建人部门系统不存在!<br> ");
                    }
                }
                SaCarDataEO saCarDataEO = new SaCarDataEO();
                saCarDataEO.setBmProjectName(bmProjectName);
                saCarDataEO.setCarName(carName);
                saCarDataEO.setCarModel(carModel);
                saCarDataEO.setChassisNumber(chassisNumber);
                saCarDataEO.setCarVin(carVin);
                saCarDataEO.setCarType(carType);
                saCarDataEO.setCarEngineNo(carEngineNo);
                saCarDataEO.setCarEngineType(carEngineType);
                saCarDataEO.setCarWeight(carWeight);
                saCarDataEO.setPassengerNumber(passengerNumber);
                saCarDataEO.setCarMilieage(carMilieage);
                saCarDataEO.setProducedFactory(producedFactory);
                saCarDataEO.setPartStatus(partStatus);
                saCarDataEO.setTyreFactory(tyreFactory);
                saCarDataEO.setTyreType(tyreType);
                saCarDataEO.setTyrePressure(tyrePressure);
                saCarDataEO.setCreateByDepart(createByDepart);
                Boolean aBoolean = checkInfo(eoList, saCarDataEO);
                if (aBoolean){
                    errorMess.append("导入失败！第" +  (i + 1) + "行信息重复!<br> ");
                }
                eoList.add(saCarDataEO);
            }catch (Exception e){
                LOGGER.error(e.getMessage(), e);
            }
        }
        if (errorMess.length() == 0){
            for (SaCarDataEO car : eoList){
                List<SaCarDataEO> saCarDataEOS = saCarDataDAO.selectCarByInfo(car.getChassisNumber(), car.getCreateByDepart());
                if (CollectionUtils.isEmpty(saCarDataEOS)){
                    car.setId(UUID.randomUUID());
                    car.setCreateBy(UserUtils.getUserId());
                    car.setCreateTime(date);
                    car.setDelFlag(0);
                    saCarDataDAO.insertSelective(car);
                }else {
                    for(SaCarDataEO saCarDataEO : saCarDataEOS){
                        car.setId(saCarDataEO.getId());
                        car.setUpdateBy(UserUtils.getUserId());
                        car.setUpdateTime(date);
                        saCarDataDAO.updateByPrimaryKeySelective(car);
                    }
                }
            }
            result = Result.success("0", "批量导入成功！");
            return result;
        }else {
            //导入失败--提示错误信息
            result = Result.error("-1", errorMess.toString());
            return result;
        }
    }

    /**
     * 验证导入的list中是否包含相同记录
     */
    public Boolean checkInfo(List<SaCarDataEO> saCarDataEOS,SaCarDataEO saCarDataEO){
        Boolean flag = false;
        if (CollectionUtils.isNotEmpty(saCarDataEOS)){
            for (SaCarDataEO car : saCarDataEOS){
                String chassisNumber = StringUtils.isEmpty(car.getChassisNumber()) ?  "" : car.getChassisNumber();
                String createByDepart = StringUtils.isEmpty(car.getCreateByDepart()) ?  "" : car.getCreateByDepart();
                String chassisNumber1 = StringUtils.isEmpty(saCarDataEO.getChassisNumber()) ?  "" : saCarDataEO.getChassisNumber();
                String createByDepart1 = StringUtils.isEmpty(saCarDataEO.getCreateByDepart()) ?  "" : saCarDataEO.getCreateByDepart();
                if (chassisNumber.equals(chassisNumber1) && createByDepart.equals(createByDepart1)){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public List<SaCarDataEO> getCarList(List<String> idList) {
        Map<String, Object> params=new HashMap();
        params.put("idList",idList);
        return saCarDataDAO.getCarByIds(params);
    }
}
