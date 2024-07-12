/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Database;
import models.Product;
/**
 *
 * @author PC
 */
public class ProductRepo {
    private int noOfRecords;

    private final static String GET_ALL_PRODUCT_SQL = """
                                                      select * from products where status != ?
                                                      ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
                                                    """;
    private final static String DELETE_PRODUCT_BY_ID_SQL = "update products set status = ? where id = ?";
    private final static String INSERT_NEW_PRODUCT_SQL = """ 
                                                         insert into products (category_id,title,image,quantity,slug,
                                                                             discount_percentage,status,[desc],short_desc,
                                                                            price,meta_title,meta_description,meta_keyword,secret_info)
                                                                            values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                                                         """;
    private final static String GET_PRODUCT_BY_ID_SQL = "select * from products where id = ?";
    private final static String UPDATE_PRODUCT_BY_ID_SQL = """
                                                           update products 
                                                           set  category_id = ?,
                                                                title = ?,
                                                           	image = ?,
                                                           	quantity = ?,
                                                           	slug = ?,
                                                           	discount_percentage = ?,
                                                                status = ?,
                                                           	[desc] = ?,
                                                                short_desc = ?,
                                                                price = ?,
                                                                meta_title = ?,
                                                                meta_description = ?,
                                                                meta_keyword = ?,
                                                                secret_info = ?
                                                           where id = ?
                                                           """;
    private final static CategoryRepo categoryRepo = new CategoryRepo();
    
    public List<Product> findAllProduct(int offset, int recordsPerPage) {
        List<Product> products = new ArrayList<>();
        Product product;
        try {
            PreparedStatement preparedStatement = Database.getConnect().prepareStatement(GET_ALL_PRODUCT_SQL);
            preparedStatement.setString(1, "deleted");
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, recordsPerPage);
//            preparedStatement.setString(2, "%" + key + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setTitle(resultSet.getString("title"));
                product.setDiscount_percentage(resultSet.getDouble("discount_percentage"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setStatus(resultSet.getString("status"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategory(categoryRepo.findCategoryById(resultSet.getInt("category_id")));
                product.setImg(resultSet.getString("image"));
                products.add(product);
            }
            
        } catch (SQLException e) {
        }
        return products;
    }

    public boolean add(Product product) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(INSERT_NEW_PRODUCT_SQL);
            preparedStatement.setInt(1, product.getCategory().getId());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setString(3, product.getImg());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getSlug());
            preparedStatement.setDouble(6, product.getDiscount_percentage());
            preparedStatement.setString(7, product.getStatus());
            preparedStatement.setString(8, product.getDesc());
            preparedStatement.setString(9, product.getShort_desc());
            preparedStatement.setDouble(10, product.getPrice());
            preparedStatement.setString(11, product.getMeta_title());
            preparedStatement.setString(12, product.getMeta_description());
            preparedStatement.setString(13, product.getMeta_keyword());
            preparedStatement.setString(14, product.getSecret_info());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    
    public void deleteProductById(int id) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(DELETE_PRODUCT_BY_ID_SQL);
            preparedStatement.setString(1, "deleted");
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        } catch (SQLException e) {
        }
    }

    public Product findProductById(int id) {
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(GET_PRODUCT_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setTitle(resultSet.getString("title"));
                product.setDiscount_percentage(resultSet.getDouble("discount_percentage"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setStatus(resultSet.getString("status"));
                product.setPrice(resultSet.getDouble("price"));
                product.setCategory(categoryRepo.findCategoryById(resultSet.getInt("category_id")));
                product.setImg(resultSet.getString("image"));
               
                product.setShort_desc(resultSet.getString("short_desc"));
                product.setDesc(resultSet.getString("desc"));

                product.setSlug(resultSet.getString("slug"));
                product.setMeta_title(resultSet.getString("meta_title"));
                product.setMeta_keyword(resultSet.getString("meta_keyword"));
                product.setMeta_description(resultSet.getString("meta_description"));
                product.setSecret_info(resultSet.getString("secret_info"));
            }
        } catch (SQLException e) {
        }
        return product;
    }

    public boolean edit(Product product) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(UPDATE_PRODUCT_BY_ID_SQL);
            preparedStatement.setInt(1, product.getCategory().getId());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setString(3, product.getImg());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getSlug());
            preparedStatement.setDouble(6, product.getDiscount_percentage());
            preparedStatement.setString(7, product.getStatus());
            preparedStatement.setString(8, product.getDesc());
            preparedStatement.setString(9, product.getShort_desc());
            preparedStatement.setDouble(10, product.getPrice());
            preparedStatement.setString(11, product.getMeta_title());
            preparedStatement.setString(12, product.getMeta_description());
            preparedStatement.setString(13, product.getMeta_keyword());
            preparedStatement.setString(14, product.getSecret_info());
            
            preparedStatement.setInt(15, product.getId());

            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
   
    
    public int getNoOfRecords() {
        try{
         // Getting the total number of records
            String countQuery = "SELECT COUNT(*) FROM products";
            Statement countStmt = Database.getConnect().createStatement();
            ResultSet countRs = countStmt.executeQuery(countQuery);
            if (countRs.next()) {
                this.noOfRecords = countRs.getInt(1);
            }
        } catch (SQLException e) {}
        return noOfRecords;
    }
}
