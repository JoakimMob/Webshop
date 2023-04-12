package com.example.webshop.business;

import com.example.webshop.data.CustomerRepository;
import com.example.webshop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class WebShopService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    Customer customer;
    Product product;

    public Customer login(String loginUser, String password) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser, password);
        customer = customerList.get(1);
        return customer;
    }

    public WebShopService() {
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(String productName, String productCategory, Double productPrice) {
        product = productRepository.save(new Product(productName, productCategory, productPrice));
        return product;
    }

    public String checkIfUserExists(String loginUser, String password) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser, password);
        if (customerList.isEmpty()) {
            customer = customerRepository.save(new Customer(loginUser, password));
            return "";
        }
        return "User already exists";
    }
}
