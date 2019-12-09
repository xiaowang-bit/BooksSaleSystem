package com.zhc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxq.model.User;
import com.zhc.service.UserService;


public class UpdateUserServlet extends HttpServlet {
	
    public UpdateUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cellphone = request.getParameter("cellphone");
		String mobilephone = request.getParameter("mobilephone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		User user = new User(id, username, password, cellphone, mobilephone, address, email);
		
		UserService userService = new UserService();
		boolean result = userService.updateUser(user);
		
		PrintWriter out = response.getWriter();
		if(result) {
			out.print("更改成功！");
		}else {
			out.print("更改失败！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
