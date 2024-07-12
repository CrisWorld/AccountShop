<%-- 
    Document   : list
    Created on : Jul 9, 2024, 1:01:55 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                                        <h4 class="card-title">Category List</h4>
                                        <p class="card-title-desc">Category List</p>    
                                        
                                        <div class="table-responsive">
                                            <table class="table table-hover mb-0">
        
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>Name</th>
                                                        <th>Banner</th>
                                                        <th>Description</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                            <th scope="row">1</th>
                                                            <td>League of legend</td>
                                                            <td>img</td>
                                                            <td>LOL</td>
                                                            <td> 
                                                                <a class="btn btn-sm btn-outline-info waves-effect waves-light fas fa-edit" data-bs-toggle="modal"
                                                                        data-bs-target="#edit${category.id}">
                                                                </a>
                                                                
                                                                <a class="btn btn-sm btn-outline-danger waves-effect waves-light fas fa-trash-alt" data-bs-toggle="modal"
                                                                        data-bs-target="#delete${category.id}">
                                                                </a>
                                                                    
                                                                 <!-- ============ handle edit ================ -->            
                                                                <form action="#" method="post" class="card-body" enctype="multipart/form-data">
                                                                    <div class="modal" id="edit${category.id}" tabindex="-1">
                                                                        <div class="modal-dialog">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h5 style="color : blue" class="modal-title">Edit Category</h5>
                                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <!--<p>Edit " ${product.title} " ?</p>-->
                                                                                    <!--FORM EDIT-->
                                                                                    <div class="row mb-3">
                                                                                        <div class="preview col-12">
                                                                                          <label for="file-input" class="col-sm-3 col-form-label">Banner</label>
                                                                                          <input accept="image/*" type="file" id="file-input" name="img"/>
                                                                                        </div>
                                                                                        <div class="col-12 d-flex justify-content-center">
                                                                                            <img id="img-preview" class="" src="" style="height: 100px"/>
                                                                                        </div>
                                                                                    </div>
                                                                                    
                                                                                    <div class="row mb-3">
                                                                                        <label for="example-text-input" class="col-sm-3 col-form-label">Name</label>
                                                                                        <div class="col-sm-9">
                                                                                            <input name="name" class="form-control" type="text" placeholder="LOL" id="example-text-input">
                                                                                        </div>                                         
                                                                                    </div>
                                                                                    
                                                                                    <div class="row mb-3">
                                                                                        <label for="example-text-input" class="col-sm-3 col-form-label">Description</label>
                                                                                        <div class="col-sm-9">
                                                                                            <input name="description" class="form-control" type="text" placeholder="Description" id="example-text-input">
                                                                                        </div>                                         
                                                                                    </div>
                                                                                    
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="submit" class="btn btn-primary">Edit</button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </form>
                                                                
                                                                <!-- ================ handle delete ================== -->    
                                                                <form action="#" method="post">
                                                                    <div class="modal" id="delete${category.id}" tabindex="-1">
                                                                        <div class="modal-dialog">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h5 style="color : red" class="modal-title">Delete Warning !!!</h5>
                                                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <p>You want to delete category having name " ${category.title} " ?</p>
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
                                                </tbody>
                                            </table>
                                            
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
        
        <script>
            const input = document.getElementById('file-input');
            const image = document.getElementById('img-preview');

            input.addEventListener('change', (e) => {
                if (e.target.files.length) {
                    const src = URL.createObjectURL(e.target.files[0]);
                    image.src = src;
                }
            });
            
            // for change to base64string
//            document.getElementById('fileInput').addEventListener('change', function() {
//            const file = this.files[0];
//            const reader = new FileReader();
//            reader.onload = function() {
//                const base64String = reader.result.split(',')[1];
//                document.getElementById('base64String').value = base64String;
//            };
//            reader.readAsDataURL(file);
//            });
        </script>
        
    </body>
</html>
