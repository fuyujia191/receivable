package cn.jwinni.springboot.domain;

import java.util.Date;

/**
 *  短信验证码表
 */
public class ValidateCode  {

    /**
     *  id
     */
    private Integer id;
    /**
     *  手机号码
     */
    private String telNo;
    /**
     *  验证码
     */
    private String code;
    /**
     *  创建时间
     */
    private Date createDate;
    /**
     *  过期时间
     */
    private  String expireDate;
    /**
     *  使用情况   0 未使用   1 已使用
     */
    private  Integer isUsed;
    /**
     *  使用时间
     */
    private  Date usingDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public Date getUsingDate() {
        return usingDate;
    }

    public void setUsingDate(Date usingDate) {
        this.usingDate = usingDate;
    }
}
