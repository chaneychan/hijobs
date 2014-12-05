package com.dream.hijobs.service.dto;

import java.io.Serializable;
import java.util.List;

public class UserPageDTO implements Serializable{
	private static final long serialVersionUID = -3453501965349417152L;

	private int totalCount;
	private List<UserDTO> list;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<UserDTO> getList() {
		return list;
	}
	public void setList(List<UserDTO> list) {
		this.list = list;
	}
}
