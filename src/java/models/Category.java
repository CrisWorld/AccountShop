/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Category {
    private int id;
    private String name;
    private String description;
    private String banner;
    private ArrayList<Category> childs;
    private Integer parentId;
    
    public Category() {
    }
    
    
    
    public Category(int id) {
        this.id = id;
    }

    public Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(int id, String name, String description, String banner, ArrayList<Category> childs) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.banner = banner;
        this.childs = childs;
    }
    
    public Category(int id, String name, String description, String banner, ArrayList<Category> childs, Integer parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.banner = banner;
        this.childs = childs;
        this.parentId = parentId;
    }

    
    public Category(String name,  String banner, String description, Integer parentId) {
        this.name = name;
        this.description = description;
        this.banner = banner;
        this.parentId = parentId;
    }

    public Category(String name, String description, String banner) {
        this.name = name;
        this.description = description;
        this.banner = banner;
    }

    public Category(int id, String name, String description, String banner, Integer parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.banner = banner;
        this.parentId = parentId;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public ArrayList<Category> getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Category> childs) {
        this.childs = childs;
    }

    
    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", name=" + name + ", description=" + description + ", banner=" + banner + ", parent_id=" + parentId + '}';
    }
    
}
