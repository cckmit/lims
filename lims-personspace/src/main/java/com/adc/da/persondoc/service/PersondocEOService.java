package com.adc.da.persondoc.service;

import com.adc.da.base.page.Pager;
import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.page.MessageEOPage;
import com.adc.da.persondoc.entity.PersondocTypeEO;
import com.adc.da.persondoc.page.PersondocEOPage;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.persondoc.dao.PersondocEODao;
import com.adc.da.persondoc.entity.PersondocEO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * <br>
 * <b>功能：</b>TP_PERSONDOC PersondocEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-07-24 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("persondocEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class PersondocEOService extends BaseService<PersondocEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(PersondocEOService.class);

    @Autowired
    private PersondocEODao dao;

    public PersondocEODao getDao() {
        return dao;
    }

    @Autowired
    private TsAttachmentEODao tsDao;

    @Autowired
    private IFileStore iFileStore;

    @Autowired
    private PersondocTypeEOService persondocTypeEOService;

    private volatile String typeIds = "";

    /**
     * 分页查询
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<PersondocEO> queryByPage(PersondocEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.queryByPage(page);
    }

    /**
     * 配置查询条件
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public PersondocEOPage pageSet(PersondocEOPage page, String searchPhrase) throws Exception {
        if (StringUtils.isNotEmpty(searchPhrase)) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
        //递归类型ids
        //这里的typeids用的是全局变量，所以可能会有线程问题
        synchronized (this) {
            if (StringUtils.isNotEmpty(page.getDoctypeId())) {
                List<PersondocTypeEO> persondocTypeEOList = persondocTypeEOService.getAllTypes(page.getDoctypeId());
                getTypeIds(persondocTypeEOList);
                String ids = typeIds + "'" + page.getDoctypeId() + "'";
                page.setDoctypeId(ids);
                //将全局变量值为空
                typeIds = "";
            }
        }
        //当前用户
        page.setCreateById(UserUtils.getUser().getUsid());
        page.setPager(new Pager());
        return page;
    }

    public void getTypeIds(List<PersondocTypeEO> persondocTypeEOList){
        for(PersondocTypeEO type:persondocTypeEOList){
               typeIds += "'"+type.getId()+"',";
               if(CollectionUtils.isNotEmpty(type.getTypeList())){
                   getTypeIds(type.getTypeList());
               }
        }
    }

    public int queryDocByTypeId(String typeId){
        return dao.queryDocByTypeId(typeId);
    }

    /**
     * 保存实体
     * @param file
     * @param persondocEO
     */
    public void savefile(MultipartFile file, PersondocEO persondocEO) throws IOException {
        String fileName = file.getOriginalFilename();
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        String extension = FileUtil.getFileExtension(file.getOriginalFilename());
        String fileSize = file.getSize()/1024 + "KB";
        //文件流
        InputStream is = file.getInputStream();
        //返回路径
        String path = this.iFileStore.storeFile(is, extension, "");
        //附件表
        TsAttachmentEO tsAttachmentEO = new TsAttachmentEO();
        tsAttachmentEO.setAttachmentName(fileName);
        tsAttachmentEO.setAttachmentSize(fileSize);
        tsAttachmentEO.setAttachmentType(extension);
        tsAttachmentEO.setCreateTime(date);
        tsAttachmentEO.setCreateBy(UserUtils.getUserId());
        tsAttachmentEO.setSavePath(path);
        tsAttachmentEO.setDelFlag(0);
       tsDao.insertSelective(tsAttachmentEO);
       //文档表
        persondocEO.setCcExtension(extension);
        persondocEO.setCreatTime(date);
        persondocEO.setDelFlag(0);
        persondocEO.setFileName(fileName);
        persondocEO.setCreateById(UserUtils.getUserId());
        persondocEO.setFileSize(fileSize);
        persondocEO.setAttachmentid(tsAttachmentEO.getId());
        dao.insertSelective(persondocEO);

    }


}
