/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.Role;
import models.User;
import untils.Account;
import untils.RoleDAO;

/**
 *
 * @author quoch
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AccountServlet extends HttpServlet {
    private static final String UPLOAD_AVATAR_DIR = "uploads\\user\\avatar";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if("create".equalsIgnoreCase(action)){
            ArrayList<Role> roles = (ArrayList<Role>) RoleDAO.getAllRoles();
            request.setAttribute("roles", roles); 
            request.getRequestDispatcher("/admin/account/create.jsp").forward(request, response);
        } if("edit".equalsIgnoreCase(action)) {
            String username = request.getParameter("username");
            if(username == null) response.sendRedirect("/admin/account");
            else {
                User account = Account.getAccountByUserName(username);
                if(account != null){
                    ArrayList<Role> roles = (ArrayList<Role>) RoleDAO.getAllRoles();
                    request.setAttribute("roles", roles);
                    request.setAttribute("account", account);
                    request.getRequestDispatcher("/admin/account/edit.jsp").forward(request, response);
                } else {
                    response.sendRedirect("/admin/account");
                }
            }
        } else {
            ArrayList<User> accounts = (ArrayList<User>) Account.getAdminUsersWithRoles();
            request.setAttribute("accounts", accounts);
            request.getRequestDispatcher("/admin/account/list.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
        System.out.println("method: "+method);
        if(method == null){
            doCreate(request, response);
        } else if(method.equals("put")) {
            doUpdate(request, response);
        } else if(method.equals("delete")){
            doDelete(request, response);
        }
    }
    
    protected void doCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account accountService = new Account();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullname");
            String image = "/admin/assets/images/users/avatar-1.jpg";
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_AVATAR_DIR;
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            // Lấy các phần của file
            for (Part part : request.getParts()) {
                String contentDisp = part.getHeader("content-disposition");
                if (contentDisp != null && contentDisp.contains("filename")) {
                    String mimeType = getFileExtension(extractFileName(part));
                    if(!mimeType.equals("")){
                        String fileName = username+"."+mimeType;
                        part.write(uploadFilePath + File.separator + fileName);
                        image = File.separator + UPLOAD_AVATAR_DIR + File.separator + fileName;
                    }
                }
            }
            
            
            Integer roleId;
            if(request.getParameter("role_id") == null || request.getParameter("role_id").equals("")) roleId = null;
            else {
                roleId = Integer.parseInt(request.getParameter("role_id"));
            }
//            String username, String password, String fullname, String image, boolean isAdmin, int roleId, int cartId
            User user = new User(username, password, fullName, image, true, roleId, null);
            if(accountService.create(user)){
                response.sendRedirect("/admin/account");
            } else {
                response.sendRedirect("/admin/account?action=create");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    protected void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account accountService = new Account();
        try{
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullname");
            User userToGetImage = Account.getAccountByUserName(username);
            if(userToGetImage == null) throw new Exception("User not iound");
            String image = userToGetImage.getImage();
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_AVATAR_DIR;
            File uploadDir = new File(uploadFilePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            // Lấy các phần của file
            for (Part part : request.getParts()) {
                String contentDisp = part.getHeader("content-disposition");
                if (contentDisp != null && contentDisp.contains("filename")) {
                    String mimeType = getFileExtension(extractFileName(part));
                    if(!mimeType.equals("")){
                        String fileName = username+"."+mimeType;
                        part.write(uploadFilePath + File.separator + fileName);
                        image = File.separator + UPLOAD_AVATAR_DIR + File.separator + fileName;
                    }
                }
            }
            
            
            Integer role_id;
            if(request.getParameter("role_id") == null || request.getParameter("role_id").equals("")) role_id = null;
            else role_id = Integer.parseInt(request.getParameter("role_id"));
//            String username, String password, String fullname, String image, boolean isAdmin, int roleId, int cartId
            User user = new User(username, password, fullName, image, true, role_id, null);
            if(accountService.updateAccount(user)){
                System.out.println("Updated user thanh cong");
            }
            response.sendRedirect("/admin/account?action=edit&username="+username);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account accountService = new Account();
        try{
            String username = request.getParameter("username");
            if(accountService.deleteAccount(username)){

            } else {
                
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String fileName = s.substring(s.indexOf("=") + 1).trim().replace("\"", "");
                return fileName.replaceAll(".*[\\\\/]", ""); // Đảm bảo chỉ lấy tên tệp
            }
        }
        return "";
    }
    
    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
    
    
    public static void main(String[] args) {
//        System.out.println("Nguyễn Minh Hoàng QUốc");
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

}
