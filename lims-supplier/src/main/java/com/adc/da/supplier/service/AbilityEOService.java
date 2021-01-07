package com.adc.da.supplier.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.base.page.Pager;
import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.file.store.IFileStore;
import com.adc.da.login.util.UserUtils;
import com.adc.da.supplier.dao.AbilityEODao;
import com.adc.da.supplier.entity.AbilityEO;
import com.adc.da.supplier.entity.SupProjectEO;
import com.adc.da.supplier.page.AbilityEOPage;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.util.utils.CollectionUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;


/**
 *
 * <br>
 * <b>功能：</b>SUP_ABILITY AbilityEOService<br>
 * <b>作者：</b>code generator<br>
 * <b>日期：</b> 2019-08-15 <br>
 * <b>版权所有：<b>版权归北京卡达克数据技术中心所有。<br>
 */
@Service("abilityEOService")
@Transactional(value = "transactionManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class AbilityEOService extends BaseService<AbilityEO, String> {

    private static final Logger logger = LoggerFactory.getLogger(AbilityEOService.class);

    @Autowired
    private AbilityEODao dao;

    @Autowired
    private TsAttachmentEODao tsDao;

    @Autowired
    private IFileStore iFileStore;

    @Autowired
    private SupProjectEOService supProjectEOService;

    public AbilityEODao getDao() {
        return dao;
    }

    /**
     * 分页数据
     * @param page
     * @param searchPhrase
     * @return
     */
    public AbilityEOPage pageSet(AbilityEOPage page , String searchPhrase){
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
     * @param page
     * @param searchPhrase
     * @return
     */
    public List<AbilityEO> queryByPage(AbilityEOPage page, String searchPhrase) throws Exception {
        //配置查询条件
        page = pageSet(page, searchPhrase);
        return dao.queryByPage(page);
    }

    /**
     * 保存供应商能力示例
     * @param abilityEO
     */
    public void saveAbilityEO(AbilityEO abilityEO, MultipartFile file) throws IOException {
        abilityEO.setDelFlag("0");
        if(com.adc.da.util.utils.StringUtils.isNotEmpty(file)) {
            TsAttachmentEO tsAttachmentEO = this.saveAttachment(file);
            abilityEO.setAttachmentId(tsAttachmentEO.getId());
            abilityEO.setAttachmentName(tsAttachmentEO.getAttachmentName());
        }
        //新增检验项目
        dao.insertSelective(abilityEO);
        if(CollectionUtils.isNotEmpty(abilityEO.getSupProjectEOList())) {
            supProjectEOService.batchSavePro(abilityEO.getSupProjectEOList(), abilityEO.getId());
        }
    }

    /**
     * 编辑
     * @param abilityEO
     * @throws Exception 
     */
    public void updateAbilityEO(AbilityEO abilityEO, MultipartFile file) throws Exception {
        //删除子表（检验项目表）
        //supProjectEOService.batchDelete(abilityEO.getId());
        if(com.adc.da.util.utils.StringUtils.isNotEmpty(file)) {
            TsAttachmentEO tsAttachmentEO = this.saveAttachment(file);
            abilityEO.setAttachmentId(tsAttachmentEO.getId());
            abilityEO.setAttachmentName(tsAttachmentEO.getAttachmentName());
        }
        abilityEO.setDelFlag("0");
        //保存修改后的供应商信息
        dao.updateByPrimaryKeySelective(abilityEO);
        //重新添加检验项目
        if(com.adc.da.util.utils.StringUtils.isNotEmpty(abilityEO.getSupProjectEOList())){
            //supProjectEOService.batchSavePro(abilityEO.getSupProjectEOList(), abilityEO.getId());
        	for (SupProjectEO eo : abilityEO.getSupProjectEOList()) {
        		SupProjectEO selectEO = supProjectEOService.selectByPrimaryKey(eo.getId());
        		if(com.adc.da.util.utils.StringUtils.isEmpty(selectEO)) {
        			eo.setSupAbilityId(abilityEO.getId());
        			eo.setDelFlag("0");
        			supProjectEOService.insertSelective(eo);
        			break;
        		}
        		supProjectEOService.updateByPrimaryKeySelective(eo);
			}
        }
    }

    /**
     * 保存附件
     * @param file
     * @return
     * @throws IOException
     */
    public TsAttachmentEO saveAttachment(MultipartFile file) throws IOException {
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
        return tsAttachmentEO;
    }


}
