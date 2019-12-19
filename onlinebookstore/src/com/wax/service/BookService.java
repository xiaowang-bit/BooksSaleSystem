package com.wax.service;

import java.util.ArrayList;
import java.util.List;

import com.wax.dao.BookDao;
import com.xxq.dao.OrderInfoDao;
import com.xxq.model.Book;
import com.xxq.utils.C3P0Util;

public class BookService {
	BookDao cdao=new BookDao();
	@SuppressWarnings("unused")
	public int addBook(Book book) {
		/**
		 * @author 王澳星
		 * @param Book对象
		 * @return int值，大于零表示添加成功，小于等于零则添加失败
		 */
		int row=0;
		List<Book> categoryList = cdao.searchById(book.getId());	
		if (categoryList.size()<=0||categoryList==null) {
			row = cdao.insert(book);
		}
		return row;
	}

	public List<Book> searchBook(String id ) {
		/**
		 * @author 王澳星
		 * @param Book的id
		 * @return Book列表
		 */
		 List<Book> list=new ArrayList<Book>();
		 list = cdao.searchById(id);
		 return list;
	}
	public List<Book> searchAllBook(int currentPage,int pagesize) {
		/**
		 * @author 王澳星
		 * @param Book的id
		 * @return Book列表
		 */
		 List<Book> list=new ArrayList<Book>();
		 list = cdao.searchAll(currentPage,pagesize);
		 return list;
	}
	public int getTotalCount() {
		String sql = "select count(1) from book ";
		return C3P0Util.getTotalCount(sql);
		
	}
}
