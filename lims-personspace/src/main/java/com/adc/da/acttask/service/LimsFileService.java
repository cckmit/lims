package com.adc.da.acttask.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.adc.da.calender.entity.PersonCalenderEO;
import com.adc.da.calender.service.PersonCalenderEOService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.common.PDFStampUtil;
import com.adc.da.file.store.IFileStore;
import com.adc.da.login.util.UserUtils;
import com.adc.da.message.entity.MessageEO;
import com.adc.da.message.service.MessageEOService;
import com.adc.da.sys.dao.BaseBusEODao;
import com.adc.da.sys.dao.TsAttachmentEODao;
import com.adc.da.sys.dao.UserEODao;
import com.adc.da.sys.entity.BaseBusEO;
import com.adc.da.sys.entity.TsAttachmentEO;
import com.adc.da.sys.entity.UserEO;
import com.adc.da.sys.service.OrgEOService;
import com.adc.da.util.EmailConfig;
import com.adc.da.util.MailUtils;
import com.adc.da.util.utils.DateUtils;
import com.adc.da.util.utils.FileUtil;
import com.adc.da.util.utils.StringUtils;
import com.adc.da.util.utils.UUID;
import com.adc.da.workflow.service.ActivitiTaskService;
import com.adc.da.workflow.vo.ActivitiTaskVO;
import com.zhuozhengsoft.pageoffice.FileSaver;


