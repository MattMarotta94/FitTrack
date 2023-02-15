package com.nashss.se.fittrack.activity;

import com.nashss.se.fittrack.activity.requests.GetExerciseRequest;
import com.nashss.se.fittrack.activity.results.GetExerciseResult;
import com.nashss.se.fittrack.converters.ModelConverter;
import com.nashss.se.fittrack.dynamodb.ExerciseDao;
import com.nashss.se.fittrack.dynamodb.models.Exercise;
import com.nashss.se.fittrack.models.ExerciseModel;

import javax.inject.Inject;

/**
 * GetExerciseActivity.
 */
public class GetExerciseActivity {
    private final ExerciseDao exerciseDao;

    /**
     * Instantiates a GetExerciseActivity object.
     * @param exerciseDao the exerciseDao.
     */
    @Inject
    public GetExerciseActivity(ExerciseDao exerciseDao) {
        this.exerciseDao = exerciseDao;
    }
    /**
     * This method handles the incoming request by retrieving the exercise from the database.
     * It then returns the exercise.
     * @param getExerciseRequest request object containing the workout date.
     * @return getExerciseResult object containing the API defined.
     */
    public GetExerciseResult handleRequest(final GetExerciseRequest getExerciseRequest) {
        String requestedType = getExerciseRequest.getType();
        String requestedName = getExerciseRequest.getName();
        Exercise exercise = exerciseDao.getExercise(requestedType, requestedName);
        ExerciseModel exerciseModel = new ModelConverter().toExerciseModel(exercise);

        return GetExerciseResult.builder()
                .withExercise(exerciseModel)
                .build();
    }

}
