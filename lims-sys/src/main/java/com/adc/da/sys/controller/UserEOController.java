package com.adc.da.sys.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.entity.UserExternalTrainingEO;
import com.adc.da.sys.entity.UserInternalTrainingEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.sys.service.UserExternalTrainingEOService;
import com.adc.da.sys.service.UserInternalTrainingEOService;
import com.adc.da.sys.vo.UserExternalTrainingVO;
import com.adc.da.sys.vo.UserInternalTrainingVO;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.RequestUtils;
import com.adc.da.file.service.FileEOService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.adc.da.base.page.Pager;
import com.adc.da.base.web.BaseController;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.page.UserEOPage;
import com.adc.da.sys.service.UserEOService;
import com.adc.da.sys.vo.UserVO;
import com.adc.da.util.http.ResponseMessage;
import com.adc.da.util.http.Result;
import com.adc.da.util.utils.BeanMapper;
import com.adc.da.util.http.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理模块相关接口
 * 1.详情
 * 2.分页查询
 * 3.新增
 * 4.修改
 * 5.删除
 * 6.配置角色信息
 * 7.批量配置用户角色信息 @author 李坤澔
 * 8.批量配置用户组织信息 @author 李坤澔
 *
 *
 * @author comments created by Lee Kwanho
 * date 2018/08/13
 * @see UserEOService
 * @see UserEO
 * @see UserVO
 * @see UserEOPage
 * @see com.adc.da.sys.dao.UserEODao
 * @see mybatis.mapper.sys
 */
@RestController
@RequestMapping("/${restPath}/sys/user")
@Api(tags = { "Sys-用户管理" })
public class UserEOController extends BaseController<UserEO> {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(UserEOController.class);

    /**
     * //匹配除了空格以外的任意字符
     */
    public static final String REGEX_EXCEPT_BLANK = "[^\\s]+";

    /**
     * 用户service自动装配
     *
     * @see UserEOService
     */
    @Autowired
    private UserEOService userEOService;

    @Autowired
    private OrgEOService orgEOService;

    @Autowired
    private FileEOService fileEOService;

    @Autowired
    private UserExternalTrainingEOService userExternalTrainingEOService;

    @Autowired
    private UserInternalTrainingEOService userInternalTrainingEOService;

    /**
     * dozer相关，EO间VO转换
     *
     * @see dozer
     */
    @Autowired
    private BeanMapper beanMapper;

    /**
     * 显示单一用户信息
     * 调用beanMapper 进行vo与eo间的转换
     * 权限字段：sys:user:get
     * '@PathVariable' id 对应 GetMapping 中的{id}
     * '@NotNull' 限制字段不能为空
     *
     * @param id 用户id
     * @return 用户详情
     */
    @ApiOperation(value = "|UserEO|详情")
    @GetMapping("/{id}")
    @RequiresPermissions("sys:user:get")
    public ResponseMessage<UserVO> getById(@NotNull @PathVariable("id") String id) {
        UserVO userVO = beanMapper.map(userEOService.getUserWithRoles(id), UserVO.class);
        return Result.success(userVO);
    }

    /**
     * 分页查询，可设定分页大小，页码，用户ID，角色ID，组织ID，
     * 若出现异常则会记录日志，返回异常信息，
     * 关于模糊查询可以有两种方式处理，一种是在java中拼接%，另外一种是在xml中拼接%，举例如下
     * 权限字段：sys:user:list
     *
     * @param pageNo   页码
     * @param pageSize 分页大小
     * @param usName   用户ID
     * @param roleName 角色名称
     * @param orgId    组织名称
     * @return 返回用户详细信息
     */
    @BusinessLog(description = "用户管理-查询所有用户")
    @ApiOperation(value = "|UserEO|分页查询")
    @GetMapping("")
    @RequiresPermissions("sys:user:list")
    public ResponseMessage<PageInfo<UserVO>> page(Integer pageNo, Integer pageSize,
                                                  String usName, String roleName, String orgId,
                                                  String searchPhrase,String account,String userCode,
                                                  String cellPhoneNumber,String email,String orgName,
                                                  String usposition) {
        UserEOPage page = new UserEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }

