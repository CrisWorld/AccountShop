/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Product;
import untils.ProductDAO;

/**
 *
 * @author CHUC DY
 */
public class ProductServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entered doPost method");

        String slug = request.getParameter("slug");
        System.out.println("Received slug parameter: " + slug);

        try {
            ProductDAO productDAO = new ProductDAO();
            System.out.println("Created ProductDAO instance");

            Product product = productDAO.getProductBySlug(slug);
            System.out.println("Fetched product from DAO: " + product);

            request.setAttribute("product", product);
            System.out.println("Set product attribute in request");

            request.getRequestDispatcher("client/detailproduct.jsp").forward(request, response);
            System.out.println("Forwarded request to detailproduct.jsp");
        } catch (SQLException ex) {
            System.out.println("SQLException occurred: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getAllProducts();
            request.setAttribute("productList", productList);
            request.getRequestDispatcher("/client/displayall.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
