/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author PC
 */
public class RevenueData {
    private int year;
    private int month;
    private double monthlyRevenue;

    public RevenueData(int year, int month, double monthlyRevenue) {
        this.year = year;
        this.month = month;
        this.monthlyRevenue = monthlyRevenue;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public double getMonthlyRevenue() {
        return monthlyRevenue;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setMonthlyRevenue(double monthlyRevenue) {
        this.monthlyRevenue = monthlyRevenue;
    }
    
}

