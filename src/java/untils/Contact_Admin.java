/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Contact;
import models.Database;

/**
 *
 * @author thaip
 */
public class Contact_Admin {
    private final String showContact = "Select * from contacts";
    private final String deleteContact = "DELETE FROM contacts where id = ?";
    public List<Contact> Display_Contact() throws SQLException{
        List<Contact> list = new ArrayList<>();
        try (Connection con = Database.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(showContact);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                list.add(new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
            }
        }catch (SQLException ex) {
            Logger.getLogger(Contact_Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public void delete_Contact(int id){
        try(Connection con = Database.getConnect()) {
            PreparedStatement stmt = con.prepareStatement(deleteContact);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(Contact_Admin.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public static void main(String[] args) throws SQLException {
        Contact_Admin contact = new Contact_Admin();
     
        List<Contact> list = contact.Display_Contact();
        for (Contact contact1 : list) {
            System.out.println(contact1.toString());
        }
        
    }
}
