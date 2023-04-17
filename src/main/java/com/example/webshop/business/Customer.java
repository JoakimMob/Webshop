package com.example.webshop.business;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id", nullable = false)
    private Long id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<CustomerOrder> customerOrders;

    public Customer() {
    }

    public void addOrder(CustomerOrder customerOrder){
        customerOrders.add(customerOrder);
    }

    public Customer(Long id, String email, String password, List<CustomerOrder> customerOrders) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.customerOrders = customerOrders;
    }

    public Customer(String email, String password) {
        this.email = email;
        this.password = password;
        customerOrders= new ArrayList<>();
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
