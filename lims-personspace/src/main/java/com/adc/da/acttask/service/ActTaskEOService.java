package com.adc.da.acttask.service;

import com.adc.da.acttask.dao.ActTaskEODao;
import com.adc.da.acttask.entity.ActTaskEO;
import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("actTaskEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED,
        rollbackFor = Throwable.class)
public class ActTaskEOService extends BaseService<ActTaskEO, String> {

    @Autowired
    private ActTaskEODao dao;

    @Autowired
    private BaseBusEODao baseBusEODao;

    @Override
    public ActTaskEODao getDao() {
        return dao;
    }

    /**
     * 待办分页
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<ActTaskEO> ruTaskPage(ActTaskEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.ruTaskPage(page);
    }

    /**
     * 已办分页
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<ActTaskEO> hiTaskPage(ActTaskEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.hiTaskPage(page);
    }

    /**
     * 跟踪分页
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<ActTaskEO> trackTaskPage(ActTaskEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.trackTaskPage(page);
    }

    /**
     * 分页数据
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public ActTaskEOPage pageSet(ActTaskEOPage page, String searchPhrase) throws Exception {
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
        page.setCurrUserId(UserUtils.getUser().getUsid());
        return page;
    }

    /**
     * 待办总数
     * @param page
     * @return
     */
    public int queryRuCount(ActTaskEOPage page){
        return dao.queryRuCount(page);
    }

    /**
     * 已办总数
     * @param page
     * @return
     */
    public int queryHiCount(ActTaskEOPage page){
        return dao.queryHiCount(page);
    }

    /**
     * 已办总数
     * @param page
     * @return
     */
    public int queryTrackCount(ActTaskEOPage page){
        return dao.queryTrackCount(page);
    }

    /**
     * 根据baseBusId获取流程信息
    * @Title：getApprovalInfoById
    * @param baseBusId
    * @return
    * @return: ActTaskEO
    * @author： ljy  
    * @date： 2019年9月20日
     */
    public ActTaskEO getApprovalInfoById(String baseBusId) {
    	ActTaskEOPage page = new ActTaskEOPage();
    	page.setBusinessKey(baseBusId);
    	page.setPage(1);
    	page.setPageSize(1);
    	List<ActTaskEO> list = dao.ruTaskPage(page);
    	if(CollectionUtils.isNotEmpty(list)) {
    		return list.get(0);
    	}
    	return null;
    }

    /**
     *
     * @param id
     * @return
     */
    public String findRequestEOByBusinessId(String id){
        List<BaseBusEO> baseBusEOList = baseBusEODao.selectByBusinessId(id);
        if(CollectionUtils.isNotEmpty(baseBusEOList)){
            return dao.queryTaskId(baseBusEOList.get(0).getId());
        }
        return "Error";
    }

    /**
     * findProcIdByBusinessId
     * @param id
     * @return
     */
    public String findProcIdByBusinessId(String id){
        List<BaseBusEO> baseBusEOList = baseBusEODao.selectByBusinessId(id);
        if(CollectionUtils.isNotEmpty(baseBusEOList)){
            return dao.queryProcId(baseBusEOList.get(0).getId());
        }
        return "Error";
    }
    
}
