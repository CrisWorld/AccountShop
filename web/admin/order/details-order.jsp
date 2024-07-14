<%@page import="models.Product"%>
<%@page import="models.Order"%>
<% 
    Order order = (Order) request.getAttribute("order");
%>
<!doctype html>
<html lang="en">

    <head>
        
        <meta charset="utf-8" />
        <title>Admin Page
        
        </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/favicon.ico">

        <!-- jquery.vectormap css -->
        <link href="/admin/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />

        <!-- DataTables -->
        <link href="/admin/assets/libs/datatables.net-bs4/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />

        <!-- Responsive datatable examples -->
        <link href="/admin/assets/libs/datatables.net-responsive-bs4/css/responsive.bootstrap4.min.css" rel="stylesheet" type="text/css" />  

        <!-- Bootstrap Css -->
        <link href="/admin/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" rel="stylesheet" type="text/css" />

    </head>

    <body data-topbar="dark">
    
    <!-- <body data-layout="horizontal" data-topbar="dark"> -->

        <!-- Begin page -->
        <div id="layout-wrapper">
            <%@include file="/admin/components/header.jsp" %>

            <!-- ========== Left Sidebar Start ========== -->
            <%@include file="/admin/components/menu.jsp" %>
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
                                            <li class="breadcrumb-item"><a href="/admin/order">Order</a></li>
                                            <li class="breadcrumb-item active">Detail</li>
                                        </ol>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- end page title -->
    
                        <div class="row">
                            <div class="col-xl-8">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">Order Items</h4>
    
                                        <div class="table-responsive">
                                            <table class="table table-centered mb-0 align-middle table-hover table-nowrap">
                                                <thead class="table-light">
                                                    <tr>
                                                        <th>Product</th>
                                                        <th>Price</th>
                                                        <th style="text-align: center;">Quantity</th>
                                                        <th>Total</th>
                                                    </tr>
                                                </thead><!-- end thead -->
                                                <tbody>
                                                    <% for(Product item: order.getOrderItems()) {%>
                                                        <tr>
                                                            <td><h6 class="mb-0"><%= item.getTitle() %></h6></td>
                                                            <td><%= Math.round(item.getPrice()) %> VND</td>
                                                            <td style="text-align: center;"><%= item.getQuantity() %></td>
                                                            <td><%= Math.round(item.getQuantity()*(item.getPrice() - item.getDiscount_percentage()/100*item.getPrice()) ) %> VND</td>
                                                        </tr>
                                                    <%
                                                        }
                                                    %>
                                                </tbody><!-- end tbody -->
                                            </table> <!-- end table -->
                                        </div>
                                    </div><!-- end card -->
                                </div><!-- end card -->
                            </div>
                            <!-- end col -->
                            <div class="col-xl-4">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">Customer</h4>

                                        <div class="customer-info">
                                            <div class="mb-3">
                                                <p><strong>Username: </strong> ${order.username}</p>
                                            </div>
                                            <div class="mb-3">
                                                <p><strong>Fullname: </strong> ${order.user.fullname}</p>
                                            </div>
<!--                                            <div class="mb-3">
                                                <p><strong>Phone:</strong> (123) 456-7890</p>
                                            </div>
                                            <div class="mb-3">
                                                <p><strong>Address:</strong> 123 Main Street, City, Country</p>
                                            </div>-->
                                        </div>
                                    </div>
                                </div><!-- end card -->
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">Order Details</h4>
                                        
                                        <div class="order-details">
                                            <form action="/admin/order" method="post" class="row mb-3">
                                                <label class="col-4"><strong>Order Status:</strong></label>
                                                <input type="hidden" name="id" value="${order.id}" />
                                                <div class="col-3 mx-4">
                                                    <select name="status" class="form-control d-inline-block w-auto" style="margin-right: 10px;">
                                                        <option value="approved" <%= order.getStatus().equals("approved") ? "selected" : "" %>>Approved</option>
                                                        <option value="processing" <%= order.getStatus().equals("processing") ? "selected" : "" %>>Processing</option>
                                                        <option value="cancled" <%= order.getStatus().equals("cancled") ? "selected" : "" %>>Cancled</option>
                                                    </select>
                                                </div>
                                                <div class="col-3">
                                                    <button class="btn btn-primary">Change</button>
                                                </div>
                                                
                                            </form>
                                            <div class="mb-3">
                                                <p><strong>Date created:</strong></p>
                                                <p>${order.orderDate}</p>
                                            </div>
                                            <div class="mb-3">
                                                <p><strong>Payment Method:</strong></p>
                                                <p>Banking</p>
                                            </div>
                                        </div>
                                    </div>
                                </div><!-- end card -->
                            </div> <!-- customer info -->
                        </div>
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="card">
                                    <div class="card-body">
                                        <h4 class="card-title mb-4">Transactions</h4>
                                        <div class="text-center">
                                            <img src="${order.image}" alt="megamenu-img" class="img-fluid mx-auto d-block" style="width: 300px; height: auto;">
                                        </div>
                                    </div><!-- end card-body -->
                                </div><!-- end card -->
                            </div><!-- end col -->

                            <!-- end col -->
 <!-- customer info -->
                        </div>
                        <!-- end row -->
                    </div>
                    
                </div>
                <!-- End Page-content -->
                
            </div>
            <!-- end main content-->

        </div>
        <!-- END layout-wrapper -->

        <!-- Right bar overlay-->
        <div class="rightbar-overlay"></div>

        <!-- JAVASCRIPT -->
        <script src="/admin/assets/libs/jquery/jquery.min.js"></script>
        <script src="/admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/admin/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/admin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/admin/assets/libs/node-waves/waves.min.js"></script>


        <!-- jquery.vectormap map -->
        <script src="/admin/assets/libs/admin-resources/jquery.vectormap/jquery-jvectormap-1.2.2.min.js"></script>
        <script src="/admin/assets/libs/admin-resources/jquery.vectormap/maps/jquery-jvectormap-us-merc-en.js"></script>

        <!-- Required datatable js -->
        <script src="/admin/assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
        
        <!-- Responsive examples -->
        <script src="/admin/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>


        <!-- App js -->
        <script src="/admin/assets/js/app.js"></script>
        <script src="/admin/assets/js/menu.js"></script>
    </body>

</html>