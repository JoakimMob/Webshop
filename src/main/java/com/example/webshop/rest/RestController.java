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

    @GetMapping("rest/product/all")
    public List<Product> allProducts(){
        return service.getAllProducts();
    }
    @GetMapping("rest/product/id/{id}")
    public Product getProduct(@PathVariable Integer id){
        return service.getByIdProduct(id);
    }
    @GetMapping("rest/product/category/{category}")
    public List<Product> getCategory(@PathVariable String category){
        return service.findProductByCategory(category);
    }
    @DeleteMapping("rest/deleteproduct/{id}")
    public List<Product> deleteProduct(@PathVariable Integer id){
        return service.deleteProduct(id);
    }
    @PostMapping("rest/addproduct/{name}/{category}/{price}")
    public List<Product> addProduct(@PathVariable String name, @PathVariable String category, @PathVariable Double price){
        service.addProductToDB(name,category, price);
        return service.getAllProducts();
    }
    @PutMapping("rest/updateprice/{id}/{price}")
    public List<Product> updateProductPrice(@PathVariable Integer id, @PathVariable Double price){
        service.updatePrice(id,price);
        return service.getAllProducts();
    }


}
