package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.DeleteWorkoutRequest;
import com.nashss.se.fittrack.activity.results.DeleteWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

import javax.inject.Inject;

public class DeleteWorkoutActivity {
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new DeleteWorkoutActivity object.
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public DeleteWorkoutActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by deleting the workout from the database.
     * It then returns the workout.
     * @param deleteWorkoutRequest request object containing the workout date.
     * @return deleteWorkoutResult object containing the API defined.
     */
    public DeleteWorkoutResult handleRequest(final DeleteWorkoutRequest deleteWorkoutRequest) {
        String requestedDate = deleteWorkoutRequest.getDate();
        String requestedEmail = deleteWorkoutRequest.getEmail();
        Workout workout = deleteWorkoutRequest.getWorkout(requestedDate, requestedEmail);
        WorkoutModel workoutModel = new ModelConverter().toWorkoutModel(workout);

        workoutDao.deleteWorkout(workout);

        return DeleteWorkoutResult.builder()
                .withWorkout(workoutModel)
                .build();
    }
}
