package com.example.webshop;

import com.example.webshop.business.Product;
import com.example.webshop.business.WebShopService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WebshopApplicationTests {

    @Autowired
    WebShopService webShopService;
    Product product = Mockito.mock(Product.class);
    Product product2 = Mockito.mock(Product.class);
    Product product3 = Mockito.mock(Product.class);

    @BeforeEach
    void setUp(){
        webShopService.addProductToDB(product.getName(), product.getCategory(), product.getPrice());
        webShopService.addProductToDB(product2.getName(), product2.getCategory(), product2.getPrice());
        webShopService.addProductToDB(product3.getName(), product3.getCategory(), product3.getPrice());
        webShopService.addProductToCart(1L,1);
        webShopService.addProductToCart(2L,1);
        webShopService.addProductToCart(3L,1);
    }
    @Test
    void addToCartTest(){
        assertEquals(3,webShopService.getCart().getCartItems().size());
    }

}
