package com.dream.hijobs.dao.mapper;

import java.util.List;

import com.dream.hijobs.dao.domain.City;


public interface CityMapper {
	/**
	  * 方法描述：根据省级行政区code获取所有市级行政区
	  * @param: String provinceCode 省级行政区编号
	 */
	public List<City> getAllCityListByProvinceCode(String provinceCode);
	
	/**
	  * 方法描述：获取所有市级行政区
	 */
	public List<City> getAllCityList();
	
	/**
	  * 方法描述：获取所有市级行政区
	 */
	public void update(City o);
	
	/**
	  * 方法描述：通过城市名称获取所有市级行政区
	 */
	public List<City> getCityListByName(String cityName);
}