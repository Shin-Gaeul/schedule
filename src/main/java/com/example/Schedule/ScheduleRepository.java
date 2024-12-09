package com.example.Schedule;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 일정 생성 메서드
     * @param schedule 생성할 일정 정보
     * @return 생성된 일정의 ID
     */
    public int insert(Schedule schedule) {
        String sql = "INSERT INTO schedule (todo, author, password, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                schedule.getTodo(),
                schedule.getAuthor(),
                schedule.getPassword(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt()
        );

        // MySQL의 LAST_INSERT_ID()를 사용하여 생성된 ID를 가져옴
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    /**
     * 전체 일정 조회 메서드
     * @param author 작성자명 (null 가능)
     * @param updatedAfter 수정일 기준 (null 가능)
     * @return 일정 목록
     */
    public List<Schedule> findAll(String author, LocalDateTime updatedAfter) {
        String sql = "SELECT * FROM schedule WHERE 1=1 ";
        if (author != null) {
            sql += "AND author = ? ";
        }
        if (updatedAfter != null) {
            sql += "AND updated_at >= ? ";
        }
        sql += "ORDER BY updated_at DESC";

        // SQL 파라미터 설정
        Object[] params = (author != null && updatedAfter != null)
                ? new Object[]{author, updatedAfter}
                : (author != null)
                ? new Object[]{author}
                : (updatedAfter != null)
                ? new Object[]{updatedAfter}
                : new Object[]{};

        return jdbcTemplate.query(sql, params, new ScheduleRowMapper());
    }

    /**
     * 단일 일정 조회 메서드
     * @param id 조회할 일정의 ID
     * @return 일정 정보
     */
    public Schedule findById(int id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ScheduleRowMapper());
    }

    public int update(Schedule schedule, String password) {
        // 비밀번호 검증
        String passwordCheckSql = "SELECT COUNT(*) FROM schedule WHERE id = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(passwordCheckSql, new Object[]{schedule.getId(), password}, Integer.class);

        if (count == 0) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 일정 수정
        String sql = "UPDATE schedule SET todo = ?, author = ?, updated_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                schedule.getTodo(),
                schedule.getAuthor(),
                LocalDateTime.now(),
                schedule.getId());
    }
    public int delete(int id, String password) {
        // 비밀번호 검증
        String passwordCheckSql = "SELECT COUNT(*) FROM schedule WHERE id = ? AND password = ?";
        int count = jdbcTemplate.queryForObject(passwordCheckSql, new Object[]{id, password}, Integer.class);

        if (count == 0) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 일정 삭제
        String sql = "DELETE FROM schedule WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }


}
