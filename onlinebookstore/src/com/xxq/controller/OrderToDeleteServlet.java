package com.xxq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxq.service.OrderInfoService;

@WebServlet("/OrderToDeleteServlet")
public class OrderToDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderToDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//删除订单
		
		//处理中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		OrderInfoService orderInfoService=new OrderInfoService();
		String id=request.getParameter("id");//获取订单的id
		int rows=orderInfoService.delete(id);
		if(rows>0) {
			response.sendRedirect(request.getContextPath()+"/oderItems.html");//返回到待付款订单页面
		}else {
			response.sendRedirect(request.getContextPath()+"/error.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
