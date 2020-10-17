package ru.itis.javalab.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll();
    Optional<T> findById();

    void save(T entity);
    void update(T entity);
    void deleteById(Long id);
    void delete(T entity);
}
