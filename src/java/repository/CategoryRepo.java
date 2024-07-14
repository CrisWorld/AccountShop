/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import models.Category;
import models.Database;


/**
 *
 * @author PC
 */
public class CategoryRepo {
  
    private final static String GET_ALL_CATEGORY = "select * from product_categories;";
    private final static String SELECT_CATEGORY_BY_ID_SQL = "select * from product_categories where id = ?;";
 
    
    public List<Category> findAll(){
        List<Category> categories = new ArrayList<>();
        Category category;
        try {
            Statement statement = Database.getConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_CATEGORY);
            while(resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("desc"));
                
                categories.add(category);
            }
        } catch (SQLException e) {
        }
        
        return categories;
    }

    public Category findCategoryById(int id){
        Category category;
        try {
            PreparedStatement preparedStatement = Database.getConnect().prepareStatement(SELECT_CATEGORY_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            category = new Category();
            category.setId(resultSet.getInt("id"));
            category.setName(resultSet.getString("name"));
            category.setDescription(resultSet.getString("desc"));
        } catch (SQLException e) {
           throw new RuntimeException();
        }
        return category;
    }
   
    
}
