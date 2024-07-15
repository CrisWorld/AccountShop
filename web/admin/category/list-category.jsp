<%-- 
    Document   : order-list
    Created on : Jul 2, 2024, 7:01:09 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!doctype html> 
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Admin Page - Game Account Shop</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/icon.jpg">

        <!-- jquery.vectormap css -->
        <link href="/admin/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />

        <!-- DataTables -->
        <link href="/admin/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />

        <!-- Responsive datatable examples -->
        <link href="/admin/assets/libs/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />  

        <!-- Bootstrap Css -->
        <link href="/admin/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

    </head>

    <body data-topbar="dark">
    
    <!-- <body data-layout="horizontal" data-topbar="dark"> -->

        <!-- Begin page -->
        <div id="layout-wrapper">
            <%--<%@include file="/admin/components/header.jsp" %>--%>

            <!-- ========== Left Sidebar Start ========== -->
            <%--<%@include file="/admin/components/menu.jsp" %>--%>

            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="main-content">

                <div class="page-content">
                    <div class="container-fluid">
                        
                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                                    <h4 class="mb-sm-0">Categories</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Categories</a></li>
                                            <li class="breadcrumb-item active">List</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                        </div>
    
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">All categories</h4>
                                        <div class="d-flex justify-content-end">
                                            <a style="font-size: 25px" class="col-1 fas fa-plus-circle" href="/admin/category?action=create"></a>
                                        </div>
                                        <c:if test="${empty listCate}">
                                            <tr>
                                                <td colspan="5">There are no categories !!!</td>
                                            </tr>
                                        </c:if>
                                        
                                        <div class="table-responsive">
                                            <table class="table table-hover mb-0">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Name</th>
                                                        <th>Banner</th>
                                                        <th>Description</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:if test="${empty listCate}">
                                                    <tr>
                                                        <td colspan="5">There are no categories !!!</td>
                                                    </tr>
                                                </c:if>
                                                <c:forEach var="category" items="${listCate}">
                                                    <tr>
                                                        <th scope="row">${listCate.indexOf(category) + 1}</th>
                                                        <td>${category.name}</td>
                                                        <td><img style="max-width: 100px; max-height: 100px;" src="${category.banner}"/></td>
                                                        <td>${category.description}</td>
                                                        <td>
                                                            <a href="/admin/category?action=edit&id=${category.id}" type="button" class="btn btn-sm btn-outline-info waves-effect waves-light fas fa-edit"></a>
                                                            <button type="button" class="btn btn-sm btn-outline-danger waves-effect waves-light fas fa-trash-alt" data-bs-toggle="modal" data-bs-target="#delete${category.id}">
                                                            </button>
                                                            
                                                            <form action="/admin/category?action=delete&id=${category.id}" method="post">
                                                                <div class="modal" id="delete${category.id}" tabindex="-1">
                                                                    <div class="modal-dialog">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 style="color: red" class="modal-title">Delete Warning !!!</h5>
                                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <p>Do you want to delete the category with the name "${category.name}"?</p>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="submit" class="btn btn-danger">Delete</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                                            
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                            
                                            </table>

<!--                                             <c:if test="${noOfPages > 1}">
                                                <div class="pagination pagination-rounded justify-content-end">
                                                    <c:if test="${currentPage !=  1}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/product?page=${currentPage - 1}">Previous</a>
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
                                                                    <a class="page-link" href="/admin/product?page=${i}">${i}</a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>         
                                                    </c:forEach>
                                                    <c:if test="${currentPage < noOfPages}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="/admin/product?page=${currentPage + 1}">Next</a>
                                                        </li>
                                                    </c:if>
                                                </div>
                                                
                                            </c:if>-->
                                            
                                        </div>
                                    </div><!-- end card -->
                                </div><!-- end card -->
                            </div>
                            <!-- end col -->
                    </div>
                </div>
                <!-- End Page-content -->
            </div>
            <!-- end main content-->

        <!--</div>-->
        <!-- END layout-wrapper -->

        <!-- Right bar overlay-->
        <div class="rightbar-overlay"></div>

        <!-- JAVASCRIPT -->
        <script src="assets/libs/jquery/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="assets/libs/simplebar/simplebar.min.js"></script>
        <script src="assets/libs/node-waves/waves.min.js"></script>

        <!-- jquery.vectormap map -->
        <script src="assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="assets/libs/admin-resources/jquery.vectormap/maps/jquery-jvectormap-us-merc-en.js"></script>

        <!-- Required datatable js -->
        <script src="assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
        
        <!-- Responsive examples -->
        <script src="assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>

        <script src="assets/js/pages/dashboard.init.js"></script>

        <!-- App js -->
        <script src="assets/js/app.js"></script>
        <script src="/admin/assets/js/menu.js"></script>
    </body>

</html>