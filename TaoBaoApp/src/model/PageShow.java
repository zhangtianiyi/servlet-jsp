package model;

import com.sun.rowset.CachedRowSetImpl;

public class PageShow {
	private CachedRowSetImpl rowSet; 
	private int pageSize;	
	private int currentPage;
	private int totalPage;	 
	
	public PageShow(){
	}
	
	public PageShow(CachedRowSetImpl rowSet, int pageSize, int currentPage, int totalPage) 
	{
		this.rowSet = rowSet;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
	}

    public CachedRowSetImpl getRowSet() {
        return rowSet;
    }

    public void setRowSet(CachedRowSetImpl rowSet){
        this.rowSet = rowSet;
    }

    public int getPageSize(){
        return pageSize;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    public int getCurrentPage(){
        return currentPage;
    }

    public void setCurrentPage(int currentPage){
        this.currentPage = currentPage;
    }

    public int getTotalPage(){
        return totalPage;
    }

    public void setTotalPage(int totalPage){
        this.totalPage = totalPage;
    }
}
