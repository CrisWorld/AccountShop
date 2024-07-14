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
import javax.servlet.http.HttpSession;
import models.User;
import untils.Authentication;
import untils.CartDAO;

/**
 *
 * @author quoch
 */
public class ClientAuthentication extends HttpServlet {

    private final static CartDAO cartDao = new CartDAO();
            
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
        String action = request.getParameter("type");
        if(action == null) request.getRequestDispatcher("/client/auth/login.jsp").forward(request, response);
        else if(action.equals("register")) request.getRequestDispatcher("/client/auth/register.jsp").forward(request, response);
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
        if(request.getParameter("action").equals("login")){
            doLogin(request, response);
        } else if (request.getParameter("action").equals("logout")){
            doLogout(request, response);
        } else if(request.getParameter("action").equals("register")){
            doRegister(request, response);
        }
    }
    
    protected void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username != null && password != null){
            User user = Authentication.loginUser(username, password);
            if(user == null) response.sendRedirect("/auth/client");
            else {
                if (user.getCartId() == null) {
                    user.setCartId(cartDao.createCartIfNotExist(user.getUsername()).getId());
                }
                if(!user.isIsAdmin()){
                    response.sendRedirect("/client/checkout");
                    
                    session.setAttribute("client", user);
                    response.setContentType("text/html; charset=UTF-8");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print("user được lưu vào thuộc tính 'client' của session");
                } else response.sendRedirect("/auth/client");
            }
        }
    }
    
    
    protected void doLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("client", null);
        response.sendRedirect("/auth/client");
    }

        
    protected void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullname = request.getParameter("fullname");
        System.out.println(fullname);
        if(fullname != null && password != null && username != null && Authentication.registerUser(username, password, fullname)){
            response.sendRedirect("/auth/client");
        } else {
            response.sendRedirect("/auth/client?type=register");
        }
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
