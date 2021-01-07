package com.adc.da.supRoadTest.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.supRoadTest.dao.RoadTestEODao;
import com.adc.da.supRoadTest.entity.RoadTestEO;
import com.adc.da.supRoadTest.page.RoadTestEOPage;


/**
 *
 * <br>
 * <b>功能：</b>SUP_ROAD_TEST RoadTestEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2020-08-12 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("roadTestEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class RoadTestEOService extends BaseService<RoadTestEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(RoadTestEOService.class);

    @Autowired
    private RoadTestEODao dao;

    public RoadTestEODao getDao() {
        return dao;
    }

    public List<RoadTestEO> page(RoadTestEOPage page,
                                 String searchPhrase) {
        //解析通用查询
        if (StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<Map<String, Object>> list = new ArrayList<>();
            while (dateMatcher.find()) {
                Map<String, Object> map = new HashMap<>();
                //通用查询关键字
                String search = dateMatcher.group();
                map.put("search", search);
                list.add(map);
            }
            page.setSearchMap(list);
        }
        String drType = page.getDrType();
        if (StringUtils.isNotEmpty(drType)){
            String[] split = drType.split(",");
            page.setDrType(split[0]);
        }
        return dao.queryByPage(page);
    }

}
