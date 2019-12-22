package com.zhc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

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
			if(mobilephone != null&&password!=null) {
					UserService userService = new UserService();
					boolean result = userService.addUser(user);
					if(result) {
						Object[] options = { "确定" }; 
			        	JOptionPane.showOptionDialog(null, "注册成功！", "提示", 
			        	JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
			        	null, options, options[0]); 
						request.getRequestDispatcher("login.html").forward(request, response);;
					}else {
						out.print("注册失败！");
					}
			}

		}else {
			out.print("用户名格式错误");
		}
		
		
		
		
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
