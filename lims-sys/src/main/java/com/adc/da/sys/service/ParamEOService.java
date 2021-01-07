package com.adc.da.sys.service;

import com.adc.da.base.service.BaseService;
import com.adc.da.sys.dao.OrgEODao;
import com.adc.da.sys.dao.ParamEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.ParamEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.util.constant.DeleteFlagEnum;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.RequestUtils;
import com.adc.da.util.utils.UUID;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/*
 * @Author syxy_zhangyinghui
 * @Description 系统参数管理业务层
 * @Date 15:38 2019/8/2
 * @Param
 * @return
 **/
@Service("paramEOService")
@Transactional(value = "transactionManager",
        readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ParamEOService extends BaseService<ParamEO,String> {

    private static final Logger  logger= LoggerFactory.getLogger(ParamEOService.class);

    @Autowired
    private ParamEODao paramEODao;
    @Autowired
    private UserEODao userEODao;


    @Override
    public ParamEODao getDao(){
        return paramEODao;
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 新增参数信息
     * @Date 14:29 2019/8/5
     * @Param
     * @return
     **/
    public ParamEO saveParam(ParamEO paramEO,HttpServletRequest request){
        try {
            String id = UUID.randomUUID10();
            paramEO.setId(id);
            String del = ""+DeleteFlagEnum.NORMAL.getValue();
            paramEO.setDel(del);
            //UserEO user = UserUtils.getUser();
            UserEO user = (UserEO)request.getSession().getAttribute(RequestUtils.LOGIN_USER);
            //根据userid查询所在组织机构
            String userId = user.getUsid();
            List<String> orgList = userEODao.getOrgIdListByUserId(userId);
            String[] temp = orgList.toArray(new String[orgList.size()]);
            String tempRes = "";
            for (String res : temp){
                tempRes += res+",";
            }
            paramEO.setUsId(user.getUsid());
            paramEO.setOrgId(tempRes.substring(0,tempRes.length()-1));
            String time = DateUtils.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss");
            paramEO.setCreateDate(time);
        } catch (Exception e){
            logger.info(e.getMessage());
        }
        int resultNum = paramEODao.insertParam(paramEO);
        if(resultNum==1){
            return paramEO;
        }else{
            return null;
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id修改参数信息
     * @Date 14:30 2019/8/5
     * @Param
     * @return
     **/
    public ParamEO updateParam(ParamEO paramEO){
        int resultNum = paramEODao.updateParam(paramEO);
        if(resultNum==1){
            return paramEO;
        }else{
            return null;
        }
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id删除参数信息
     * @Date 15:02 2019/8/5
     * @Param
     * @return
     **/
    public int deleteParam(String id){
        return paramEODao.deleteParam(id);
    }

    /*
     * @Author syxy_zhangyinghui
     * @Description 根据id查询参数信息
     * @Date 15:03 2019/8/5
     * @Param
     * @return
     **/
    public ParamEO getParamById(String id ){
        return paramEODao.getParamById(id);
    }

    /**
     * 根据code查询系统参数
     * @param code
     * @return
     */
    public ParamEO getParamByCode(String code){
        return paramEODao.getParamByCode(code);
    }

    /**
     * 验证参数code数据库是否存在
     * @param code
     * @param id
     */
    public Integer checkNum(String code,String id){
        Integer integer = paramEODao.checkNum(code,id);
        return  integer;
    }

    public void updateParamValue(String id,String paramValue){
        paramEODao.updateById(id,paramValue);
    }


    public ParamEO getParamByName(String name){
        return getDao().getParamByName(name);
    }
    public ParamEO updatePreById(ParamEO paramEO){
        int i = getDao().updatePreById(paramEO);
        if(i==1){
            return paramEO;
        }else{
            return null;
        }
    }
}
