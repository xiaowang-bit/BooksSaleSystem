package com.xxq.service;

import com.xxq.dao.BookDao;
import com.xxq.model.Book;

public class BookService {
	BookDao bookDao=new BookDao();
	public Book getById(String id) {
		return bookDao.getById(id);
	}
}
