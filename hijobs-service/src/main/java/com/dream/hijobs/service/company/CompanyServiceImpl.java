package com.dream.hijobs.service.company;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dream.hijobs.dao.domain.Company;
import com.dream.hijobs.dao.param.CompanyPageQuery;
import com.dream.hijobs.dao.repository.CompanyRepository;
import com.dream.hijobs.service.dto.CompanyDTO;
import com.dream.hijobs.service.dto.CompanyPageDTO;
import com.dream.hijobs.service.dto.Result;
import com.dream.hijobs.service.dto.ResultCode;

@Service
public class CompanyServiceImpl implements CompanyService {
	
    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Result<CompanyPageDTO> getCompanys(CompanyPageQuery companyPageQuery) {
		Result<CompanyPageDTO> result = new Result<CompanyPageDTO>();
		CompanyPageDTO dto = new CompanyPageDTO();
		List<Company> companys = companyRepository.getCompanys(companyPageQuery);
		int totalCount = companyRepository.getCompanysCount(companyPageQuery);
		List<CompanyDTO> companyDTOs = new ArrayList<CompanyDTO>();
		for (Company company : companys) {
			if (company != null) {
				CompanyDTO companyDTO = new CompanyDTO();
				BeanUtils.copyProperties(company, companyDTO);
				companyDTOs.add(companyDTO);
			}
		}
		dto.setList(companyDTOs);
		dto.setTotalCount(totalCount);
		result.setValue(dto);
		return result;
	}
	
	public Result<CompanyDTO> getCompany(long id) {
		Result<CompanyDTO> result = new Result<CompanyDTO>();
		Company company = companyRepository.selectById(id);
		if (company != null) {
			CompanyDTO companyDTO = new CompanyDTO();
			BeanUtils.copyProperties(company, companyDTO);
		}
		result.setValue(null);
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Result update(Company company) {
		Result result = new Result();
		try {
			Company thisCompany = new Company();
			BeanUtils.copyProperties(company, thisCompany);
			companyRepository.update(thisCompany);
		} catch (Exception e) {
			logger.error("update company error",e);
			return new Result(ResultCode.SERVER_ERROR);
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public Result add(Company company) {
		try {
			companyRepository.insert(company);
		} catch (Exception e) {
			logger.error("add company error",e);
			return new Result(ResultCode.SERVER_ERROR);
		}
		return new Result();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Result delete(long id) {
		try {
			companyRepository.delete(id);
		} catch (Exception e) {
			logger.error("delete company error",e);
			return new Result(ResultCode.SERVER_ERROR);
		}
		return new Result();
	}
}
