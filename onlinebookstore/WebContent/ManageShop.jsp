<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html class="no-js" lang="zxx">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="meta description">
    <title></title>

    <!--=== Favicon ===-->
    <link rel="shortcut icon" href="assets/img/favicon.ico" type="image/x-icon" />

    <!-- Google fonts include -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,900%7CYesteryear" rel="stylesheet">

    <!-- All Vendor & plugins CSS include -->
    <link href="assets/css/vendor.css" rel="stylesheet">
    <!-- Main Style CSS -->
    <link href="assets/css/style.css" rel="stylesheet">

    <!--[if lt IE 9]>
		<script src="/oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
		<script src="/oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

</head>

<body>

        <!-- main header start -->
        <div class="main-header d-none d-lg-block">
            <!-- header top start -->
            <div class="header-top bdr-bottom">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-6">
                            <div class="welcome-message">
                                <p>欢迎来回来</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- header top end -->

            <!-- header middle area start -->
            <div class="header-main-area sticky">
                <div class="container">
                    <div class="row align-items-center position-relative">


                        <!-- main menu area start -->
                        <div class="col-lg-6 position-static">
                            <div class="main-menu-area">
                                <div class="main-menu">
                                    <!-- main menu navbar start -->
                                    <nav class="desktop-menu">
                                        <ul>
                                            <li><a href="shop.html" style="font-size: 38px;align-content: center;" >网 上 书 店</a></li>
                                        </ul>
                                    </nav>
                                    <!-- main menu navbar end -->
                                </div>
                            </div>
                        </div>
                        <!-- main menu area end -->

                      
                    </div>
                </div>
            </div>
            <!-- header middle area end -->
        </div>
        <!-- main header start -->
