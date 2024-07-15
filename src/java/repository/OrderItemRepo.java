/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Database;
import models.OrderItem;

/**
 *
 * @author PC
 */
public class OrderItemRepo {
    
    private final static ProductRepo productRepo = new ProductRepo();
    private final static OrderRepo orderRepo = new OrderRepo();
    
    private final static String GET_ALL_ORDER_ITEMS = """
                                                      select * from order_items as oi
                                                      join orders as o on oi.order_id = o.id
                                                      where username = ?;                                                
                                                      """;
    
    
    public List<OrderItem> findAll(String username){
        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem;
        try {
            PreparedStatement preparedStatement = Database.getConnect().prepareStatement(GET_ALL_ORDER_ITEMS);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                orderItem = new OrderItem();
                orderItem.setProduct(productRepo.findProductById(resultSet.getInt("product_id")));
                orderItem.setOrder(orderRepo.findOrderById(resultSet.getInt("order_id")));
                orderItem.setPrice(resultSet.getDouble("price"));
                orderItem.setQuantity(resultSet.getInt("quantity"));
                orderItems.add(orderItem);
            }
        } catch (SQLException e) {
        }
        
        return orderItems;
    }
    
}
