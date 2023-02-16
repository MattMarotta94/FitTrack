package com.nashss.se.fittrack.activity;
import com.nashss.se.fittrack.activity.requests.CreateWorkoutRequest;
import com.nashss.se.fittrack.activity.results.CreateWorkoutResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
/**
 * Implementation of the CreateWorkoutActivity for the FitTrack's CreateWorkout API.
 * This API allows users to create a new workout entry with a date, name, and empty notes/exercise fields.
 */
public class CreateWorkoutActivity {

    private final Logger log = LogManager.getLogger();
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a new CreateWorkoutActivity object.
     * @param workoutDao WorkoutDao to access the workouts table.
     */
    @Inject
    public CreateWorkoutActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    /**
     * This method handles the incoming request by persisitng a new workout with
     * the provided workout date, name, notes, and exercise list.
     * @param createWorkoutRequest request object containing the workout name, date,
     *                             notes, and exercise list associated with it.
     * @return createWorkoutResult result object containing the API defined.
     */
    public CreateWorkoutResult handleRequest(final CreateWorkoutRequest createWorkoutRequest) {
        log.info("Received CreateWorkoutRequest {}", createWorkoutRequest);


        Workout newWorkout = new Workout();
        newWorkout.setEmail(createWorkoutRequest.getEmail());
        newWorkout.setName(createWorkoutRequest.getName());
        newWorkout.setNotes(createWorkoutRequest.getNotes());
        newWorkout.setExercises(createWorkoutRequest.getExercises());
        newWorkout.setDate(createWorkoutRequest.getDate());

        workoutDao.saveWorkout(newWorkout);

        WorkoutModel workoutModel = new ModelConverter().toWorkoutModel(newWorkout);
        return CreateWorkoutResult.builder()
                .withWorkout(workoutModel)
                .build();

    }
}
