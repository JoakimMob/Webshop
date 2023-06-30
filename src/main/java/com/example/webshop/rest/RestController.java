package com.example.webshop.rest;

import com.example.webshop.business.Product;
import com.example.webshop.business.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    WebShopService service;

    @GetMapping("rest/allproducts")
    public List<Product> allProducts(){
        return service.getAllProducts();
    }
    @GetMapping("rest/productbyid/{id}")
    public Product getProduct(@PathVariable Integer id){
        return service.getByIdProduct(id);
    }
    @GetMapping("rest/productbycategory")
    public List<Product> getCategory(@RequestParam String category){
        return service.findProductByCategory(category);
    }
    @DeleteMapping("rest/deleteproduct/{id}")
    public List<Product> deleteProduct(@PathVariable Integer id){
        return service.deleteProduct(id);
    }
    @PostMapping("rest/addproduct")
    public List<Product> addProduct(@RequestParam String name, @RequestParam String category, @RequestParam Double price){
        service.addProductToDB(name,category, price);
        return service.getAllProducts();
    }
    @PutMapping("rest/updateprice")
    public List<Product> updateProductPrice(@RequestParam Integer id, @RequestParam Double price){
        service.updatePrice(id,price);
        return service.getAllProducts();
    }


}
