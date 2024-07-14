<%-- 
    Document   : create.jsp
    Created on : 12 Jul 2024, 11:27:34
    Author     : quoch
--%>

<%@page import="models.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.Role"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
    ArrayList<Role> roles;
    User account;
%>
<%
    roles = (ArrayList<Role>) request.getAttribute("roles");
    account = (User) request.getAttribute("account");
%>

<!doctype html>
<html lang="en">

    <head>

        <meta charset="utf-8" />
        <title>Forms Elements | Upcube - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/favicon.ico">

        <!-- Bootstrap Css -->
        <link href="/admin/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" rel="stylesheet" type="text/css" />

    </head>

    <body data-topbar="dark">
        <%--<%@include file="/admin/components/header.jsp" %>--%>

        <!-- ========== Left Sidebar Start ========== -->
        <%--<%@include file="/admin/components/menu.jsp" %>--%>
        <!-- Left Sidebar End -->
        <!-- <body data-layout="horizontal" data-topbar="dark"> -->

        <!-- Begin page -->
        <div id="layout-wrapper">
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
                                    <h4 class="mb-sm-0">Edit a account</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Account</a></li>
                                            <li class="breadcrumb-item active">Edit a account</li>
                                        </ol>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <!-- end page title -->

                        <form method="post" action="/admin/account" class="row" enctype="multipart/form-data">
                            <input name="_method" value="put" type="hidden"/>
                            <div class="col-12">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="d-flex justify-content-end mb-3">
                                            <button id="toggle-password-btn" class="btn btn-primary">Change password</button>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="example-text-input" class="col-sm-2 col-form-label">Full name</label>
                                            <div class="col-sm-10">
                                                <input value="${account.fullname}" name="fullname" class="form-control" type="text" placeholder="Artisanal kale" id="example-text-input">
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div id="username-row" class="row mb-3">
                                            <label for="example-text-input" class="col-sm-2 col-form-label">Username</label>
                                            <div class="col-sm-10">
                                                <input value="${account.username}" disabled class="form-control" type="text" placeholder="artisanalkale" id="example-text-input">
                                                <input name="username" value="${account.username}" class="form-control" type="hidden">
                                            </div>
                                        </div>
                                        <div id="password-row" class="row mb-3">
                                            <label for="example-password-input" class="col-sm-2 col-form-label">Password</label>
                                            <div class="col-sm-10">
                                                <input value="${account.password}" name="password" class="form-control" type="password" value="AccountShop123" id="example-password-input">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="example-number-input" class="col-sm-2 col-form-label">Role</label>
                                            <div class="col-sm-10">
                                                <select name="role_id" class="form-select" aria-label="Default select example">
                                                    <option value="" ${account.roleId == null ? "selected" : ""}>Open this select role</option>
                                                    <c:forEach var="item" items="${roles}">
                                                        <option ${account.roleId == item.roleId ? "selected" : ""} value="${item.roleId}">${item.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div class="row mb-3">
                                            <div class="preview col-12">
                                                <label for="file-input" class="col-sm-2 col-form-label">Image</label>
                                                <input value="${account.image}" name="image" accept="image/*" type="file" id="file-input" />
                                            </div>
                                            <div class="col-12 d-flex justify-content-center">
                                                <img id="img-preview" class="" src="${account.image}" style="height: 100px"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="d-flex justify-content-end p-4">
                                        <button class="btn btn-primary" type="submit">Save</button>
                                    </div>
                                </div>
                            </div> <!-- end col -->
                        </form>
                        <!-- end row -->


                        <!-- end row -->
                    </div> <!-- container-fluid -->
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

        <!-- bs custom file input plugin -->
        <script src="/admin/assets/libs/bs-custom-file-input/bs-custom-file-input.min.js"></script>

        <script src="/admin/assets/js/pages/form-element.init.js"></script>

        <script src="/admin/assets/js/app.js"></script>
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
        <script>
            const component = $('#password-row')[0];
            $(component).remove();
            $("#toggle-password-btn").on("click", (e) => {
                e.preventDefault();
                if($("#username-row").next()[0] == component) $(component).remove();
                else $("#username-row").after(component);
            })
        </script>
        <script>
            $('li a').removeClass("active");
            $('li').removeClass("mm-active");
            $('li[root-href="'+window.location.pathname+'"]');
            $('li:has(>a[href="'+window.location.pathname+window.location.search+'"])').toggleClass("mm-active");
            $('a[href="'+window.location.pathname+window.location.search+'"])').addClass("active");
            console.log(window.location.pathname);
        </script>
    </body>
</html>

