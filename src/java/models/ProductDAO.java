package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    //you can you this 
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public ProductDAO() {
        connection = Database.getConnect();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCategoryId(resultSet.getInt("category_id"));
            product.setTitle(resultSet.getString("title"));
            product.setImage(resultSet.getString("image"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setType(resultSet.getString("type"));
            product.setSlug(resultSet.getString("slug"));
            product.setDiscountPercentage(resultSet.getFloat("discount_percentage"));
            product.setStatus(resultSet.getString("status"));
            product.setDescription(resultSet.getString("desc"));
            product.setShortDescription(resultSet.getString("short_desc"));
            product.setPrice(resultSet.getFloat("price"));
            product.setMetaTitle(resultSet.getString("meta_title"));
            product.setMetaDescription(resultSet.getString("meta_description"));
            product.setMetaKeyword(resultSet.getString("meta_keyword"));
            product.setSecretInfo(resultSet.getString("secret_info"));
            products.add(product);
        }
        return products;
    }

    public List<Product> getProductsByCategory(int categoryId) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, categoryId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCategoryId(resultSet.getInt("category_id"));
            product.setTitle(resultSet.getString("title"));
            product.setImage(resultSet.getString("image"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setType(resultSet.getString("type"));
            product.setSlug(resultSet.getString("slug"));
            product.setDiscountPercentage(resultSet.getFloat("discount_percentage"));
            product.setStatus(resultSet.getString("status"));
            product.setDescription(resultSet.getString("desc"));
            product.setShortDescription(resultSet.getString("short_desc"));
            product.setPrice(resultSet.getFloat("price"));
            product.setMetaTitle(resultSet.getString("meta_title"));
            product.setMetaDescription(resultSet.getString("meta_description"));
            product.setMetaKeyword(resultSet.getString("meta_keyword"));
            product.setSecretInfo(resultSet.getString("secret_info"));
            products.add(product);
        }
        return products;
    }

    public List<Product> getProductsByPriceRange(float minPrice, float maxPrice) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE price BETWEEN ? AND ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setFloat(1, minPrice);
        statement.setFloat(2, maxPrice);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCategoryId(resultSet.getInt("category_id"));
            product.setTitle(resultSet.getString("title"));
            product.setImage(resultSet.getString("image"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setType(resultSet.getString("type"));
            product.setSlug(resultSet.getString("slug"));
            product.setDiscountPercentage(resultSet.getFloat("discount_percentage"));
            product.setStatus(resultSet.getString("status"));
            product.setDescription(resultSet.getString("desc"));
            product.setShortDescription(resultSet.getString("short_desc"));
            product.setPrice(resultSet.getFloat("price"));
            product.setMetaTitle(resultSet.getString("meta_title"));
            product.setMetaDescription(resultSet.getString("meta_description"));
            product.setMetaKeyword(resultSet.getString("meta_keyword"));
            product.setSecretInfo(resultSet.getString("secret_info"));
            products.add(product);
        }
        return products;
    }

    public Product getProductDetail(int productId) throws SQLException {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setCategoryId(resultSet.getInt("category_id"));
            product.setTitle(resultSet.getString("title"));
            product.setImage(resultSet.getString("image"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setType(resultSet.getString("type"));
            product.setSlug(resultSet.getString("slug"));
            product.setDiscountPercentage(resultSet.getFloat("discount_percentage"));
            product.setStatus(resultSet.getString("status"));
            product.setDescription(resultSet.getString("desc"));
            product.setShortDescription(resultSet.getString("short_desc"));
            product.setPrice(resultSet.getFloat("price"));
            product.setMetaTitle(resultSet.getString("meta_title"));
            product.setMetaDescription(resultSet.getString("meta_description"));
            product.setMetaKeyword(resultSet.getString("meta_keyword"));
            product.setSecretInfo(resultSet.getString("secret_info"));
        }
        return product;
    }
}
