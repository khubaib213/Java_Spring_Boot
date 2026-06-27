package com.example.myJar;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Orders> orders;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public int getId() { return id; }
    public List<Orders> getOrders() { return orders; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}