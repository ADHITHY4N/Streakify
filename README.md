Streakify – Habit Tracking Backend API

Streakify is a backend REST API built with Spring Boot that helps users track daily habits, maintain streaks, and analyze their progress over time.

The application allows users to create habits, record daily completion logs, and view streak statistics and habit analytics through structured API endpoints.

This project demonstrates clean backend architecture, RESTful API design, and database interaction using Spring Data JPA.



Overview

Maintaining consistency in daily habits can be challenging. Streakify provides a backend service that enables users to track their habits and measure progress through streak calculations and activity logs.

The system is designed using a layered architecture, ensuring separation of concerns between request handling, business logic, and database interaction.



Features

Habit Management
*Create and manage habits
*Associate habits with users
*Define habit frequency

Habit Activity Logging
*Record daily habit completion
*Update completion status
*Maintain historical activity records

Streak Tracking
*Calculate current streak
*Calculate longest streak
*Handle missed days and duplicate logs correctly

Dashboard Insights
*Total number of habits
*Weekly activity targets
*Habit completion overview


Technology Stack

| Technology          | Purpose                         |
| ------------------- | ------------------------------- |
|   Java              | Core programming language       |
|   Spring Boot       | Backend application framework   |
|   Spring Data JPA   | Database interaction layer      |
|   Hibernate         | ORM implementation              |
|   MySQL             | Relational database             |
|   Maven             | Dependency and build management |
|   Lombok            | Boilerplate code reduction      |



Architecture

The application follows a layered architecture commonly used in enterprise Spring Boot applications.

Client Request
      │
      ▼
Controller Layer
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
   Database


Layer Responsibilities

Controller Layer
*Handles HTTP requests and returns API responses.

Service Layer
*Contains the core business logic and coordinates application workflows.

Repository Layer
*Manages database operations using Spring Data JPA.

Entity Layer
*Defines the structure of database tables.


Project Structure
Streakify
│
├── controller
│   ├── UserController
│   ├── HabitController
│   ├── HabitLogController
│   ├── StreakController
│   └── DashboardController
│
├── service
│   └── Business logic implementation
│
├── repository
│   └── JPA repositories
│
├── model
│   ├── User
│   ├── Habit
│   └── Habit_log
│
├── dto
│    
└── StreakifyApplication


Database Design
User
Stores application users.
| Field | Type   |
| ----- | ------ |
| id    | Long   |
| name  | String |
| email | String |


Habit
Represents habits created by users.
| Field     | Type   |
| --------- | ------ |
| id        | Long   |
| title     | String |
| frequency | String |
| user      | User   |


Habit Log
Tracks daily habit activity.
| Field     | Type    |
| --------- | ------- |
| id        | Long    |
| logDate   | Date    |
| completed | Boolean |
| habit     | Habit   |




API Endpoints

User APIs
---------
POST /users
GET /users/{id}

Habit APIs
----------
POST /habits
GET /habits/{id}
GET /users/{userId}/habits
DELETE /habits/{id}

Habit Log APIs
--------------
POST /habit-logs
PUT /habit-logs/{id}
GET /habit-logs/{habitId}

Streak Calculation
------------------
GET /habits/{habitId}/streak

Dashboard API
-------------
GET /dashboard/{userId}



Streak Calculation Logic
-------------------------
The streak system works by:
*Retrieving habit logs ordered by date
*Checking if consecutive days are completed
*Incrementing the streak count
*Resetting when a day is missed
*Tracking the longest streak
*This ensures accurate streak tracking even with duplicate logs or missed days.



How to Run the Project

1 Clone the Repository
git clone https://github.com/YOUR_USERNAME/Streakify.git
cd Streakify

2 Configure Database
Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/streakify
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3 Build the Project
mvn clean install

4 Run the Application
mvn spring-boot:run

Server will start at:
http://localhost:8080


Future Improvements
-------------------
JWT Authentication
*Role based access control
*Frontend integration
*Habit reminder notifications
*Analytics dashboard UI

Author

Adhithyan S

LinkedIn
https://www.linkedin.com/in/adhithyan--s/







   
