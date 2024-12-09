package com.example.Schedule;

import com.example.Schedule.Schedule;
import com.example.Schedule.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @PostMapping
    public ResponseEntity<Integer> createSchedule(@RequestBody Schedule schedule) {
        int scheduleId = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(scheduleId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable int id, @RequestBody Schedule schedule, @RequestParam String password) {
        schedule.setId(id);
        scheduleService.updateSchedule(schedule, password);
        return ResponseEntity.ok().build();
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
        return ResponseEntity.ok().build();
    }
}
