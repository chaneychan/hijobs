package com.dream.hijobs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dream.hijobs.dao.domain.Company;
import com.dream.hijobs.dao.param.CompanyPageQuery;
import com.dream.hijobs.service.company.CompanyService;
import com.dream.hijobs.service.dto.CompanyDTO;
import com.dream.hijobs.service.dto.CompanyPageDTO;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;

@Controller
@RequestMapping("/company")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
    
    @Autowired
    private CompanyService companyService;
	
	@RequestMapping("page/{page}")
	@ResponseBody
	public Result<CompanyPageDTO> getCompanys(@PathVariable int page, HttpServletRequest request){
		String name = request.getParameter("key");
		logger.info("company search key:{},page:{}",name,page);
		CompanyPageQuery companyPageQuery = new CompanyPageQuery();
		companyPageQuery.setPageNo(page);
		companyPageQuery.setName(name);
		return companyService.getCompanys(companyPageQuery);
	}
	
	@RequestMapping("{id}")
	@ResponseBody
	public Result<CompanyDTO> getCompany(@PathVariable long id,HttpServletRequest request){
		logger.info("company search id:{}",id);
		return companyService.getCompany(id);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Result update(HttpServletRequest request,CompanyDTO companyDTO,@PathVariable long id){
		if (companyDTO == null || companyDTO.getId() == null) {
			return new Result(ResultCode.COMPANY_IS_NULL);
		}
		companyDTO.setId(id);
		Company company = new Company();
		BeanUtils.copyProperties(companyDTO ,company);
		return companyService.update(company);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Result add(HttpServletRequest request,CompanyDTO companyDTO){
		if (companyDTO == null) {
			return new Result(ResultCode.COMPANY_IS_NULL);
		}
		Company company = new Company();
		BeanUtils.copyProperties(companyDTO ,company);
		return companyService.add(company);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Result add(HttpServletRequest request,@PathVariable long id){
		return companyService.delete(id);
	}
}
