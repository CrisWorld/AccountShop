/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
<<<<<<< HEAD
 * @author thaip
 */
public class Contact {
    private int id;
    private String username, message;
    private Date create_date;

    public Contact() {
    }

    public Contact(int id, String username, String message, Date create_date) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.create_date = create_date;
    }

=======
 * @author ASUS
 */
public class Contact {
    private int id;
    private String username;
    private String message;
    private Date createdAt;

    // Constructors
    public Contact() {}

    public Contact(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public Contact(String username, String message, Date createdAt) {
        this.username = username;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Contact(int id, String username, String message, Date createdAt) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
>>>>>>> origin/Truong
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

<<<<<<< HEAD
    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
=======
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
>>>>>>> origin/Truong
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        return "Contact{" + "id=" + id + ", username=" + username + ", message=" + message + ", create_date=" + create_date + '}';
    }
    
}
=======
        return "Contact{" + "id=" + id + ", username=" + username + ", message=" + message + ", createdAt=" + createdAt + '}';
    }
}

>>>>>>> origin/Truong
