package com.example.Schedule;

import com.example.Schedule.Schedule;
import com.example.Schedule.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public int createSchedule(Schedule schedule) {
        return scheduleRepository.insert(schedule);
    }
    public void updateSchedule(Schedule schedule, String password) {
        scheduleRepository.update(schedule, password);
    }


    public void deleteSchedule(int id, String password) {
        scheduleRepository.delete(id, password);
    }
    public List<Schedule> getAllSchedules(String author, LocalDateTime updatedAfter) {
        return scheduleRepository.findAll(author, updatedAfter);
    }

    public Schedule getScheduleById(int id) {
        return scheduleRepository.findById(id);
    }
}
