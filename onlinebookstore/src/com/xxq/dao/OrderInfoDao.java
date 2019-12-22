package com.xxq.dao;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xxq.model.OrderInfo;
import com.xxq.model.OrderItem;
import com.xxq.utils.C3P0Util;

public class OrderInfoDao {
	QueryRunner queryRunner=new QueryRunner();
	
	public int  add(OrderInfo orderInfo)
	{
		Connection conn=null;
		int rows=0;
		try {
			conn=C3P0Util.getConnection();
			String sql="insert into orderinfo values(?,?,?,?,?,?)";
			rows=queryRunner.update(conn, sql,orderInfo.getId(),orderInfo.getOrderId(),orderInfo.getNum(),orderInfo.getPrice()
					,orderInfo.getStatus(),orderInfo.getUser_id());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		C3P0Util.close(conn);
		}
		return rows;
	}
	public List<OrderInfo> getByStatus(int status,String id) {
		Connection conn=C3P0Util.getConnection();
		List<OrderInfo> list=null;
		try {
			String sql="select * from orderinfo where status=? and user_id=?";
			list=queryRunner.query(conn, sql,new BeanListHandler<OrderInfo>(OrderInfo.class), status,id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return list;
	}
	public OrderInfo getById(String id) {
		Connection conn=C3P0Util.getConnection();
		OrderInfo orderInfo=null;
		try {
			String sql="select * from orderinfo where id=?";
			orderInfo=queryRunner.query(conn, sql, new BeanHandler<OrderInfo>(OrderInfo.class), id);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return orderInfo;
	}
	public int delete(String id) {
		Connection conn=C3P0Util.getConnection();
		int rows=0;
		try {
			String sql="delete from orderinfo where id=?";
			rows=queryRunner.update(conn, sql, id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return rows;
	}
	public int update(OrderInfo orderInfo) {
		Connection conn=C3P0Util.getConnection();
		int rows=0;
		try {
			String sql="update orderinfo set id=?,num=?,price=?,status=?,user_id=? where id=?";
			rows=queryRunner.update(conn, sql, orderInfo.getId(),orderInfo.getNum()
					,orderInfo.getPrice(),orderInfo.getStatus(),orderInfo.getUser_id(),orderInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return rows;
	}
	public int updateNum(OrderInfo orderInfo) {
		Connection conn=C3P0Util.getConnection();
		int rows=0;
		try {
			String sql="update orderinfo num=?,price=? where id=?";
			rows=queryRunner.update(conn, sql, orderInfo.getNum()
					,orderInfo.getPrice(),orderInfo.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			C3P0Util.close(conn);
		}
		return rows;
	}
}
