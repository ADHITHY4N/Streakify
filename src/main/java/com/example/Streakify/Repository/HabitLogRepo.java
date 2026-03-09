package com.example.Streakify.Repository;

import com.example.Streakify.Model.Habit_log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitLogRepo extends JpaRepository<Habit_log,Long> {
    boolean existsByHabit_IdAndLogDate (long habit_Id, LocalDate logDate);

    List<Habit_log>findByHabit_IdOrderByLogDateAsc(long habitId);

     Optional<Habit_log>findByHabit_IdAndLogDate(long habitId, LocalDate logDate);

    long countByHabit_IdAndLogDateBetweenAndCompletedTrue(long habitId, LocalDate start, LocalDate end);

     List<Habit_log> findByHabit_IdAndCompletedTrueOrderByLogDateAsc(long habitId);

    long countByHabit_UserIdAndLogDateAndCompletedTrue(Long userId, LocalDate now);

    boolean existsByHabit_IdAndLogDateAfter(long id, LocalDate localDate);


    List<Habit_log> findByHabitId(long id);
}
