package com.adc.da.sys.dao;

import com.adc.da.base.dao.BaseDao;
import com.adc.da.sys.entity.UserExternalTrainingEO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserExternalTrainingEODao extends BaseDao<UserExternalTrainingEO> {

    /*
     * @Author syxy_zhangyinghui
     * @Description 新增用户外部培训信息
     * @Date 15:46 2019/7/25
     * @Param 
     * @return 
     **/
    void saveUserExternalTraining(UserExternalTrainingEO userExternalTrainingEO);

    /*
     * @Author syxy_zhangyinghui
     * @Description //根据用户id删除用户外部信息
     * @Date 15:48 2019/7/25
     * @Param
     * @return
     **/
    Integer deleteUserExternalTrainingByUsid(@Param("userId") String usid);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除用户外部培训信息
     * @Date 15:49 2019/7/25
     * @Param
     * @return
     **/
    Integer deleteUserExternalTrainingById(String id);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id修改用户培训信息
     * @Date 15:51 2019/7/25
     * @Param
     * @return
     **/
    void updateUserExternalTrainingById(UserExternalTrainingEO userExternalTrainingEO);
    /*
     * @Author syxy_zhangyinghui
     * @Description 根据用户id查询用户外部培训信息
     * @Date 15:41 2019/7/25
     * @Param
     * @return
     **/
    List<UserExternalTrainingEO> findUserExternalTrainingEOByUserId(@Param("userId") String usid);

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id查询用户外部培训信息
     * @Date 15:59 2019/7/25
     * @Param
     * @return
     **/
    UserExternalTrainingEO findUserExternalTrainingEOById(String id);
}
