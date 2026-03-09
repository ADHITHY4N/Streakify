package com.example.Streakify.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class HabitResponseDto {
    private Long id;
    private String title;
    private Integer frequency;
    private Long userId;
    private LocalDateTime createdAt;

    public HabitResponseDto(){}

    public HabitResponseDto(Long id,String title,Integer frequency,Long userId,LocalDateTime createdAt){
        this.id = id;
        this.title = title;
        this.frequency = frequency;
        this.userId = userId;
        this.createdAt = createdAt;
    }



}
