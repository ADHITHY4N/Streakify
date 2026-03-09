package com.example.Streakify.Controller;

import com.example.Streakify.Dto.UserRequestDto;
import com.example.Streakify.Dto.UserResponseDto;
import com.example.Streakify.Model.User;
import com.example.Streakify.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService us;


    @PostMapping("/register")
    public UserResponseDto Register(@Valid @RequestBody UserRequestDto dto){
        return  us.register(dto);


    }
    @GetMapping("/users/{id}")
    public UserResponseDto getProfile(@PathVariable Long id){
        return us.getProfile(id);

    }
    @DeleteMapping("/users/{id}")
    public String deleteAccount(@PathVariable Long id){
        boolean deleted = us.deleteAccount(id);
        if(deleted){
            return "user with id"+id+"deleted successfully";
        }else{
            return "user with id"+id+"not found";
        }
    }




}
