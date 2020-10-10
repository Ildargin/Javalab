package ru.itis.javalab.repositories;

import javax.sql.DataSource;
import java.util.List;

public class SimpleJdbcTemplate {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object ...args) {
        return  null;
        //realization
    }

}