>



    <!-- main wrapper start -->
    <main>
        <!-- breadcrumb area start -->
        <div class="breadcrumb-area common-bg">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <div class="breadcrumb-wrap">
                           	<p>这是一个由湖北大学计算机与信息工程学院计算机科学与技术和软件工程专业同学，所开发的一个网上书店javaweb项目</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- breadcrumb area end -->

        <!-- page main wrapper start -->
        <div class="shop-main-wrapper section-space pb-0">
            <div class="container">
                <div class="row">
                    <!-- shop main wrapper start -->
                    <div class="col-lg-12 order-1 order-lg-2">
                        <div class="shop-product-wrapper">
                            <!-- shop product top wrap start -->
                            <div class="shop-top-bar">
                                <div class="row align-items-center">
                                    
                                    <div class="product-short">
                                             <form method="post" action="SearchCategoryListServlet">
											<input type="text"name="category_name" style="color: ;border-radius: 10px;">
                                         	<input type="submit"value="分类查询" style="color: ;border-radius: 10px;">
                                         	</form>                                            
                                    </div>
                                         
                                    <div class="product-short">
                                         <form method="post" action="SearchBookListServlet">
                                         	 <input type="text"name="book_name" style="color: ;border-radius: 10px;">
                                         	 <input type="submit"value="图书名" style="color: ;border-radius: 10px;">
                                         </form>
                                    </div>
                                    <div class="product-short">
                                             <button type="button" value="添加图书" style="color: ;border-radius: 10px;" onclick="addProduct()">添加图书</button>                                        
                                     </div>
                                     <div class="product-short">
                                             <button type="button" value="图书分类" style="color: ;border-radius: 10px;" onclick="addCategory()">图书分类</button>                                        
                                     </div>
                                </div>
                            </div>
                            <!-- shop product top wrap start -->
							
                            <!-- product item list wrapper start -->
                            <div class="shop-product-wrap grid-view row mbn-40">
                            	<c:forEach var = "item" items="${sessionScope.books }">
                                <!-- product single item start -->
                                <div class="col-md-3 col-sm-6">
                                    <!-- product grid start -->
                                    <div class="product-item">
                                        <figure class="product-thumb">
                                            <a href="product-details.html">
                                                <img class="pri-img" src=".${item.imageName}" alt="product">
                                                <img class="sec-img" src=".${item.imageName}" alt="product">
                                            </a>
                                        </figure>
                                        <div class="product-caption">
                                            <p class="product-name">
                                                <a href="product-details.html">${item.bookname}</a>
                                            </p>
                                            <div class="price-box">
                                                <span class="price-regular">${item.price}</span>
                                            </div>
                                        </div>
                                    </div>
                                 </div>
                                    <!-- product grid end -->
                                 </c:forEach>
                            </div>
                            <!-- product item list wrapper end -->

                            <!-- start pagination area -->
                            <div class="paginatoin-area text-center">
                                <ul class="pagination-box">
                                    <li><a class="previous" href="#"><i class="lnr lnr-chevron-left"></i></a></li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a class="next" href="#"><i class="lnr lnr-chevron-right"></i></a></li>
                                </ul>
                            </div>
                            <!-- end pagination area -->
                        </div>
                    </div>
                    <!-- shop main wrapper end -->
                </div>
            </div>
        </div>
        <!-- page main wrapper end -->
    </main>
    <!-- main wrapper end -->

    <!-- Start Footer Area Wrapper -->

		<br/><br/>
        <!-- footer bottom area start -->
        <div class="footer-bottom-area">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-md-6 order-2 order-md-1">
                        <div class="copyright-text" align="right">
                            <p>计科2班与软工3班@2019.12.19</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- footer bottom area end -->

    <!-- End Footer Area Wrapper -->


    <!-- offcanvas search form start -->
    <div class="offcanvas-search-wrapper">
        <div class="offcanvas-search-inner">
            <div class="offcanvas-close">
                <i class="lnr lnr-cross"></i>
            </div>
            <div class="container">
                <div class="offcanvas-search-box">
                    <form class="d-flex bdr-bottom w-100">
                        <input type="text" placeholder="Search entire storage here...">
                        <button class="search-btn"><i class="lnr lnr-magnifier"></i>search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- offcanvas search form end -->

    <!-- offcanvas mini cart start -->
    <div class="offcanvas-minicart-wrapper">
        <div class="minicart-inner">
            <div class="offcanvas-overlay"></div>
            <div class="minicart-inner-content">
                <div class="minicart-close">
                    <i class="lnr lnr-cross"></i>
                </div>
                <div class="minicart-content-box">
                    <div class="minicart-item-wrapper">
                        <ul>
                            <li class="minicart-item">
                                <div class="minicart-thumb">
                                    <a href="product-details.html">
                                        <img src="assets/img/cart/cart-1.jpg" alt="product">
                                    </a>
                                </div>
                                <div class="minicart-content">
                                    <h3 class="product-name">
                                        <a href="product-details.html">Flowers bouquet pink for all flower lovers</a>
                                    </h3>
                                    <p>
                                        <span class="cart-quantity">1 <strong>&times;</strong></span>
                                        <span class="cart-price">$100.00</span>
                                    </p>
                                </div>
                                <button class="minicart-remove"><i class="lnr lnr-cross"></i></button>
                            </li>
                            <li class="minicart-item">
                                <div class="minicart-thumb">
                                    <a href="product-details.html">
                                        <img src="assets/img/cart/cart-2.jpg" alt="product">
                                    </a>
                                </div>
                                <div class="minicart-content">
                                    <h3 class="product-name">
                                        <a href="product-details.html">Jasmine flowers white for all flower lovers</a>
                                    </h3>
                                    <p>
                                        <span class="cart-quantity">1 <strong>&times;</strong></span>
                                        <span class="cart-price">$80.00</span>
                                    </p>
                                </div>
                                <button class="minicart-remove"><i class="lnr lnr-cross"></i></button>
                            </li>
                        </ul>
                    </div>

                    <div class="minicart-pricing-box">
                        <ul>
                            <li>
                                <span>sub-total</span>
                                <span><strong>$300.00</strong></span>
                            </li>
                            <li>
                                <span>Eco Tax (-2.00)</span>
                                <span><strong>$10.00</strong></span>
                            </li>
                            <li>
                                <span>VAT (20%)</span>
                                <span><strong>$60.00</strong></span>
                            </li>
                            <li class="total">
                                <span>total</span>
                                <span><strong>$370.00</strong></span>
                            </li>
                        </ul>
                    </div>

                    <div class="minicart-button">
                        <a href="#"><i class="fa fa-shopping-cart"></i> view cart</a>
                        <a href="#"><i class="fa fa-share"></i> checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- offcanvas mini cart end -->

    <!-- Scroll to top start -->
    <div class="scroll-top not-visible">
        <i class="fa fa-angle-up"></i>
    </div>
    <!-- Scroll to Top End -->

    <!-- All vendor & plugins & active js include here -->
    <!--All Vendor Js -->
    <script src="assets/js/vendor.js"></script>
    <!-- Active Js -->
    <script src="assets/js/active.js"></script>
    <form action="AddProductServlet" method="post" class="form-horizontal" enctype="multipart/form-data">
	        <div class="modal" id="model_addProduct">
	    	    <div class="modal-dialog">
	    		    <div class="modal-content">
	    			    <div class="modal-header">
	    				    <h1>添加图书</h1>
	    			    </div>
		    			<select name="categoryname">
	    			    	<c:forEach var = "item" items="${sessionScope.categorys }">
		    			    	<option value="${item.id}">${item.name}</option>
	    			    	</c:forEach>
		    			</select>
	    			    <div class="modal-body">
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">书&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</label>
	    					    <div class="col-sm-6">
	    						    <input type="text" class="form-control"
	    							    id="bookname" name="bookname"  />
	    					    </div>
	    				    </div>
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者:</label>
	    					    <div class="col-sm-6">
	    						    <input type="text" class="form-control"
	    							    id="author"  name="author"/>
	    					    </div>
	    				    </div>
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">价&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格:</label>
	    					    <div class="col-sm-6">
	    					    	<input type="text" class="form-control"
	    							    id="price"  name="price"/>
	    					    </div>
	    				    </div>
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;择&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片:</label>
	    					    <div class="form-group">
		    					    <input type="file" name="File" >
	    					    </div>
	    				    </div>
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</label>
	    					    <div class="col-sm-6">
	    						    <textarea class="form-control"
	    							    id="description" name="description" rows="10" ></textarea>
	    					    </div>
	    				    </div>
                        </div>
	    			    <div class="modal-footer">
	    				    <input type="submit" class="btn btn-sm btn-default"value="添加">
	    				    <input type="reset" class="btn btn-sm btn-default"
	    					    data-dismiss="modal" value="取消">
	    			    </div> 
	    		    </div>
	    	    </div>
	        </div>
	    </form>
	    
	    <form action="AddCategoryServlet" method="post" class="form-horizontal">
	        <div class="modal" id="model_addCategory">
	    	    <div class="modal-dialog">
	    		    <div class="modal-content">
	    			    <div class="modal-header">
	    				    <h3>添加分类</h3>
	    			    </div>
	    			    <div class="modal-body">
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;字:</label>
	    					    <div class="col-sm-6">
	    						    <input type="text" class="form-control"
	    							    id="category_name" name="category_name"  />
	    					    </div>
	    				    </div>
	    				    <div class="form-group">
	    					    <label class="control-label col-sm-4">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述:</label>
	    					    <div class="col-sm-6">
	    						    <textarea  class="form-control" id="category_description" rows="10" name="category_description">
	    							 </textarea>
	    					    </div>
	    				    </div>
                        </div>
	    			    <div class="modal-footer">
	    				    <input type="submit" value="添加">
	    				    <input type="reset"  data-dismiss="modal" value="取消">
	    			    </div> 
	    		    </div>
	    	    </div>
	        </div>
	    </form>
	    
</body>
<script type="text/javascript">
		function addProduct()
		{
			jQuery("#model_addProduct").modal();
		}
		function addCategory()
		{
			jQuery("#model_addCategory").modal();
		}
</script>
</html>
