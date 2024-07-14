<%-- 
    Document   : toGetDetail
    Created on : Jul 14, 2024, 12:53:37 PM
    Author     : CHUC DY
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Show Detail</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/ProductServlet" method="post">
            <input type="text" name="slug" placeholder="Enter product slug">
            <input type="submit" value="Invoke Servlet">
        </form>
        <form action="${pageContext.request.contextPath}/ProductServlet" method="get">

            <input type="submit" value="Invoke Servlet">
        </form>
    </body>
</html>
