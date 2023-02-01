package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.UpdateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.UpdateWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;

public class UpdateWorkoutActivity {
    private final WorkoutDao workoutDao;

    public UpdateWorkoutActivity(WorkoutDao workoutDao){
        this.workoutDao = workoutDao;
    }

    public UpdateWorkoutResult handleRequest(final UpdateWorkoutRequest updateWorkoutRequest){

       // work in progress

        Workout workout = workoutDao.getWorkout(updateWorkoutRequest.getDate());

        workout.setDate(updateWorkoutRequest.getDate());
        workout = workoutDao.saveWorkout(workout);

        return UpdateWorkoutResult.builder()
                .withWorkout(new ModelConverter().toWorkoutModel(workout))
                .build();

    }
}
