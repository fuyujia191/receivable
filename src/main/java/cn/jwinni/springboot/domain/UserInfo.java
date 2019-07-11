package cn.jwinni.springboot.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户报名信息表
 */
public class UserInfo implements Serializable {
    /**
     *  序列化id
     */
    private static final long serialVersionUID = 1l;
    /**
     * id 主键自增
     */
    private Integer id;
    /**
     *  客户名称
     */
    private String name;
    /**
     *  客户性别
     */
    private Integer sex;
    /**
     *  客户身份证号码
     */
    private String idcard;
    /**
     *  客户手机号码
     */
    private String phone;
    /**
     *  加入时间
     */
    private Date joinDate;
    /**
     *  添加时间
     */
    private Date addDate;
    /**
     *  夏令营营期
     */
    private String projectName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }
}
