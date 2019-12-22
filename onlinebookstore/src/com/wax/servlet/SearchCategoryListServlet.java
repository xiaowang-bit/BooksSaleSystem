package com.wax.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wax.service.BookService;
import com.wax.service.CategoryService;
import com.xxq.model.Book;
import com.xxq.model.Category;
import com.xxq.utils.Page;

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
		HttpSession session = request.getSession();
		String book_name = request.getParameter("book_name");
		String cpage = request.getParameter("currentPage");
		if(cpage==null) {
			cpage="1";
		}
		int currentPage=Integer.parseInt(cpage);
		int pagesize=8;
		BookService bs=new BookService();
		int totalCount = bs.getTotalCount();
		List<Book> ctList = bs.searchCategoryBook(category_id, currentPage, pagesize);
		Page page=new Page(ctList,totalCount,  currentPage,  pagesize);
		session.setAttribute("books",page);
		if(ctList.size()>0&&ctList!=null) {
			request.getRequestDispatcher("shop.jsp").forward(request, response);;
		}else {
			request.getRequestDispatcher("shop.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
