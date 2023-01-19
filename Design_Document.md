# Design Document

## **Problem Statement**

My fitness tracker (name pending) will provide users with an easy way to keep track of their progress. My program will act as a gym journal and allow users to record their workout data. Users will have the ability to record what exercises they’ve done, what weight they used, the rep range, and/or the time they spent doing said exercise. Each entry will have a section for notes that users can refer back to if they choose to. The fitness tracker will have a date section users will fill out before they record any new data. This will allow users to have their data organized chronologically and further act as a journal. Users will be able to search previous entries by date and view the contents of that entry.

---
## **Top Questions To Resolve in Review**

---
## **Use Cases**
    
* U1. As a user, I want to create a profile that will store all of my entries.
* U2. As a user, I want to select an exercise to add to my workout.
* U3. As a user, I want to add what weight I used for a weight based exercise.
* U4. As a user, I want to add how many repetitions I did for a repetition based exercise.
* U5. As a user, I want to log how long I did an exercise for a timed based exercise.
* U6. As a user, I want to log how many miles I did for a distance based exercise.
* U7. As a user, I want to log a combination of weight/reps/time/distance if the exercise utilizes more than one metric.
* U8. As a user, I want to add notes to my workout.
* U9. As a user, I want to retrieve a single previous workout using the date I logged the workout.
* U10. As a user, I want to view how many times I worked out in a given week/month/year.
---
## **Project Scope**
* ### **In Scope**
  * Creating and retrieving a workout entry.
  * Adding exercise data to a new workout entry.
  * Adding notes to a new workout entry.
  * Retrieving all workouts a user has logged.
* ### **Out of Scope**
  * Editing old entries.
  * Sharing workouts between users.
  * Adding custom exercises.
  * Tagging exercises as favorites.
  * Generating a workout based on body part/what the user wants to focus on.
---
## **Proposed Architecture Overview** 

---
## **API**
Models
```
// WorkoutModel 

Date date; 
String name;
String notes; 
```
```
// ExerciseModel

String exercise;
```
### **Get Workout Endpoint**
* Accepts `GET` request to `/workouts/:date`
* Accepts a Date and returns the corresponding WorkoutModel
  * If the user did not work out that day an empty workout will be returned

### **Create Workout Endpoint**
* Accepts `POST` request to `/workouts`
* Accepts data to create a new workout with a Date, provided name, exercise data, and an optional notes field.
* Workout name will be validated by checking for illegal characters. 
  * If the workout contains any invalid characters, will throw InvalidAttributeException

### **Update Workout Endpoint**
* Accepts `PUT` request to `/workouts/:date`
* Accepts data to update a previous workout including the updated workout name and notes

![Client sends the submit update form the Website Workout page. Website workout page sends an update request to UpdateWorkoutActivity. UpdateWorkoutActivity saves updates to the workout database. ](main/Update_Workout.puml)
   
---
## **Tables**
Workouts
```
date // partition key, string 
name // string
exercises // list
notes // string
```
Exercises
```
exercise // partition key, string
```