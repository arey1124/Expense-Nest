package com.example.expensenest.entity;

public class UserInsights {
    //Id
    //Name
    //Value
    //Type
    //GraphString

    private int id;
    private String name;
    private double value;
    private String typeOfChart;
    private String graphString;

    public UserInsights(){}

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

    public double getValue (){return value;}
    public void setValue(double value) {this.value =value;}
    public String getTypeOfChart() {
        return typeOfChart;
    }

    public void setTypeOfChart(String typeOfChart) {
        this.typeOfChart = typeOfChart;
    }

    public String getGraphString() {
        return graphString;
    }

    public void setGraphString(String graphString) {
        this.graphString = graphString;
    }
}
