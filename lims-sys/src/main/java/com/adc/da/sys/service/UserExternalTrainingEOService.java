package com.adc.da.sys.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.UserExternalTrainingEODao;
import com.adc.da.sys.entity.UserExternalTrainingEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userExternalTrainingEOService")
@Transactional(value = "transactionManager",
        readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class UserExternalTrainingEOService extends BaseService<UserExternalTrainingEO,String > {

    @Autowired
    private UserExternalTrainingEODao userExternalTrainingEODao;

    public UserExternalTrainingEODao getDao() {
        return userExternalTrainingEODao;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 新增用户外部培训信息
     * @Date 16:04 2019/7/25
     * @Param
     * @return
     **/
    @Transactional(rollbackFor = NullPointerException.class)
    public UserExternalTrainingEO saveUserExternalTraining(UserExternalTrainingEO userExternalTrainingEO)
            throws NullPointerException{
        if(StringUtils.isEmpty(userExternalTrainingEO.getStartTime())){
            throw new NullPointerException("开始时间不能为空！");
        }
        if(StringUtils.isEmpty(userExternalTrainingEO.getEndTime())){
            throw new NullPointerException("结束时间不能为空！");
        }
        if(StringUtils.isEmpty(userExternalTrainingEO.getCourseName())){
            throw new NullPointerException("课程名称不能为空！");
        }
        userExternalTrainingEO.setId(UUID.randomUUID10());
        userExternalTrainingEO.setDelFlag(DeleteFlagEnum.NORMAL.getValue()+"");
        userExternalTrainingEODao.saveUserExternalTraining(userExternalTrainingEO);
        return userExternalTrainingEO;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id删除用户外部培训信息
     * @Date 16:09 2019/7/25
     * @Param
     * @return
     **/
    public Integer deleteUserExternalTrainingByUsid(@Param("userId") String usid){
        return userExternalTrainingEODao.deleteUserExternalTrainingByUsid(usid);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id删除用户外部培训信息
     * @Date 16:09 2019/7/25
     * @Param
     * @return
     **/
    public Integer deleteUserExternalTrainingById(String id){
       return userExternalTrainingEODao.deleteUserExternalTrainingById(id);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id修改用户培训信息
     * @Date 16:30 2019/7/25
     * @Param
     * @return
     **/
    public UserExternalTrainingEO updateUserExternalTrainingById(UserExternalTrainingEO userExternalTrainingEO){
        userExternalTrainingEODao.updateUserExternalTrainingById(userExternalTrainingEO);
        return userExternalTrainingEO;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id查询用户外部培训信息
     * @Date 15:40 2019/7/25
     * @Param
     * @return
     **/
    public List<UserExternalTrainingEO> getUserExternalTrainingEOByUserId(@Param("userId") String usid){
        return userExternalTrainingEODao.findUserExternalTrainingEOByUserId(usid);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除用户培训信息
     * @Date 17:15 2019/7/25
     * @Param
     * @return
     **/
    public UserExternalTrainingEO findUserExternalTrainingEOById(String id){
        return userExternalTrainingEODao.findUserExternalTrainingEOById(id);
    }


}
