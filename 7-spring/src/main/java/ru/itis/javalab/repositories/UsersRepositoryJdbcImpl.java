package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String SQL_SELECT_BY_AGE = "SELECT * FROM USERS WHERE AGE = ?";
    private static final String SQL_SELECT = "SELECT * from USERS";
    public static final String SQL_ADD_USER = "INSERT INTO USERS (first_name, last_name, password, email) VALUES (?, ?, ?, ?);";
    public static final String SQL_FIND_BY_EMAIL = "SELECT * FROM  USERS WHERE email=?";

    private SimpleJdbcTemplate template;

    private final RowMapper<User> userRowMapper = row -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName((row.getString("last_name")))
            .password(row.getString("password"))
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
        return usersList;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> usersList = template.query(SQL_FIND_BY_EMAIL, userRowMapper, email);
        if (usersList.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(usersList.get(0));
        }
    }


    @Override
    public void save(User entity) {
        List<User> user = template.query(SQL_ADD_USER, userRowMapper, new String[]{entity.getFirstName(), entity.getLastName(), entity.getPassword(), entity.getEmail()});
    }


    @Override
    public void update(User entity) {
    }


    @Override
    public void delete(User entity) {
    }


}