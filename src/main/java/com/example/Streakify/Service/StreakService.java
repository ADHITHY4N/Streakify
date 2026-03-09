package com.example.Streakify.Service;

import com.example.Streakify.Dto.StreakResponseDto;
import com.example.Streakify.Model.Habit_log;
import com.example.Streakify.Repository.HabitLogRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StreakService {
    private final HabitLogRepo repo;

    public StreakService(HabitLogRepo repo) {
        this.repo = repo;
    }

    public StreakResponseDto getStreak(long habitId) {
        List<Habit_log> logs = repo.findByHabit_IdAndCompletedTrueOrderByLogDateAsc(habitId);
        int longest=0;
        int run=0;
        LocalDate prev=null;


        for (Habit_log log:logs){

//            if(!log.isCompleted()){
//                run=0;
//                prev=null;
//                continue;
//            }
            LocalDate d=log.getLogDate();
            if(prev==null){
                run=1;
            } else if (d.equals(prev.plusDays(1))) {
                run++;
            } else if (d.equals(prev)) {
                continue;
            }else{
                run=1;
            }
            if(run>longest){
                longest=run;
            }
            prev=d;
        }
        int current=0;
        LocalDate expectedDate = LocalDate.now();

        boolean todayCompleted = logs.stream()
                .anyMatch(log -> log.getLogDate().equals(LocalDate.now()) && log.isCompleted());

        if (!todayCompleted) {
            expectedDate = expectedDate.minusDays(1);
        }

        for (int i = logs.size() - 1; i >= 0; i--) {
            LocalDate logDate = logs.get(i).getLogDate();

            if (logDate.equals(expectedDate)) {
                current++;
                expectedDate = expectedDate.minusDays(1);
            } else if (logDate.isAfter(expectedDate)) {
                continue;
            } else {
                break;
            }
        }

        return new StreakResponseDto(current, longest);

    }
}
