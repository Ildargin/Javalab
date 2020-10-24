package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    List<User> findALLByAge(Integer age);

    Optional<User> findFirstByEmailAndPassword(String[] FirstNameAndPassword);
}