package com.adc.da.summary.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.adc.da.sys.entity.ParamEO;
import com.adc.da.sys.service.ParamEOService;
import com.adc.da.util.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.adc.da.base.service.BaseService;
import com.adc.da.common.ConstantUtils;
import com.adc.da.summary.dao.CostSummaryEODao;
import com.adc.da.summary.entity.CostSummaryEO;
import com.adc.da.summary.page.CostSummaryEOPage;
import com.adc.da.util.utils.StringUtils;

@Service("CostSummaryEOService")
@Transactional(value = "transactionManager", readOnly = false,
        propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class CostSummaryEOService extends BaseService<CostSummaryEO, String> {

	@Autowired
	private CostSummaryEODao costSummaryEODao;

	@Autowired
    private ParamEOService paramEOService;
	
	
	public CostSummaryEODao getDao() {
		return costSummaryEODao;
	}
	
	/**
     * 费用结算-分页查询
     * @Title: page
     * @param page
     * @return
     * @return ResponseMessage<PageInfo<CostSummaryVO>>
     * @author: ljy
     * @date: 2020年8月13日
     */
	public List<CostSummaryEO> page(CostSummaryEOPage page, String searchPhrase){
		//通用查询的参数不为空即为通用查询
		if(StringUtils.isNotEmpty(searchPhrase) && 
			StringUtils.isNotEmpty(searchPhrase.trim())){
            searchPhrase = searchPhrase.trim();
            Pattern datePattern = Pattern.compile(ConstantUtils.REGEX_EXCEPT_BLANK);
            Matcher dateMatcher = datePattern.matcher(searchPhrase);
            List<String> list = new ArrayList<>();
            while (dateMatcher.find()) {
                String search = dateMatcher.group();
                list.add(search);
            }
            page.setSearchPhrase(list);
        }
		return costSummaryEODao.queryByPage(page);
	}


    /**
     * 根据供应商ID查询费用
     * @param supId
     * @return
     */
    public CostSummaryEO getCostSummaryEOBySupId(String supId,String trialTaskId,String formKey) {
        return costSummaryEODao.getCostSummaryEOBySupId(supId,trialTaskId,formKey);
    }

    /**
     * 生成结算单编号
     */
    public String getCostSummaryNum(String prefix,String code){
        ParamEO paramByCode = paramEOService.getParamByCode(code);
        String paramValue = paramByCode.getParamValue();
        String id = paramByCode.getId();
        int i = Integer.parseInt(paramValue);
        //获取当前年份
        String dateYear = DateUtils.dateToString(new Date(), ConstantUtils.DATE_FORMAT3);
        //流水号
        String newSerialNumber = null;
        if (i < 10)
        {
            newSerialNumber = "00" + i;
        }
        else if (i >= 10 && i < 100)
        {
            newSerialNumber = "0" + i;
        }
        else
        {
            newSerialNumber = Integer.toString(i);
        }
        String num = prefix + newSerialNumber + "-" + dateYear;
        int newNum = 0;
        if(999 == i ){
            newNum = 0;
        }else {
            newNum = i + 1;
        }
        String s = Integer.toString(newNum);
        paramEOService.updateParamValue(id,s);
        return num;
    }
}
