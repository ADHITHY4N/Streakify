package com.example.Streakify.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class HabitLogRequestDto {

    @NotNull(message = "Log date is required")
    private LocalDate logDate;

    private boolean completed;

}
