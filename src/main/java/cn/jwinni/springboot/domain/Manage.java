package cn.jwinni.springboot.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统管理员
 */
public class Manage implements Serializable {

    /**
     *  序列化id
     */
    private static final long serialVersionUID = 1l;

    /**
     *  id
     */
    private Integer id;

    /**
     *  管理员名称
     */
    private String manageName;

    /**
     *  系统登录账号
     */
    private String adminName;

    /**
     *  系统密码
     */
    private String password;

    /**
     *  手机号码
     */
    private String telNo;

    /**
     *  添加时间
     */
    private Date addDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
