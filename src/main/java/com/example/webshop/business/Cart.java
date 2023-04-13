package com.example.webshop.business;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


public class Cart {
    List<CartItem> cartItems;

    public List<CartItem> getCartItems(){
        return cartItems;
    }

    public List<CartItem> removeItemFromCart(int id){
        if (getCartItems().get(id).getAmount() == 1){
            getCartItems().remove(id);
        } else {
            getCartItems().get(id).removeOneFromAmount();
        }
        return getCartItems();
    }

    public Double sumOfAllProducts(){
        Double temp =0.0;
        for (int i = 0; i < cartItems.size(); i++) {
            temp += (int) (cartItems.get(i).product.getPrice()*cartItems.get(i).amount);
        }
        return temp;
    }

    public Cart(){
        this.cartItems=new ArrayList<>();
    }



}
