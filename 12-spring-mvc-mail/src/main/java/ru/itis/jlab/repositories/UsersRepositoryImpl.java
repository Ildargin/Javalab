package ru.itis.jlab.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itis.jlab.models.User;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final JdbcTemplate template;

    //language=SQL
    private final String SQL_SAVE = "INSERT INTO users (email, hash_password, confirmed_code, status)  values (?, ?, ?, ?)";

    @Autowired
    public UsersRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public boolean save(User user) {
        try {
            template.update(SQL_SAVE, user.getEmail(), user.getPassword(), user.getConfirmedCode().toString(), user.getStatus().getString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
