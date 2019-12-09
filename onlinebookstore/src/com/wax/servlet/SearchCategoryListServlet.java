package com.wax.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wax.service.CategoryService;
import com.xxq.model.Category;

@WebServlet("/SearchCategoryListServlet")
public class SearchCategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchCategoryListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String category_id = request.getParameter("category_id");
		CategoryService categoryService=new CategoryService();
		List<Category> ctList= categoryService.searchCategory(category_id);
		HttpSession session = request.getSession();
		session.setAttribute("ctList", ctList);
		if(ctList.size()>0&&ctList!=null) {
			request.getRequestDispatcher("");
		}else {
			response.sendRedirect("");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
