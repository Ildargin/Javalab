package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            statement = connection.prepareStatement(sql);
            int position = 1;
            if (args.length > 0) {
                for (Object arg : args) {
                    System.out.println(arg);
                    statement.setObject(position, arg);
                    position++;
                }
            }
            System.out.println(statement.toString());
            try {
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                return Collections.emptyList();
            }
            while (resultSet.next()) {
                Result.add(rowMapper.mapRow(resultSet));
            }
            return Result;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
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
    }

    public <T> Boolean checkQuery(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<T> Result = new ArrayList<>();

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            int position = 1;
            if (args.length > 0) {
                for (Object arg : args) {
                    System.out.println(arg);
                    statement.setObject(position, arg);
                    position++;
                }
            }
            try {
                resultSet = statement.executeQuery();
            } catch (SQLException e) {
                return false;
            }
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        } finally {
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
            return false;
        }
    }
}