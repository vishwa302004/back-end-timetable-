package com.example.timetable.repository;

import com.example.timetable.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByName(String name);
}
