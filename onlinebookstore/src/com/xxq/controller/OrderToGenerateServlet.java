package com.xxq.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxq.model.OrderInfo;
import com.xxq.model.OrderItem;
import com.xxq.model.User;
import com.xxq.service.OrderInfoService;
import com.xxq.service.OrderItemService;

@WebServlet("/OrderToGenerateServlet")
public class OrderToGenerateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderToGenerateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//处理中文乱码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		OrderItemService orderItemService=new OrderItemService();
		OrderInfoService orderInfoService=new OrderInfoService();
		User user=(User)request.getSession().getAttribute("user");
		String uuid=UUID.randomUUID().toString();
		//接收json数组并转换成List<OrderItem>
		List<OrderItem> list=null;
		float price=0;
		String jsonStr=request.getParameter("json");//接收json字符串
		JSONObject json=JSON.parseObject(jsonStr);//json字符串换成jsonobject对象
		JSONArray jsarr=json.getJSONArray("数组名");//json对象通过数组名取的对应的数组
		for(int i=0;i<jsarr.size();i++) {
			OrderItem orderItem=new OrderItem();
			JSONObject jsobj=jsarr.getJSONObject(i);////jsonarray对象通过getjsonobjext(index)方法取得数组里面的jsonobject对象
			orderItem.setId(jsobj.getString("id"));
			orderItem.setNum(jsobj.getIntValue("num"));
			orderItem.setPrice(jsobj.getFloatValue("price"));
			orderItem.setOrderInfo_id(jsobj.getString(uuid));
			orderItem.setBook_id(jsobj.getString("book_id"));
			price+=orderItem.getPrice();
			list.add(orderItem);
		}
		
		//存入orderitem表
		for(OrderItem orderItem:list) {
			orderItemService.add(orderItem);
		}
		
		//生成订单表项对象，并设置属性值，存入orderinfo表
		OrderInfo orderInfo=new OrderInfo();
		orderInfo.setId(uuid);
		orderInfo.setNum(list.size());
		orderInfo.setPrice(price);
		orderInfo.setStatus(0);
		orderInfo.setUser_id(user.getId());
		orderInfoService.add(orderInfo);
		
		//移除session里面被选中的商品
		List<OrderItem> orderItems=(List<OrderItem>) request.getSession().getAttribute("orderItems");
		for(int i=0;i<list.size();i++) {
			for(int j=0;j<orderItems.size();j++) {
				if(list.get(i).getId()==orderItems.get(j).getId()) {
					orderItems.remove(j);
					break;
				}
			}
		}
		//返回刚刚选择的商品,放在弹出的付款页面中
		String jsString=JSONObject.toJSONString(list);
		response.getWriter().write(jsString);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}
