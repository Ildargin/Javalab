package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleJdbcTemplate {
    private final DataSource dataSource;

    SimpleJdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> Result = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            statement = prepareStatement(connection, sql, args);
            //https://stackoverflow.com/questions/30906855/java-sql-statement-executequerystring-sql-throws-sqlexception-when-delete-sql
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

    public Boolean checkQuery(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            statement = prepareStatement(connection, sql, args);
            if (sql.toLowerCase().contains("insert")) {
                statement.executeUpdate();
                return true;
            } else {
                resultSet = statement.executeQuery();
            }
            return resultSet.next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
            close(connection, statement, resultSet);
        }
    }

    private void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignore) {
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignore) {
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
            }
        }
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

}