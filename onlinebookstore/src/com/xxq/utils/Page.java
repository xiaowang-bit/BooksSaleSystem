package com.xxq.utils;

import java.util.List;
import java.util.Map;

import com.xxq.model.Book;

public class Page {
	private List objectList;
	private int totalCount;
	private int pagesize;
	private int currentPage;
	private int totalPage;
	public Page() {
		super();
	}
	public List getObjectList() {
		return objectList;
	}
	public void setObjectList(List<Object> objectList) {
		this.objectList = objectList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = this.totalCount%8==0?this.totalCount/8:this.totalCount/8+1;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Page(List<Book> searchAllBook, int totalCount, int currentPage, int pagesize) {
		super();
		this.objectList = searchAllBook;
		this.pagesize = pagesize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		this.totalPage =  this.totalCount%pagesize==0?this.totalCount/pagesize:(this.totalCount/pagesize+1);
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
}
