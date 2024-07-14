/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.HashMap;
import models.Database;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.RevenueData;
/**
 *
 * @author PC
 */
public class StaticRepo {
     
    
    public HashMap<String, Integer> getStatic(){
        
        String sql = "SELECT pc.name AS category_name, COUNT(o.id) AS order_count " +
                     "FROM orders o " +
                     "JOIN order_items oi ON o.id = oi.order_id " +
                     "JOIN products p ON oi.product_id = p.id " +
                     "JOIN product_categories pc ON p.category_id = pc.id " +
                     "GROUP BY pc.name";
        
        HashMap<String, Integer> categoryData = new HashMap<>();
        
        try {
            PreparedStatement statement = Database.getConnect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("category_name");
                int orderCount = resultSet.getInt("order_count");
                categoryData.put(categoryName, orderCount);
            }
        } catch (SQLException e) {
            
        }

        return categoryData;
    }
    
    public List<RevenueData> getMonthlyRevenue() {
        String sql = "SELECT YEAR(order_date) AS year, MONTH(order_date) AS month, SUM(total_amount) AS monthly_revenue " +
                     "FROM orders " +
                     "GROUP BY YEAR(order_date), MONTH(order_date) " +
                     "ORDER BY year, month";
        
        List<RevenueData> revenueDataList = new ArrayList<>();
        
        try {
            PreparedStatement statement = Database.getConnect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int year = resultSet.getInt("year");
                int month = resultSet.getInt("month");
                double monthlyRevenue = resultSet.getDouble("monthly_revenue");
                RevenueData revenueData = new RevenueData(year, month, monthlyRevenue);
                revenueDataList.add(revenueData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenueDataList;
    }
    
}
