/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;
import repository.ProductRepo;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Category;
import repository.CategoryRepo;


/**
 *
 * @author quoch
 */
public class ClientProductServlet extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductRepo productRepo = new ProductRepo();
        String slug = request.getParameter("slug");  
    }
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRepo productRepo = new ProductRepo();
        String slug = request.getParameter("slug");
        String action = request.getParameter("action");
        if(slug == null && action == null){
            List<Product> productList = productRepo.findAllProduct(0, 10, "", "0", "9999999999", "");
            request.setAttribute("productList", productList);
            System.out.println(productList.size());
            request.getRequestDispatcher("/client/displayall.jsp").forward(request, response);
        } else if(action != null && action.equals("category")){
            try {
                showByCategory(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ClientProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Product product = productRepo.findProductBySlug(slug);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/client/detailproduct.jsp").forward(request, response);  
        }
        
    }
     
    protected void showByCategory(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        ProductRepo productRepo = new ProductRepo();
        String categoryId = request.getParameter("category");
        if(categoryId == null) response.sendRedirect("/home");
        else {
            CategoryRepo categoryRepo = new CategoryRepo();
            Category category = categoryRepo.findCategoryById(Integer.parseInt(categoryId));
            request.setAttribute("category", category);
            request.setAttribute("productList", productRepo.findAllProduct(0, 8, "", "0", "999999999", categoryId));
            request.getRequestDispatcher("/client/displayall.jsp").forward(request, response);
        }
    }

}
