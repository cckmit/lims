package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.UserInternalTrainingEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInternalTrainingEODao extends BaseDao<UserInternalTrainingEO> {

    /*
     * @Author syxy_zhangyinghui
     * @Description 新增用户内部培训信息
     * @Date 9:54 2019/7/26
     * @Param
     * @return
     **/
    void saveUserInternalTraining(UserInternalTrainingEO userInternalTrainingEO);

   /*
    * @Author syxy_zhangyinghui
    * @Description 根据用户id批量删除用户内部培训信息
    * @Date 9:55 2019/7/26
    * @Param
    * @return
    **/
    Integer deleteUserInternalTrainingByUsid(@Param("userId") String usid);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除用户内部培训信息
     * @Date 9:59 2019/7/26
     * @Param [id]
     * @return java.lang.Integer
     **/
    Integer deleteUserInternalTrainingById(String id);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id修改用户培训信息
     * @Date 10:00 2019/7/26
     * @Param [userInternalTrainingEO]
     * @return void
     **/
    void updateUserInternalTrainingById(UserInternalTrainingEO userInternalTrainingEO);
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id查询用户内部培训信息
     * @Date 10:00 2019/7/26
     * @Param [usid]
     * @return java.util.List<com.adc.da.sys.entity.UserInternalTrainingEO>
     **/
    List<UserInternalTrainingEO> findUserInternalTrainingEOByUserId(@Param("userId") String usid);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id查询用户内部培训信息
     * @Date 10:01 2019/7/26
     * @Param [id]
     * @return com.adc.da.sys.entity.UserInternalTrainingEO
     **/
    UserInternalTrainingEO findUserInternalTrainingEOById(String id);
}
