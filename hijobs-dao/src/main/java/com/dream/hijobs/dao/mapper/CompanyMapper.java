package com.dream.hijobs.dao.mapper;

import java.util.List;

import com.dream.hijobs.dao.domain.Company;
import com.dream.hijobs.dao.param.CompanyPageQuery;

public interface CompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
    
    List<Company> getCompanys(CompanyPageQuery userPageQuery);
    
    int getCompanysCount(CompanyPageQuery userPageQuery);
    
    int deleteByLogical(Long id);
}