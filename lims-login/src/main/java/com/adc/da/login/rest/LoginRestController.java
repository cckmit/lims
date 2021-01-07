package com.adc.da.login.rest;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import com.adc.da.common.ConstantUtils;
import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.log.service.LogEOService;
import com.adc.da.login.security.SystemAuthorizingRealm;
import com.adc.da.login.service.AreaLoginService;
import com.adc.da.sys.service.MenuEOService;
import com.adc.da.sys.util.LogUtils;
import com.adc.da.util.utils.*;
import io.loadkit.Res;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.adc.da.login.entity.OnlineUserEO;
import com.adc.da.login.security.UsernamePasswordToken;
import com.adc.da.login.security.exception.CaptchaException;
import com.adc.da.login.security.validatecode.IVerifyCodeGen;
import com.adc.da.login.security.validatecode.SimpleCharVerifyCodeGenImpl;
import com.adc.da.login.security.validatecode.VerifyCode;
import com.adc.da.login.service.OnlineUserListener;
import com.adc.da.login.util.CacheUtils;
import com.adc.da.login.util.UserUtils;
import com.adc.da.login.vo.LoginVO;
import com.adc.da.login.vo.OnlineUserVO;
import com.adc.da.sys.annotation.EnableAccess;
import com.adc.da.sys.entity.MenuEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.thymeleaf.templateresolver.ITemplateResolutionValidity;

/**
 * 登录接口
 * 1.登录 get,已弃用
 * 2.登录 post
 * 3.登出
 * 4.修改密码
 * 5.修改用户信息
 * 6.获取登录用户信息
 * 7.获取登录用户菜单权限，已弃用
 * 8.生成4位验证码
 * 9.生成6位验证码
 *
 * @author comments created by Lee Kwanho
 * date 2018-09-06
 **/
