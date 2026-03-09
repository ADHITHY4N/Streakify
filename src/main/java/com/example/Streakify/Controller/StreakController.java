package com.example.Streakify.Controller;

import com.example.Streakify.Dto.StreakResponseDto;
import com.example.Streakify.Service.StreakService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StreakController {
    private final StreakService streakService;

    public StreakController(StreakService streakService){
        this.streakService=streakService;

    }
    @GetMapping("/habits/{habitId}/streak")
    public StreakResponseDto getStreak(@PathVariable long habitId){
        return streakService.getStreak(habitId);
    }
    }

