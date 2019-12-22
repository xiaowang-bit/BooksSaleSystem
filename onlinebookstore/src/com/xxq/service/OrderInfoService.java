package com.xxq.service;

import java.util.List;

import com.xxq.dao.OrderInfoDao;
import com.xxq.model.OrderInfo;

public class OrderInfoService {
	OrderInfoDao orderInfoDao=new OrderInfoDao();
	public int add(OrderInfo orderInfo) {
		return orderInfoDao.add(orderInfo);
	}
	public List<OrderInfo> getByStatus(int status,String id) {
		return orderInfoDao.getByStatus(status,id);
	}
	public OrderInfo getById(String id) {
		return orderInfoDao.getById(id);
	}
	public int delete(String id) {
		return orderInfoDao.delete(id);
	}
	public int update(OrderInfo orderInfo) {
		return orderInfoDao.update(orderInfo);
	}
}
