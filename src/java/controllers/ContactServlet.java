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
        doShowContact(request, response);
    }

    
     protected void doShowContact(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        // Tạo đối tượng Contact_Admin
        Contact_Admin contactAdmin = new Contact_Admin();
        
        // Xử lý phân trang
        int page = 1; // Trang mặc định
        int recordsPerPage = 10; // Số bản ghi mỗi trang
        
        // Lấy tham số trang từ request
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1; // Nếu có lỗi khi phân tích tham số, quay lại trang mặc định
            }
        }
        
        // Lấy danh sách liên hệ cho trang hiện tại
        List<Contact> listContact = contactAdmin.Display_Contact(page);
        
        // Tính toán tổng số trang
        int totalPages = contactAdmin.getTotalPages();
        
        // Điều chỉnh trang nếu cần
        if (page < 1) {
            page = 1; // Trang không thể nhỏ hơn 1
        } else if (page > totalPages) {
            page = totalPages; // Trang không thể lớn hơn tổng số trang
        }
        
        // Thiết lập các thuộc tính cho JSP
        request.setAttribute("messages", listContact);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        
        // Chuyển tiếp đến JSP
        request.getRequestDispatcher("ContactView.jsp").forward(request, response);
    } catch (SQLException e) {
        e.printStackTrace(); // Hoặc xử lý lỗi theo cách khác
        // Có thể chuyển hướng đến một trang lỗi hoặc hiển thị thông báo lỗi
        request.setAttribute("errorMessage", "An error occurred while fetching contact data.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}


    
    protected void doDeleteContact(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException, SQLException {
    // Tạo đối tượng Contact_Admin
    Contact_Admin contactAdmin = new Contact_Admin();

    // Lấy ID của liên hệ cần xóa từ tham số yêu cầu
    int id = Integer.parseInt(request.getParameter("delete_contact"));

    // Xóa liên hệ khỏi cơ sở dữ liệu
    contactAdmin.delete_Contact(id);

    // Lấy tham số trang hiện tại từ yêu cầu (hoặc mặc định là trang 1 nếu không có)
    int page = 1; // Trang mặc định nếu không có tham số
    String pageParam = request.getParameter("page");
    if (pageParam != null && !pageParam.isEmpty() && !pageParam.equals("1")) {
        try {
            page = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            page = 1; // Nếu có lỗi khi phân tích tham số, quay lại trang mặc định
        }
    }

    // Lấy tổng số liên hệ và số trang hiện tại sau khi xóa
    List<Contact> listContact = contactAdmin.Display_Contact(page);
    int totalPages = contactAdmin.getTotalPages();

    // Nếu trang hiện tại lớn hơn tổng số trang, đặt trang thành tổng số trang
    if (page > totalPages) {
        page = totalPages;
    }

    // Nếu không còn liên hệ nào, quay về trang trước đó hoặc trang 1
    if (totalPages == 0) {
        page = 1;
    }

    // Cập nhật danh sách liên hệ cho trang hiện tại
    listContact = contactAdmin.Display_Contact(page);

    // Thiết lập các thuộc tính cho JSP
    request.setAttribute("messages", listContact);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);

    // Chuyển tiếp đến JSP để hiển thị danh sách liên hệ đã cập nhật
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
