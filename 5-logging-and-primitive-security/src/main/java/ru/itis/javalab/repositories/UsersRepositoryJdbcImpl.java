package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String SQL_SELECT_BY_AGE = "SELECT * FROM USERS WHERE AGE = ?";
    private static final String SQL_SELECT = "SELECT * from USERS";

    private SimpleJdbcTemplate template;

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName((row.getString("last_name")))
            .age(row.getInt("age"))
            .build();

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.template = new SimpleJdbcTemplate(dataSource);
    }

    @Override
    public List<User> findALLByAge(Integer age) {
        List<User> Users = template.query(SQL_SELECT_BY_AGE, userRowMapper, age);
        return Users;
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = template.query(SQL_SELECT, userRowMapper);
        for(User user : usersList ){
            System.out.println(user);
        }
        return usersList;
    }

    @Override
    public Optional<User> findFirstByFirstnameAndLastname(String smth) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
    }

    @Override
    public void update(User entity) {
    }


    @Override
    public void delete(User entity) {
    }


}