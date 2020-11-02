package ru.itis.javalab.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@WebServlet("/json/users")
public class UsersJsonServlet  extends HttpServlet {

    private UsersService usersService;
    private ObjectMapper objectMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService =  applicationContext.getBean(UsersService.class);
        objectMapper = applicationContext.getBean(ObjectMapper.class);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        List<UserDto> users = usersService.getAllUser(Integer.parseInt(page), Integer.parseInt(size));
        res.getWriter().write(objectMapper.writeValueAsString(users));
        res.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Reader reader = req.getReader();
        UserDto json = objectMapper.readValue(reader, UserDto.class);
        usersService.addUser(json);
    }
}
