package com.example.demo.vo;
import java.util.LinkedList;

/**
 * 用户信息以及用户权限信息
 *
 * @date: 2019-09-30
 * @author: chy
 */
public class SsoUserInfo {

    /**
     * 用户Id
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 性别
     */
    private Byte gender;

    /**
     * 移动电话
     */
    private String phone;

    /**
     * 固定电话
     */
    private String tel;

    /**
     * 邮件
     */
    private String email;
    /**
     * 用户级别
     * */
    private Integer userLv;
    /**
     * 省份
     * */
    private String provinceName;
    /**
     * 城市
     * */
    private String cityName;
    /**
     * 区县
     * */
    private String regionName;
    /**
     * 行政代码
     * */
    private Integer xZCode;
    /**
     * 用户状态
     * */
    private Integer userStatus;
    /**
     * 用户应用权限
     * */
    private Long userAppAuth;
    /**
     * 用户服务权限
     * */
    private Long userServiceAuth;
    /**
     * 所属企业名称
     * */
    private String companyName;
    /**
     * 用户类别
     * */
    private Integer userType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName != null ? userName : "";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName != null ? realName : "";
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone != null ? phone : "";
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel != null ? tel : "";
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email != null ? email : "";
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvinceName(){ return provinceName != null ? provinceName : "";}
    public void setProvinceName(String provinceName){this.provinceName = provinceName;}

    public String getCityName(){ return cityName != null ? cityName : "";}
    public void setCityName(String cityName){this.cityName = cityName;}

    public String getRegionName(){ return regionName != null ? regionName : "";}
    public void setRegionName(String regionName){this.regionName = regionName;}

    public Integer getxZCode(){ return xZCode;}
    public void setxZCode(Integer xZCode){this.xZCode = xZCode;}

    public Integer getUserStatus(){ return userStatus;}
    public void setUserStatus(Integer userStatus){this.userStatus = userStatus;}

    public Long getUserAppAuth(){ return userAppAuth;}
    public void setUserAppAuth(long userAppAuth){this.userAppAuth = userAppAuth;}

    public Long getUserServiceAuth(){ return userServiceAuth;}
    public void setUserServiceAuth(long userServiceAuth){this.userServiceAuth = userServiceAuth;}

    public String getCompanyName(){ return companyName != null ? companyName : "";}
    public void setCompanyName(String companyName){this.companyName = companyName;}

    public Integer getUserType(){ return userType;}
    public void setUserType(Integer userType){this.userType = userType;}

    public Integer getUserLv(){ return userLv;}
    public void setUserLv(Integer userLv){this.userLv = userLv;}
}
