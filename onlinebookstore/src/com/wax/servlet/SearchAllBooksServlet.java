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
import com.xxq.model.Book;
import com.xxq.utils.Page;

@WebServlet("/SearchAllBooksServlet")
public class SearchAllBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchAllBooksServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String cpage = request.getParameter("currentPage");
		if(cpage==null) {
			cpage="1";
		}
		int currentPage=Integer.parseInt(cpage);
		int pagesize=8;
		BookService bs=new BookService();
		int totalCount = bs.getTotalCount();
		List<Book> searchAllBook = bs.searchAllBook(currentPage, pagesize);
		Page page=new Page(searchAllBook,totalCount,  currentPage,  pagesize);
		session.setAttribute("books",page);
		request.getRequestDispatcher("shop.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
