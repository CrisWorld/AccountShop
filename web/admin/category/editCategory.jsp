<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <!-- Bootstrap Css -->
        <link href="/admin/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/42.0.0/ckeditor5.css" />
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
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box d-sm-flex align-items-center justify-content-between">
                                    <h4 class="mb-sm-0">Categories</h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="/admin/category">Categories</a></li>
                                            <li class="breadcrumb-item active">Edit</li>
                                        </ol>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <div class="card">

                                    <!--ACTION HERE-->

                                    <form method="post" action="/admin/category" class="card-body" enctype="multipart/form-data">
                                        <h4 class="card-title">Edit Category</h4>
                                        <input type="hidden" name="action" value="edit"/>
                                        <input type="hidden" name="id" value="${category.id}"/>
                                        <!--name-->
                                        <div class="row mb-3">
                                            <label for="example-text-input" class="col-sm-2 col-form-label" >Name</label>
                                            <div class="col-sm-10">
                                                <input name="name" class="form-control" type="text" placeholder="Category Name" id="example-text-input" value="${category.name}">
                                            </div>                                         
                                        </div>
                                        <!--   banner-->
                                        <div class="row mb-3">
                                            <div class="preview col-12">
                                                <label for="file-input" class="col-sm-2 col-form-label">Banner</label>
                                                <input accept="image/*" type="file" id="file-input" name="img"/>
                                            </div>
                                            <div class="col-12 d-flex justify-content-center">
                                                <img id="img-preview" class="" src="${category.banner}" style="height: 100px"/>
                                            </div>
                                        </div>
                                        <!--desc-->
                                        <div class="row mb-3">
                                            <label for="meta_title" class="col-sm-2 col-form-label">Description</label>
                                            <div class="col-sm-10">
                                                <textarea name="desc" class="form-control" rows="3" placeholder="Category Description" id="meta_title">${category.description}</textarea>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="example-text-input" class="col-sm-2 col-form-label">Parent Category</label>
                                            <div class="col-sm-10">                                            
                                                <select name="parent_id" class="form-select">
                                                    <option value="" ${category.parentId==null ? "selected" : ""}>Parent Category</option>
                                                    
                                                    <c:forEach var="categoryEdit" items="${categories}">
                                                        <c:if test="${category.id != categoryEdit.id}">
                                                            <option value="${categoryEdit.id}" ${category.parentId==categoryEdit.id ? "selected" : ""}>${categoryEdit.name}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>  
                                        </div>
                                        <!--button-->
                                        <div class="d-flex justify-content-end">
                                            <button class="btn btn-primary" type="submit">Save</button>
                                        </div>
                                    </form>
                                </div>
                            </div> <!-- end col -->
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
        <script src="/admin/assets/libs/tinymce/tinymce.min.js"></script>
        <script src="/admin/assets/js/pages/form-editor.init.js"></script>
        <script src="/admin/assets/js/menu.js"></script>
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
