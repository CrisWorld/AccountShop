/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;
import java.sql.*;
import java.util.ArrayList;
import models.Cart;
import models.Category;
import models.Database;
import models.Order;
import models.Product;
/**
 *
 * @author quoch
 */
public class DashboardDAO {
    public int getTotalOrder(){
        String sql = """
                        SELECT COUNT(*) AS total
                        FROM orders;
                     """;
        try (Connection connection = Database.getConnect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalOrder(String status){
        String sql = """
                        SELECT COUNT(*) AS total
                        FROM orders WHERE status = ?;
                     """;
        try (Connection connection = Database.getConnect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getToltalUser(){
        String sql = """
                        SELECT COUNT(*) AS total
                        FROM users
                     """;
        try (Connection connection = Database.getConnect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getToltalUser(boolean isAdmin){
        String sql = """
                        SELECT COUNT(*) AS total
                        FROM users
                        WHERE isAdmin = ?;
                     """;
        try (Connection connection = Database.getConnect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, isAdmin);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getToltalProduct(){
        String sql = """
                        SELECT COUNT(*) AS total
                        FROM products;
                     """;
        try (Connection connection = Database.getConnect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("total");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public ArrayList<Order> getOrderLastest(int number){
        String sql = """
                        SELECT id, username, order_date, total_amount, status
                        FROM orders
                        ORDER BY order_date DESC
                        OFFSET 0 ROWS FETCH NEXT ? ROWS ONLY;
                     """;
        ArrayList<Order> orders = new ArrayList<Order>();
        try (Connection connection = Database.getConnect();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, number);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setUsername(rs.getString("username"));
                order.setTotal_amount(rs.getDouble("total_amount"));
                order.setStatus(rs.getString("status"));
                order.setOrderDate(rs.getDate("order_date"));
                orders.add(order);
            };
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
