package com.adc.da.sys.controller;

import javax.servlet.http.HttpServletRequest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adc.da.util.utils.PublicKeyMap;
import com.adc.da.util.utils.RSAUtils;

/**
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * 用于RSA加密
 * date 2018/08/13
 *
 * @see RSAUtils
 */
@RestController
@RequestMapping("/${restPath}/sys/rsa")
@Api(tags = { "Sys-RSA加密" })
public class RsaSecurityController {

    /**
     * 用于RSA加密 ， 基于session中id生成密钥
     *
     * @param request http请求
     * @return 返回公钥
     */
    @GetMapping("/modulus/exponent")
    public PublicKeyMap generateModulusAndExponent(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        PublicKeyMap publicKeyMap = RSAUtils.getPublicKeyMap(sessionId);
        if (publicKeyMap == null) {
            /* 初始化密钥组 */
            RSAUtils.generateKeyPair(sessionId);
            /* 获取公钥 */
            publicKeyMap = RSAUtils.getPublicKeyMap(sessionId);
        }
        return publicKeyMap;
    }

}
