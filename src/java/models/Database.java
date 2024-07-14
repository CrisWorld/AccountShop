
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
    
    public static ArrayList<Contact> listAllContact() {
        ArrayList<Contact> list = new ArrayList<>();
        try (Connection con = getConnect()) {
            PreparedStatement stmt = con.prepareStatement("SELECT id, username, message, created_at FROM contacts");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4).toLocalDateTime()));
            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
