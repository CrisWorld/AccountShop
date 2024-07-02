<%-- 
    Document   : auth-login.jsp
    Created on : 2 Jul 2024, 12:50:19
    Author     : quoch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>

    <meta charset="utf-8" />
    <title>Login | Upcube - Admin & Dashboard Template</title>
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

<body class="auth-body-bg">
    <div class="bg-overlay"></div>
    <div class="wrapper-page">
        <div class="container-fluid p-0">
            <div class="card">
                <div class="card-body">

                    <div class="text-center mt-4">
                        <div class="mb-3">
                            <a href="#" class="auth-logo">
                                <!-- Logo here -->
                                <img src="/admin/assets/images/logo-dark.png" height="30" class="logo-dark mx-auto" alt="">
                                <img src="/admin/assets/images/logo-light.png" height="30" class="logo-light mx-auto" alt="">
                            </a>
                        </div>
                    </div>

                    <h4 class="text-muted text-center font-size-18"><b>Sign In</b></h4>

                    <div class="p-3">
                        <form class="form-horizontal mt-3" action="/auth/admin?action=login" method="post">

                            <div class="form-group mb-3 row">
                                <div class="col-12">
                                    <input name="username" class="form-control" type="text" required="" placeholder="Username">
                                </div>
                            </div>

                            <div class="form-group mb-3 row">
                                <div class="col-12">
                                    <input name="password" class="form-control" type="password" required="" placeholder="Password">
                                </div>
                            </div>

                            <div class="form-group mb-3 row">
                                <div class="col-12">
                                    <div class="custom-control custom-checkbox">
                                        <input type="checkbox" class="custom-control-input" id="customCheck1">
                                        <label class="form-label ms-1" for="customCheck1">Remember me</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group mb-3 text-center row mt-3 pt-1">
                                <div class="col-12">
                                    <button class="btn btn-info w-100 waves-effect waves-light" type="submit">Log
                                        In</button>
                                </div>
                            </div>

                            <div class="form-group mb-0 row mt-2">
                                <div class="col-sm-7 mt-3">
                                    <a href="auth-recoverpw.html" class="text-muted"><i class="mdi mdi-lock"></i> Forgot
                                        your password?</a>
                                </div>
                                <div class="col-sm-5 mt-3">
                                    <a href="auth-register.html" class="text-muted"><i
                                            class="mdi mdi-account-circle"></i> Create an account</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- end -->
                </div>
                <!-- end cardbody -->
            </div>
            <!-- end card -->
        </div>
        <!-- end container -->
    </div>
    <!-- end -->

    <!-- JAVASCRIPT -->
    <script src="/admin/assets/libs/jquery/jquery.min.js"></script>
    <script src="/admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="/admin/assets/libs/metismenu/metisMenu.min.js"></script>
    <script src="/admin/assets/libs/simplebar/simplebar.min.js"></script>
    <script src="/admin/assets/libs/node-waves/waves.min.js"></script>

    <script src="/admin/assets/js/app.js"></script>

</body>

</html>
