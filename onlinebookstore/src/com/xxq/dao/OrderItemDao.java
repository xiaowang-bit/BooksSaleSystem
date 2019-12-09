package com.xxq.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xxq.model.Book;
import com.xxq.model.OrderItem;
import com.xxq.utils.C3P0Util;



public class OrderItemDao {
	QueryRunner queryRunner=new QueryRunner();
	
	public List <OrderItem> getAllOrderItems() {
		Connection conn=C3P0Util.getConnection();
		List <OrderItem> list=null;
		try {
			String sql="select * from orderitem";
			list=queryRunner.query(conn, sql, new BeanListHandler<OrderItem>(OrderItem.class));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return list;
	}
	public int  add(OrderItem orderItem)
	{
		Connection conn=null;
		int rows=0;
		try {
			conn=C3P0Util.getConnection();
			String sql="insert into orderitem values(?,?,?,?,?)";
			rows=queryRunner.update(conn, sql,orderItem.getId(),orderItem.getNum()
					,orderItem.getPrice(),orderItem.getOrderInfo_id(),orderItem.getBook_id());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		C3P0Util.close(conn);
		}
		return rows;
	}
	
	public List<OrderItem> getByOderInfo_id(String orderInfo_id) {
		Connection conn=C3P0Util.getConnection();
		List<OrderItem> list=null;
		try {
			String sql="select * from orderitem where orderInfo_id=?";
			list=queryRunner.query(conn, sql,new BeanListHandler<OrderItem>(OrderItem.class), orderInfo_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return list;
	}
	public int delete(String id) {
		Connection conn=C3P0Util.getConnection();
		int rows=0;
		try {
			String sql="delete from orderitem where id=?";
			rows=queryRunner.update(conn, sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return rows;
	}
	public OrderItem getById(String id) {
		Connection conn=C3P0Util.getConnection();
		OrderItem orderItem=null;
		try {
			String sql="select * from orderitem where id=?";
			orderItem=queryRunner.query(conn, sql, new BeanHandler<OrderItem>(OrderItem.class), id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return orderItem;
	}
}
