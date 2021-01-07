package com.adc.da.agent.dao;

import com.adc.da.agent.page.AgentEOPage;
import com.adc.da.base.dao.BaseDao;
import com.adc.da.agent.entity.AgentEO;

import java.util.List;

/**
 *
 * <br>
 * <b>功能：</b>TP_AGENT AgentEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface AgentEODao extends BaseDao<AgentEO> {

    /**
     * weituoren chaxun 2
     * @param page
     * @return
     */
    public int queryByCount2(AgentEOPage page);

    public List<AgentEO>  queryByPage2(AgentEOPage page);

}
