package com.xxq.service;

import java.util.List;

import com.xxq.dao.OrderItemDao;
import com.xxq.model.OrderItem;

public class OrderItemService {
	OrderItemDao orderItemDao=new OrderItemDao();
	public List<OrderItem> getAllOrderItems(){
		return orderItemDao.getAllOrderItems();
	}
	public int add(OrderItem orderItem) {
		return orderItemDao.add(orderItem);
	}
	public List<OrderItem> getByOrderInfo_id(String orderInfo_id) {
		return orderItemDao.getByOderInfo_id(orderInfo_id);
	}
	public int delete(String id) {
		return orderItemDao.delete(id);
	}
	public OrderItem getById(String id) {
		return orderItemDao.getById(id);
	}
}
