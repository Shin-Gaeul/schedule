package com.example.Schedule;

import com.example.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import com.example.User.User;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleService(ScheduleRepository scheduleRepository,UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    public Schedule createSchedule(String todo, String content, String author, String password,int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Schedule schedule = new Schedule();
        schedule.setUser(user);
        schedule.setTodo(todo);
        schedule.setContent(content); // 할일 내용 추가
        schedule.setAuthor(author);
        schedule.setPassword(password);
        schedule.setCreatedAt(LocalDateTime.now());
        schedule.setUpdatedAt(LocalDateTime.now());
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Schedule getScheduleById(int id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public Schedule updateSchedule(int id, String todo, String content, String author, String password) {
        Schedule schedule = getScheduleById(id);

        // Schedule에서 User 객체를 통해 비밀번호 확인
        if (!schedule.getUser().getpassword().equals(password)) {
            throw new IllegalArgumentException("Password does not match");
        }

        schedule.setTodo(todo);
        schedule.setContent(content); // 할일 내용 추가
        schedule.setAuthor(author);
        schedule.setUpdatedAt(LocalDateTime.now());
        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(int id, String password) {
        Schedule schedule = getScheduleById(id);

        // Schedule에서 User 객체를 통해 비밀번호 확인
        if (!schedule.getUser().getpassword().equals(password)) {
            throw new IllegalArgumentException("Password does not match");
        }

        scheduleRepository.delete(schedule);
    }
}
