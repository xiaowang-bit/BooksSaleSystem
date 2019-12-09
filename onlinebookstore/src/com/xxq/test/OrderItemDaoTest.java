package com.xxq.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.xxq.dao.OrderItemDao;
import com.xxq.model.OrderItem;

public class OrderItemDaoTest {

	@Test
	public void testGetAllOrderItems() {
		OrderItemDao orderItemDao=new OrderItemDao();
		List<OrderItem> ois=orderItemDao.getAllOrderItems();
		System.out.println(ois);
	}

}
