/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Contact {
    private int id;
    private String username;
    private String message;
    private LocalDateTime createdAt;

    // Constructors
    public Contact() {}

    public Contact(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public Contact(String username, String message, LocalDateTime createdAt) {
        this.username = username;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Contact(int id, String username, String message, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        return createdAt.format(formatter);
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", username=" + username + ", message=" + message + ", createdAt=" + createdAt + '}';
    }
}

