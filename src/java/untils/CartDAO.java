/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package untils;
import java.sql.*;
import java.util.ArrayList;
import models.Cart;
import models.Category;
import models.Database;
import models.Product;

public class CartDAO {

    public Cart getCartById(int cartId) {
        Cart cart = new Cart();
        String sqlCart = "SELECT * FROM carts WHERE id = ?";
        String sqlProducts = "SELECT p.*, cp.quantity as cp_quantity FROM products p INNER JOIN cart_products cp ON p.id = cp.product_id WHERE cp.cart_id = ?";

        try (Connection connection = Database.getConnect()) {
            // Get Cart details
            try (PreparedStatement cartStmt = connection.prepareStatement(sqlCart)) {
                cartStmt.setInt(1, cartId);
                ResultSet cartRs = cartStmt.executeQuery();
                if (cartRs.next()) {
                    cart.setId(cartRs.getInt("id"));
                    cart.setCreatedAt(cartRs.getString("created_at"));
                    cart.setUpdatedAt(cartRs.getString("updated_at"));
                }
            }

            // Get Products in Cart
            try (PreparedStatement productsStmt = connection.prepareStatement(sqlProducts)) {
                productsStmt.setInt(1, cartId);
                ResultSet productsRs = productsStmt.executeQuery();
                while (productsRs.next()) {
                    Category category = productsRs.getObject("category_id") == null ? new Category(productsRs.getInt("category_id")) : new Category();
                    Product product = new Product(
                        productsRs.getInt("id"),
                        productsRs.getString("title"),
                        productsRs.getString("image"),
                        productsRs.getInt("cp_quantity"),
                        productsRs.getDouble("discount_percentage"),
                        productsRs.getString("status"),
                        productsRs.getDouble("price"),
                        category,
                        productsRs.getString("slug"),
                        productsRs.getString("desc"),
                        productsRs.getString("short_desc"),
                        productsRs.getString("secret_info"),
                        productsRs.getString("meta_title"),
                        productsRs.getString("meta_description"),
                        productsRs.getString("meta_keyword")
                    );
                    System.out.println("vao day");
                    cart.getProducts().add(product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cart;
    }

    public void clearProductFromCart(int cartId, int productId) {
        String sql = "DELETE FROM cart_products WHERE cart_id = ? AND product_id = ?";

        try (Connection connection = Database.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.setInt(2, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearAllProductsFromCart(int cartId) {
        String sql = "DELETE FROM cart_products WHERE cart_id = ?";

        try (Connection connection = Database.getConnect();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, cartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // this method is not use.
//    public void updateProductQuantityInCart(int cartId, int productId, int quantity) {
//        String sql = "UPDATE cart_products SET quantity = ? WHERE cart_id = ? AND product_id = ?";
//
//        try (Connection connection = Database.getConnect();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, quantity);
//            statement.setInt(2, cartId);
//            statement.setInt(3, productId);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void addProductToCart(int cartId, int productId, int quantity) {
        String sqlCheck = "SELECT * FROM cart_products WHERE cart_id = ? AND product_id = ?";
        String sqlInsert = "INSERT INTO cart_products (cart_id, product_id, quantity) VALUES (?, ?, ?)";
        String sqlUpdate = "UPDATE cart_products SET quantity = quantity + ? WHERE cart_id = ? AND product_id = ?";

        try (Connection connection = Database.getConnect()) {
            try (PreparedStatement checkStmt = connection.prepareStatement(sqlCheck)) {
                checkStmt.setInt(1, cartId);
                checkStmt.setInt(2, productId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    try (PreparedStatement updateStmt = connection.prepareStatement(sqlUpdate)) {
                        updateStmt.setInt(1, quantity);
                        updateStmt.setInt(2, cartId);
                        updateStmt.setInt(3, productId);
                        updateStmt.executeUpdate();
                    }
                } else {
                    try (PreparedStatement insertStmt = connection.prepareStatement(sqlInsert)) {
                        insertStmt.setInt(1, cartId);
                        insertStmt.setInt(2, productId);
                        insertStmt.setInt(3, quantity);
                        insertStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cart createCartIfNotExist(String username) {
        String sqlInsertCart = "INSERT INTO carts DEFAULT VALUES";
        String sqlUpdateUser = "UPDATE users SET cart_id = ? WHERE username = ?";

        try (Connection connection = Database.getConnect()) {
            // Tạo giỏ hàng mới
            int newCartId = -1;
            try (PreparedStatement insertStmt = connection.prepareStatement(sqlInsertCart, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.executeUpdate();
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    newCartId = generatedKeys.getInt(1);
                }
            }

            if (newCartId != -1) {
                // Cập nhật cart_id cho người dùng
                try (PreparedStatement updateStmt = connection.prepareStatement(sqlUpdateUser)) {
                    updateStmt.setInt(1, newCartId);
                    updateStmt.setString(2, username);
                    updateStmt.executeUpdate();
                }

                return getCartById(newCartId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Cart createCartIfNotExist(int user_id) {
        String sqlInsertCart = "INSERT INTO carts DEFAULT VALUES";
        String sqlUpdateUser = "UPDATE users SET cart_id = ? WHERE id = ?";

        try (Connection connection = Database.getConnect()) {
            // Tạo giỏ hàng mới
            int newCartId = -1;
            try (PreparedStatement insertStmt = connection.prepareStatement(sqlInsertCart, Statement.RETURN_GENERATED_KEYS)) {
                insertStmt.executeUpdate();
                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    newCartId = generatedKeys.getInt(1);
                }
            }

            if (newCartId != -1) {
                // Cập nhật cart_id cho người dùng
                try (PreparedStatement updateStmt = connection.prepareStatement(sqlUpdateUser)) {
                    updateStmt.setInt(1, newCartId);
                    updateStmt.setInt(2, user_id);
                    updateStmt.executeUpdate();
                }

                return getCartById(newCartId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public double getCartTotal(int cartId) {
        String sql = "SELECT dbo.calculate_cart_total(?) AS total_amount";
        double totalAmount = 0.0;
        try {
            PreparedStatement ps = Database.getConnect().prepareStatement(sql);
            ps.setInt(1, cartId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        totalAmount = rs.getDouble("total_amount");
                    }
                }
            
        } catch (SQLException e) {
        }
        return totalAmount;
    }
    
    public double getDiscountCartTotal(int cartId) {
        String sql = "SELECT dbo.calculate_discounted_cart_total(?) AS total_amount";
        double totalAmount = 0.0;
        try {
            PreparedStatement ps = Database.getConnect().prepareStatement(sql);
            ps.setInt(1, cartId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        totalAmount = rs.getDouble("total_amount");
                    }
                }
            
        } catch (SQLException e) {
        }
        return totalAmount;
    }
    
    public static void main(String[] args) {
        System.out.println(new CartDAO().getCartTotal(1));
    }
}
