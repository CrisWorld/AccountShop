/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import models.Database;
import models.Order;
import models.OrderItem;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import models.Category;
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
            order.setTotal_amount(resultSet.getDouble("total_amount"));
            order.setStatus(resultSet.getString("status"));
            order.setOrderDate(resultSet.getDate("order_date"));
        } catch (SQLException e) {
           throw new RuntimeException();
        }
        return order;
    }

}
