package com.dream.hijobs.service.company;

import com.dream.hijobs.dao.domain.Company;
import com.dream.hijobs.dao.param.CompanyPageQuery;
import com.dream.hijobs.service.dto.CompanyDTO;
import com.dream.hijobs.service.dto.CompanyPageDTO;
import com.dream.hijobs.service.dto.Result;

public interface CompanyService {
	
	public Result<CompanyDTO> getCompany(long id);
	
	public Result<CompanyPageDTO> getCompanys(CompanyPageQuery companyPageQuery);
	
	public Result update(Company company);
	
	public Result add(Company company);
	
	public Result delete(long id);
	
}
