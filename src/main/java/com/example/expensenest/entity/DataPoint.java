package com.example.expensenest.entity;

import java.util.Date;

public class DataPoint {
    private String label;
    private double value;
    private double totalAmount;
    private Date timeStamp;
    private String name;
    private double sumAmount;

    public DataPoint(String label, double value) {
        this.label = label;
        this.value = value;
    }

    public DataPoint(double totalAmount, Date timeStamp) {
        this.totalAmount = totalAmount;
        this.timeStamp = timeStamp;
    }

    public DataPoint(String name, Integer sumAmount) {
        this.name = name;
        this.sumAmount = sumAmount;
    }

    public String getname(){
        return name;
    }

    public Integer getSumAmount(){
        return (int) sumAmount;
    }
    public String getLabel() {
        return label;
    }

    public double getValue() {
        return value;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
}
