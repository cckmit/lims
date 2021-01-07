package com.adc.da.agent.service;

import com.adc.da.agent.page.AgentEOPage;
import com.adc.da.base.page.Pager;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.agent.dao.AgentEODao;
import com.adc.da.agent.entity.AgentEO;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * <br>
 * <b>功能：</b>TP_AGENT AgentEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("agentEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AgentEOService extends BaseService<AgentEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AgentEOService.class);

    @Autowired
    private AgentEODao dao;

    public AgentEODao getDao() {
        return dao;
    }

    /**
     * 分页查询1（代理人查询）
     * @param page
     * @param searchPhrase
     * @return
     * @throws Exception
     */
    public List<AgentEO> queryByPage(AgentEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.queryByPage(page);
    }

    /**
     * 分页查询2（委托人查询）
     * @param page
     * @param searchPhrase
     * @return
     * @throws Exception
     */
    public List<AgentEO> queryByPage2(AgentEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.queryByPage2(page);
    }

    /**
     * 配置查询条件
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public AgentEOPage pageSet(AgentEOPage page, String searchPhrase) throws Exception {
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
        page.setCreateById(UserUtils.getUserId());
        page.setOrderBy(page.getCreateDate());
        page.setOrder("DESC");
        page.setPager(new Pager());
        return page;
    }

    /**
     * 条数查询2（委托人查询）
     * @param page
     * @return
     */
    public int queryByCount2(AgentEOPage page){
        return dao.queryByCount2(page);
    }

}
