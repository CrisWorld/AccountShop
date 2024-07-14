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
        if(slug == null){
            List<Product> productList = productRepo.findAllProduct(0, 10, "", "0", "9999999999", "");
            request.setAttribute("productList", productList);
            System.out.println(productList.size());
            request.getRequestDispatcher("/client/displayall.jsp").forward(request, response);
        } else {
            Product product = productRepo.findProductBySlug(slug);
            request.setAttribute("product", product);
            request.getRequestDispatcher("/client/detailproduct.jsp").forward(request, response);  
        }
        
    }

}
