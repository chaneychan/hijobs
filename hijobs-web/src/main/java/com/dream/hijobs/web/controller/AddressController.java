package com.dream.hijobs.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dream.hijobs.dao.domain.Province;
import com.dream.hijobs.service.address.AddressService;
import com.dream.hijobs.service.dto.Result;

@RestController
@RequestMapping(value="address")
public class AddressController {
	
    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    
	@Autowired
	private AddressService addressService;

	
	@RequestMapping(value="AllProvCityArea")
	public Result<List<Province>> load(HttpServletRequest request) {
		return addressService.getAllProvCityAreaList();
	}

}
