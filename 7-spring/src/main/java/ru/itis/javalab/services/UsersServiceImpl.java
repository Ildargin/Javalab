package ru.itis.javalab.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.dto.SignUpForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Boolean checkUserByEmailAndPassword(String email, String password) {
        Optional<User> user = usersRepository.findByEmail(email);
        if (user.isPresent()) {
            String dbPassword = user.get().getPassword().trim();
            return passwordEncoder.matches(password, dbPassword);
        }
        return false;
    }

    @Override
    public void saveUser(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .password(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .build();
        usersRepository.save(user);
    }
}
