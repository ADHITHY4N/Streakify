package com.example.Streakify.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name="created_at")
    private LocalDateTime createdate;

    @PrePersist
    public void onCreate(){
        this.createdate =LocalDateTime.now();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public void setCreatedate(LocalDateTime createdate) {
        this.createdate = createdate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public  String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedate() {
        return createdate;
    }






}
