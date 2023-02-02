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


public class CreateWorkoutActivity {

    private final Logger log = LogManager.getLogger();
    private final WorkoutDao workoutDao;

    @Inject
    public CreateWorkoutActivity(WorkoutDao workoutDao){
        this.workoutDao = workoutDao;
    }

    public CreateWorkoutResult handleRequest(final CreateWorkoutRequest createWorkoutRequest){
        log.info("Received CreateWorkoutRequest {}", createWorkoutRequest);

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
