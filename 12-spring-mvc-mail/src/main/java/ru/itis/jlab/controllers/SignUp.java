package ru.itis.jlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.jlab.models.SignUpForm;
import ru.itis.jlab.services.SignUpService;
import javax.servlet.http.HttpSession;

@Controller
public class SignUp {

    private final SignUpService signUpService;

    @Autowired
    public SignUp(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String getSignUpPage(SignUpForm form, HttpSession session) {
        signUpService.signUp(form, session);
        return "redirect:/";
    }
}
