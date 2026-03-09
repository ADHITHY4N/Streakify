package com.example.Streakify.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DashboardResponseDto {
    private long totalHabits;
    private long activeHabits;
    private long completedToday;
    private List<StreakSummaryDto> currentStreaks;
    private int consistencyScore;



}
