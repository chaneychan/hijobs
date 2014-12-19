package com.dream.hijobs.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dream.hijobs.dao.domain.Company;
import com.dream.hijobs.dao.mapper.CompanyMapper;
import com.dream.hijobs.dao.param.CompanyPageQuery;

@Repository
public class CompanyRepository {

	@Autowired
	private CompanyMapper mapper;
	
	public long insert(Company company){
		company.setGmtCreated(new Date());
		company.setGmtModified(new Date());
		company.setIsDel(false);
		return mapper.insert(company);
	}
	
	public int update(Company company){
		return mapper.updateByPrimaryKeySelective(company);
	}
	
	public Company selectById(long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public List<Company> getCompanys(CompanyPageQuery companyPageQuery){
		return mapper.getCompanys(companyPageQuery);
	}
	
	public int getCompanysCount(CompanyPageQuery companyPageQuery){
		return mapper.getCompanysCount(companyPageQuery);
	}
	
	public int delete(long id){
		return mapper.deleteByLogical(id);
	}
}
