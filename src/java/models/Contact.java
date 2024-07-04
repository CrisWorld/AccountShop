/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.Date;

/**
 *
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

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "Contact{" + "id=" + id + ", username=" + username + ", message=" + message + ", create_date=" + create_date + '}';
    }
    
}