@Service
@Transactional(value = "transactionManager", readOnly = false, 
propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class LimsFileService {

    @Value("${file.path}")
    private String filepath;
    
    @Value("${watermark.name}")
    private String waterMarkName;
    
    @Autowired
    private IFileStore iFileStore;
     
    @Autowired
    private TsAttachmentEODao tsAttachmentEODao;
	
    @Autowired
    private BaseBusEODao baseBusEODao;
    
    @Autowired
    private EmailConfig emailConfig;
    
    @Autowired
    private UserEODao userEODao;
    
    @Autowired
    private MessageEOService messageEOService;
    
    @Autowired
    private PersonCalenderEOService calenderEOService;
    
    @Autowired
    private OrgEOService orgEOService;
    
    
    @Value("${mail.address}")
    private String mailAddr;
    
    @Autowired
    private ActivitiTaskService activitiTaskService;
    
    /**
     * 附件保存
    * @Title：saveAttachment
    * @param file
    * @return
    * @throws Exception
    * @return: String
    * @author： ljy  
    * @date： 2019年8月20日
     */
    public String saveAttachment(MultipartFile file) throws Exception {
		//当前日期
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		//文件后缀
		String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
		//文件大小
		long size = file.getSize()/1024;
		String sizeStr = String.valueOf(size);
		//文件流
		InputStream is = file.getInputStream();
		//返回路径
	    String path = this.iFileStore.storeFile(is, fileExtension, "");
	    //保存附件
	    TsAttachmentEO tsEo = new TsAttachmentEO();
	    //设置UUID
	    tsEo.setId(UUID.randomUUID());
		//删除标记  0 未删除;  1删除
	    tsEo.setDelFlag(0); 
	    tsEo.setCreateBy(UserUtils.getUserId());
	    tsEo.setCreateTime(date);
	    tsEo.setUpdateBy(UserUtils.getUserId());
	    tsEo.setUpdateTime(date);
	    //原附件名称
	    tsEo.setAttachmentName(FileUtil.getFileName(file.getOriginalFilename()));
	    //附件大小（kb）
	    tsEo.setAttachmentSize(sizeStr);
	    //附件类型（后缀）
	    tsEo.setAttachmentType(fileExtension);
	    //保存路径
	    tsEo.setSavePath(path);
	    //保存
	    tsAttachmentEODao.insert(tsEo);
	    return tsEo.getId();
	}
    
    /**
        *  pageoffice 转 PDF后的附件保存
    * @Title：saveAttachment
    * @param fs
    * @return
    * @throws Exception
    * @return: String
    * @author： ljy  
    * @date： 2019年8月22日
     */
    public String saveAttachment(FileSaver fs,String reportNo) throws Exception {
    	TsAttachmentEO tsEo = new TsAttachmentEO();
 	    //当前日期
 		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
 	    //设置UUID
 	    tsEo.setId(UUID.randomUUID());
 		//删除标记  0 未删除;  1删除
 	    tsEo.setDelFlag(0); 
 	    tsEo.setCreateBy(UserUtils.getUserId());
 	    tsEo.setCreateTime(date);
 	    tsEo.setUpdateBy(UserUtils.getUserId());
 	    tsEo.setUpdateTime(date);
 	    //原附件名称
 	    tsEo.setAttachmentName("试验报告【" + reportNo + "】");
 	    //附件大小（kb）
 	    tsEo.setAttachmentSize(String.valueOf(fs.getFileSize()));
 	    //附件类型（后缀）
 	    String fileExtend = fs.getFileExtName().substring(1, fs.getFileExtName().length());
 	    tsEo.setAttachmentType(fileExtend);
 	    //保存路径
 	    tsEo.setSavePath(fs.getFileName());
 	    //保存
 	    tsAttachmentEODao.insert(tsEo);
 	    //图片加水印
        String path = fs.getFileName();
	    //定义水印地址
	    String waterMarkPath = path.substring(0, path.lastIndexOf(ConstantUtils.SPOT));
	    String outputFile = waterMarkPath + ConstantUtils.WATERMARK + ConstantUtils.SPOT + fileExtend;
	    PDFStampUtil.waterMark(filepath + path, filepath + outputFile, waterMarkName);
	    //保存水印路径
	    tsEo.setWaterMarkPath(outputFile);
	    tsAttachmentEODao.updateByPrimaryKeySelective(tsEo);
	    return tsEo.getId();
	}
	/**
	 * 保存业务流程关联表
	* @Title：saveBaseBus
	* @param businessId 业务id
	* @param businessType 业务类型
	* @param title 标题
	* @return: void
	* @author： ljy  
	* @date： 2019年8月26日
	 */
	public String saveBaseBus (String businessId, String businessType, String title) {
		//保存业务BASEBUS
        BaseBusEO baseBusEO = new BaseBusEO();
        //设置UUID
        baseBusEO.setId(UUID.randomUUID());
		//设置时间
		String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
		baseBusEO.setCreateBy(UserUtils.getUserId());
		baseBusEO.setCreateTime(date);
        baseBusEO.setBusinessId(businessId);
        baseBusEO.setTitle(title);
        baseBusEO.setBusinessType(businessType);
        //保存BASEBUS
        baseBusEODao.insertBaseBus(baseBusEO);
        return baseBusEO.getId();
	}
    
    /**
     * 给待办人发送消息/邮件/工作日历
    * @Title：processSendMessages
    * @param splits 用户IDS
    * @param title 标题
    * @param link 链接
    * @param businessId 业务id
    * @return: void
    * @author： ljy  
    * @date： 2019年8月27日
     * @throws Exception 
     */
    public void processSendMessages(String[] splits, String title, 
    		String link, String businessId) throws Exception {
    	//当前日期
      	String date = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT);
    	List<String> userArray = Arrays.asList(splits);
    	for (int i = 0; i < userArray.size(); i++) {
	        //发送消息
			//报告较为特殊, 打开的链接为PDF
			 MessageEO messageEO = new MessageEO(UUID.randomUUID(), "0", link, date, 
					 UserUtils.getUser().getUsname(), title, userArray.get(i), businessId);
			messageEOService.insertSelective(messageEO);
			//工作日历
			calenderEOService.insertSelective(new PersonCalenderEO(UUID.randomUUID(), 
					date, title, userArray.get(i), ""));
			//发送邮件,邮件实体建立
			MailUtils email = new MailUtils();
			String token = UUID.randomUUID();
			email.setToken(token);
			UserEO user = userEODao.selectByPrimaryKey(userArray.get(i));
			if(StringUtils.isNotEmpty(user)) {
				ConstantUtils.DELAYMAILMAP.put(token, user.getUsid());
				try {
					if(StringUtils.isNotEmpty(user.getEmail())) {
    					email.setReceiver(user.getEmail());
    					email.setSubject(title);
    					email.setContent("<a href='"+mailAddr+token+"'>"+title+"</a> ");
    					//发送邮件
    					emailConfig.sendMailHtml(email);
    				}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
			
		}

    }
    
    /**
     * 根据任务id 获取流程中的用户ids
    * @Title：getProcessUsersByTaskId
    * @param taskId
    * @return
    * @return: String[]
    * @author： ljy  
    * @date： 2019年9月23日
     */
    public String[] getProcessUsersByTaskId(String taskId) {
    	//退回给相关人员发送消息
        List<ActivitiTaskVO> taskList = activitiTaskService.getProcessingRecords(taskId);
        Set<String> set = new HashSet<String>();
        for (ActivitiTaskVO vo : taskList) {
        	if(StringUtils.isNotEmpty(vo.getCurrentUsersIds())) {
        		String[] arr = vo.getCurrentUsersIds().split(ConstantUtils.COMMA);
    			for(String str : arr) {
    				set.add(str);
    			}
        	}
		}
        //返回
        return (String[]) set.toArray(new String[set.size()]);
    }
    

    /**
     * 根据组织机构id 获取该组织机构及以下的所有用户
    * @Title：getUserIdsByOrgId
    * @param orgId
    * @return
    * @return: String
    * @author： ljy  
    * @date： 2019年9月26日
     */
    public List<String> getUserIdsByOrgId(String orgId){
    	//根据组织机构id 获取该组织机构及以下的所有用户
    	List<UserEO> users = orgEOService.listUserEOByOrgId(orgId);
    	//设置返回值
		List<String> userIds = new ArrayList<>();
		//循环获取用户id
		for(UserEO user : users) {
			userIds.add(user.getUsid());
		}
		//返回
		return userIds;
    }
}
