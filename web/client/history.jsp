<%-- 
    Document   : checkout
    Created on : Jul 13, 2024, 1:19:20 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    
    <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/favicon.ico">
         <!--Toast-->
        <link rel="stylesheet" type="text/css" href="/admin/assets/libs/toastr/build/toastr.min.css">
        <!-- Bootstrap Css -->
        <link href="/client/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="/admin/assets/libs/magnific-popup/magnific-popup.css">
    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/client/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/assets/css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Topbar Start -->
    <%@include file="/client/components/header.jsp" %>
    <!-- Navbar End -->


    <!-- Page Header Start -->
    <div class="container-fluid bg-secondary mb-5">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
            <h1 class="font-weight-semi-bold text-uppercase mb-3">History</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="">Home</a></p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">History</p>
            </div>
        </div>
    </div>
    <!-- Page Header End -->
    <div class="container pt-12">
        <div class="row px-xl-12">
            <div class="col-lg-12">
                <div class="mb-12">
                    <h4 class="font-weight-semi-bold mb-4">Order History</h4>
                    <div class="row">
                        <table class="table table-hover">
                            <thead>
                              <tr>
                                <th scope="col">#</th>
                                <th scope="col">Products</th>
                                <th scope="col">Price</th>
                                <th scope="col">Quantity</th>
                                <th scope="col">Secret-Info</th>
                                <th scope="col">Order_date</th>
                                
                              </tr>
                            </thead>
                            <tbody>
                                
                                <c:forEach var="orderItem" items="${list}">                       
                                    <tr>
                                        <th scope="row">${list.indexOf(orderItem) + 1}</th>
                                        <td>${orderItem.product.title}</td>
                                        <td>${orderItem.price}</td>
                                        <td>${orderItem.quantity}</td>
                                        <c:if test="${orderItem.order.status == 'approved'}">
                                            <td style="font-weight: bold">${orderItem.product.secret_info}</td>
                                        </c:if>
                                        <c:if test="${orderItem.order.status == 'processing'}">
                                            <td><span class="badge rounded-pill bg-warning">${orderItem.order.status}</span></td>
                                        </c:if>
                                        <td>${orderItem.order.orderDate}</td>
                                    </tr>                                
                                </c:forEach>
                          
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
    
    <!-- Checkout End -->

    <!-- Footer Start -->
    <%@include file="/client/components/footer.jsp" %>
    <!-- Footer End -->
    
    <!-- toastr plugin -->
    <script src="/admin/assets/libs/toastr/build/toastr.min.js"></script>

    <!-- toastr init -->
    <script src="/admin/assets/js/pages/toastr.init.js"></script>
    <!-- Template Javascript -->
    <script src="/client/assets/js/main.js"></script>
    
    <c:if test="${sessionScope.showToast == true}">
        <script>
            $(document).ready(function() {
                toastr.options = {
                    "closeButton": false,
                    "debug": false,
                    "newestOnTop": true,
                    "progressBar": false,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": false,
                    "onclick": null,
                    "showDuration": 300,
                    "hideDuration": 1000,
                    "timeOut": 2000,
                    "extendedTimeOut": 1000,
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                  },
                toastr.success('Successfull !!!', 'Success');
            });
        </script>
        <%
            session.removeAttribute("showToast");
        %>
        </c:if>
        
        <c:if test="${sessionScope.showToast == false}">
        <script>
            $(document).ready(function() {
                toastr.options = {
                    "closeButton": false,
                    "debug": false,
                    "newestOnTop": false,
                    "progressBar": false,
                    "positionClass": "toast-top-right",
                    "preventDuplicates": false,
                    "onclick": null,
                    "showDuration": 300,
                    "hideDuration": 1000,
                    "timeOut": 2000,
                    "extendedTimeOut": 1000,
                    "showEasing": "swing",
                    "hideEasing": "linear",
                    "showMethod": "fadeIn",
                    "hideMethod": "fadeOut"
                  },
                toastr.info('Sản phẩm không tồn tại.', 'Error');
            });
        </script>
        <%
            session.removeAttribute("showToast");
        %>
        </c:if>
    
</body>

</html>
