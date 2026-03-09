package com.example.Streakify.Controller;

import com.example.Streakify.Dto.DashboardResponseDto;
import com.example.Streakify.Service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class DashboardController {
private final DashboardService dashboardService;

public DashboardController(DashboardService dashboardService){
    this.dashboardService=dashboardService;
}
@GetMapping("/{userId}/dashboard")
public DashboardResponseDto getDashboard(@PathVariable Long userId){
    return dashboardService.getDashboard(userId);

}

}
