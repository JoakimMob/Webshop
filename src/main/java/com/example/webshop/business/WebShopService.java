package com.example.webshop.business;

import com.example.webshop.data.CustomerRepository;
import com.example.webshop.data.OrderRepository;
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

    @Autowired
    OrderRepository orderRepository;

    Customer customer;
    Product product;
    Cart cart;
    CustomerOrder customerOrder;


    public Customer login(String loginUser, String password) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser, password);
        customer = customerList.get(0);
        return customer;
    }

    public WebShopService() {
        cart=new Cart();
        customerOrder= new CustomerOrder(customer, cart.getCartItems());
    }

    public List<Product>findProduct(String productName){
        return productRepository.findByName(productName);
    }

    public List<CustomerOrder> getAllCustomerOrders(){
        return orderRepository.findAll();
    }

    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    public List<Product> getAllCategories(){
        return (List<Product>) productRepository.findAll().stream().map(Product::getCategory);
    }

    public List<Product> findProductByCategory(String category){
        return productRepository.findByCategory(category);
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
        customer.addOrder(new CustomerOrder(customer, getCart().getCartItems()));
        customer = customerRepository.save(customer);
        clearCart();
    }

    public void clearCart(){
        cart = new Cart();
    }

    public List<CustomerOrder> getCustomerOrders(){
        return customer.getCustomerOrders();
    }

    public Cart getCart(){
        return cart;
    }

    public List<CartItem> removeCartItem(int id){
        return cart.removeItemFromCart(id);
    }

    public String checkIfUserExists(String loginUser, String password, boolean admin) {
        List<Customer> customerList = customerRepository.findByEmailAndPassword(loginUser, password);
        if (customerList.isEmpty()) {
            customer = customerRepository.save(new Customer(loginUser, password, admin));
            return "User now registered";
        }
        return "User already exists";
    }

    public Boolean getAdminLogin(){
        return customer.isAdmin();
    }
}
