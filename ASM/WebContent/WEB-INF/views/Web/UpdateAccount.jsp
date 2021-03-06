<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>

<head>
<base href="${pageContext.servletContext.contextPath}/">
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Phần Mêm Quản Lí Nhân Viên</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/png" href="assets/images/icon/favicon.ico">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/themify-icons.css">
    <link rel="stylesheet" href="assets/css/metisMenu.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/slicknav.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- amchart css -->
    <link rel="stylesheet" href="https://www.amcharts.com/lib/3/plugins/export/export.css" type="text/css" media="all" />
    <!-- others css -->
    <link rel="stylesheet" href="assets/css/typography.css">
    <link rel="stylesheet" href="assets/css/default-css.css">
    <link rel="stylesheet" href="assets/css/styles.css">
    <link rel="stylesheet" href="assets/css/responsive.css">
    <link rel="stylesheet" href="index.css">
    <!-- modernizr css -->
    <script src="assets/js/vendor/modernizr-2.8.3.min.js"></script>
          <style type="text/css">
        .button {
    padding: 12px 20px;
    font-size: 20px;
    text-align: center;
    cursor: pointer;
    outline: none;
    color: #fff;
    background-color: #4CAF50;
    border: none;
    border-radius: 15px;
    box-shadow: 0 5px #999;
}

.button:hover {
    background-color: blue;
}

.button:active {
    background-color: blue;
    box-shadow: 0 5px #666;
    transform: translateY(4px);
}</style>
</head>

<body>
    <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
    <!-- preloader area start -->
    <div id="preloader">
        <div class="loader"></div>
    </div>
    <!-- preloader area end -->
    <!-- page container area start -->
    <div class="page-container">
        <!-- sidebar menu area start -->
        <div class="sidebar-menu">
            <div class="sidebar-header">
                <div class="logo">
                    <a href="Web/index.htm"><img src="assets/images/icon/Master group.png" alt="logo"></a>
                </div>
            </div>
            <div class="main-menu">
                <div class="menu-inner">
                    <nav>
                        <ul class="metismenu" id="menu">
                            <li class="active">
                                <a href="javascript:void(0)" aria-expanded="true"><i class="ti-dashboard"></i><span>Quản Lí Tài Khoản</span></a>
                                <ul class="collapse">
                                    <li class="active"><a href="Web/TaiKhoan.htm">Thêm Tài Khoản</a></li>
                                    <li><a href="Web/ListAccount.htm">Danh Sách Tài Khoản</a></li>

                                </ul>
                            </li>
                                 <li>

                                <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-users"></i>
                                    <span>Quản Lí Nhân Viên</span></a>
                                <ul class="collapse">
                                    <li><a href="Staff/AddNhanVien.htm">Thêm Nhân Viên</a></li>
                                    <li><a href="Staff/ListStaff.htm">Danh Sách Nhân Viên</a></li>

                                </ul>
                            </li>
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-address-book"></i> <span>Quản Lí Phòng Ban</span></a>
                                <ul class="collapse">
                                    <li><a href="Departs/AddDeparts.htm">Thêm Phòng Ban</a></li>
                                    <li><a href="Departs/ListDeparts.htm">Danh Sách Phòng Ban</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-institution"></i> <span>Quản Lí Thành Tích</span></a>
                                <ul class="collapse">
                                    <li><a href="Record/AddRecord.htm">Thêm Thành Tích & Kỉ Luật</a></li>
                                    <li><a href="Record/ListRecord.htm">Danh Sách Thành Tích & Kỉ Luật</a></li>

                                </ul>

                            </li>
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-graduation-cap"></i> <span>Quản Lí Thống Kê</span></a>
                                <ul class="collapse">
                                    <li><a href="Web/ListTotal1.htm">Thành Tích Cá Nhân </a></li>
                                    <li><a href="Web/ListTotal2.htm">Thành Tích Phòng Ban</a></li>
                                </ul>

                            </li>
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="ti-layers-alt"></i> <span>Liên Kết</span></a>
                                <ul class="collapse">
                                    <li><a href="Web/login.htm">Login</a></li>
                                    <li><a href="Web/register.htm">Register</a></li>
                                    <li><a href="Web/reset-pass.htm">reset password</a></li>

                                </ul>
                            </li>
                            	<li><a href="javascript:void(0)" aria-expanded="true"><i
									class="fa fa-graduation-cap"></i> <span>Ngôn Ngữ</span></a>
								<ul class="collapse">
									<div id="translate_select"></div>

								</ul></li>
                            <li>
                                <a href="javascript:void(0)" aria-expanded="true"><i class="fa fa-exclamation-triangle"></i>
                                    <span>Error</span></a>
                                <ul class="collapse">
                                    <li><a href="Web/404.htm">Error 404</a></li>

                                </ul>
                            </li>

                        </ul>
                    </nav>
                </div>
            </div>
        </div>
        <!-- sidebar menu area end -->
        <!-- main content area start -->
        <div class="main-content">
            <!-- header area start -->
            <div class="header-area">
                <div class="row align-items-center">
                    <!-- nav and search button -->
                    <div class="col-md-6 col-sm-8 clearfix">
                        <div class="nav-btn pull-left">
                            <span></span>
                            <span></span>
                            <span></span>
                        </div>
                        <div class="search-box pull-left">
                            <form action="#">
                                <input type="text" name="search" placeholder="Search..." required>
                                <i class="ti-search"></i>
                            </form>
                        </div>
                    </div>


                </div>


                <div class="page-title-area">
                    <div class="row align-items-center">
                        <div class="col-sm-6">
                            <div class="breadcrumbs-area clearfix">
                                <h4 class="page-title pull-left">Cập Nhật Nhân Viên</h4>
                                <ul class="breadcrumbs pull-left">
                                    <li><a href="Web/index.htm">Trang Chủ</a></li>
                                    <li><span>Cập Nhật Nhân Viên</span></li>
                                </ul>
                            </div>
                        </div>

                    </div>
                    <br>
                    <br>
                </div>
                    ${message}
                         <form:form action="Web/Update.htm" modelAttribute="user">
		            
                    <label>Tài Khoản:</label> <form:input path="username" class="form-control" type="text" name="username"/><br>
                    <label>Mật Khẩu:</label> <form:input path="password" class="form-control" type="text" name="password"/><br>
                    <label>Họ và Tên:</label> <form:input path="fullname" class="form-control" type="text" name="fullname"/><br>
