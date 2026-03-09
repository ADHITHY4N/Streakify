package com.example.Streakify.Repository;

import com.example.Streakify.Model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HabitRepo extends JpaRepository<Habit,Long> {
    boolean existsByUserIdAndTitle(Long userId, String title);
    List<Habit> findByUserId(Long userId);

    long countByUserId(Long userId);
}
