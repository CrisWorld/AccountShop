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
import models.OrderItem;
import models.Product;
import models.User;
import repository.OrderItemRepo;
import repository.OrderRepo;
import untils.CartDAO;

/**
 *
 * @author PC
 */
@WebServlet(name = "HistoryServlet", urlPatterns = {"/client/history"})
public class HistoryServlet extends HttpServlet {

    private final static CartDAO cartDAO = new CartDAO();
    private final static OrderItemRepo orderItemRepo = new OrderItemRepo();

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
            List<OrderItem> res = orderItemRepo.findAll(user.getUsername());

            request.setAttribute("list", res);

            request.getRequestDispatcher("/client/history.jsp").forward(request, response);
        }
    }
    
}
