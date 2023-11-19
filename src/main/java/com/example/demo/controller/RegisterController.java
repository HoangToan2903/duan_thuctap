package com.example.demo.controller;


import com.example.demo.model.User;
import com.example.demo.service.SecurityService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;


    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("newUser", new User());
        return "client/register";
    }

    @PostMapping("/register")
    public String registerProcess(@ModelAttribute("newUser") @Valid User nguoiDung, BindingResult bindingResult, Model model) {

        userValidator.validate(nguoiDung, bindingResult);

        if (bindingResult.hasErrors()) {
            return "client/register";
        }

        userService.saveUserForMember(nguoiDung);

        securityService.autoLogin(nguoiDung.getEmail(), nguoiDung.getConfirmPassword());

        return "redirect:/login";
    }
}
