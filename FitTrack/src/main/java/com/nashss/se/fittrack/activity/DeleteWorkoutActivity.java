package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.DeleteWorkoutRequest;
import com.nashss.se.fittrack.activity.results.DeleteWorkoutResult;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the DeleteWorkoutActivity for the FitTrack's DeleteWorkout API.
 */
public class DeleteWorkoutActivity {
    private final WorkoutDao workoutDao;

    private final Logger log = LogManager.getLogger();

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
        log.info("email", deleteWorkoutRequest.getEmail());
        log.info("date", deleteWorkoutRequest.getDate());
        String requestedDate = deleteWorkoutRequest.getDate();
        String requestedEmail = deleteWorkoutRequest.getEmail();
        log.info("handle request 1st", deleteWorkoutRequest);

        workoutDao.deleteWorkout(requestedDate, requestedEmail);

        log.info("handle request 2nd", deleteWorkoutRequest);

        return DeleteWorkoutResult.builder()
                .withString("Deleted Successfully")
                .build() ;
    }
}
