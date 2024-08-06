package com.example.timetable.controller;

import com.example.timetable.model.TimeTable;
import com.example.timetable.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/timetable")
public class TimeTableController {

    @Autowired
    private TimeTableService timeTableService;

    @PostMapping
    public ResponseEntity<TimeTable> addTimeTable(@RequestBody TimeTable timeTable, @RequestParam Long courseId, @RequestParam Long teacherId) {
        TimeTable savedTimeTable = timeTableService.saveTimeTable(timeTable, courseId, teacherId);
        return ResponseEntity.ok(savedTimeTable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeTable> getTimeTableById(@PathVariable Long id) {
        Optional<TimeTable> timeTable = timeTableService.getTimeTableById(id);
        return timeTable.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TimeTable>> getAllTimeTables() {
        List<TimeTable> timeTables = timeTableService.getAllTimeTables();
        return ResponseEntity.ok(timeTables);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeTable> updateTimeTable(@PathVariable Long id, @RequestBody TimeTable timeTableDetails, @RequestParam Long courseId, @RequestParam Long teacherId) {
        Optional<TimeTable> timeTable = timeTableService.getTimeTableById(id);
        if (timeTable.isPresent()) {
            TimeTable existingTimeTable = timeTable.get();
            existingTimeTable.setDay(timeTableDetails.getDay());
            existingTimeTable.setTimeSlot(timeTableDetails.getTimeSlot());
            TimeTable updatedTimeTable = timeTableService.updateTimeTable(existingTimeTable, courseId, teacherId);
            return ResponseEntity.ok(updatedTimeTable);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeTable(@PathVariable Long id) {
        timeTableService.deleteTimeTableById(id);
        return ResponseEntity.ok().build();
    }
}
