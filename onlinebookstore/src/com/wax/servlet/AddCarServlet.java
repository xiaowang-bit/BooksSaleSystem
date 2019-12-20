package com.wax.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		Float book_price = Float.parseFloat(request.getParameter("book_price"));
		String book_id = request.getParameter("book_id");
		String nums = request.getParameter("num"+book_id);
		if(nums==null) {
			nums="1";
		}
		int num = Integer.parseInt(nums);
		String id=CreateOderId.getOrderCode(System.currentTimeMillis());
		List<OrderItem> oldItem=(List<OrderItem>) request.getSession().getAttribute("items");
		if(oldItem!=null&&oldItem.size()>0) {
			for(OrderItem od:oldItem) {
				if(book_id.equals(od.getBook_id())){
					od.setNum(num);
					od.setPrice(num*book_price);
				}else {
					OrderItem item=new OrderItem();
					item.setId(id);
					item.setBook_id(book_id);
					item.setNum(num);
					item.setPrice(book_price*num);
					oldItem.add(item);
				}
			}
		}else {
			oldItem=new ArrayList<OrderItem>();
			OrderItem item=new OrderItem();
			item.setId(id);
			item.setBook_id(book_id);
			item.setNum(num);
			item.setPrice(book_price*num);
			System.out.println(oldItem);
			item.setOrderInfo_id("0");
			oldItem.add(item);
		}
		request.getSession().setAttribute("items", oldItem);	
		request.getRequestDispatcher("shop.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
