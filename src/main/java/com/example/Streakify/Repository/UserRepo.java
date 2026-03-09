package com.example.Streakify.Repository;

import com.example.Streakify.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

boolean existsByEmail(String email);


    void deleteById(Long id);
}
