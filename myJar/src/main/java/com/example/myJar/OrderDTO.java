package com.example.myJar;

public class OrderDTO {

    private int id;
    private String item;
    private double amount;
    private String userName;

    public OrderDTO(){};

    public OrderDTO(int id, String item, double amount, String userName)
    {
        this.id= id;
        this.item= item;
        this.amount=amount;
        this.userName= userName;
    }

    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public double getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
