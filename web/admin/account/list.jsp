<%-- 
    Document   : list
    Created on : 12 Jul 2024, 11:18:08
    Author     : quoch
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="models.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    ArrayList<User> accounts;
%>
<%
    accounts = (ArrayList<User>) request.getAttribute("accounts");
%>
<!doctype html>
<html lang="en">

    <head>

        <meta charset="utf-8" />
        <title>Calendar | Upcube - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/favicon.ico">

        <!-- Plugin css -->
        <link rel="stylesheet" href="/admin/assets/libs/@fullcalendar/core/main.min.css" type="text/css">
        <link rel="stylesheet" href="/admin/assets/libs/@fullcalendar/daygrid/main.min.css" type="text/css">
        <link rel="stylesheet" href="/admin/assets/libs/@fullcalendar/bootstrap/main.min.css" type="text/css">
        <link rel="stylesheet" href="/admin/assets/libs/@fullcalendar/timegrid/main.min.css" type="text/css">

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
            <!-- Left Sidebar End -->
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
                                    <h4 class="mb-sm-0">Account Table</h4>
                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <div class="row">
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-end">
                                            <a class="btn btn-info waves-effect waves-light" title="Create" href="?action=create">
                                                <i class="fas fa-plus"></i>
                                            </a>
                                        </div>
                                        <div>
                                            <table id="alternative-page-datatable" class="table align-middle table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Full Name</th>
                                                        <th>Username</th>
                                                        <th>Role</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${accounts}" var="item">
                                                        <tr>
                                                            <td><c:out value="${item.fullname}"/></td>
                                                            <td>${item.username}</td>
                                                            <td>${item.roleName != null ? item.roleName : 'Không có' }</td>
                                                            <td style="width: 100px">
                                                                <a href="/admin/account?action=edit&username=${item.username}" class="btn btn-outline-secondary btn-sm edit" title="Edit">
                                                                    <i class="fas fa-pencil-alt"></i>
                                                                </a>
                                                                <button onclick="deleteByUserName(this)" data-username="${item.username}" class="btn btn-outline-danger btn-outline-secondary btn-sm delete" title="Delete">
                                                                    <i class="fas fa-eraser"></i>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div> <!-- end col -->
                        </div> <!-- end row -->
                    </div> <!-- container-fluid -->
                </div>
                <!-- End Page-content -->
            </div>
            <!-- end main content-->

        </div>
        <!-- END layout-wrapper -->

        <!-- Right Sidebar -->
        <div class="right-bar">
            <div data-simplebar class="h-100">
                <div class="rightbar-title d-flex align-items-center px-3 py-4">

                    <h5 class="m-0 me-2">Settings</h5>

                    <a href="javascript:void(0);" class="right-bar-toggle ms-auto">
                        <i class="mdi mdi-close noti-icon"></i>
                    </a>
                </div>

                <!-- Settings -->
                <hr class="mt-0" />
                <h6 class="text-center mb-0">Choose Layouts</h6>

                <div class="p-4">
                    <div class="mb-2">
                        <img src="/admin/assets/images/layouts/layout-1.jpg" class="img-fluid img-thumbnail" alt="layout-1">
                    </div>

                    <div class="form-check form-switch mb-3">
                        <input class="form-check-input theme-choice" type="checkbox" id="light-mode-switch" checked>
                        <label class="form-check-label" for="light-mode-switch">Light Mode</label>
                    </div>

                    <div class="mb-2">
                        <img src="/admin/assets/images/layouts/layout-2.jpg" class="img-fluid img-thumbnail" alt="layout-2">
                    </div>
                    <div class="form-check form-switch mb-3">
                        <input class="form-check-input theme-choice" type="checkbox" id="dark-mode-switch"
                               data-bsStyle="/admin/assets/css/bootstrap-dark.min.css" data-appStyle="/admin/assets/css/app-dark.min.css">
                        <label class="form-check-label" for="dark-mode-switch">Dark Mode</label>
                    </div>

                    <div class="mb-2">
                        <img src="/admin/assets/images/layouts/layout-3.jpg" class="img-fluid img-thumbnail" alt="layout-3">
                    </div>
                    <div class="form-check form-switch mb-5">
                        <input class="form-check-input theme-choice" type="checkbox" id="rtl-mode-switch"
                               data-appStyle="/admin/assets/css/app-rtl.min.css">
                        <label class="form-check-label" for="rtl-mode-switch">RTL Mode</label>
                    </div>


                </div>

            </div> <!-- end slimscroll-menu-->
        </div>
        <!-- /Right-bar -->

        <!-- Right bar overlay-->
        <div class="rightbar-overlay"></div>

        <!-- JAVASCRIPT -->
        <script src="/admin/assets/libs/jquery/jquery.min.js"></script>
        <script src="/admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/admin/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/admin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/admin/assets/libs/node-waves/waves.min.js"></script>

        <!-- Required datatable js -->
        <script src="/admin/assets/libs/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-bs4/js/dataTables.bootstrap4.min.js"></script>
        <!-- Buttons examples -->
        <script src="/admin/assets/libs/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-buttons-bs4/js/buttons.bootstrap4.min.js"></script>
        <script src="/admin/assets/libs/jszip/jszip.min.js"></script>
        <script src="/admin/assets/libs/pdfmake/build/pdfmake.min.js"></script>
        <script src="/admin/assets/libs/pdfmake/build/vfs_fonts.js"></script>
        <script src="/admin/assets/libs/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-buttons/js/buttons.colVis.min.js"></script>

        <script src="/admin/assets/libs/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-select/js/dataTables.select.min.js"></script>

        <!-- Responsive examples -->
        <script src="/admin/assets/libs/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="/admin/assets/libs/datatables.net-responsive-bs4/js/responsive.bootstrap4.min.js"></script>

        <!-- Datatable init js -->
        <script src="/admin/assets/js/pages/datatables.init.js"></script>

        <script src="/admin/assets/js/app.js"></script>
        <script>
            async function deleteByUserName(button){
                const username = $(button).data("username");
                const url = "/admin/account";
                if(username){
                    try {
                        const formData = new FormData();
                        formData.append('username', username);
                        formData.append('_method', 'delete');
                        const response = await fetch(url, {
                            method: 'POST', // Phương thức HTTP DELETE
                            body: formData // Chuyển đổi dữ liệu sang chuỗi JSON
                        });

                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        } else {
                            window.location.reload();
                        }
                    } catch (error) {
                        console.error('There was a problem with the fetch operation:', error);
                    }
                }
            }
        </script>
        <script>
            const input = document.getElementById('file-input');
            const image = document.getElementById('img-preview');

            input.addEventListener('change', (e) => {
                if (e.target.files.length) {
                    const src = URL.createObjectURL(e.target.files[0]);
                    image.src = src;
                }
            });
        </script>
    </body>

</html>
