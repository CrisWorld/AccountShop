/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.RevenueData;
import repository.StaticRepo;

/**
 *
 * @author PC
 */
@WebServlet(name = "StaticServlet", urlPatterns = {"/admin/static"})
public class StaticServlet extends HttpServlet {

    private final static StaticRepo staticRepo = new StaticRepo();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
        HashMap<String, Integer> categoryData = staticRepo.getStatic();
        List<RevenueData> revenueDataList = staticRepo.getMonthlyRevenue();
        
        request.setAttribute("categoryData", categoryData);
        request.setAttribute("revenueData", revenueDataList);
        request.getRequestDispatcher("/admin/chart/chart.jsp").forward(request, response);
        
    }
}
