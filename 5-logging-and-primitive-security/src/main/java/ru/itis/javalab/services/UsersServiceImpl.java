package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
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
    public Optional<User> findUserByEmailAndPassword(String email, String  password) {
        return usersRepository.findFirstByEmailAndPassword(new String[] {email, password});
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
        usersRepository.save(user);
    }
}
