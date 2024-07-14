/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Order;
import repository.OrderRepo;


public class AdminOrderServlet extends HttpServlet {


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            doList(request, response);
        } else if(action.equalsIgnoreCase("detail")){
            doDetail(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        int order_id = Integer.parseInt(request.getParameter("id"));
        OrderRepo orderRepo = new OrderRepo();
        orderRepo.updateOrderStatus(order_id, status);
        response.sendRedirect("/admin/order?action=detail&id="+order_id);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderRepo orderRepo = new OrderRepo();
        ArrayList<Order> orders = orderRepo.findAll();
        System.out.println(orders.size());
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/admin/order/order-list.jsp").forward(request, response);
    }
    
    protected void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderRepo orderRepo = new OrderRepo();
        int order_id = Integer.parseInt(request.getParameter("id"));
        System.out.println("order_id: "+order_id);
        Order order = orderRepo.findOrderIncludeItems(order_id);
        System.out.println(order.getOrderItems().size());
        request.setAttribute("order", order);
        request.getRequestDispatcher("/admin/order/details-order.jsp").forward(request, response);
    }
   
}
