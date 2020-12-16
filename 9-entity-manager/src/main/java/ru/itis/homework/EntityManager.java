package ru.itis.homework;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class EntityManager {
    private DataSource dataSource;

    public EntityManager(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    // createTable("account", User.class);
    public <T> void createTable(String tableName, Class<T> entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            String[] primitiveClassArr = field.getType().toString().split("[.]");
            String name = field.getName();
            String type = field.getType().getSimpleName();
        }

    }

    public void save(String tableName, Object entity) {
        Class<?> classOfEntity = entity.getClass();
        // сканируем его поля
        // сканируем значения этих полей
        // генерируем insert into
    }

    // User user = entityManager.findById("account", User.class, Long.class, 10L);
    public <T, ID> T findById(String tableName, Class<T> resultType, Class<ID> idType, ID idValue) {
        // сгенеририровать select
        return null;
    }

    private PreparedStatement prepareStatement(Connection connection, String sql, Object... args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        int position = 1;
        if (args.length > 0) {
            for (Object arg : args) {
                statement.setObject(position, arg);
                position++;
            }
        }
        return statement;
    }

    private <T> List<T> queryExecutor(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> Result = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            statement = prepareStatement(connection, sql, args);
            if (sql.toLowerCase().contains("insert")) {
                statement.executeUpdate();
                return Collections.emptyList();
            } else {
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()) {
                Result.add(rowMapper.mapRow(resultSet));
            }
            return Result;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            close(connection, statement, resultSet);
        }

    }

}
