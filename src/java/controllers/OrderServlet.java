/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Order;
import models.OrderItem;
import models.Product;
import models.User;
import repository.OrderRepo;
import untils.CartDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "OrderServlet", urlPatterns = {"/client/order"})
public class OrderServlet extends HttpServlet {

    private final static OrderRepo orderRepo = new OrderRepo();
    private final static CartDAO cartDAO = new CartDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
//        request.getRequestDispatcher("/client/checkout.jsp").forward(request, response);
//        response.sendRedirect("/client/checkout");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("client") == null) {
            response.sendRedirect("/auth/client");
        } else {
        
            User user = (User) session.getAttribute("client");
            Cart cart = cartDAO.getCartById(user.getCartId());
            List<Product> res = cart.getProducts();

            double total_amount = Double.parseDouble(request.getParameter("total"));
            
            // create order
            Order order = new Order();
            order.setStatus("approve");
            order.setTotal_amount(total_amount);
            order.setUser(user);
            int orderID = orderRepo.createOrder(order);
      
            if (orderID > 0) { 
                // order_items
                OrderItem orderItem;
                
                for (Product p : res) {
                    orderItem = new OrderItem();

                    orderItem.setOrder(order);
                    orderItem.setProduct(p);
                    orderItem.setPrice(p.getPrice());
                    orderItem.setQuantity(1);
                    
                    orderRepo.addOrderItem(orderItem, orderID);   
                }
            }
              
            cartDAO.clearAllProductsFromCart(cart.getId());
            
//        request.getRequestDispatcher("/client/checkout.jsp").forward(request, response);
            response.sendRedirect("/client/checkout");
        }
        
    }
    
    public static void main(String[] args) {
//        System.out.println(orderItems.size());

    }
    
}
