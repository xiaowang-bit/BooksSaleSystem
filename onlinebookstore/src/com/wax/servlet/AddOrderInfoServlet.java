package com.wax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wax.service.OrderInfoService;
import com.xxq.model.OrderInfo;
import com.xxq.utils.CreateOderId;

@WebServlet("/AddOrderInfoServlet")
public class AddOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddOrderInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(request.getParameter("id"));
		orderInfo.setOrderId(CreateOderId.getOrderCode(Long.parseLong(request.getParameter("user_id"))));
		orderInfo.setPrice(Integer.parseInt(request.getParameter("price")));
		orderInfo.setNum(Integer.parseInt(request.getParameter("price")));
		orderInfo.setUser_id(request.getParameter("user_id"));
		OrderInfoService ois=new OrderInfoService();
		ois.addCar(orderInfo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
