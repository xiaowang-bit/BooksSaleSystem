<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

    <!-- Start Header Area -->
    <header class="header-area">
        <!-- main header start -->
        <div class="main-header d-none d-lg-block">
            <!-- header top start -->
            <div class="header-top bdr-bottom">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-lg-6">
                            <div class="welcome-message">
                                <p>欢迎来到网上书店</p>
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
                                            <li><a href="shop.html" style="font-size: 38px;">网 上 书 店</a></li>
                                        </ul>
                                    </nav>
                                    <!-- main menu navbar end -->
                                </div>
                            </div>
                        </div>
                        <!-- main menu area end -->

                        <!-- mini cart area start -->
                        <div class="col-lg-3">
                            <div class="header-configure-wrapper">
                                <div class="header-configure-area">
                                    <ul class="nav justify-content-end">
                                        <li class="user-hover">
                                            <a href="#">
                                                <i class="lnr lnr-user"></i>
                                            </a>
                                            <ul class="dropdown-list">
                                                <li><a href="#">登录</a></li>
                                                <li><a href="#">账户信息</a></li>
                                                <li><a href="#">注册</a></li>
                                                <li><a href="#">注销</a></li>
                                            </ul>
                                        </li>

                                        <li>
                                            <a href="index.html" class="minicart-btn">
                                                <i class="lnr lnr-cart"></i>
                                                <div class="notification">2</div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="AllOrder.html" class="minicart-btn">
                                                <i class="lnr lnr-cart"></i>
                                                <div class="notification">2</div>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- mini cart area end -->

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
                                    
                                    <div class="col-lg-7 col-md-6 order-2 order-md-1">
                                        <div class="top-bar-left">
                                            <div class="product-short">
                                                <form method="post" action="SearchCategoryListServlet">
												<input type="text"name="category_name" style="color: ;border-radius: 10px;">
                                            	<input type="submit"value="分类查询" style="color: ;border-radius: 10px;">
                                            	</form>                                            
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 col-md-6 order-1 order-md-2">
                                        <div class="top-bar-right">
                                            <div class="product-short">
                                            <form method="post" action="SearchBookListServlet">
                                            	 <input type="text"name="book_name" style="color: ;border-radius: 10px;">
                                            	 <input type="submit"value="图书名" style="color: ;border-radius: 10px;">
                                            </form>
                                                
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- shop product top wrap start -->

								<!-- product item list wrapper start -->
                            <div class="shop-product-wrap grid-view row mbn-40">
                                <!-- product single item start -->
                               <c:forEach var = "item" items="${sessionScope.books }">
                                <div class="col-md-3 col-sm-6">
                                    <!-- product grid start -->
                                    <div class="product-item">
                                        <figure class="product-thumb">
                                            <a href="product-details.html">
                                                <img class="pri-img" src=".${item.imageName}" alt="product">
                                                <img class="sec-img" src=".${item.imageName}" alt="product">
                                            </a>
                                            <div class="button-group">
                                                <a href="#" data-toggle="tooltip" data-placement="left" title="Add to Cart"><i class="lnr lnr-cart"></i></a>
                                            </div>
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
</body>

</html>
