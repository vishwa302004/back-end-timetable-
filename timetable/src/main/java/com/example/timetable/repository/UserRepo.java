package com.example.timetable.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.timetable.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}