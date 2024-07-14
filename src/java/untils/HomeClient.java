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
import repository.CategoryRepo;

public class HomeClient {
    private final static CategoryRepo categoryRepo = new CategoryRepo();

    public static List<Product> Search(String title, int page, int pageSize) throws SQLException {
        List<Product> list = new ArrayList<>();
        try (Connection con = Database.getConnect()) {
            String condition = "SELECT * FROM products WHERE title LIKE ? ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            PreparedStatement stmt = con.prepareStatement(condition);
            stmt.setString(1, "%" + title + "%");
            int offset = (page - 1) * pageSize;
            stmt.setInt(2, offset);
            stmt.setInt(3, pageSize);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getString("title"), rs.getString("image"), rs.getInt("quantity"), rs.getDouble("discount_percentage"), rs.getString("status"), rs.getDouble("price"), null, rs.getString("slug"), rs.getString("desc"), rs.getString("short_desc"), rs.getString("secret_info"), rs.getString("meta_title"), rs.getString("meta_description"), rs.getString("meta_keyword")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public static int getTotalRecords(String title) throws SQLException {
    int totalRecords = 0;
    try (Connection con = Database.getConnect()) {
        String countQuery = "SELECT COUNT(*) FROM products WHERE title LIKE ?";
        PreparedStatement countStmt = con.prepareStatement(countQuery);
        countStmt.setString(1, "%" + title + "%");
        ResultSet countRs = countStmt.executeQuery();
        if (countRs.next()) {
            totalRecords = countRs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(HomeClient.class.getName()).log(Level.SEVERE, null, ex);
    }
    return totalRecords;
}


    public static void main(String[] args) throws SQLException {
//        int page = 1;
//        int pageSize = 10;  
//        List<Product> list = Search("Wireless", page, pageSize);
//        
//        for (Product product : list) {
//            System.out.println(product.toString());
//        }
    }
}
