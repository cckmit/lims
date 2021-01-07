package com.adc.da.message.service;

import com.adc.da.acttask.page.ActTaskEOPage;
import com.adc.da.acttask.service.ActTaskEOService;
import com.adc.da.base.page.Pager;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.dao.MessageEODao;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.page.MessageEOPage;
import com.adc.da.message.util.AppPushUtil;
import com.adc.da.message.util.WebSocketUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <br>
 * <b>功能：</b>TP_MESSAGE MessageEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-23 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("messageEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class MessageEOService extends BaseService<MessageEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(MessageEOService.class);

    @Autowired
    private MessageEODao dao;

    @Autowired
    private ActTaskEOService actTaskEOService;

    public MessageEODao getDao() {
        return dao;
    }

    /**
     * 分页
     *
     * @param page
     * @param searchfield
     * @return
     * @throws Exception
     */
    public List<MessageEO> queryByPage(MessageEOPage page, String searchfield) throws Exception {
        //配置查询条件
        page = pageSet(page, searchfield);
        return dao.queryByPage(page);
    }

    /**
     * 配置查询条件
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public MessageEOPage pageSet(MessageEOPage page, String searchPhrase) throws Exception {
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
        page.setCcCreateById(UserUtils.getUser().getUsid());
        page.setPager(new Pager());

        return page;
    }

    /**
     * 发送消息通知
     *
     * @param messageEO
     * @throws IOException
     */
    public void sendMessage(MessageEO messageEO) throws IOException {
        if (StringUtils.isNotEmpty(messageEO.getCcCreateById())) {
            //判断是否已经连接socket
            if (com.adc.da.util.utils.StringUtils.isNotEmpty(WebSocketUtil.webSocketMap.get(messageEO.getCcCreateById()))) {
                //socket连接
                WebSocketUtil webSocketUtil = WebSocketUtil.webSocketMap.get(messageEO.getCcCreateById());
                //判断该连接是否失效
                if (WebSocketUtil.webSocketSet.contains(webSocketUtil)) {
                    // 发送消息通知
                    WebSocketUtil.sendMessage(messageEO.getTitle(), webSocketUtil);
                    //更新消息通知个数
                    MessageEOPage page = new MessageEOPage();
                    page.setCcCreateById(messageEO.getCcCreateById());
                    int msgCount = dao.queryBySize(page);
                    WebSocketUtil.sendMessage("MSGCOUNT:"+msgCount, webSocketUtil);
                    //更新待办个数
                    ActTaskEOPage actPage = new ActTaskEOPage();
                    actPage.setCurrUserId(messageEO.getCcCreateById());
                    int ruTaskCount = actTaskEOService.queryRuCount(actPage);
                    WebSocketUtil.sendMessage("RUTASKCOUNT:"+ruTaskCount, webSocketUtil);
                } else {
                    //已经失效的话，删除掉失效的连接
                    WebSocketUtil.webSocketMap.remove(messageEO.getCcCreateById());
                }
            }
            //以下代码是移动端个推  确认废弃
//            AppPushUtil.msgPush(messageEO);
        }
    }

    /**
     * 标记已读
     *
     * @param ids
     */
    public void updateByIds(String ids) {
        String[] idsArr = ids.split(",");
        dao.updateByIds(idsArr);
    }

    public int insertBatch(List<MessageEO> messageEOList) {
        return dao.insertBatch(messageEOList);
    }


    /**
     * 查询未读消息通知的数量
     */
    public int queryIsread(String usId) {
        return dao.queryIsread(usId);
    }

    /**
     * 查询未读消息通知的数量
     */
    public int queryBySize(MessageEOPage page) {
        page.setCcCreateById(UserUtils.getUserId());
        return dao.queryBySize(page);
    }
}
