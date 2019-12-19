package com.wax.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wax.service.CategoryService;
import com.xxq.model.User;
import com.zhc.dao.UserDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		UserDao dao=new UserDao();
		User user = dao.searchByUserName(name);
		if(user!=null&&user.getPassword().equals(password)) {
			HttpSession session = request.getSession();
			session.setAttribute("login_user", user);
			CategoryService cs=new CategoryService();
			request.getSession().setAttribute("categorys",cs.getAllCategory());
			if("Admin88".equals(name)&&user.getPassword().equals(password)) {
				request.getRequestDispatcher("ManageShop.jsp").forward(request, response);;
			}else {
				request.getRequestDispatcher("shop.html").forward(request, response);;
			}
		}else {
			response.sendRedirect("fail.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
