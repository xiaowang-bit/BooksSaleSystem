package com.xxq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
//		if(rows>0) {
//			request.getRequestDispatcher("AllOrder.jsp");//返回到待付款订单页面
//		}else {
//			request.getRequestDispatcher("fail.jsp");
//		}
		Object[] options = { "确定" }; 
    	JOptionPane.showOptionDialog(null, "删除成功！", "提示", 
    	JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
    	null, options, options[0]); 
    	request.getRequestDispatcher("AllOrder.jsp").forward(request, response);;//返回到待付款订单页面
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
