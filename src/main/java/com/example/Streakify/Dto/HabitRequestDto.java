package com.example.Streakify.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HabitRequestDto {
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Frequency is required")
    @Min(value = 1, message = "Min value is 1")
    @Max(value = 7, message = "Max value is 7")
    private Integer frequency;

    @NotNull(message = "UserId is required")
    private Long userId;

}
