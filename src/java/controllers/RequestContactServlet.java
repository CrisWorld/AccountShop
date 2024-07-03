package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

public class RequestContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String message = request.getParameter("message");

        request.setAttribute("name", name);
        request.setAttribute("email", email);
        request.setAttribute("title", title);
        request.setAttribute("message", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/request-contact.jsp");
        dispatcher.forward(request, response);
    }
}
