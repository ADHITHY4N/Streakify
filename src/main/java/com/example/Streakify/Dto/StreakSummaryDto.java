package com.example.Streakify.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StreakSummaryDto {
    private String habitName;
    private int currentStreak;
    private int longestStreak;

}
