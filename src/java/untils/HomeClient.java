/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Database;
import models.Product;

/**
 *
 * @author thaip
 */
public class HomeClient {
    public static List<Product> Search(String title) throws  SQLException {
        List<Product> list = new ArrayList<>();
        try( Connection con = Database.getConnect()){
            
            String condition = "SELECT * FROM products WHERE title LIKE ?";
            PreparedStatement stmt = con.prepareStatement(condition);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getString("title"), rs.getString("image"), rs.getInt("quantity"), rs.getDouble("discount_percentage"), rs.getString("status"), rs.getDouble("price"), null, rs.getString("slug"), rs.getString("desc"), rs.getString("short_desc"), rs.getString("secret_info"), rs.getString("meta_title"), rs.getString("meta_description"), rs.getString("meta_keyword")));
            }
        }catch(SQLException ex ){
            Logger.getLogger(HomeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
