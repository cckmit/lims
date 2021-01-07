package com.adc.da.log.entity;

import com.adc.da.base.entity.BaseEntity;

import java.util.Date;

/**
 * <b>功能：</b>TS_LOG LogEOEntity<br>
 * <b>版权所有：<b>版权归天津卡达克数据技术中心所有。<br>
 * <p>
 * <p>字段列表：</p>
 * <li>ipAddress -> ip_address</li>
 * <li>browser -> browser</li>
 * <li>startTime -> start_time</li>
 * <li>endTime -> end_time</li>
 * <li>usid -> usid</li>
 * <li>operateTime -> operate_time</li>
 * <li>method -> method</li>
 * <li>description -> description</li>
 * <li>className -> class_name</li>
 * <li>account -> account</li>
 * <li>id -> id</li>
 * date 2018-08-15
 *
 * @author comments created by Lee Kwanho
 * @see com.adc.da.log.controller.LogEOController
 */
public class LogEO extends BaseEntity {

    /**
     * ip地址
     */
    private String ipAddress;

    /**
     * 浏览器信息
     */
    private String browser;

    /**
     * 生成时间
     * 格式转换为 年-月-日 时：分：秒
     * 类型为Date 与 VO不同
     */
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 日志结束时间（意义不明，数值与日志生成时间相同）
     * 格式转换为 年-月-日 时：分：秒
     * 类型为Date 与 VO不同
     */
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 操作用户的ID
     */
    private String usid;

    /**
     * 默认为空，意义不明，操作时间
     * 格式转换为 年-月-日 时：分：秒
     * 类型为Date 与 VO不同
     */
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    /**
     * 日志涉及的操作方法
     */
    private String method;

    /**
     * 默认为空，描述字段
     */
    private String description;

    /**
     * 日志涉及的类名
     */
    private String className;

    /**
     * 操作用户的账号
     */
    private String account;

    /**
     * 日志ID
     */
    private String id;

    /**  **/
    public String getIpAddress() {
        return this.ipAddress;
    }

    /**  **/
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**  **/
    public String getBrowser() {
        return this.browser;
    }

    /**  **/
    public void setBrowser(String browser) {
        this.browser = browser;
    }

    /**  **/
    public Date getStartTime() {
        return this.startTime;
    }

    /**  **/
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**  **/
    public Date getEndTime() {
        return this.endTime;
    }

    /**  **/
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**  **/
    public String getUsid() {
        return this.usid;
    }

    /**  **/
    public void setUsid(String usid) {
        this.usid = usid;
    }

    /**  **/
    public Date getOperateTime() {
        return this.operateTime;
    }

    /**  **/
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    /**  **/
    public String getMethod() {
        return this.method;
    }

    /**  **/
    public void setMethod(String method) {
        this.method = method;
    }

    /**  **/
    public String getDescription() {
        return this.description;
    }

    /**  **/
    public void setDescription(String description) {
        this.description = description;
    }

    /**  **/
    public String getClassName() {
        return this.className;
    }

    /**  **/
    public void setClassName(String className) {
        this.className = className;
    }

    /**  **/
    public String getAccount() {
        return this.account;
    }

    /**  **/
    public void setAccount(String account) {
        this.account = account;
    }

    /**  **/
    public String getId() {
        return this.id;
    }

    /**  **/
    public void setId(String id) {
        this.id = id;
    }

}
