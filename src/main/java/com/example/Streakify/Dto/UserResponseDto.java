package com.example.Streakify.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter

public class UserResponseDto {
        private Long id;
        private String name;
        private String email;
        private LocalDateTime createdAt;

        public UserResponseDto() {}

        public UserResponseDto(Long id,String name,  String email, LocalDateTime createdAt) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.createdAt = createdAt;
        }

    }
