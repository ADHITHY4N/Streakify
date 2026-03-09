package com.example.Streakify.Service;

import com.example.Streakify.Dto.HabitLogRequestDto;
import com.example.Streakify.Dto.HabitLogResponseDto;
import com.example.Streakify.Dto.HabitResponseDto;
import com.example.Streakify.Model.Habit;
import com.example.Streakify.Model.Habit_log;
import com.example.Streakify.Repository.HabitLogRepo;
import com.example.Streakify.Repository.HabitRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class HabitLogService {
    private final HabitLogRepo habitLogRepo;
    private final HabitRepo habitRepo;

    public HabitLogService(HabitLogRepo habitLogRepo,
                           HabitRepo habitRepo) {
        this.habitLogRepo = habitLogRepo;
        this.habitRepo = habitRepo;
    }



    public HabitLogResponseDto createLog(long habit_Id, HabitLogRequestDto dto) {

       Habit  habit=habitRepo.findById(habit_Id)
                .orElseThrow(()->new RuntimeException("habit not found"));

       Habit_log habitLog=new Habit_log();
        if(dto.getLogDate()!=null) {
            habitLog.setLogDate(dto.getLogDate());
        }else{
            habitLog.setLogDate(LocalDate.now());
        }
        habitLog.setCompleted(dto.isCompleted());
        if(habitLogRepo.existsByHabit_IdAndLogDate(habit_Id,habitLog.getLogDate())){
            throw new RuntimeException("Log already exists for this date");
        }
        if(dto.getLogDate().isAfter(LocalDate.now())){
            throw new RuntimeException("Future date not Possible");
        }
        habitLog.setHabit(habit);
        Habit_log saved= habitLogRepo.save(habitLog);
        return new HabitLogResponseDto(
                saved.getId(),
                saved.getHabit().getId(),
                saved.getLogDate(),
                saved.isCompleted()
        );
        

    }

    public List<HabitLogResponseDto> getAllLogs(long habitId) {
        habitRepo.findById(habitId)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

         List<Habit_log> saved= habitLogRepo.findByHabit_IdOrderByLogDateAsc(habitId);
        return saved.stream()
                .map(log -> new HabitLogResponseDto(
                        log.getId(),
                        habitId,
                        log.getLogDate(),
                        log.isCompleted()

                ))
                .toList();

    }

    public HabitLogResponseDto updateLog(long habitId,LocalDate logDate,HabitLogRequestDto dto) {

        if (logDate == null) {
            throw new RuntimeException("logDate is required to update log");
        }

        Habit_log existing = habitLogRepo
                .findByHabit_IdAndLogDate(habitId, logDate)
                .orElseThrow(() -> new RuntimeException("Log not found for this date"));

        existing.setCompleted(dto.isCompleted());

       Habit_log saved= habitLogRepo.save(existing);
       return new HabitLogResponseDto(
               saved.getId(),
               saved.getHabit().getId(),
               saved.getLogDate(),
               saved.isCompleted()

       );
    }


    public String getWeeklyStatus(long habitId) {

            Habit habit = habitRepo.findById(habitId)
                    .orElseThrow(() -> new RuntimeException("Habit not found"));

            LocalDate today = LocalDate.now();

            LocalDate start = today.minusDays(today.getDayOfWeek().getValue() - 1L);

            LocalDate end = start.plusDays(6);

            Long completedCount = habitLogRepo
                    .countByHabit_IdAndLogDateBetweenAndCompletedTrue(habitId, start, end);

            int target = habit.getFrequency();

            if (completedCount < target) {
                return "In Progress";
            }
            else if (completedCount == target) {
                return "Target Achieved";
            }
            else {
                return "Target Exceed, Keep Going";
            }
        }

    }

