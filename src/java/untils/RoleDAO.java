/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Database;
import models.Role;
/**
 *
 * @author quoch
 */
public class RoleDAO {
    private static final String SELECT_ALL_ROLES_SQL = "SELECT id, name FROM roles";
    public static List<Role> getAllRoles() {
        List<Role> roles = new ArrayList<>();

        try (Connection connection = Database.getConnect();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROLES_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int roleId = rs.getInt("id");
                String roleName = rs.getString("name");

                Role role = new Role(roleId, roleName);
                roles.add(role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }
}
