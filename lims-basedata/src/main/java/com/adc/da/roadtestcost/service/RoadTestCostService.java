package com.adc.da.roadtestcost.service;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.roadcost.entity.RoadCostEo;
import com.adc.da.roadtestcost.dao.RoadTestCostDao;
import com.adc.da.roadtestcost.entity.RoadTestCostEo;
import com.adc.da.roadtestcost.page.RoadTestCostEoPage;
import com.adc.da.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("roadTestCostService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoadTestCostService extends BaseService<RoadTestCostEo,String> {

    @Autowired
    private RoadTestCostDao roadTestCostDao;

    @Override
    public BaseDao<RoadTestCostEo> getDao() {
        return roadTestCostDao;
    }

    public List<RoadTestCostEo> getRoadTestCostEntityPage(RoadTestCostEoPage page, String searchPhrase) {
        //通用查询的参数不为空即为通用查询
        if (StringUtils.isNotEmpty(searchPhrase) &&
                StringUtils.isNotEmpty(searchPhrase.trim())) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            //将查询条件循环放入list中
            List<String> list = new ArrayList<>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }

        //查询
        return roadTestCostDao.queryByPage(page);
    }

    /**
     * 根据马力和车型路线查询路试单价
     * @param horsePower
     * @param driveCarType
     * @param roadLine
     * @return
     */
    public String getPriceByHorsePowerAndCarTypeAndRoadLine(String horsePower, String driveCarType, String roadLine) {
        return roadTestCostDao.getPriceByHorsePowerAndCarTypeAndRoadLine(horsePower,driveCarType,roadLine);
    }
}
