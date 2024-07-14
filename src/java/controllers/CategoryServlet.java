package controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class CategoryServlet extends HttpServlet {
    
    private final static CategoryRepo categoryList = new CategoryRepo();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "create" -> showCreateForm(request, response);
            case "edit" -> showEditForm(request, response);
            default -> listCategory(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "";
        }
        
        switch (action) {
            case "create" -> doCreate(request, response);
            case "edit" -> doEdit(request, response);
            case "delete" -> deleteById(request, response);
        }
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("id"));
        Category category = categoryList.findById(categoryId);
        request.setAttribute("category", category);
        
        // filter parent category
        List<Category> categories = categoryList.findAll();
        List<Category> categoriesParent = new ArrayList<Category>();
        
        for(Category cate: categories){
            if(cate.getParentId() == null) categoriesParent.add(cate);
        }
        
        request.setAttribute("categories", categoriesParent);
        request.getRequestDispatcher("/admin/category/editCategory.jsp").forward(request, response);
    }
    
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // filter parent category
        List<Category> categories = categoryList.findAll();
        List<Category> categoriesParent = new ArrayList<Category>();
        
        for(Category category: categories){
            if(category.getParentId() == null) categoriesParent.add(category);
        }
        
        request.setAttribute("categories", categoriesParent);
        request.getRequestDispatcher("/admin/category/createCategory.jsp").forward(request, response);
    }

    private void deleteById(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryList.deleteById(id);
        response.sendRedirect("/admin/category");
    }

    private void listCategory(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Category> cateList = categoryList.findAll();
        request.setAttribute("listCate", cateList);
        request.getRequestDispatcher("/admin/category/list-category.jsp").forward(request, response);
    }
    
//    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String banner = doUploadFile(request);
//        String description = request.getParameter("desc");
//        int parentID = Integer.parseInt(request.getParameter("id"));
//
//        Category category = new Category(name, banner, description, parentID);
//        categoryList.add(category);
//        response.sendRedirect("/admin/category");
//    }
    private void doCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String banner = doUploadFile(request);
        String description = request.getParameter("desc");

        String parentIDStr = request.getParameter("parent_id");
        Integer parentID = (parentIDStr != null && !parentIDStr.equals("") ) ? Integer.parseInt(parentIDStr) : null;

        Category category = new Category(name, banner, description, parentID);
        categoryList.add(category);
        response.sendRedirect("/admin/category");
    }
    
    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        String banner = doUploadFile(request);
        if(banner.equals("")){
            banner = categoryList.findCategoryById(id).getBanner();
        }
        String description = request.getParameter("desc");
        String parentIDStr = request.getParameter("parent_id");
        Integer parentID = (parentIDStr != null && !parentIDStr.isEmpty()) ? Integer.parseInt(parentIDStr) : null;
        
//        Part part = request.getPart("img");
//        if (part != null && part.getSize() > 0) {
//            banner = doUploadFile(request);
//        }

        Category category = new Category(name, banner, description, parentID);
        category.setId(id);
        categoryList.edit(category);
        response.sendRedirect("/admin/category");
    }    
    
    private String doUploadFile(HttpServletRequest request) {
        String img = "";
        try {
            Part part = request.getPart("img");
            String realPath = request.getServletContext().getRealPath("images/category");
            String fileName = Path.of(part.getSubmittedFileName()).getFileName().toString();
            if(fileName.equals("")){
                throw new Exception("File is null");
            }
            if (!Files.exists(Path.of(realPath))) {
                Files.createDirectories(Path.of(realPath));              
            }
            img = "../images/category/" + fileName;
            part.write(realPath + "/" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
    
    @Override
    public String getServletInfo() {
        return "Category Management Servlet";
    }
}
