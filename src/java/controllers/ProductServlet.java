/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Category;
import models.Product;
import repository.CategoryRepo;
import repository.ProductRepo;

/**
 *
 * @author PC
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet(urlPatterns = "/admin/product", name = "ProductServlet")
public class ProductServlet extends HttpServlet {
    
    private final static ProductRepo productRepo = new ProductRepo();
    private final static CategoryRepo categoryRepo = new CategoryRepo();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String action = request.getParameter("action");
        
        if (action == null) {
            action ="";
        }
        
        switch (action) {
            case "create" -> showCreateForm(request, response);
            case "edit" -> showEditForm(request, response);
            default -> list(request, response);
        }
    }
    
    /*============================================ Authenticate =============================================================================*/
//    private boolean isAdmin(User user) {
//        return user.getRole().stream().anyMatch(role -> "ADMIN".equals(role.getName()));
//    }
//    
//    private User getUserInSession(HttpServletRequest request){
//        return (User) request.getSession().getAttribute("user");
//    }
//    
    
    /*============================================ GET METHOD =============================================================================*/
    // search method in list method
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        /* for search */
        String titleSearch = request.getParameter("titleSearch");
        String priceStartSearch = request.getParameter("priceStartSearch");
        String priceEndSearch = request.getParameter("priceEndSearch");
        String categorySearch = request.getParameter("categorySearch");
        
        if (titleSearch == null || titleSearch.isBlank()) {
            titleSearch = "";
        } 
        
        if (categorySearch == null || categorySearch.isBlank()) {
            categorySearch = "";
        } else {
            Category category = categoryRepo.findCategoryById(Integer.parseInt(categorySearch));
            request.setAttribute("category", category);
        }
        
        if (priceStartSearch == null || priceStartSearch.isBlank()) {
            request.setAttribute("priceStartSearch", "");    
            priceStartSearch = "0";
        } else request.setAttribute("priceStartSearch", priceStartSearch);    
        
        if (priceEndSearch == null || priceEndSearch.isBlank()) {
            request.setAttribute("priceEndSearch", "");
            priceEndSearch = "99999999";
        } else request.setAttribute("priceEndSearch", priceEndSearch);
        
        /* for pagination */
        int page = 1;
        int recordsPerPage = 5;
        int noOfRecords = productRepo.getNoOfRecords(titleSearch, priceStartSearch, priceEndSearch);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
             
        /* for get list */
        List<Product> res;
        res = productRepo.findAllProduct((page - 1) * recordsPerPage,recordsPerPage, titleSearch, priceStartSearch, priceEndSearch, categorySearch);
        
        if(!res.isEmpty()) {
            if (page < 1) {
                response.sendRedirect("/admin/product?page=1");

            } else if (page > noOfPages) {
               response.sendRedirect("/admin/product?page=" + noOfPages);
            }
        }      
        
        /* for pagination */
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("list", res);
        request.setAttribute("categories", categoryRepo.findAll());
        
        /** for search **/
        request.setAttribute("titleSearch", titleSearch);
        request.setAttribute("categorySearch", categorySearch);
        
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request, response);
    }

    private void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        productRepo.deleteProductById(id);
    
        response.sendRedirect("/admin/product");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));        
 
        Product product = productRepo.findProductById(id);
        request.setAttribute("product", product);
        request.setAttribute("categories", categoryRepo.findAll());
        request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
        
    }
    
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setAttribute("categories", categoryRepo.findAll());
        request.getRequestDispatcher("/admin/product/edit.jsp").forward(request, response);
    }   
    
    /*============================================ POST METHOD =============================================================================*/
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action ="";
        }
        
        switch (action) {
            case "create" -> doCreate(request, response);
            case "edit" -> doEdit(request, response);
            case "delete" -> deleteById(request, response);
        }
    }
    
    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
        // upload file
        String img = doUploadFile(request);
       
        String title = request.getParameter("title");
        String slug = request.getParameter("slug");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        double discount_percentage = Double.parseDouble(request.getParameter("discount"));
        String status = request.getParameter("status");
        String short_desc = request.getParameter("short_desc");  
        String desc = request.getParameter("description");  
        int categoryID = Integer.parseInt(request.getParameter("category"));
        Category category = categoryRepo.findCategoryById(categoryID);
      
        String secret_info = request.getParameter("secret_info");  
        String meta_title = request.getParameter("meta_title");  
        String meta_keyword = request.getParameter("meta_keywords");
        String meta_description = request.getParameter("meta_description"); 
        
        Product product = new Product(title, img, quantity, discount_percentage, status, price, category, slug, desc, short_desc, secret_info, meta_title, meta_description, meta_keyword);
//           Product product = new Product(img, title, slug, quantity, price, secret_info);   
        productRepo.add(product);
        
        response.sendRedirect("/admin/product");
    }
    
    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
        
            int id = Integer.parseInt(request.getParameter("product_id"));
            
            String default_img = request.getParameter("default_img");
            String img;
            Part part = request.getPart("img");
            if (part.getSize() == 0) {
                img = default_img;
            } else {
                img = doUploadFile(request);
            }
            
            String title = request.getParameter("title");
            String slug = request.getParameter("slug");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            double price = Double.parseDouble(request.getParameter("price"));
            double discount_percentage = Double.parseDouble(request.getParameter("discount"));
            String status = request.getParameter("status");
            String short_desc = request.getParameter("short_desc");  
            String desc = request.getParameter("description");  
            int categoryID = Integer.parseInt(request.getParameter("category"));
            Category category = categoryRepo.findCategoryById(categoryID);

            String secret_info = request.getParameter("secret_info");  
            String meta_title = request.getParameter("meta_title");  
            String meta_keyword = request.getParameter("meta_keywords");
            String meta_description = request.getParameter("meta_description"); 


            Product product = new Product(id, title, img, quantity, discount_percentage, status, price, category, slug, desc, short_desc, secret_info, meta_title, meta_description, meta_keyword);

            productRepo.edit(product);

        } catch (NumberFormatException e) {
            
        }

        response.sendRedirect("/admin/product");
        
    }    
    
    // ====================================
    private String doUploadFile(HttpServletRequest request){
        
        // upload file
        String img = "";
        try {
            Part part = request.getPart("img");
            String realPart = request.getServletContext().getRealPath("images/product");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
            if (!Files.exists(Path.of(realPart))) {
                Files.createDirectories(Path.of(realPart));              
            }
            img = "../images/product/" + fileName;
            part.write(realPart + "/" + fileName);
        }catch (Exception e) {
        }
        return img;
    }
}
