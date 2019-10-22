package com.example.demo.vo;

import java.util.Date;

/**
 * 所属组织架构
 *
 * @date: 2019-09-30
 * @author: chy
 */
public class SsoUserOrg {

    /**
     * Id
     */
    private Integer id;

    /**
     * 组织架构名称
     */
    private String orgName;

    /**
     * 父Id
     */
    private Integer parentId;

    /**
     * 组织架构全路径
     */
    private String orgFullPath;

    /**
     * 组织架构名称全路径
     */
    private String orgFullNamePath;

    /**
     * 组织架构状态
     */
    private Byte orgStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getOrgFullPath() {
        return orgFullPath;
    }

    public void setOrgFullPath(String orgFullPath) {
        this.orgFullPath = orgFullPath;
    }

    public String getOrgFullNamePath() {
        return orgFullNamePath;
    }

    public void setOrgFullNamePath(String orgFullNamePath) {
        this.orgFullNamePath = orgFullNamePath;
    }

    public Byte getOrgStatus() {
        return orgStatus;
    }

    public void setOrgStatus(Byte orgStatus) {
        this.orgStatus = orgStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
