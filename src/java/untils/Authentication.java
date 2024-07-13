/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;

import models.Database;
import java.sql.*;
import models.User;

/**
 *
 * @author quoch
 */
public class Authentication {
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";
        private static final String INSERT_CLIENT_SQL = "INSERT INTO users (username, password, fullname, isAdmin) VALUES (?, ?, ?, 0)";

    public static User loginUser(String username, String password) {
        User user = null;
        try {
            Connection connection = Database.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("fullname"));
                user.setImage(rs.getString("image"));
                user.setIsAdmin(rs.getBoolean("isAdmin"));
                user.setRoleId(rs.getInt("role_id"));
                user.setCartId(rs.getInt("cart_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    
    public static boolean registerUser(String username, String password, String fullname) {
        try {
            Connection connection = Database.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, fullname);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
