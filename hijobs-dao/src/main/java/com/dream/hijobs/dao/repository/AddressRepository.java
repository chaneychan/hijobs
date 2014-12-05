package com.dream.hijobs.dao.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dream.hijobs.dao.domain.Area;
import com.dream.hijobs.dao.domain.City;
import com.dream.hijobs.dao.domain.Province;
import com.dream.hijobs.dao.enums.IsSpecialProvince;
import com.dream.hijobs.dao.mapper.AreaMapper;
import com.dream.hijobs.dao.mapper.CityMapper;
import com.dream.hijobs.dao.mapper.ProvinceMapper;

@Repository
public class AddressRepository {
	
	@Autowired
	private ProvinceMapper ProvinceMapper;
	
	@Autowired
	private CityMapper CityMapper;
	
	@Autowired
	private AreaMapper AreaMapper;
	
	
	public List<Province> getAllprovinceList() {
		return ProvinceMapper.getAllprovinceList();
	}

	
	public List<City> getAllCityListByProvinceCode(String provinceCode){
		return CityMapper.getAllCityListByProvinceCode(provinceCode);
	}

	
	public List<Area> getAllAreaListByCityCode(String cityCode){
		return AreaMapper.getAllAreaListByCityCode(cityCode);
	}

	
	public List<Area> getAllAreaListByProvinceCode(String provinceCode){
		List<Area> provinceAllAreaList = new LinkedList<Area>();
		List<City> cityList = this.getAllCityListByProvinceCode(provinceCode);
		for (City City : cityList) {
			List<Area> areaList = this.getAllAreaListByCityCode(City.getCityCode());
			provinceAllAreaList.addAll(areaList);
		}
		return provinceAllAreaList;
	}

	
	public List<Province> getAllProvCityAreaList() {
//		List<Province> ProvinceList = getAllProvCityAreaListByTair(ALL_PROV_CITY_AREA_LIST);
		List<Province> ProvinceList = new ArrayList<>();
		if(ProvinceList==null || ProvinceList.size()==0){		
			ProvinceList = this.getAllProvCityAreaListForDB();
//			putAllProvCityAreaListToTair(ProvinceList);
		}
		return ProvinceList;
	}
	
//	@SuppressWarnings({ "unchecked" })
//	private List<Province> getAllProvCityAreaListByTair(String key){
//		Result<Object> readResult = tradecenterTairManage.get(key);
//		if(!readResult.isSuccess()){
//			throw new TradeCenterException(readResult.getErrMsg());
//		}
//		if(readResult.getModule()==null){
//			return null;
//		}
//		return (List<Province>)readResult.getModule();
//	}
	
//	private void putAllProvCityAreaListToTair(List<Province> obj){
//		Result<Boolean> putResult = tradecenterTairManage.put(ALL_PROV_CITY_AREA_LIST,obj);
//		if(!putResult.isSuccess()){
//			throw new TradeCenterException(putResult.getErrMsg());
//		}
//	}
	
	
	private List<Province> getAllProvCityAreaListForDB(){
		List<Province> ProvinceList = ProvinceMapper.getAllprovinceList();
		if(ProvinceList!=null && ProvinceList.size()>0){
			for (Province province : ProvinceList) {
				Integer isSpecial = province.getIsSpecial();
				List<City> cityList = CityMapper.getAllCityListByProvinceCode(province.getProvinceCode());
				province.setProvinCityList(cityList);
				for (City city : cityList) {
					List<Area> areaList = AreaMapper.getAllAreaListByCityCode(city.getCityCode());
					city.setAreaList(areaList);
					if(isSpecial!=null && isSpecial.intValue()==IsSpecialProvince.YES.getValue()){
						province.getProvinAreaList().addAll(areaList);
					}
				}
			}
		}
		return ProvinceList;
	}


	
	public List<City> getAllCityList()  {
		return CityMapper.getAllCityList();
	}
	
	public void init(){
//		List<Province> ProvinceList = getAllProvCityAreaListForDB();
//		try {
//			putAllProvCityAreaListToTair(ProvinceList);
//		} catch (TradeCenterException e) {
//			logger.error("初始化地址数据出错!",e);
//		}
	}

	
	public List<City> getCityListByName(String cityName){
		return CityMapper.getCityListByName(cityName);
	}
}
