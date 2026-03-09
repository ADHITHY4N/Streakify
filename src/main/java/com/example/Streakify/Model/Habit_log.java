package com.example.Streakify.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


import java.time.LocalDate;
@Entity
@Table(name = "habit_logs",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"habit_id","date"})
        })

public class Habit_log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate logDate;

    @Column(nullable = false)
    private boolean completed;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "habit_id",nullable = false)
    private Habit habit;


    public Habit_log(){}
    public Habit_log(LocalDate logDate,boolean completed,Habit habit){
        this.logDate=logDate;
        this.completed=completed;
        this.habit=habit;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDate logDate) {
        this.logDate = logDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
