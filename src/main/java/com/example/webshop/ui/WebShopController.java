package com.example.webshop.ui;

import com.example.webshop.business.Cart;
import com.example.webshop.business.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebShopController {
    @Autowired
    WebShopService webShopService;

    @PostMapping("/login")
    public String login(@RequestParam String loginUser, @RequestParam String password, Model m) {
        m.addAttribute("person", webShopService.login(loginUser, password));
        m.addAttribute("products", webShopService.getAllProducts());
        m.addAttribute("category", webShopService.getAllCategories());
        if (webShopService.getAdminLogin()){
            return "adminPage";
        }
        return "productSite";
    }

    @GetMapping("/register")
    public String registerSite(Model m) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String loginUser, @RequestParam String password,
                           @RequestParam(value = "adminBox", required = false) Boolean adminBox, Model m) {
        if (adminBox==null){
            adminBox = false;
        }
        String check = webShopService.checkIfUserExists(loginUser, password, adminBox);
        m.addAttribute("check", check);
        return "register";
    }

    @GetMapping("/addProducts")
    public String addProductSite() {
        return "addProducts";
    }

    @PostMapping("/addtocart")
    public String addToCart(@RequestParam Long index, @RequestParam int amount, Model m) {
        Cart cart = webShopService.addProductToCart(index, amount);
        m.addAttribute("products", webShopService.getAllProducts());
        m.addAttribute("category", webShopService.getAllCategories());
        System.out.println(index + " Cart " + amount);
        m.addAttribute("cart", cart);
        return "productSite";
    }

    @GetMapping("/showcart")
    public String showCart(Model m) {
        m.addAttribute("cart", webShopService.getCart());
        m.addAttribute("amount", webShopService.getCart().sumOfAllProducts());
        return "showCart";
    }

    @PostMapping("/addProducts")
    public String addProduct(@RequestParam String productName, @RequestParam String category, @RequestParam Double productPrice
            , Model m) {
        m.addAttribute("product", webShopService.addProductToDB(productName, category, productPrice));
        return "addProducts";
    }

    @PostMapping("/removeproductfromcart")
    public String removeFromCart(@RequestParam Integer id, Model m) {
        webShopService.removeCartItem(id);
        m.addAttribute("amount", webShopService.getCart().sumOfAllProducts());
        m.addAttribute("cart", webShopService.getCart());
        return "showCart";
    }

    @PostMapping("/placeorder")
    public String placeOrder(Model m){
        webShopService.addToOrder();
        m.addAttribute("customerorders", webShopService.getCustomerOrders());
        return "placedOrder";
    }

    @PostMapping("/showspecificproduct")
    public String searchForSpecificProduct(Model m, @RequestParam String searchedName){
        m.addAttribute("products", webShopService.findProduct(searchedName));
        m.addAttribute("category", webShopService.getAllCategories());
        return "productSite";
    }

    @PostMapping("/showspecificcategory")
    public String searchForSpecificCategory(Model m, @RequestParam String chosenCategory){
        m.addAttribute("category", webShopService.getAllCategories());
        m.addAttribute("products", webShopService.findProductByCategory(chosenCategory));
        return "productSite";
    }

    @GetMapping("/showAllOrders")
    public String addShowAllOrdersSite(Model m){
        m.addAttribute("customersordersadmin", webShopService.getAllCustomerOrders());
        m.addAttribute("customer", webShopService.getAllCustomers());
        return "showAllOrders";
    }

    @PostMapping("/showAllOrders")
    public String showAllOrdersAsAdmin(Model m, @RequestParam Integer shipped){
        webShopService.getAllCustomerOrders().get(shipped-1).setShipped(true);
        webShopService.saveOrder(webShopService.getAllCustomerOrders().get(shipped-1));
        m.addAttribute("customer", webShopService.getAllCustomers());
        m.addAttribute("customersordersadmin", webShopService.getAllCustomerOrders());
        return "showAllOrders";
    }


}
