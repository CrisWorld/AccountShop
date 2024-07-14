/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Order;
import untils.OrderDAO;

/**
 *
 * @author CHUC DY
 */
public class ShowOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders;
        try {
            orders = orderDAO.getAllOrders();
        } catch (Exception e) {
            // Handle the exception
            e.printStackTrace();
            return;
        }
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("client/detail.jsp").forward(request, response);
    }
}
