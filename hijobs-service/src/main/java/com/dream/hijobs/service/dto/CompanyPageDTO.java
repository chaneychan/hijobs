package com.dream.hijobs.service.dto;

import java.io.Serializable;
import java.util.List;

public class CompanyPageDTO implements Serializable{
	private static final long serialVersionUID = -3453501965349417152L;

	private int totalCount;
	private List<CompanyDTO> list;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<CompanyDTO> getList() {
		return list;
	}
	public void setList(List<CompanyDTO> list) {
		this.list = list;
	}
}
