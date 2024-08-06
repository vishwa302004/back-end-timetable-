package com.example.timetable.repository;

import com.example.timetable.model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {
}
