/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;
import java.sql.*;

/**
 *
 * @author quoch
 */
public class User {
    private String username;
    private String password;
    private String fullname;
    private String image;
    private boolean isAdmin;
    private Integer roleId;
    private Integer cartId;

    // Constructors, getters and setters

    public User() {}

    public User(String username, String password, String fullname, String image, boolean isAdmin, Integer roleId, Integer cartId) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.image = image;
        this.isAdmin = isAdmin;
        this.roleId = roleId;
        this.cartId = cartId;
    }
    
    // Getters and Setters for each field

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }
}
