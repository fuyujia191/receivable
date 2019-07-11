package cn.jwinni.springboot.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *  夏令营项目表
 */
public class Prodect implements Serializable {
    /**
     *  序列化id
     */
    private static final long serialVersionUID = 1l;
    /**
     *  id
     */
    private Integer id;
    /**
     *  夏令营项目名称
     */
    private String projectName;
    /**
     *  夏令营项目价格
     */
    private BigDecimal projectPrice;
    /**
     *  添加时间
     */
    private Date addDate;
    /**
     *  开始时间
     */
    private  Date startDate;
    /**
     *  结束时间
     */
    private  Date endDate;
    /**
     *  扩展参数1
     */
    private String param1;
    /**
     *  扩展参数2
     */
    private String param2;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public BigDecimal getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(BigDecimal projectPrice) {
        this.projectPrice = projectPrice;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }
}
