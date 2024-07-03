<%-- 
    Document   : ContactView
    Created on : Jul 2, 2024, 9:59:41 PM
    Author     : thaip
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Admin Page - Contact</title>
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
                                        <h4 class="card-title">Contact List</h4>
                                        <p class="card-title-desc">Contact List</p>    
                                        
                                        <div class="table-responsive">
                                            <table class="table table-hover mb-0">
        
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Username</th>
                                                        <th>Message</th>
                                                        <th>Time</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th scope="row">1</th>
                                                        <td>admin123</td>
                                                        <td>loi login</td>
                                                        <td>19:23 20/06/2024</td>
                                                        <td>
                                                            <button type="button" class="btn btn-sm btn-outline-danger waves-effect waves-light"><i class="fas fa-trash-alt"></i></button>
                                                        </td>
                                                    </tr>
<!--                                                    <tr>
                                                        <th scope="row">2</th>
                                                        <td>Acc liên quân nhiều skin</td>
                                                        <td>10</td>
                                                        <td>Liên quân</td>
                                                        <td>20.000 VNĐ</td>
                                                        <td>
                                                            <span class="badge bg-dark">Hidden</span>
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btn btn-sm btn-outline-info waves-effect waves-light"><i class="fas fa-edit"></i></button>
                                                            <button type="button" class="btn btn-sm btn-outline-danger waves-effect waves-light"><i class="fas fa-trash-alt"></i></button>
                                                        </td>
                                                    </tr>-->
                                                </tbody>
                                            </table>
                                            <div class="mt-4">
                                                <nav aria-label="...">
                                                    <ul class="pagination pagination-rounded justify-content-end">
                                                        <li class="page-item">
                                                            <a class="page-link" href="#" tabindex="-1">Previous</a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" href="#">1</a></li>
                                                        <li class="page-item active">
                                                            <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
                                                        </li>
                                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                                        <li class="page-item">
                                                            <a class="page-link" href="#">Next</a>
                                                        </li>
                                                    </ul>
                                                </nav>
                                            </div>
                                            
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