<!--                     <label>Email:</label> <input class="form-control" type="text" name="email"></input><br> -->
<!--                     <label>Điện Thoại:</label> <input class="form-control" type="text" name="phone"></input><br> -->
<!--                     <label>Chức Vụ:&nbsp;&nbsp;</label><input type="radio" name="role" value="1">&nbsp;&nbsp;Admin</input>&nbsp;&nbsp;&nbsp; -->
<!--                     <input type="radio" name="role" value="0">&nbsp;&nbsp;User</input><br><br> -->
                     <button class="button">Cập Nhật</button>
                </form:form>
               
                  
                    <!-- page title area end -->

                    <!-- latest news area end -->
                    <!-- exchange area start -->

                    <!-- exchange area end -->
            </div>
            <!-- row area start-->
        </div>
    </div>
    <!-- main content area end -->
    <!-- footer area start-->
    <footer>
        <div class="footer-area">
            <p>© Copyright 2020. All right reserved. Template by <a href="Web/index.htm">Master Group</a>.</p>
        </div>
    </footer>
    <!-- footer area end-->


    <!-- offset area end -->
    <!-- jquery latest version -->
    <script src="assets/js/vendor/jquery-2.2.4.min.js"></script>
    <!-- bootstrap 4 js -->
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/metisMenu.min.js"></script>
    <script src="assets/js/jquery.slimscroll.min.js"></script>
    <script src="assets/js/jquery.slicknav.min.js"></script>

    <!-- start chart js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
    <!-- start highcharts js -->
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <!-- start zingchart js -->
    <script src="https://cdn.zingchart.com/zingchart.min.js"></script>
    <script>
        zingchart.MODULESDIR = "https://cdn.zingchart.com/modules/";
        ZC.LICENSE = ["569d52cefae586f634c54f86dc99e6a9", "ee6b7db5b51705a13dc2339db3edaf6d"];
    </script>
    <!-- all line chart activation -->
    <script src="assets/js/line-chart.js"></script>
    <!-- all pie chart -->
    <script src="assets/js/pie-chart.js"></script>
    <!-- others plugins -->
    <script src="assets/js/plugins.js"></script>
    <script src="assets/js/scripts.js"></script>
    	<script type="text/javascript"
										src="//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit">
										
									</script>
									<script type="text/javascript">
										function googleTranslateElementInit() {
											new google.translate.TranslateElement(
													{
														pageLanguage : 'vi'
													}, 'translate_select');
										}
									</script>
</body>

</html>