<%-- 
    Document   : list.jsp
    Created on : 1 Jul 2024, 20:21:48
    Author     : quoch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!doctype html>
<html lang="en">

    <head>

        <meta charset="utf-8" />
        <title>Basic Tables | Upcube - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/favicon.ico">
         <!--Toast-->
        <link rel="stylesheet" type="text/css" href="/admin/assets/libs/toastr/build/toastr.min.css">
        <!-- Bootstrap Css -->
        <link href="/admin/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="/admin/assets/libs/magnific-popup/magnific-popup.css">
        
        <style>
            .full-page-message {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 50vh;
                text-align: center;
                font-size: 24px;
                color: #333;
            }
        </style>
        
    </head>

    <body data-topbar="dark">

        <!-- <body data-layout="horizontal" data-topbar="dark"> -->

        <!-- Begin page -->
        <div id="layout-wrapper">


            <%@include file="/admin/components/header.jsp" %>

            <!-- ========== Left Sidebar Start ========== -->
            <%@include file="/admin/components/menu.jsp" %>
            <!-- Left Sidebar End -->



            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="main-content">

                <div class="page-content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title">Product List</h4>
                                        <p class="card-title-desc">Product List</p>    

                                        <div class="row">
                                            <form class="row col-11" method="get">

                                                <input style="margin-right : 10px" class="col-2" type="text" placeholder="Title" name="titleSearch" value="${titleSearch}"/>

                                                <select style="margin-right : 10px" name="categorySearch" class="col-2">
                                                    <option value="">Category</option>
                                                    <c:if test="${not empty category}">
                                                        <option selected hidden value="${category.id}">${category.name}</option>
                                                    </c:if>
                                                    <!--<option>Open this select menu</option>-->
                                                    <c:forEach var="category" items="${requestScope.categories}">
                                                        <option value="${category.id}">${category.name}</option>
                                                    </c:forEach>
                                                </select>

                                                <input style="margin-right : 10px" class="col-2" type="number" step="0.1" min="0" max="90000000" placeholder="Start Price" name="priceStartSearch" value="${priceStartSearch}"/>

                                                <input class="col-2" type="number" step="0.1" min="0" max="900000000" placeholder="End Price" name="priceEndSearch" value="${priceEndSearch}"/>

                                                <button class="btn btn-outline-light col-2" type="submit"><div class="fas fa-search" ></div></button>
                                            </form>

                                            <a style="font-size: 25px" class="col-1 fas fa-plus-circle" href="/admin/product?action=create"></a>
                                        </div>

                                        <div class="table-responsive">
                                            <table class="table table-hover mb-0">

                                                <c:if test="${empty list}">
                                                    <div class="full-page-message">
                                                        No account available !!!
                                                    </div>
                                                </c:if> 

                                                <c:if test="${not empty list}">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>Title</th>
                                                            <!--<th>Quantity</th>-->
                                                            <th>Type</th>
                                                            <th>Price (VND)</th>
                                                            <th>Image</th>
                                                            <th>Status</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>

                                                        <c:forEach var="product" items="${list}">
                                                            <tr>
                                                                <th scope="row">${list.indexOf(product) + 1}</th>
                                                                <td>${product.title}</td>

                                                                <td>${product.category.name}</td>
                                                                <td>${product.price}</td>
                                                                <td>

                                                                    <a class="image-popup-no-margins" href="${product.img}">
                                                                        <img class="img-fluid rounded me-2" width="100" src="${product.img}"/>
                                                                    </a>
                                                                <td>
                                                                    <span class="badge bg-success">${product.status}</span>
                                                                </td>
                                                                <td>
                                                                    <a  href="/admin/product?action=edit&id=${product.id}" type="button" class="btn btn-sm btn-outline-info waves-effect waves-light fas fa-edit"></a>
                                                                    <!--<a href="/admin/product?action=delete" type="button" class="btn btn-sm btn-outline-danger waves-effect waves-light fas fa-trash-alt"></a>-->

                                                                    <button type="button" class="btn btn-sm btn-outline-danger waves-effect waves-light fas fa-trash-alt" data-bs-toggle="modal"
                                                                            data-bs-target="#delete${product.id}">

                                                                    </button>

                                                                    <form action="/admin/product?action=delete&id=${product.id}" method="post">
                                                                        <div class="modal" id="delete${product.id}" tabindex="-1">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <div class="modal-header">
                                                                                        <h5 style="color : red" class="modal-title">Delete Warning !!!</h5>
                                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                    </div>
                                                                                    <div class="modal-body">
                                                                                        <p>You want to delete product having title " ${product.title} " ?</p>
                                                                                    </div>
                                                                                    <div class="modal-footer">
                                                                                        <input hidden value="${currentPage}" name="currentPage"/>
                                                                                        <button type="submit" class="btn btn-danger">Delete</button>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </form>

                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </c:if> 
                                                </tbody>
                                            </table>



                                            <c:if test="${noOfPages > 1}">
                                                <div class="pagination pagination-rounded justify-content-end">
                                                    <c:if test="${currentPage !=  1}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/product?page=${currentPage - 1}&titleSearch=${titleSearch}&categorySearch=${categorySearch}&priceStartSearch=${priceStartSearch}&priceEndSearch=${priceEndSearch}">Previous</a>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach var="i" begin="1" end="${noOfPages}">     
                                                        <c:choose>
                                                            <c:when test="${i == currentPage}">
                                                                <li class="page-item active">
                                                                    <span class="page-link">${i}</span>
                                                                </li>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <li class="page-item">
                                                                    <a class="page-link" href="/admin/product?page=${i}&titleSearch=${titleSearch}&categorySearch=${categorySearch}&priceStartSearch=${priceStartSearch}&priceEndSearch=${priceEndSearch}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>         
                                                    </c:forEach>
                                                    <c:if test="${currentPage < noOfPages}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/product?page=${currentPage + 1}&titleSearch=${titleSearch}&categorySearch=${categorySearch}&priceStartSearch=${priceStartSearch}&priceEndSearch=${priceEndSearch}">Next</a>
                                                        </li>
                                                    </c:if>
                                                </div>

                                            </c:if>

                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->
                    </div> <!-- container-fluid -->
                </div>
                <!-- End Page-content -->

            </div>
            <!-- end main content-->
        </div>
        <!-- END layout-wrapper -->
        <div class="rightbar-overlay"></div>
        <!-- JAVASCRIPT -->
        <script src="/admin/assets/libs/jquery/jquery.min.js"></script>
        <script src="/admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/admin/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/admin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/admin/assets/libs/node-waves/waves.min.js"></script>

        <script src="/admin/assets/js/app.js"></script>
        <script src="/admin/assets/js/menu.js"></script>

        <!-- Magnific Popup-->
        <script src="/admin/assets/libs/magnific-popup/jquery.magnific-popup.min.js"></script>

        <!-- lightbox init js-->
        <script src="/admin/assets/js/pages/lightbox.init.js"></script>
        
        <!-- toastr plugin -->
        <script src="/admin/assets/libs/toastr/build/toastr.min.js"></script>

        <!-- toastr init -->
        <script src="/admin/assets/js/pages/toastr.init.js"></script>
        
        
        <c:if test="${sessionScope.showToast == true}">
        <script>
            $(document).ready(function() {
                toastr.options = {
                    "closeButton": false,
                    "debug": false,
                    "newestOnTop": true,
                    "progressBar": false,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": false,
                    "onclick": null,
                    "showDuration": 300,
                    "hideDuration": 1000,
                    "timeOut": 2000,
                    "extendedTimeOut": 1000,
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                  }
                toastr.success('Successfull !!!', 'Success');
            });
        </script>
        <%
            session.removeAttribute("showToast");
        %>
        </c:if>
        
        <c:if test="${sessionScope.showToast == false}">
        <script>
            $(document).ready(function() {
                toastr.options = {
                    "closeButton": false,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": false,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": false,
                    "onclick": null,
                    "showDuration": 300,
                    "hideDuration": 1000,
                    "timeOut": 2000,
                    "extendedTimeOut": 1000,
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                  }
                toastr.error('Error happpen !!!', 'Success');
            });
        </script>
        <%
            session.removeAttribute("showToast");
        %>
        </c:if>
        
        
    </body>
</html>
