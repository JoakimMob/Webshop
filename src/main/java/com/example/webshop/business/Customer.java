package com.example.webshop.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrders;
    private boolean admin;

    public Customer() {
    }

    public void addOrder(CustomerOrder customerOrder) {
        customerOrders.add(customerOrder);
    }

    public Customer(Long id, String email, String password, List<CustomerOrder> customerOrders, boolean admin) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.customerOrders = customerOrders;
        this.admin = admin;
    }

    public Customer(String email, String password, boolean admin) {
        this.email = email;
        this.password = password;
        customerOrders = new ArrayList<>();
        this.admin = admin;
    }

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
        customerOrders = new ArrayList<>();
        this.admin = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin(){
        return admin;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
