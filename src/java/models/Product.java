/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class Product {
    private int id;
    private String title;
    private String img;
    private int quantity;
    private double discount_percentage;
    private String status;
    private double price;
    private Category category;

    private String slug;
    private String desc;   
    private String short_desc;
    private String secret_info;
    private String meta_title;
    private String meta_description;
    private String meta_keyword;

    public Product() {
    }

    public Product(int id, String title, String img, int quantity, double discount_percentage, String status, double price, Category category, String slug, String desc, String short_desc, String secret_info, String meta_title, String meta_description, String meta_keyword) {
        this.id = id;
        this.title = title;
        this.img = img;
        this.quantity = quantity;
        this.discount_percentage = discount_percentage;
        this.status = status;
        this.price = price;
        this.category = category;
        this.slug = slug;
        this.desc = desc;
        this.short_desc = short_desc;
        this.secret_info = secret_info;
        this.meta_title = meta_title;
        this.meta_description = meta_description;
        this.meta_keyword = meta_keyword;
    }
    
    public Product(String title, String img, int quantity, double discount_percentage, String status, double price, Category category, String slug, String desc, String short_desc, String secret_info, String meta_title, String meta_description, String meta_keyword) {
        this.title = title;
        this.img = img;
        this.quantity = quantity;
        this.discount_percentage = discount_percentage;
        this.status = status;
        this.price = price;
        this.category = category;
        this.slug = slug;
        this.desc = desc;
        this.short_desc = short_desc;
        this.secret_info = secret_info;
        this.meta_title = meta_title;
        this.meta_description = meta_description;
        this.meta_keyword = meta_keyword;
    }

    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecret_info() {
        return secret_info;
    }

    public void setSecret_info(String secret_info) {
        this.secret_info = secret_info;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getMeta_title() {
        return meta_title;
    }

    public void setMeta_title(String meta_title) {
        this.meta_title = meta_title;
    }

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    public String getMeta_keyword() {
        return meta_keyword;
    }

    public void setMeta_keyword(String meta_keyword) {
        this.meta_keyword = meta_keyword;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", title=" + title + ", img=" + img + ", quantity=" + quantity + ", discount_percentage=" + discount_percentage + ", status=" + status + ", price=" + price + ", category=" + category + ", slug=" + slug + ", desc=" + desc + ", short_desc=" + short_desc + ", secret_info=" + secret_info + ", meta_title=" + meta_title + ", meta_description=" + meta_description + ", meta_keyword=" + meta_keyword + '}';
    }
}
