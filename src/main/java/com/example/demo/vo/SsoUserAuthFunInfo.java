package com.example.demo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 权限信息
 *
 * @date: 2019-09-30
 * @author: chy
 */
@JsonInclude(JsonInclude.Include.ALWAYS)
public class SsoUserAuthFunInfo {

    /**
     * 功能Id
     */
    @JSONField(name = "Id", ordinal = 1)
    private Integer id;

    /**
     * 父功能Id
     */
    @JSONField(name = "ParentId", ordinal = 2)
    private Integer parentId;

    /**
     * 功能名称
     */
    @JSONField(name = "FunName", ordinal = 3)
    private String funName;

    /**
     * 功能类型
     */
    @JSONField(name = "FunType", ordinal = 4)
    private Byte funType;

    /**
     * 功能状态
     */
    @JSONField(name = "FunStatus", ordinal = 5)
    private Byte funStatus;

    /**
     * 创建时间
     */
    @JSONField(name = "CreateTime", ordinal = 6)
    private String createTime;

    /**
     * 所属应用编号
     */
    @JSONField(name = "AppId", ordinal = 7)
    private String appId;

    /**
     * 所属应用名称
     */
    @JSONField(name = "AppName", ordinal = 8)
    private String appName;

    /**
     * 所属用户Id
     */
    @JSONField(name = "UserId", ordinal = 9)
    private Integer userId;

    /**
     * 功能级别
     */
    @JSONField(name = "FunLv", ordinal = 10)
    private Integer funLv;

    /**
     * 应用状态
     */
    @JSONField(name = "AppStatus", ordinal = 11)
    private Byte appStatus;

    /**
     * 应用域
     */
    @JSONField(name = "AppDomain", ordinal = 12)
    private String appDomain;

    /**
     * 接收tokenUrl
     */
    @JSONField(name = "TokenUrl", ordinal = 13)
    private String tokenUrl;

    /**
     * 功能排序依据
     */
    @JSONField(name = "Sort", ordinal = 14)
    private Integer sort;

    /**
     * 应用图标
     */
    @JSONField(name = "Logo", ordinal = 15)
    private String logo;

    /**
     * 应用虚拟路径
     */
    @JSONField(name = "AbsoluteUrl", ordinal = 16)
    private String absoluteUrl;

    /**
     * 是否菜单
     */
    @JSONField(name = "IsMenu", ordinal = 17)
    private boolean isMenu;

    /**
     * 应用Id
     */
    @JSONField(name = "AId", ordinal = 18)
    private Integer aId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public Byte getFunType() {
        return funType;
    }

    public void setFunType(Byte funType) {
        this.funType = funType;
    }

    public Byte getFunStatus() {
        return funStatus;
    }

    public void setFunStatus(Byte funStatus) {
        this.funStatus = funStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFunLv() {
        return funLv;
    }

    public void setFunLv(Integer funLv) {
        this.funLv = funLv;
    }

    public Byte getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(Byte appStatus) {
        this.appStatus = appStatus;
    }

    public String getAppDomain() {
        return appDomain;
    }

    public void setAppDomain(String appDomain) {
        this.appDomain = appDomain;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAbsoluteUrl() {
        return absoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        this.absoluteUrl = absoluteUrl;
    }

    public boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(boolean isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }
}
