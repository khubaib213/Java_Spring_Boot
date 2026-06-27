package com.example.myJar;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name= "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String item;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("orders")
    private User user;
    public Orders(){}

    public Orders(String item, double amount, User user)
    {
        this.item= item;
        this.amount= amount;
        this.user= user;
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

    public User getUser() {
        return user;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

