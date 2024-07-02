/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import classes.Notification;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import untils.Account;

/**
 *
 * @author quoch
 */
public class AccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
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
            int role_id = Integer.parseInt(request.getParameter("role_id"));
//            String username, String password, String fullname, String image, boolean isAdmin, int roleId, int cartId
            User user = new User(username, password, fullName, "image", true, role_id, null);
            if(accountService.create(user)){

            } else {
                
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
            int role_id = Integer.parseInt(request.getParameter("role_id"));
//            String username, String password, String fullname, String image, boolean isAdmin, int roleId, int cartId
            User user = new User(username, password, fullName, "image", true, role_id, null);
            if(accountService.updateAccount(user)){

            } else {
                
            }
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
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

}
