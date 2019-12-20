package com.xxq.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxq.model.OrderInfo;
import com.xxq.model.OrderItem;
import com.xxq.model.User;
import com.xxq.service.OrderInfoService;
import com.xxq.service.OrderItemService;

@WebServlet("/OrderToGenerateServlet")
public class OrderToGenerateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderToGenerateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//生成订单
		
		//处理中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		OrderItemService orderItemService=new OrderItemService();
		OrderInfoService orderInfoService=new OrderInfoService();
		
		User user = (User) request.getSession().getAttribute("login_user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/error/error.jsp");
			return;
		}
		
		String uuid=UUID.randomUUID().toString();//生成订单号
		//接收json数组并转换成List<OrderItem>
		List<OrderItem> list=new ArrayList<OrderItem>();
		float price=0;
		
		//生成订单表项对象，并设置属性值，存入orderinfo表
		List<OrderItem> cart=(List<OrderItem>) request.getSession().getAttribute("items");
		for (OrderItem p : cart) {
			OrderItem orderItem=new OrderItem();
			orderItem.setId(p.getId());
			orderItem.setNum(p.getNum());
			orderItem.setPrice(p.getPrice());
			orderItem.setOrderInfo_id(uuid);
			orderItem.setBook_id(p.getBook_id());
			price+=orderItem.getPrice();
			System.out.println(orderItem);
			list.add(orderItem);
		}
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(uuid);
		orderInfo.setOrderId(uuid);
		orderInfo.setNum(list.size());
		orderInfo.setPrice(price);
		orderInfo.setStatus(0);
		orderInfo.setUser_id(user.getId());
		orderInfoService.add(orderInfo);

		//存入orderitem表
		for(OrderItem orderItem:list) {
			orderItemService.add(orderItem);
		}
		
		//移除session里面被选中的商品
		cart.clear();
		request.getSession().setAttribute("items", cart);
		//返回刚刚选择的商品,放在弹出的付款页面中
		request.getRequestDispatcher("shop.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
