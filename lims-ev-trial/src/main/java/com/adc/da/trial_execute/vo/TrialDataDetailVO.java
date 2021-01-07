package com.adc.da.trial_execute.vo;

import com.adc.da.base.entity.BaseEntity;

public class TrialDataDetailVO extends BaseEntity {
    /**
     *主键
     */
    private String id;

    /**
     *试验数据id
     */
    private String trialdataId;

    /**
     *累计台时h
     */
    private String totalHours;

    /**
     *工况时间
     */
    private String operationTime;

    /**
     *转速
     */
    private String speed;

    /**
     *扭矩
     */
    private String torque;

    /**
     *功率
     */
    private String p;

    /**
     *修正扭矩
     */
    private String tEc;

    /**
     *修正功率
     */
    private String pEc;

    /**
     *燃油油耗量
     */
    private String fbRate;

    /**
     *燃油消耗率
     */
    private String fuelcosp;

    /**
     *活塞漏气量
     */
    private String blowVal;

    /**
     *出水温度
     */
    private String two;

    /**
     *出水压力
     */
    private String pwo;

    /**
     *进水温度
     */
    private String twi;

    /**
     *进水压力
     */
    private String pwi;

    /**
     *排气温度
     */
    private String tExh;

    /**
     *排气背力
     */
    private String pExh;

    /**
     *燃油温度
     */
    private String tFuel;

    /**
     *燃油压力
     */
    private String pFuel;

    /**
     *进气温度
     */
    private String tInlet;

    /**
     *进气压力
     */
    private String pInlet;

    /**
     *机油温度
     */
    private String tOil;

    /**
     *机油压力
     */
    private String pOil;

    /**
     *曲轴箱压力
     */
    private String pCrank;

    /**
     *大气压力
     */
    private String pAir;

    /**
     *大气温度
     */
    private String tAir;

    /**
     *大气湿度
     */
    private String phi;

    /**
     *涡前排温
     */
    private String tExhWq;

    /**
     *涡前排压
     */
    private String pExhWq;

    /**
     *催后排温
     */
    private String tExhCh;

    /**
     *催后排压
     */
    private String pExhCh;

    /**
     *一缸排温
     */
    private String tExcCOne;

    /**
     *二缸排温
     */
    private String tExcCTwo;

    /**
     *三缸排温
     */
    private String tExcCThree;

    /**
     *四缸排温
     */
    private String tExcCFour;

    /**
     *中冷进气温
     */
    private String tIcI;

    /**
     *中冷进气压
     */
    private String pIcI;

    /**
     *中冷出气温
     */
    private String tIcO;

    /**
     *中冷出气压
     */
    private String pIcO;

    /**
     *油门开度
     */
    private String alpha;

    /**
     *创建者
     */
    private String createBy;

    /**
     *创建时间
     */
    private String createTime;
    
    /**
     * 删除标记 0 未删除; 1 删除
     */
    private String delFlag;
    
    /**
     * 创建者名
     */
    private String createName;
    
    /**
     *试验任务id
     */
    private String trialtaskId;
    
    
    
   	public String getTrialtaskId() {
		return trialtaskId;
	}

	public void setTrialtaskId(String trialtaskId) {
		this.trialtaskId = trialtaskId;
	}
    
	public String getCreateName() {
   		return createName;
   	}

   	public void setCreateName(String createName) {
   		this.createName = createName;
   	}
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTrialdataId() {
		return trialdataId;
	}

	public void setTrialdataId(String trialdataId) {
		this.trialdataId = trialdataId;
	}

	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	public String getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getTorque() {
		return torque;
	}

