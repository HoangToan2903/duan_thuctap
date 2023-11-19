package com.example.demo.controller;



import com.example.demo.model.User;
import com.example.demo.service.CartService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.MimeMessageHelper;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("loggedInUser")
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    ProductService productService;

    @Autowired
    private JavaMailSender javaMailSender;

    @ModelAttribute("loggedInUser")
    public User loggedInUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(auth.getName());
    }



    @PostMapping("/cancel/{orderID}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderID) {
        try {
            orderService.cancelOrder(orderID);
            return ResponseEntity.ok("Đã hủy đơn hàng thành công!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Hủy đơn hàng thất bại!");
        }
    }

}