        if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
            //通用查询
            searchPhrase = searchPhrase.trim();
            String patten = REGEX_EXCEPT_BLANK;
            Pattern datapattern = Pattern.compile(patten);
            Matcher matcher = datapattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (matcher.find()){
                String search = matcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
            page.setSearchPhraseOperation("LIKE");
        }else{
            //普通查询
            if (StringUtils.isNotEmpty(account)){
                //登录账号查询
                page.setAccount(account);
                page.setAccountOperator("LIKE");
            }
            if (StringUtils.isNotEmpty(usName)) {
                //真实姓名查询
                //xml 文件中  usname ${usnameOperator} '%${usname}%' ，不需要在此处做处理
                page.setUsname(usName);
                page.setUsnameOperator("LIKE");
            }
            if (StringUtils.isNotEmpty(roleName)) {
                // 所属角色查询
                //xml 文件中  未做处理rolename，采用拼接%未做处理rolename%的方式
                page.setRolename(roleName);
                page.setRolenameOperator("LIKE");
            }
            //工号
            if(StringUtils.isNotEmpty(userCode)){
                page.setUserCode("%"+userCode+"%");
                page.setUserCodeOperator("LIKE");
            }
            //职位
            if(StringUtils.isNotEmpty(usposition)){
                page.setUsposition("%"+usposition+"%");
                page.setUspositionOpetator("LIKE");
            }
            //邮箱
            if(StringUtils.isNotEmpty(email)){
                page.setEmail("%"+email+"%");
                page.setEmailOperator("LIKE");
            }
            //手机号
            if(StringUtils.isNotEmpty(cellPhoneNumber)){
                page.setCellPhoneNumber("%"+cellPhoneNumber+"%");
                page.setCellPhoneNumberOperator("LIKE");
            }
        }
        //所属机构ID
        if (StringUtils.isNotEmpty(orgId)) {
            page.setOrgId(orgId);
            page.setOrgIdOperator("=");
        }
        //所属机构
        if(StringUtils.isNotEmpty(orgName)){
            page.setOrgName(orgName);
            page.setOrgNameOperator("LIKE");
        }
        page.setPager(new Pager());
        try {
            List<UserEO> rows = userEOService.queryByPage(page);
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), UserVO.class));
        } catch (Exception e) {
            logger.error("查询用户数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }

    }
    @BusinessLog(description = "用户管理-查询角色为CV试验工程师的所有用户")
    @ApiOperation(value = "用户管理-查询角色为CV试验工程师的所有用户")
    @GetMapping("/pageForCVEngineer")
    @RequiresPermissions("sys:user:list")
    public ResponseMessage<PageInfo<UserVO>> pageForCVEngineer(Integer pageNo, Integer pageSize,
                                                  String usName, String roleName, String orgId,
                                                  String searchPhrase,String account,String userCode,
                                                  String cellPhoneNumber,String email,String orgName,
                                                  String usposition) {
        UserEOPage page = new UserEOPage();
        if (pageNo != null) {
            page.setPage(pageNo);
        }
        if (pageSize != null) {
            page.setPageSize(pageSize);
        }

        if(StringUtils.isNotEmpty(searchPhrase) && StringUtils.isNotEmpty(searchPhrase.trim())){
            //通用查询
            searchPhrase = searchPhrase.trim();
            String patten = REGEX_EXCEPT_BLANK;
            Pattern datapattern = Pattern.compile(patten);
            Matcher matcher = datapattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (matcher.find()){
                String search = matcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
            page.setSearchPhraseOperation("LIKE");
        }else{
            //普通查询
            if (StringUtils.isNotEmpty(account)){
                //登录账号查询
                page.setAccount(account);
                page.setAccountOperator("LIKE");
            }
            if (StringUtils.isNotEmpty(usName)) {
                //真实姓名查询
                //xml 文件中  usname ${usnameOperator} '%${usname}%' ，不需要在此处做处理
                page.setUsname(usName);
                page.setUsnameOperator("LIKE");
            }
            if (StringUtils.isNotEmpty(roleName)) {
                // 所属角色查询
                //xml 文件中  未做处理rolename，采用拼接%未做处理rolename%的方式
                page.setRolename(roleName);
                page.setRolenameOperator("LIKE");
            }
            //工号
            if(StringUtils.isNotEmpty(userCode)){
                page.setUserCode("%"+userCode+"%");
                page.setUserCodeOperator("LIKE");
            }
            //职位
            if(StringUtils.isNotEmpty(usposition)){
                page.setUsposition("%"+usposition+"%");
                page.setUspositionOpetator("LIKE");
            }
            //邮箱
            if(StringUtils.isNotEmpty(email)){
                page.setEmail("%"+email+"%");
                page.setEmailOperator("LIKE");
            }
            //手机号
            if(StringUtils.isNotEmpty(cellPhoneNumber)){
                page.setCellPhoneNumber("%"+cellPhoneNumber+"%");
                page.setCellPhoneNumberOperator("LIKE");
            }
            //所属机构ID
            if (StringUtils.isNotEmpty(orgId)) {
                page.setOrgId(orgId);
                page.setOrgIdOperator("=");
            }
            //所属机构
            if(StringUtils.isNotEmpty(orgName)){
                page.setOrgName(orgName);
                page.setOrgNameOperator("LIKE");
            }
        }
        //在这里给page set值，就可以实现根据角色来查询出用户列表
        page.setRolename("试验中心工程师");
        page.setRolenameOperator("=");
        page.setPager(new Pager());
        try {
            List<UserEO> rows = userEOService.queryByPageForCV(page);
            return Result.success(beanMapper.mapPage(getPageInfo(page.getPager(), rows), UserVO.class));
        } catch (Exception e) {
            logger.error("查询用户数据异常：", e);
            return Result.error("-1", "查询参数异常", null);
        }

    }

    /**
     * 新增用户，对用户名，登录名，用户名，密码进行校验
     * 密码需要满足，6-10位，且包含数字字母组合，不能纯数字
     * 权限字段：sys:user:save
     *
     * @param userVO 用户信息
     * @return 处理结果
     */
    @ApiOperation(value = "|UserEO|新增")
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:user:save")
    public ResponseMessage<UserVO> create(@RequestBody UserVO userVO) {
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();

        if (StringUtils.isBlank(userVO.getAccount())) {
            errorCode.append("r0014");
            errorMessage.append("登录名不能为空");
        } else if (userEOService.getUserByLoginName(userVO.getAccount()) != null) {
            errorCode.append("r0015");
            errorMessage.append("登陆账号已经存在");
        } else if (StringUtils.isBlank(userVO.getPassword())) {
            errorCode.append("r0016");
            errorMessage.append("密码不能为空");
        } else if (!userVO.getPassword().matches("^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_]+$)(?![a-z0-9]+$)(?![a-z\\W_]+$)(?![0-9\\W_]+$)[a-zA-Z0-9\\W_]{8,16}$")) {
            errorCode.append("r0017");
            errorMessage.append("密码至少满足8-16位数字,大写字母,小写字母,特殊符号其中三种");
        } else {
            String createTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
            userVO.setCreateTime(createTime);
            /*
             *  前台如果base64传输密文，则需要解码
             *  调用该方法： userVO.setPassword(new String(Encodes.decodeBase64(userVO.getPassword())))
             */

            UserEO userEO = userEOService.save(beanMapper.map(userVO, UserEO.class));
            String userId = userEO.getUsid();
            if(StringUtils.isNotEmpty(userVO.getHonor())){
                userEOService.saveUserHonor(userId,userVO.getHonor(),userVO.getHonordescription());
            }
            if(StringUtils.isNotEmpty(userVO.getSeal())){
                userEOService.saveUserSeal(userId,userVO.getSeal());
            }
            if(StringUtils.isNotEmpty(userVO.getAvatar())){
                userEOService.saveUserAvatar(userId,userVO.getAvatar());
            }
            userEOService.saveUserRole(userEO);
            userEOService.saveUserOrg(userEO);
            return Result.success("0","操作成功！",beanMapper.map(userEO, UserVO.class));
        }

        /*
         * 到这一步 errorCode 和 errorMessage 肯定不会为空，即便为空也不会出现异常。
         */
        return Result.error(errorCode.toString(), errorMessage.toString(), userVO);

    }

    /**
     * 修改用户
     * 权限字段：sys:user:update
     *
     * @param userVO 用户信息
     * @return 修改结果
     */
    @ApiOperation(value = "|UserEO|修改")
    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @RequiresPermissions("sys:user:update")
    public ResponseMessage<UserVO> update(@RequestBody UserVO userVO) {
        String updateTime = DateUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        userVO.setUpdateTime(updateTime);
        try {
            userEOService.updateByPrimaryKeySelective(beanMapper.map(userVO, UserEO.class));
            String userId =userVO.getUsid();
            if(StringUtils.isNotEmpty(userVO.getHonor())){
                userEOService.saveUserHonor(userId,userVO.getHonor(),userVO.getHonordescription());
            }
            if(StringUtils.isNotEmpty(userVO.getSeal())){
                userEOService.saveUserSeal(userId,userVO.getSeal());
            }
            if(StringUtils.isNotEmpty(userVO.getAvatar())){
                userEOService.saveUserAvatar(userId,userVO.getAvatar());
            }
            userEOService.saveUserRole(beanMapper.map(userVO, UserEO.class));
            userEOService.saveUserOrg(beanMapper.map(userVO, UserEO.class));
        } catch (Exception e) {
            logger.error("修改用户信息失败", e);
            return Result.error("-1", "修改用户信息失败：" + e, userVO);
        }

        return Result.success("0","操作成功！",userVO);
    }

    /**
     * 删除用户，逻辑删除，支持批量删除
     * 权限字段：sys:user:delete
     *
     * @param ids 用户id数组
     * @return 处理结果
     */
    @ApiOperation(value = "|UserEO|删除")
    @DeleteMapping("/{ids}")
    @RequiresPermissions("sys:user:delete")
    public ResponseMessage delete(@NotNull @PathVariable("ids") String[] ids, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute(RequestUtils.LOGIN_USER_ID);
        List<String> idList = Arrays.asList(ids);
        if (idList.contains(userId)) {
            return Result.error("r0018", "登录用户不能删除自己！");
        }
        userEOService.delete(idList);
        return Result.success("0","操作成功！");

    }

    /**
     * 配置用户角色信息
     *
     * @param userVO 用户信息
     * @return 处理结果
     */
    @ApiOperation(value = "配置用户角色|UserEO|")
    @PostMapping("/saveUserRole")
    @RequiresPermissions("sys:user:saveUserRole")
    public ResponseMessage<UserVO> saveUserRole(@RequestBody UserVO userVO) {
        userEOService.saveUserRole(beanMapper.map(userVO, UserEO.class));
        return Result.success("0","操作成功！",userVO);
    }

    /**
     * 批量配置用户角色信息，若该角色已有记录，则不会重复插入（依靠数据库表唯一性约束）。
     * 权限字段:sys:user:saveMultiUserRole
     *
     * @param userIds 用户id组
     * @param roleIds 角色id组
     * @return 成功修改
     * @author 李坤澔
     */
    @ApiOperation(value = "|UserEO|批量配置用户角色")
    @PostMapping("/saveUserRole/{userIds}/{roleIds}")
    @RequiresPermissions("sys:user:saveMultiUserRole")
    public ResponseMessage saveMultiUserRole(@NotNull @PathVariable("userIds") String[] userIds,
                                             @NotNull @PathVariable("roleIds") String[] roleIds) {
        userEOService.saveMultiUserRole(Arrays.asList(userIds), Arrays.asList(roleIds));
        return Result.success("0","操作成功！");
    }

    /**
     * 批量配置用户组织信息，若该角色有记录，不会重复插入（依靠数据库表唯一性约束）
     * 原理与批量配置用户角色相同
     * 权限字段：sys:user:saveMultiUserOrg
     *
     * @param userIds 用户id组
     * @param orgIds  组织id组
     * @return 成功修改
     * @author 李坤澔
     */
    @ApiOperation(value = "|UserEO|批量配置用户组织")
    @PostMapping("/saveUserOrg/{userIds}/{orgIds}")
    @RequiresPermissions("sys:user:saveMultiUserOrg")
    public ResponseMessage saveMultiUserOrg(@NotNull @PathVariable("userIds") String[] userIds,
                                            @NotNull @PathVariable("orgIds") String[] orgIds) {
        userEOService.saveMultiUserOrg(Arrays.asList(userIds), Arrays.asList(orgIds));
        return Result.success("0","操作成功！");
    }

    /**
     * 批量删除用户角色
     * 权限字段：sys:user:deleteMultiUserRole
     *
     * @param ids     用户id组
     * @param roleIds 组织id组
     * @return 成功信息
     * @author 李坤澔
     */
    @ApiOperation(value = "|UserEO|删除批量用户角色")
    @DeleteMapping("/deleteRole/{ids}/{roleIds}")
    @RequiresPermissions("sys:user:deleteMultiUserRole")
    public ResponseMessage deleteMultiUserRoleByRoleId(@NotNull @PathVariable("ids") String[] ids,
                                                       @NotNull @PathVariable("roleIds") String[] roleIds) {
        userEOService.deleteMultiUserRoleByRoleId(Arrays.asList(ids), Arrays.asList(roleIds));
        return Result.success("0","操作成功！");
    }

    /**
     * 批量删除用户组织
     * 权限字段：sys:user:deleteMultiUserOrg
     *
     * @param ids    用户id组
     * @param orgIds 组织id组
     * @return 处理结果
     * @author 李坤澔
     */
    @ApiOperation(value = "|UserEO|删除批量用户组织")
    @DeleteMapping("/deleteOrg/{ids}/{orgIds}")
    @RequiresPermissions("sys:user:deleteMultiUserOrg")
    public ResponseMessage deleteMultiUserOrgByOrgId(@NotNull @PathVariable("ids") String[] ids,
                                                     @NotNull @PathVariable("orgIds") String[] orgIds) {

        List<String> orgIdList = new ArrayList<>(Arrays.asList(orgIds));

        try {
            /*
             * 对子组织中包含的用户也进行删除
             * @author Lee Kwanho 李坤澔
             *  date 2019-02-27
             */
            for (String orgId : orgIds) {
                orgIdList.addAll(orgEOService.getSubOrgId(orgId));
            }

        } catch (Exception e) {
            return Result.error("-1","操作失败！");
        }

        userEOService.deleteMultiUserOrgByOrgId(Arrays.asList(ids), orgIdList);

        return Result.success("0","操作成功！");
    }

    @ApiOperation(value = "|UserEO|上传头像")
    @PostMapping("/saveUserAvatar/{userId}/{fileId}")
    @RequiresPermissions("sys:user:avatar")
    public ResponseMessage saveUserAvatar(@NotNull @PathVariable("userId") String userId,
                                          @NotNull @PathVariable("fileId") String fileId) {

        try {
            fileEOService.selectByPrimaryKey(fileId);
            userEOService.saveUserAvatar(userId, fileId);
            return Result.success();
        } catch (Exception e) {
            logger.error("avatar", e);
            return Result.error("error" + e);
        }

    }
    /*
     * @Author syxy_zhangyinghui
     * @Description //根据用户id锁定或者激活用户 0锁定 1激活
     * @Date 14:50 2019/7/24
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserEO|锁定/激活用户")
    @GetMapping("/lockUser")
    @RequiresPermissions("sys:user:lockUser")
    @BusinessLog(description = "用户管理-锁定/激活用户")
    public ResponseMessage<String> lockUser(@NotNull @RequestParam("userId") String userId,
                                            @NotNull @RequestParam("lockStatus") String lockStatus){
        UserEO userEO = userEOService.lockUser(userId,lockStatus);
        String message = "";
        if ("0".equals(userEO.getExtInfo5())){
            message= "用户：" + userEO.getUsname() + "锁定成功!";
        }else{
            message= "用户：" + userEO.getUsname() + "激活成功!";
        }
        return Result.success(message);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description //用户批量导入功能
     * @Date 14:51 2019/7/24
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserEO|批量导入用户")
    @PostMapping("/importUsers")
    @RequiresPermissions("sys:user:importUsers")
    public ResponseMessage<String> importUsers(@RequestParam("file") MultipartFile file){
        try {
            String message = userEOService.importUsers(file);
            return Result.success("0",message);
        }catch (Exception e){
            logger.info(e.getMessage());
            return Result.error(e.getMessage());
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 新增用户外部培训
     * @Date 16:47 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserExternalTrainingEO|新增用户外部培训")
    @PostMapping("/saveUserExternalTraining")
    //@RequiresPermissions("sys:user:saveUserExternalTraining")
    public ResponseMessage<UserExternalTrainingVO> saveUserExternalTraining(
            @RequestBody UserExternalTrainingVO userExternalTrainingVO){
        UserExternalTrainingEO resultEo = userExternalTrainingEOService.saveUserExternalTraining(
                beanMapper.map(userExternalTrainingVO,UserExternalTrainingEO.class));
        return Result.success("0","操作成功！",beanMapper.map(resultEo,UserExternalTrainingVO.class));
    }


    /*
     * @Author syxy_zhangyinghui
     * @Description 修改用户外部培训
     * @Date 16:51 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserExternalTrainingEO|修改用户外部培训")
    @PostMapping("/updateUserExternalTrainingById")
    public ResponseMessage<UserExternalTrainingVO> updateUserExternalTrainingById(
            @RequestBody UserExternalTrainingVO userExternalTrainingVO){
        UserExternalTrainingEO resultEo = userExternalTrainingEOService.updateUserExternalTrainingById(
                beanMapper.map(userExternalTrainingVO,UserExternalTrainingEO.class));
        return Result.success("0","操作成功！",beanMapper.map(resultEo,UserExternalTrainingVO.class));
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除用户外部培训信息
     * @Date 17:11 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserExternalTrainingEO|删除用户外部培训")
    @DeleteMapping("/deleteUserExternalTrainingById")
    public ResponseMessage<UserExternalTrainingVO> deleteUserExternalTrainingById(@RequestParam("id") String id){
        Integer resultCode = userExternalTrainingEOService.deleteUserExternalTrainingById(id);
        if (resultCode>0){
            return Result.success("1","删除成功");
        }else{
            return Result.error("-1","删除失败！");
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 批量删除用户外部培训
     * @Date 17:13 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserExternalTrainingEO|批量删除用户外部培训")
    @DeleteMapping("/deleteUserExternalTrainingByUsid")
    public ResponseMessage<UserExternalTrainingVO> deleteUserExternalTrainingByUsid(
            @RequestParam("userId") String userId){
        Integer resultCode = userExternalTrainingEOService.deleteUserExternalTrainingByUsid(userId);
        if (resultCode>0){
            return Result.success("1","删除成功");
        }else{
            return Result.error("-1","删除失败！");
        }
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户idid查询用户外部培训信息
     * @Date 15:06 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserExternalTrainingEO|用户外部培训列表")
    @GetMapping("/getUserExternalTrainingEOByUserId")
    public ResponseMessage<List<UserExternalTrainingVO>> getUserExternalTrainingEOByUserId(
            @RequestParam("userId") String userId){
        List<UserExternalTrainingEO> resultEO = userExternalTrainingEOService.getUserExternalTrainingEOByUserId(userId);
        return Result.success(beanMapper.mapList(resultEO,UserExternalTrainingVO.class));
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description  用户外部培训详情
     * @Date 17:19 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserExternalTrainingEO|用户外部培训详情")
    @GetMapping("/findUserExternalTrainingEOById")
    public ResponseMessage<UserExternalTrainingVO> findUserExternalTrainingEOById(
            @RequestParam("id") String id){
        UserExternalTrainingEO resultEo = userExternalTrainingEOService.findUserExternalTrainingEOById(id);
        return Result.success(beanMapper.map(resultEo,UserExternalTrainingVO.class));
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 新增用户内部培训
     * @Date 11:02 2019/7/26
     * @Param [userExternalTrainingVO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.entity.UserInternalTrainingEO>
     **/
    @ApiOperation(value = "|UserInternalTrainingEO|新增用户内部培训")
    @PostMapping("/saveUserInternalTraining")
    //@RequiresPermissions("sys:user:saveUserInternalTraining")
    public ResponseMessage<UserInternalTrainingVO> saveUserInternalTraining(
            @RequestBody UserInternalTrainingVO userInternalTrainingVO){
        UserInternalTrainingEO resultEo = userInternalTrainingEOService.saveUserInternalTraining(
                beanMapper.map(userInternalTrainingVO,UserInternalTrainingEO.class));
        return Result.success("0","操作成功！",beanMapper.map(resultEo,UserInternalTrainingVO.class));
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description /修改用户内部培训
     * @Date 11:08 2019/7/26
     * @Param [userInternalTrainingVO]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.UserInternalTrainingVO>
     **/
    @ApiOperation(value = "|UserInternalTrainingEO|修改用户内部培训")
    @PostMapping("/updateUserInternalTrainingById")
    public ResponseMessage<UserInternalTrainingVO> updateUserExternalTrainingById(
            @RequestBody UserInternalTrainingVO userInternalTrainingVO){
        UserInternalTrainingEO resultEo = userInternalTrainingEOService.updateUserExternalTrainingById(
                beanMapper.map(userInternalTrainingVO, UserInternalTrainingEO.class));
        return Result.success("0","操作成功！",beanMapper.map(resultEo,UserInternalTrainingVO.class));
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除用户内部培训信息
     * @Date 11:12 2019/7/26
     * @Param [id]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.UserInternalTrainingVO>
     **/
    @ApiOperation(value = "|UserInternalTrainingEO|删除用户内部培训")
    @DeleteMapping("/deleteUserInternalTrainingById")
    public ResponseMessage<UserInternalTrainingVO> deleteUserInternalTrainingById(@RequestParam("id") String id){
        Integer resultCode = userInternalTrainingEOService.deleteUserInternalTrainingById(id);
        if (resultCode>0){
            return Result.success("1","删除成功");
        }else{
            return Result.error("-1","删除失败！");
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 批量删除用户外部培训
     * @Date 17:13 2019/7/25
     * @Param
     * @return
     **/
    @ApiOperation(value = "|UserInternalTrainingEO|批量删除用户内部培训")
    @DeleteMapping("/deleteUserInternalTrainingByUsid")
    public ResponseMessage<UserInternalTrainingVO> deleteUserInternalTrainingByUsid(
            @RequestParam("userId") String userId){
        Integer resultCode = userInternalTrainingEOService.deleteUserInternalTrainingByUsid(userId);
        if (resultCode>0){
            return Result.success("1","删除成功");
        }else{
            return Result.error("-1","删除失败！");
        }
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户idid查询用户内部培训信息
     * @Date 11:17 2019/7/26
     * @Param [userId]
     * @return com.adc.da.util.http.ResponseMessage<java.util.List<com.adc.da.sys.vo.UserInternalTrainingVO>>
     **/
    @ApiOperation(value = "|UserInternalTrainingEO|用户内部培训列表")
    @GetMapping("/getUserInternalTrainingEOByUserId")
    public ResponseMessage<List<UserInternalTrainingVO>> getUserInternalTrainingEOByUserId(
            @RequestParam("userId") String userId){
        List<UserInternalTrainingEO> resultEO = userInternalTrainingEOService.getUserInternalTrainingEOByUserId(userId);
        return Result.success(beanMapper.mapList(resultEO,UserInternalTrainingVO.class));
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 用户内部培训详情
     * @Date 11:20 2019/7/26
     * @Param [id]
     * @return com.adc.da.util.http.ResponseMessage<com.adc.da.sys.vo.UserInternalTrainingVO>
     **/
    @ApiOperation(value = "|UserInternalTrainingEO|用户外部培训详情")
    @GetMapping("/findUserInternalTrainingEOById")
    public ResponseMessage<UserInternalTrainingVO> findUserInternalTrainingEOById(
            @RequestParam("id") String id){
        UserInternalTrainingEO resultEo = userInternalTrainingEOService.findUserInternalTrainingEOById(id);
        return Result.success(beanMapper.map(resultEo,UserInternalTrainingVO.class));
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 查询所有用户列表
     * @Date 13:25 2019/7/26
     * @Param 
     * @return 
     **/
    @ApiOperation(value = "|UserEO|查询所有用户列表")
    @GetMapping("/findAllUsers")
    public ResponseMessage<List<UserVO>> findAllUsers(){
        try {
            List<UserEO> rows = userEOService.queryByList(new UserEOPage());
            return Result.success(beanMapper.mapList(rows,UserVO.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
