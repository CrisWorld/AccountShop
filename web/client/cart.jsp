<%-- 
    Document   : cart
    Created on : 1 Jul 2024, 10:30:32
    Author     : quoch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>EShopper - Bootstrap Shop Template</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="assets/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="assets/css/style.css" rel="stylesheet">
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
                        <tr>
                            <td class="align-middle"><img src="assets/img/product-1.jpg" alt="" style="width: 50px;"> Colorful Stylish Shirt</td>
                            <td class="align-middle price" data-price="150">150 VNĐ</td>
                            <td class="align-middle">
                                <div class="input-group quantity mx-auto" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-primary btn-minus" >
                                        <i class="fa fa-minus"></i>
                                        </button>
                                    </div>
                                    <input type="text" class="form-control form-control-sm bg-secondary text-center" value="1" limit="3" disabled>
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-primary btn-plus">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle"><span class="total-price" data-price="150">150 </span> VNĐ</td>
                            <td class="align-middle"><button class="btn btn-sm btn-primary btn-remove"><i class="fa fa-times"></i></button></td>
                        </tr>
                        <tr>
                            <td class="align-middle"><img src="assets/img/product-1.jpg" alt="" style="width: 50px;"> Colorful Stylish Shirt</td>
                            <td class="align-middle price" data-price="150">150 VNĐ</td>
                            <td class="align-middle">
                                <div class="input-group quantity mx-auto" style="width: 100px;">
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-primary btn-minus" >
                                        <i class="fa fa-minus"></i>
                                        </button>
                                    </div>
                                    <input type="text" class="form-control form-control-sm bg-secondary text-center" value="1" limit="3" disabled>
                                    <div class="input-group-btn">
                                        <button class="btn btn-sm btn-primary btn-plus">
                                            <i class="fa fa-plus"></i>
                                        </button>
                                    </div>
                                </div>
                            </td>
                            <td class="align-middle"><span class="total-price" data-price="150">150 </span> VNĐ</td>
                            <td class="align-middle"><button class="btn btn-sm btn-primary btn-remove"><i class="fa fa-times"></i></button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-4">
                <form class="mb-5" action="">
                    <div class="input-group">
                        <input type="text" class="form-control p-4" placeholder="Coupon Code">
                        <div class="input-group-append">
                            <button class="btn btn-primary">Apply Coupon</button>
                        </div>
                    </div>
                </form>
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
                            <h6 class="font-weight-medium"><span id="discount" data-discount="0.1">0</span> VNĐ</h6>
                        </div>
                    </div>
                    <div class="card-footer border-secondary bg-transparent">
                        <div class="d-flex justify-content-between mt-2">
                            <h5 class="font-weight-bold">Total</h5>
                            <h5 class="font-weight-bold"><span id="final-total">200</span> VNĐ</h5>
                        </div>
                        <button class="btn btn-block btn-primary my-3 py-3">Proceed To Checkout</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Cart End -->
    <%@include file="/client/components/footer.jsp" %>
    <script>
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
        $('.btn-minus').on("click", (e) => {
            const container = $(e.currentTarget).closest('.quantity');
            const priceTotalEl = $(e.currentTarget).closest('tr').find('.total-price');
            const priceEl = $(e.currentTarget).closest('tr').find('.price');
            const inputEl = $(container).find('input');
            if($(inputEl).val() > 1) {
                $(inputEl).val($(inputEl).val()-1);
                updateTotalOfSingleProduct($(priceTotalEl), $(priceEl).data('price'), $(inputEl).val());
                updateCartSummary();
            }
            // cật nhật quantity của cart_products
        });
        
        $('.btn-plus').on("click", (e) => {
            const container = $(e.currentTarget).closest('.quantity');
            const priceTotalEl = $(e.currentTarget).closest('tr').find('.total-price');
            const priceEl = $(e.currentTarget).closest('tr').find('.price');
            const inputEl = $(container).find('input');
            if($(inputEl).val() < $(inputEl).attr('limit')) {
                $(inputEl).val(parseInt($(inputEl).val())+1);
                updateTotalOfSingleProduct(priceTotalEl, $(priceEl).data('price'), $(inputEl).val());
                updateCartSummary();
            }
            // cật nhật quantity của cart_products
        });
        
        $('.btn-remove').on('click', (e) => {
            const container = $(e.currentTarget).closest('tr');
            console.log(container);
            $(container).remove();
            // gọi api xóa record của bảng cart_products
        });
    </script>
</body>

</html>
