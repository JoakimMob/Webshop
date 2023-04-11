package com.example.webshop.business;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class CustomerOrder {
    @ManyToOne
    Customer customer;
    @Id
    @GeneratedValue ( strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

}
