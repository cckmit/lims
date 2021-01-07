package com.adc.da.message.util;

import com.adc.da.common.ConstantUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.util.utils.StringUtils;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppPushUtil {

    public static String appId;

    public static String appKey;

    public static String masterSecret;

    public AppPushUtil(@Value("${appId}") String appId, @Value("${appKey}") String appKey, @Value("${masterSecret}") String masterSecret){
        this.appId = appId;
        this.appKey = appKey;
        this.masterSecret = masterSecret;
    }

    static String host = "www.baidu.com";

    public static void msgPush(MessageEO messageEO){
        String clientId = ConstantUtils.CLIENT_MAP.get(messageEO.getCcCreateById());
        if(StringUtils.isNotEmpty(clientId)) {
            IGtPush push = new IGtPush(appKey, masterSecret);
            NotificationTemplate template = getNotificationTemplate(messageEO);
            SingleMessage message = new SingleMessage();
            message.setOffline(true);
            // 离线有效时间，单位为毫秒
            message.setOfflineExpireTime(24 * 3600 * 1000);
            message.setData(template);
            // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
            message.setPushNetWorkType(0);
            Target target = new Target();
            target.setAppId(appId);
            target.setClientId(clientId);
            //target.setAlias(Alias);
            IPushResult ret = null;
            try {
                ret = push.pushMessageToSingle(message, target);
            } catch (RequestException e) {
                e.printStackTrace();
                ret = push.pushMessageToSingle(message, target, e.getRequestId());
            }
            if (ret != null) {
                System.out.println(ret.getResponse().toString());
            } else {
                System.out.println("服务器响应异常");
            }
        }

    }

    public static NotificationTemplate getNotificationTemplate(MessageEO messageEO) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(messageEO.getTitle());
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
//        style.setChannel("通知渠道id");
//        style.setChannelName("通知渠道名称");
//        style.setChannelLevel(3); //设置通知渠道重要性
        template.setStyle(style);
        template.setTransmissionType(1);  // 透传消息接受方式设置，1：立即启动APP，2：客户端收到消息后需要自行处理
        template.setTransmissionContent("请输入您要透传的内容");
        return template;
    }

    public static void main(String [] str){
//        msgPush("");
    }



    

}
