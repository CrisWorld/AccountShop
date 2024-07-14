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

        <!-- Favicon -->
        <link href="/client/assets/img/favicon.ico" rel="icon">

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
        
                
        <%@include file="/client/components/header.jsp" %>

        <!-- Page Header Start -->
        <div class="container-fluid bg-secondary mb-5">
            <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
                <h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
                <div class="d-inline-flex">
                    <p class="m-0"><a href="">Home</a></p>
                    <p class="m-0 px-2">-</p>
                    <p class="m-0">Checkout</p>
                </div>
            </div>
        </div>
        <!-- Page Header End -->
        <div class="container-fluid pt-5">
            <div class="row px-xl-5">
                <div class="col-lg-8">
                    <div class="mb-4">
                        <h4 class="font-weight-semi-bold mb-4">Order Total</h4>
                        <div class="row">

                            <c:if test="${not empty message}">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th scope="col">${message}</th>
                                        </tr>
                                    </thead>
                                </table>
                            </c:if>


                            <c:if test= "${not empty list}" >

                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Products</th>
                                            <th scope="col">Price</th>
                                            <th scope="col">Quantity</th>
                                            <th scope="col">Total (VND)</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <c:forEach var="product" items="${list}">                       
                                            <tr>
                                                <th scope="row">${list.indexOf(product) + 1}</th>
                                                <td>${product.title}</td>
                                                <td>${product.price}</td>
                                                <td>${product.quantity}</td>
                                                <td>${product.price}</td>
                                            </tr>                                
                                        </c:forEach>

                                        <tr>
                                            <td class="text-center font-weight-bold" colspan="4">Subtotal</td>
                                            <td colspan="1">${subtotal}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-center font-weight-bold" colspan="4">Discount</td>
                                            <td colspan="1">${discount}</td>
                                        </tr>
                                        <tr>
                                            <td class="text-center" colspan="4"><h5 class="font-weight-bold">Total</h5></td>
                                            <td colspan="1">${total}</td>
                                        </tr>

                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="card border-secondary mb-5">
                        <div class="card-header bg-secondary border-0">
                            <h4 class="font-weight-semi-bold m-0">Payment</h4>
                        </div>

                        <div class="card-footer border-secondary bg-transparent">

                            <!-- ============ FORM ORDER =================-->

                            <c:if test= "${not empty list}" >
                                <form action="/client/order" method="post" enctype="multipart/form-data">

                                    <div class="card-body">
                                        <div class="form-group">
                                            <div class="custom-control custom-radio">
                                                <input type="radio" class="custom-control-input" name="payment" id="bank" checked>
                                                <label class="custom-control-label" for="bank">Bank (MB Bank 0775771042)</label>
                                            </div>
<!--                                        </div>
                                        <div class="form-group">-->

                                            <div class="preview custom-control">
                                                <label for="file-input custom-file-label col-sm-2">Image</label>
                                                <input required accept="image/*" type="file" id="file-input" name="transaction"/>
                                            </div>
                                            <div class="col-12 d-flex justify-content-center">
                                                <img id="img-preview" class="" src="" style="height: 100px"/>
                                            </div>

<!--                                            <div class="custom-control custom-radio">
                                                <input accept="image/*" type="file" class="custom-file-input" id="customFile">
                                                <label class="custom-file-label" for="customFile">Paid Image</label>
                                            </div>-->
                                        </div>
                                    </div>

                                    <input hidden name="total" value="${total}" />
                                    <button type="submit" class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Place Order</button> 
                                </form>
                            </c:if>

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <!-- Checkout End -->

        <!-- Footer Start -->
        <%@include file="/client/components/footer.jsp" %>

        <script src="/client/assets/js/main.js"></script>

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
