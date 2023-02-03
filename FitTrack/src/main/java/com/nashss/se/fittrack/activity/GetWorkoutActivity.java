package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.GetWorkoutRequest;
import com.nashss.se.fittrack.activity.results.GetWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

import java.util.Date;
import javax.inject.Inject;


public class GetWorkoutActivity {
    private final WorkoutDao workoutDao;

    @Inject
    public GetWorkoutActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }


    public GetWorkoutResult handleRequest(final GetWorkoutRequest getWorkoutRequest) {
        Date requestedDate = getWorkoutRequest.getDate();
        Workout workout = workoutDao.getWorkout(requestedDate);
        WorkoutModel workoutModel = new ModelConverter().toWorkoutModel(workout);

        return GetWorkoutResult.builder()
                .withWorkout(workoutModel)
                .build();
    }
}
