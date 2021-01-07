package com.adc.da.seal.service;

import com.adc.da.base.page.Pager;
import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.login.util.UserUtils;
import com.adc.da.seal.page.BmSealEOPage;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.seal.dao.BmSealEODao;
import com.adc.da.seal.entity.BmSealEO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <br>
 * <b>功能：</b>BM_SEAL BmSealEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-01 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("bmSealEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class BmSealEOService extends BaseService<BmSealEO, String> {

    @Autowired
    private BmSealEODao dao;

    @Autowired
    private TsAttachmentEODao tsDao;

    @Autowired
    private IFileStore iFileStore;

    public BmSealEODao getDao() {
        return dao;
    }

    /**
     * 分页数据
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public BmSealEOPage pageSet(BmSealEOPage page, String searchPhrase) {
        if (StringUtils.isNotEmpty(searchPhrase)) {
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<String>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
        //当前用户
        page.setPager(new Pager());
        return page;
    }

    /**
     * 分页查询
     *
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<BmSealEO> queryByPage(BmSealEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.queryByPage(page);
    }

    /**
     * 保存实体
     *
     * @param file
     * @param bmSealEO
     */
    public String savefile(MultipartFile file, BmSealEO bmSealEO) throws IOException {
        if (StringUtils.isNotEmpty(bmSealEO.getId())) {
            //更新
            if (dao.countSealCode(bmSealEO.getSealCode()) > 1) {
                return "-1";
            }
        } else {
            if (dao.countSealCode(bmSealEO.getSealCode()) > 0) {
                return "-1";
            }
        }
        bmSealEO.setCreateById(UserUtils.getUserId());
        bmSealEO.setDelFlag("0");
        if(file!=null){
            TsAttachmentEO tsAttachmentEO = addTsAttachmentEO(file);
            bmSealEO.setSealFileExtend(tsAttachmentEO.getAttachmentType());
            bmSealEO.setSealStyle(tsAttachmentEO.getSavePath());
            bmSealEO.setSealFileId(tsAttachmentEO.getId());
//        bmSealEO.setSealStatus("0");//0 启用   1，未启用
        }
        //公章表
        if (StringUtils.isNotEmpty(bmSealEO.getId())) {
            //更新
            dao.updateByPrimaryKeySelective(bmSealEO);
        } else {
            //新增
            bmSealEO.setCreateTime(DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT));
            dao.insertSelective(bmSealEO);
        }
        return "0";

    }

    public TsAttachmentEO addTsAttachmentEO(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
        String extension = FileUtil.getFileExtension(file.getOriginalFilename());
        String fileSize = file.getSize() / 1024 + "KB";
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
        return tsAttachmentEO;
    }

    /**
     * @param id
     * @return
     */
    public int updateSealStatus(String id, String status) {
        return dao.updateSealStatus(id, status);
    }

    /**
     * 根据code查找实体
     * @param sealCode
     * @return
     */
    public BmSealEO findSealCode(String sealCode){
        return dao.findSealCode(sealCode);
    }


}
