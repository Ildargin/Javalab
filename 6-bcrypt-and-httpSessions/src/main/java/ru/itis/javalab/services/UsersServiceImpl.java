package ru.itis.javalab.services;

import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String[] args) {
        return usersRepository.findFirstByEmailAndPassword(args);
    }

    @Override
    public void saveUser(Map pool) {
        User user = User.builder()
                .firstName((String) pool.get("first_name"))
                .lastName((String) pool.get("last_name"))
                .password((String) pool.get("password"))
                .email((String) pool.get("email"))
                .build();
        usersRepository.save(user);
    }
}
