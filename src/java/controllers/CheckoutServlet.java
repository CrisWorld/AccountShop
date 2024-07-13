/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Product;
import models.User;
import untils.CartDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "CheckoutServlet", urlPatterns = {"/client/checkout"})
public class CheckoutServlet extends HttpServlet {

    private final static CartDAO cartDAO = new CartDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String action = request.getParameter("action");
        
        if (action == null) {
            action ="";
        }
        
        switch (action) {
            default -> list(request, response);
        }
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("client") == null) {
            response.sendRedirect("/auth/client");
        } else {
        
            User user = (User) session.getAttribute("client");
            Cart cart = cartDAO.getCartById(user.getCartId());
            List<Product> res = cart.getProducts();
            double subTotal = cartDAO.getCartTotal(cart.getId());
            double discount = 0;
            double total = subTotal - discount;

            request.setAttribute("list", res);
            request.setAttribute("subtotal", subTotal);
            request.setAttribute("discount", discount);
            request.setAttribute("total", total);

            request.getRequestDispatcher("/client/checkout.jsp").forward(request, response);
        }
    }
    
    
}
