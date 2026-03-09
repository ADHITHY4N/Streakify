package com.example.Streakify.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StreakResponseDto {
    private int currentStreak;
    private int longestStreak;

    public StreakResponseDto(){}

    public StreakResponseDto(int currentStreak,int longestStreak){
        this.currentStreak=currentStreak;
        this.longestStreak=longestStreak;
    }


}
