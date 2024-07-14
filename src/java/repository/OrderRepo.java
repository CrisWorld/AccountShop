/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.*;
import models.Database;
import models.Order;
import models.OrderItem;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Category;
import models.Product;
import untils.Account;
/**
 *
 * @author PC
 */
public class OrderRepo {
    
    private final static String INSERT_NEW_ORDER_SQL = """
                                                      INSERT INTO orders (username, total_amount, status) VALUES
                                                      (?, ?, ?);
                                                    """;
    
    private final static String INSERT_NEW_ORDER_ITEMS_SQL = """
                                                      INSERT INTO order_items (order_id, product_id, quantity, price) VALUES
                                                      (?, ?, ?, ?);
                                                    """;
                
    private final static String SELECT_ORDER_BY_ID_SQL = "select * from orders where id = ?;";
    
    private final static String SELECT_ALL_ORDER_SQL = "select * from orders;";
 
    
  
    public int createOrder(Order order) {
        String sql = "{CALL AddOrder(?, ?, ?, ?, ?)}";
        try {
            CallableStatement stmt = Database.getConnect().prepareCall(sql);

            stmt.setString(1, order.getStatus());
            stmt.setDouble(2, order.getTotal_amount());
            stmt.setString(3, order.getUser().getUsername());
            stmt.setString(4, order.getImage());
            stmt.registerOutParameter(5, Types.INTEGER);

            stmt.execute();

            return stmt.getInt(5);
        } catch (SQLException e) {
            return -1;
        }
    }
     
    public boolean addOrderItem(OrderItem orderItem, int orderID) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(INSERT_NEW_ORDER_ITEMS_SQL);
            
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, orderItem.getProduct().getId());
            preparedStatement.setInt(3, orderItem.getQuantity());
            preparedStatement.setDouble(4, orderItem.getPrice());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public Order findOrderById(int id){
        Order order;
        try {
            PreparedStatement preparedStatement = Database.getConnect().prepareStatement(SELECT_ORDER_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order = new Order();
            order.setId(resultSet.getInt("id"));
            order.setImage(resultSet.getString("image"));
            order.setUsername(resultSet.getString("username"));
            order.setUser(Account.getAccountByUserName(order.getUsername()));
            order.setTotal_amount(resultSet.getDouble("total_amount"));
            order.setStatus(resultSet.getString("status"));
            order.setOrderDate(resultSet.getDate("order_date"));
        } catch (SQLException e) {
           throw new RuntimeException();
        }
        return order;
    }
    
    public Order findOrderIncludeItems(int id){
        String sqlOrderItems = "SELECT p.*, oi.quantity as oi_quantity FROM order_items oi JOIN products p ON oi.product_id = p.id WHERE oi.order_id = ?";
        
        try (Connection connection = Database.getConnect();
            PreparedStatement orderItemsStmt = connection.prepareStatement(sqlOrderItems)) {
            orderItemsStmt.setInt(1, id);
            List<Product> products = new ArrayList<>();
            ResultSet orderItemsRs = orderItemsStmt.executeQuery();

            while (orderItemsRs.next()) {
                Product product = new Product();
                product.setId(orderItemsRs.getInt("id"));
                product.setTitle(orderItemsRs.getString("title"));
                product.setImg(orderItemsRs.getString("image"));
                product.setQuantity(orderItemsRs.getInt("oi_quantity"));
                product.setDiscount_percentage(orderItemsRs.getDouble("discount_percentage"));
                product.setStatus(orderItemsRs.getString("status"));
                product.setPrice(orderItemsRs.getDouble("price"));
                product.setSlug(orderItemsRs.getString("slug"));
                product.setDesc(orderItemsRs.getString("desc"));
                product.setShort_desc(orderItemsRs.getString("short_desc"));
                products.add(product);
            }

            Order order = findOrderById(id);
            order.setOrderItems((ArrayList<Product>) products);
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public ArrayList<Order> findAll(){
        ArrayList<Order> orders = new ArrayList<Order>();
        try{
            PreparedStatement preparedStatement = Database.getConnect().prepareStatement(SELECT_ALL_ORDER_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setImage(resultSet.getString("image"));
                order.setUsername(resultSet.getString("username"));
                order.setTotal_amount(resultSet.getDouble("total_amount"));
                order.setStatus(resultSet.getString("status"));
                order.setOrderDate(resultSet.getDate("order_date"));
                orders.add(order);
            };
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return orders;
    }
    
    public void updateOrderStatus(int orderId, String newStatus) {
        String sql = "UPDATE orders SET status = ? WHERE id = ?";
        
        try (Connection connection = Database.getConnect();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
             
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
