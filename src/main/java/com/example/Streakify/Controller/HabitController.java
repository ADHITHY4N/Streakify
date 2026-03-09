package com.example.Streakify.Controller;

import com.example.Streakify.Dto.HabitRequestDto;
import com.example.Streakify.Dto.HabitResponseDto;
import com.example.Streakify.Model.Habit;
import com.example.Streakify.Service.HabitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
public class HabitController {
    @Autowired
    public final HabitService habitService;
    public HabitController(HabitService habitService){
        this.habitService=habitService;
    }
    @PostMapping("/habit")
    public HabitResponseDto CreateHabit(@Valid @RequestBody HabitRequestDto dto){
        return habitService.CreateHabit(dto);

    }
    @GetMapping("/user/{userId}")
    public List<HabitResponseDto> getHabit(@PathVariable Long userId){
        return habitService.getHabit(userId);
    }
    @PostMapping("{id}")
    public HabitResponseDto updateHabit(@PathVariable Long id,@Valid @RequestBody HabitRequestDto dto){
        return habitService.updateHabit(id,dto);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id){
        boolean deleted = habitService.deleteHabit(id);
        if(deleted){
            return ResponseEntity.ok("Habit deleted successfully");

        }return ResponseEntity.status(404).body("Habit ot found");

    }





}
