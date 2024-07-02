/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;
import java.sql.*;
import models.Database;

import models.User;

/**
 *
 * @author quoch
 */
public class Account {
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO users (username, password, fullname, image, isAdmin, role_id, cart_id) VALUES (?, ?, ?, ?, 1, ?, ?)";
    private static final String UPDATE_ACCOUNT_SQL = "UPDATE users SET password = ?, fullname = ?, image = ?, isAdmin = ?, role_id = ?, cart_id = ? WHERE username = ?";
    private static final String DELETE_ACCOUNT_SQL = "DELETE FROM users WHERE username = ?";
    public boolean create(User user) throws SQLException{
        try{
            Connection con = Database.getConnect();
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setString(4, user.getImage());
            preparedStatement.setInt(5, user.getRoleId());
            preparedStatement.setInt(6, user.getCartId());
            preparedStatement.executeUpdate();
            return true;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean updateAccount(User user) {
        try (Connection connection = Database.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL)) {
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getFullname());
            preparedStatement.setString(3, user.getImage());
            preparedStatement.setBoolean(4, true);
            preparedStatement.setInt(5, user.getRoleId());
            preparedStatement.setInt(6, user.getCartId());
            preparedStatement.setString(7, user.getUsername());
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteAccount(String username) {
        try (Connection connection = Database.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_SQL)) {
            preparedStatement.setString(1, username);
            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
