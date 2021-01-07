package com.adc.da.pc_drive.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adc.da.base.page.Pager;
import com.adc.da.common.ConstantUtils;
import com.adc.da.pc_drive.dao.RoadLineRealityEODao;
import com.adc.da.pc_drive.entity.RoadLineEO;
import com.adc.da.pc_drive.entity.RoadLineRealityEO;
import com.adc.da.pc_drive.vo.TrustRelated;
import com.adc.da.sys.service.DicTypeEOService;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.adc.da.base.web.BaseController;
import com.adc.da.pc_drive.entity.PcDriveRecordEO;
import com.adc.da.pc_drive.page.PcDriveRecordEOPage;
import com.adc.da.pc_drive.service.PcDriveRecordEOService;

import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/${restPath}/pc_drive/pcDriveRecord")
@Api(description = "|PcDriveRecordEO|行车记录表")
public class PcDriveRecordEOController extends BaseController<PcDriveRecordEO>{

    private static final Logger logger = LoggerFactory.getLogger(PcDriveRecordEOController.class);

    @Autowired
    private PcDriveRecordEOService pcDriveRecordEOService;

    @Autowired
    private DicTypeEOService dicTypeEOService;


	@ApiOperation(value = "|PcDriveRecordEO|分页查询")
    @GetMapping("/page")
    //@RequiresPermissions("pc_drive:pcDriveRecord:page")
    public ResponseMessage<PageInfo<PcDriveRecordEO>> page(PcDriveRecordEOPage page,String searchPhrase,
                                                           String taskNumber,String createBy,String taskOrPlan,String trialId) throws Exception {
        if (StringUtils.isNotEmpty(searchPhrase)) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<String>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
        page.setCreateBy(createBy);
        page.setTaskOrPlan(taskOrPlan);
        page.setDriverId("%"+createBy+"%");
        page.setDriverIdOperator("like");
        //是0代表试验任务执行
        if("0".equals(taskOrPlan)){
            page.setTaskNumber(taskNumber);
        }
        //1代表计划
        if("1".equals(taskOrPlan)){
            page.setTrialId(trialId);
        }
        page.setPager(new Pager());
        Integer count = pcDriveRecordEOService.queryByCount(page);
        page.getPager().setRowCount(count);
        List<PcDriveRecordEO> rows = pcDriveRecordEOService.queryByPage(page);
        return Result.success(getPageInfo(page.getPager(), rows));
    }

	@ApiOperation(value = "|PcDriveRecordEO|查询")
    @GetMapping("")
    //@RequiresPermissions("pc_drive:pcDriveRecord:list")
    public ResponseMessage<List<PcDriveRecordEO>> list(PcDriveRecordEOPage page) throws Exception {
        return Result.success(pcDriveRecordEOService.queryByList(page));
	}

    @ApiOperation(value = "|PcDriveRecordEO|详情")
    @GetMapping("/{id}")
    //@RequiresPermissions("pc_drive:pcDriveRecord:get")
    public ResponseMessage<PcDriveRecordEO> find(@PathVariable String id) throws Exception {
        PcDriveRecordEO pcDriveRecordEO = pcDriveRecordEOService.selectByPrimaryKey(id);
        List<RoadLineEO> roadLineEOS = pcDriveRecordEOService.selectRoadLineEoByDriveRecordId(id);
        if(!CollectionUtils.isEmpty(roadLineEOS)){
            pcDriveRecordEO.setRoadLineEOS(roadLineEOS);
        }
        return Result.success(pcDriveRecordEO);
    }

