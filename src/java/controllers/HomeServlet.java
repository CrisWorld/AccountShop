package controllers;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Product;

import models.Category;
import models.Product;
import repository.CategoryRepo;
import repository.ProductRepo;

import untils.HomeClient;

public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equals("Search")) {
            try {

                doShowContact(request, response);

                doShowSearchResult(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                showHome(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    protected void doShowContact(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
    String infoSearch = request.getParameter("searchInfo");
    String pageParam = request.getParameter("page");
    String pageSizeParam = request.getParameter("pageSize");
    
    int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1; 
    int pageSize = (pageSizeParam != null) ? Integer.parseInt(pageSizeParam) : 3; 

    int totalRecords = HomeClient.getTotalRecords(infoSearch);
    
    int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

    List<Product> list = HomeClient.Search(infoSearch, page, pageSize);
    request.setAttribute("listSearch", list);
    request.setAttribute("currentPage", page);
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("totalPages", totalPages);
    
    request.getRequestDispatcher("search.jsp").forward(request, response);
}

    protected void doShowSearchResult(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        String infoSearch = request.getParameter("searchInfo");
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");

        int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1; 
        int pageSize = (pageSizeParam != null) ? Integer.parseInt(pageSizeParam) : 6; 

        int totalRecords = HomeClient.getTotalRecords(infoSearch);

        int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

        List<Product> list = HomeClient.Search(infoSearch, page, pageSize);
        request.setAttribute("listSearch", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("/client/search.jsp").forward(request, response);
    }
    
    protected void showHome(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
        ProductRepo productRepo = new ProductRepo();
//        CategoryRepo categoryRepo = new CategoryRepo();
//        request.setAttribute("categoriesShow", (ArrayList<Category>) categoryRepo.findAll());
        request.setAttribute("products", productRepo.findAllProduct(0, 8, "", "0", "999999999", ""));
        request.getRequestDispatcher("/client/index.jsp").forward(request, response);
    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

