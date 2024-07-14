/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Cart;
import models.Product;
import models.User;
import repository.ProductRepo;
import untils.CartDAO;

/**
 *
 * @author quoch
 */
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartDAO cartRepo = new CartDAO();
        HttpSession session = request.getSession();
        User client = (User) session.getAttribute("client");
        if(client.getCartId() == null){
            Cart cart = cartRepo.createCartIfNotExist(client.getUsername());
            System.out.println(cart.getId());
            client.setCartId(cart.getId());
            session.setAttribute("client", client);
        }
        Cart cart = cartRepo.getCartById(client.getCartId());
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/client/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
        if(method == null){
            doCreate(request, response);
        } else if(method.equalsIgnoreCase("delete")){
            try {
                doRemoveProductFromCart(request, response);
            } catch (Exception ex) {
                Logger.getLogger(CartServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
    
    protected void doRemoveProductFromCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        HttpSession session = request.getSession();
        User client = (User) session.getAttribute("client");
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        CartDAO cartRepo = new CartDAO();
        if(client.getCartId() != null) {
            cartRepo.clearProductFromCart(client.getCartId(), product_id);
            response.sendRedirect("/cart");
        }
        else throw new Exception("Cart id is null");
    }

    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User client = (User) session.getAttribute("client");
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        CartDAO cartRepo = new CartDAO();
        if(client.getCartId() == null){
            Cart cart = cartRepo.createCartIfNotExist(client.getUsername());
            client.setCartId(cart.getId());
            session.setAttribute("client", client);
        }
        ProductRepo productRepo = new ProductRepo();
        Product product = productRepo.findProductById(product_id);
        if(product != null && product.getQuantity() > 0){
            cartRepo.addProductToCart(client.getCartId(), product_id, 1);
            response.sendRedirect("/cart");
        } else response.sendRedirect("/");
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
