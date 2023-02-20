package com.nashss.se.fittrack.activity;
import com.nashss.se.fittrack.activity.requests.GetAllWorkoutsRequest;
import com.nashss.se.fittrack.activity.results.GetAllWorkoutsResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.WorkoutDao;
import com.nashss.se.fittrack.dynamodb.models.Workout;
import com.nashss.se.fittrack.models.WorkoutModel;

import javax.inject.Inject;
import java.util.List;

public class GetAllWorkoutsActivity {
    private final WorkoutDao workoutDao;

    @Inject
    public GetAllWorkoutsActivity(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    public GetAllWorkoutsResult handleRequest(final GetAllWorkoutsRequest getAllWorkoutsRequest) {
        List<Workout> workoutsList = workoutDao.getAllWorkouts(getAllWorkoutsRequest.getEmail());
        List<WorkoutModel> workoutModelList = new ModelConverter().toWorkoutModel(workoutsList);

        return GetAllWorkoutsResult.builder()
                .withWorkout(workoutModelList)
                .build();
    }

}
