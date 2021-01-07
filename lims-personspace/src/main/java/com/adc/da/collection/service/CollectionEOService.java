package com.adc.da.collection.service;

import com.adc.da.base.page.Pager;
import com.adc.da.collection.page.CollectionEOPage;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.util.utils.DateUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.collection.dao.CollectionEODao;
import com.adc.da.collection.entity.CollectionEO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <br>
 * <b>功能：</b>TP_COLLECTION CollectionEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("collectionEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CollectionEOService extends BaseService<CollectionEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(CollectionEOService.class);

    @Autowired
    private CollectionEODao dao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    public CollectionEODao getDao() {
        return dao;
    }

    /**
     * 分页查询
     * @param page
     * @param searchPhrase
     * @return
     * @throws Exception
     */
    public List<CollectionEO> queryByPage(CollectionEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page,searchPhrase );
        return dao.queryByPage(page);
    }


    /**
     * 配置查询条件
     * @param page
     * @param searchPhrase
     * @return
     */
    public CollectionEOPage pageSet(CollectionEOPage page, String searchPhrase) throws Exception {
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
        //当前用户
//        page.setCcCreateById(UserUtils.getUser().getUsid());
        page.setPager(new Pager());

        return page;
    }

    /**
     * 新增
     * @param collectionEO
     * @return
     */
    public int insertSelective(CollectionEO collectionEO){
        //变更收藏状态
        baseBusEODao.collectionStatus(collectionEO.getSendlink(), "1");
        collectionEO.setCcCreateById(UserUtils.getUserId());
        collectionEO.setCollectiontime(DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        collectionEO.setDelFlag(0l);
        return dao.insertSelective(collectionEO);
    }

    /**
     * 取消收藏
     * @param id
     */
    public void deleteByPrimaryKey(String id){
        CollectionEO collectionEO = dao.selectByPrimaryKey(id);
        baseBusEODao.collectionStatus(collectionEO.getSendlink(), "0");
        dao.deleteByPrimaryKey(id);
    }

}
