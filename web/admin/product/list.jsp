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
                                        <a href="/admin/product?action=create">Add</a>
                                        <div class="table-responsive">
                                            <table class="table table-hover mb-0">
        
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Title</th>
                                                        <th>Quantity</th>
                                                        <th>Type</th>
                                                        <th>Price (VND)</th>
                                                        <th>Image</th>
                                                        <th>Status</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:if test="${empty list}">
                                                        <div>There is no account !!!</div>
                                                    </c:if> 
                                                    <c:forEach var="product" items="${list}">
                                                        <tr>
                                                            <th scope="row">${list.indexOf(product) + 1}</th>
                                                            <td>${product.title}</td>
                                                            <td>${product.quantity}</td>
                                                            <td>${product.category.name}</td>
                                                            <td>${product.price}</td>
                                                            <td><img style="max-width: 100px; max-height: 100px;" src="${product.img}"/></td>
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

                                             <c:if test="${noOfPages > 1}">
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

        <!-- JAVASCRIPT -->
        <script src="/admin/assets/libs/jquery/jquery.min.js"></script>
        <script src="/admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/admin/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/admin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/admin/assets/libs/node-waves/waves.min.js"></script>

        <script src="/admin/assets/js/app.js"></script>

    </body>
</html>

