
package models;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import repository.CategoryRepo;
import repository.ProductRepo;


public class Database implements DatabaseInfo{
    public static Connection getConnect(){
        try{ 
            Class.forName(DRIVERNAME); 
	} catch(ClassNotFoundException e) {
            System.out.println("Error loading driver" + e);
	}
        try{            
            Connection con = DriverManager.getConnection(DBURL,USERDB,PASSDB);
            return con;
        }
        catch(SQLException e) {
            System.out.println("Error: " + e);
        }
        return null;
    }
    
    public static void main(String[] a) {
//        ArrayList<Contact> list = MainRun.listAllContact();
//        for (Contact item : list) {
//            System.out.println(item);
//        }
        List<Category> listCategory = CategoryRepo.findAll();
        for (Category item : listCategory) {
            System.out.println(item);
        }
        
//        List<Product> listProduct = ProductRepo.listAllProduct();
//        for (Product item : listProduct) {
//            System.out.println(item);
//        }
    }
    
    
    
    public static boolean insertContact(String name, String message) {
        String sql = "INSERT INTO contacts (username, message) VALUES (?, ?)";
        try (Connection con = getConnect();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, message);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
