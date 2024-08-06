package com.example.timetable.service;

import com.example.timetable.model.TimeTable;
import com.example.timetable.model.Course;
import com.example.timetable.model.Teacher;
import com.example.timetable.repository.TimeTableRepository;
import com.example.timetable.repository.CourseRepository;
import com.example.timetable.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeTableService {

    @Autowired
    private TimeTableRepository timeTableRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    public TimeTable saveTimeTable(TimeTable timeTable, Long courseId, Long teacherId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (course.isPresent() && teacher.isPresent()) {
            timeTable.setCourse(course.get());
            timeTable.setTeacher(teacher.get());
            return timeTableRepository.save(timeTable);
        } else {
            throw new RuntimeException("Course or Teacher not found");
        }
    }

    public Optional<TimeTable> getTimeTableById(Long id) {
        return timeTableRepository.findById(id);
    }

    public List<TimeTable> getAllTimeTables() {
        return timeTableRepository.findAll();
    }

    public TimeTable updateTimeTable(TimeTable timeTable, Long courseId, Long teacherId) {
        Optional<Course> course = courseRepository.findById(courseId);
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);

        if (course.isPresent() && teacher.isPresent()) {
            timeTable.setCourse(course.get());
            timeTable.setTeacher(teacher.get());
            return timeTableRepository.save(timeTable);
        } else {
            throw new RuntimeException("Course or Teacher not found");
        }
    }

    public void deleteTimeTableById(Long id) {
        timeTableRepository.deleteById(id);
    }
}
