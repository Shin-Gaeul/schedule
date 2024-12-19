package com.example.Schedule;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Schedule> createSchedule(@RequestBody Schedule schedule) {
        Schedule createdSchedule = scheduleService.createSchedule(
                schedule.getTodo(),
                schedule.getContent(), // 할일 내용 추가
                schedule.getAuthor(),
                schedule.getPassword(),
                schedule.getId()
        );
        System.out.println("새로 생성된 일정 ID: " + createdSchedule.getId());
        return ResponseEntity.ok(createdSchedule);
    }

    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable int id, @RequestBody Schedule schedule) {
        Schedule updatedSchedule = scheduleService.updateSchedule(
                id,
                schedule.getTodo(),
                schedule.getContent(), // 할일 내용 추가
                schedule.getAuthor(),
                schedule.getPassword()
        );
        return ResponseEntity.ok(updatedSchedule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.noContent().build();
    }
}
