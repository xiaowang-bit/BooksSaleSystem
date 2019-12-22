package com.xxq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xxq.model.OrderInfo;
import com.xxq.model.OrderItem;
import com.xxq.model.User;
import com.xxq.service.OrderInfoService;
import com.xxq.service.OrderItemService;


@WebServlet("/OrderStatus0Servlet")
public class OrderStatus0Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderStatus0Servlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//返回前台待付款订单数据
		
		//处理中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		User attribute =(User) request.getSession().getAttribute("login_user");
		String id = attribute.getId();
		OrderInfoService orderInfoService=new OrderInfoService();
		//返回status=0的商品(待付款)给页面，作为代付款订单
		List<OrderInfo> orderInfos=orderInfoService.getByStatus(0,id);
		request.setAttribute("will_pay", orderInfos);
		request.getRequestDispatcher("AllOrder.jsp").forward(request, response);
//		String jsString=JSONObject.toJSONString(orderInfos);
//		response.getWriter().write(jsString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
