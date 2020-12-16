package ru.itis.javalab.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.models.User;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemlateImpl implements UsersRepository {

    private static final String SQL_SELECT_ALL = "SELECT * FROM USERS";
    private static final String SQL_SELECT_ALL_WITH_PAGES = "SELECT * FROM USERS LIMIT ? OFFSET ?";
    private static final String SQL_SELECT_BY_AGE = "SELECT * FROM USERS WHERE AGE=?";
    public static final String SQL_SELECT_BY_EMAIL = "SELECT * FROM USERS WHERE EMAIL=?";

    private JdbcTemplate jdbcTemplate;

    private RowMapper<User>  userRowMapper  = (row, i) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName((row.getString("last_name")))
            .password(row.getString("password"))
            .age(row.getInt("age"))
            .build();

    public UsersRepositoryJdbcTemlateImpl(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findALLByAge(Integer age) {
        return jdbcTemplate.query(SQL_SELECT_BY_AGE, userRowMapper, age);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        User user;
        try {
            user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userRowMapper, email);
        } catch (EmptyResultDataAccessException e) {
            user = null;
        }
        return Optional.ofNullable(user);
    }

    @Override
    public void save(User entity) {
        //TODO: Insert user
        System.out.println(entity);
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public List<User> findAll(int size, int page) {
        return jdbcTemplate.query(SQL_SELECT_ALL_WITH_PAGES, userRowMapper, page, page*size);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }
}
