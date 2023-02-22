package com.nashss.se.fittrack.activity;
import com.nashss.se.fittrack.activity.requests.GetAllWorkoutsRequest;
import com.nashss.se.fittrack.activity.results.GetAllWorkoutsResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

import java.util.List;

import javax.inject.Inject;

/**
 * GetAllWorkoutsActivity.
 */
public class GetAllWorkoutsActivity {
    private final WorkoutDao workoutDao;

    /**
     * Instantiates a GetAllWorkoutsActivity object.
     * @param workoutDao the workoutDao.
     */
    @Inject
    public GetAllWorkoutsActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    /**
     *  * This method handles the incoming request by retrieving the workouts from the database.
     * @param getAllWorkoutsRequest request object containing the users email.
     * @return all workouts under the users email key.
     */
    public GetAllWorkoutsResult handleRequest(final GetAllWorkoutsRequest getAllWorkoutsRequest) {
        List<Workout> workoutsList = workoutDao.getAllWorkouts(getAllWorkoutsRequest.getEmail());
        List<WorkoutModel> workoutModelList = new ModelConverter().toWorkoutModel(workoutsList);

        return GetAllWorkoutsResult.builder()
                .withWorkout(workoutModelList)
                .build();
    }

}
