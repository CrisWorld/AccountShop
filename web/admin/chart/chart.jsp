<%-- 
    Document   : chart
    Created on : Jul 14, 2024, 10:25:19 PM
    Author     : PC
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">

    <head>
        
        <meta charset="utf-8" />
        <title>Flot Charts | Upcube - Admin & Dashboard Template</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesdesign" name="author" />


        <!-- Bootstrap Css -->
        <link href="/admin/assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="/admin/assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="/admin/assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

        <!-- App favicon -->
        <link rel="shortcut icon" href="/admin/assets/images/icon.jpg">

         <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/42.0.0/ckeditor5.css" />
        
        
    </head>

    <body data-topbar="dark">
    
    <!-- <body data-layout="horizontal" data-topbar="dark"> -->

        
            <%@include file="/admin/components/header.jsp" %>

            <!-- ========== Left Sidebar Start ========== -->
            <%@include file="/admin/components/menu.jsp" %>
            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="main-content">

                <div class="page-content">
                    <div class="row container-fluid">
                        
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-4">Category</h4>
                                    <div id="pie_chart" class="apex-charts" dir="ltr"></div>  
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-body">
                                    <h4 class="card-title mb-4">Monthly Revenue</h4>
                                    <div id="line_chart" class="apex-charts" dir="ltr"></div>  
                                </div>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <!-- End Page-content -->
            
                
            </div>
            <!-- end main content-->

        </div>
        <!-- END layout-wrapper -->
        <div class="rightbar-overlay"></div>
       

        <!-- JAVASCRIPT -->
        <script src="/admin/assets/libs/jquery/jquery.min.js"></script>
        <script src="/admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="/admin/assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="/admin/assets/libs/simplebar/simplebar.min.js"></script>
        <script src="/admin/assets/libs/node-waves/waves.min.js"></script>

        <!-- flot plugins -->
        <script src="/admin/assets/libs/flot-charts/jquery.flot.js"></script>
        <script src="/admin/assets/libs/flot-charts/jquery.flot.time.js"></script>
        <script src="/admin/assets/libs/jquery.flot.tooltip/js/jquery.flot.tooltip.min.js"></script>
        <script src="/admin/assets/libs/flot-charts/jquery.flot.resize.js"></script>
        <script src="/admin/assets/libs/flot-charts/jquery.flot.pie.js"></script>
        <script src="/admin/assets/libs/flot-charts/jquery.flot.selection.js"></script>
        <script src="/admin/assets/libs/flot-charts/jquery.flot.stack.js"></script>
        <script src="/admin/assets/libs/flot.curvedLines/curvedLines.js"></script>
        <script src="/admin/assets/libs/flot-charts/jquery.flot.crosshair.js"></script>

        <!-- flot init -->
        <script src="/admin/assets/js/pages/flot.init.js"></script>   

        <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
        <script src="/admin/assets/js/menu.js"></script>
        <script src="/admin/assets/js/app.js"></script>
        
        
        <c:if test="${not empty categoryData}">
            <c:set var="labels" value=""/>
            <c:set var="data" value=""/>

            <c:forEach items="${categoryData}" var="entry">
                <c:set var="labels" value="${labels}'${entry.key}',"/>
                <c:set var="data" value="${data}${entry.value},"/>
            </c:forEach>

            <c:if test="${not empty labels}">
                <c:set var="labels" value="${fn:substring(labels, 0, fn:length(labels)-1)}"/> <!-- Remove the last comma -->
                <c:set var="data" value="${fn:substring(data, 0, fn:length(data)-1)}"/> <!-- Remove the last comma -->
            </c:if>

                <script>
                    document.addEventListener('DOMContentLoaded', function () {
                        var options = {
                            series: [${data}], // Data for the pie chart
                            chart: {
                                height: 350,
                                type: 'pie',
                            },
                            labels: [${labels}], // Labels for the pie chart
                        };

                        var chart = new ApexCharts(document.querySelector("#pie_chart"), options);
                        chart.render();
                    });
                </script>
        </c:if>
        
        
        <c:if test="${not empty revenueData}">
            <c:set var="labels" value=""/>
            <c:set var="data" value=""/>

            <c:forEach items="${revenueData}" var="entry">
                <c:set var="labels" value="${labels}'${entry.month}/${entry.year}',"/>
                <c:set var="data" value="${data}${entry.monthlyRevenue},"/>
            </c:forEach>

            <c:if test="${not empty labels}">
                <c:set var="labels" value="${fn:substring(labels, 0, fn:length(labels)-1)}"/> <!-- Xóa dấu phẩy cuối -->
                <c:set var="data" value="${fn:substring(data, 0, fn:length(data)-1)}"/> <!-- Xóa dấu phẩy cuối -->
            </c:if>

            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var options = {
                        series: [{
                            name: 'Revenue',
                            data: [${data}] // Dữ liệu cho biểu đồ đường
                        }],
                        chart: {
                            height: 350,
                            type: 'line',
                            zoom: {
                                enabled: false
                            }
                        },
                        dataLabels: {
                            enabled: false
                        },
                        stroke: {
                            curve: 'smooth'
                        },
                        title: {
                            text: 'Monthly Revenue',
                            align: 'left'
                        },
                        grid: {
                            row: {
                                colors: ['#f3f3f3', 'transparent'], // Màu của hàng
                                opacity: 0.5
                            },
                        },
                        xaxis: {
                            categories: [${labels}], // Nhãn cho biểu đồ
                        }
                    };

                    var chart = new ApexCharts(document.querySelector("#line_chart"), options);
                    chart.render();
                });
            </script>
        </c:if>
                
    </body>
</html>

