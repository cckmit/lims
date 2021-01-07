package com.adc.da.kCar.vo;

import java.util.ArrayList;
import java.util.List;


//接受整车样品对接接口返回样品数据
public class KCarInfoVO {
    /*
    *K库车辆状态
     */
    private String LSTATUS;
    /*
     *车型代码
     */
    private String CONFIGNAME;
    /*
     *底盘号
     */
    private String BATHOLITHNO;
    /*
     *发动机号
     */
    private String EGNO;
    /*
     *车况
     */
    private String RESTOREINFO;
    /*
     *电机号
     */
    private String MOTORNUM;
    /*
     *电池包总成
     */
    private String BATTERYPN;
    /*
     *VIN码
     */
    private String VIN;

    /**
     * 借车信息
     */
    List<KCarBorrowInfoVO> BorrowInfo = new ArrayList<>();
    
    
    
	public String getLSTATUS() {
		return LSTATUS;
	}

	public void setLSTATUS(String lSTATUS) {
		LSTATUS = lSTATUS;
	}

	public String getCONFIGNAME() {
		return CONFIGNAME;
	}

	public void setCONFIGNAME(String cONFIGNAME) {
		CONFIGNAME = cONFIGNAME;
	}

	public String getBATHOLITHNO() {
		return BATHOLITHNO;
	}

	public void setBATHOLITHNO(String bATHOLITHNO) {
		BATHOLITHNO = bATHOLITHNO;
	}

	public String getEGNO() {
		return EGNO;
	}

	public void setEGNO(String eGNO) {
		EGNO = eGNO;
	}

	public String getRESTOREINFO() {
		return RESTOREINFO;
	}

	public void setRESTOREINFO(String rESTOREINFO) {
		RESTOREINFO = rESTOREINFO;
	}

	public String getMOTORNUM() {
		return MOTORNUM;
	}

	public void setMOTORNUM(String mOTORNUM) {
		MOTORNUM = mOTORNUM;
	}

	public String getBATTERYPN() {
		return BATTERYPN;
	}

	public void setBATTERYPN(String bATTERYPN) {
		BATTERYPN = bATTERYPN;
	}

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String vIN) {
		VIN = vIN;
	}

	public List<KCarBorrowInfoVO> getBorrowInfo() {
		return BorrowInfo;
	}

	public void setBorrowInfo(List<KCarBorrowInfoVO> borrowInfo) {
		BorrowInfo = borrowInfo;
	}

}
