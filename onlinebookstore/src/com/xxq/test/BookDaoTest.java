package com.xxq.test;

import org.junit.Test;

import com.xxq.dao.BookDao;
import com.xxq.model.Book;

public class BookDaoTest {

	@Test
	public void testGetById() {
		Book book=new Book();
		BookDao bookDao=new BookDao();
		book=bookDao.getById("020fada5-6a88-4565-aca5-dbf4cae14d54");
		System.out.println(book.getBookname());
		
	}

}
