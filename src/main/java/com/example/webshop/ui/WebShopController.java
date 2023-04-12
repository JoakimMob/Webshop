package com.example.webshop.ui;

import com.example.webshop.business.WebShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebShopController {
    @Autowired
    WebShopService webShopService;

    @PostMapping("/login")
    public String login(@RequestParam String loginUser, @RequestParam String password, Model m){
        m.addAttribute("person", webShopService.login(loginUser,password));
        return "product";
    }


}
