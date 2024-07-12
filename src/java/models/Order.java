package models;

import java.sql.Timestamp;

public class Order {

    private int id;
    private String username;
    private Timestamp orderDate;
    private Integer voucherId;
    private double totalAmount;
    private String status;
    private String fullname;
    private String voucherCode;
    private double discountPercentage;

    // Getters and setters
    public Order(int id, String username, Timestamp orderDate, Integer voucherId, double totalAmount, String status, String fullname, String voucherCode, double discountPercentage) {
        this.id = id;
        this.username = username;
        this.orderDate = orderDate;
        this.voucherId = voucherId;
        this.totalAmount = totalAmount;
        this.status = status;
        this.fullname = fullname;
        this.voucherCode = voucherCode;
        this.discountPercentage = discountPercentage;
    }

    public Order() {
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

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

}
