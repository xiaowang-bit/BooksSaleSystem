package com.xxq.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xxq.model.Book;
import com.xxq.utils.C3P0Util;



public class BookDao {
	QueryRunner queryRunner=new QueryRunner();
	
	public Book getById(String id) {
		Connection conn=C3P0Util.getConnection();
		Book book=null;
		try {
			String sql="select * from book where id=?";
			book=queryRunner.query(conn, sql,new BeanHandler<Book>(Book.class), id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return book;
	}
	public Book getByName(String name) {
		Connection conn=C3P0Util.getConnection();
		Book book=null;
		try {
			String sql="select * from book where bookname=?";
			book=queryRunner.query(conn, sql,new BeanHandler<Book>(Book.class), name);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return book;
	}
	
}
