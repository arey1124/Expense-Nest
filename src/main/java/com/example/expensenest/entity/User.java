package com.example.expensenest.entity;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String isVerfied;
    private int userType;
    private String phoneNumber;
    private int companyId;

    public User() {

    }

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(int id, String name, String email, String password, String isVerfied, int userType, String phoneNumber, int companyId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isVerfied = isVerfied;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.companyId = companyId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsVerfied() {
        return isVerfied;
    }

    public void setIsVerfied(String isVerfied) {
        this.isVerfied = isVerfied;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
