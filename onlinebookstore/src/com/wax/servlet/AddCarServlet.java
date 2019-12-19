package com.wax.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wax.service.BookService;
import com.xxq.model.OrderItem;
import com.xxq.utils.CreateOderId;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCarServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		BookService bs=new BookService();
		OrderItem item=new OrderItem();
		String book_price = request.getParameter("book_price");
		item.setBook_id(request.getParameter("book_id"));
		item.setId(CreateOderId.getOrderCode(System.currentTimeMillis()));
		Object oldItem = request.getSession().getAttribute("item");
		if(oldeitem!=null)
		item.setNum();
		item.setPrice(item.getNum()*Long.parseLong(book_price));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
