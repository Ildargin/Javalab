package ru.itis.javalab.services;

import ru.itis.javalab.dto.SignUpForm;
import ru.itis.javalab.models.User;

import java.util.List;


public interface UsersService {
    List<User> getAllUsers();
    void saveUser(SignUpForm pool);
    Boolean checkUserByEmailAndPassword(String email, String password);
}
