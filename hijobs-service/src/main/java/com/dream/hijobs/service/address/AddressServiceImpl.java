package com.dream.hijobs.service.address;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.hijobs.dao.domain.Area;
import com.dream.hijobs.dao.domain.City;
import com.dream.hijobs.dao.domain.Province;
import com.dream.hijobs.dao.repository.AddressRepository;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;

/**
 * @author chaney.chan 2014年11月22日
 */
@Service
public class AddressServiceImpl implements AddressService {
	private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);

	@Autowired
	private AddressRepository AddressRepository;
	
	private Result<List<Province>> result = null;

	@Override
	public Result<List<Province>> getAllprovinceList() {
		Result<List<Province>> result = new Result<List<Province>>();
		try {
			List<Province> list = AddressRepository.getAllprovinceList();
			result.setValue(list);
		} catch (Exception e) {
			logger.error("AddressServiceImpl.getAllprovinceList()", e);
			result.setRc(ResultCode.SERVER_ERROR);
		}
		return result;
	}

	@Override
	public Result<List<City>> getAllCityListByProvinceCode(String provinceCode) {
		Result<List<City>> result = new Result<List<City>>();
		if (StringUtils.isBlank(provinceCode)) {
			result.setRc(ResultCode.PROVINCE_CODE_PARAMETER_ERROR);
			return result;
		}
		try {
			List<City> list = AddressRepository.getAllCityListByProvinceCode(provinceCode);
			result.setValue(list);
		} catch (Exception e) {
			logger.error("AddressServiceImpl.getAllCityListByProvinceCode(String),provinceCode="+ provinceCode, e);
			result.setRc(ResultCode.SERVER_ERROR);
		}
		return result;
	}

	@Override
	public Result<List<Area>> getAllAreaListByCityCode(String cityCode) {
		Result<List<Area>> result = new Result<List<Area>>();
		if (StringUtils.isBlank(cityCode)) {
			result.setRc(ResultCode.CITY_CODE_PARAMETER_ERROR);
			return result;
		}
		try {
			List<Area> list = AddressRepository.getAllAreaListByCityCode(cityCode);
			result.setValue(list);
		} catch (Exception e) {
			logger.error("AddressServiceImpl.getAllAreaListByCityCode(String),cityCode="+ cityCode, e);
			result.setRc(ResultCode.SERVER_ERROR);
		}
		return result;
	}

	@Override
	public Result<List<Area>> getAllAreaListByProvinceCode(String provinceCode) {
		Result<List<Area>> result = new Result<List<Area>>();
		if (StringUtils.isBlank(provinceCode)) {
			result.setRc(ResultCode.PROVINCE_CODE_PARAMETER_ERROR);
			return result;
		}
		try {
			List<Area> list = AddressRepository.getAllAreaListByProvinceCode(provinceCode);
			result.setValue(list);
		} catch (Exception e) {
			logger.error("AddressServiceImpl.getAllAreaListByProvinceCode(String),provinceCode="+ provinceCode, e);
			result.setRc(ResultCode.SERVER_ERROR);
		}
		return result;
	}

	@Override
	public Result<List<Province>> getAllProvCityAreaList() {
		if (result != null) {
			return result;
		}else {
			try {
				result = new Result<List<Province>>();
				List<Province> list = AddressRepository.getAllProvCityAreaList();
				result.setValue(list);
			} catch (Exception e) {
				logger.error("AddressServiceImpl.getAllprovinceList()", e);
				result.setRc(ResultCode.SERVER_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result<List<City>> getAllCityList() {
		Result<List<City>> result = new Result<List<City>>();
		try {
			List<City> list = AddressRepository.getAllCityList();
			result.setValue(list);
		} catch (Exception e) {
			logger.error("AddressServiceImpl.getAllCityList()", e);
			result.setRc(ResultCode.SERVER_ERROR);
		}
		return result;
	}

	@Override
	public Result<List<City>> getCityListByName(String cityName) {
		Result<List<City>> result = new Result<List<City>>();
		try {
			List<City> list = AddressRepository.getCityListByName(cityName);
			result.setValue(list);
		} catch (Exception e) {
			logger.error("AddressServiceImpl.getCityListByName(String),cityName="+ cityName, e);
			result.setRc(ResultCode.SERVER_ERROR);
		}
		return result;
	}

}
