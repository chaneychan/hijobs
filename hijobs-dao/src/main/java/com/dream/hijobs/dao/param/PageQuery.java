package com.dream.hijobs.dao.param;

import java.io.Serializable;

/**
 * @author chaney.chan
 * 2014年11月20日
 */
public class PageQuery implements Serializable {
    private static final long serialVersionUID = -7507646548498597628L;
    private int pageSize = 20;
    private int pageNo = 1;
    
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getStartRow() {
    	int startRow = (pageNo - 1) * pageSize;
    	if(startRow < 0){
    		return 0;
    	}else {
			return startRow;
		}
    }
}
