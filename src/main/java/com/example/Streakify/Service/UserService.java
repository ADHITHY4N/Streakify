package com.example.Streakify.Service;

import com.example.Streakify.Dto.UserRequestDto;
import com.example.Streakify.Dto.UserResponseDto;
import com.example.Streakify.Model.User;
import com.example.Streakify.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo ur;
    public UserResponseDto register(UserRequestDto dto){
        String email=dto.getEmail();
        if(email==null){
            throw new RuntimeException("Invalid email");
        }
        User user=new User();
        email=email.trim().toLowerCase();
        user.setName(dto.getName().trim());
        user.setEmail(email);

//        if(!email.contains("@")){
//            throw new RuntimeException("Invalid email");
//        }
        if(ur.existsByEmail(email)){
            throw new RuntimeException("Email already exist");

        }

           User saved=ur.save(user);
        return new UserResponseDto(
                 saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getCreatedate()
        );



    }


    public UserResponseDto getProfile(Long id) {
        User user = ur.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id:"+id));
        return new UserResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedate()
        );


    }
    public boolean deleteAccount(Long id) {
       if(ur.existsById(id)){
            ur.deleteById(id);
            return true;
       }
       return false;
    }
}
