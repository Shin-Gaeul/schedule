package com.example.Schedule;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleRowMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setTodo(rs.getString("todo"));
        schedule.setAuthor(rs.getString("author"));
        schedule.setPassword(rs.getString("password"));
        schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return schedule;
    }
}
