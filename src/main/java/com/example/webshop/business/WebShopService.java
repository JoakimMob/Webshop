package com.example.webshop.business;

import com.example.webshop.data.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class WebShopService {

    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    public Customer login(String loginUser, String password) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser,password);
        customer = customerList.get(1);
        return customer;
    }
}