	public void setTorque(String torque) {
		this.torque = torque;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String gettEc() {
		return tEc;
	}

	public void settEc(String tEc) {
		this.tEc = tEc;
	}

	public String getpEc() {
		return pEc;
	}

	public void setpEc(String pEc) {
		this.pEc = pEc;
	}

	public String getFbRate() {
		return fbRate;
	}

	public void setFbRate(String fbRate) {
		this.fbRate = fbRate;
	}

	public String getFuelcosp() {
		return fuelcosp;
	}

	public void setFuelcosp(String fuelcosp) {
		this.fuelcosp = fuelcosp;
	}

	public String getBlowVal() {
		return blowVal;
	}

	public void setBlowVal(String blowVal) {
		this.blowVal = blowVal;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public String getPwo() {
		return pwo;
	}

	public void setPwo(String pwo) {
		this.pwo = pwo;
	}

	public String getTwi() {
		return twi;
	}

	public void setTwi(String twi) {
		this.twi = twi;
	}

	public String getPwi() {
		return pwi;
	}

	public void setPwi(String pwi) {
		this.pwi = pwi;
	}

	public String gettExh() {
		return tExh;
	}

	public void settExh(String tExh) {
		this.tExh = tExh;
	}

	public String gettFuel() {
		return tFuel;
	}

	public void settFuel(String tFuel) {
		this.tFuel = tFuel;
	}

	public String getpFuel() {
		return pFuel;
	}

	public void setpFuel(String pFuel) {
		this.pFuel = pFuel;
	}

	public String gettInlet() {
		return tInlet;
	}

	public void settInlet(String tInlet) {
		this.tInlet = tInlet;
	}

	public String getpInlet() {
		return pInlet;
	}

	public void setpInlet(String pInlet) {
		this.pInlet = pInlet;
	}

	public String gettOil() {
		return tOil;
	}

	public void settOil(String tOil) {
		this.tOil = tOil;
	}

	public String getpOil() {
		return pOil;
	}

	public void setpOil(String pOil) {
		this.pOil = pOil;
	}

	public String getpCrank() {
		return pCrank;
	}

	public void setpCrank(String pCrank) {
		this.pCrank = pCrank;
	}

	public String getpAir() {
		return pAir;
	}

	public void setpAir(String pAir) {
		this.pAir = pAir;
	}

	public String gettAir() {
		return tAir;
	}

	public void settAir(String tAir) {
		this.tAir = tAir;
	}

	public String getPhi() {
		return phi;
	}

	public void setPhi(String phi) {
		this.phi = phi;
	}

	public String gettExhWq() {
		return tExhWq;
	}

	public void settExhWq(String tExhWq) {
		this.tExhWq = tExhWq;
	}

	public String getpExhWq() {
		return pExhWq;
	}

	public void setpExhWq(String pExhWq) {
		this.pExhWq = pExhWq;
	}

	public String gettExhCh() {
		return tExhCh;
	}

	public void settExhCh(String tExhCh) {
		this.tExhCh = tExhCh;
	}

	public String getpExhCh() {
		return pExhCh;
	}

	public void setpExhCh(String pExhCh) {
		this.pExhCh = pExhCh;
	}

	public String gettExcCOne() {
		return tExcCOne;
	}

	public void settExcCOne(String tExcCOne) {
		this.tExcCOne = tExcCOne;
	}

	public String gettExcCTwo() {
		return tExcCTwo;
	}

	public void settExcCTwo(String tExcCTwo) {
		this.tExcCTwo = tExcCTwo;
	}

	public String gettExcCThree() {
		return tExcCThree;
	}

	public void settExcCThree(String tExcCThree) {
		this.tExcCThree = tExcCThree;
	}

	public String gettExcCFour() {
		return tExcCFour;
	}

	public void settExcCFour(String tExcCFour) {
		this.tExcCFour = tExcCFour;
	}

	public String gettIcI() {
		return tIcI;
	}

	public void settIcI(String tIcI) {
		this.tIcI = tIcI;
	}

	public String getpIcI() {
		return pIcI;
	}

	public void setpIcI(String pIcI) {
		this.pIcI = pIcI;
	}

	public String gettIcO() {
		return tIcO;
	}

	public void settIcO(String tIcO) {
		this.tIcO = tIcO;
	}

	public String getpIcO() {
		return pIcO;
	}

	public void setpIcO(String pIcO) {
		this.pIcO = pIcO;
	}

	public String getAlpha() {
		return alpha;
	}

	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getpExh() {
		return pExh;
	}

	public void setpExh(String pExh) {
		this.pExh = pExh;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

    
}