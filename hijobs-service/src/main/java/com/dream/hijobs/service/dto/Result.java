package com.dream.hijobs.service.dto;

/**
 * 结果基类，用于返回外部系统调用结果
 * @author chaney.chan
 * 2014年9月2日 
 * @param <V>
 */
public class Result<V> {
	private ResultCode rc;
	private V value;

	public Result() {
		this.rc = ResultCode.SUCCESS;
	}
	
	public Result(ResultCode rc) {
		this.rc = rc;
	}

	public Result(ResultCode rc, V value) {
		this.rc = rc;
		this.value = value;
	}

//	/**
//	 * whether the request is success.
//	 * <p>
//	 * if the target is not exist, this method return true. 
//	 */
//	public boolean isSuccess() {
//		return rc.isSuccess();
//	}

	public V getValue() {
		return this.value;
	}

	/**
	 * @return the result code of this request
	 */
	public ResultCode getRc() {
		return rc;
	}

	/**
	 * @param rc the rc to set
	 */
	public void setRc(ResultCode rc) {
		this.rc = rc;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
}
