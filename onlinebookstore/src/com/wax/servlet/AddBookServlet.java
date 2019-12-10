package com.wax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wax.service.BookService;
import com.wax.service.CategoryService;
import com.xxq.model.Book;
import com.xxq.model.Category;

@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String book_id = request.getParameter("book_id");
		String book_name = request.getParameter("book_name");
		String book_description = request.getParameter("book_description");
		String book_author=request.getParameter("book_author");
		float book_price=Float.parseFloat(request.getParameter("book_price"));
		String book_imageName=request.getParameter("book_imageName");
		String book_category_id=request.getParameter("book_category_id");
		BookService bookService=new BookService();
		Book book=new Book(book_id, book_name, book_author, book_price, book_imageName, book_description, book_category_id);
		int row = bookService.addBook(book);
		if(row>0) {
			request.getRequestDispatcher("");
		}else {
			response.sendRedirect("");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
