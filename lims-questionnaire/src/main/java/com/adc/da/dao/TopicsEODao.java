package com.adc.da.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.base.page.BasePage;
import com.adc.da.entity.TopicsEO;

import java.util.List;

public interface TopicsEODao extends BaseDao<TopicsEO> {
    List<TopicsEO> manageQueryByPage(BasePage page);
}