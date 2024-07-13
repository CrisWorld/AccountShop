/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Contact;
import untils.Contact_Admin;

/**
 *
 * @author thaip
 */
public class ContactServlet extends HttpServlet {

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
     protected void doShowContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Contact_Admin contact = new Contact_Admin();
        List<Contact> listContact = contact.Display_Contact();
        request.setAttribute("messages", listContact);
        request.getRequestDispatcher("ContactView.jsp").forward(request, response);
    }
    
    protected void doDeleteContact(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Contact_Admin contact = new Contact_Admin();
        
        int id = Integer.parseInt(request.getParameter("delete_contact"));
        contact.delete_Contact(id);
        List<Contact> listContact = contact.Display_Contact();
        request.setAttribute("messages", listContact);
        request.getRequestDispatcher("ContactView.jsp").forward(request, response);
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
        if(method.equals("Delete_contact")){
            try {
                doDeleteContact(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(ContactServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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