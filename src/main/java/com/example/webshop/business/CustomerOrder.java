package com.example.webshop.business;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CustomerOrder {
    @ManyToOne
    Customer customer;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<CartItem> cartItems;
    Boolean shipped;


    public CustomerOrder(List<CartItem> cartItems, Customer customer) {
        this.customer = customer;
        this.cartItems = cartItems;
        this.shipped = false;
    }

    public Boolean getShipped() {
        return shipped;
    }

    public void setShipped(Boolean shipped) {
        this.shipped = shipped;
    }

    public CustomerOrder(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CustomerOrder() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
