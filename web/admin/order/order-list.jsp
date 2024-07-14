<%-- 
    Document   : order-list
    Created on : Jul 2, 2024, 7:01:09 PM
    Author     : ASUS
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="models.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Order> orders = (ArrayList<Order>) request.getAttribute("orders");
%>
<!doctype html> 
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Game Account Shop - Admin Page</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">

        <!-- jquery.vectormap css -->
        <link href="assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />

        <!-- DataTables -->
        <link href="assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />

        <!-- Responsive datatable examples -->
        <link href="assets/libs/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />  

        <!-- Bootstrap Css -->
        <link href="assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

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
                                    <h4 class="mb-sm-0">Order</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Order</a></li>
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
                                        <h4 class="card-title mb-4">Latest Order</h4>
                                        <div class="table-responsive">
                                            <table class="table table-centered mb-0 align-middle table-hover table-nowrap">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th>Username</th>
                                                        <th>Total price</th>
                                                        <th>Order date</th>
                                                        <th>Status</th>
                                                        <th>Details</th>
                                                    </tr>
                                                </thead><!-- end thead -->
                                                <tbody>
                                                    <% 
                                                        for(Order item: orders){
                                                    %>
                                                        <tr>
                                                            <td><h6 class="mb-0"><%= item.getUsername() %></h6></td>
                                                            <td><%= item.getTotal_amount() %> VND</td>
                                                            <td><%= item.getOrderDate().toString() %></td>
                                                            <td>
                                                                <% 
                                                                    if(item.getStatus().equals("approved")){
                                                                %> 
                                                                    <span class="badge rounded-pill bg-success">Approved</span>
                                                                <%
                                                                    }
                                                                %>
                                                                <% 
                                                                    if(item.getStatus().equals("processing")){
                                                                %>
                                                                    <span class="badge rounded-pill bg-warning">Processing</span>
                                                                <%}%>
                                                                <% 
                                                                    if(item.getStatus().equals("cancelled")){
                                                                %>
                                                                    <span class="badge rounded-pill bg-danger">Cancelled</span>
                                                                <%}%>
                                                            </td>
                                                            <td>
                                                                <a href="/admin/order?action=detail&id=<%= item.getId() %>" class="btn btn-primary"><i class="far fa-eye"></i></a>
                                                            </td>
                                                        </tr>
                                                    <%
                                                        }
                                                    %>
                                                       
                                                    
                                                    <!-- end -->
                                                </tbody><!-- end tbody -->
                                            </table> <!-- end table -->
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

        </div>
        <!-- END layout-wrapper -->

        <!-- JAVASCRIPT -->
        <script src="assets/libs/jquery/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="assets/libs/simplebar/simplebar.min.js"></script>
        <script src="assets/libs/node-waves/waves.min.js"></script>

        
        <!-- apexcharts -->
        <script src="assets/libs/apexcharts/apexcharts.min.js"></script>

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