package com.xxq.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.xxq.model.OrderInfo;
import com.xxq.service.OrderInfoService;

@WebServlet("/OrderToPayServlet")
public class OrderToPayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderToPayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理订单的付款
		
		//处理中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		OrderInfoService orderInfoService=new OrderInfoService();
		String id2=request.getParameter("id");//获取订单的id
		OrderInfo orderInfo=orderInfoService.getById(id2);
		orderInfo.setStatus(1);
		int rows=orderInfoService.update(orderInfo);
		if(rows>0) {
		Object[] options = { "确定" }; 
    	JOptionPane.showOptionDialog(null, "修改成功！", "提示", 
    	JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
    	null, options, options[0]); 
    	request.getRequestDispatcher("AllOrder.jsp").forward(request, response);
    	}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