@Validated
@Controller
@RequestMapping(value = "${restPath}/")
@Api(tags = "Login-登录模块")
@DependsOn(value = "configBeforeInit")     //读取自定义的域登陆账号配置
public class LoginRestController {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRestController.class);

    /**
     * 无缓存，用于校验
     */
    private static final String NO_CACHE = "no-cache";

    /**
     * 登录失败Map字段
     */
    private static final String LOGIN_FAIL_MAP = "loginFailMap";

    /**
     * 服务层装配
     *
     * @see UserEOService
     */
    @Autowired
    private UserEOService userService;

    @Autowired
    private SystemAuthorizingRealm systemAuthorizingRealm;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LogEOService logEOService;

    @Autowired
    private MenuEOService menuEOService;


    /**
     * eo vo 转换
     *
     * @see BeanMapper
     * @see dozer
     */
    @Autowired
    BeanMapper beanMapper;

    /**
     * 读取配置文件判断是否需要开启Base64加密
     * 默认值为false
     */
    @Value("${isPassEncrypted:false}")
    private boolean isPassEncrypted;

    @Value("${user.defaultPassword}")
    private String resetPassWord;//重置的密码值


    //域登陆
    @Value("${preUsername}")
    private String preUsername;
    @Value("${prePassword}")
    private String prePassword;
    @Value("${AreaUrl}")
    private String AreaUrl;
    @Value("${AreaServiceUrl}")
    private String AreaServiceUrl;
    @Value("${useAreaLogin}")
    private String useAreaLogin;

    /**
     * 4位验证码
     *
     * @param request  请求信息
     * @param response 返回信息
     */
    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        verifyCode(request, response, 80, 28, 4);
    }

    /**
     * 6位验证码
     *
     * @param request  请求信息
     * @param response 返回信息
     */
    @GetMapping("/verifyCode6")
    public void verifyCode6(HttpServletRequest request, HttpServletResponse response) {
        verifyCode(request, response, 100, 28, 6);
    }

    /**
     * 生成验证码
     * 由 verifyCode6 和 verifyCode 调用
     *
     * @param request  请求信息
     * @param response 返回信息
     * @param width    宽度
     * @param height   高度
     * @param number   字符数
     * @author Lee Kwanho 李坤澔
     * date 2018-09-06
     */
    private void verifyCode(HttpServletRequest request, HttpServletResponse response, int width, int height,
                            int number) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {
            VerifyCode verifyCode = iVerifyCodeGen.generate(width, height, number);
            request.getSession().setAttribute("VerifyCode", verifyCode.getCode());
            response.setHeader("Pragma", NO_CACHE);
            response.setHeader("Cache-Control", NO_CACHE);
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            LOGGER.error("verifyCode Error", e);
        }
    }

    /**
     * 预校验验证码
     *
     * @param request 请求信息
     * @param verify  输入数据
     * @author dingqiang 丁强
     * date 2018-12-18
     */
    @ApiOperation(value = "预校验验证码")
    @PutMapping("/judgeVerify")
    @ResponseBody
    public ResponseMessage judgeVerify(HttpServletRequest request, @RequestParam String verify) {

        String verifyCode = (String) request.getSession().getAttribute("VerifyCode");
        if (verify == null) {
            return Result.error("验证码不能为空！");
        }

        if (null == verifyCode) {
            return Result.error("验证码生成失败，请联系管理员！");
        }

        if (!verify.equalsIgnoreCase(verifyCode)) {
            return Result.error("验证码不正确！");
        }
        return Result.success("验证码输入正确");
    }

    /**
     * Post方式登录
     *
     * @param request 请求体
     * @param loginVO 登录数据
     * @return 登录结果
     */
    @ApiOperation(value = "Post方式登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseMessage postLogin(HttpServletRequest request, @RequestBody LoginVO loginVO) {
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        String verifyCode = loginVO.getVerifyCode();
        System.out.println("-----------------"+request.getSession().getId()+"---------------------");

        if (StringUtils.isBlank(loginVO.getUsername())) {
            return Result.error("r0014", "登录名不能为空");
        }
        if (StringUtils.isBlank(loginVO.getPassword())) {
            return Result.error("r0016", "密码不能为空");
        }
        return login(request, username, password, verifyCode);
    }

    /**
     * 登录非空验整
     * @param loginVO
     */
    public String notNullCheck(LoginVO loginVO){
        String username = loginVO.getUsername();
        String password = loginVO.getPassword();
        String verifyCode = loginVO.getVerifyCode();
        if (StringUtils.isBlank(loginVO.getUsername())) {
            return "登录名不能为空";
        }
        if (StringUtils.isBlank(loginVO.getPassword())) {
            return "密码不能为空";
        }
        return "0";
    }

    /**
     * Post方式登录
     *
     * @param request 请求体
     * @param loginVO 登录数据
     * @return 登录结果
     */
    @ApiOperation(value = "Post方式登录")
    @PostMapping(value = "/mobileLogin")
    @ResponseBody
    @EnableAccess
    public ResponseMessage mobileLogin(HttpServletRequest request, @RequestBody LoginVO loginVO) {
        String res = notNullCheck(loginVO);
        if(!"0".equals(res)){
            return Result.error("-1", res);
        }
        ResponseMessage msg = login(request, loginVO.getUsername(), loginVO.getPassword(), loginVO.getVerifyCode());
        //将用户设备ID和用户id存入全局参数，进行管理。
        if("0".equals(msg.getRespCode())){
            UserVO userVO = (UserVO) msg.getData();
            ConstantUtils.CLIENT_MAP.put(userVO.getUsid(), loginVO.getClientId());
        }
        return msg;
    }

    /**
     * loginPage testcontroller
     *
     * @param request 请求体
     * @return 登录结果
     */
    @ApiOperation(value = "loginPage_betaVersion")
    @GetMapping(value = "/loginPage")
    @ResponseBody
    public ResponseMessage loginPage(HttpServletRequest request) {

        return new ResponseMessage("302", "/login.html", false);
    }

    /**
     * Get方式登录，已弃用
     *
     * @param request      请求体
     * @param username     用户名
     * @param password     密码
     * @param isRememberMe 是否记住密码
     * @param verifyCode   验证码
     * @return 登录信息
     * @author Lee Kwanho 李坤澔
     * date 2018-09-06
     * @deprecated 明文不安全，安全测试不允许使用
     */
    @Deprecated
    @ApiOperation(value = "Get方式登录，弃用")
    @GetMapping(value = "/login")
    @ResponseBody
    public ResponseMessage getLogin(HttpServletRequest request,
                                    @RequestParam @NotNull(message = "请输入用户名") String username,
                                    @RequestParam @NotNull(message = "请输入密码") String password,
                                    @RequestParam(value = "isRememberMe", defaultValue = "false") Boolean isRememberMe,
                                    String verifyCode) {
        return login(request, username, password, verifyCode);
    }


    /**
     * 登录方法
     *
     * @param request    请求体
     * @param username   用户名
     * @param password   密码
     * @param verifyCode 验证码
     * @return 登录信息
     */
    @BusinessLog(description = "用户登录系统")
    private ResponseMessage login(HttpServletRequest request, String username, String password, String verifyCode) {

        Boolean areaLogin = false;
        Boolean serviceStatus  = true;   //远程服务接口状态
        UserEO user = userService.getUserByLoginName(username);
        if(user!=null){
            if("0".equals(user.getExtInfo5()))     return Result.error("-1", "当前用户为锁定状态，不可登录");         //0锁定 1激活可用
        }
        if("0".equals(useAreaLogin)) {
            try {
                areaLogin = AreaLoginService.dflqUserValidateService(AreaUrl, username, password, preUsername, prePassword,AreaServiceUrl);   //域登陆
            } catch (Exception e) {
                e.printStackTrace();
                serviceStatus = false;
//            return Result.error("-1","域登陆校验接口存在异常");     //完成优化为服务挂掉，本地账号成功，在返回的 Result.success中判断code，仍可登陆，但是在页面爆出错误
            }
        }
        Subject subject = SecurityUtils.getSubject();
        UserEO userEO;
        // 前台如果base64传输密文，则需要解码
        if (isPassEncrypted) {
            password = new String(Encodes.decodeBase64(password), StandardCharsets.UTF_8);
        }
        try {
            UsernamePasswordToken usernamePasswordToken = null;

            if(areaLogin)  usernamePasswordToken = new UsernamePasswordToken(username,verifyCode);    //域登陆免密登录
            else  usernamePasswordToken = new UsernamePasswordToken(username, password.toCharArray(), verifyCode);    //普通账号密码登录

            subject.login(usernamePasswordToken);

            userEO = UserUtils.getUser();
            request.getSession().setAttribute(RequestUtils.LOGIN_USER, userEO);

            UserEO returnEO = userService.getUserWithRoles(userEO.getUsid());

            request.getSession().setAttribute(RequestUtils.LOGIN_USER_ID, userEO.getUsid());
            request.getSession().setAttribute(RequestUtils.LOGIN_ROLE_ID, UserUtils.getRoleIds());

            // 记录登录在线人数
            OnlineUserEO onlineUserEO = new OnlineUserEO();
            onlineUserEO.setAccount(username);
            onlineUserEO.setIp(IpUtil.getIpAddr(request));
            onlineUserEO.setLoginTime(new Date());

            final Serializable onlineUserListener = new OnlineUserListener(onlineUserEO);

            request.getSession(false).setAttribute(userEO.getUsname(), onlineUserListener);
            delLoginErrorCount(username);

            //更新登录系统日志
            LogUtils.writeLog("com.adc.da.login.rest.LoginRestController","login",
                    userEO.getUsname()+"-登录系统",userEO.getUsid(),System.currentTimeMillis(),request);
            //记录登录状态，保存到全局变量
            ConstantUtils.LOGIN_USER_MAP.put(username, request.getSession().getId());
            if(serviceStatus) return Result.success("0",beanMapper.map(returnEO, UserVO.class));
            else return Result.success("-1","本地账号登陆成功,但域登陆校验接口存在异常",beanMapper.map(returnEO, UserVO.class));

        } catch (CaptchaException e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.info("验证码验证失败");
            return Result.error("r0012", "您输入的验证码不正确", systemAuthorizingRealm.isNeedValidCode(username));
        } catch (UnknownAccountException e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.info("用户[{}]身份验证失败", username);
            return Result.error("r0011", "您输入的帐号或密码有误", systemAuthorizingRealm.isNeedValidCode(username));
        } catch (IncorrectCredentialsException e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.info("用户[{}]密码验证失败", username);
            return Result.error("r0011", "您输入的帐号或密码有误", systemAuthorizingRealm.isNeedValidCode(username));
        } catch (Exception e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.error(e.getMessage(), e);
            return Result.error("r0013", e.getMessage(), systemAuthorizingRealm.isNeedValidCode(username));
        }

    }

    /**
     * 获取在线用户
     *
     * @param response 请求体
     * @return 当前在线用户列表
     */
    @ApiOperation(value = "获取在线用户")
    @GetMapping("/onlineUser")
    @ResponseBody
    public ResponseMessage<OnlineUserVO> onlineUser(HttpServletResponse response) {
        Map<String, OnlineUserEO> map = OnlineUserListener.getOnlineMap();
        List<OnlineUserEO> onlineUsers = new ArrayList<>(map.values());
        OnlineUserVO onlineUserVO = new OnlineUserVO();
        onlineUserVO.setOnlineUsers(onlineUsers);
        onlineUserVO.setTotal(onlineUsers.size());
        return Result.success(onlineUserVO);
    }

    /**
     * 退出登录
     *
     * @return success
     */
    @ApiOperation(value = "退出登录")
    @GetMapping("/logout")
    @ResponseBody
    @BusinessLog(description = "用户管理-退出系统")
    @EnableAccess
    public ResponseMessage logout() throws Exception {
        String account = UserUtils.getUser().getAccount();
        //删除用户登录全局变量
        ConstantUtils.LOGIN_USER_MAP.remove(account);
        UserUtils.logout();
        return Result.success();
    }

    /**
     * 登录成功之后获取当前登录用户信息的接口
     *
     * @param response 请求体
     * @return 当前用户信息
     */
    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/userInfo")
    @ResponseBody
    public ResponseMessage<UserVO> userInfo(HttpServletResponse response) {

        try {
            UserEO user = UserUtils.getUser();
            if (user != null) {
                String id = user.getUsid();
                /* 获取用户信息，角色信息，组织信息 */
                UserVO userVO = beanMapper.map(userService.getUserWithRoles(id), UserVO.class);
                return Result.success(userVO);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return Result.error("401", "未授权", null);
            }
        } catch (Exception e) {
            LOGGER.error("获取登录用户信息", e);
            return Result.error("500", "获取登录用户信息" + e.getMessage(), null);
        }

    }

    /**
     * 获取用户菜单,已用菜单管理实现
     *
     * @return 菜单权限
     * @author Lee Kwanho 李坤澔
     * date 2018-09-06
     * @see com.adc.da.sys.controller.MenuEOController
     * @deprecated 使用菜单管理替代
     */
    @ApiOperation(value = "获取登录用户菜单权限")
    @GetMapping("/userMenu")
    @ResponseBody
    @Deprecated
    public ResponseMessage<MenuEO> userMenu() {
        return Result.success(UserUtils.getMenuTree());
    }

    /**
     * 用于修改当前用户信息，与用户管理相似，但是不能修改他人信息（会强制替换为当前用户）
     *
     * @param response 请求体，获取登录信息
     * @param userVO   获取修改信息
     * @author Lee Kwanho 李坤澔
     * date 2018-10-17
     */
    @ApiOperation(value = "修改当前用户信息")
    @PutMapping("/updateUserInfo")
    @ResponseBody
    @BusinessLog(description = "用户管理-修改当前用户信息")
    public ResponseMessage updateUserInfo(HttpServletResponse response, @RequestBody UserVO userVO) {
        String updateTime = DateUtils.dateToString(new Date(), DateUtils.YYYY_MM_DD_HH_MM_SS_EN);
        userVO.setUpdateTime(updateTime);

        try {
            /*
             * 替换usid 为当前登录用户
             * 不能修改ID，账户account，用户名，以及工号
             */
            UserEO user = UserUtils.getUser();
            userVO.setUsid(user.getUsid());
            userVO.setAccount(user.getAccount());
            userVO.setUsname(user.getUsname());
            userVO.setUserCode(user.getUserCode());
            /* 更新返回用户角色信息/ 不能修改角色*/
            userService.updateByPrimaryKeySelective(beanMapper.map(userVO, UserEO.class));

            /* 更新头像信息 */
            if (StringUtils.isNotEmpty(userVO.getAvatar())) {
                userService.saveUserAvatar(userVO.getUsid(), userVO.getAvatar());
            }
            /* 返回值附带角色组织等信息 */
            return Result.success(beanMapper.map(userService.getUserWithRoles(user.getUsid()), UserVO.class));

        } catch (Exception e) {
            LOGGER.error("修改当前用户信息 error", e);
            return Result.error("修改当前用户信息 error" + e.getMessage());
        }
    }

    /**
     * 用于修改密码,当前用户
     * 会进行校验
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 若成功修改则返回成功
     */
    @ApiOperation(value = "修改密码")
    @PutMapping("/updatePassword")
    @ResponseBody
    @BusinessLog(description = "用户管理-修改密码")
    public ResponseMessage updatePassword(@NotNull(message = "请输入旧密码") @RequestParam String oldPassword,
                                          @NotNull(message = "请输入新密码") @RequestParam String newPassword) {
        // 前台如果base64传输密文，则需要解码
        if (isPassEncrypted) {
            oldPassword = new String(Encodes.decodeBase64(oldPassword), StandardCharsets.UTF_8);
            newPassword = new String(Encodes.decodeBase64(newPassword), StandardCharsets.UTF_8);
        }
        //  ^(?=.*[a-zA-Z])(?=.*\d)(?=.*[#@!~%^&*.])[a-zA-Z\d#@!~%^&*.]{6,10}$
        //  ^(?![0-9]*$)[a-zA-Z0-9]{6,10}$
        //  ^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_]+$)(?![a-z0-9]+$)(?![a-z\W_]+$)(?![0-9\W_]+$)[a-zA-Z0-9\W_]{8,16}$
        if (!newPassword.matches("^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$")) {
            return Result.error("r0018", "新密码至少满足8-16位数字,大写字母,小写字母,特殊符号其中三种");
        }
        String userId =UserUtils.getUserId();
        userService.updatePassword(userId, oldPassword, newPassword);
        return Result.success("0","操作成功！");
    }

    
    
    public static void main(String[] args) {
		String a = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$";
		
		String newPassword = "12346aA!@#$%^";
		
		System.err.println(newPassword.matches(a));
	}
    
    
    
    @ApiOperation(value = "重置密码")
    @PutMapping("/resetPassword")
    @ResponseBody
    @BusinessLog(description = "用户管理-重置密码")
    public ResponseMessage<String> resetPassword(@NotNull @RequestParam String userId){
        userService.resetPassword(userId,resetPassWord);
        return Result.success("成功重置密码为"+resetPassWord);
    }

    @ApiOperation(value = "获取菜单权限")
    @GetMapping("/findMenus")
    @ResponseBody
    public ResponseMessage<List<MenuEO>> findMenus (){
        try {
            String usid = UserUtils.getUserId();
            List<MenuEO> menuEOList = menuEOService.listMenuEOByUserId(usid);
            return Result.success("0", "SUCCESS", menuEOList);
        }catch (Exception e){
            return Result.error("401","获取权限失败");
        }
    }

    /**
     * 登录成功清除失败记录
     *
     * @param userName 用户名
     * @author Dingqiang
     */
    private void delLoginErrorCount(String userName) {
        Map<String, Integer> loginFailMap = (Map<String, Integer>) CacheUtils.getErrorCache(LOGIN_FAIL_MAP);
        /*
         *  非空直接清除失败次数,没必要进行初始化等操作
         *  @author Lee Kwanho 李坤澔
         *  date 2019-01-30
         */
        if (loginFailMap != null) {
            loginFailMap.put(userName, 0);
        }

    }

    /**
     * Post方式登录
     *
     * @param request 请求体
     * @param userId 登录id
     * @return 登录结果
     */
    @ApiOperation(value = "代理人方式登录")
    @PostMapping(value = "/agentLogin")
    @ResponseBody
    public ResponseMessage agentLogin(HttpServletRequest request, String userId) {
        try {
            //判断是否有权限登录
            //userId ：被代理人Id    UserUtils.getUserId() ： 当前登录人/代理人ID
            int count = userService.findAgent(userId, UserUtils.getUserId());
            if(count<=0){
                return Result.error("-1","没有代理登录权限或权限已过期！");
            }
            UserEO user = userService.selectByPrimaryKey(userId);
            String username = user.getAccount();
            return agentLogin2(request, username);
        }catch(Exception e){
            return  Result.error("-1","代理登录失败！");
        }
    }


    /**
     * Post方式登录
     *
     * @param request 请求体
     * @param token 登录数据
     * @return 登录结果
     */
    @ApiOperation(value = "邮件链接方式登录")
    @GetMapping(value = "/mailLogin")
    @ResponseBody
    public ResponseMessage mailLogin(HttpServletRequest request, HttpServletResponse response, String token) {
        try {
            String userId = ConstantUtils.DELAYMAILMAP.get(token);
            if(StringUtils.isEmpty(userId)){
                return Result.error("401","已超出登录时间！");
            }
            UserEO user = userService.selectByPrimaryKey(userId);
            String username = user.getAccount();
            agentLogin2(request, username);
            return Result.success("0","todo",beanMapper.map(user, UserVO.class));
        }catch(Exception e){
            return  Result.error();
        }
    }



    /**
     * 代理人登录
     * @param request
     * @param username
     * @return
     */
    public ResponseMessage agentLogin2(HttpServletRequest request, String username){

        Subject subject = SecurityUtils.getSubject();
        UserEO userEO;
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username,"");
            subject.login(token);
            //获取用户
            userEO = UserUtils.getUser();
            request.getSession().setAttribute(RequestUtils.LOGIN_USER, userEO);

            UserEO returnEO = userService.getUserWithRoles(userEO.getUsid());

            request.getSession().setAttribute(RequestUtils.LOGIN_USER_ID, userEO.getUsid());
            request.getSession().setAttribute(RequestUtils.LOGIN_ROLE_ID, UserUtils.getRoleIds());

            // 记录登录在线人数
            OnlineUserEO onlineUserEO = new OnlineUserEO();
            onlineUserEO.setAccount(username);
            onlineUserEO.setIp(IpUtil.getIpAddr(request));
            onlineUserEO.setLoginTime(new Date());

            final Serializable onlineUserListener = new OnlineUserListener(onlineUserEO);

            request.getSession(false).setAttribute(userEO.getUsname(), onlineUserListener);
            delLoginErrorCount(username);

            //更新登录系统日志
            LogUtils.writeLog("com.adc.da.login.rest.LoginRestController","login",
                    userEO.getUsname()+"-登录系统",userEO.getUsid(),System.currentTimeMillis(),request);
            return Result.success(beanMapper.map(returnEO, UserVO.class));

        } catch (CaptchaException e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.info("验证码验证失败");
            return Result.error("r0012", "您输入的验证码不正确", systemAuthorizingRealm.isNeedValidCode(username));
        } catch (UnknownAccountException e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.info("用户[{}]身份验证失败", username);
            return Result.error("r0011", "您输入的帐号或密码有误", systemAuthorizingRealm.isNeedValidCode(username));
        } catch (IncorrectCredentialsException e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.info("用户[{}]密码验证失败", username);
            return Result.error("r0011", "您输入的帐号或密码有误", systemAuthorizingRealm.isNeedValidCode(username));
        } catch (Exception e) {
            systemAuthorizingRealm.increaseLoginErrorCount(username);
            LOGGER.error(e.getMessage(), e);
            return Result.error("r0013", e.getMessage(), systemAuthorizingRealm.isNeedValidCode(username));
        }

    }

    @ApiOperation("验证登录是否过期")
    @GetMapping("/checkSession")
    @ResponseBody
    public ResponseMessage checkSession(){
        return Result.success("0","ok");
    }


}
