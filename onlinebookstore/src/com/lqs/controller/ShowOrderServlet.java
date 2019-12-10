package com.lqs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxq.model.Order;
import com.xxq.model.User;

import com.xxq.service.OrderInfoService;



public class ShowOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.�õ���ǰ�û�
		User user = (User) request.getSession().getAttribute("user");

		if (user == null) {
			response.getWriter().write(
					"����<a href='" + request.getContextPath()
							+ "/index.jsp'>��¼</a>");
			return;
		}

		// 2.����OrderService�в�ѯ��������
		OrderInfoService service = new OrderInfoService();

		try {
			List<Order> orders = service.getByStatus(user);

			request.setAttribute("orders", orders);

			request.getRequestDispatcher("/showOrder.jsp").forward(request,
					response);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
