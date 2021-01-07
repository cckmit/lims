package com.adc.da.message.controller;

import com.adc.da.base.web.BaseController;
import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.login.util.UserUtils;
import com.adc.da.login.vo.LoginVO;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.page.MessageEOPage;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.message.util.WebSocketUtil;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.http.PageInfo;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/${restPath}/message/message")
@Api(tags = "PersonSpace-消息通知")

public class MessageEOController extends BaseController<MessageEO> {

    private static final Logger logger = LoggerFactory.getLogger(MessageEOController.class);

    @Autowired
    private MessageEOService messageEOService;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private UserEODao userEODao;

    @Value("${mail.address}")
    private String mailAddress;

    @BusinessLog(description = "个人空间-消息通知-分页查询")
    @ApiOperation(value = "|MessageEO|分页查询")
    @GetMapping("/page")
//    @RequiresPermissions("message:message:page")
    @EnableAccess
    public ResponseMessage<PageInfo<MessageEO>> page(MessageEOPage page, String searchfield) throws Exception {
        try {
            List<MessageEO> rows = messageEOService.queryByPage(page, searchfield);
            page.getPager().setRowCount(messageEOService.queryByCount(page));
            return Result.success("0", "查询成功", getPageInfo(page.getPager(), rows));
        } catch (Exception e) {
            logger.error(e.getMessage(), MessageEO.class);
            return Result.error("-1", "查询失败");
        }
    }

    @BusinessLog(description = "个人空间-消息通知-总数")
    @ApiOperation(value = "|MessageEO|分页查询")
    @GetMapping("/count")
//    @RequiresPermissions("message:message:count")
    public ResponseMessage<String> count(MessageEOPage page) throws Exception {
        try {
            int count = messageEOService.queryBySize(page);
            return Result.success("0", "查询成功", count + "");
        } catch (Exception e) {
            logger.error(e.getMessage(), MessageEO.class);
            return Result.error("-1", "查询失败");
        }
    }


    @BusinessLog(description = "个人空间-我的收藏-查看")
    @ApiOperation(value = "|MessageEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("message:message:get")
    public ResponseMessage<MessageEO> find(@PathVariable String id) throws Exception {
        return Result.success("0", "查询成功", messageEOService.selectByPrimaryKey(id));
    }

    @BusinessLog(description = "个人空间-我的收藏-新增")
    @ApiOperation(value = "|MessageEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
//    @RequiresPermissions("message:message:save")
    public ResponseMessage<MessageEO> create(@RequestBody MessageEO messageEO) throws Exception {
        messageEOService.insertSelective(messageEO);
        return Result.success("0", "新增成功", messageEO);
    }

    @BusinessLog(description = "个人空间-我的收藏-已读")
    @ApiOperation(value = "|MessageEO|标记已读")
    @GetMapping("/update")
//    @RequiresPermissions("message:message:update")
    @EnableAccess
    public ResponseMessage<MessageEO> update(String ids) throws Exception {
        messageEOService.updateByIds(ids);
        return Result.success("0", "标记成功");
    }

    @BusinessLog(description = "个人空间-发送邮件测试")
    @ApiOperation(value = "|MessageEO|发送邮件测试")
    @PutMapping("/sendEmail")
//    @RequiresPermissions("message:message:sendEmail")
    public ResponseMessage<MessageEO> sendEmail(String ids) throws Exception {
        String token = UUID.randomUUID();
        ConstantUtils.DELAYMAILMAP.put(token, ids);
        MailUtils mailUtils = new MailUtils();
        mailUtils.setToken(token);
        mailUtils.setSubject("测试");
        mailUtils.setReceiver("adc_fudongjie@163.com");
        mailUtils.setContent("<a href='" + mailAddress + token + "'>LIMS待办</a> ");
        emailConfig.sendMailHtml(mailUtils);
        return Result.success("0", "标记成功");
    }

    @BusinessLog(description = "个人空间-我的收藏-个推")
    @ApiOperation(value = "|MessageEO|个推")
    @PostMapping("/getui")
//    @RequiresPermissions("message:message:save")
    public ResponseMessage<MessageEO> getui(@RequestBody MessageEO messageEO) throws Exception {
        messageEOService.sendMessage(messageEO);
        return Result.success("0", "新增成功", messageEO);
    }

    @BusinessLog(description = "个人空间-消息通知-显示未读消息通知的数量")
    @ApiOperation(value = "|MessageEO|查询未读消息通知的数量")
    @GetMapping("/showUnread")
    public ResponseMessage showUnread() throws Exception {

        int count = messageEOService.queryIsread(UserUtils.getUserId());
        return Result.success("0", "查询成功", count);
    }

    @BusinessLog(description = "个人空间-异地登录验证")
    @ApiOperation(value = "|异地登录验证|")
    @PostMapping("/doubleLoginCheck")
    public ResponseMessage<String> doubleLoginCheck(HttpServletRequest request, @RequestBody LoginVO loginVO) throws Exception {
        try{
            String loginName = loginVO.getUsername();
            if(ConstantUtils.LOGIN_USER_MAP.containsKey(loginName)){
                String jsid = ConstantUtils.LOGIN_USER_MAP.get(loginName);
                if(StringUtils.equals(jsid, request.getSession().getId())){
                    return Result.success("0","同一浏览器登录，不做限制");
                } else {
                    UserEO userEO = userEODao.findUserByAccount(loginName);
                    WebSocketUtil webSocketUtil = WebSocketUtil.webSocketMap.get(userEO.getUsid());
                    if(StringUtils.isNotEmpty(webSocketUtil)) {
                        webSocketUtil.sendMessage("logOut", webSocketUtil);
                    }
                    return Result.success("-1","您的账号已在其他地方登录，继续登录则其他地点登录账号会被迫下线！");
                }
            } else {
                return Result.success("0","可以正常登陆！");
            }
        }catch(Exception e){
            logger.error(e.getMessage());
            return Result.error("-1","验证失败！");
        }
    }

}
