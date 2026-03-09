package com.example.Streakify.Service;

import com.example.Streakify.Dto.DashboardResponseDto;
import com.example.Streakify.Dto.StreakSummaryDto;
import com.example.Streakify.Model.Habit;
import com.example.Streakify.Model.Habit_log;
import com.example.Streakify.Repository.HabitLogRepo;
import com.example.Streakify.Repository.HabitRepo;
import com.example.Streakify.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
public class DashboardService {
    private final UserRepo userRepo;
    private final HabitRepo habitRepo;
    private final HabitLogRepo habitLogRepo;
    private final StreakService streakService;

    public DashboardService(UserRepo userRepo,HabitRepo habitRepo,HabitLogRepo habitLogRepo,StreakService streakService){
        this.userRepo=userRepo;
        this.habitRepo=habitRepo;
        this.habitLogRepo=habitLogRepo;
        this.streakService=streakService;
    }

    public DashboardResponseDto getDashboard(Long userId) {
        userRepo.findById(userId)
                .orElseThrow(()->
                        new RuntimeException("User not found"));

        long totalHabits=habitRepo.countByUserId(userId);

        long completedToday=habitLogRepo.countByHabit_UserIdAndLogDateAndCompletedTrue(userId, LocalDate.now());

        List<Habit> habits=habitRepo.findByUserId(userId);

        long activeHabits=habits.stream()
                .filter(habit ->
                        habitLogRepo.existsByHabit_IdAndLogDateAfter(
                                habit.getId(),
                                LocalDate.now().minusDays(7)
                        )
                )
                .count();

        List<StreakSummaryDto> streaks=
                habits.stream()
                        .map(habit ->{
                            var streak=streakService.getStreak(habit.getId());
                            return new
                                    StreakSummaryDto(
                                            habit.getTitle(),
                                    streak.getCurrentStreak(),
                                    streak.getLongestStreak()
                            );

                        })
                        .toList();

        int consistencyScore=calculateConsistency(userId);

        return new DashboardResponseDto(
                totalHabits,
                activeHabits,
                completedToday,
                streaks,
                consistencyScore
        );


    }

    private int calculateConsistency(Long userId) {

      List<Habit> habits= habitRepo.findByUserId(userId);
        if(habits.isEmpty()) return 0;
        LocalDate  today=LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);

        long daysPassed=
                ChronoUnit.DAYS.between(startOfWeek,today)+1;

        long completedThisWeek =0;
        for(Habit habit:habits){
            List<Habit_log> logs=
                    habitLogRepo.findByHabitId(habit.getId());
            for(Habit_log log:logs){
                if(log.isCompleted()
                &&!log.getLogDate().isBefore(startOfWeek)
                &&!log.getLogDate().isAfter(today)){
                    completedThisWeek++;
                }
            }
        }

        int totalWeeklyTarget=habits.stream()
                .mapToInt(Habit::getFrequency)
                .sum();

        double expectedTillToday=totalWeeklyTarget*(daysPassed/7.0);

        if(expectedTillToday==0)return 0;
        int score=
                (int)((completedThisWeek*100)/expectedTillToday);
        return Math.min(score,100);



    }
}
