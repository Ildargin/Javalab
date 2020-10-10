package ru.itis.javalab.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersServlet extends HttpServlet {

    private UsersRepository usersRepository;

    @Override
    public void init() throws ServletException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/javaCRUD");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("franzefer");
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        HikariDataSource dataSourse = new HikariDataSource(hikariConfig);
        usersRepository = new UsersRepositoryJdbcImpl(dataSourse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<User> users = usersRepository.findAll();
        for(User user : users) {
            System.out.println(user);
        }

    }
}
