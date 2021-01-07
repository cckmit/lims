package com.adc.da.login.security.validatecode;

/**
 * 验证码实体类
 */
public class VerifyCode {

    /**
     * 验证码
     */
    private String code;

    /**
     * 背景图片
     */
    private byte[] imgBytes;

    /**
     * 过期时间
     */
    private long expireTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
