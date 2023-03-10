# Design Document

## **Problem Statement**

FitTrack will provide users with an easy way to keep track of their progress. My program will act as a gym journal and allow users to record their workout data. Users will have the ability to record what exercises they’ve done, what weight they used, the rep range, and/or the time they spent doing said exercise. Each entry will have a section for notes that users can refer back to if they choose to. The fitness tracker will record what date the workout entry was created. This will allow users to have their data organized chronologically and further act as a journal. Users will be able to search previous entries by date and view the contents of that entry.

---
## **Use Cases**
    
* U1. As a user, I want to create a profile that will store all of my entries.
* U2. As a user, I want to select an exercise to add to my workout.
* U3. As a user, I want to add a weight lifting exercise to my workout, logging weight, sets, and repetitions.
* U4. As a user, I want to add a cardio exercise to my workout, logging distance and time.
* U5. As a user, I want to add a calisthenics exercise to my workout, logging sets and repetitions.
* U6. As a user, I want to add notes to my workout.
* U7. As a user, I want to view a previous workout using the date I logged the workout.
* U8. As a user, I want to edit a previous workout. 
---
## **Project Scope**
* ### **In Scope**
  * Creating and retrieving a workout entry.
  * Adding exercise data to a new workout entry.
  * Adding notes to a new workout entry.
  * Retrieving all workouts a user has logged.
  * Editing old entries.
* ### **Out of Scope**
  * Sharing workouts between users.
  * Adding custom exercises.
  * Tagging exercises as favorites.
  * Generating a workout based on body part/what the user wants to focus on.
  * Allowing users to view their workouts sorted by a date range.
---
## **Proposed Architecture Overview** 

---
## **API**
Models
```
//WorkoutModel 
Date date; 
String name;
String notes; 
List<Exercise> exercises;
```
```
//WeightLiftingModel
String name;
String weight:
int sets;
int reps;
```
```
//CardioModel
String name;
int distance;
double time;
```
```
//CalisthenicsModel
String name;
int sets;
int reps;
```

### **Get Workout Endpoint**
* Accepts `GET` request to `/workouts/:date`
* Accepts a Date and returns the corresponding com.nashss.se.fittrack.models.WorkoutModel.
  * If the user did not work out that day an empty workout will be returned.

### **Create Workout Endpoint**
* Accepts `POST` request to `/workouts/`
* Accepts data to create a new workout with a Date, provided name, exercise data, and an optional notes field.
* Workout name will be validated by checking for illegal characters. 
  * If the workout contains any invalid characters, will throw InvalidAttributeException.

### **Create User Profile**
* Accepts `POST` request to `/user/:workouts`
* Accepts data to create a user profile.

### **Update Workout Endpoint**
* Accepts `PUT` request to `/workouts/:date`
* Accepts data to update a previous workout including the updated workout name, exercise information, and notes.

 
![Client sends the submit update form the Website Workout page. Website workout page sends an update request to UpdateWorkoutActivity. UpdateWorkoutActivity saves updates to the workout database. ](/resources/Update_Workout.png)
   
---
## **Tables**
Workouts
```
userid // partition key, string
date // sort key, string 
name // string
exercises // list
notes // string
```
Exercises
```
exerciseType // partition key, string
exerciseName // sort key, string
metricTypes // stringSet
description // string
```