package com.wax.service;
import java.util.List;

import com.xxq.dao.OrderInfoDao;
import com.xxq.model.OrderInfo;

public class OrderInfoService {
	OrderInfoDao orderInfoDao=new OrderInfoDao();
	public int addCar(OrderInfo orderInfo) {
		OrderInfo byId = orderInfoDao.getById(orderInfo.getId());
		if(byId!=null) {
			orderInfo.setNum(orderInfo.getNum()+1);
			orderInfo.setPrice(byId.getPrice()+orderInfo.getPrice());
			return orderInfoDao.updateNum(orderInfo);
		}	
		return orderInfoDao.add(orderInfo);
	}
}
