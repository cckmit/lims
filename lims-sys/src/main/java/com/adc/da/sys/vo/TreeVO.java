package com.adc.da.sys.vo;

import java.util.List;

/**
 * 抽象类VO
 * 用于菜单和组织
 *
 * @param <T>
 * @author comments created by Lee Kwanho
 * date 2018-08-16
 * @see OrgVO
 * @see MenuVO
 */
public abstract class TreeVO<T extends TreeVO> {

    /**
     * id字段
     */
    protected String id;

    /**
     * 名称字段
     */
    protected String name;

    /**
     * 所有父级编号
     */
    protected String parentId;

    /**
     * 所有父级编号
     */
    protected String parentIds;

    /**
     * 父级编号
     */
    protected T parent;

    protected List<T> childList;

    /**
     * 删除标识
     */
    protected Integer delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public List<T> getChildList() {
        return childList;
    }

    public void setChildList(List<T> childList) {
        this.childList = childList;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
