package com.example.Streakify.Service;

import com.example.Streakify.Dto.HabitRequestDto;
import com.example.Streakify.Dto.HabitResponseDto;
import com.example.Streakify.Model.Habit;
import com.example.Streakify.Model.User;
import com.example.Streakify.Repository.HabitRepo;
import com.example.Streakify.Repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HabitService {
    private final UserRepo userRepo;
    private final HabitRepo habitRepo;

    public HabitService(UserRepo userRepo,HabitRepo habitRepo){
        this.userRepo=userRepo;
        this.habitRepo=habitRepo;
    }

    public HabitResponseDto CreateHabit(HabitRequestDto dto) {
        if (dto.getUserId() == null) {
            throw new RuntimeException("user_id is required");
        }
        Long userId=dto.getUserId();
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habit habit=new Habit();
        Integer frequency=dto.getFrequency();
        habit.setFrequency(frequency);

        String title=dto.getTitle().trim();
        habit.setTitle(title);
        if(habitRepo.existsByUserIdAndTitle(userId,title)){
            throw new RuntimeException("Habit already exist for the user");
        }
        habit.setUser(user);
        Habit saved= habitRepo.save(habit);
        return new HabitResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getFrequency(),
                saved.getUser().getId(),
                saved.getCreatedAt()
        );


    }

    public List<HabitResponseDto> getHabit(Long userId) {
         List<Habit> habit=habitRepo.findByUserId(userId);
        return habit.stream()
                        .map(h->new HabitResponseDto(
                                h.getId(),
                                h.getTitle(),
                                h.getFrequency(),
                                h.getUser().getId(),
                                h.getCreatedAt()
                        ))
                        .toList();
    }

    public HabitResponseDto updateHabit(Long id, HabitRequestDto dto) {
        Habit habit=habitRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        habit.setTitle(dto.getTitle().trim());
        habit.setFrequency(dto.getFrequency());
        Habit saved=habitRepo.save(habit);
        return new HabitResponseDto(
                saved.getId(),
                saved.getTitle(),
                saved.getFrequency(),
                saved.getUser().getId(),
                saved.getCreatedAt()
        );


    }

    public boolean deleteHabit(Long id) {
        if(!habitRepo.existsById(id)) return false;
        habitRepo.deleteById(id);
        return true;

    }
}
