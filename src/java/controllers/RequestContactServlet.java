package controllers;

import models.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestContactServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        // Insert into database using Database class
//     //   boolean success = Database.insertContact(name, message);
//
//        if (success) {
//            // Forward to admin.jsp if insertion was successful
//            response.sendRedirect("/client/contact.html");
//        } else {
//            // Handle failure scenario, possibly redirect to an error page
//            response.sendRedirect(request.getContextPath() + "/error.jsp");
//        }
//    }
    }
}
