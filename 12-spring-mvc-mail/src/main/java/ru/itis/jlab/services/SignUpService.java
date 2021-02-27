package ru.itis.jlab.services;

import ru.itis.jlab.models.SignUpForm;

import javax.servlet.http.HttpSession;

public interface SignUpService {
    void signUp(SignUpForm form,  HttpSession session);
}
