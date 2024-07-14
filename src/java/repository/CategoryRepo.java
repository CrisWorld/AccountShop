package repository;

import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import models.Category;
import models.Database;

public class CategoryRepo {
  
    private final static String GET_ALL_CATEGORY = "SELECT * FROM product_categories;";
    private final static String SELECT_CATEGORY_BY_ID_SQL = "SELECT * FROM product_categories WHERE id = ?;";
    private final static String INSERT_NEW_CATEGORY_SQL = """
                                                            INSERT INTO product_categories (name, banner, [desc], parent_id)
                                                            VALUES (?, ?, ?, ?)
                                                            """;
    private final static String UPDATE_CATEGORY_BY_ID_SQL = """
                                                            UPDATE product_categories
                                                            SET name = ?, banner = ?, [desc] = ?, parent_id = ?
                                                            WHERE id = ?;
                                                            """;
    private final static String DELETE_CATEGORY_BY_ID_SQL = "DELETE FROM product_categories WHERE id = ?;";
    
    public static List<Category> findAll() {
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
                category.setBanner(resultSet.getString("banner"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }   

    public Category findCategoryById(int id) {
        Category category = null;
        try {
            PreparedStatement preparedStatement = Database.getConnect().prepareStatement(SELECT_CATEGORY_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("desc"));
                category.setBanner(resultSet.getString("banner"));
                category.setParentId(resultSet.getObject("parent_id")==null ? null : resultSet.getInt("parent_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return category;
    }
    
    public void add(Category category) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(INSERT_NEW_CATEGORY_SQL);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getBanner());
            preparedStatement.setString(3, category.getDescription());
            if(category.getParentId()!= null){
                preparedStatement.setInt(4, category.getParentId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void edit(Category category) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(UPDATE_CATEGORY_BY_ID_SQL);
            preparedStatement.setString(1, category.getName());
            preparedStatement.setString(2, category.getBanner());
            preparedStatement.setString(3, category.getDescription());
            if(category.getParentId()!= null){
                preparedStatement.setInt(4, category.getParentId());
            } else {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            }
            preparedStatement.setInt(5, category.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = Database.getConnect()
                    .prepareStatement(DELETE_CATEGORY_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Category findById(int id) {
        return findCategoryById(id);
    }
}