package com.zhc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxq.model.User;
import com.xxq.utils.CreateOderId;
import com.zhc.service.UserService;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		
		String id = CreateOderId.getOrderCode(System.currentTimeMillis())+request.getParameter("account");
		String username = request.getParameter("account");
		String password = request.getParameter("password");
		String cellphone = request.getParameter("cellphone");
		String mobilephone = request.getParameter("mobilephone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		User user = new User(id, username, password, cellphone, mobilephone, address, email);
		
		
		PrintWriter out = response.getWriter();
		
		//用户名正则,4到16位（字母，数字，下划线，减号）
		if(username.matches("^[a-zA-Z0-9_-]{4,16}$")) {
			out.print("用户名格式正确");
		}else {
			out.print("用户名格式错误");
		}
		
		//密码正则：至少8个字符，至少1个大写字母，1个小写字母和1个数字
		if(password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
			out.print("密码格式正确");
		}else {
			out.print("密码格式错误");
		}
		
		//验证cellphone的正则
		if(cellphone.matches("^[0][1-9]{2,3}[0-9]{5,10}$")) {
			out.print("cellphone格式正确");
		}else {
			out.print("cellphone格式错误");
		}
		
		//手机号正则
		if(mobilephone != null) {
			if(mobilephone.matches("^[1]([3-9])[0-9]{9}$")) {
				out.print("手机号格式正确");
			}else {
				out.print("手机号格式错误");
			}
		}
		
		
		
		
		
		UserService userService = new UserService();
		boolean result = userService.addUser(user);
		
		if(result) {
			out.print("注册成功！");
		}else {
			out.print("注册失败！");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
