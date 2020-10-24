package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface UsersService {
    List<User> getAllUsers();
    void saveUser(Map<String,String> pool);
    Optional<User> findUserByEmailAndPassword(String[] args);
}
