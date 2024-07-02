<%-- 
    Document   : create.jsp
    Created on : 1 Jul 2024, 20:21:59
    Author     : quoch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/42.0.0/ckeditor5.css" />
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
                            <div class="col-12">
                                <div class="card">
                                    <form action="" method="post" class="card-body" enctype="multipart/form-data">
                                        <h4 class="card-title">Create Product</h4>
<!--                                        <p class="card-title-desc">Here are examples of <code class="highlighter-rouge">.form-control</code> applied to each
                                            textual HTML5 <code class="highlighter-rouge">&lt;input&gt;</code> <code class="highlighter-rouge">type</code>.</p>-->
                                        <div class="row mb-3">
                                            <div class="preview col-12">
                                              <label for="file-input" class="col-sm-2 col-form-label">Image</label>
                                              <input accept="image/*" type="file" id="file-input" />
                                            </div>
                                            <div class="col-12 d-flex justify-content-center">
                                                <img id="img-preview" class="" src="" style="height: 100px"/>
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="example-text-input" class="col-sm-2 col-form-label">Title</label>
                                            <div class="col-sm-10">
                                                <input name="title" class="form-control" type="text" placeholder="Artisanal kale" id="example-text-input">
                                            </div>
                                        </div>
                                        <div class="row mb-2">
                                            <label for="slug" class="col-sm-2 col-form-label">Slug</label>
                                            <div class="col-sm-10">
                                                <input name="slug" class="form-control" type="text" placeholder="abc-xyz" id="slug">
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div class="row mb-3">
                                            <div class="col-3">
                                                <label for="quantity" class="col-sm-12 col-form-label">Quantity</label>
                                                <div class="col-sm-12">
                                                    <input class="form-control" min="0" type="number" placeholder="0" id="quantity">
                                                </div>
                                            </div>
                                            <div class="col-3">
                                                <label for="price" class="col-sm-12 col-form-label">Price</label>
                                                <div class="col-sm-12">
                                                    <input name="price" class="form-control" type="number" placeholder="200000" id="price">
                                                </div>
                                            </div>
                                            <div class="col-3">
                                                <label for="status" class="col-sm-12 col-form-label">Status</label>
                                                <select name="status" class="form-select">
                                                    <option value="showing" selected>Showing</option>
                                                    <option value="hidden">Hidden</option>
                                                </select>
                                            </div>
                                            <div class="col-3">
                                                <label for="discount" class="col-sm-12 col-form-label">Discount (%)</label>
                                                <div class="col-sm-12">
                                                    <input name="discount" min="0" max="100" class="form-control" type="number" placeholder="0-100" id="discount">
                                                </div>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div class="row mb-3">
                                            <label for="example-url-input" class="col-sm-2 col-form-label">Short Description</label>
                                            <div class="col-sm-10">
                                                <textarea name="short_desc" class="form-control" placeholder="" id="example-url-input"></textarea>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div class="row mb-3">
                                            <label for="elm1" class="col-sm-2 col-form-label">Description</label>
                                            <div class="col-sm-10">
                                                <textarea id="elm1" name="description"></textarea>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div class="row mb-5">
                                            <label for="elm2" class="col-sm-2 col-form-label">Secret Information</label>
                                            <div class="col-sm-10">
                                                <textarea id="elm2" class="form-control" name="secret_info"></textarea>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                        <div class="row mb-3">
                                            <label for="slug" class="col-sm-2 col-form-label">Meta title</label>
                                            <div class="col-sm-10">
                                                <input name="meta_title" class="form-control" type="text" placeholder="Title" id="slug">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="meta_keywords" class="col-sm-2 col-form-label">Meta keywords</label>
                                            <div class="col-sm-10">
                                                <input name="meta_keywords" class="form-control" type="text" placeholder="abc, xyz" id="meta_keywords">
                                            </div>
                                        </div>
                                        <div class="row mb-3">
                                            <label for="meta_description" class="col-sm-2 col-form-label">Meta description</label>
                                            <div class="col-sm-10">
                                                <textarea id="meta_description" class="form-control" name="meta_description"></textarea>
                                            </div>
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
