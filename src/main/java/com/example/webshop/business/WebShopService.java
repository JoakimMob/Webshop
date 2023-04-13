package com.example.webshop.business;

import com.example.webshop.data.CustomerRepository;
import com.example.webshop.data.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
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
    Cart cart;

    List<CustomerOrder> customerOrder;

    public Customer login(String loginUser, String password) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser, password);
        customer = customerList.get(0);
        return customer;
    }

    public WebShopService() {
        cart=new Cart();
        customerOrder = new ArrayList<>();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Product getByIdProduct(long id){
        return productRepository.findById(id).get();
    }

    public Product addProductToDB(String productName, String productCategory, Double productPrice) {
        product = productRepository.save(new Product(productName, productCategory, productPrice));
        return product;
    }

    public Cart addProductToCart(Long id, int amount){
        cart.cartItems.add(new CartItem(getByIdProduct(id), amount));
        return cart;
    }

    public void addToOrder(){
        customerOrder.add(new CustomerOrder(getCart().getCartItems()));
        customer.setCustomerOrders(customerOrder);
        clearCart();
    }

    public void clearCart(){
        cart = new Cart();
    }

    public List<CustomerOrder> getCustomerOrder(){
        return customerOrder;
    }

    public Cart getCart(){
        return cart;
    }

    public List<CartItem> removeCartItem(int id){
        return cart.removeItemFromCart(id);
    }

    public String checkIfUserExists(String loginUser, String password) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser, password);
        if (customerList.isEmpty()) {
            customer = customerRepository.save(new Customer(loginUser, password));
            return "User now registered";
        }
        return "User already exists";
    }
}
