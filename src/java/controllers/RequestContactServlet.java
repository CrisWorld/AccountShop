package controllers;

import models.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        try {
            boolean success = Database.insertContact(name, message);

            if (success) {
                response.sendRedirect(request.getContextPath() + "/client/contact.jsp?message=Message+sent+successfully!");
            } else {
                response.sendRedirect(request.getContextPath() + "/client/contact.jsp?error=Send+message+failed+!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/client/contact.jsp?error=An+unexpected+error+occurred.+Please+try+again+later.");
        }
    }
}
