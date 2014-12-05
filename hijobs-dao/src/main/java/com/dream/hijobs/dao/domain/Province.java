package com.dream.hijobs.dao.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 省级行政区
 * @author chaney.chan
 * 2014年11月21日
 */
public class Province implements Serializable {
    
	private static final long serialVersionUID = 4036766499941995065L;

    private Integer id;

    /**
     * province_code 省级行政区编号
     */
    private String provinceCode;

    /**
     * province_name 省级行政区名称
     */
    private String provinceName;

    /**
     * is_special 是否特殊省 0否 1是
     */
    private Integer isSpecial;

    private Date gmtModify;

    private Date gmtCreate;
    
    
    private List<City> ProvinCityList=new ArrayList<City>();
    
    private List<Area> ProvinAreaList=new ArrayList<Area>();

    
	public List<City> getProvinCityList() {
		return ProvinCityList;
	}

	public void setProvinCityList(List<City> ProvinCityList) {
		this.ProvinCityList = ProvinCityList;
	}

	public List<Area> getProvinAreaList() {
		return ProvinAreaList;
	}

	public void setProvinAreaList(List<Area> ProvinAreaList) {
		this.ProvinAreaList = ProvinAreaList;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
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