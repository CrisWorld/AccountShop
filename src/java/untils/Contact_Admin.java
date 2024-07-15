package untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Contact;
import models.Database;

public class Contact_Admin {

    private final String showContact = "SELECT * FROM contacts ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    private final String deleteContact = "DELETE FROM contacts WHERE id = ?";
    private final int PAGE_SIZE = 5; // Số bản ghi trên mỗi trang

    public List<Contact> Display_Contact(int page) throws SQLException {
        List<Contact> list = new ArrayList<>();
        int start = (page - 1) * PAGE_SIZE;

        try (Connection con = Database.getConnect();
             PreparedStatement stmt = con.prepareStatement(showContact)) {

            stmt.setInt(1, start);
            stmt.setInt(2, PAGE_SIZE);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                list.add(new Contact(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));

            }
        }
        return list;
    }

    public int getTotalPages() throws SQLException {
        int totalRecords = 0;
        String query = "SELECT COUNT(*) FROM contacts";
        try (Connection con = Database.getConnect();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                totalRecords = rs.getInt(1);
            }
        }
        return (int) Math.ceil(totalRecords / (double) PAGE_SIZE);
    }

    public void delete_Contact(int id) {
        try (Connection con = Database.getConnect();
             PreparedStatement stmt = con.prepareStatement(deleteContact)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Log lỗi hoặc xử lý lỗi theo cách khác
        }
    }
    public static void main(String[] args) throws SQLException {
        Contact_Admin contact = new Contact_Admin();
        List<Contact> list = contact.Display_Contact(1);
        for (Contact contact1 : list) {
            System.out.println(contact1.toString());
        }
    }
}
