package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;


public interface UsersService {
    List<User> getAllUsers();
    void saveUser(UserDto userDto);
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
