package com.dream.hijobs.dao.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 市级行政区
 * @author chaney.chan
 * 2014年11月21日
 */
public class City implements Serializable {
    
	private static final long serialVersionUID = 7376051377826734333L;

    private Integer id;

    /**
     * city_code 市级行政区编号
     */
    private String cityCode;

    /**
     * city_name 市级行政区名称
     */
    private String cityName;
    
    /**
     * first_letter 首字母
     */
    private String firstLetter;
    

    /**
     * father_code 所属省级行政区编号
     */
    private String fatherCode;

    private Date gmtModify;

    private Date gmtCreate;

    private List<Area> AreaList;

    public List<Area> getAreaList() {
		return AreaList;
	}

	public void setAreaList(List<Area> AreaList) {
		this.AreaList = AreaList;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
}