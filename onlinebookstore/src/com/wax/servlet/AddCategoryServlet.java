package com.wax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wax.service.CategoryService;
import com.xxq.model.Category;
import com.xxq.utils.CreateOderId;

@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCategoryServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String category_id = CreateOderId.getAgainCode(System.currentTimeMillis());
		String category_name = request.getParameter("category_name");
		String category_description = request.getParameter("category_description");
		CategoryService categoryService=new CategoryService();
		Category ct=new Category(category_id, category_name, category_description);
		int row = categoryService.addCategory(ct);
		if(row>0) {
			CategoryService cs=new CategoryService();
			request.getSession().setAttribute("categorys",cs.getAllCategory());
			request.getRequestDispatcher("ManageShop.jsp").forward(request, response);
		}else {
			response.sendRedirect("fail.jsp");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
