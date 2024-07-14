<%-- 
    Document   : ContactView
    Created on : Jul 2, 2024, 9:59:41 PM
    Author     : thaip
--%>
<%@page import="models.Contact"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="untils.Contact_Admin"%>

<!DOCTYPE html>
<html>
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


            <%@ include file="/admin/components/header.jsp" %>

            <!-- ========== Left Sidebar Start ========== -->
            <%@ include file="/admin/components/menu.jsp" %>
            <!-- Left Sidebar End -->



            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="main-content">

                <div class="page-content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                                    <h4 class="mb-sm-0">Contact</h4>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="card">
                                    <div class="card-body">  
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
                                                <%
                                                    Contact_Admin contact = new Contact_Admin();
                                                    List<Contact> listContact = contact.Display_Contact();
                                                    request.setAttribute("messages", listContact);
                                                %>
                                                <tbody>
                                                    <c:forEach var="mess" items="${messages}">
                                                        <tr>
                                                            <th scope="row">${mess.id}</th>
                                                            <td>${mess.username}</td>
                                                            <td>${mess.message}</td>
                                                            <td>${mess.createdAt}</td>
                                                            <td>
                                                                <form action="/admin/contact" method="post">
                                                                    <input type="hidden" name="delete_contact" value="${mess.id}" />
                                                                    <button type="submit" value="Delete_contact" name="_method" class="btn btn-sm btn-outline-danger waves-effect waves-light"><i class="fas fa-trash-alt"></i></button>
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>

                                                </table>
<!--                                                <div class="mt-4">
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
                                                </div>-->

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