package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.CreateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.CreateWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

public class CreateWorkoutActivity {
    private final WorkoutDao workoutDao;

    public CreateWorkoutActivity(WorkoutDao workoutDao){
        this.workoutDao = workoutDao;
    }

    public CreateWorkoutResult handleRequest(final CreateWorkoutRequest createWorkoutRequest){

        //work in progress

        Workout newWorkout = new Workout();
        newWorkout.setName(createWorkoutRequest.getName());
        newWorkout.setNotes(createWorkoutRequest.getNotes());
        newWorkout.setExerciseList(createWorkoutRequest.getExerciseList());
        newWorkout.setDate(createWorkoutRequest.getDate());

        workoutDao.saveWorkout(newWorkout);

        WorkoutModel workoutModel = new ModelConverter().toWorkoutModel(newWorkout);
        return CreateWorkoutResult.builder()
                .withWorkout(workoutModel)
                .build();

    }
}
