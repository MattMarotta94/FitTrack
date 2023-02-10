package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.UpdateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.UpdateWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;

import javax.inject.Inject;

/**
 * Implementation of the UpdateWorkoutActivity for the FitTrack's UpdateWorkout API.
 * This API allows users to update their saved workouts information.
 */
public class UpdateWorkoutActivity {
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new UpdateWorkoutActivity object.
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public UpdateWorkoutActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by retrieving the workout, updating it, and persisting the workout.
     * @param updateWorkoutRequest request object containing the workout date.
     * @return updateWorkoutResult result object containing the API defined.
     */
    public UpdateWorkoutResult handleRequest(final UpdateWorkoutRequest updateWorkoutRequest) {

      Workout workout = workoutDao.getWorkout("fakeemail", updateWorkoutRequest.getDate());

        workout.setDate(updateWorkoutRequest.getDate());
        workout = workoutDao.saveWorkout(workout);

        return UpdateWorkoutResult.builder()
                .withWorkout(new ModelConverter().toWorkoutModel(workout))
                .build();

    }
}
