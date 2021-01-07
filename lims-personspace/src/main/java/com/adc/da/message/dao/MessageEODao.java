package com.adc.da.message.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.page.MessageEOPage;

import java.util.List;

/**
 * <br>
 * <b>功能：</b>TP_MESSAGE MessageEODao<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
public interface MessageEODao extends BaseDao<MessageEO> {

    /**
     * 标记已读
     *
     * @param idsArr
     * @return
     */
    int updateByIds(String[] idsArr);

    int insertBatch(List<MessageEO> messageEOList);

    /**
     * 查询未读消息通知的数量
     */
    int queryIsread(String usIds);

    int queryBySize(MessageEOPage page);
}
