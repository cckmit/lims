package com.adc.da.sys.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.adc.da.log.annotation.BusinessLog;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.RoleEODao;
import com.adc.da.sys.entity.OrgEO;
import com.adc.da.sys.entity.RoleEO;
import com.adc.da.sys.page.UserEOPage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;

import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.exception.AdcDaBaseException;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.PasswordUtils;
import com.adc.da.util.utils.UUID;
import com.alibaba.fastjson.JSONObject;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理模块Service层
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 *
 * @author comments created by Lee Kwanho
 * @see UserEODao
 * @see com.adc.da.sys.controller.UserEOController
 */
@Service("userEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserEOService extends BaseService<UserEO, String> {
    private static final Logger logger = LoggerFactory.getLogger(UserEOService.class);

    /**
     * Dao层自动装配
     *
     * @see UserEODao
     */
    @Autowired
    private UserEODao dao;
    public UserEODao getDao() {
        return dao;
    }

    @Autowired
    private RoleEODao roleEODao;
    
    @Autowired
    private OrgEODao orgEODao;

    @Value("${user.defaultPassword}")
    private String password;

    /*
     * @Author syxy_zhangyinghui
     * @Description //注入角色管理类
     * @Date 13:17 2019/7/23
     * @Param
     * @return
     **/
    @Autowired
    private RoleEODao roleDao;

    public RoleEODao getRoleDao() {
        return roleDao;
    }

    @Autowired
    private OrgEODao orgDao;

    public OrgEODao getOrgEODao(){
        return orgDao;
    }

    /**
     * 判断代理人是否在允许代理期间
     * @param agentId 被代理人ID
     *        userId   代理人/当前登录人Id
     * @return
     */
    public int findAgent(String agentId, String userId){
       return dao.findAgent(agentId, userId);
    }

    /**
     * 新增用户，设置10位id，设置删除标识位，对密码进行加密，设置额外信息为空
     *
     * @param userEO 用户信息
     * @return 用户信息
     */
    public UserEO save(UserEO userEO) {
        userEO.setUsid(UUID.randomUUID10());
        userEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue());
        userEO.setPassword(PasswordUtils.encryptPassword(userEO.getPassword()));
        if (userEO.getExtInfo() == null) {
            userEO.setExtInfo("");
        }
        dao.insert(userEO);
        return userEO;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description //存用户的资质  保存前先删除
     * @Date 13:38 2019/7/22
     * @Param [userId, fileId, description]
     * @return com.adc.da.sys.entity.UserEO
     **/
    @Transactional(rollbackFor = Exception.class)
    public String saveUserHonor(String userId,String fileId,String description){
        String id = UUID.randomUUID();
        dao.deleteUserHonor(userId);
        dao.saveUserHonor(id,userId,fileId,description);
        return id;
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description //保存印章信息  保存前删除
     * @Date 14:07 2019/7/22
     * @Param
     * @return
     **/
    @Transactional(rollbackFor = Exception.class)
    public  String saveUserSeal(String userId,String fileId){
        String id = UUID.randomUUID();
        dao.deleteUserSeal(userId);
        dao.saveUserSeal(id,userId,fileId);
        return id;
    }

    /**
     * 获取角色信息，用户名，用于新增用户校验
     * 根据account获取UserEO
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Transactional(readOnly = true)
    public UserEO getUserByLoginName(String userName) {
        return dao.getUserEOByAccount(userName);
    }

    /**
     * 更新密码，先对旧密码进行校验，然后执行更新操作
     *
     * @param userId      用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    public void updatePassword(String userId, String oldPassword, String newPassword) {
        UserEO userEO = dao.get(userId);
        if (!PasswordUtils.validatePassword(oldPassword, userEO.getPassword())) {
            throw new AdcDaBaseException("0001", "修改密码失败，旧密码错误");
        }
        dao.updatePassword(userId, PasswordUtils.encryptPassword(newPassword));
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description //根据用户id重置用户密码为固定的一个值
     * @Date 15:59 2019/7/22
     * @Param
     * @return
     **/
    public void  resetPassword(String userId,String resetPassword){
        dao.updatePassword(userId, PasswordUtils.encryptPassword(resetPassword));
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description //根据用户id锁定或者激活用户  0位锁定1为激活
     * @Date 16:23 2019/7/22
     * @Param
     * @return
     **/
    public UserEO lockUser(String userId,String lockStatus){
        dao.lockUser(userId,lockStatus);
        return dao.get(userId);
    }

    /**
     * 删除用户及用户角色关联
     *
     * @param ids 用户id组
     */
    public void delete(List<String> ids) {
        dao.deleteLogicInBatch(ids);
    }

    /**
     * 查询用户及用户所对应的角色
     *
     * @param id 用户id
     * @return 用户角色信息
     */
    @Transactional(readOnly = true)
    public UserEO getUserWithRoles(String id) {
        UserEO user = dao.getUserWithRoles(id);
        List<RoleEO> roleList = roleEODao.getRoleListByUserId(id);
        if(roleList != null && !roleList.isEmpty()){
            String roleCode = StringUtils.join(roleList.stream().map(role -> role.getRoleCode()).collect(Collectors.toList()), ",");
            user.setRoleCode(roleCode);
        }
        return user;
    }

    /**
     * 设置用户角色关联，先删除原有角色关联，然后重新设置
     *
     * @param userEO 用户角色信息
     * @return 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public UserEO saveUserRole(UserEO userEO) {
        if (CollectionUtils.isNotEmpty(userEO.getRoleIdList())) {
            dao.deleteUserRoleByUsid(userEO.getUsid());
            for (String roleId : userEO.getRoleIdList()) {
                dao.saveUserRole(userEO.getUsid(), roleId);
            }
        }
        return userEO;
    }

    @Transactional(rollbackFor = Exception.class)
    public String saveUserAvatar(String userID, String fileID) {
        String id = UUID.randomUUID10();
        /*进行旧头像的删除*/
        dao.deleteUserAvatar(userID);
        dao.saveUserAvatar(id, userID, fileID);
        return id;
    }

    /**
     * 设置用户组织机构关联，先删除原有组织信息，然后重新插入数据
     *
     * @param userEO 用户信息
     * @return 用户信息
     */
    @Transactional(rollbackFor = Exception.class)
    public UserEO saveUserOrg(UserEO userEO) {
        if (CollectionUtils.isNotEmpty(userEO.getOrgIdList())) {
            dao.deleteUserOrgByUsid(userEO.getUsid());
            for (String orgId : userEO.getOrgIdList()) {
                dao.saveUserOrg(userEO.getUsid(), orgId);
            }
        }
        return userEO;
    }

    // 根据account获取未逻辑删除的UserEO
    @BusinessLog(description = "根据登录用户名查询登录用户信息")
    @Transactional(readOnly = true)
    public UserEO getUserByLoginNameNotDeleted(String userName) {
        return dao.getUserEOByAccountNotDeleted(userName);
    }

    /**
     * 批量设置用户角色关联 ，对每个角色进行批量插入操作，依靠数据库唯一性约束保证数据不重复插入
     *
     * @param userIds 用户id组
     * @param roleIds 角色id组
     * @author Lee Kwanho 李坤澔
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMultiUserRole(List<String> userIds, List<String> roleIds) {
        for (String roleId : roleIds) {
            dao.saveMultiUserRole(userIds, roleId);
        }
    }

    /**
     * 批量设置用户组织关联，对每个组织进行批量插入操作，依靠数据库唯一性约束保证数据不重复插入
     *
     * @param userIds 用户id组
     * @param orgIds  组织id组
     * @author Lee Kwanho 李坤澔
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveMultiUserOrg(List<String> userIds, List<String> orgIds) {
        for (String userId : userIds) {
            //先删除关联关系
            dao.deleteRelationByUserId(userId);
            //再添加关联关系:默认每个人只有一个组织机构
            dao.saveMultiUserOrg(userIds, orgIds.get(0));
        }
    }

    /**
     * 批量删除用户角色关联
     *
     * @param ids     用户id组
     * @param roleIds 角色id组
     * @author Lee Kwanho 李坤澔
     */
    public void deleteMultiUserRoleByRoleId(List<String> ids, List<String> roleIds) {
        for (String roleId : roleIds) {
            dao.deleteMultiUserRoleByRoleId(ids, roleId);
        }

    }

    /**
     * 批量删除用户组织关联
     *
     * @param ids    用户id组
     * @param orgIds 组织id组
     * @author Lee Kwanho 李坤澔
     */
    public void deleteMultiUserOrgByOrgId(List<String> ids, List<String> orgIds) {
        for (String orgId : orgIds) {
            dao.deleteMultiUserOrgByOrgId(ids, orgId);
        }

    }
    /*
     * @Author syxy_zhangyinghui
     * @Description //批量导入用户
     * @Date 9:07 2019/7/23
     * @Param
     * @return
     **/
    @Transactional(rollbackFor = Exception.class)
    public String importUsers(MultipartFile file) throws Exception {
        String message = "";
        if(file.isEmpty()){
            logger.info("文件不存在，导入失败......",new FileNotFoundException());
            message = "文件不存在，导入失败";
            return message;
        }
        FileInputStream fis = (FileInputStream)file.getInputStream();
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);
        //记录导入行数
        int successRows = 0;
        //记录失败行数
        List<String> errorRows = new ArrayList<>();
        //从第二行开始解析
        for(int i = 1; i <= sheet.getLastRowNum(); i++) {
            try {
                Row row = sheet.getRow(i);
                //用户名
                String userName = getCellValueByCell(row.getCell(0));
                //真实姓名
                String realName = getCellValueByCell(row.getCell(1));
                //邮箱
                String email = getCellValueByCell(row.getCell(2));
                //手机
                String mobilePhone = getCellValueByCell(row.getCell(3));
                //办公电话
                String officePhone = getCellValueByCell(row.getCell(4));
                //工号
                String officeNum = getCellValueByCell(row.getCell(5));
                //职位
                String position = getCellValueByCell(row.getCell(6));
                //试验室
                String group = getCellValueByCell(row.getCell(7));
                //科室
                String section = getCellValueByCell(row.getCell(8));
                //部门
                String department = getCellValueByCell(row.getCell(9));
                //角色
                String role = getCellValueByCell(row.getCell(10));

                //验证用户名是否为空
                if(StringUtils.isEmpty(userName)) {
                    message = "用户名不能为空，请核实导入文件！";
                    throw new NullPointerException(message);
                }
                UserEO user = null;
                //根据用户名查询用户信息  验证用户名唯一性
                UserEO userEO = dao.getUserEOByAccount(userName);
                if (com.adc.da.util.utils.StringUtils.isNotEmpty(userEO)){
                    //用户已存在  更新存在用户的信息
                    user = userEO;
                }else{
                    //用户不存在  新增用户信息
                    //用户不存在，则新增用户
                    user = new UserEO();
                    //用户名
                    user.setAccount(userName);
                    //用户初始化密码
                    user.setPassword(password);

                }
                //用户锁定或者激活状态  0 锁定  1激活
                user.setExtInfo5("1");
                //删除状态  0 可用  1已删除
                user.setDelFlag(0);
                user.setUsname(realName);//用户姓名
                user.setEmail(email);//用户邮箱
                user.setCellPhoneNumber(mobilePhone);//手机号
                user.setOfficePhone(officePhone);//办公号码
                user.setUserCode(officeNum);//工号
                user.setUsposition(position);//职位
                if(StringUtils.isEmpty(user.getUsid())){
                    //新增
                    this.save(user);
                }else{
                    //修改更新
                    this.updateByPrimaryKeySelective(user);
                }
                //绑定组织机构
                String resultOrgId ="";
                if(StringUtils.isNotEmpty(group)) {
                    //试验室不为空，绑定到试验室
                    //①根据试验室名称获取试验室信息
                    resultOrgId = this.getOrgIdByGroupAndSectionAndDepartment(group,section,department);
                }else if(StringUtils.isNotEmpty(section)) {
                    //科室不为空，绑定到科室
                    resultOrgId = this.getOrgIdBySectionAndDepartment(section,department);

                }else if(StringUtils.isNotEmpty(department)) {
                    //部门不为空，绑定到部门
                    //根据部门名称获取实体类
                    resultOrgId = this.getOrgIdByDepartment(department);
                }
                //绑定组织机构
                if(StringUtils.isNotEmpty(resultOrgId)){
                    //如果他不为空，那么绑定组织机构
                    //根据orgId和用户id查询时候绑定组织机构
                    dao.deleteUserOrgByUsid(user.getUsid());//删除用户绑定的组织机构，然后添加
                    dao.saveUserOrg(user.getUsid(),resultOrgId);
                }
                //绑定角色
                if(StringUtils.isNotEmpty(role)) {
                    String[] roleNames = role.split(",");
                    List<String> roleNamesList = new ArrayList<>();
                    for (String roleName:roleNames){
                        roleNamesList.add(roleName);
                    }
                    List<RoleEO> roleList = roleDao.selectByRoleName(roleNamesList);
                    List<String> roleIdList = new ArrayList<>();
                    for (RoleEO roleEO : roleList){
                        roleIdList.add(roleEO.getId());
                    }
                    user.setRoleIdList(roleIdList);
                    user.setRoleEOList(roleList);
                    this.saveUserRole(user);
                }
                successRows++;
            } catch (Exception e) {
                errorRows.add(i + 1 + "");
                logger.error(e.getMessage(), e);
                throw new Exception("系统错误！");
            }
        }
        message = "导入完毕，共新增/更新" + successRows + "行，失败" + errorRows.size() + "行";
        if(!errorRows.isEmpty()) {
            message += "（行数：" + StringUtils.join(errorRows.toArray(), ',') + "）";
        }
        return message;
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 组织机构中最上级部门不为空其他为空的时候获取部门id
     * @Date 10:20 2019/7/24
     * @Param [department]  部门名称
     * @return java.lang.String
     **/
    private String getOrgIdByDepartment(@Param("department") String department){
        List<OrgEO> orgList = orgDao.getOrgByOrgName(department);
        if(com.adc.da.util.utils.StringUtils.isEmpty(orgList)){
            throw new NullPointerException(department+"组织机构不存在，请核实！");
        }else if(orgList.size()==1){
            return orgList.get(0).getId();
        }else if (orgList.size()>1){
            throw new NullPointerException(department+"在系统组织机构中存在多个，请核实！");
        }else{
            throw new NullPointerException("系统错误，请联系开发人员！");
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 组织机构中科室不为空的情况下判定筛选组织机构id
     * @Date 10:20 2019/7/24
     * @Param
     * @return
     **/
    private String getOrgIdBySectionAndDepartment(@Param("section") String section,
                                                  @Param("department") String department){
        List<OrgEO> orgList = orgDao.getOrgByOrgName(section);
        if(com.adc.da.util.utils.StringUtils.isEmpty(orgList)){
            throw new NullPointerException(section+"组织机构不存在，请核实！");
        }else if(orgList.size()==1){
            //正常找到组织机构绑定
            return orgList.get(0).getId();
        }else if (orgList.size()>1){
            //上系统中存在两个  继续向上一级查询判定
            if(StringUtils.isNotEmpty(department)){
                Map<String, Object> map = new HashMap<>();
                map.put("orgPName",department);
                map.put("orgName",section);
                orgList = orgDao.getOrgByTwoOrgName(map);
                if(com.adc.da.util.utils.StringUtils.isNotEmpty(orgList)){
                    throw new NullPointerException(department+"不是"+section+"的上级组织机构，请核实！");
                }else if(orgList.size()==1){
                    //确认section的id
                    return orgList.get(0).getId();
                }else if(orgList.size()>1){
                    throw new NullPointerException(department+"下"+section+"的组织机构存在多个，请核实！");
                }else{
                    throw new NullPointerException("系统错误，请联系开发人员！");
                }
            }else{
                throw new NullPointerException(section+"在系统中存在多个，请填写上一级组织机构名称！");
            }
        }else{
            throw new NullPointerException("系统错误，请联系开发人员！");
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 小组或者试验室不为空的时候筛选组织机构id
     * @Date 10:29 2019/7/24
     * @Param
     * @return
     **/
    private String getOrgIdByGroupAndSectionAndDepartment(@Param("group") String group,
                                                          @Param("section") String section,
                                                          @Param("department") String department){
        List<OrgEO> orgList = orgDao.getOrgByOrgName(group);
        if(com.adc.da.util.utils.StringUtils.isEmpty(orgList)){
            //没有找到  系统中不存在
            throw new NullPointerException(group+"在系统中不存在，请核实！");
        }else if(orgList.size()==1){
            //正常找到组织机构绑定
            return orgList.get(0).getId();
        }else if (orgList.size()>1){
            //存在多个，可能别的组织机构下也存在改名称，判断上一级科室是否有值，联查
            if(StringUtils.isNotEmpty(section)) {
                //科室不为空
                Map<String, Object> map = new HashMap<>();
                map.put("orgPName",section);
                map.put("orgName",group);
                orgList = orgDao.getOrgByTwoOrgName(map);
                if(com.adc.da.util.utils.StringUtils.isEmpty(orgList)){
                    throw new NullPointerException(section+"不是"+group+"的上级组织机构，请核实！");
                }else if(orgList.size()==1){
                    //正常找到组织机构绑定
                    return orgList.get(0).getId();
                }else if (orgList.size()>1){
                    //上一级在系统中也存在两个  继续向上一级查询判定
                    if(StringUtils.isNotEmpty(department)){
                        map = new HashMap<>();
                        map.put("orgPName",department);
                        map.put("orgName",section);
                        orgList = orgDao.getOrgByTwoOrgName(map);
                        if(com.adc.da.util.utils.StringUtils.isNotEmpty(orgList)){
                            throw new NullPointerException(department+"不是"+section+"的上级组织机构，请核实！");
                        }else if(orgList.size()==1){
                            //确认section的id
                            String sectionOrgId = orgList.get(0).getId();
                            orgList = orgDao.getOrgByOrgNameAndOrgId(sectionOrgId,group);
                            if (com.adc.da.util.utils.StringUtils.isNotEmpty(orgList)){
                                throw new NullPointerException("组织机构填写有误，请核实！");
                            }else if(orgList.size()==1){
                                return orgList.get(0).getId();
                            }else{
                                throw new NullPointerException(section+"下"+group+"的组织机构存在多个，请核实！");
                            }
                        }else if(orgList.size()>1){
                            throw new NullPointerException(department+"下"+section+"的组织机构存在多个，请核实！");
                        }else{
                            throw new NullPointerException("系统错误，请联系开发人员！");
                        }

                    }else{
                        throw new NullPointerException(section+"在系统中存在多个，请写上一级组织机构名称！");
                    }

                }else{
                    throw new NullPointerException("系统错误，请联系开发人员！");
                }
            }else{
                throw new NullPointerException(group+"在系统中存在多个，请写上一级组织机构名称！");
            }
        }else {
            throw new NullPointerException("系统错误，请联系开发人员！");
        }
    }

    /**
     * 获取单元格各类型值，返回字符串类型
     * @param cell
     * @return
     */
    public static String getCellValueByCell(Cell cell) {
        //判断是否为null或空串
        if (cell==null || "".equals(cell.toString().trim())) {
            return "";
        }
        String cellValue = "";
        //CellType cellType = cell.getCellTypeEnum();
        int cellType=cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING: //字符串类型
                cellValue= cell.getStringCellValue().trim();
                cellValue=StringUtils.isEmpty(cellValue) ? "" : cellValue;
                break;
            case Cell.CELL_TYPE_BOOLEAN:  //布尔类型
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: //数值类型
                if (HSSFDateUtil.isCellDateFormatted(cell)) {//时间类型
                    Date date = cell.getDateCellValue();
                    cellValue = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
                } else {//数值类型
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_FORMULA: //公式类型
                try {
                    cellValue = String.valueOf(cell.getStringCellValue());
                } catch (IllegalStateException e) {
                    cellValue = new DecimalFormat("#.######").format(cell.getNumericCellValue());
                }
                break;
            default: //其它类型，取空串
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     * 格局ids查询用户
     * @param ids
     * @return
     */
    public List<UserEO> findUserByIds(String[] ids){
        return dao.findUserByIds(ids);
    }
    /**
     * 根据code查询用户
     * @param code
     * @return
     */
    public List<UserEO> getUserByCode(String code){
        return dao.getUser(code);
    }

    /**
     * 根据用户姓名查询用户
     * @param name
     * @return
     */
    public List<UserEO> getUserByUserName(String name) {
        return dao.getUserByUserName(name);
    }

    /**
     * 通用查询，查询出对应的角色列表
     * @param page
     * @return
     */
    public List<UserEO> queryByPageForCV(UserEOPage page) throws Exception {
        Integer rowCount = dao.queryByCountByRole(page);
        page.getPager().setRowCount(rowCount);
        return dao.queryByPageByRole(page);
    }

    /**
     * 根据用户ID查询用户
     * @param usId
     * @return
     */
    public UserEO getUserById(String usId){
        UserEO userEO;
        userEO = dao.getUserById(usId);
        if(userEO != null){
            String orgId = dao.getOrgIdByUserId(usId);
            OrgEO orgEO = orgDao.getOrgEOById(orgId);
            userEO.setOrgName(orgEO.getName());
        }else{
            userEO = new UserEO();
            userEO.setUsname("");
            userEO.setCellPhoneNumber("");
            userEO.setOrgName("");
        }
        return userEO;
    }
    
    /**
	 * 根据组织机构和角色查找用户，如果组织机构下不存在该角色，则查找上级组织机构的角色
	 * @param roleId
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getUsersByRoleAndOrg(String roleId, String orgId){
		List<UserEO> userList = getUserListByRoleAndOrg(roleId, orgId);
		List<Map<String, Object>> result = new ArrayList<>();
		if(!userList.isEmpty()){
			result = userList.stream().map(userVO -> {
				JSONObject json = new JSONObject();
				json.put("usid", userVO.getUsid());
				json.put("username", userVO.getUsname());
				return json;
			}).collect(Collectors.toList());
		}
		return result;
	}

	/**
	 * 递归查询组织下对应角色用户
	 * @Title: getUserListByRoleAndOrg
	 * @param roleId
	 * @param orgId
	 * @return
	 * @return List<UserEO>
	 * @author: ljy
	 * @date: 2020年5月21日
	 */
	public List<UserEO> getUserListByRoleAndOrg(String roleId, String orgId){
		List<UserEO> userList = dao.queryByRoleAndOrg(roleId, orgId);
		if(CollectionUtils.isEmpty(userList)) {
			//查找上级组织机构
			OrgEO org = orgEODao.selectByPrimaryKey(orgId);
			if(!"0".equals(org.getParentId())) {
				return getUserListByRoleAndOrg(roleId, org.getParentId());
			}
		}
		return userList;
	}
}
