package com.dream.hijobs.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 区县级行政区
 * @author chaney.chan
 * 2014年11月21日
 */
public class Area implements Serializable {
    
	private static final long serialVersionUID = 7756000839776664062L;

    private Integer id;

    /**
     * area_code 区/县级行政区编号
     */
    private String areaCode;

    /**
     * area_name 区/县级行政区名称
     */
    private String areaName;

    /**
     * father_code 所属市级行政区编号
     */
    private String fatherCode;

    private Date gmtModify;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getFatherCode() {
        return fatherCode;
    }

    public void setFatherCode(String fatherCode) {
        this.fatherCode = fatherCode;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}