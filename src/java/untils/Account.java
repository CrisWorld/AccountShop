/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Database;

import models.User;

/**
 *
 * @author quoch
 */
public class Account {
    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO users (username, password, fullname, image, isAdmin, role_id) VALUES (?, ?, ?, ?, 1, ?)";
    private static final String UPDATE_ACCOUNT_SQL = "UPDATE users SET password = ?, fullname = ?, image = ?, isAdmin = ?, role_id = ?, cart_id = ? WHERE username = ?";
    private static final String UPDATE_ACCOUNT_WITHOUTPASSWORD_SQL = "UPDATE users SET fullname = ?, image = ?, isAdmin = ?, role_id = ?, cart_id = ? WHERE username = ?";
    private static final String DELETE_ACCOUNT_SQL = "DELETE FROM users WHERE username = ?";
    private static final String SELECT_ACCOUNT_SQL = "SELECT users.username, users.fullname, users.image, users.role_id, roles.name AS role_name " +
                                                    "FROM users " +
                                                    "LEFT JOIN roles ON users.role_id = roles.id " +
                                                    "WHERE users.isAdmin = 1;";
    private static final String SELECT_ACCOUNT_BY_USERNAME_SQL = "SELECT users.username, users.fullname, users.image, users.role_id, users.cart_id, users.isAdmin, roles.name AS role_name " +
                                                    "FROM users " +
                                                    "LEFT JOIN roles ON users.role_id = roles.id " +
                                                    "WHERE users.username = ?;";
    public static boolean create(User user) throws SQLException{
        try{
            Connection con = Database.getConnect();
            PreparedStatement preparedStatement = con.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFullname());
            preparedStatement.setString(4, user.getImage());
            if(user.getRoleId() == null) preparedStatement.setNull(5, java.sql.Types.INTEGER);
            else preparedStatement.setInt(5, user.getRoleId());
            preparedStatement.executeUpdate();
            return true;
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    public static boolean updateAccount(User user) {
        try {
            Connection connection = Database.getConnect();
            PreparedStatement preparedStatement;
            if(user.getPassword() != null) {
                preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL);
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getFullname());
                preparedStatement.setString(3, user.getImage());
                preparedStatement.setBoolean(4, true);
                if(user.getRoleId() != null) preparedStatement.setInt(5, user.getRoleId());
                else preparedStatement.setNull(5, java.sql.Types.INTEGER);
                if(user.getCartId() != null) preparedStatement.setInt(6, user.getCartId());
                else preparedStatement.setNull(6, java.sql.Types.INTEGER);
                preparedStatement.setString(7, user.getUsername());
            }
            else {
                preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_WITHOUTPASSWORD_SQL);
                preparedStatement.setString(1, user.getFullname());
                preparedStatement.setString(2, user.getImage());
                preparedStatement.setBoolean(3, true);
                if(user.getRoleId() != null) preparedStatement.setInt(4, user.getRoleId());
                else preparedStatement.setNull(4, java.sql.Types.INTEGER);
                if(user.getCartId() != null) preparedStatement.setInt(5, user.getCartId());
                else preparedStatement.setNull(5, java.sql.Types.INTEGER);
                preparedStatement.setString(6, user.getUsername());
            }
            
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean deleteAccount(String username) {
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
    
     public static List<User> getAdminUsersWithRoles() {
        List<User> accounts = new ArrayList<>();

        try (Connection connection = Database.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String fullname = rs.getString("fullname");
                String image = rs.getString("image");
                Integer roleId = rs.getObject("role_id", Integer.class);
                String roleName = rs.getString("role_name");
                accounts.add(new User(username, "", fullname, image, true, roleId, 0, roleName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
     
     public static User getAccountByUserName(String username){
         User account = null;
         try (Connection connection = Database.getConnect();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_USERNAME_SQL)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String fullname = rs.getString("fullname");
                String image = rs.getString("image");
                Integer cart_id = rs.getObject("cart_id") == null ? null : (Integer) rs.getObject("cart_id");
                Integer roleId = rs.getObject("role_id", Integer.class);
                boolean isAdmin = rs.getBoolean("isAdmin");
                String roleName = rs.getString("role_name");
                account = new User(username, "", fullname, image, isAdmin, roleId, cart_id, roleName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
     }
}
