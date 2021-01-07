package com.adc.da.roadcost.service;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.Pager;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.roadcost.RoadCostPage;
import com.adc.da.roadcost.dao.RoadCostDao;
import com.adc.da.roadcost.entity.RoadCostEo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("roadCostService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoadCostService extends BaseService<RoadCostEo,String> {
    @Autowired
    private RoadCostDao roadCostDao;
    @Override
    public BaseDao<RoadCostEo> getDao() {
        return roadCostDao;
    }

    public List<RoadCostEo> page(RoadCostPage roadCostPage, String searchPhrase) {
        if (StringUtils.isNotEmpty(searchPhrase)) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<String>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            roadCostPage.setSearchPhrase(list);
        }
        roadCostPage.setPager(new Pager());
        return roadCostDao.queryByPage(roadCostPage);
    }

    /**
     * 根据马力和车型路线查询路送单价
     * @param horsePower
     * @param driveCarType
     * @param roadLine
     * @return
     */
    public String getPriceByHorsePowerAndCarTypeAndRoadLine(String horsePower, String driveCarType, String roadLine) {
        return roadCostDao.getPriceByHorsePowerAndCarTypeAndRoadLine(horsePower,driveCarType,roadLine);
    }
}
