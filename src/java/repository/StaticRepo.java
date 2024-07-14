/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.HashMap;
import models.Database;
import java.sql.*;
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
}
