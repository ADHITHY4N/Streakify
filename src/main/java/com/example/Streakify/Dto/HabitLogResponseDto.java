package com.example.Streakify.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HabitLogResponseDto {

    private Long id;
    private Long habitId;
    private LocalDate logDate;
    private boolean completed;

    public HabitLogResponseDto(){}

    public HabitLogResponseDto(Long id, Long habitId,
                               LocalDate logDate,
                               boolean completed){
        this.id = id;
        this.habitId = habitId;
        this.logDate = logDate;
        this.completed = completed;
    }


}
