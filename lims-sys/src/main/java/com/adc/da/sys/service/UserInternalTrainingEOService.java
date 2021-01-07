package com.adc.da.sys.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.UserInternalTrainingEODao;
import com.adc.da.sys.entity.UserExternalTrainingEO;
import com.adc.da.sys.entity.UserInternalTrainingEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userInternalTrainingEOService")
@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = Throwable.class)
public class UserInternalTrainingEOService extends BaseService<UserInternalTrainingEO,String > {

    @Autowired
    private UserInternalTrainingEODao userInternalTrainingEODao;


    public UserInternalTrainingEODao getDao(){
        return userInternalTrainingEODao;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description /新增用户内部培训信息
     * @Date 10:12 2019/7/26
     * @Param
     * @return
     **/
    @Transactional(rollbackFor = NullPointerException.class)
    public UserInternalTrainingEO saveUserInternalTraining(UserInternalTrainingEO userInternalTrainingEO)
            throws NullPointerException{
        if(StringUtils.isEmpty(userInternalTrainingEO.getCourseName())){
            throw new NullPointerException("课程名称不能为空！");
        }
        userInternalTrainingEO.setId(UUID.randomUUID10());
        userInternalTrainingEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue()+"");
        userInternalTrainingEODao.saveUserInternalTraining(userInternalTrainingEO);
        return userInternalTrainingEO;
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id删除用户内部培训
     * @Date 10:13 2019/7/26
     * @Param [usid]
     * @return java.lang.Integer
     **/
    public Integer deleteUserInternalTrainingByUsid(@Param("userId") String usid){
        return userInternalTrainingEODao.deleteUserInternalTrainingByUsid(usid);
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id删除用户内部培训信息
     * @Date 10:22 2019/7/26
     * @Param [id]
     * @return java.lang.Integer
     **/
    public Integer deleteUserInternalTrainingById(String id){
        return userInternalTrainingEODao.deleteUserInternalTrainingById(id);
    }
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id修改用户内部培训信息
     * @Date 10:22 2019/7/26
     * @Param [userExternalTrainingEO]
     * @return com.adc.da.sys.entity.UserExternalTrainingEO
     **/
    public UserInternalTrainingEO updateUserExternalTrainingById(UserInternalTrainingEO userInternalTrainingEO){
        userInternalTrainingEODao.updateUserInternalTrainingById(userInternalTrainingEO);
        return userInternalTrainingEO;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id查询用户内部培训信息
     * @Date 10:24 2019/7/26
     * @Param [usid]
     * @return java.util.List<com.adc.da.sys.entity.UserExternalTrainingEO>
     **/
    public List<UserInternalTrainingEO> getUserInternalTrainingEOByUserId(@Param("userId") String usid){
        return userInternalTrainingEODao.findUserInternalTrainingEOByUserId(usid);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除用户培训信息
     * @Date 10:25 2019/7/26
     * @Param [id]
     * @return com.adc.da.sys.entity.UserInternalTrainingEO
     **/
    public UserInternalTrainingEO findUserInternalTrainingEOById(String id){
        return userInternalTrainingEODao.findUserInternalTrainingEOById(id);
    }
}
