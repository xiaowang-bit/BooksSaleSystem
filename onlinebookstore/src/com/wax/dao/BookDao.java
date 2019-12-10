package com.wax.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xxq.model.Book;
import com.xxq.utils.C3P0Util;

public class BookDao {
	QueryRunner qr=new QueryRunner(C3P0Util.getDataSource());
	public int insert(Book book) {
		/**
		 * @param 传入一个Book对象
		 * @return int值，大于零表示插入成功，小于等于零则插入失败
		 * @author 王澳星
		 */
		int row=0;
		String sql="insert into book(id,bookname,author,price,imageName,description,category_id) "
				+ "vale(?,?,?,?,?,?,?)";
		Object[]ob={book.getId(),book.getBookname(),book.getAuthor(),book.getPrice(),book.getImageName(),book.getDescription(),book.getCategory_id()};
		try {
			row = qr.update(sql,ob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public List<Book> searchById(String id) {
		/**
		 * @param 传入一个Book的id
		 * @return Book列表
		 * @author 王澳星
		 */
		List<Book> list=new ArrayList<Book>();
		String sql="select * from book";
		try {
			list = qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public int update(Book book) {
		/**
		 * @param 传入一个Book对象
		 * @return int值，大于零表示更新成功，小于等于零则更新失败
		 * @author 王澳星
		 */
		int row=0;
		String sql="update book set bookname=?,author=?,price=?,imageName=?,description=?,category_id=? where id=?";
		Object[]ob={book.getBookname(),book.getAuthor(),book.getPrice(),book.getImageName(),book.getDescription(),book.getCategory_id(),book.getId()};
		try {
			row=qr.update(sql,ob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
	}
	public int delete(String id) {
		/**
		 * @author 王澳星
		 * @param 传入一个Book的id
		 * @return int值，大于零表示删除成功，小于等于零则删除失败
		 */
		int row =0;
		String sql="delete from book where id=?";
		try {
			row = qr.update(sql,id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row;
		
	}
}
