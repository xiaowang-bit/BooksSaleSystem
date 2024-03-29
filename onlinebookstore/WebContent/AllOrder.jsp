<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../css/my-index.css">
    <title>My Order</title>
    <!-- 接受json数据 -->
    <!-- <script>
    $(document).ready(function () {   
       //当点击查询按钮的时候执行 
       $("#unpay-tab").bind("click",function(){
         //先销毁表格 
      $('#status0').bootstrapTable('destroy'); 
      //初始化表格,动态从服务器加载数据 
      $("#status0").bootstrapTable({ 
       method: "get", //使用get请求到服务器获取数据 
       url: "", //要请求数据的文件路径
       sidePagination: "server", //表示服务端请求
       onLoadSuccess: function(){ //加载成功时执行 
            layer.msg("加载成功"); 
           }, 
           onLoadError: function(){ //加载失败时执行 
            layer.msg("加载数据失败"); 
           } 
       });
        
        $("#pay-tab").bind("click", (function(){
          $('#status0').bootstrapTable('destroy'); 
          //初始化表格,动态从服务器加载数据 
          $("#cusTable").bootstrapTable({ 
           method: "get", //使用get请求到服务器获取数据 
           url: "", //要请求数据的文件路径
           sidePagination: "server", //表示服务端请求 
           onLoadSuccess: function(){ //加载成功时执行 
            layer.msg("加载成功"); 
           }, 
           onLoadError: function(){ //加载失败时执行 
            layer.msg("加载数据失败"); 
           } 
           }); 
        }); 

        $("#complete-tab").bind("click",function(){
          $('#status0').bootstrapTable('destroy'); 
          //初始化表格,动态从服务器加载数据 
          $("#cusTable").bootstrapTable({ 
           method: "get", //使用get请求到服务器获取数据 
           url: "", //要请求数据的文件路径 
           sidePagination: "server", //表示服务端请求 
           onLoadSuccess: function(){ //加载成功时执行 
            layer.msg("加载成功"); 
           }, 
           onLoadError: function(){ //加载失败时执行 
            layer.msg("加载数据失败"); 
           } 
           }); 
        }); 
      }); 
    </script> -->
</head>
<body>


	 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>



<div class="container-fluid my-order-page">
		<div class ="col-12" style="background:#000; color:#FFF;height: 40px;">
		<span style="font-size:20px;text-align:center;display:block;position: relative;top:50%;transform:translateY(-50%);">我 的 订 单</span></div>
    <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
      <li class="nav-item">
        <a class="nav-link"  href="OrderStatus0Servlet" role="tab" aria-controls="home" aria-selected="true">待付款订单</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"  href="OrderStatus1Servlet" role="tab" aria-controls="profile" aria-selected="false">已付款订单</a>
      </li>
      <li class="nav-item">
        <a class="nav-link"   href="OrderStatus2Servlet" role="tab" aria-controls="contact" aria-selected="false">已收货订单</a>
      </li>
    </ul>
<!-- 未付款表格1 -->
  <div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="unpay" role="tabpanel" aria-labelledby="unpay-tab">
  	<div class="table-responsive">
  	<table class="table table-hover table-bordered" id="status0"><!-- table-striped  -->
      <thead>
        <tr>
      <th scope="col"></th>
          <th scope="col">订单编号</th>
          <th scope="col">商品数量</th>
          <th scope="col">小计</th>
          <th scope="col">订单操作</th>
        </tr>
      </thead>
<!-- 假数据 -->
        <tbody>
		<c:forEach var = "item" items="${requestScope.will_pay }">
          <tr>
            <th scope="row">1</th>
            <td>${item.id }</td>
            <td>${item.num }</td>
            <td>${item.price }</td>
            <td><a href="OrderToPayServlet?id=${item.id}">提交</a></td>
          </tr>
          </c:forEach>
        </tbody>
    </table>
<!-- 表格1分页 -->
      <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li class="page-item"><a class="page-link" href="#">1</a></li>
          <li class="page-item"><a class="page-link" href="#">2</a></li>
          <li class="page-item"><a class="page-link" href="#">3</a></li>
          <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>

  </div>
</div>


</div>

</body>
</html>