    @ApiOperation(value = "|PcDriveRecordEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("pc_drive:pcDriveRecord:save")
    public ResponseMessage<PcDriveRecordEO> create(@RequestBody PcDriveRecordEO pcDriveRecordEO) throws Exception {
	    pcDriveRecordEO.setId(UUID.randomUUID());
        pcDriveRecordEOService.insertSelective(pcDriveRecordEO);
        if(!CollectionUtils.isEmpty(pcDriveRecordEO.getRoadLineEOS())){
            for (RoadLineEO roadLineEO : pcDriveRecordEO.getRoadLineEOS()) {
                pcDriveRecordEOService.insertRoadLineEO(roadLineEO,pcDriveRecordEO);
            }
        }
        return Result.success("0","新增成功",pcDriveRecordEO);
    }

    @ApiOperation(value = "|PcDriveRecordEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    //@RequiresPermissions("pc_drive:pcDriveRecord:update")
    public ResponseMessage<PcDriveRecordEO> update(@RequestBody PcDriveRecordEO pcDriveRecordEO) throws Exception {
        pcDriveRecordEOService.updateByPrimaryKeySelective(pcDriveRecordEO);
        if(!CollectionUtils.isEmpty(pcDriveRecordEO.getRoadLineEOS())){
            for (RoadLineEO roadLineEO : pcDriveRecordEO.getRoadLineEOS()) {
                if(StringUtils.isEmpty(roadLineEO.getId())){
                    pcDriveRecordEOService.insertRoadLineEO(roadLineEO,pcDriveRecordEO);
                }else{
                    //设置价格
                    pcDriveRecordEOService.calculatePrice(roadLineEO,pcDriveRecordEO);
                    pcDriveRecordEOService.updateRoadLineEO(roadLineEO);
                }
            }
        }
        return Result.success("0","修改成功",pcDriveRecordEO);
    }

    @ApiOperation(value = "|PcDriveRecordEO|删除")
    @DeleteMapping("/{id}")
    //@RequiresPermissions("pc_drive:pcDriveRecord:delete")
    public ResponseMessage delete(@PathVariable String id) throws Exception {
	    //删除之前先将主表的路试或路送的里程更新
        RoadLineEO roadLineEO = pcDriveRecordEOService.getRoadLine(id);
        PcDriveRecordEO pcDriveRecordEO = pcDriveRecordEOService.selectByPrimaryKey(roadLineEO.getDriverRecordId());
        //如果是路送
        if(StringUtils.isNotEmpty(roadLineEO.getMilePlus()) && "CarType,LineRoad".equals(roadLineEO.getDrType())){
            //更新路送总里程
            double mile = Double.parseDouble(pcDriveRecordEO.getRealRoadMil()) - Double.parseDouble(roadLineEO.getMilePlus());
            pcDriveRecordEO.setRealRoadMil(String.valueOf(mile));
            pcDriveRecordEOService.updateByPrimaryKeySelective(pcDriveRecordEO);
        }
        //路试情况
        if(StringUtils.isNotEmpty(roadLineEO.getMilePlus()) && "RoadTestCarType,LineRoad".equals(roadLineEO.getDrType())){
            double mile = Double.parseDouble(pcDriveRecordEO.getRealRoadTestMil())
                    - Double.parseDouble(roadLineEO.getMilePlus());
            pcDriveRecordEO.setRealRoadTestMil(String.valueOf(mile));
            pcDriveRecordEOService.updateByPrimaryKeySelective(pcDriveRecordEO);
        }
        pcDriveRecordEOService.deleteByPrimaryKey(id);
        logger.info("delete from DRIVING_ROAD_LINE where id = {}", id);
        return Result.success("0","删除成功");
    }

    /**
     * 根据任务书编号查询委托单承接相关方
     * @param taskNum
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "|查询委托单承接相关方")
    @GetMapping("/getTrust/{taskNum}")
    public ResponseMessage selectTrustByTaskNum(@PathVariable String taskNum) throws Exception {
        TrustRelated trustRelated = pcDriveRecordEOService.getTrustByTaskNum(taskNum);
        return Result.success(trustRelated);
    }
    /**
     * 根据行车记录id获取整合后的数据详情
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "|PcDriveRecordEO|详情数据整合后")
    @GetMapping("integration/{id}")
    //@RequiresPermissions("pc_drive:pcDriveRecord:get")
    public ResponseMessage<PcDriveRecordEO> integration(@PathVariable String id) throws Exception {
        String driveRecordId = "";
        PcDriveRecordEO pcDriveRecordEO = pcDriveRecordEOService.selectByProjectId(id);
        if(pcDriveRecordEO!=null) driveRecordId = pcDriveRecordEO.getId();
        else return Result.success(new PcDriveRecordEO());
        List<RoadLineRealityEO> realityEOS = pcDriveRecordEOService.selectRoadLineRealityEoByDriveRecordId(driveRecordId);
        if(realityEOS!=null&&!realityEOS.isEmpty()){
            pcDriveRecordEO.setRealityEOS(realityEOS);
            return Result.success(pcDriveRecordEO);
        } else {
            List<RoadLineEO> roadLineEOS = pcDriveRecordEOService.selectRoadLineEoByDriveRecordId(driveRecordId);
            //整合后的集合
            List<RoadLineEO> lineEOS = new ArrayList<>();
            realityEOS = new ArrayList<>();
            //记录已经存在的工况类别
            List<String> condition = new ArrayList<>();
            //记录的上一次的类别
            String drType = null;
            if (!CollectionUtils.isEmpty(roadLineEOS)) {
                for (int i = 0; i < roadLineEOS.size(); i++) {
                    //判别类别是路送  路送的逻辑 相邻路送取最后一条
                    if ("CarType,LineRoad".equals(roadLineEOS.get(i).getDrType())) {
                        if (roadLineEOS.get(i).getDrType().equals(drType) && drType != null) {
                            lineEOS.remove(lineEOS.size() - 1);
                        }
                        lineEOS.add(roadLineEOS.get(i));
                    }
                    //判断类别是否是路试  路试的逻辑
                    if ("RoadTestCarType,LineRoad".equals(roadLineEOS.get(i).getDrType())) {
                        //判断工况类别  工况逻辑 取行车路况相同的最后一条，每种路况只能有一条数据
                        //记录已经有的工况类别
                        if (condition.size() > 0) {
                            for (String s : condition) {
                                if (s.equals(roadLineEOS.get(i).getTestConditions())) {
                                    //获取类别为路试，工况已经存在的存入list的实体
                                    RoadLineEO lineEO = lineEOS.stream().filter(roadLineEO -> "RoadTestCarType,LineRoad".equals(roadLineEO.getDrType()) && s.equals(roadLineEO.getTestConditions())).findAny().orElse(null);
                                    if (lineEO != null) {
                                        lineEOS.remove(lineEO);
                                    }
                                }
                            }
                        }
                        condition.add(roadLineEOS.get(i).getTestConditions());
                        lineEOS.add(roadLineEOS.get(i));
                    }
                    //在最后记录上一次的类别
                    drType = roadLineEOS.get(i).getDrType();
                }
                for (RoadLineEO rle : lineEOS) {
                    //将整理后的行车记录加入返回集合
                    RoadLineRealityEO realityEO=new RoadLineRealityEO();
                    BeanUtils.copyProperties(rle,realityEO);
                    realityEO.setId(null);
                    realityEO.setEndTime(rle.getEndTime());
                    realityEOS.add(realityEO);
                }
                pcDriveRecordEO.setRealityEOS(realityEOS);
            }
            return Result.success(pcDriveRecordEO);
        }
    }
}
