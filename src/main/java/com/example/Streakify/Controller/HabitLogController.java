package com.example.Streakify.Controller;

import com.example.Streakify.Dto.HabitLogRequestDto;
import com.example.Streakify.Dto.HabitLogResponseDto;
import com.example.Streakify.Model.Habit_log;
import com.example.Streakify.Service.HabitLogService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class HabitLogController {
    private final HabitLogService habitLogService;

    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }
    @PostMapping("/habit/{habit_id}/log")
    public HabitLogResponseDto createLog(@PathVariable long habit_id,
                                         @Valid @RequestBody HabitLogRequestDto dto){
        return habitLogService.createLog(habit_id,dto);

    }
    @GetMapping("/habit/{habit_id}/log")
    public List<HabitLogResponseDto> getAllLogs(@PathVariable long habit_id){
        return habitLogService.getAllLogs(habit_id);
    }

    @PutMapping("/habit/{habit_id}/{logDate}")
    public HabitLogResponseDto updateLog(@PathVariable long habit_id, @PathVariable LocalDate logDate,@Valid @RequestBody HabitLogRequestDto dto){
        return habitLogService.updateLog(habit_id,logDate,dto);
    }
    @GetMapping("/habit/{habit_id}/weekly-status")
    public String weeklyStatus(@PathVariable long habit_id){
        return habitLogService.getWeeklyStatus(habit_id);
    }

    
}
