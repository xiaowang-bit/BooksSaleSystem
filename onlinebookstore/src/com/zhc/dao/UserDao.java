package com.zhc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xxq.model.User;
import com.xxq.utils.C3P0Util;


public class UserDao {
	
	//根据用户id判断是否存在此用户
	public boolean isExist(String id) {
		return queryUserById(id) == null ? false : true; //返回false表示数据库中不存在此id，true表示已存在
	}	
	
	
	//注册用户
	public boolean addUser(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = C3P0Util.getConnection();
			String sql = "insert into users values(?,?,?,?,?,?,?)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getCellphone());
			pstmt.setString(5, user.getMobilephone());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getEmail());
			count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//注销用户（删除）
	public boolean DeleteUser(String id) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = C3P0Util.getConnection();
			String sql = "delete from users where id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//修改用户信息
	public boolean UpdateUser(User user) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		int count;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = C3P0Util.getConnection();
			String sql = "update users set username = ?,password = ?,cellphone = ?,mobilephone = ?,"
					+ "address = ?,email = ? where id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getCellphone());
			pstmt.setString(4, user.getMobilephone());
			pstmt.setString(5, user.getAddress());
			pstmt.setString(6, user.getEmail());
			pstmt.setString(7, user.getId());
			count = pstmt.executeUpdate();
			if(count > 0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//根据账户id查询用户
	public User queryUserById(String id) {
		User user = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = C3P0Util.getConnection();
			String sql = "select * from users where id = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String cellphone = rs.getString("cellphone");
				String mobilephone = rs.getString("mobilephone");
				String address = rs.getString("address");
				String email = rs.getString("email");
				user = new User(id, username, password, cellphone, mobilephone, address, email);
			}
			return user;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
