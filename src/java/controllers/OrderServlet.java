/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.Cart;
import models.Order;
import models.OrderItem;
import models.Product;
import models.User;
import repository.OrderRepo;
import repository.ProductRepo;
import untils.CartDAO;

/**
 *
 * @author PC
 */

@MultipartConfig(maxFileSize = 16177215)
@WebServlet(name = "OrderServlet", urlPatterns = {"/client/order"})
public class OrderServlet extends HttpServlet {

    private final static OrderRepo orderRepo = new OrderRepo();
    private final static CartDAO cartDAO = new CartDAO();
    private final static ProductRepo productRepo = new ProductRepo();
    
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
        boolean isSuccess = true;
        if (session == null || session.getAttribute("client") == null) {
            response.sendRedirect("/auth/client");
        } else {
        
            User user = (User) session.getAttribute("client");
            Cart cart = cartDAO.getCartById(user.getCartId());
            List<Product> res = cart.getProducts();
            if (res.isEmpty()) {
                request.setAttribute("message", "Cart is empty !!!");
                request.getRequestDispatcher("/client/checkout.jsp").forward(request, response);
            }
            
            // CHECK PRODUCT IS AVAILABLE
            for (Product p : res) {
                Product pReal = productRepo.findProductById(p.getId());
                if (!pReal.getStatus().equals("showing")) {
                    isSuccess = false;
                    session.setAttribute("showToast", isSuccess);        
                    response.sendRedirect("/client/history");
                }          
            }
            
            double total_amount = Double.parseDouble(request.getParameter("total"));
            String img = doUploadFile(request);
            
            // create order
            Order order = new Order();
            order.setStatus("processing");
            order.setTotal_amount(total_amount);
            order.setUser(user);
            order.setImage(img);
            
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
                    
                    boolean addSuccess = orderRepo.addOrderItem(orderItem, orderID);
                    if (!addSuccess) {
                        isSuccess = false;
                        continue;
                    }
                    
                    productRepo.updateStatusAfterOrder(p.getId());
                }
                
                if (isSuccess) {
                    cartDAO.clearAllProductsFromCart(cart.getId());
                }
            }
            
            session.setAttribute("showToast", isSuccess);        
            response.sendRedirect("/client/history");
        }
        
    }

    // ====================================
    private String doUploadFile(HttpServletRequest request){
        
        // upload file
        String img = "";
        try {
            Part part = request.getPart("transaction");
            String realPart = request.getServletContext().getRealPath("images/transaction");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
            if (!Files.exists(Path.of(realPart))) {
                Files.createDirectories(Path.of(realPart));              
            }
            img = "../images/transaction/" + fileName;
            part.write(realPart + "/" + fileName);
        }catch (Exception e) {
        }
        return img;
    }
    
}
