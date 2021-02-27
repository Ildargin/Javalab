package ru.itis.jlab.repositories;


import ru.itis.jlab.models.User;

public interface UsersRepository {
    boolean save(User user);

}
