package com.dream.hijobs.dao.mapper;

import java.util.List;

import com.dream.hijobs.dao.domain.Area;


public interface AreaMapper {
	/**
	  * 方法描述：根据市级行政区code获取所有区/县级行政区
	  * @param: String cityCode 市级行政区编号
	 */
	public List<Area> getAllAreaListByCityCode(String cityCode);
}