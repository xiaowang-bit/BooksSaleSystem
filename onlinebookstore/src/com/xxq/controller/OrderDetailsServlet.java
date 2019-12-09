package com.xxq.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.xxq.model.Book;
import com.xxq.model.OrderDetails;
import com.xxq.model.OrderItem;
import com.xxq.service.BookService;
import com.xxq.service.OrderInfoService;
import com.xxq.service.OrderItemService;


@WebServlet("/OrderDetailsServlet")
public class OrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public OrderDetailsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//显示订单详情
		//处理中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		OrderInfoService orderInfoService=new OrderInfoService();
		BookService bookService =new BookService();
		List<OrderDetails> list=null;
		String id=request.getParameter("id");//获取订单的id
		OrderItemService orderItemService=new OrderItemService();
		List<OrderItem> orderItems=orderItemService.getByOrderInfo_id(id);
		for(OrderItem orderItem:orderItems) {
			Book book=bookService.getById(orderItem.getBook_id());
			OrderDetails orderDetails=new OrderDetails();
			orderDetails.setBook(book);
			orderDetails.setOrderItem(orderItem);
			list.add(orderDetails);
		}
		//传json数据到前台
		String jsString=JSONObject.toJSONString(list);
		response.getWriter().write(jsString);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
