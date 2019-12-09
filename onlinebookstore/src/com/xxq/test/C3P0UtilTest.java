package com.xxq.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xxq.utils.C3P0Util;

public class C3P0UtilTest {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	@Test
	public void testGetDataSource() {
		dataSource=(ComboPooledDataSource) C3P0Util.getDataSource();
	}

	@Test
	public void testGetConnection() {
		Connection conn=C3P0Util.getConnection();
		System.out.println(conn);
		System.out.println("连接成功！");
	}

	@Test
	public void testRelease() {
		
	}
	@Test
	public void close() {
		
	}
}
