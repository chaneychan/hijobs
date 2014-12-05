package com.dream.hijobs.service.address;

import java.util.List;

import com.dream.hijobs.dao.domain.Area;
import com.dream.hijobs.dao.domain.City;
import com.dream.hijobs.dao.domain.Province;
import com.dream.hijobs.service.dto.Result;

/**
 * 地址信息服务
 * @author chaney.chan
 * 2014年11月22日
 */
public interface AddressService {
	/**
	  * 获取所有省级行政区
	  * @return: Result<List<Province>> 系统调用成功:result.isSuccess()==true
	  * @author: chaney.chan
	  * @version: 2014年11月22日
	 */
	public Result<List<Province>> getAllprovinceList();
	
	/**
	  * 根据省级行政区code获取所有市级行政区
	  * @param: String provinceCode 省级行政区编号
	  * @return: Result<List<City>> 系统调用成功:result.isSuccess()==true
	  * @author: chaney.chan
	  * @version: 2014年11月22日
	 */
	public Result<List<City>> getAllCityListByProvinceCode(String provinceCode);
	
	
	/**
	  * 根据省级行政区code获取所有区/县级行政区
	  * @param: String provinceCode 省级行政区编号
	  * @return: Result<List<Area>> 系统调用成功:result.isSuccess()==true
	  * @author: chaney.chan
	  * @version: 2014年11月22日
	 */
	public Result<List<Area>> getAllAreaListByProvinceCode(String provinceCode);
	
	/**
	  * 根据市级行政区code获取所有区/县级行政区
	  * @param: String cityCode 市级行政区编号
	  * @return: Result<List<Area>> 系统调用成功:result.isSuccess()==true
	  * @author: chaney.chan
	  * @version: 2014年11月22日
	 */
	public Result<List<Area>> getAllAreaListByCityCode(String cityCode);
	
	/**
	  * 一次性加载所有省市区三级联动数据
	  * @return: Result<List<Province>> 系统调用成功:result.isSuccess()==true
	  * @author: chaney.chan
	  * @version: 2014年11月22日
	 */
	public Result<List<Province>> getAllProvCityAreaList();
	
	/**
	  * 获取所有市级行政区
	  * @return: Result<List<City>>
	  * @author: chaney.chan 系统调用成功:result.isSuccess()==true
	  * @version: 2014年11月22日
	 */
	public Result<List<City>> getAllCityList();
	
	/**
	  * 通过城市名称获取所有市级行政区
	  * @param: String cityName 城市名称
	  * @return: List<City>
	  * @author: chaney.chan
	  * @version: 2014年11月22日
	 */
	public Result<List<City>> getCityListByName(String cityName);
}
