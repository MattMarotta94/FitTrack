package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.GetWorkoutRequest;
import com.nashss.se.fittrack.activity.results.GetWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

import java.util.Date;
import javax.inject.Inject;

/**
 * Implementation of the GetWorkoutActivity for the FitTrack's GetWorkout API.
 * This API allows users to get one of their saved workouts.
 */
public class GetWorkoutActivity {
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new GetWorkoutActivity object.
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public GetWorkoutActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by retrieving the workout from the database.
     * It then returns the workout.
     * @param getWorkoutRequest request object containing the workout date.
     * @return getWorkoutResult object containing the API defined.
     */
    public GetWorkoutResult handleRequest(final GetWorkoutRequest getWorkoutRequest) {
        Date requestedDate = getWorkoutRequest.getDate();
        Workout workout = workoutDao.getWorkout(requestedDate);
        WorkoutModel workoutModel = new ModelConverter().toWorkoutModel(workout);

        return GetWorkoutResult.builder()
                .withWorkout(workoutModel)
                .build();
    }
}
