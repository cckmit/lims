package com.adc.da.sys.entity;

import com.adc.da.base.entity.BaseEntity;

/**
 * <p>字段列表：</p>
 * <li>userId -> user_id</li>
 * <li>orgId -> org_id</li>
 * @author comments created by Lee Kwanho
 * date 2018-08-16
 */

public class UserOrgEO extends BaseEntity {
    /**
     * 用户id
     */
    private String userId;

    /**
     * 组织id
     */
    private String orgId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
