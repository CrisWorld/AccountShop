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
    
    
    
    public static void main(String[] args) {
        DashboardDAO dao = new DashboardDAO();
        System.out.println(dao.getToltalProduct());
    }
}
