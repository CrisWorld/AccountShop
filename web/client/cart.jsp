<%-- 
    Document   : cart
    Created on : 1 Jul 2024, 10:30:32
    Author     : quoch
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="models.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    Cart cart;
%>
<% 
    cart = (Cart) request.getAttribute("cart");
%>
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
    <link href="/client/assets/lib/owlcarousel/owl.carousel.min.js" rel="stylesheet">
    
    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/assets/css/style.css" rel="stylesheet">
</head>

<body>
    <%@include file="/client/components/header.jsp" %>
    <!-- Page Header Start -->
    <div class="container-fluid bg-secondary mb-5">
        <div class="d-flex flex-column align-items-center justify-content-center" style="min-height: 300px">
            <h1 class="font-weight-semi-bold text-uppercase mb-3">Shopping Cart</h1>
            <div class="d-inline-flex">
                <p class="m-0"><a href="">Home</a></p>
                <p class="m-0 px-2">-</p>
                <p class="m-0">Shopping Cart</p>
            </div>
        </div>
    </div>
    <!-- Page Header End -->


    <!-- Cart Start -->
    <div class="container-fluid pt-5">
        <div class="row px-xl-5">
            <div class="col-lg-8 table-responsive mb-5">
                <table class="table table-bordered text-center mb-0">
                    <thead class="bg-secondary text-dark">
                        <tr>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody class="align-middle">
                        <c:forEach var="item" items="${cart.products}">
                            <tr>
                                <td class="align-middle">
                                    <img src="${item.img}" alt="${item.title}" style="width: 50px;">
                                    ${item.title}
                                </td>
                                <td class="align-middle price" data-price="${Math.round(item.price*(100-item.discount_percentage)/100)}">${Math.round(item.price*(100-item.discount_percentage)/100)} VNĐ</td>
                                <td class="align-middle">
                                    <div class="input-group quantity mx-auto" style="width: 100px;">
<!--                                        <div class="input-group-btn">
                                            <button class="btn btn-sm btn-primary btn-minus">
                                                <i class="fa fa-minus"></i>
                                            </button>
                                        </div>-->
                                        <input type="text" class="form-control form-control-sm bg-secondary text-center" value="${item.quantity}" limit="1" disabled>
<!--                                        <div class="input-group-btn">
                                            <button class="btn btn-sm btn-primary btn-plus">
                                                <i class="fa fa-plus"></i>
                                            </button>
                                        </div>-->
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <span class="total-price" data-price="${Math.round(item.price*(100-item.discount_percentage)/100*item.quantity)}">${Math.round(item.price*(100-item.discount_percentage)/100*item.quantity)}</span> VNĐ
                                </td>
                                <td class="align-middle">
                                    <form action="/cart" method="post">
                                        <input name="_method" type="hidden" value="delete"/>
                                        <input name="product_id" type="hidden" value="${item.id}"/>
                                        <button class="btn btn-sm btn-primary btn-remove">
                                            <i class="fa fa-times"></i>
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4">
<!--                <form class="mb-5" action="">
                    <div class="input-group">
                        <input type="text" class="form-control p-4" placeholder="Coupon Code">
                        <div class="input-group-append">
                            <button class="btn btn-primary">Apply Coupon</button>
                        </div>
                    </div>
                </form>-->
                <div class="card border-secondary mb-5">
                    <div class="card-header bg-secondary border-0">
                        <h4 class="font-weight-semi-bold m-0">Cart Summary</h4>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-between mb-3 pt-1">
                            <h6 class="font-weight-medium">Subtotal</h6>
                            <h6 class="font-weight-medium"><span id="sub-total">200</span> VNĐ</h6>
                        </div>
                        <div class="d-flex justify-content-between">
                            <h6 class="font-weight-medium">Discount from coupon</h6>
                            <h6 class="font-weight-medium"><span id="discount" data-discount="0">0</span> VNĐ</h6>
                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold"><span id="final-total">200</span> VNĐ</h5>
                        </div>
                        <a style="text-decoration: none" href="/client/checkout"><button class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</button></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Cart End -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script>
        $(() => {
            updateCartSummary();
            function updateCartSummary(){
                let total = 0;
                $('.total-price').filter((index, item) => {
                    total += $(item).data('price');
                });
                let discount = $('#discount').data('discount')*total;
                $('#discount').text(discount);
                $('#sub-total').text(total);
                let finalTotal = total-discount;
                $('#final-total').text(finalTotal);
            }
            function updateTotalOfSingleProduct(element, price, quantity){
                $(element).data('price', price*quantity);
                $(element).text(price*quantity + " ");
            }
//            $('.btn-minus').on("click", (e) => {
//                const container = $(e.currentTarget).closest('.quantity');
//                const priceTotalEl = $(e.currentTarget).closest('tr').find('.total-price');
//                const priceEl = $(e.currentTarget).closest('tr').find('.price');
//                const inputEl = $(container).find('input');
//                if($(inputEl).val() > 2) {
//                    $(inputEl).val($(inputEl).val()-1);
//                    updateTotalOfSingleProduct($(priceTotalEl), $(priceEl).data('price'), $(inputEl).val());
//                    updateCartSummary();
//                }
//                // cật nhật quantity của cart_products
//            });
////
//            $('.btn-plus').on("click", (e) => {
//                const container = $(e.currentTarget).closest('.quantity');
//                const priceTotalEl = $(e.currentTarget).closest('tr').find('.total-price');
//                const priceEl = $(e.currentTarget).closest('tr').find('.price');
//                const inputEl = $(container).find('input');
//                console.log($(inputEl).val());
//                if($(inputEl).val() < $(inputEl).attr('limit')) {
//                    console.log($(inputEl).attr('limit'));
//                    $(inputEl).val(parseInt($(inputEl).val())+1);
//                    updateTotalOfSingleProduct(priceTotalEl, $(priceEl).data('price'), $(inputEl).val());
//                    updateCartSummary();
//                }
//                // cật nhật quantity của cart_products
//            });
        });
    </script>
    <%@include file="/client/components/footer.jsp" %>
</body>

</html>
