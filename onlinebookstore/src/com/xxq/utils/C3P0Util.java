package com.xxq.utils;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class C3P0Util {
    
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 获取连接池对象
     * @return
     */
	
    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     * 从池中获取连接对象
     * @return
     */
    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 释放资源
     * @param connection
     */
    public static void release(ResultSet rs, PreparedStatement ps, Connection conn) {
        try {
       	 if (rs != null) {
    			rs.close();
    			rs = null; // 垃圾回收，上！
    		}
    		if (ps != null) {
    			ps.close();
    			ps = null; // 垃圾回收，上！
    		}
    		if (conn != null) {
    			conn.close();
    			conn = null; // 垃圾回收，上！
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void close(Connection conn){
    	if (conn != null) {
    		try {
    			conn.close();
    		} catch (SQLException e) {
    		e.printStackTrace();
    			throw new RuntimeException("关闭连接失败",e);
    			}
    		}
        }
}

