package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EgiKarina
 */
public class OrderDAO {

    private String url;
    private String username;
    private String password;
    
    //url is the database's name, username and password is your 
    public OrderDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password); PreparedStatement statement = connection.prepareStatement("SELECT o.id, o.username, o.order_date, o.voucher_id, o.total_amount, o.status, "
                + "u.fullname, v.code AS voucher_code, v.discount_percentage "
                + "FROM orders o "
                + "LEFT JOIN users u ON o.username = u.username "
                + "LEFT JOIN vouchers v ON o.voucher_id = v.id")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    order.setId(resultSet.getInt("id"));
                    order.setUsername(resultSet.getString("username"));
                    order.setOrderDate(resultSet.getTimestamp("order_date"));
                    order.setVoucherId(resultSet.getInt("voucher_id"));
                    order.setTotalAmount(resultSet.getDouble("total_amount"));
                    order.setStatus(resultSet.getString("status"));
                    order.setFullname(resultSet.getString("fullname"));
                    order.setVoucherCode(resultSet.getString("voucher_code"));
                    order.setDiscountPercentage(resultSet.getDouble("discount_percentage"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
        }

        return orders;
    }

    public void updateOrder(Order order) {
        try (Connection connection = Database.getConnect(); PreparedStatement statement = connection.prepareStatement("UPDATE orders SET username = ?, order_date = ?, voucher_id = ?, total_amount = ?, status = ? WHERE id = ?")) {

            statement.setString(1, order.getUsername());
            statement.setTimestamp(2, order.getOrderDate());
            statement.setInt(3, order.getVoucherId());
            statement.setDouble(4, order.getTotalAmount());
            statement.setString(5, order.getStatus());
            statement.setInt(6, order.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteOrder(int orderId) {
        try (Connection connection = Database.getConnect(); PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?")) {

            statement.setInt(1, orderId);

            statement.executeUpdate();

            // Delete related order items
            try (PreparedStatement orderItemsStatement = connection.prepareStatement("DELETE FROM order_items WHERE order_id = ?")) {
                orderItemsStatement.setInt(1, orderId);
                orderItemsStatement.executeUpdate();
            }
        } catch (SQLException e) {
        }
    }
}
